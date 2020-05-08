package 클라이언트.학생GUI;

import 클라이언트.Protocol;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.*;

public class TaxPayment extends JFrame implements Printable {

	public static JPanel panel;
	private JLabel label;
	private JTextField textField;
	public static JTextField 대학구분;
	private JTextField textField_2;
	public static JTextField 학과명;
	private JTextField textField_4;
	public static JTextField 학번;
	private JTextField textField_6;
	public static JTextField 성명;
	private JTextField textField_8;
	public static JTextField 합격생활관;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	public static JTextField 신청금액;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JTextArea textArea;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JTextField textField_25;
	private JTextField textField_26;
	private JTextField textField_27;
	private JTextField textField_28;
	private JTextField textField_29;
	private JTextField textField_30;
	private JTextField textField_31;
	private JTextField textField_32;
	private JTextField textField_33;

	// 결핵진단서 출력기능
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		printAll(graphics);
		return Printable.PAGE_EXISTS;
	}

	public TaxPayment(InputStream input, OutputStream output) {
		// frame
		setVisible(true);
		addWindowListener(new WindowAdapter() { // 내부 무명클래스로서
			public void windowClosing(WindowEvent e) { // 이벤트프로그램
				dispose();
			}
		});

		// 크기 1050이 딱 맞음
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(780, 1150));

		// 출력하기
		JButton 출력버튼 = new JButton("출력하기");
		출력버튼.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		출력버튼.setBounds(490, 1050, 97, 34);
		panel.add(출력버튼);
		출력버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("출력!");
				try {
					PrinterJob job = PrinterJob.getPrinterJob();
					job.setPrintable(TaxPayment.this);
					if (!job.printDialog())
						return;
					job.print();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		label = new JLabel("2020\uB144\uB3C4 \uC0DD\uD65C\uAD00\uBE44 \uB0A9\uBD80\uACE0\uC9C0\uC11C");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBounds(242, 25, 300, 30);
		panel.add(label);

		textField = new JTextField();
		textField.setText("대학구분");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(30, 60, 150, 40);
		panel.add(textField);

		대학구분 = new JTextField();
		대학구분.setText(" ");
		대학구분.setHorizontalAlignment(SwingConstants.CENTER);
		대학구분.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		대학구분.setColumns(10);
		대학구분.setBounds(30, 100, 150, 40);
		panel.add(대학구분);

		textField_2 = new JTextField();
		textField_2.setText("학과");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(180, 60, 200, 40);
		panel.add(textField_2);

		학과명 = new JTextField();
		학과명.setHorizontalAlignment(SwingConstants.CENTER);
		학과명.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		학과명.setColumns(10);
		학과명.setBounds(180, 100, 200, 40);
		panel.add(학과명);

		textField_4 = new JTextField();
		textField_4.setText("학번");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_4.setColumns(10);
		textField_4.setBounds(380, 60, 120, 40);
		panel.add(textField_4);

		학번 = new JTextField();
		학번.setHorizontalAlignment(SwingConstants.CENTER);
		학번.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		학번.setColumns(10);
		학번.setBounds(380, 100, 120, 40);
		panel.add(학번);

		textField_6 = new JTextField();
		textField_6.setText("\uC131\uBA85");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_6.setColumns(10);
		textField_6.setBounds(500, 60, 100, 40);
		panel.add(textField_6);

		성명 = new JTextField();
		성명.setText(" ");
		성명.setHorizontalAlignment(SwingConstants.CENTER);
		성명.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		성명.setColumns(10);
		성명.setBounds(500, 100, 100, 40);
		panel.add(성명);

		textField_8 = new JTextField();
		textField_8.setText("\uC0DD\uD65C\uAD00\uAD6C\uBD84");
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_8.setColumns(10);
		textField_8.setBounds(600, 60, 144, 40);
		panel.add(textField_8);

		합격생활관 = new JTextField();
		합격생활관.setText(" ");
		합격생활관.setHorizontalAlignment(SwingConstants.CENTER);
		합격생활관.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		합격생활관.setColumns(10);
		합격생활관.setBounds(600, 100, 144, 40);
		panel.add(합격생활관);

		textField_10 = new JTextField();
		textField_10.setText("\uAD6C\uBD84");
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_10.setColumns(10);
		textField_10.setBounds(30, 230, 150, 50);
		panel.add(textField_10);

		textField_11 = new JTextField();
		textField_11.setText("\uAE08\uC561");
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_11.setColumns(10);
		textField_11.setBounds(30, 280, 150, 50);
		panel.add(textField_11);

		textField_12 = new JTextField();
		textField_12.setText("\uC0DD\uD65C\uAD00\uBE44(\uC2DD\uBE44\uD3EC\uD568)");
		textField_12.setHorizontalAlignment(SwingConstants.CENTER);
		textField_12.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_12.setColumns(10);
		textField_12.setBounds(180, 230, 564, 50);
		panel.add(textField_12);

		신청금액 = new JTextField();
		신청금액.setText("0");
		신청금액.setHorizontalAlignment(SwingConstants.CENTER);
		신청금액.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		신청금액.setColumns(10);
		신청금액.setBounds(180, 280, 564, 50);
		panel.add(신청금액);

		label_1 = new JLabel("\u25CE\uB0A9\uBD80\uB0B4\uC5ED");
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_1.setBounds(30, 207, 85, 20);
		panel.add(label_1);

		label_2 = new JLabel("\u25CE\uB0A9\uBD80\uAE30\uAC04 : ");
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_2.setBounds(30, 357, 300, 20);
		panel.add(label_2);

		label_3 = new JLabel("\u25CE\uB0A9\uBD80\uACC4\uC88C");
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_3.setBounds(30, 450, 85, 20);
		panel.add(label_3);

		label_4 = new JLabel("\u203B\uB18D\uD611\uD559\uAD50\uCF54\uB4DC : 1467732");
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_4.setBounds(544, 421, 200, 20);
		panel.add(label_4);

		label_5 = new JLabel("\u203B\uC218\uB0A9\uAD6C\uBD84 : 1(\uC624\uB984\uAD00), 4(\uD478\uB984\uAD00)");
		label_5.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_5.setBounds(504, 450, 240, 20);
		panel.add(label_5);

		label_6 = new JLabel(
				"\u203B\uAC00\uC0C1\uACC4\uC88C\uBC88\uD638\uB294 \uB9E4\uD559\uAE30\uB9C8\uB2E4 \uBCC0\uACBD\uB429\uB2C8\uB2E4.");
		label_6.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_6.setBounds(30, 602, 310, 20);
		panel.add(label_6);

		label_7 = new JLabel("\u203B\uB0A9\uBD80\uC548\uB0B4");
		label_7.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_7.setBounds(331, 695, 85, 20);
		panel.add(label_7);

		textArea = new JTextArea();
		textArea.setText(
				"\u25A1 \uB0A9\uBD80\uBC29\uBC95\n\u25A0 \uAC00\uC0C1\uACC4\uC88C \uD655\uC778 \uBC0F \uACE0\uC9C0\uC11C \uCD9C\uB825\n- \uD559\uAD50\uD648\uD398\uC774\uC9C0 \uACE0\uC815\uACF5\uC9C0<2020\uD559\uB144\uB3C4 \uC2E0\uC785\uC0DD \uC0DD\uD65C\uAD00 \uD569\uACA9\uC790 \uD655\uC778 \uBC0F \uACE0\uC9C0\uC11C \uCD9C\uB825>\uD074\uB9AD, \n\uD559\uC0DD\uC870\uD68C \uD6C4 \uAC00\uC0C1\uACC4\uC88C \uD655\uC778 \uBC0F \uACE0\uC9C0\uC11C \uCD9C\uB825\n\u25A0 \uC740\uD589\uCC3D\uAD6C\uBC29\uBB38 : \uB0A9\uBD80\uACE0\uC9C0\uC11C \uC9C0\uCC38\uD558\uC5EC \uB0A9\uBD80 \uAE30\uAC04 \uB0B4 \uB18D\uD611\uC740\uD589 \uC218\uB0A9, \n\uAE30\uD55C \uB0B4 \uBBF8\uB0A9 \uC2DC \uC790\uB3D9\uD0C8\uB77D\uCC98\uB9AC \uB418\uBBC0\uB85C \uC8FC\uC758\uD560 \uAC83\n\n\u25A0 \uAC00\uC0C1\uACC4\uC88C \uB0A9\uBD80 : \uC704 \uBC29\uBC95\uC73C\uB85C \uAC00\uC0C1\uACC4\uC88C \uD655\uC778 \uD6C4 \uACE0\uC9C0\uC11C \uC0C1\uC758 \uAE08\uC561 \uC774\uCCB4\n- \uBC1B\uB294\uC0AC\uB78C(\uD559\uC0DD \uBCF8\uC778 \uC774\uB984) \uBC18\uB4DC\uC2DC \uD655\uC778\n\n\u25A1 \uB0A9\uBD80\uD655\uC778\n\u25A0 \uC740\uD589 \uD655\uC778 : \uC2E4\uC2DC\uAC04\n\u25A0 \uBCF8\uC778 \uD655\uC778 : \uB0A9\uBD80 \uB2E4\uC74C\uB0A0 10\uC2DC \uC774\uD6C4 \uD559\uAD50 \uBA54\uC778 \uD648\uD398\uC774\uC9C0 \uD31D\uC5C5\uACF5\uC9C0");
		textArea.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textArea.setBounds(30, 731, 712, 290);
		panel.add(textArea);

		textField_14 = new JTextField();
		textField_14.setText("\uC740\uD589");
		textField_14.setHorizontalAlignment(SwingConstants.CENTER);
		textField_14.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_14.setColumns(10);
		textField_14.setBounds(30, 480, 150, 40);
		panel.add(textField_14);

		textField_15 = new JTextField();
		textField_15.setText("\uAD6D\uBBFC\uC740\uD589");
		textField_15.setHorizontalAlignment(SwingConstants.CENTER);
		textField_15.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_15.setColumns(10);
		textField_15.setBounds(30, 520, 150, 25);
		panel.add(textField_15);

		textField_16 = new JTextField();
		textField_16.setText("\uB18D\uD611");
		textField_16.setHorizontalAlignment(SwingConstants.CENTER);
		textField_16.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_16.setColumns(10);
		textField_16.setBounds(30, 545, 150, 25);
		panel.add(textField_16);

		textField_17 = new JTextField();
		textField_17.setText("\uB300\uAD6C\uC740\uD589");
		textField_17.setHorizontalAlignment(SwingConstants.CENTER);
		textField_17.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_17.setColumns(10);
		textField_17.setBounds(30, 570, 150, 25);
		panel.add(textField_17);

		textField_18 = new JTextField();
		textField_18.setText("\uC218\uB0A9\uAD6C\uBD84");
		textField_18.setHorizontalAlignment(SwingConstants.CENTER);
		textField_18.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_18.setColumns(10);
		textField_18.setBounds(180, 480, 80, 40);
		panel.add(textField_18);

		textField_19 = new JTextField();
		textField_19.setText("1");
		textField_19.setHorizontalAlignment(SwingConstants.CENTER);
		textField_19.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_19.setColumns(10);
		textField_19.setBounds(180, 520, 80, 25);
		panel.add(textField_19);

		textField_20 = new JTextField();
		textField_20.setText("4");
		textField_20.setHorizontalAlignment(SwingConstants.CENTER);
		textField_20.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_20.setColumns(10);
		textField_20.setBounds(180, 545, 80, 25);
		panel.add(textField_20);

		textField_21 = new JTextField();
		textField_21.setText("1");
		textField_21.setHorizontalAlignment(SwingConstants.CENTER);
		textField_21.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_21.setColumns(10);
		textField_21.setBounds(180, 570, 80, 25);
		panel.add(textField_21);

		textField_22 = new JTextField();
		textField_22.setText("\uAC1C\uC778\uBCC4 \uAC00\uC0C1\uACC4\uC88C\uBC88\uD638");
		textField_22.setHorizontalAlignment(SwingConstants.CENTER);
		textField_22.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_22.setColumns(10);
		textField_22.setBounds(260, 480, 200, 40);
		panel.add(textField_22);

		textField_23 = new JTextField();
		textField_23.setText("940302-00-084122");
		textField_23.setHorizontalAlignment(SwingConstants.CENTER);
		textField_23.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_23.setColumns(10);
		textField_23.setBounds(260, 520, 200, 25);
		panel.add(textField_23);

		textField_24 = new JTextField();
		textField_24.setText("302-1064-4945-91");
		textField_24.setHorizontalAlignment(SwingConstants.CENTER);
		textField_24.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_24.setColumns(10);
		textField_24.setBounds(260, 545, 200, 25);
		panel.add(textField_24);

		textField_25 = new JTextField();
		textField_25.setText("185-13-023005");
		textField_25.setHorizontalAlignment(SwingConstants.CENTER);
		textField_25.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_25.setColumns(10);
		textField_25.setBounds(260, 570, 200, 25);
		panel.add(textField_25);

		textField_26 = new JTextField();
		textField_26.setText("\uBC1B\uB294 \uC0AC\uB78C");
		textField_26.setHorizontalAlignment(SwingConstants.CENTER);
		textField_26.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_26.setColumns(10);
		textField_26.setBounds(460, 480, 80, 40);
		panel.add(textField_26);

		textField_27 = new JTextField();
		textField_27.setText("\uB3C4\uC218\uD638");
		textField_27.setHorizontalAlignment(SwingConstants.CENTER);
		textField_27.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_27.setColumns(10);
		textField_27.setBounds(460, 520, 80, 25);
		panel.add(textField_27);

		textField_28 = new JTextField();
		textField_28.setText("\uB3C4\uC218\uD638");
		textField_28.setHorizontalAlignment(SwingConstants.CENTER);
		textField_28.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_28.setColumns(10);
		textField_28.setBounds(460, 545, 80, 25);
		panel.add(textField_28);

		textField_29 = new JTextField();
		textField_29.setText("\uB3C4\uC218\uD638");
		textField_29.setHorizontalAlignment(SwingConstants.CENTER);
		textField_29.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_29.setColumns(10);
		textField_29.setBounds(460, 570, 80, 25);
		panel.add(textField_29);

		textField_30 = new JTextField();
		textField_30.setText("\uBE44\uACE0");
		textField_30.setHorizontalAlignment(SwingConstants.CENTER);
		textField_30.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_30.setColumns(10);
		textField_30.setBounds(540, 480, 206, 40);
		panel.add(textField_30);

		textField_31 = new JTextField();
		textField_31.setText("\uB0A9\uBD80\uAC00\uB2A5\uC2DC\uAC04 : 10\uC2DC~17\uC2DC");
		textField_31.setHorizontalAlignment(SwingConstants.CENTER);
		textField_31.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_31.setColumns(10);
		textField_31.setBounds(540, 520, 206, 25);
		panel.add(textField_31);

		textField_32 = new JTextField();
		textField_32.setText("\uB0A9\uBD80\uAC00\uB2A5\uC2DC\uAC04 : 10\uC2DC~17\uC2DC");
		textField_32.setHorizontalAlignment(SwingConstants.CENTER);
		textField_32.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_32.setColumns(10);
		textField_32.setBounds(540, 545, 206, 25);
		panel.add(textField_32);

		textField_33 = new JTextField();
		textField_33.setText("\uB0A9\uBD80\uAC00\uB2A5\uC2DC\uAC04 : 10\uC2DC~17\uC2DC");
		textField_33.setHorizontalAlignment(SwingConstants.CENTER);
		textField_33.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_33.setColumns(10);
		textField_33.setBounds(540, 570, 206, 25);
		panel.add(textField_33);

		JTextField 납부금액 = new JTextField();
		납부금액.setHorizontalAlignment(SwingConstants.CENTER);
		납부금액.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		납부금액.setColumns(10);
		납부금액.setBounds(110, 1050, 110, 34);
		panel.add(납부금액);

		JLabel 원 = new JLabel("원");
		원.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		원.setBounds(220, 1053, 23, 23);
		panel.add(원);

		// 납부 버튼
		JButton 납부버튼 = new JButton("납부하기");
		납부버튼.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		납부버튼.setBounds(250, 1050, 97, 34);
		panel.add(납부버튼);
		// 금액납부기능
		납부버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 납부요청
					if (납부금액.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "납부 금액을 입력하세요.");
					} else if (!납부금액.getText().equals(신청금액.getText())) {
						JOptionPane.showMessageDialog(null, "신청금액과 같은 금액을 입력하세요.");
					} else {
						Protocol protocol = new Protocol(Protocol.TYPE9_RENEW_REQ, Protocol.T9_CD11_납부);
						protocol.setString(납부금액.getText());
						output.write(protocol.getPacket());

						// 납부응답
						protocol = new Protocol();
						byte[] header = protocol.getPacket();
						input.read(header);
						int protocolType = header[0];
						int protocolCode = header[1];
						if (protocolType == Protocol.TYPE10_RENEW_RES && protocolCode == Protocol.T10_CD22_납부성공) {
							JOptionPane.showMessageDialog(null, "납부 성공.");
						} else {
							JOptionPane.showMessageDialog(null, "납부 실패.");
						}
					}
				} catch (IOException a) {
				}
			}
		});

		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(panel);

		add(jScrollPane);
		setLocation(100, 100);
		setSize(820, 1150);

	}
}