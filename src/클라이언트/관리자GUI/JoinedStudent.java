package 클라이언트.관리자GUI;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import 클라이언트.Protocol;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.Action;

public class JoinedStudent extends JPanel {

	private final Action action = new SwingAction();
	Vector<Student1> slist = new Vector<Student1>();

	public JoinedStudent(InputStream input, OutputStream output) {
		setBounds(100, 100, 1250, 800);

		JTextField 학번textField;

		setBackground(Color.WHITE);
		setBounds(100, 100, 1250, 800);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		JLabel lblNewLabel = new JLabel("▶ 생활관 관리 시스템 > 입사신청자 조회 및 입사 선발자 등록");
		lblNewLabel.setBounds(37, 46, 700, 29);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		add(lblNewLabel);

		JLabel 년도및학기Label = new JLabel("년도/학기");
		년도및학기Label.setBounds(37, 83, 86, 36);
		년도및학기Label.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		add(년도및학기Label);

		JComboBox 년도comboBox = new JComboBox();
		년도comboBox.setBounds(122, 83, 97, 37);
		년도comboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		년도comboBox.setModel(new DefaultComboBoxModel(new String[] { "\uC804\uCCB4", "2020" }));
		add(년도comboBox);

		JComboBox 학기comboBox = new JComboBox();
		학기comboBox.setBounds(236, 83, 93, 37);
		학기comboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		학기comboBox.setModel(new DefaultComboBoxModel(new String[] { "\uC804\uCCB4", "1\uD559\uAE30" }));
		add(학기comboBox);

		JLabel 대학구분Label = new JLabel("대학구분");
		대학구분Label.setBounds(346, 83, 75, 36);
		대학구분Label.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		add(대학구분Label);

		JComboBox 대학구분comboBox = new JComboBox();
		대학구분comboBox.setBounds(426, 83, 75, 37);
		대학구분comboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		대학구분comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "\uC804\uCCB4", "\uD559\uBD80\uC0DD", "\uB300\uD559\uC6D0\uC0DD" }));
		add(대학구분comboBox);

		JLabel label_6 = new JLabel("▶ 입사신청자 목록");
		label_6.setBounds(37, 243, 300, 29);
		label_6.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		add(label_6);

		JLabel label_7 = new JLabel("\uC0DD\uD65C\uAD00\uAD6C\uBD84");
		label_7.setBounds(37, 133, 86, 36);
		label_7.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		add(label_7);

		JComboBox 생활관comboBox = new JComboBox();
		생활관comboBox.setBounds(122, 133, 207, 37);
		생활관comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "\uC804\uCCB4", "\uD478\uB984\uAD001\uB3D9", "\uD478\uB984\uAD001\uB3D9 \uD0D1\uCE35",
						"\uD478\uB984\uAD002\uB3D9", "\uD478\uB984\uAD002\uB3D9 \uD0D1\uCE35",
						"\uD478\uB984\uAD003\uB3D9", "\uD478\uB984\uAD003\uB3D9 \uD0D1\uCE35",
						"\uD478\uB984\uAD004\uB3D9", "\uD478\uB984\uAD004\uB3D9 \uD0D1\uCE35", "\uC2E0\uD3C9\uAD00",
						"\uC624\uB984\uAD001\uB3D9", "\uC624\uB984\uAD002\uB3D9", "\uC624\uB984\uAD003\uB3D9" }));
		생활관comboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		add(생활관comboBox);
		JLabel label_9 = new JLabel("\uC9C0\uB9DD\uAD6C\uBD84");
		label_9.setBounds(346, 133, 64, 36);
		label_9.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		add(label_9);

		JComboBox 지망comboBox = new JComboBox();
		지망comboBox.setBounds(426, 132, 97, 39);
		지망comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "\uC804\uCCB4", "1\uB144", "1\uC9C0\uB9DD", "2\uC9C0\uB9DD", "3\uC9C0\uB9DD" }));
		지망comboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		add(지망comboBox);

		JLabel label_10 = new JLabel("\uC2DD\uBE44\uAD6C\uBD84");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(540, 133, 86, 36);
		label_10.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		add(label_10);

		JComboBox 식사comboBox = new JComboBox();
		식사comboBox.setBounds(654, 132, 97, 39);
		식사comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "\uC804\uCCB4", "\uC2DD\uC0AC\uC548\uD568", "5\uC77C\uC2DD", "7\uC77C\uC2DD" }));
		식사comboBox.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		add(식사comboBox);

		JLabel label_3 = new JLabel("학번");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label_3.setBounds(540, 83, 64, 36);
		add(label_3);

		학번textField = new JTextField();
		학번textField.setText(" ");
		학번textField.setHorizontalAlignment(SwingConstants.RIGHT);
		학번textField.setBounds(621, 83, 178, 36);
		학번textField.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		add(학번textField);
		학번textField.setColumns(10);

		JButton 조회button = new JButton("조회");
		조회button.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		조회button.setBackground(Color.WHITE);
		조회button.setBounds(1044, 134, 143, 36);
		add(조회button);

		JButton 삭제button = new JButton("삭제");
		삭제button.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		삭제button.setBackground(Color.WHITE);
		삭제button.setBounds(1077, 241, 110, 36);

		add(삭제button);

		JButton 추가button = new JButton("추가");
		추가button.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		추가button.setBackground(Color.WHITE);
		추가button.setBounds(955, 241, 110, 36);

		add(추가button);

		조회button.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) { // 조회버튼
				// 입사신청자조회 메시지 전송
				try {
					Protocol protocol = new Protocol(Protocol.TYPE7_VIEW_REQ, Protocol.T7_CD7_입사신청자조회요청);
					String condition_result = null;
					//전체 및 해당 조건에 대해 만족시키지 못하는 경우
					if ((!생활관comboBox.getSelectedItem().equals("전체") && 지망comboBox.getSelectedItem().equals("전체"))
							|| (생활관comboBox.getSelectedItem().equals("전체")
									&& !지망comboBox.getSelectedItem().equals("전체"))) {
						JOptionPane.showMessageDialog(null, "생활관명 또는 지망만 선택하셨습니다.");
						return;
					} else {
						condition_result = 년도comboBox.getSelectedItem() + "/" + 학기comboBox.getSelectedItem() + "/"
								+ 대학구분comboBox.getSelectedItem() + "/" + 생활관comboBox.getSelectedItem() + "/"
								+ 지망comboBox.getSelectedItem() + "/" + 식사comboBox.getSelectedItem() + "/"
								+ 학번textField.getText();// 조견결과를 서버로 전송하기 위해 String형태로 만듬
						protocol.setString(condition_result);
						output.write(protocol.getPacket());
					}
					// 입사신청자 조회 요청 결과 수신 후 데이터 처리
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
					slist = new Vector<Student1>();
					// 요청에 대한 정상 결과
					if (protocolType == Protocol.TYPE8_VIEW_RES && protocolCode == Protocol.T8_CD14_입사신청자조회승인) {
						// 리스트를 활용하여 조회 결과를 처리
						String[] temp = protocol.getString().split("/");
						int auto_checked = Integer.parseInt(temp[0]);
						String year; // 년도
						String semester; // 학기
						String university; // 대학구분
						String department; // 학과
						String level; // 학년
						String studentNumber; // 학번
						String name;// 성명
						String wish; // 지망
						String dormitory; // 생활관
						String meal; // 식사
						String address; // 학부모 주소
						float grade; // 성적

						for (int i = 1; i < temp.length;) {
							year = temp[i]; // 년도
							i++;
							semester = temp[i]; // 학기
							i++;
							university = temp[i]; // 대학구분
							i++;
							department = temp[i]; // 학과
							i++;
							level = temp[i]; // 학년
							i++;
							studentNumber = temp[i]; // 학번
							i++;
							name = temp[i];// 성명
							i++;
							wish = temp[i]; // 지망
							i++;
							dormitory = temp[i]; // 생활관
							i++;
							meal = temp[i]; // 식사
							i++;
							address = temp[i]; // 학부모 주소
							i++;
							grade = Float.parseFloat(temp[i]); // 성적
							i++;
							if (address.contains("제주도") || address.contains("울릉군")) {// 0.4점
								grade += 0.4;
							} else if (address.contains("서울") || address.contains("경기") || address.contains("인천")
									|| address.contains("강원") || address.contains("충청") || address.contains("전라")
									|| address.contains("광주") || address.contains("세종")) {// 0.3점
								grade += 0.3;
							} else if (address.contains("대전") || address.contains("부산") || address.contains("울산")
									|| address.contains("경상남도")) {// 0.2
								grade += 0.2;
							} else if (address.contains("경상북도") || address.contains("대구")) {// 0.1
								if (address.contains("구미") && address.contains("동")) {// 점수 없음
								} else {
									grade += 0.1;
								}
							}
							//벡터 리스트에 추가
							slist.add(new Student1(year, semester, university, department, level, studentNumber, name,
									wish, dormitory, meal, address, grade));
						}
						Collections.sort(slist, new StudentComparator()); // 성적 + 학부모 주소를 토대로 성적 정렬
						// ========================TABLE=============================
						int num = 0;
						Object[][] data;
						Object[] title = { "No", "체크", "대학구분", "학과명", "학년", "학번", "성명", "지망", "생활관구분", "식비구분" };
						data = new Object[slist.size()][10];
						for (int j = 0; j < data.length; j++) {
							data[j][0] = (j + 1);
							if (num < auto_checked) {
								data[j][1] = true;
								num++;
							} else {
								data[j][1] = false;
							}
							data[j][2] = slist.elementAt(j).university;
							data[j][3] = slist.elementAt(j).department;
							data[j][4] = slist.elementAt(j).level;
							data[j][5] = slist.elementAt(j).studentNumber;
							data[j][6] = slist.elementAt(j).name;
							data[j][7] = slist.elementAt(j).wish;
							data[j][8] = slist.elementAt(j).dormitory;
							data[j][9] = slist.elementAt(j).meal;
						}
						@SuppressWarnings("serial")
						DefaultTableCellRenderer dcr = new DefaultTableCellRenderer() {
							public Component getTableCellRendererComponent // 셀렌더러
							(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
								JCheckBox box = new JCheckBox();
								box.setSelected(((Boolean) value).booleanValue());
								box.setHorizontalAlignment(JLabel.CENTER);
								return box;
							}
						};
						DefaultTableModel dtm = new DefaultTableModel(data, title);
						JTable table = new JTable(dtm);
						table.getColumn("체크").setCellRenderer(dcr);
						table.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
						JCheckBox box = new JCheckBox();
						box.setHorizontalAlignment(JLabel.CENTER);
						table.getColumn("체크").setCellEditor(new DefaultCellEditor(box));

						JScrollPane scrollPane = new JScrollPane(table);
						scrollPane.setBounds(37, 307, 1150, 229);
						add(scrollPane);
						추가button.addActionListener(new ActionListener() { // 추가버튼 액션
							public void actionPerformed(ActionEvent e) {
								try {
									// 선택된 학생들을 입사선발자로 등록을 요청
									int add_cnt = 0;
									Protocol protocol = new Protocol(Protocol.TYPE9_RENEW_REQ,
											Protocol.T9_CD1_입사신청자를입사선발자로등록요청);
									String result = ""; // 체크결과에 대해 결과string 저장
									for (int j = 0; j < slist.size(); j++) {
										Boolean comboboxChecked = Boolean.valueOf(table.getValueAt(j, 1).toString());
										if (comboboxChecked) {
											String year = slist.elementAt(j).year;
											String semester = slist.elementAt(j).semester;
											String university = slist.elementAt(j).university;
											String studentNumber = slist.elementAt(j).studentNumber;
											String wish = slist.elementAt(j).wish;
											String dormitory = slist.elementAt(j).dormitory;
											String meal = slist.elementAt(j).meal;
											result += year + "/" + semester + "/" + university + "/" + studentNumber
													+ "/" + wish + "/" + dormitory + "/" + meal + "/";
											add_cnt++;
										}
									}
									//선택 항목이 없는 경우
									if (add_cnt == 0) {
										JOptionPane.showMessageDialog(null, "선택된 항목이 없습니다.");
										return;
									} else {
										protocol.setString(result);
										output.write(protocol.getPacket());
									}
									// 입사선발자합격처리 요청결과를 받음
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
											&& protocolCode == Protocol.T10_CD2_입사신청자를입사선발자로등록성공) {
										JOptionPane.showMessageDialog(null, "정상 처리되었습니다");
										return;
									}

								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							}
						});
						삭제button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									// 선택된 학생들을 입사신청자에서 삭제요청
									int remove_cnt = 0;
									Protocol protocol = new Protocol(Protocol.TYPE9_RENEW_REQ,
											Protocol.T9_CD4_입사신청자를목록에서삭제요청);
									String result = ""; // 체크결과에 대해 결과string 저장
									for (int j = 0; j < slist.size(); j++) {
										Boolean comboboxChecked = Boolean.valueOf(table.getValueAt(j, 1).toString());
										if (comboboxChecked) {
											String year = slist.elementAt(j).year;
											String semester = slist.elementAt(j).semester;
											String university = slist.elementAt(j).university;
											String studentNumber = slist.elementAt(j).studentNumber;
											String wish = slist.elementAt(j).wish;
											String dormitory = slist.elementAt(j).dormitory;
											String meal = slist.elementAt(j).meal;
											result += year + "/" + semester + "/" + university + "/" + studentNumber
													+ "/" + wish + "/" + dormitory + "/" + meal + "/";
											remove_cnt++;
										}

									}
									if (remove_cnt == 0) {
										JOptionPane.showMessageDialog(null, "선택된 항목이 없습니다.");
										return;
									} else {
										protocol.setString(result);
										output.write(protocol.getPacket());
									}

									// 입사신청자삭제 요청결과를 받음
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
											&& protocolCode == Protocol.T10_CD8_입사신청자를목록에서삭제성공) {
										JOptionPane.showMessageDialog(null, "정상 처리되었습니다");
										return;
									}
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});
					} else if (protocolType == Protocol.TYPE8_VIEW_RES && protocolCode == Protocol.T8_CD15_입사신청자조회거절) { // 입사신청자조회거절
						JOptionPane.showMessageDialog(null, "조회결과가 존재하지 않습니다.");
						return;
					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}

	class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}

class Student1 {
	public String year;
	public String semester;
	public String university;
	public String department;
	public String level;
	public String studentNumber;
	public String name;
	public String wish;
	public String dormitory;
	public String meal;
	public String address;
	public float grade;

	public String getYear() {
		return year;
	}

	public String getSemester() {
		return semester;
	}

	public String getUniversity() {
		return university;
	}

	public String getDepartment() {
		return department;
	}

	public String getLevel() {
		return level;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public String getName() {
		return name;
	}

	public String getWish() {
		return wish;
	}

	public String getDormitory() {
		return dormitory;
	}

	public String getMeal() {
		return meal;
	}

	public String getAddress() {
		return address;
	}

	public float getGrade() {
		return grade;
	}

	Student1(String year, String semester, String university, String department, String level, String studentNumber,
			String name, String wish, String dormitory, String meal, String address, float grade) {
		this.year = year;
		this.semester = semester;
		this.university = university;
		this.department = department;
		this.level = level;
		this.studentNumber = studentNumber;
		this.name = name;
		this.wish = wish;
		this.dormitory = dormitory;
		this.meal = meal;
		this.grade = grade;
	}
}

class StudentComparator implements Comparator {
	public int compare(Object grade0, Object grade1) {
		return ((Student1) grade0).grade > ((Student1) grade1).grade ? -1 : (grade0 == grade1 ? 0 : 1);
	}

}