package 클라이언트.관리자GUI;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import 클라이언트.Protocol;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

public class SelectedStudent extends JPanel {

   public static JComboBox 년도Combo, 학기Combo, 대학구분Combo, 학과Combo, 결핵여부Combo, 납부구분Combo, 생활관구분Combo = null;
   public static JTextField 학번 = null;
   private static JTextField 학번Text;
   Vector<Student> slist, real_slist;

   public SelectedStudent(InputStream input, OutputStream output) {

      // setVisible(true);
      JLabel label_11, label_13;

      setBounds(100, 50, 1250, 800);
      setBackground(Color.WHITE);
      setBorder(new EmptyBorder(5, 5, 5, 5));
      setLayout(null);

      // 입사선발자 초기 셋팅
      Object[][] data = new Object[0][9];
      Object[] title = { "No", "체크", "대학구분", "학과명", "학년", "학번", "성명", "생활관구분", "선발결과", "결핵진단서제출일시", "입금여부" };

      JTable table = new JTable(data, title);

      table.setFont(new Font("맑은 고딕", Font.PLAIN, 16));

      JScrollPane scrollPane = new JScrollPane(table);
      scrollPane.setBounds(27, 244, 1197, 188);
      add(scrollPane);

      // 입사자 초기 셋팅
      Object[] title_1 = { "No", "대학구분", "학과명", "학년", "학번", "성명", "생활관구분", "호실", "침대번호" };
      Object[][] data_1 = new Object[0][11];
      JTable table_1 = new JTable(data_1, title_1);
      JScrollPane scrollPane_1 = new JScrollPane(table_1);
      scrollPane_1.setBounds(27, 498, 1197, 188);
      add(scrollPane_1);

      JLabel label = new JLabel("\u25B6 입사선발자 및 입사자 조회");
      label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      label.setBounds(37, 10, 300, 46);
      add(label);

      JLabel label_1 = new JLabel("\u25B6 입사선발자");
      label_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      label_1.setBounds(37, 199, 213, 46);
      add(label_1);

      JLabel label_2 = new JLabel("\u25B6 \uC785\uC0AC\uC790");
      label_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      label_2.setBounds(37, 452, 213, 46);
      add(label_2);

      //
      JLabel lblNewLabel_1 = new JLabel("년도/학기");
      lblNewLabel_1.setBounds(186, 66, 75, 36);
      lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      add(lblNewLabel_1);

      년도Combo = new JComboBox();
      년도Combo.setBounds(275, 63, 75, 37);
      년도Combo.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      년도Combo.setModel(new DefaultComboBoxModel(new String[] { "2020" }));
      add(년도Combo);

      학기Combo = new JComboBox();
      학기Combo.setBounds(362, 63, 75, 37);
      학기Combo.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      학기Combo.setModel(new DefaultComboBoxModel(new String[] { "1학기" }));
      add(학기Combo);

      JLabel label1;
      label_11 = new JLabel("대학구분");
      label_11.setBounds(488, 63, 75, 36);
      label_11.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      add(label_11);

      대학구분Combo = new JComboBox();
      대학구분Combo.setBounds(575, 63, 75, 37);
      대학구분Combo.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      대학구분Combo.setModel(new DefaultComboBoxModel(new String[] { "전체", "학부생", "대학원생" }));
      add(대학구분Combo);

      label_13 = new JLabel("학과");
      label_13.setBounds(734, 64, 43, 34);
      label_13.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      add(label_13);

      학과Combo = new JComboBox();
      학과Combo.setBounds(789, 62, 213, 39);
      학과Combo.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      학과Combo.setModel(new DefaultComboBoxModel(new String[] { "전체", "컴퓨터소프트웨어공학과" }));
      add(학과Combo);

      JLabel label_5 = new JLabel("\uB0A9\uBD80\uAD6C\uBD84");
      label_5.setBounds(499, 158, 64, 36);
      label_5.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      add(label_5);

      납부구분Combo = new JComboBox();
      납부구분Combo.setBounds(575, 155, 97, 39);
      납부구분Combo.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      납부구분Combo.setModel(new DefaultComboBoxModel(new String[] { "전체", "납부", "미납" }));
      add(납부구분Combo);

      JLabel label_7 = new JLabel("\uC0DD\uD65C\uAD00\uAD6C\uBD84");
      label_7.setBounds(181, 110, 86, 36);
      label_7.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      add(label_7);

      생활관구분Combo = new JComboBox();
      생활관구분Combo.setBounds(275, 110, 144, 37);
      생활관구분Combo.setModel(new DefaultComboBoxModel(
            new String[] { "전체", "푸름관4동 탑층", "푸름관4동", "푸름관3동(1년)", "푸름관3동 탑층", "푸름관3동", "푸름관2동(1년)", "푸름관2동 탑층",
                  "푸름관2동", "푸름관1동 탑층", "푸름관1동", "오름관3동", "오름관2동", "오름관1동", "신평관2인실", "신평관1인실" }));
      생활관구분Combo.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      add(생활관구분Combo);

      학번Text = new JTextField();
      학번Text.setBounds(789, 158, 213, 36);
      학번Text.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      add(학번Text);
      학번Text.setColumns(10);

      JLabel label_3 = new JLabel("\uD559\uBC88");
      label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      label_3.setBounds(734, 164, 49, 25);
      add(label_3);

      JLabel label_4 = new JLabel("\uACB0\uD575\uC9C4\uB2E8\uC11C\uC81C\uCD9C\uC5EC\uBD80 ");
      label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      label_4.setBounds(110, 158, 157, 36);
      add(label_4);

      결핵여부Combo = new JComboBox();
      결핵여부Combo.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      결핵여부Combo.setModel(new DefaultComboBoxModel(new String[] { "전체", "제출", "미제출" }));
      결핵여부Combo.setBounds(275, 157, 97, 39);
      add(결핵여부Combo);

//===========================================< 버튼 구현 >=================================================
      JButton 입사선발조회 = new JButton("\uC785\uC0AC\uC120\uBC1C\uC790 \uC870\uD68C");
      입사선발조회.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String 년도, 학기, 대학구분, 학과, 결핵여부, 납부구분, 생활관구분, 학번;

            if (e.getSource() == 입사선발조회) { // 조회 버튼 클릭
               // 입사선발자 조회를 위해 콤보박스를 선택했을 경우
               if (학번Text.getText().equals("")) {
                  try {
                     // 입사선발자 조회 메시지 전송
                     Protocol protocol = new Protocol(Protocol.TYPE7_VIEW_REQ, Protocol.T7_CD8_입사선발자조회요청);

                     // 입사선발자 조회를 위해 ~~~ 가져옴
                     년도 = 년도Combo.getSelectedItem().toString();
                     학기 = 학기Combo.getSelectedItem().toString();
                     대학구분 = 대학구분Combo.getSelectedItem().toString();
                     학과 = 학과Combo.getSelectedItem().toString();
                     결핵여부 = 결핵여부Combo.getSelectedItem().toString();
                     납부구분 = 납부구분Combo.getSelectedItem().toString();
                     생활관구분 = 생활관구분Combo.getSelectedItem().toString();

                     String selectedStudent_Data = 년도 + "/" + 학기 + "/" + 대학구분 + "/" + 학과 + "/" + 결핵여부 + "/"
                           + 납부구분 + "/" + 생활관구분;
                     
                     // 서버에게 데이터 전송
                     protocol.setString(selectedStudent_Data);
                     output.write(protocol.getPacket());
                     
                     // 입사선발자 요청 결과 수신 후 데이터 처리
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
                     slist = new Vector<Student>();
                     if (protocolType == Protocol.TYPE8_VIEW_RES && protocolCode == Protocol.T8_CD16_입사선발자조회승인) {
                        // 학생들을 %로 구분
                        String[] students = protocol.getString().split("%");
                        String group_type = null; // 대학구분
                        String department = null; // 학과명
                        String studentLevel = null; // 학년
                        String studentNumber = null; // 학번
                        String studentName = null; // 성명
                        String dormitory_type = null; // 생활관구분
                        String selection_result = null; // 선발결과
                        String tuberMCU = null; // 결핵진단서 제출여부
                        String money = null; // 입금여부

                        // 학생들의 각 데이터를 /로 구분
                        for (int i = 0; i < students.length; i++) {
                           System.out.println(students[i]);
                           String[] temp = students[i].split("/");
                           group_type = temp[0]; // 대학구분
                           department = temp[1]; // 학과명
                           studentLevel = temp[2]; // 학년
                           studentNumber = temp[3]; // 학번
                           studentName = temp[4];// 성명
                           dormitory_type = temp[5]; // 생활관구분
                           selection_result = temp[6]; // 선발결과
                           tuberMCU = temp[7]; // 결핵진단서 제출여부
                           money = temp[8]; // 입금여부

                           slist.add(new Student(group_type, department, studentLevel, studentNumber,
                                 studentName, dormitory_type, selection_result, tuberMCU, money));
                        }

                        // 화면에 결과 등록
                        Object[][] data;
                        Object[] title = { "No", "체크", "대학구분", "학과명", "학년", "학번", "성명", "생활관구분", "선발결과",
                              "결핵진단서제출일시", "입금여부" };
                        data = new Object[students.length][11];
                        for (int i = 0; i < data.length; i++) {
                           data[i][0] = (i + 1);

                           if (slist.elementAt(i).tuberMCU.equals("X")
                                 || slist.elementAt(i).money.equals("미납")) {
                              data[i][1] = false;
                           } else {
                              data[i][1] = true;
                           }

                           data[i][2] = slist.elementAt(i).group_type;
                           data[i][3] = slist.elementAt(i).department;
                           data[i][4] = slist.elementAt(i).studentLevel;
                           data[i][5] = slist.elementAt(i).studentNumber;
                           data[i][6] = slist.elementAt(i).studentName;
                           data[i][7] = slist.elementAt(i).dormitory_type;
                           data[i][8] = slist.elementAt(i).selection_result;
                           data[i][9] = slist.elementAt(i).tuberMCU;
                           data[i][10] = slist.elementAt(i).money;
                        }
                        @SuppressWarnings("serial")
                        DefaultTableCellRenderer dcr = new DefaultTableCellRenderer() {
                           public Component getTableCellRendererComponent // 셀렌더러
                           (JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
                                 int column) {
                              JCheckBox box = new JCheckBox();
                              box.setSelected(((Boolean) value).booleanValue());
                              box.setHorizontalAlignment(JLabel.CENTER);
                              return box;
                           }
                        };

                        JTable table = new JTable(data, title);
                        table.getColumn("체크").setCellRenderer(dcr);
                        JCheckBox box = new JCheckBox();
                        box.setHorizontalAlignment(JLabel.CENTER);
                        table.getColumn("체크").setCellEditor(new DefaultCellEditor(box));

                        table.setFont(new Font("맑은 고딕", Font.PLAIN, 16));

                        JScrollPane scrollPane = new JScrollPane(table);
                        scrollPane.setBounds(27, 244, 1197, 188);
                        add(scrollPane);

                     }

                     // 요청에 대해 결과를 보여주지 않고 팝업창으로 확인 불가 메시지.
                     else if (protocolType == Protocol.TYPE8_VIEW_RES
                           && protocolCode == Protocol.T8_CD17_입사선발자조회거절) {
                        JOptionPane.showMessageDialog(null, "입사선발자 조회가 불가능합니다.");
                     }
                  } catch (IOException e1) {
                     // TODO Auto-generated catch block
                     e1.printStackTrace();
                  }

               } else {
                  // 입사선발자 조회를 위해 학번을 입력했을 경우
                  try {
                     // 입사선발자 조회 메시지 전송
                     Protocol protocol = new Protocol(Protocol.TYPE7_VIEW_REQ, Protocol.T7_CD8_입사선발자조회요청);

                     // 입사선발자 조회를 위해 ~~~ 가져옴
                     학번 = 학번Text.getText();
                     JOptionPane.showMessageDialog(null, "학번을 입력하셨습니다. 콤보박스 선택과 관련없이 조회합니다.");

                     String selectedStudent_Data = 학번;

                     protocol.setString(selectedStudent_Data);
                     output.write(protocol.getPacket());

                     // 입사선발자 요청 결과 수신 후 데이터 처리
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

                     if (protocolType == Protocol.TYPE8_VIEW_RES && protocolCode == Protocol.T8_CD16_입사선발자조회승인) {
                     
                        String group_type = null; // 대학구분
                        String department = null; // 학과명
                        String studentLevel = null; // 학년
                        String studentNumber = null; // 학번
                        String studentName = null; // 성명
                        String dormitory_type = null; // 생활관구분
                        String selection_result = null; // 선발결과
                        String tuberMCU = null; // 결핵진단서 제출여부
                        String money = null; // 입금여부
                        
                        // 학생들의 데이터를 /로 구분
                        String[] temp = protocol.getString().split("/");
                        group_type = temp[0]; // 대학구분
                        department = temp[1]; // 학과명
                        studentLevel = temp[2]; // 학년
                        studentNumber = temp[3]; // 학번
                        studentName = temp[4];// 성명
                        dormitory_type = temp[5]; // 생활관구분
                        selection_result = temp[6]; // 선발결과
                        tuberMCU = temp[7]; // 결핵진단서 제출여부
                        money = temp[8]; // 입금여부

                        // 화면에 결과 등록
                        Object[][] data;
                        Object[] title = { "No", "체크", "대학구분", "학과명", "학년", "학번", "성명", "생활관구분", "선발결과",
                              "결핵진단서제출일시", "입금여부" };
                        data = new Object[1][11];
                        for (int i = 0; i < data.length; i++) {
                           data[i][0] = (i + 1);

                           if (tuberMCU.equals("X") || money.equals("미납")) {
                              data[i][1] = false;
                           } else {
                              data[i][1] = true;
                           }

                           data[i][2] = group_type;
                           data[i][3] = department;
                           data[i][4] = studentLevel;
                           data[i][5] = studentNumber;
                           data[i][6] = studentName;
                           data[i][7] = dormitory_type;
                           data[i][8] = selection_result;
                           data[i][9] = tuberMCU;
                           data[i][10] = money;
                        }
                        @SuppressWarnings("serial")
                        DefaultTableCellRenderer dcr = new DefaultTableCellRenderer() {
                           public Component getTableCellRendererComponent // 셀렌더러
                           (JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
                                 int column) {
                              JCheckBox box = new JCheckBox();
                              box.setSelected(((Boolean) value).booleanValue());
                              box.setHorizontalAlignment(JLabel.CENTER);
                              return box;
                           }
                        };

                        JTable table = new JTable(data, title);
                        table.getColumn("체크").setCellRenderer(dcr);
                        JCheckBox box = new JCheckBox();
                        box.setHorizontalAlignment(JLabel.CENTER);
                        table.getColumn("체크").setCellEditor(new DefaultCellEditor(box));

                        table.setFont(new Font("맑은 고딕", Font.PLAIN, 16));

                        JScrollPane scrollPane = new JScrollPane(table);
                        scrollPane.setBounds(27, 244, 1197, 188);
                        add(scrollPane);

                     }

                     // 요청에 대해 결과를 보여주지 않고 팝업창으로 확인 불가 메시지.
                     else if (protocolType == Protocol.TYPE8_VIEW_RES
                           && protocolCode == Protocol.T8_CD17_입사선발자조회거절) {
                        JOptionPane.showMessageDialog(null, "해당 학번의 입사선발자가 존재하지 않습니다.");
                     }
                  } catch (IOException e1) {
                     // TODO Auto-generated catch block
                     e1.printStackTrace();
                  }

               }

            }

         }
      });
      입사선발조회.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      입사선발조회.setBounds(1048, 158, 176, 37);
      add(입사선발조회);

      JButton 입사자등록 = new JButton("\uC785\uC0AC\uC790 \uB4F1\uB85D");
      입사자등록.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // 입사자등록
            String group_type = null; // 대학구분
            String department = null; // 학과명
            String studentLevel = null; // 학년
            String studentNumber = null; // 학번
            String studentName = null; // 성명
            String dormitory_type = null; // 생활관구분
            String selection_result = null; // 선발결과
            String tuberMCU = null; // 결핵진단서 제출여부
            String money = null; // 입금여부

            int add_cnt = 0;

            if (e.getSource() == 입사자등록) {

               try {
                  // 입사자 등록 메시지 전송
                  Protocol protocol = new Protocol(Protocol.TYPE9_RENEW_REQ, Protocol.T9_CD2_입사선발자를입사자로등록요청);
                  String temp = "";
                  // 입사자 등록을 위해 ~~ 가져옴
                  for (int i = 0; i < slist.size(); i++) {
                     if (!slist.elementAt(i).tuberMCU.equals("null") && !slist.elementAt(i).money.equals("미납")) {
                        temp += slist.elementAt(i).studentNumber + "/";
                        add_cnt++;
                     }
                  }

                  // 입사자 대상이 없을 경우 
                  if (add_cnt != 0) {
                     protocol.setString(temp);
                  } else {
                     JOptionPane.showMessageDialog(null, "선택항목이 없습니다.");
                     return;
                  }

                  output.write(protocol.getPacket());

                  // 입사자 등록 수신 후 데이터 처리
                  protocol = new Protocol();
                  byte[] header = protocol.getPacket();
                  input.read(header);
                  int protocolType = header[0];
                  int protocolCode = header[1];

                  if (protocolType == Protocol.TYPE10_RENEW_RES
                        && protocolCode == Protocol.T10_CD4_입사선발자를입사자로등록성공) {
                     JOptionPane.showMessageDialog(null, "입사자 등록에 성공하였습니다.");
                  } else if (protocolType == Protocol.TYPE10_RENEW_RES
                        && protocolCode == Protocol.T10_CD5_입사선발자를입사자로등록실패) {
                     JOptionPane.showMessageDialog(null, "입자자 등록이 실패하였습니다.");
                  }

               } catch (IOException e1) {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
               }

            }

         }
      });
      입사자등록.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      입사자등록.setBounds(1048, 205, 176, 36);
      add(입사자등록);

      JButton 입사자조회 = new JButton("\uC785\uC0AC\uC790 \uC870\uD68C");
      입사자조회.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // 입사자조회
            String 년도, 학기, 대학구분, 학과, 결핵여부, 납부구분, 생활관구분, 학번;

            if (e.getSource() == 입사자조회) { // 조회 버튼 클릭
               // 입사자 조회를 위해 콤보박스를 선택한 경우
               if (학번Text.getText().equals("")) {

                  try {
                     // 입사자 조회 메시지 전송
                     Protocol protocol = new Protocol(Protocol.TYPE7_VIEW_REQ, Protocol.T7_CD9_입사자조회요청);

                     // 입사자 조회를 위해 ~~~ 가져옴
                     년도 = 년도Combo.getSelectedItem().toString();
                     학기 = 학기Combo.getSelectedItem().toString();
                     대학구분 = 대학구분Combo.getSelectedItem().toString();
                     학과 = 학과Combo.getSelectedItem().toString();
                     결핵여부 = "제출";
                     납부구분 = "납부";
                     생활관구분 = 생활관구분Combo.getSelectedItem().toString();

                     JOptionPane.showMessageDialog(null,
                           "결핵진단서제출여부는 '제출', 납부구분은 '납부'로 '자동 선택'되어 입사자 목록이 보여집니다.");

                     String selectedStudent_Data = 년도 + "/" + 학기 + "/" + 대학구분 + "/" + 학과 + "/" + 결핵여부 + "/"
                           + 납부구분 + "/" + 생활관구분;

                     protocol.setString(selectedStudent_Data);
                     output.write(protocol.getPacket());

                     // 입사선발자 요청 결과 수신 후 데이터 처리
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

                     System.out.println(protocol.getString());
                     // 프로토콜에 대해 요청, 거부 확인
                     // 요청에 대해 정상결과
                     real_slist = new Vector<Student>();
                     if (protocolType == Protocol.TYPE8_VIEW_RES && protocolCode == Protocol.T8_CD18_입사자조회승인) {
                        // 학생들을 %로 구분
                        String[] students = protocol.getString().split("%");

                        String group_type = null; // 대학구분
                        String department = null; // 학과명
                        String studentLevel = null; // 학년
                        String studentNumber = null; // 학번
                        String studentName = null; // 성명
                        String dormitory_type = null; // 생활관구분
                        String room = null; // 호실번호
                        String bed = null; // 침대번호
                        String stuInfo = group_type + department + studentLevel + studentNumber + studentName
                              + dormitory_type + room + bed;

                        for (int i = 0; i < students.length; i++) {
                           // 학생 데이터를 /로 구분
                           String[] temp = students[i].split("/");
                           group_type = temp[0]; // 대학구분
                           department = temp[1]; // 학과명
                           studentLevel = temp[2]; // 학년
                           studentNumber = temp[3]; // 학번
                           studentName = temp[4];// 성명
                           dormitory_type = temp[5]; // 생활관구분
                           room = temp[6]; // 호실정보
                           bed = temp[7]; // 침대번호

                           real_slist.add(new Student(group_type, department, studentLevel, studentNumber,
                                 studentName, dormitory_type, room, bed));
                        }

                        // 화면에 결과 등록
                        Object[][] data_1;
                        Object[] title_1 = { "No", "대학구분", "학과명", "학년", "학번", "성명", "생활관구분", "호실", "침대번호" };

                        data_1 = new Object[students.length][9];
                        for (int i = 0; i < data_1.length; i++) {
                           data_1[i][0] = (i + 1);
                           data_1[i][1] = real_slist.elementAt(i).group_type;
                           data_1[i][2] = real_slist.elementAt(i).department;
                           data_1[i][3] = real_slist.elementAt(i).studentLevel;
                           data_1[i][4] = real_slist.elementAt(i).studentNumber;
                           data_1[i][5] = real_slist.elementAt(i).studentName;
                           data_1[i][6] = real_slist.elementAt(i).dormitory_type;
                           data_1[i][7] = real_slist.elementAt(i).room;
                           data_1[i][8] = real_slist.elementAt(i).bed;
                        }
                        JTable table_1 = new JTable(data_1, title_1);
                        table_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));

                        JScrollPane scrollPane_1 = new JScrollPane(table_1);
                        scrollPane_1.setBounds(27, 498, 1197, 188);
                        add(scrollPane_1);

                     }

                     // 입사자가 아닌 모든 경우(기숙사 신청 X, 불합격, 입사선발자이지만 결핵 진단서, 미입금 경우 포함)
                     // 요청에 대해 결과를 보여주지 않고 팝업창으로 확인 불가 메시지.
                     else if (protocolType == Protocol.TYPE8_VIEW_RES
                           && protocolCode == Protocol.T8_CD19_입사자조회거절) {
                        JOptionPane.showMessageDialog(null, "입사자 조회가 불가능합니다.");
                     }
                  } catch (IOException e1) {
                     // TODO Auto-generated catch block
                     e1.printStackTrace();
                  }
               } else {
                  // 입사자 조회를 위해 학번만 입력한 경우
                  try {
                     // 입사자 조회 메시지 전송
                     Protocol protocol = new Protocol(Protocol.TYPE7_VIEW_REQ, Protocol.T7_CD9_입사자조회요청);

                     // 입사선발자 조회를 위해 ~~~ 가져옴
                     학번 = 학번Text.getText();
                     JOptionPane.showMessageDialog(null, "학번을 입력하셨습니다. 콤보박스 선택과 관련없이 조회합니다.");

                     String selectedStudent_Data = 학번;

                     protocol.setString(selectedStudent_Data);
                     output.write(protocol.getPacket());

                     // 입사자 요청 결과 수신 후 데이터 처리
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
                     if (protocolType == Protocol.TYPE8_VIEW_RES && protocolCode == Protocol.T8_CD18_입사자조회승인) {
                        
                        String group_type = null; // 대학구분
                        String department = null; // 학과명
                        String studentLevel = null; // 학년
                        String studentNumber = null; // 학번
                        String studentName = null; // 성명
                        String dormitory_type = null; // 생활관구분
                        String room = null; // 호실번호
                        String bed = null; // 침대번호
                        String stuInfo = group_type + department + studentLevel + studentNumber + studentName
                              + dormitory_type + room + bed;

                        // 학셍 데이터를 /로 구분
                        String[] temp = protocol.getString().split("/");
                        group_type = temp[0]; // 대학구분
                        department = temp[1]; // 학과명
                        studentLevel = temp[2]; // 학년
                        studentNumber = temp[3]; // 학번
                        studentName = temp[4];// 성명
                        dormitory_type = temp[5]; // 생활관구분
                        room = temp[6]; // 호실정보
                        bed = temp[7]; // 침대번호

                        // 결과 등록
                        Object[][] data_1;
                        Object[] title_1 = { "No", "대학구분", "학과명", "학년", "학번", "성명", "생활관구분", "호실", "침대번호" };

                        data_1 = new Object[1][9];
                        for (int i = 0; i < data_1.length; i++) {
                           data_1[i][0] = (i + 1);
                           data_1[i][1] = group_type;
                           data_1[i][2] = department;
                           data_1[i][3] = studentLevel;
                           data_1[i][4] = studentNumber;
                           data_1[i][5] = studentName;
                           data_1[i][6] = dormitory_type;
                           data_1[i][7] = room;
                           data_1[i][8] = bed;
                        }
                        JTable table_1 = new JTable(data_1, title_1);
                        table_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));

                        JScrollPane scrollPane_1 = new JScrollPane(table_1);
                        scrollPane_1.setBounds(27, 498, 1197, 188);
                        add(scrollPane_1);

                        if (stuInfo.equals(""))
                           JOptionPane.showMessageDialog(null, "입사자가 존재하지 않습니다.");

                     } else if (protocolType == Protocol.TYPE8_VIEW_RES
                           && protocolCode == Protocol.T8_CD19_입사자조회거절) {
                        JOptionPane.showMessageDialog(null, "입사자 조회가 불가능합니다.");
                     }
                  } catch (IOException e1) {
                     // TODO Auto-generated catch block
                     e1.printStackTrace();
                  }

               }
            }
         }

      });
      입사자조회.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      입사자조회.setBounds(1055, 452, 169, 42);
      add(입사자조회);

   }
}

// 학생 리스트를 저장할 Student class
class Student {

   public String group_type = ""; // 대학구분
   public String department = ""; // 학과명
   public String studentLevel = ""; // 학년
   public String studentNumber = ""; // 학번
   public String studentName = ""; // 성명
   public String dormitory_type = ""; // 생활관구분
   public String selection_result = ""; // 선발결과
   public String tuberMCU = ""; // 결핵진단서 제출여부
   public String money = "";
   public String room = "";
   public String bed = "";

   Student() {
   }

   public Student(String group_type, String department, String studentLevel, String studentNumber, String studentName,
         String dormitory_type, String selection_result, String tuberMCU, String money) {
      this.group_type = group_type;
      this.department = department;
      this.studentLevel = studentLevel;
      this.studentName = studentName;
      this.studentNumber = studentNumber;
      this.dormitory_type = dormitory_type;
      this.selection_result = selection_result;
      this.tuberMCU = tuberMCU;
      this.money = money;
   }

   public Student(String group_type, String department, String studentLevel, String studentNumber, String studentName,
         String dormitory_type, String room, String bed) {
      this.group_type = group_type;
      this.department = department;
      this.studentLevel = studentLevel;
      this.studentName = studentName;
      this.studentNumber = studentNumber;
      this.dormitory_type = dormitory_type;
      this.room = room;
      this.bed = bed;
   }
}