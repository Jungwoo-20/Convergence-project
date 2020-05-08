package 클라이언트.관리자GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import 클라이언트.Protocol;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class ScheduleUpload extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	public ScheduleUpload(InputStream input, OutputStream output) {
		setBounds(100, 10, 1250, 800);

		JTextField textField_11, textField_12, textField_13, textField_14, textField_15;

		setBackground(Color.WHITE);
		setBounds(100, 100, 1250, 800);
		setLayout(null);

		JLabel label = new JLabel("\u25B6 \uC120\uBC1C \uC77C\uC815");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBounds(176, 59, 150, 35);
		add(label);

		textField_11 = new JTextField();
		textField_11.setText("\uAE30\uAC04 \uBA85");
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		textField_11.setBounds(176, 124, 310, 40);
		add(textField_11);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "\uC120\uD0DD",
				"\uC0DD\uD65C\uAD00 \uC785\uC0AC \uC2E0\uCCAD",
				"\uC0DD\uD65C\uAD00 \uAD00\uBC30\uC815 \uBC0F \uD569\uACA9\uC790 \uBC1C\uD45C",
				"\uC0DD\uD65C\uAD00\uBE44 \uB0A9\uBD80", "\uACB0\uD575\uC9C4\uB2E8\uC11C \uC81C\uCD9C \uAE30\uAC04",
				"\uC0DD\uD65C\uAD00 \uCD94\uAC00\uD569\uACA9\uC790 \uBC1C\uD45C",
				"\uC0DD\uD65C\uAD00 \uCD94\uAC00\uD569\uACA9\uC790 \uC0DD\uD65C\uAD00\uBE44 \uB0A9\uBD80" }));
		comboBox.setMaximumRowCount(6);
		comboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		comboBox.setBounds(176, 164, 310, 40);
		add(comboBox);

		textField_12 = new JTextField();
		textField_12.setText("\uC2DC\uC791 \uC2DC\uAC04");
		textField_12.setHorizontalAlignment(SwingConstants.CENTER);
		textField_12.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_12.setEditable(false);
		textField_12.setColumns(10);
		textField_12.setBounds(176, 247, 478, 40);
		add(textField_12);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "2020" }));
		comboBox_1.setBounds(184, 294, 60, 25);
		add(comboBox_1);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(
				new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		comboBox_2.setBounds(281, 294, 45, 25);
		add(comboBox_2);

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
				"26", "27", "28", "29", "30", "31" }));
		comboBox_3.setBounds(363, 294, 45, 25);
		add(comboBox_3);

		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
		comboBox_4.setBounds(452, 294, 45, 25);
		add(comboBox_4);

		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(
				new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
						"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
						"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
						"47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
		comboBox_5.setBounds(534, 294, 45, 25);
		add(comboBox_5);

		textField_13 = new JTextField();
		textField_13.setText("             \uB144           \uC6D4           \uC77C           \uC2DC           \uBD84");
		textField_13.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_13.setEditable(false);
		textField_13.setColumns(10);
		textField_13.setBounds(176, 287, 478, 40);
		add(textField_13);

		textField_14 = new JTextField();
		textField_14.setText("\uB9C8\uAC10 \uC2DC\uAC04");
		textField_14.setHorizontalAlignment(SwingConstants.CENTER);
		textField_14.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_14.setEditable(false);
		textField_14.setColumns(10);
		textField_14.setBounds(176, 362, 478, 40);
		add(textField_14);

		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] { "2020" }));
		comboBox_6.setBounds(184, 409, 60, 25);
		add(comboBox_6);

		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setModel(new DefaultComboBoxModel(
				new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		comboBox_7.setBounds(281, 409, 45, 25);
		add(comboBox_7);

		JComboBox comboBox_8 = new JComboBox();
		comboBox_8.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
				"26", "27", "28", "29", "30", "31" }));
		comboBox_8.setBounds(363, 409, 45, 25);
		add(comboBox_8);

		JComboBox comboBox_9 = new JComboBox();
		comboBox_9.setModel(new DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
		comboBox_9.setBounds(452, 409, 45, 25);
		add(comboBox_9);

		JComboBox comboBox_10 = new JComboBox();
		comboBox_10.setModel(new DefaultComboBoxModel(
				new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
						"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
						"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
						"47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
		comboBox_10.setBounds(534, 409, 45, 25);
		add(comboBox_10);

		textField_15 = new JTextField();
		textField_15.setText("             \uB144           \uC6D4           \uC77C           \uC2DC           \uBD84");
		textField_15.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_15.setEditable(false);
		textField_15.setColumns(10);
		textField_15.setBounds(176, 402, 478, 40);
		add(textField_15);

		JButton button = new JButton("\uB4F1\uB85D");
		button.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		button.setBounds(734, 124, 80, 40);
		add(button);

		textField = new JTextField();
		textField.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField.setBounds(176, 533, 638, 136);
		add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setText("\uC548\uB0B4");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(176, 494, 638, 40);
		add(textField_1);

		JButton button_1 = new JButton("\uC77C\uC815 \uC870\uD68C");
		button_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		button_1.setBounds(828, 122, 120, 45);
		add(button_1);

		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 선발일정 조회 요청 프로토콜 전송
					Protocol protocol = new Protocol(Protocol.TYPE7_VIEW_REQ, Protocol.T7_CD13_선발일정조회);
					output.write(protocol.getPacket());

					// 요청 결과 수신
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
					String result = protocol.getString();

					// 받은 데이터 처리
					ArrayList list = new ArrayList();
					for (int i = 1; i < result.split("/").length; i++) {
						list.add(result.split("/")[i].toString());
					}
					for (int i = 1; i < 3; i++) {	//yyyy-mm-dd hh:mm 형식
						for (int j = i; j < list.size(); j += 4) {
							String[] tmp = list.get(j).toString().split(":");
							list.set(j, tmp[0] + ":" + tmp[1]);
						}
					}
					for (int i = 0; i < list.size(); i++) {		//값이 null인경우 공백으로 표시
						if (list.get(i).equals("null"))
							list.set(i, "");
					}

					// 조회 성공, 실패 확인
					if (protocolType == Protocol.TYPE8_VIEW_RES && protocolCode == Protocol.T8_CD26_선발일정조회승인) {
						LookupSchedule ls = new LookupSchedule();		//LookupSchedule 새 탭으로 띄우기(선발일정 조회)
						ls.textField_4.setText(list.get(0).toString());
						ls.textField_5.setText(list.get(1).toString() + "~" + list.get(2).toString());
						ls.textField_6.setText(list.get(3).toString());
						ls.textField_7.setText(list.get(4).toString());
						ls.textField_8.setText(list.get(5).toString() + "~" + list.get(6).toString());
						ls.textField_9.setText(list.get(7).toString());
						ls.textField_10.setText(list.get(8).toString());
						ls.textField_11.setText(list.get(9).toString() + "~" + list.get(10).toString());
						ls.textField_12.setText(list.get(11).toString());
						ls.textField_13.setText(list.get(12).toString());
						ls.textField_14.setText(list.get(13).toString() + "~" + list.get(14).toString());
						ls.textField_15.setText(list.get(15).toString());
						ls.textField_16.setText(list.get(16).toString());
						ls.textField_17.setText(list.get(17).toString() + "~" + list.get(18).toString());
						ls.textField_18.setText(list.get(19).toString());
						ls.textField_19.setText(list.get(20).toString());
						ls.textField_20.setText(list.get(21).toString() + "~" + list.get(22).toString());
						ls.textField_21.setText(list.get(23).toString());
					} else if (protocolType == Protocol.TYPE8_VIEW_RES && protocolCode == Protocol.T8_CD27_선발일정조회거절)
						JOptionPane.showMessageDialog(null, "등록된 일정이 없습니다.");

				} catch (IndexOutOfBoundsException e1) {
					System.out.println("");
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 선발일정 등록 요청 및 데이터 전송
					Protocol protocol = new Protocol(Protocol.TYPE9_RENEW_REQ, Protocol.T9_CD8_선발일정등록요청);
					String a = comboBox.getSelectedItem().toString();
					String b = comboBox_1.getSelectedItem().toString() + "-0" + comboBox_2.getSelectedItem().toString()
							+ "-0" + comboBox_3.getSelectedItem().toString() + " "
							+ comboBox_4.getSelectedItem().toString() + ":" + comboBox_5.getSelectedItem().toString()
							+ ":00";
					String c = comboBox_6.getSelectedItem().toString() + "-0" + comboBox_7.getSelectedItem().toString()
							+ "-0" + comboBox_8.getSelectedItem().toString() + " "
							+ comboBox_9.getSelectedItem().toString() + ":" + comboBox_10.getSelectedItem().toString()
							+ ":00";
					String d = textField.getText();
					if (d.equals("")) { // 안내를 입력하지 않은 경우
						String res = "2020/1/" + a + "/" + b + "/" + c;
						protocol.setString(res);
					} else { // 모두 입력한 경우
						String res = "2020/1/" + a + "/" + b + "/" + c + "/" + d;
						protocol.setString(res);
					}
					output.write(protocol.getPacket());

					// 요청 결과 수신
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

					// 등록 성공, 실패 확인
					if (protocolType == Protocol.TYPE10_RENEW_RES && protocolCode == Protocol.T10_CD16_선발일정등록성공)
						JOptionPane.showMessageDialog(null, "등록 성공!");
					else if (protocolType == Protocol.TYPE10_RENEW_RES && protocolCode == Protocol.T10_CD17_선발일정등록실패)
						JOptionPane.showMessageDialog(null, "등록 실패. 다시 시도하세요!");

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}

//선발일정 조회를 위한 화면
class LookupSchedule extends JFrame {
	public JPanel contentPane;
	public JTextField textField_4;
	public JTextField textField_1;
	public JTextField textField_2;
	public JTextField textField_3;
	public JTextField textField_5;
	public JTextField textField_6;
	public JTextField textField_7;
	public JTextField textField_8;
	public JTextField textField_9;
	public JTextField textField_16;
	public JTextField textField_17;
	public JTextField textField_18;
	public JTextField textField_10;
	public JTextField textField_11;
	public JTextField textField_12;
	public JTextField textField_13;
	public JTextField textField_14;
	public JTextField textField_15;
	public JTextField textField_19;
	public JTextField textField_20;
	public JTextField textField_21;
	public JButton btnNewButton;

	LookupSchedule() {
		addWindowListener(new WindowAdapter() { // 내부 무명클래스로서
			public void windowClosing(WindowEvent e) { // 이벤트프로그램
				dispose();
			}
		});
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_4.setColumns(10);
		textField_4.setBounds(40, 130, 260, 50);
		contentPane.add(textField_4);

		textField_1 = new JTextField();
		textField_1.setText("\uC77C\uC815\uAD6C\uBD84");
		textField_1.setEditable(false);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(40, 80, 260, 50);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setText("\uAE30\uAC04");
		textField_2.setEditable(false);
		textField_2.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(300, 80, 380, 50);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setText("\uC548\uB0B4");
		textField_3.setEditable(false);
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		textField_3.setColumns(10);
		textField_3.setBounds(680, 80, 260, 50);
		contentPane.add(textField_3);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_5.setColumns(10);
		textField_5.setBounds(300, 130, 380, 50);
		contentPane.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setEditable(false);
		textField_6.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_6.setColumns(10);
		textField_6.setBounds(680, 130, 260, 50);
		contentPane.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_7.setColumns(10);
		textField_7.setBounds(40, 180, 260, 50);
		contentPane.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_8.setColumns(10);
		textField_8.setBounds(300, 180, 380, 50);
		contentPane.add(textField_8);

		textField_9 = new JTextField();
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setEditable(false);
		textField_9.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_9.setColumns(10);
		textField_9.setBounds(680, 180, 260, 50);
		contentPane.add(textField_9);

		textField_16 = new JTextField();
		textField_16.setEditable(false);
		textField_16.setHorizontalAlignment(SwingConstants.CENTER);
		textField_16.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_16.setColumns(10);
		textField_16.setBounds(40, 330, 260, 50);
		contentPane.add(textField_16);

		textField_17 = new JTextField();
		textField_17.setEditable(false);
		textField_17.setHorizontalAlignment(SwingConstants.CENTER);
		textField_17.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_17.setColumns(10);
		textField_17.setBounds(300, 330, 380, 50);
		contentPane.add(textField_17);

		textField_18 = new JTextField();
		textField_18.setHorizontalAlignment(SwingConstants.CENTER);
		textField_18.setEditable(false);
		textField_18.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_18.setColumns(10);
		textField_18.setBounds(680, 330, 260, 50);
		contentPane.add(textField_18);

		textField_10 = new JTextField();
		textField_10.setEditable(false);
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_10.setColumns(10);
		textField_10.setBounds(40, 230, 260, 50);
		contentPane.add(textField_10);

		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_11.setColumns(10);
		textField_11.setBounds(300, 230, 380, 50);
		contentPane.add(textField_11);

		textField_12 = new JTextField();
		textField_12.setHorizontalAlignment(SwingConstants.CENTER);
		textField_12.setEditable(false);
		textField_12.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_12.setColumns(10);
		textField_12.setBounds(680, 230, 260, 50);
		contentPane.add(textField_12);

		textField_13 = new JTextField();
		textField_13.setEditable(false);
		textField_13.setHorizontalAlignment(SwingConstants.CENTER);
		textField_13.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_13.setColumns(10);
		textField_13.setBounds(40, 280, 260, 50);
		contentPane.add(textField_13);

		textField_14 = new JTextField();
		textField_14.setEditable(false);
		textField_14.setHorizontalAlignment(SwingConstants.CENTER);
		textField_14.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_14.setColumns(10);
		textField_14.setBounds(300, 280, 380, 50);
		contentPane.add(textField_14);

		textField_15 = new JTextField();
		textField_15.setHorizontalAlignment(SwingConstants.CENTER);
		textField_15.setEditable(false);
		textField_15.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_15.setColumns(10);
		textField_15.setBounds(680, 280, 260, 50);
		contentPane.add(textField_15);

		textField_19 = new JTextField();
		textField_19.setEditable(false);
		textField_19.setHorizontalAlignment(SwingConstants.CENTER);
		textField_19.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_19.setColumns(10);
		textField_19.setBounds(40, 380, 260, 50);
		contentPane.add(textField_19);

		textField_20 = new JTextField();
		textField_20.setEditable(false);
		textField_20.setHorizontalAlignment(SwingConstants.CENTER);
		textField_20.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_20.setColumns(10);
		textField_20.setBounds(300, 380, 380, 50);
		contentPane.add(textField_20);

		textField_21 = new JTextField();
		textField_21.setHorizontalAlignment(SwingConstants.CENTER);
		textField_21.setEditable(false);
		textField_21.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_21.setColumns(10);
		textField_21.setBounds(680, 380, 260, 50);
		contentPane.add(textField_21);

		btnNewButton = new JButton("\uB2EB\uAE30");
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnNewButton.setBounds(430, 460, 110, 40);
		contentPane.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setTitle("일정 조회");
		setBounds(210, 150, 1000, 600);
		setResizable(false);
		setVisible(true);
	}
}
