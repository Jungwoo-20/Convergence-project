package 클라이언트;

import 클라이언트.관리자GUI.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.*;

public class Onestop_Admin extends JFrame {

	public Onestop_Admin(Socket socket, InputStream input, OutputStream output, String std) {
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

		// frame 설정
		setVisible(true);
		setTitle("생활관 관리 시스템 _ 관리자용  " + std);
		setBounds(100, 10, 1250, 850);
		setLayout(new BorderLayout(0, 0));

		// GUI객체생성, Tab에추가
		ScheduleUpload scheduleUpload = new ScheduleUpload(input, output);
		FeeUpload feeUpload = new FeeUpload(input, output);
		JoinedStudent joinedStudent = new JoinedStudent(input, output);
		SelectedStudent selectedStudent = new SelectedStudent(input, output);
		TuberMCUpload tuberMCUpload = new TuberMCUpload(input, output);

		JTabbedPane Tab_Admin = new JTabbedPane(JTabbedPane.TOP);
		Tab_Admin.addTab("선발일정 등록", scheduleUpload);
		Tab_Admin.addTab("생활관 사용료 및 급식비 등록", feeUpload);
		Tab_Admin.addTab("입사신청자 조회 및 입사선발자 등록", joinedStudent);
		Tab_Admin.addTab("입사선발자 결과등록", selectedStudent);
		Tab_Admin.addTab("결핵진단서 제출확인 및 업로드", tuberMCUpload);
		add(Tab_Admin);

	}
}