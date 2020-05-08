package 서버;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	// 연결할 포트를 지정합니다.
	private static final int PORT = 5000;
	// 스레드 풀의 최대 스레드 개수를 지정합니다.
	private static final int THREAD_CNT = 50;
	private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_CNT);

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {

			// 서버소켓 생성
			serverSocket = new ServerSocket(PORT);

			// 소켓을 호스트의 포트와 binding
			String localHostAddress = InetAddress.getLocalHost().getHostAddress();
			System.out.println("[server] binding! \naddress:" + localHostAddress + ", port:" + PORT);

			System.out.println("<클라이언트 접속 대기중...>");

			// 소켓서버가 종료될때까지 무한루프
			while (true) {
				// 소켓 접속 요청이 올때까지 대기합니다.
				Socket socket = serverSocket.accept();

				// 연결이 되었다는 메시지 출력
				InetSocketAddress remoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
				String remoteHostName = remoteSocketAddress.getAddress().getHostAddress();
				int remoteHostPort = remoteSocketAddress.getPort();
				System.out.println("[server] connected! \nconnected socket address:" + remoteHostName + ", port:"
						+ remoteHostPort);

				try {
					// 요청이 오면 스레드 풀의 스레드로 소켓을 넣어줍니다.
					// 이후는 스레드 내에서 처리합니다.
					threadPool.execute(new ConnectionWrap(socket));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class ConnectionWrap implements Runnable {

	Socket socket = null;
	int cnt;

	public ConnectionWrap(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		try {
			// 스트림
			OutputStream output = socket.getOutputStream();
			InputStream input = socket.getInputStream();

			// DB connection
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("local", "root", "password");

			// 서버처리담당객체생성
			LOGIN_HANDLER login_handler = new LOGIN_HANDLER(input, output, conn);
			FILE_HANDLER file_handler = new FILE_HANDLER(input, output, conn);
			VIEW_HANDLER view_handler = new VIEW_HANDLER(input, output, conn);
			RENEW_HANDLER renew_handler = new RENEW_HANDLER(input, output, conn);

			// 프로토콜
			Protocol protocol;

			// 학생,로그인 관련 변수
			int LoginFailCnt = 0;
			int StudentNumber = -1;
			boolean exit = false;

			// 받은패킷처리
			while (true) {
				protocol = new Protocol();
				byte[] header = protocol.getPacket();
				input.read(header);
				int protocolType = header[0];
				int protocolCode = header[1];
				int bodylength = protocol.byteToInt(header, 2);
				if (bodylength != 0) {
					byte[] body = new byte[bodylength];
					input.read(body);
					protocol.setPacket(header, body);
				}

				InetSocketAddress remoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
				String remoteHostName = remoteSocketAddress.getAddress().getHostAddress();

				switch (protocolType) {
				case Protocol.EXIT: // 프로그램 종료 수신
					input.close();
					output.close();
					socket.close();

					System.out.println("[" + remoteHostName + " 클라이언트 정상종료]");
					exit = true;
					break;
				case Protocol.TYPE1_LOGIN_REQ: // 로그인 인증요청 수신 -> 로그인 인증 요청에 대한 응답 발신
					System.out.println("[" + remoteHostName + " 클라이언트가 로그인인증요청을 보냄]");
					login_handler.login(protocol, LoginFailCnt);
					StudentNumber = login_handler.getStudentNum();
					break;
				case Protocol.TYPE3_FILE_SEND_REQ: // 파일 전송 요청수신 -> 파일전송요청에 대한 응답 발신 -> 파일전송 -> 파일전송성공여부 발신
					System.out.println("[" + remoteHostName + " 클라이언트가 파일송신요청 메세지를 보냄]");
					switch (protocolCode) {
					case Protocol.T3_CD0_GYEOLHAEK:
						file_handler.GYEOLHAEK_SEND(protocol, header, StudentNumber);
						break;
					case Protocol.T3_CD1_IPSASEOYAKSEO:
						file_handler.IPSASEOYAKSEO(protocol);
						break;
					}
					break;

				case Protocol.TYPE7_VIEW_REQ: // 조회요청 -> 조회요청에 대한 응답 발신
					System.out.println("[" + remoteHostName + " 클라이언트가 조회요청 메세지를 보냄]");
					switch (protocolCode) {
					case Protocol.T7_CD0_학적내역조회요청:
						view_handler.CODE00(protocol);
						break;
					case Protocol.T7_CD1_생활관비조회요청:
						view_handler.CODE01(protocol);
						break;
					case Protocol.T7_CD2_호실조회요청:
						view_handler.CODE02(protocol, StudentNumber);
						break;
					case Protocol.T7_CD3_입사지원내역조회요청:
						view_handler.CODE03(protocol, StudentNumber);
						break;
					case Protocol.T7_CD4_선발결과조회요청:
						view_handler.CODE04(protocol, StudentNumber);
						break;
					case Protocol.T7_CD5_해당결핵진단서존재여부조회요청:
						view_handler.CODE05(protocol, StudentNumber);
						break;
					case Protocol.T7_CD6_입사신청자세부내역조회요청:
						view_handler.CODE06(protocol);
						break;
					case Protocol.T7_CD7_입사신청자조회요청:
						view_handler.CODE07(protocol);
						break;
					case Protocol.T7_CD8_입사선발자조회요청:
						view_handler.CODE08(protocol);
						break;
					case Protocol.T7_CD9_입사자조회요청:
						view_handler.CODE09(protocol);
						break;
					case Protocol.T7_CD10_결핵진단서제출현황조회요청:
						view_handler.CODE10(protocol);
						break;
					case Protocol.T7_CD11_사용료조회:
						view_handler.CODE11(protocol);
						break;
					case Protocol.T7_CD12_급식비조회:
						view_handler.CODE12(protocol);
						break;
					case Protocol.T7_CD13_선발일정조회:
						view_handler.CODE13(protocol);
						break;
					case Protocol.T7_CD14_고지서조회요청:
						view_handler.CODE14(protocol, StudentNumber);
						break;
					}
					break;

				case Protocol.TYPE9_RENEW_REQ: // 갱신요청 -> 갱신요청에 대한 응답 발신
					System.out.println("[" + remoteHostName + " 클라이언트가 갱신요청 메세지를 보냄]");
					switch (protocolCode) {
					case Protocol.T9_CD0_입사신청요청:
						renew_handler.CODE00(protocol, StudentNumber);
						break;
					case Protocol.T9_CD1_입사신청자를입사선발자로등록요청:
						renew_handler.CODE01(protocol);
						break;
					case Protocol.T9_CD2_입사선발자를입사자로등록요청:
						renew_handler.CODE02(protocol);
						break;
					case Protocol.T9_CD3_진단일시등록요청:
						renew_handler.CODE03(protocol);
						break;
					case Protocol.T9_CD4_입사신청자를목록에서삭제요청:
						renew_handler.CODE04(protocol);
						break;
					case Protocol.T9_CD5_입사선발자를목록에서삭제요청:
						renew_handler.CODE05(protocol);
						break;
					case Protocol.T9_CD6_입사자를목록에서삭제요청:
						renew_handler.CODE06(protocol);
						break;
					case Protocol.T9_CD7_진단일시삭제요청:
						renew_handler.CODE07(protocol);
						break;
					case Protocol.T9_CD8_선발일정등록요청:
						renew_handler.CODE08(protocol);
						break;
					case Protocol.T9_CD9_사용료등록요청:
						renew_handler.CODE09(protocol);
						break;
					case Protocol.T9_CD10_급식비등록요청:
						renew_handler.CODE10(protocol);
						break;
					case Protocol.T9_CD11_납부:
						renew_handler.CODE11(protocol, StudentNumber);
						break;
					}
					break;

				case Protocol.TYPE11_FILE_RECEIVE_REQ:
					System.out.println("[" + remoteHostName + " 클라이언트가 파일수신요청 메세지를 보냄]");
					switch (protocolCode) {
					case Protocol.T11_CD0_GYEOLHAEK:
						file_handler.GYEOLHAEK_RECEIVE(protocol);
						break;
					}
					break;
				}

				if (exit) {
					break;
				}
			}

		} catch (IOException e) {
		} catch (ClassNotFoundException d) {
		} catch (SQLException a) {
		}
	}
}
