package 클라이언트.학생GUI;

import 클라이언트.Protocol;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class TuberMCSubmit extends JPanel {
	public JTextField textField, txtAaajpg, txtAaajpg_1, textField_2, textField_3, textField_4, textField_5;

	public TuberMCSubmit(InputStream input, OutputStream output) {
		setBounds(100, 10, 1250, 800);

		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBounds(100, 100, 1250, 800);
		setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("\u25B6 \uC9C4\uB2E8\uC11C \uC81C\uCD9C");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 19));
		lblNewLabel_1.setBounds(95, 170, 150, 35);
		add(lblNewLabel_1);

		JButton btnNewButton = new JButton("\uC81C\uCD9C");
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnNewButton.setBounds(1033, 170, 80, 35);
		add(btnNewButton);

		JLabel label = new JLabel("\uC81C\uCD9C\uB144\uB3C4");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label.setBounds(95, 217, 150, 45);
		label.setBorder(LineBorder.createGrayLineBorder());
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setText("2020");
		textField.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField.setBounds(244, 217, 150, 45);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBorder(LineBorder.createGrayLineBorder());
		add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("\uC81C\uCD9C\uD559\uAE30");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_1.setBorder(LineBorder.createGrayLineBorder());
		label_1.setBounds(393, 217, 150, 45);
		label_1.setHorizontalAlignment(JLabel.CENTER);
		add(label_1);

		JLabel label_2 = new JLabel("\uC81C\uCD9C\uC11C\uB958\uAD6C\uBD84");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_2.setBorder(LineBorder.createGrayLineBorder());
		label_2.setBounds(95, 261, 150, 45);
		label_2.setHorizontalAlignment(JLabel.CENTER);
		add(label_2);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "1\uD559\uAE30", "2\uD559\uAE30", "1\uD559\uAE30\uCD94\uAC00",
						"2\uD559\uAE30\uCD94\uAC00", "\uC5EC\uB984\uD559\uAE30", "\uC5EC\uB984\uD559\uAE30\uCD94\uAC00",
						"\uACA8\uC6B8\uD559\uAE30", "\uACA8\uC6B8\uD559\uAE30\uCD94\uAC00" }));
		comboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(542, 217, 150, 45);
		add(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "\uACB0\uD575\uC9C4\uB2E8\uC11C" }));
		comboBox_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setBounds(244, 261, 448, 45);
		add(comboBox_1);

		JButton btnNewButton_1 = new JButton("\uD30C\uC77C\uC5F4\uAE30");
		btnNewButton_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnNewButton_1.setBounds(95, 305, 150, 45);
		add(btnNewButton_1);

		txtAaajpg = new JTextField();
		txtAaajpg.setEditable(false);
		txtAaajpg.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		txtAaajpg.setColumns(10);
		txtAaajpg.setBounds(244, 305, 869, 45);
		txtAaajpg.setHorizontalAlignment(SwingConstants.LEFT);
		txtAaajpg.setBorder(LineBorder.createGrayLineBorder());
		add(txtAaajpg);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_2.setEditable(false);
		textField_2.setEnabled(false);
		textField_2.setBounds(691, 217, 422, 89);
		textField_2.setBorder(LineBorder.createGrayLineBorder());
		add(textField_2);
		textField_2.setColumns(10);

		JLabel label_3 = new JLabel(
				"제출결과");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_3.setBorder(LineBorder.createGrayLineBorder());
		label_3.setBounds(95, 362, 1018, 45);
		label_3.setHorizontalAlignment(JLabel.CENTER);
		add(label_3);

		JLabel label_4 = new JLabel("\uC81C\uCD9C\uC11C\uB958\uAD6C\uBD84");
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_4.setBorder(LineBorder.createGrayLineBorder());
		label_4.setBounds(95, 406, 150, 45);
		label_4.setHorizontalAlignment(JLabel.CENTER);
		add(label_4);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "\uACB0\uD575\uC9C4\uB2E8\uC11C" }));
		comboBox_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		comboBox_2.setBackground(Color.WHITE);
		comboBox_2.setBounds(244, 406, 150, 45);
		add(comboBox_2);

		JLabel label_5 = new JLabel("\uC81C\uCD9C\uC77C\uC2DC");
		label_5.setForeground(Color.BLACK);
		label_5.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_5.setBorder(LineBorder.createGrayLineBorder());
		label_5.setBounds(393, 406, 150, 45);
		label_5.setHorizontalAlignment(JLabel.CENTER);
		add(label_5);

		JLabel label_6 = new JLabel("\uC9C4\uB2E8\uC77C\uC2DC");
		label_6.setForeground(Color.BLACK);
		label_6.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_6.setBorder(LineBorder.createGrayLineBorder());
		label_6.setBounds(691, 406, 150, 45);
		label_6.setHorizontalAlignment(JLabel.CENTER);
		add(label_6);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_3.setColumns(10);
		textField_3.setBounds(542, 406, 150, 45);
		textField_3.setBorder(LineBorder.createGrayLineBorder());
		add(textField_3);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("Dialog", Font.PLAIN, 16));
		textField_4.setColumns(10);
		textField_4.setBounds(840, 406, 273, 45);
		textField_4.setBorder(LineBorder.createGrayLineBorder());
		add(textField_4);

		JLabel label_7 = new JLabel("\uD30C\uC77C\uBA85");
		label_7.setForeground(Color.BLACK);
		label_7.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_7.setBorder(LineBorder.createGrayLineBorder());
		label_7.setBounds(95, 450, 150, 45);
		label_7.setHorizontalAlignment(JLabel.CENTER);
		add(label_7);

		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.LEFT);
		textField_5.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBorder(LineBorder.createGrayLineBorder());
		textField_5.setBounds(244, 450, 869, 45);
		add(textField_5);

		JFileChooser fileChooser = new JFileChooser();
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = fileChooser.showOpenDialog(null); // 파일오픈 다이얼로그 를 띄움
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String fileName = fileChooser.getSelectedFile().getName();
					txtAaajpg.setText(fileName);
				}

			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 서버에 파일전송요청 전송
					String filelocation = fileChooser.getSelectedFile().toString();
					File file = new File(filelocation);
					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
					Protocol protocol = new Protocol(Protocol.TYPE3_FILE_SEND_REQ, Protocol.T3_CD0_GYEOLHAEK);
					String gyeolhaek = file.getName() + "/" + Long.toString(file.length());
					protocol.setString(gyeolhaek);
					output.write(protocol.getPacket());

					// 파일전송요청에 대한 응답
					protocol = new Protocol();
					byte[] header = protocol.getPacket();

					// 파일전송
					byte[] f = new byte[(int) file.length()];
					bis.read(f);
					
					// f를 filesize로 잘라서 보내기
					if (file.length() < Protocol.LEN_FILE) { // flag를 사용하지 않는 경우
						protocol = new Protocol(Protocol.TYPE5_FILE_SEND, Protocol.T5_CD0_GYEOLHAEK, f);
						output.write(protocol.getPacket());
					} else { // flag를 사용하는 경우
						for (int i = (int) file.length(), j = 0; i >= 0; i -= Protocol.LEN_FILE, j++) {
							if (i < Protocol.LEN_FILE) {
								byte[] temp = new byte[i];
								System.arraycopy(f, j * Protocol.LEN_FILE, temp, 0, i);
								protocol = new Protocol(Protocol.TYPE5_FILE_SEND, Protocol.T5_CD0_GYEOLHAEK, 1, 1, j,
										temp);
								output.write(protocol.getPacket());
							} else {
								byte[] temp = new byte[Protocol.LEN_FILE];
								System.arraycopy(f, j * Protocol.LEN_FILE, temp, 0, Protocol.LEN_FILE);
								protocol = new Protocol(Protocol.TYPE5_FILE_SEND, Protocol.T5_CD0_GYEOLHAEK, 1, 0, j,
										temp);
								output.write(protocol.getPacket());
							}
						}
					}
					bis.close();

					// 파일전송결과 받기
					protocol = new Protocol();
					header = protocol.getPacket();
					input.read(header);
					if (protocol.getType() == Protocol.TYPE6_FILE_RES && protocol.getCode() == Protocol.T6_CD0_SUCCESS)
						JOptionPane.showMessageDialog(null, "제출 성공");
					else if (protocol.getType() == Protocol.TYPE6_FILE_RES
							&& protocol.getCode() == Protocol.T6_CD1_FAIL)
						JOptionPane.showMessageDialog(null, "제출 실패");

					// 서버에 보낸 파일 학인하는 프로토콜
					protocol = new Protocol(Protocol.TYPE7_VIEW_REQ, Protocol.T7_CD5_해당결핵진단서존재여부조회요청);
					output.write(protocol.getPacket());

					// 받은 패킷 처리
					protocol = new Protocol();
					header = protocol.getPacket();
					input.read(header);
					int protocolType = header[0];
					int protocolCode = header[1];
					int bodylength = protocol.byteToInt(header, 2);
					if (bodylength != 0) {
						byte[] body = new byte[bodylength];
						input.read(body);
						protocol.setPacket(header, body);
					}

					// 보낸 파일 정보 받아서 출력
					if (protocolType == Protocol.TYPE8_VIEW_RES && protocolCode == Protocol.T8_CD10_해당결핵진단서존재) {
						String result1 = protocol.getString();
						String[] tmp = result1.split("/");
						if (tmp.length < 4) {
							textField_3.setText(tmp[0]);
							textField_5.setText(tmp[1]);
						} else {
							textField_3.setText(tmp[0]);
							textField_4.setText(tmp[1]);
							textField_5.setText(tmp[2]);
						}
					}
				} catch (Exception e1) {
					System.out.println("");
				}
			}
		});
	}

}