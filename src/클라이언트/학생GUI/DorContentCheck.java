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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

//생활관 신청조회 고지서 출력
public class DorContentCheck extends JPanel {

    //생성자
    public DorContentCheck(InputStream input, OutputStream output) {

        //Panel 설정
        setBounds(100, 10, 1250, 800);
        setBackground(Color.WHITE);
        setLayout(null);

        //생활관 입사지원 내역 테이블
        JTable 입사지원내역테이블;
        String 입사지원내역테이블열 [] = {"No", "년/학기", "지망", "생활관구분", "학기식사", "방학식사"};
        String 입사지원내역테이블행 [][]= null;
        DefaultTableModel 입사지원내역테이블모델 = new DefaultTableModel(입사지원내역테이블행,입사지원내역테이블열){
            public boolean isCellEditable(int i, int c){ return false; }
        };
        입사지원내역테이블 = new JTable(입사지원내역테이블모델);
        입사지원내역테이블.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        입사지원내역테이블.setRowHeight(30);
        입사지원내역테이블.getColumnModel().getColumn(0).setMaxWidth(60);
        JScrollPane scrollPane1 = new JScrollPane(입사지원내역테이블);
        scrollPane1.setBounds(100,125,1000,169);
        add(scrollPane1);

        //생활관 선발 결과 테이블
        JTable 선발결과테이블;
        String 선발결과_열[] = {"No", "년/학기", "지망", "생활관구분", "학기식사", "방학식사","결과"};
        String 선발결과_행 [][] = null;
        DefaultTableModel 선발결과테이블모델 = new DefaultTableModel(선발결과_행,선발결과_열){
            public boolean isCellEditable(int i, int c){ return false; }
        };
        선발결과테이블 = new JTable(선발결과테이블모델);
        선발결과테이블.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        선발결과테이블.setRowHeight(30);
        선발결과테이블.getColumnModel().getColumn(0).setMaxWidth(60);
        JScrollPane scrollPane = new JScrollPane(선발결과테이블);
        scrollPane.setBounds(100,365,1000,169);
        add(scrollPane);


        //========================================버튼===============================================================
        //신청버튼
        JButton 조회버튼 = new JButton("조회");
        조회버튼.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        조회버튼.setBounds(1003, 58, 97, 34);
        add(조회버튼);
        //조회버튼클릭시 입사지원내역, 선발결과 조회
        조회버튼.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel)입사지원내역테이블.getModel();
                model.setNumRows(0);
                DefaultTableModel model2 = (DefaultTableModel)선발결과테이블.getModel();
                model2.setNumRows(0);
                try {
                    //입사지원내역조회요청
                    Protocol protocol = new Protocol(Protocol.TYPE7_VIEW_REQ, Protocol.T7_CD3_입사지원내역조회요청);
                    output.write(protocol.getPacket());

                    //입사지원내역조회응답
                    protocol = new Protocol();
                    byte[] header = protocol.getPacket();
                    input.read(header);
                    int protocolType = header[0];
                    int protocolCode = header[1];
                    if(protocolType==Protocol.TYPE8_VIEW_RES&&protocolCode==Protocol.T8_CD6_입사지원내역조회승인){
                        int bodylength = protocol.byteToInt(header, 2);
                        if (bodylength != 0) {
                            byte[] body = new byte[bodylength];
                            input.read(body);
                            protocol.setPacket(header, body);
                        }
                        String[] temp = protocol.getString().split("/");
                        int cnt = 0;
                        if(!temp[0].equals("null")){
                            cnt++;
                            입사지원내역테이블모델.addRow(new Object[]{cnt,"1년"," ",temp[0],temp[1],temp[2]});
                        }
                        if(!temp[3].equals("null")){
                            cnt++;
                            입사지원내역테이블모델.addRow(new Object[]{cnt,"한학기","1지망",temp[3],temp[4]," "});
                        }
                        if(!temp[5].equals("null")){
                            cnt++;
                            입사지원내역테이블모델.addRow(new Object[]{cnt,"한학기","2지망",temp[5],temp[6]," "});
                        }
                        if(!temp[7].equals("null")){
                            cnt++;
                            입사지원내역테이블모델.addRow(new Object[]{cnt,"한학기","3지망",temp[7],temp[8]," "});
                        }

                        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
                        dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                        TableColumnModel tcm = 입사지원내역테이블.getColumnModel();
                        for(int i=0; i<tcm.getColumnCount(); i++){
                            tcm.getColumn(i).setCellRenderer(dtcr);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "신청내역이 없습니다.");
                    }

                    //선발결과조회요청
                    protocol = new Protocol(Protocol.TYPE7_VIEW_REQ, Protocol.T7_CD4_선발결과조회요청);
                    output.write(protocol.getPacket());

                    //선발결과조회요청응답
                    protocol = new Protocol();
                    header = protocol.getPacket();
                    input.read(header);
                    protocolType = header[0];
                    protocolCode = header[1];
                    if(protocolType==Protocol.TYPE8_VIEW_RES&&protocolCode==Protocol.T8_CD8_선발결과조회승인){
                        int bodylength = protocol.byteToInt(header, 2);
                        if (bodylength != 0) {
                            byte[] body = new byte[bodylength];
                            input.read(body);
                            protocol.setPacket(header, body);
                        }
                        String str = protocol.getString().replace("null"," ");
                        String[] temp = str.split("/");
                        if(temp[5].equals("합격")){
                            선발결과테이블모델.addRow(new Object[]{"1",temp[0],temp[1],temp[2],temp[3],temp[4],temp[5]});
                            DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
                            dtcr.setHorizontalAlignment(SwingConstants.CENTER);
                            TableColumnModel tcm = 선발결과테이블.getColumnModel();
                            for(int i=0; i<tcm.getColumnCount(); i++){
                                tcm.getColumn(i).setCellRenderer(dtcr);
                            }
                        }
                    }

                }catch (IOException a){
                }
            }
        });


        //고지서 출력 버튼 클릭시 고지서내용 조회 요청 및 TaxPayment 생성.
        JButton 고지서출력버튼 = new JButton("출력");
        고지서출력버튼.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        고지서출력버튼.setBounds(100, 600, 97, 34);
        add(고지서출력버튼);
        고지서출력버튼.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(선발결과테이블.getRowCount()==0){
                        JOptionPane.showMessageDialog(null, "합격하지 않으셨습니다.");
                    }else {
                        TaxPayment taxpayment = new TaxPayment(input, output);
                        //고지서조회요청
                        Protocol protocol = new Protocol(Protocol.TYPE7_VIEW_REQ, Protocol.T7_CD14_고지서조회요청);
                        output.write(protocol.getPacket());

                        //고지서조회응답
                        protocol = new Protocol();
                        byte[] header = protocol.getPacket();
                        input.read(header);
                        int protocolType = header[0];
                        int protocolCode = header[1];
                        if (protocolType == Protocol.TYPE8_VIEW_RES && protocolCode == Protocol.T8_CD28_고지서조회승인) {
                            int bodylength = protocol.byteToInt(header, 2);
                            if (bodylength != 0) {
                                byte[] body = new byte[bodylength];
                                input.read(body);
                                protocol.setPacket(header, body);
                            }
                            System.out.println(protocol.getString());
                            String[] temp = protocol.getString().split("/");
                            taxpayment.대학구분.setText(temp[0]);
                            taxpayment.학과명.setText(temp[1]);
                            taxpayment.학번.setText(temp[2]);
                            taxpayment.성명.setText(temp[3]);
                            taxpayment.합격생활관.setText(temp[4]);
                            taxpayment.신청금액.setText(temp[5]);
                        }
                    }
                }catch (IOException h){
                }
            }
        });

        //========================================라벨========================================================
        //라벨
        JLabel 고지서출력라벨 = new JLabel("▶ 고지서 출력");
        고지서출력라벨.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        고지서출력라벨.setBounds(100, 565, 197, 23);
        add(고지서출력라벨);

        JLabel 생활관선발결과라벨 = new JLabel("▶ 생활관 선발 결과");
        생활관선발결과라벨.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        생활관선발결과라벨.setBounds(100, 325, 194, 35);
        add(생활관선발결과라벨);

        JLabel 입사지원내역라벨 = new JLabel("▶ 생활관 입사지원 내역");
        입사지원내역라벨.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        입사지원내역라벨.setBounds(100, 92, 350, 23);
        add(입사지원내역라벨);
    }
}