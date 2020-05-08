package 서버;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FILE_HANDLER {

	InputStream input;
	OutputStream output;
	Connection conn;

	FILE_HANDLER(InputStream input, OutputStream output, Connection conn) {
		this.input = input;
		this.output = output;
		this.conn = conn;
	}

	void GYEOLHAEK_SEND(Protocol protocol, byte[] header, int StudentNumber) throws IOException, SQLException {
		Statement stmt = null;
		Date date = new Date();		//제출일자 확인을 위한 date 객체 설정
		SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
		String[] temp = protocol.getString().split("/");
		
		//파일 전송 요청 승인
		protocol = new Protocol(Protocol.TYPE4_FILE_SEND_RES, Protocol.T4_CD0_ACCEPT);
		output.write(protocol.getPacket());

		// image배열만들기
		int filesize = Integer.parseInt(temp[1]);
		byte[] image = new byte[filesize];
		for (int i = 0; i < filesize;) {
			protocol = new Protocol();
			header = protocol.getPacket();
			input.read(header);
			byte[] body1 = new byte[protocol.getLength()];
			input.read(body1);
			System.arraycopy(body1, 0, image, i, protocol.getLength());
			i += protocol.getLength();
		}
		String studentFileName = Integer.toString(StudentNumber) + ".jpg";	//파일 이름 설정
		String serverPath = "C:\\결핵진단서\\" + studentFileName;	//파일 저장 경로 설정
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(serverPath));
		bos.write(image);
		bos.close();

		// DB에 등록
		String sql = "insert into 결핵진단서 (년도,학기,학번,제출서류구분,파일명,제출일시,서버파일경로) values ('2020','1','" + StudentNumber
				+ "','결핵진단서','" + studentFileName + "','" + today.format(date) + "','" + serverPath
				+ "') on duplicate key update 파일명='" + studentFileName + "',제출일시='" + today.format(date) + "'";
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);

		// 파일수신 응답
		protocol = new Protocol(Protocol.TYPE6_FILE_RES, Protocol.T6_CD0_SUCCESS);
		output.write(protocol.getPacket());
	}

	// 결핵진단서 파일 다운로드
	void GYEOLHAEK_RECEIVE(Protocol protocol) throws IOException, SQLException {

		String stdNum = protocol.getString();

		// 서버컴퓨터에 학번으로 저장되어있음
		String serverPath = "C:\\결핵진단서\\" + stdNum + ".jpg";

		// 지정된 경로의 파일명,크기 패킷에 저장
		File file = new File(serverPath);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		protocol = new Protocol(Protocol.TYPE3_FILE_SEND_REQ, Protocol.T3_CD0_GYEOLHAEK);
		String gyeolhaek = file.getName() + "/" + Long.toString(file.length());
		protocol.setString(gyeolhaek);
		output.write(protocol.getPacket());

		// 파일전송요청에 대한 응답
		protocol = new Protocol();
		byte[] header = protocol.getPacket();
		input.read(header);
		System.out.println(protocol.getType() + " " + protocol.getCode());

		// 파일전송
		byte[] f = new byte[(int) file.length()];
		bis.read(f);
		// f를 filesize로 잘라서 보내기
		if (file.length() < Protocol.LEN_FILE) {// flag를 사용하지 않는 경우
			protocol = new Protocol(Protocol.TYPE5_FILE_SEND, Protocol.T5_CD0_GYEOLHAEK, f);
			output.write(protocol.getPacket());
		} else {// flag를 사용하는 경우
			for (int i = (int) file.length(), j = 0; i >= 0; i -= Protocol.LEN_FILE, j++) {
				if (i < Protocol.LEN_FILE) {
					byte[] temp = new byte[i];
					System.arraycopy(f, j * Protocol.LEN_FILE, temp, 0, i);
					protocol = new Protocol(Protocol.TYPE5_FILE_SEND, Protocol.T5_CD0_GYEOLHAEK, 1, 1, j, temp);
					output.write(protocol.getPacket());
				} else {
					byte[] temp = new byte[Protocol.LEN_FILE];
					System.arraycopy(f, j * Protocol.LEN_FILE, temp, 0, Protocol.LEN_FILE);
					protocol = new Protocol(Protocol.TYPE5_FILE_SEND, Protocol.T5_CD0_GYEOLHAEK, 1, 0, j, temp);
					output.write(protocol.getPacket());
				}
			}

			bis.close();

			// 파일전송결과 받기
			protocol = new Protocol();
			header = protocol.getPacket();
			input.read(header);
			System.out.println(protocol.getType() + " " + protocol.getCode());

			System.out.println("[성공]");

		}
	}

	void IPSASEOYAKSEO(Protocol protocol) throws IOException, SQLException {
	}

}
