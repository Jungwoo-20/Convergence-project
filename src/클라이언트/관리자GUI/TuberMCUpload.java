package 클라이언트.관리자GUI;

import java.io.*;
import java.util.*;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import 서버.Protocol;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

//결핵진단서 제출현황조회 및 진단일시등록 
public class TuberMCUpload extends JPanel {

   public TuberMCUpload(InputStream input, OutputStream output) {
      // setVisible(true);
      setBounds(100, 10, 1250, 800);
      JTextField textField;

      // tuberMCUpload
      setBounds(100, 100, 1250, 800);
      setBackground(Color.WHITE);
      setBounds(100, 100, 1250, 800);
      setLayout(null);

      // ▶현황조회label
      JLabel label1 = new JLabel("▶ 현황 조회");
      label1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      label1.setBounds(100, 10, 197, 23);
      add(label1);

      // selectPanel
      JPanel selectPanel = new JPanel();
      selectPanel.setBorder(null);
      selectPanel.setBackground(Color.WHITE);
      selectPanel.setBounds(100, 47, 1063, 102);
      add(selectPanel);
      selectPanel.setLayout(new GridLayout(2, 8, 0, 0));

      
      //년도
      JLabel yearlabel = new JLabel("년도");
      yearlabel.setHorizontalAlignment(SwingConstants.CENTER);
      yearlabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      yearlabel.setBorder(LineBorder.createGrayLineBorder());
      selectPanel.add(yearlabel);
      JTextField yeartextfield = new JTextField();
      yeartextfield.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      selectPanel.add(yeartextfield);
      yeartextfield.setColumns(10);
      Calendar cal = new GregorianCalendar(Locale.KOREA);
      String year = Integer.toString(cal.get(Calendar.YEAR));
      yeartextfield.setText("2020");

      
      //학기
      JLabel semesterlabel = new JLabel("학기");
      semesterlabel.setHorizontalAlignment(SwingConstants.CENTER);
      semesterlabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      semesterlabel.setBorder(LineBorder.createGrayLineBorder());
      selectPanel.add(semesterlabel);

      JComboBox semesterComboBox = new JComboBox();
      semesterComboBox.addItem("1");
      semesterComboBox.addItem("2");
      selectPanel.add(semesterComboBox);

      
      //생활관구분
      JLabel dormlabel = new JLabel("생활관 구분");
      dormlabel.setHorizontalAlignment(SwingConstants.CENTER);
      dormlabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      dormlabel.setBorder(LineBorder.createGrayLineBorder());
      selectPanel.add(dormlabel);

      JComboBox dorComboBox = new JComboBox();
      dorComboBox.addItem("전체");
      dorComboBox.addItem("푸름관1동");
      dorComboBox.addItem("푸름관1동 탑층");
      dorComboBox.addItem("푸름관2동");
      dorComboBox.addItem("푸름관2동 탑층");
      dorComboBox.addItem("푸름관3동");
      dorComboBox.addItem("푸름관3동 탑층");
      dorComboBox.addItem("푸름관4동");
      dorComboBox.addItem("오름관1동");
      dorComboBox.addItem("오름관2동");
      dorComboBox.addItem("오름관3동");
      dorComboBox.addItem("신평관 1인실");
      dorComboBox.addItem("신평관 2인실");
      selectPanel.add(dorComboBox);

      
      //학번
      JLabel stdnumlabel = new JLabel("학번");
      stdnumlabel.setHorizontalAlignment(SwingConstants.CENTER);
      stdnumlabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      stdnumlabel.setBorder(LineBorder.createGrayLineBorder());
      selectPanel.add(stdnumlabel);
      JTextField stdnumtextfield = new JTextField();
      stdnumtextfield.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      selectPanel.add(stdnumtextfield);
      stdnumtextfield.setColumns(10);

      
      //제출여부
      JLabel whetherlabel = new JLabel("제출여부");
      whetherlabel.setHorizontalAlignment(SwingConstants.CENTER);
      whetherlabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      whetherlabel.setBorder(LineBorder.createGrayLineBorder());
      selectPanel.add(whetherlabel);

      JComboBox whetherCombobox = new JComboBox();
      whetherCombobox.addItem("제출");
      whetherCombobox.addItem("미제출");
      selectPanel.add(whetherCombobox);

      
      //제출기간
      JLabel termlabel = new JLabel("제출기간");
      termlabel.setHorizontalAlignment(SwingConstants.CENTER);
      termlabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      termlabel.setBorder(LineBorder.createGrayLineBorder());
      selectPanel.add(termlabel);
      JTextField termtextfield1 = new JTextField();
      termtextfield1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      selectPanel.add(termtextfield1);
      termtextfield1.setColumns(10);
      JLabel alabel = new JLabel("~");
      alabel.setHorizontalAlignment(SwingConstants.CENTER);
      alabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      alabel.setBorder(new LineBorder(new Color(0, 0, 0)));
      selectPanel.add(alabel);
      JTextField termtextfield2 = new JTextField();
      termtextfield2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      selectPanel.add(termtextfield2);
      termtextfield2.setColumns(10);

      JTextField temptf = new JTextField();
      temptf.setEditable(false);
      selectPanel.add(temptf);
      JTextField temptf1 = new JTextField();
      temptf1.setEditable(false);
      selectPanel.add(temptf1);

      // 조회Button
      JButton button1 = new JButton("조회");
      button1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      button1.setBounds(1075, 159, 88, 34);
      add(button1);

      // ▶목록label
      JLabel label2 = new JLabel("▶ 목록");
      label2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      label2.setBounds(100, 216, 197, 23);
      add(label2);

      // printTable
      String ghhead[] = { "No", "년도", "학기", "학년", "학번", "성명", "생활관구분", "서류구분", "파일명", "제출일시", "진단일시", "휴대전화번호" };
      DefaultTableModel model1 = new DefaultTableModel(ghhead, 0);
      JTable printTable = new JTable(model1);
      printTable.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      printTable.setBounds(100, 268, 1076, 450);
      printTable.getColumnModel().getColumn(0).setMinWidth(40);
      add(printTable);
      DefaultTableModel m = (DefaultTableModel) printTable.getModel();

      JScrollPane scrollPane1 = new JScrollPane(printTable);
      scrollPane1.setLocation(100, 268);
      scrollPane1.setSize(1063, 450);
      add(scrollPane1);

      // 저장
      JButton setBotton = new JButton("저장");
      setBotton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {

               
               //결핵진단서를 제출하지 않았을경우 진단일시 등록 할 수 없음
               if (whetherCombobox.getSelectedItem().toString() == "미제출") {
                  JOptionPane.showMessageDialog(null, "진단일시를 등록할 수 없습니다.\n제출여부와 파일상태를 확인하세요.");
               }

               else if (whetherCombobox.getSelectedItem().toString() == "제출") {

                  String req = "";

                  //학번과 진단일시를 String으로 만들어 서버에 전송  ==> 학번1/진단일시1%학번2/진단일시2%학번3/진단일시3%
                  for (int i = 0; i < m.getRowCount(); i++) {
                     String stdnum = (String) m.getValueAt(i, 4);
                     String diagDatetime = (String) m.getValueAt(i, 10);

                     req += stdnum + "/" + diagDatetime + "%";
                  }

                  Protocol protocol = new Protocol(Protocol.TYPE9_RENEW_REQ, Protocol.T9_CD3_진단일시등록요청);
                  protocol.setString(req);
                  output.write(protocol.getPacket());
               }

            } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
      });
      setBotton.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      setBotton.setBounds(973, 218, 88, 34);
      add(setBotton);

      // 취소
      JButton button3 = new JButton("취소");
      button3.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      button3.setBounds(1075, 218, 88, 34);
      add(button3);

      // 제출파일 다운로드
      JButton btnNewButton_2 = new JButton("\uC120\uD0DD \uD30C\uC77C \uB2E4\uC6B4\uB85C\uB4DC");
      btnNewButton_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
      btnNewButton_2.setBounds(772, 218, 189, 34);
      add(btnNewButton_2);

      btnNewButton_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               
               //결핵진단서를 제출하지 않았을경우 파일 다운로드 할 수 없음
               if (whetherCombobox.getSelectedItem().toString() == "미제출") {
                  JOptionPane.showMessageDialog(null, "파일을 다운로드 할 수 없습니다.\n제출여부와 파일상태를 확인하세요.");
               }

               //선택한 행의 학번을 서버에 보내서 파일다운로드 요청
               else if (whetherCombobox.getSelectedItem().toString() == "제출") {

                  String req = "";
                  String stdnum = "";
                  for (int i = 0; i < m.getRowCount(); i++) {
                     stdnum = (String) m.getValueAt(printTable.getSelectedRow(), 4);

                     req = stdnum;
                  }

                  Protocol protocol = new Protocol(Protocol.TYPE11_FILE_RECEIVE_REQ, Protocol.T11_CD0_GYEOLHAEK);
                  protocol.setString(req);
                  output.write(protocol.getPacket());

                  //FileDialog를 열어 저장 경로 및 파일명 지정
                  Frame f = null;
                  FileDialog dialog = new FileDialog(f, "저장", FileDialog.SAVE);
                  dialog.setDirectory("."); // .은 지금폴더
                  dialog.setFilenameFilter(new FilenameFilter() {
                     public boolean accept(File dir, String name) {
                        return name.endsWith(".jpg");
                     }
                  });
                  dialog.setFile(stdnum + ".jpg");
                  dialog.setVisible(true);

                  //경로명 파일명 설정
                  String fName = dialog.getDirectory() + dialog.getFile();

                  if (dialog.getFile() != null) {

                     boolean exit = false;

                     // while (true) {
                     // 클라이언트로부터 패킷 수신
                     protocol = new Protocol();
                     byte[] header = protocol.getPacket();
                     input.read(header);
                     int bodylength = protocol.byteToInt(header, 2);
                     if (bodylength != 0) {
                        byte[] body = new byte[bodylength];
                        input.read(body);
                        protocol.setPacket(header, body);
                     }

                     int TYPE = protocol.getType();
                     if (TYPE == 0) {
                        exit = true;
                     } else if (TYPE == Protocol.TYPE3_FILE_SEND_REQ) {
                        System.out.println(protocol.getType() + " " + protocol.getCode());
                        String[] temp = protocol.getString().split("/");
                        System.out.println(temp[0]);
                        System.out.println(temp[1]);

                        protocol = new Protocol(Protocol.TYPE4_FILE_SEND_RES, Protocol.T4_CD0_ACCEPT);
                        output.write(protocol.getPacket());

                        // image배열만들기
                        int filesize = Integer.parseInt(temp[1]);
                        byte[] image = new byte[filesize];
                        for (int i = 0; i < filesize;) {
                           protocol = new Protocol();
                           header = protocol.getPacket();
                           input.read(header);
                           byte[] body = new byte[protocol.getLength()];
                           input.read(body);
                           System.arraycopy(body, 0, image, i, protocol.getLength());
                           i += protocol.getLength();
                        }
                        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fName));
                        bos.write(image);
                        bos.close();

                     }

                     System.out.println("[성공]");

                  }
               }

            } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }

         }
      });

      button1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               m.setNumRows(0);

               Protocol protocol;
               String req = null;

               //제출기간이 둘다 입력되도록 예외처리
               if ((termtextfield1.getText().equals("") && !termtextfield2.getText().equals(""))
                     || (!termtextfield1.getText().equals("") && termtextfield2.getText().equals(""))) {
                  JOptionPane.showMessageDialog(null, "제출기간을 모두 입력하세요.");
               }

               //검색조건을 서버로 전송
               else {
                  if (stdnumtextfield.getText() == "") { // 학번 없을경우. null/년도/학기/생활관/제출여부/제출기간1/제출기간2
                     req = "null/" + yeartextfield.getText() + "/"
                           + semesterComboBox.getSelectedItem().toString() + "/"
                           + dorComboBox.getSelectedItem().toString() + "/"
                           + whetherCombobox.getSelectedItem().toString() + "/" + termtextfield1.getText()
                           + "/" + termtextfield2.getText();
                  } else { // 학번이 있는경우. 학번/년도/학기/생활관/제출여부/제출기간1/제출기간2
                     req = stdnumtextfield.getText() + "/" + yeartextfield.getText() + "/"
                           + semesterComboBox.getSelectedItem().toString() + "/"
                           + dorComboBox.getSelectedItem().toString() + "/"
                           + whetherCombobox.getSelectedItem().toString() + "/" + termtextfield1.getText()
                           + "/" + termtextfield2.getText();
                  }

                  protocol = new Protocol(Protocol.TYPE7_VIEW_REQ, Protocol.T7_CD10_결핵진단서제출현황조회요청);
                  protocol.setString(req);
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

                  
                  //받은 데이터를 테이블에 추가
                  try {
                     if (protocolType == Protocol.TYPE8_VIEW_RES
                           && protocolCode == Protocol.T8_CD20_결핵진단서제출현황조회승인) {
                        // String 결과들을 /로 나누어 배열에 저장
                        String[] temp1 = null;
                        temp1 = protocol.getString().split("%");
                        int no = 0;

                        for (int i = 0; i < temp1.length; i++) {
                           String[] temp2 = temp1[i].split("/");
                           String year = temp2[0]; // 년도
                           String semester = temp2[1]; // 학기
                           String grade = temp2[2]; // 학년
                           String stdNum = temp2[3];// 학번
                           String name = temp2[4]; // 성명
                           String dor = temp2[5]; // 생활관구분
                           String fileType = temp2[6]; // 제출서류구분
                           String fileName = temp2[7]; // 파일명
                           String sudmitDatetime = temp2[8]; // 제출일시
                           String diagDatetime = temp2[9]; // 진단일시
                           String phoneNum = temp2[10]; // 휴대전화번호

                           no++;

                           // 모델에 데이터 추가 , 1번째 출에 새로운 데이터를 추가합니다
                           m.addRow(new Object[] { no, year, semester, grade, stdNum, name, dor, fileType,
                                 fileName, sudmitDatetime, diagDatetime, phoneNum });
                           // 추가를 마치고 데이터 갱신을 알립니다.
                           printTable.updateUI();
                        }
                     }
                  } catch (NullPointerException e2) {
                     JOptionPane.showMessageDialog(null, "조회된 결과가 없습니다.");
                  }
               }
            } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
      });
   }
}