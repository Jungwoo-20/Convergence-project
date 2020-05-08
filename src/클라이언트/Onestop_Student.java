package 클라이언트;

import 클라이언트.학생GUI.DorApplication;
import 클라이언트.학생GUI.DorContentCheck;
import 클라이언트.학생GUI.DorRoomCheck;
import 클라이언트.학생GUI.TuberMCSubmit;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Onestop_Student extends JFrame {

	public Onestop_Student(Socket socket, InputStream input, OutputStream output, String std) throws IOException {

		// 창닫기버튼클릭시 서버에 종료메세지보내고 종료
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					Protocol protocol = new Protocol(Protocol.EXIT);
					output.write(protocol.getPacket());
					input.close();
					output.close();
					socket.close();
				} catch (IOException a) {
				}
				System.out.println("[클라이언트정상종료]");
				System.exit(0);
			}
		});

		// Frame 설정
		setVisible(true);
		setTitle("생활관 관리 시스템 _ 학생용" + std);
		setBounds(100, 10, 1250, 800);

		// GUI객체생성, Tab에추가
		DorApplication dorApplication = new DorApplication(input, output);
		DorRoomCheck dorRoomCheck = new DorRoomCheck(input, output);
		DorContentCheck dorContentCheck = new DorContentCheck(input, output);
		TuberMCSubmit tuberMCSubmit = new TuberMCSubmit(input, output);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("생활관 입사신청", dorApplication);
		tabbedPane.addTab("생활관 호실조회", dorRoomCheck);
		tabbedPane.addTab("생활관 입사신청내역/결과 조회, 고지서 출력", dorContentCheck);
		tabbedPane.addTab("결핵진단서 제출", tuberMCSubmit);
		getContentPane().add(tabbedPane);

		// 결핵진단서 탭 선택 시 이벤트 (이전에 결핵진단서를 냈는지 확인)
		ChangeListener changeListener = new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
				int index = sourceTabbedPane.getSelectedIndex();
				if (sourceTabbedPane.getTitleAt(index).equals("결핵진단서 제출")) {
					try {
						// 서버에 파일이 있는지 존재여부 확인 요청
						Protocol protocol = new Protocol(Protocol.TYPE7_VIEW_REQ, Protocol.T7_CD5_해당결핵진단서존재여부조회요청);
						output.write(protocol.getPacket());

						// 받은 패킷 처리
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
						
						//결핵진단서 존재
						if (protocolType == Protocol.TYPE8_VIEW_RES && protocolCode == Protocol.T8_CD10_해당결핵진단서존재) {
							String result = protocol.getString();
							String[] tmp = result.split("/");
							if (tmp.length < 4) {
								tuberMCSubmit.textField_3.setText(tmp[0]);
								tuberMCSubmit.textField_5.setText(tmp[1]);
							} else {
								tuberMCSubmit.textField_3.setText(tmp[0]);
								tuberMCSubmit.textField_4.setText(tmp[1]);
								tuberMCSubmit.textField_5.setText(tmp[2]);
							}
							JOptionPane.showMessageDialog(null, "이미 제출하였습니다.");
						}
						//결핵진단서 존재하지 않음
						else if (protocolType == Protocol.TYPE8_VIEW_RES
								&& protocolCode == Protocol.T8_CD11_해당결핵진단서존재하지않음) {
							JOptionPane.showMessageDialog(null, "결핵진단서를 제출해주세요.");
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		};
		tabbedPane.addChangeListener(changeListener);
	}
}
