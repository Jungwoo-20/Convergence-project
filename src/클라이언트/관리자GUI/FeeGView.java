package 클라이언트.관리자GUI;

import 클라이언트.Protocol;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

//급식비조회 버튼을 눌렀을 때 나오는 창
public class FeeGView extends JFrame {
   //표의 머리
    private String head[] = { "생활관명", "학기구분", "식사구분", "급식비" };
    //표의 내용물
    private String[][] body = new String[34][4];
    private DefaultTableModel model = new DefaultTableModel(head, 0);
    private JPanel contentPane;
    //급식비현황을 담을 테이블
    private JTable table;
    //table을 담을 scrollPane객체
    private JScrollPane scrollPane = new JScrollPane(table);
    private JLabel 제목 = new JLabel("급식비 조회 결과");

    public FeeGView(InputStream input, OutputStream output) {
        try {
            setVisible(true);
            setBounds(100, 10, 1250, 800);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);
            contentPane.setBackground(Color.WHITE);
            //급식비조회를 위한 프로토콜발신
            Protocol protocol = new Protocol(Protocol.TYPE7_VIEW_REQ, Protocol.T7_CD12_급식비조회);
            output.write(protocol.getPacket());

            //프로토콜수신
            protocol = new Protocol();
            byte[] header = protocol.getPacket();
            input.read(header);
            int protocolType = header[0];
            int protocolCode = header[1];
            if (protocolType == Protocol.TYPE8_VIEW_RES && protocolCode == Protocol.T8_CD24_급식비조회승인) {
                int bodylength = protocol.byteToInt(header, 2);
                if (bodylength != 0) {
                    byte[] body = new byte[bodylength];
                    input.read(body);
                    protocol.setPacket(header, body);
                }
                //급식비현황을 split으로 나누어서 value배열에 저장.
                String[] value = protocol.getString().split("/");
                //순서대로 생활관명, 학기구분, 식사구분, 급식비에 넣음.
                for(int i=0;i<value.length;i++) {
                    switch(i%4) {
                        case 0:
                            body[i/4][0] = value[i];
                            break;
                        case 1:
                            body[i/4][1] = value[i];
                            break;
                        case 2:
                            body[i/4][2] = value[i];
                            break;
                        case 3:
                            body[i/4][3] = value[i];
                            break;
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "조회할내용이없습니다.");
                dispose();
            }
            model = new DefaultTableModel(body, head);


            table = new JTable(model);
            table.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
            table.setBounds(87, 197, 1063, 450);
            table.getColumnModel().getColumn(0).setMinWidth(40);



            scrollPane.setLocation(87, 197);
            scrollPane.setSize(1063, 450);
            scrollPane.setViewportView(table);
            contentPane.add(scrollPane);


            제목.setFont(new Font("맑은 고딕", Font.PLAIN, 24));
            제목.setBounds(520, 78, 200, 45);
            contentPane.add(제목);
            setResizable(false);
            setVisible(true);
        }
        catch(IOException e) {

        }
    }
}