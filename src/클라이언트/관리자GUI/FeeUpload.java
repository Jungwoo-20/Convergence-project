package 클라이언트.관리자GUI;

import 클라이언트.Protocol;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

//생활관사용료와 급식비 등록을 위한 창
public class FeeUpload extends JPanel {
	// 콤보박스의 내용
	String[] dorm = { "선택안함", "푸름관1동", "푸름관1동 탑층", "푸름관2동 탑층", "푸름관3동 탑층", "푸름관2동", "푸름관3동", "푸름관4동", "오름관1동", "오름관2동",
			"오름관3동", "신평관 1인실", "신평관 2인실" };
	String[] YS = { "선택안함", "1년", "1년(방학)", "한학기" };
	String[] S = { "선택안함", "한학기" };
	String[] meal = { "선택안함", "7일식", "5일식" };

	// 콤보박스 내용 바꾸기위한 콤보박스모델
	DefaultComboBoxModel semesterS = new DefaultComboBoxModel(S);
	DefaultComboBoxModel semesterYS = new DefaultComboBoxModel(YS);
	DefaultComboBoxModel semesterG = new DefaultComboBoxModel(S);
	DefaultComboBoxModel semesterYG = new DefaultComboBoxModel(YS);

	JLabel 생활관_사용료_등록 = new JLabel("생활관 사용료 등록");
	JLabel 급식비_등록 = new JLabel("급식비 등록");
	JButton 사용료_등록_버튼 = new JButton("등록");
	JButton 급식비_등록_버튼 = new JButton("등록");
	JButton 사용료_조회_버튼 = new JButton("조회");
	JButton 급식비_조회_버튼 = new JButton("조회");
	JComboBox 생활관명_사용료 = new JComboBox(dorm);
	JComboBox 학기구분_사용료 = new JComboBox(YS);
	JComboBox 생활관명_급식비 = new JComboBox(dorm);
	JComboBox 학기구분_급식비 = new JComboBox(YS);
	JComboBox 식사구분_급식비 = new JComboBox(meal);
	JLabel 생활관명_사용료_라벨 = new JLabel("생활관명");
	JLabel 학기구분_사용료_라벨 = new JLabel("학기구분");
	JLabel 생활관_사용료_라벨 = new JLabel("생활관 사용료");
	JLabel 생활관명_급식비_라벨 = new JLabel("생활관명");
	JLabel 학기구분_급식비_라벨 = new JLabel("학기구분");
	JLabel 식사구분_급식비_라벨 = new JLabel("식사구분");
	JLabel 급식비_라벨 = new JLabel("급식비");
	JTextArea txtArea = new JTextArea();
	JTextField 생활관_사용료_결과 = new JTextField();
	JTextField 급식비_결과 = new JTextField();

	public FeeUpload(InputStream input, OutputStream output) {
		setBounds(100, 10, 1250, 800);
		setBounds(0, 0, 1244, 759);
		setBackground(Color.WHITE);

		LineBorder lb = new LineBorder(Color.black, 1, false);
		setLayout(null);

		생활관_사용료_등록.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		생활관_사용료_등록.setBounds(82, 109, 174, 36);
		add(생활관_사용료_등록);

		급식비_등록.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		급식비_등록.setBounds(82, 392, 117, 36);
		add(급식비_등록);

		사용료_등록_버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == 사용료_등록_버튼) {
					// 사용료에 선택되지않은 값이 없다면 등록가능
					if (!생활관명_사용료.getSelectedItem().toString().equals("선택안함")
							&& !학기구분_사용료.getSelectedItem().toString().equals("선택안함")
							&& !생활관_사용료_결과.getText().equals("")) {
						try {
							// data에 생활관 사용료, 생활관명, 학기구분을 넣어 발신
							String data = 생활관_사용료_결과.getText() + "/" + 생활관명_사용료.getSelectedItem().toString() + "/"
									+ 학기구분_사용료.getSelectedItem().toString();
							// 프로토콜발신
							Protocol protocol = new Protocol(Protocol.TYPE9_RENEW_REQ, Protocol.T9_CD9_사용료등록요청);
							protocol.setString(data);
							output.write(protocol.getPacket());

							// 프로토콜수신
							protocol = new Protocol();
							byte[] header = protocol.getPacket();
							input.read(header);

							int protocolType = header[0];
							int protocolCode = header[1];

							if (protocolType == Protocol.TYPE10_RENEW_RES
									&& protocolCode == Protocol.T10_CD18_사용료등록성공) {
								JOptionPane.showMessageDialog(null, "갱신성공");
							} else if (protocolType == Protocol.TYPE10_RENEW_RES
									&& protocolCode == Protocol.T10_CD19_사용료등록실패) {
								JOptionPane.showMessageDialog(null, "갱신실패");
							}
						} catch (IOException e1) {

						}

					}
				}
			}
		});
		사용료_등록_버튼.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		사용료_등록_버튼.setBounds(82, 286, 75, 30);
		add(사용료_등록_버튼);

		// 급식비 등록
		급식비_등록_버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == 급식비_등록_버튼) {
					// 사용료에 선택되지않은 값이 없다면 등록가능
					if (!생활관명_급식비.getSelectedItem().toString().equals("선택안함")
							&& !학기구분_급식비.getSelectedItem().toString().equals("선택안함")
							&& !식사구분_급식비.getSelectedItem().toString().equals("선택안함") && !급식비_결과.getText().equals("")) {
						try {
							// data에 급식비, 생활관명, 학기구분, 식사구분을 넣어 발신
							String data = 급식비_결과.getText() + "/" + 생활관명_급식비.getSelectedItem().toString() + "/"
									+ 학기구분_급식비.getSelectedItem().toString() + "/"
									+ 식사구분_급식비.getSelectedItem().toString();
							// 프로토콜발신
							Protocol protocol = new Protocol(Protocol.TYPE9_RENEW_REQ, Protocol.T9_CD10_급식비등록요청);
							protocol.setString(data);
							output.write(protocol.getPacket());
							// 프로토콜수신
							protocol = new Protocol();
							byte[] header = protocol.getPacket();
							input.read(header);

							int protocolType = header[0];
							int protocolCode = header[1];
							if (protocolType == Protocol.TYPE10_RENEW_RES
									&& protocolCode == Protocol.T10_CD20_급식비등록성공) {
								JOptionPane.showMessageDialog(null, "갱신성공");
							} else if (protocolType == Protocol.TYPE10_RENEW_RES
									&& protocolCode == Protocol.T10_CD21_급식비등록실패) {
								JOptionPane.showMessageDialog(null, "갱신실패");
							}
						} catch (IOException e1) {

						}

					}
				}
			}
		});
		급식비_등록_버튼.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		급식비_등록_버튼.setBounds(82, 564, 75, 30);
		add(급식비_등록_버튼);

		// 사용료 조회
		사용료_조회_버튼.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		사용료_조회_버튼.setBounds(232, 286, 75, 30);
		사용료_조회_버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == 사용료_조회_버튼) {
					new FeeSView(input, output);
				}
			}
		});
		add(사용료_조회_버튼);

		// 급식비 조회
		급식비_조회_버튼.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		급식비_조회_버튼.setBounds(232, 564, 75, 30);
		급식비_조회_버튼.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == 급식비_조회_버튼) {
					new FeeGView(input, output);
				}
			}
		});
		add(급식비_조회_버튼);

		// 사용료 생활관명
		생활관명_사용료.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		생활관명_사용료.setBorder(lb);
		생활관명_사용료.setBounds(82, 210, 150, 55);
		생활관명_사용료.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == 생활관명_사용료) {
					학기구분_사용료.setSelectedIndex(0);
					생활관_사용료_결과.setText("");
					if (생활관명_사용료.getSelectedItem().toString().equals("푸름관2동")
							|| 생활관명_사용료.getSelectedItem().toString().equals("푸름관3동")) {
						학기구분_사용료.setModel(semesterYS);
					} else {
						학기구분_사용료.setModel(semesterS);
					}

				}
			}
		});
		add(생활관명_사용료);

		// 사용료 학기구분

		학기구분_사용료.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		학기구분_사용료.setBorder(lb);
		학기구분_사용료.setBounds(232, 210, 150, 55);
		add(학기구분_사용료);

		// 급식비 생활관명

		생활관명_급식비.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		생활관명_급식비.setBorder(lb);
		생활관명_급식비.setBounds(82, 493, 150, 55);
		생활관명_급식비.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				학기구분_급식비.setSelectedIndex(0);
				식사구분_급식비.setSelectedIndex(0);
				급식비_결과.setText("");
				if (e.getSource() == 생활관명_급식비) {
					if (생활관명_급식비.getSelectedItem().toString().equals("푸름관2동")
							|| 생활관명_급식비.getSelectedItem().toString().equals("푸름관3동")) {
						학기구분_급식비.setModel(semesterYG);
					} else {
						학기구분_급식비.setModel(semesterG);
					}

				}
			}
		});
		add(생활관명_급식비);

		// 급식비 학기구분

		학기구분_급식비.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		학기구분_급식비.setBorder(lb);
		학기구분_급식비.setBounds(232, 493, 150, 55);
		add(학기구분_급식비);

		// 급식비 식사구분

		식사구분_급식비.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		식사구분_급식비.setBorder(lb);
		식사구분_급식비.setBounds(382, 493, 150, 55);
		add(식사구분_급식비);

		// 사용료 등록 label모음

		생활관명_사용료_라벨.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		생활관명_사용료_라벨.setBounds(82, 155, 150, 55);
		생활관명_사용료_라벨.setBorder(lb);
		생활관명_사용료_라벨.setEnabled(false);
		add(생활관명_사용료_라벨);

		학기구분_사용료_라벨.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		학기구분_사용료_라벨.setBounds(232, 155, 150, 55);
		학기구분_사용료_라벨.setBorder(lb);
		학기구분_사용료_라벨.setEnabled(false);
		add(학기구분_사용료_라벨);

		생활관_사용료_라벨.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		생활관_사용료_라벨.setBounds(382, 155, 150, 55);
		생활관_사용료_라벨.setBorder(lb);
		생활관_사용료_라벨.setEnabled(false);
		add(생활관_사용료_라벨);

		// 급식비 등록 label모음

		생활관명_급식비_라벨.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		생활관명_급식비_라벨.setBounds(82, 438, 150, 55);
		생활관명_급식비_라벨.setBorder(lb);
		생활관명_급식비_라벨.setEnabled(false);
		add(생활관명_급식비_라벨);

		학기구분_급식비_라벨.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		학기구분_급식비_라벨.setBounds(232, 438, 150, 55);
		학기구분_급식비_라벨.setBorder(lb);
		학기구분_급식비_라벨.setEnabled(false);
		add(학기구분_급식비_라벨);

		식사구분_급식비_라벨.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		식사구분_급식비_라벨.setBounds(382, 438, 150, 55);
		식사구분_급식비_라벨.setBorder(lb);
		식사구분_급식비_라벨.setEnabled(false);
		add(식사구분_급식비_라벨);

		급식비_라벨.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		급식비_라벨.setBounds(532, 438, 150, 55);
		급식비_라벨.setBorder(lb);
		급식비_라벨.setEnabled(false);
		add(급식비_라벨);

		// 주의사항

		txtArea.setText(
				"\u203B\uC8FC\uC758\uC0AC\uD56D\r\n \uC0AC\uC6A9\uB8CC \uB4F1\uB85D\r\n \uC0DD\uD65C\uAD00\uBA85, \uD559\uAE30\uAD6C\uBD84\uC744 \uBAA8\uB450 \uCF64\uBCF4\uBC15\uC2A4\uC5D0\uC11C \uD074\uB9AD \uD6C4 \uB4F1\uB85D\uC744 \uB20C\uB7EC\uC57C\r\n \uB4F1\uB85D\uB429\uB2C8\uB2E4.\r\n \uAE09\uC2DD\uBE44 \uB4F1\uB85D\r\n \uC0DD\uD65C\uAD00\uBA85, \uD559\uAE30\uAD6C\uBD84, \uC2DD\uC0AC\uAD6C\uBD84\uC744 \uBAA8\uB450 \uCF64\uBCF4\uBC15\uC2A4\uC5D0\uC11C \uD074\uB9AD \uD6C4 \uB4F1\uB85D\uC744\r\n \uB20C\uB7EC\uC57C \uB4F1\uB85D\uB429\uB2C8\uB2E4.");

		txtArea.setEnabled(false);
		txtArea.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		txtArea.setBorder(lb);
		txtArea.setBounds(753, 112, 400, 507);
		add(txtArea);

		IntegerDocument 생활관_사용료_숫자 = new IntegerDocument();
		IntegerDocument 급식비_숫자 = new IntegerDocument();

		생활관_사용료_결과 = new JTextField();
		생활관_사용료_결과.setBounds(382, 210, 150, 55);
		생활관_사용료_결과.setBorder(lb);
		생활관_사용료_결과.setDocument(생활관_사용료_숫자);
		add(생활관_사용료_결과);
		생활관_사용료_결과.setColumns(10);

		급식비_결과 = new JTextField();
		급식비_결과.setBounds(532, 493, 150, 55);
		급식비_결과.setBorder(lb);
		급식비_결과.setDocument(급식비_숫자);
		add(급식비_결과);
		급식비_결과.setColumns(10);

	}

}

//textField 숫자만 받을 수 있게
class IntegerDocument extends PlainDocument {

	int currentValue = 0;

	public IntegerDocument() {
	}

	public int getValue() {
		return currentValue;
	}

	public void insertString(int offset, String string, AttributeSet attributes) throws BadLocationException {

		if (string == null) {
			return;
		} else {
			String newValue;
			int length = getLength();
			if (length == 0) {
				newValue = string;
			} else {
				String currentContent = getText(0, length);
				StringBuffer currentBuffer = new StringBuffer(currentContent);
				currentBuffer.insert(offset, string);
				newValue = currentBuffer.toString();
			}
			currentValue = checkInput(newValue, offset);
			super.insertString(offset, string, attributes);
		}
	}

	public void remove(int offset, int length) throws BadLocationException {
		int currentLength = getLength();
		String currentContent = getText(0, currentLength);
		String before = currentContent.substring(0, offset);
		String after = currentContent.substring(length + offset, currentLength);
		String newValue = before + after;
		currentValue = checkInput(newValue, offset);
		super.remove(offset, length);
	}

	public int checkInput(String proposedValue, int offset) throws BadLocationException {
		if (proposedValue.length() > 0) {
			try {
				int newValue = Integer.parseInt(proposedValue);
				return newValue;
			} catch (NumberFormatException e) {
				throw new BadLocationException(proposedValue, offset);
			}
		} else {
			return 0;
		}
	}
}