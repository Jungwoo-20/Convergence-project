package 클라이언트.학생GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import static 클라이언트.Login.ID_textField;
import 클라이언트.Protocol;

public class DorRoomCheck extends Panel {
	public static JTextField 납부여부Text, 식비구분Text, 선발결과Text, 호실번호Text, 생활관Text, 침대번호Text = null;

	public DorRoomCheck(InputStream input, OutputStream output) {

		JLabel label_6, 선발결과, 식비구분, 호실번호, 납부여부, 생활관, 침대번호;

		setBounds(100, 10, 1250, 800);

		setBounds(100, 10, 1250, 800);

		setBackground(Color.WHITE);
		setBounds(100, 100, 1250, 800);
		setLayout(null);

		label_6 = new JLabel("\u25B6 \uC2E0\uCCAD \uC0DD\uD65C\uAD00 \uBC0F \uD638\uC2E4 \uD655\uC778");
		label_6.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_6.setBounds(92, 196, 373, 43);
		add(label_6);

		선발결과 = new JLabel("선발결과");
		선발결과.setHorizontalAlignment(SwingConstants.CENTER);
		선발결과.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		선발결과.setBorder(LineBorder.createGrayLineBorder());
		선발결과.setBounds(92, 289, 150, 43);
		add(선발결과);

		식비구분 = new JLabel("식비구분");
		식비구분.setHorizontalAlignment(SwingConstants.CENTER);
		식비구분.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		식비구분.setBorder(LineBorder.createGrayLineBorder());
		식비구분.setBounds(92, 331, 150, 43);
		add(식비구분);

		호실번호 = new JLabel("호실번호");
		호실번호.setHorizontalAlignment(SwingConstants.CENTER);
		호실번호.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		호실번호.setBorder(LineBorder.createGrayLineBorder());
		호실번호.setBounds(92, 374, 150, 43);
		add(호실번호);

		납부여부 = new JLabel("납부여부");
		납부여부.setHorizontalAlignment(SwingConstants.CENTER);
		납부여부.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		납부여부.setBorder(LineBorder.createGrayLineBorder());
		납부여부.setBounds(600, 289, 150, 43);
		add(납부여부);

		생활관 = new JLabel("생활관");
		생활관.setHorizontalAlignment(SwingConstants.CENTER);
		생활관.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		생활관.setBorder(LineBorder.createGrayLineBorder());
		생활관.setBounds(600, 331, 150, 43);
		add(생활관);

		침대번호 = new JLabel("침대번호");
		침대번호.setHorizontalAlignment(SwingConstants.CENTER);
		침대번호.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		침대번호.setBorder(LineBorder.createGrayLineBorder());
		침대번호.setBounds(600, 374, 150, 43);
		add(침대번호);

		납부여부Text = new JTextField();
		납부여부Text.setHorizontalAlignment(SwingConstants.CENTER);
		납부여부Text.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		납부여부Text.setColumns(10);
		납부여부Text.setBounds(750, 289, 364, 43);
		add(납부여부Text);

		식비구분Text = new JTextField();
		식비구분Text.setHorizontalAlignment(SwingConstants.CENTER);
		식비구분Text.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		식비구분Text.setColumns(10);
		식비구분Text.setBounds(242, 331, 358, 43);
		add(식비구분Text);

		선발결과Text = new JTextField();
		선발결과Text.setHorizontalAlignment(SwingConstants.CENTER);
		선발결과Text.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		선발결과Text.setColumns(10);
		선발결과Text.setBounds(242, 289, 358, 43);
		add(선발결과Text);

		호실번호Text = new JTextField();
		호실번호Text.setHorizontalAlignment(SwingConstants.CENTER);
		호실번호Text.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		호실번호Text.setColumns(10);
		호실번호Text.setBounds(242, 374, 358, 43);
		add(호실번호Text);

		생활관Text = new JTextField();
		생활관Text.setHorizontalAlignment(SwingConstants.CENTER);
		생활관Text.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		생활관Text.setColumns(10);
		생활관Text.setBounds(750, 331, 364, 43);
		add(생활관Text);

		침대번호Text = new JTextField();
		침대번호Text.setHorizontalAlignment(SwingConstants.CENTER);
		침대번호Text.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		침대번호Text.setColumns(10);
		침대번호Text.setBounds(750, 374, 364, 43);
		add(침대번호Text);

		JButton btnNewButton = new JButton("조회");
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnNewButton) { // 조회 버튼 클릭
					try {
						// 호실조회 메시지 전송
						Protocol protocol = new Protocol(Protocol.TYPE7_VIEW_REQ, Protocol.T7_CD2_호실조회요청);
						output.write(protocol.getPacket());

						// 호실조회 요청 결과 수신 후 데이터 처리
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

						// 프로토콜에 대해 요청, 거부 확인
						// 요청에 대해 정상결과
						if (protocolType == Protocol.TYPE8_VIEW_RES && protocolCode == Protocol.T8_CD4_호실조회승인) {
							// String 결과들을 /로 나누어 배열에 저장
							String[] temp = protocol.getString().split("/");
							String selection_result = temp[0]; // 선발결과
							String payment_status = temp[1]; // 납부여부
							String food_expenses = temp[2]; // 식비 구분
							String barracks = temp[3];// 생활관구분
							String room_number = temp[4]; // 호실번호
							String bed_number = temp[5]; // 침대번호
							// 결과 등록
							선발결과Text.setText(selection_result);
							납부여부Text.setText(payment_status);
							식비구분Text.setText(food_expenses);
							생활관Text.setText(barracks);
							호실번호Text.setText(room_number);
							침대번호Text.setText(bed_number);
						}

						// 입사자가 아닌 모든 경우(기숙사 신청 X, 불합격, 입사선발자이지만 결핵 진단서, 미입금 경우 포함)
						// 요청에 대해 결과를 보여주지 않고 팝업창으로 확인 불가 메시지.
						else if (protocolType == Protocol.TYPE8_VIEW_RES && protocolCode == Protocol.T8_CD5_호실조회거절) {
							JOptionPane.showMessageDialog(null, "호실조회가 불가능합니다.");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		btnNewButton.setBounds(1003, 201, 111, 37);
		add(btnNewButton);
	}

}