package 클라이언트.학생GUI;

import 클라이언트.Protocol;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.*;
import javax.swing.border.LineBorder;

//생활관입사신청을 위한 창
public class DorApplication extends JPanel {

	// 프로토콜 판별자
	boolean dist = true;
	// 1년신청String
	String[] yearDom = { "선택안함", "푸름관2동", "푸름관3동" };

	// 1학기신청String
	String[] semesterDom = { "선택안함", "푸름관1동", "푸름관1동 탑층", "푸름관2동", "푸름관2동 탑층", "푸름관3동", "푸름관3동 탑층", "푸름관4동", "오름관1동",
			"오름관2동", "오름관3동", "신평관 1인실", "신평관 2인실" };

	// 7일식 String, 5일식 String
	String[] noMeal = { "선택안함", "7일식", "5일식", "식사안함" };
	String[] meal = { "선택안함", "7일식", "5일식" };

	// 콤보박스 내용 바꾸기
	DefaultComboBoxModel nomeal1C = new DefaultComboBoxModel(noMeal);
	DefaultComboBoxModel meal1C = new DefaultComboBoxModel(noMeal);
	DefaultComboBoxModel nomeal2C = new DefaultComboBoxModel(noMeal);
	DefaultComboBoxModel meal2C = new DefaultComboBoxModel(noMeal);
	DefaultComboBoxModel nomeal3C = new DefaultComboBoxModel(noMeal);
	DefaultComboBoxModel meal3C = new DefaultComboBoxModel(noMeal);

	// 1년신청
	JComboBox<String> yearCombo1 = new JComboBox<String>(yearDom);//
	JComboBox<String> yearCombo2 = new JComboBox<String>(noMeal);//
	JComboBox<String> yearCombo3 = new JComboBox<String>(noMeal);//
	// 1지망신청
	JComboBox<String> half1Combo1 = new JComboBox<String>(semesterDom);//
	JComboBox<String> half1Combo2 = new JComboBox<String>(noMeal);//
	// 2지망신청
	JComboBox<String> half2Combo1 = new JComboBox<String>(semesterDom);//
	JComboBox<String> half2Combo2 = new JComboBox<String>(noMeal);//
	// 3지망신청
	JComboBox<String> half3Combo1 = new JComboBox<String>(semesterDom);//
	JComboBox<String> half3Combo2 = new JComboBox<String>(noMeal);//

	//
	JLabel label = new JLabel("\u25B6 \uD55C\uD559\uAE30 \uAE30\uC219 \uBAA8\uC9D1");
	JLabel label_1 = new JLabel("\u25B6 1\uB144 \uAE30\uC219 \uBAA8\uC9D1");

	// 신청버튼
	JButton button = new JButton("신청");
	JLabel 구분_1년 = new JLabel("\uAD6C\uBD84");
	JLabel 식사구분_학기 = new JLabel("식사구분(학기)");
	JLabel 식사구분_방학 = new JLabel("식사구분(방학)");

	JLabel 생활관비_1년 = new JLabel("\uC0DD\uD65C\uAD00\uBE44");
	JLabel 생활관비_1년_결과 = new JLabel(" ");
	JLabel 지망1 = new JLabel("1\uC9C0\uB9DD");
	JLabel 식사구분1 = new JLabel("\uC2DD\uC0AC\uAD6C\uBD84");
	JLabel 생활관비1 = new JLabel("\uC0DD\uD65C\uAD00\uBE44");
	JLabel 생활관비_1지망_결과 = new JLabel(" ");
	JLabel 지망2 = new JLabel("2\uC9C0\uB9DD");
	JLabel 식사구분2 = new JLabel("\uC2DD\uC0AC\uAD6C\uBD84");
	JLabel 생활관비2 = new JLabel("\uC0DD\uD65C\uAD00\uBE44");
	JLabel 생활관비_2지망_결과 = new JLabel(" ");
	JLabel 지망3 = new JLabel("3\uC9C0\uB9DD");
	JLabel 식사구분3 = new JLabel("\uC2DD\uC0AC\uAD6C\uBD84");
	JLabel 생활관비3 = new JLabel("\uC0DD\uD65C\uAD00\uBE44");
	JLabel 생활관비_3지망_결과 = new JLabel(" ");

	public DorApplication(InputStream input, OutputStream output) {
		setBounds(100, 50, 1250, 800);
		LineBorder lb = new LineBorder(Color.black, 1, false);
		setBackground(Color.WHITE);
		setBounds(100, 100, 1250, 800);

		yearCombo1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		yearCombo1.setEditable(false);
		yearCombo2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		yearCombo2.setEditable(false);
		yearCombo3.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		yearCombo3.setEditable(false);
		half1Combo1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		half1Combo1.setEditable(false);
		half1Combo2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		half1Combo2.setEditable(false);
		half2Combo1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		half2Combo1.setEditable(false);
		half2Combo2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		half2Combo2.setEditable(false);
		half3Combo1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		half3Combo1.setEditable(false);
		half3Combo2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		half3Combo2.setEditable(false);

		// 1년 신청
		yearCombo1.setSize(125, 50);
		yearCombo1.setLocation(225, 133);
		add(yearCombo1);
		yearCombo1.addActionListener(new ActionListener() {
			// 1년 생활관을 선택하면 나머지 선택안함으로 초기화
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == yearCombo1) {
					yearCombo2.setSelectedIndex(0);
					yearCombo3.setSelectedIndex(0);
					생활관비_1년_결과.setText("");
				}
			}
		});

		yearCombo2.setSize(125, 50);
		yearCombo2.setLocation(475, 133);
		add(yearCombo2);
		yearCombo2.addActionListener(new ActionListener() {
			// 식사구분(학기)을 선택하면 식사구분(방학)이 선택되어있다면 생활관비조회.
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == yearCombo2) {
					int tmp = 0;
					JComboBox<String> cb = (JComboBox<String>) e.getSource();
					int item = cb.getSelectedIndex();
					// 값중에 하나라도 선택안함이 있고 yearCombo2의 값을 선택했다면 생활관비값을 ""으로 바꾸어줌.
					if (yearCombo1.getSelectedItem() != "선택안함" && yearCombo2.getSelectedItem() != "선택안함"
							&& yearCombo3.getSelectedItem() != "선택안함") {
						try {
							// 생활관, 식사구분(학기), 식사구분(방학)을 data에 넣어줌.
							String data = yearCombo1.getSelectedItem() + "/" + "1년" + "/" + yearCombo2.getSelectedItem()
									+ "/" + yearCombo3.getSelectedItem();
							Protocol protocol = new Protocol(Protocol.TYPE7_VIEW_REQ, Protocol.T7_CD1_생활관비조회요청);
							protocol.setString(data);
							output.write(protocol.getPacket());

							for (int i = 0; i <= 1; i++) {
								protocol = new Protocol();
								byte[] header = protocol.getPacket();
								input.read(header);
								int protocolType = header[0];
								int protocolCode = header[1];
								if (protocolType == Protocol.TYPE8_VIEW_RES
										&& protocolCode == Protocol.T8_CD2_생활관비조회승인) {
									int bodylength = protocol.byteToInt(header, 2);
									if (bodylength != 0) {
										byte[] body = new byte[bodylength];
										input.read(body);
										protocol.setPacket(header, body);
									}
									if (protocolType == Protocol.TYPE8_VIEW_RES
											&& protocolCode == Protocol.T8_CD2_생활관비조회승인) {
										// 생활관결과값을 tmp에 더함.
										tmp += Integer.parseInt(protocol.getString());

									} else if (protocolType == Protocol.TYPE8_VIEW_RES
											&& protocolCode == Protocol.T8_CD3_생활관비조회거절) {
									}
								}
							}
							// 결과값을 띄워줌.
							생활관비_1년_결과.setText(Integer.toString(tmp));
						} catch (IOException e1) {
						}
					}
					// 하나라도 선택을 하지 않았으므로 결과값을 ""으로 초기화.
					else {
						생활관비_1년_결과.setText("");
					}
				}
			}
		});

		yearCombo3.setSize(125, 50);
		yearCombo3.setLocation(745, 133);
		add(yearCombo3);
		yearCombo3.addActionListener(new ActionListener() {
			// 식사구분(방학)을 선택하면 식사구분(학기)이 선택되어있다면 생활관비조회.
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == yearCombo3) {
					int tmp = 0;
					JComboBox<String> cb = (JComboBox<String>) e.getSource();
					int item = cb.getSelectedIndex();
					// 값중에 하나라도 선택안함이 있고 yearCombo3의 값을 선택했다면 생활관비값을 ""으로 바꾸어줌.
					if (yearCombo1.getSelectedItem() != "선택안함" && yearCombo2.getSelectedItem() != "선택안함"
							&& yearCombo3.getSelectedItem() != "선택안함") {
						try {
							// 생활관, 식사구분(학기), 식사구분(방학)을 data에 넣어줌.
							String data = yearCombo1.getSelectedItem() + "/" + "1년" + "/" + yearCombo2.getSelectedItem()
									+ "/" + yearCombo3.getSelectedItem();
							Protocol protocol = new Protocol(Protocol.TYPE7_VIEW_REQ, Protocol.T7_CD1_생활관비조회요청);
							protocol.setString(data);
							output.write(protocol.getPacket());

							for (int i = 0; i <= 1; i++) {
								protocol = new Protocol();
								byte[] header = protocol.getPacket();
								input.read(header);
								int protocolType = header[0];
								int protocolCode = header[1];
								if (protocolType == Protocol.TYPE8_VIEW_RES
										&& protocolCode == Protocol.T8_CD2_생활관비조회승인) {
									int bodylength = protocol.byteToInt(header, 2);
									if (bodylength != 0) {
										byte[] body = new byte[bodylength];
										input.read(body);
										protocol.setPacket(header, body);
									}
									if (protocolType == Protocol.TYPE8_VIEW_RES
											&& protocolCode == Protocol.T8_CD2_생활관비조회승인) {
										// 생활관결과값을 tmp에 더함.
										tmp += Integer.parseInt(protocol.getString());

									} else if (protocolType == Protocol.TYPE8_VIEW_RES
											&& protocolCode == Protocol.T8_CD3_생활관비조회거절) {
									}
								}
							}
							// 결과값을 띄워줌.
							생활관비_1년_결과.setText(Integer.toString(tmp));
						} catch (IOException e1) {
						}
					}
					// 하나라도 선택을 하지 않았으므로 결과값을 ""으로 초기화.
					else {
						생활관비_1년_결과.setText("");
					}
				}
			}
		});

		// 1학기 1지망
		half1Combo1.setSize(166, 50);
		half1Combo1.setLocation(266, 330);
		add(half1Combo1);

		half1Combo1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == half1Combo1) {
					// 1지망값을 선택시 식사와 결과값을 초기화
					half1Combo2.setSelectedIndex(0);
					생활관비_1지망_결과.setText("");
					// 선택안함을 선택시 동작없음.
					if (!half1Combo1.getSelectedItem().toString().equals("선택안함")) {
						// 2지망, 3지망과 기숙사중복시 기숙사중복을 띄우고 1지망의 값을 모두 초기화.
						if (half2Combo1.getSelectedItem().toString().equals(half1Combo1.getSelectedItem().toString())
								|| half3Combo1.getSelectedItem().toString()
										.equals(half1Combo1.getSelectedItem().toString())) {
							JOptionPane.showMessageDialog(null, "기숙사중복입니다.");
							half1Combo1.setSelectedIndex(0);
							half1Combo2.setSelectedIndex(0);
							생활관비_1지망_결과.setText("");
						}
						// 중복이 아니라면 선택값에 따라서 식사구분의 모델을 다르게 넣어줌.
						else {
							JComboBox<String> cb = (JComboBox<String>) e.getSource();
							int item = cb.getSelectedIndex();
							if (item == 8 || item == 9 || item == 10) {
								half1Combo2.setModel(meal1C);
							} else {
								half1Combo2.setModel(nomeal1C);
							}
						}
					}
				}
			}
		});

		half1Combo2.setSize(166, 50);
		half1Combo2.setLocation(598, 330);
		add(half1Combo2);
		half1Combo2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == half1Combo2) {
					try {
						// 1지망 생활관이나 식사가 선택안함인 경우 생활관비초기화
						if (half1Combo1.getSelectedItem().toString().equals("선택안함")) {
							생활관비_1지망_결과.setText("");
						} else if (half1Combo2.getSelectedItem().toString().equals("선택안함")) {
							생활관비_1지망_결과.setText("");
						}
						// 생활관과 식사가 선택이 되면 생활관비조회요청함.
						else {
							// data에 1지망 생활관과 식사구분을 넣음
							String temp = "";
							String data = half1Combo1.getSelectedItem().toString() + "/" + "한학기" + "/"
									+ half1Combo2.getSelectedItem().toString();
							// 프로토콜발신
							Protocol protocol = new Protocol(Protocol.TYPE7_VIEW_REQ, Protocol.T7_CD1_생활관비조회요청);
							protocol.setString(data);
							output.write(protocol.getPacket());

							// 프로토콜수신
							protocol = new Protocol();
							byte[] header = protocol.getPacket();
							input.read(header);

							int protocolType = header[0];
							int protocolCode = header[1];
							if (protocolType == Protocol.TYPE8_VIEW_RES && protocolCode == Protocol.T8_CD2_생활관비조회승인) {
								int bodylength = protocol.byteToInt(header, 2);
								if (bodylength != 0) {
									byte[] body = new byte[bodylength];
									input.read(body);
									protocol.setPacket(header, body);
								}
								temp = protocol.getString();
								// 생활관비 띄워줌.
								생활관비_1지망_결과.setText(temp);
							} else if (protocolType == Protocol.TYPE8_VIEW_RES
									&& protocolCode == Protocol.T8_CD3_생활관비조회거절) {
							}
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}

		});

		// 1학기 2지망

		half2Combo1.setSize(166, 50);
		half2Combo1.setLocation(266, 455);
		add(half2Combo1);
		half2Combo1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == half2Combo1) {
					// 2지망값을 선택시 식사와 결과값을 초기화
					half2Combo2.setSelectedIndex(0);
					생활관비_2지망_결과.setText("");
					// 선택안함을 선택시 동작없음.
					if (!half2Combo1.getSelectedItem().toString().equals("선택안함")) {
						// 1지망, 3지망과 기숙사중복시 기숙사중복을 띄우고 2지망의 값을 모두 초기화.
						if (half1Combo1.getSelectedItem().toString().equals(half2Combo1.getSelectedItem().toString())
								|| half3Combo1.getSelectedItem().toString()
										.equals(half2Combo1.getSelectedItem().toString())) {
							JOptionPane.showMessageDialog(null, "기숙사중복입니다.");
							half2Combo1.setSelectedIndex(0);
							half2Combo2.setSelectedIndex(0);
							생활관비_2지망_결과.setText("");
						}
						// 중복이 아니라면 선택값에 따라서 식사구분의 모델을 다르게 넣어줌.
						else {
							JComboBox<String> cb = (JComboBox<String>) e.getSource();
							int item = cb.getSelectedIndex();
							if (item == 8 || item == 9 || item == 10) {
								half2Combo2.setModel(meal2C);
							} else {
								half2Combo2.setModel(nomeal2C);
							}
						}
					}
				}

			}
		});

		half2Combo2.setSize(166, 50);
		half2Combo2.setLocation(598, 455);
		add(half2Combo2);
		half2Combo2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == half2Combo2) {
					try {
						// 2지망 생활관이나 식사가 선택안함인 경우 생활관비초기화
						if (half2Combo1.getSelectedItem().toString().equals("선택안함")) {
							생활관비_2지망_결과.setText("");
						} else if (half2Combo2.getSelectedItem().toString().equals("선택안함")) {
							생활관비_2지망_결과.setText("");
						}
						// 생활관과 식사가 선택이 되면 생활관비조회요청함.
						else {
							// data에 2지망 생활관과 식사구분을 넣음
							String temp = "";
							String data = half2Combo1.getSelectedItem().toString() + "/" + "한학기" + "/"
									+ half2Combo2.getSelectedItem().toString();
							// 프로토콜발신
							Protocol protocol = new Protocol(Protocol.TYPE7_VIEW_REQ, Protocol.T7_CD1_생활관비조회요청);
							protocol.setString(data);
							output.write(protocol.getPacket());

							// 프로토콜수신
							protocol = new Protocol();
							byte[] header = protocol.getPacket();
							input.read(header);

							int protocolType = header[0];
							int protocolCode = header[1];
							if (protocolType == Protocol.TYPE8_VIEW_RES && protocolCode == Protocol.T8_CD2_생활관비조회승인) {
								int bodylength = protocol.byteToInt(header, 2);
								if (bodylength != 0) {
									byte[] body = new byte[bodylength];
									input.read(body);
									protocol.setPacket(header, body);
								}
								temp = protocol.getString();
								// 생활관비 띄워줌.
								생활관비_2지망_결과.setText(temp);
							} else if (protocolType == Protocol.TYPE8_VIEW_RES
									&& protocolCode == Protocol.T8_CD3_생활관비조회거절) {
							}
						}
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
			}
		});

		// 1학기 3지망

		half3Combo1.setSize(166, 50);
		half3Combo1.setLocation(266, 580);
		add(half3Combo1);
		half3Combo1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == half3Combo1) {
					// 3지망값을 선택시 식사와 결과값을 초기화
					half3Combo2.setSelectedIndex(0);
					생활관비_3지망_결과.setText("");
					// 선택안함을 선택시 동작없음.
					if (!half3Combo1.getSelectedItem().toString().equals("선택안함")) {
						// 1지망, 2지망과 기숙사중복시 기숙사중복을 띄우고 3지망의 값을 모두 초기화.
						if (half1Combo1.getSelectedItem().toString().equals(half3Combo1.getSelectedItem().toString())
								|| half2Combo1.getSelectedItem().toString()
										.equals(half3Combo1.getSelectedItem().toString())) {
							JOptionPane.showMessageDialog(null, "기숙사중복입니다.");
							half3Combo1.setSelectedIndex(0);
							half3Combo2.setSelectedIndex(0);
							생활관비_3지망_결과.setText("");
						}
						// 중복이 아니라면 선택값에 따라서 식사구분의 모델을 다르게 넣어줌.
						else {
							JComboBox<String> cb = (JComboBox<String>) e.getSource();
							int item = cb.getSelectedIndex();
							if (item == 8 || item == 9 || item == 10) {
								half3Combo2.setModel(meal3C);

							} else {
								half3Combo2.setModel(nomeal3C);
							}
						}
					}
				}
			}
		});

		half3Combo2.setSize(166, 50);
		half3Combo2.setLocation(598, 580);
		add(half3Combo2);
		half3Combo2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == half3Combo2) {
					try {
						// 3지망 생활관이나 식사가 선택안함인 경우 생활관비초기화
						if (half3Combo1.getSelectedItem().toString().equals("선택안함")) {
							생활관비_3지망_결과.setText("");
						} else if (half3Combo2.getSelectedItem().toString().equals("선택안함")) {
							생활관비_3지망_결과.setText("");
						}
						// 생활관과 식사가 선택이 되면 생활관비조회요청함.
						else {
							// data에 3지망 생활관과 식사구분을 넣음
							String temp = "";
							String data = half3Combo1.getSelectedItem().toString() + "/" + "한학기" + "/"
									+ half3Combo2.getSelectedItem().toString();
							// 프로토콜발신
							Protocol protocol = new Protocol(Protocol.TYPE7_VIEW_REQ, Protocol.T7_CD1_생활관비조회요청);
							protocol.setString(data);
							output.write(protocol.getPacket());

							// 프로토콜수신
							protocol = new Protocol();
							byte[] header = protocol.getPacket();
							input.read(header);

							int protocolType = header[0];
							int protocolCode = header[1];
							if (protocolType == Protocol.TYPE8_VIEW_RES && protocolCode == Protocol.T8_CD2_생활관비조회승인) {
								int bodylength = protocol.byteToInt(header, 2);
								if (bodylength != 0) {
									byte[] body = new byte[bodylength];
									input.read(body);
									protocol.setPacket(header, body);
								}
								temp = protocol.getString();
								// 생활관비 띄워줌.
								생활관비_3지망_결과.setText(temp);
							} else if (protocolType == Protocol.TYPE8_VIEW_RES
									&& protocolCode == Protocol.T8_CD3_생활관비조회거절) {
							}
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		setLayout(null);

		// 1년기숙모집
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBounds(100, 280, 197, 23);
		add(label);

		// 한학기기숙모집
		label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label_1.setBounds(100, 83, 197, 23);
		add(label_1);

		// 신청버튼
		button.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		button.setBounds(1003, 90, 97, 34);
		// buttonAction
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == button) {
					dist = true;
					String data = "";
					// 보낼 데이터를 케이스에 따라 다르게 만들기
					// 케이스: 1년, 1년1지망, 1년2지망, 1년3지망, 1지망, 1지망2지망, 1지망2지망3지망
					if (!(생활관비_1년_결과.getText().equals("") && 생활관비_1지망_결과.getText().equals("")
							&& 생활관비_2지망_결과.getText().equals("") && 생활관비_3지망_결과.getText().equals(""))) {
						try {
							// 학기만
							if (생활관비_1년_결과.getText().equals("")) {
								if (생활관비_3지망_결과.getText().equals("")) {
									// 1지망
									if (생활관비_2지망_결과.getText().equals("")) {
										data = half1Combo1.getSelectedItem().toString() + "/"
												+ half1Combo2.getSelectedItem().toString();
									}
									// 1지망 먼저 선택
									else if (생활관비_1지망_결과.getText().equals("")) {
										JOptionPane.showMessageDialog(null, "1지망부터 선택");
										dist = false;
									}
									// 1지망, 2지망
									else {
										data = half1Combo1.getSelectedItem().toString() + "/"
												+ half1Combo2.getSelectedItem().toString() + "/"
												+ half2Combo1.getSelectedItem().toString() + "/"
												+ half2Combo2.getSelectedItem().toString();
									}
								}
								// 1지망, 2지망, 3지망
								else if (생활관비_1지망_결과.getText().equals("") || 생활관비_2지망_결과.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "1지망 또는 2지망부터 선택");
									dist = false;
								} else {
									data = half1Combo1.getSelectedItem().toString() + "/"
											+ half1Combo2.getSelectedItem().toString() + "/"
											+ half2Combo1.getSelectedItem().toString() + "/"
											+ half2Combo2.getSelectedItem().toString() + "/"
											+ half3Combo1.getSelectedItem().toString() + "/"
											+ half3Combo2.getSelectedItem().toString();
								}
							}
							// 다시
							// 1년 + 학기
							else {
								// 1년
								if (생활관비_1지망_결과.getText().equals("") && 생활관비_2지망_결과.getText().equals("")
										&& 생활관비_3지망_결과.getText().equals("")) {
									data = yearCombo1.getSelectedItem().toString() + "/"
											+ yearCombo2.getSelectedItem().toString() + "/"
											+ yearCombo3.getSelectedItem().toString();
								}
								// 1, 3선택 2x
								else if (생활관비_1지망_결과.getText().equals("") && 생활관비_2지망_결과.getText().equals("")
										&& !생활관비_3지망_결과.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "2지망부터 선택");
									dist = false;
								} else if (생활관비_1지망_결과.getText().equals("") && !생활관비_2지망_결과.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "1지망부터 선택");
									dist = false;
								} else if (!생활관비_1지망_결과.getText().equals("") && 생활관비_2지망_결과.getText().equals("")
										&& !생활관비_3지망_결과.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "2지망부터 선택");
									dist = false;
								} else {
									if (생활관비_3지망_결과.getText().equals("")) {
										// 1년 + 1지망
										if (생활관비_2지망_결과.getText().equals("")) {
											data = yearCombo1.getSelectedItem().toString() + "/"
													+ yearCombo2.getSelectedItem().toString() + "/"
													+ yearCombo3.getSelectedItem().toString() + "/"
													+ half1Combo1.getSelectedItem().toString() + "/"
													+ half1Combo2.getSelectedItem().toString();
										}
										// 1년 + 1지망 + 2지망
										else {
											data = yearCombo1.getSelectedItem().toString() + "/"
													+ yearCombo2.getSelectedItem().toString() + "/"
													+ yearCombo3.getSelectedItem().toString() + "/"
													+ half1Combo1.getSelectedItem().toString() + "/"
													+ half1Combo2.getSelectedItem().toString() + "/"
													+ half2Combo1.getSelectedItem().toString() + "/"
													+ half2Combo2.getSelectedItem().toString();
										}
									}
									// 1지망 + 2지망 + 3지망
									else {
										data = yearCombo1.getSelectedItem().toString() + "/"
												+ yearCombo2.getSelectedItem().toString() + "/"
												+ yearCombo3.getSelectedItem().toString() + "/"
												+ half1Combo1.getSelectedItem().toString() + "/"
												+ half1Combo2.getSelectedItem().toString() + "/"
												+ half2Combo1.getSelectedItem().toString() + "/"
												+ half2Combo2.getSelectedItem().toString() + "/"
												+ half3Combo1.getSelectedItem().toString() + "/"
												+ half3Combo2.getSelectedItem().toString();
									}
								}
							}
							// 실패신호를 보내지 않고 제대로 된 데이터를 만들었을 경우
							if (dist == true) {
								Protocol protocol = new Protocol(Protocol.TYPE9_RENEW_REQ, Protocol.T9_CD0_입사신청요청);
								protocol.setString(data);
								output.write(protocol.getPacket());

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
								if (protocolType == Protocol.TYPE10_RENEW_RES
										&& protocolCode == Protocol.T10_CD0_입사신청성공) {
									JOptionPane.showMessageDialog(null, "입사신청성공");
								} else if (protocol.getString().equals("") && protocolType == Protocol.TYPE10_RENEW_RES
										&& protocolCode == Protocol.T10_CD1_입사신청실패) {
									JOptionPane.showMessageDialog(null, "입사신청실패");
								} else {
									JOptionPane.showMessageDialog(null, "이성생활관신청불가");
								}
							}
						} catch (IOException e1) {

						}
					} else {
						JOptionPane.showMessageDialog(null, "모든 콤보박스 선택");
					}
				}
			}
		});
		add(button);

		// 1년 기숙

		구분_1년.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		구분_1년.setBounds(100, 133, 125, 50);
		구분_1년.setBorder(lb);
		add(구분_1년);

		식사구분_학기.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		식사구분_학기.setBounds(350, 133, 125, 50);
		식사구분_학기.setBorder(lb);
		add(식사구분_학기);

		식사구분_방학.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		식사구분_방학.setBounds(600, 133, 145, 50);
		식사구분_방학.setBorder(lb);
		add(식사구분_방학);

		생활관비_1년.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		생활관비_1년.setBounds(870, 133, 105, 50);
		생활관비_1년.setBorder(lb);
		add(생활관비_1년);

		생활관비_1년_결과.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		생활관비_1년_결과.setBounds(975, 133, 125, 50);
		생활관비_1년_결과.setBorder(lb);
		생활관비_1년_결과.setText("");
		add(생활관비_1년_결과);

		// 1학기 1지망

		지망1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		지망1.setBounds(100, 330, 166, 50);
		지망1.setBorder(lb);
		add(지망1);

		식사구분1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		식사구분1.setBounds(432, 330, 166, 50);
		식사구분1.setBorder(lb);
		add(식사구분1);

		생활관비1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		생활관비1.setBounds(764, 330, 166, 50);
		생활관비1.setBorder(lb);
		add(생활관비1);

		// 생활관비

		생활관비_1지망_결과.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		생활관비_1지망_결과.setBounds(930, 330, 166, 50);
		생활관비_1지망_결과.setBorder(lb);
		생활관비_1지망_결과.setText("");
		add(생활관비_1지망_결과);

		// 1학기 2지망

		지망2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		지망2.setBounds(100, 455, 166, 50);
		지망2.setBorder(lb);
		add(지망2);

		식사구분2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		식사구분2.setBounds(432, 455, 166, 50);
		식사구분2.setBorder(lb);
		add(식사구분2);

		생활관비2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		생활관비2.setBounds(764, 455, 166, 50);
		생활관비2.setBorder(lb);
		add(생활관비2);

		// 생활관비

		생활관비_2지망_결과.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		생활관비_2지망_결과.setBounds(930, 455, 166, 50);
		생활관비_2지망_결과.setBorder(lb);
		생활관비_2지망_결과.setText("");
		add(생활관비_2지망_결과);

		// 1학기 3지망

		지망3.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		지망3.setBounds(100, 580, 166, 50);
		지망3.setBorder(lb);
		add(지망3);

		식사구분3.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		식사구분3.setBounds(432, 580, 166, 50);
		식사구분3.setBorder(lb);
		add(식사구분3);

		생활관비3.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		생활관비3.setBounds(764, 580, 166, 50);
		생활관비3.setBorder(lb);
		add(생활관비3);

		// 생활관비

		생활관비_3지망_결과.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		생활관비_3지망_결과.setBounds(930, 580, 166, 50);
		생활관비_3지망_결과.setBorder(lb);
		생활관비_3지망_결과.setText("");
		add(생활관비_3지망_결과);

	}

}