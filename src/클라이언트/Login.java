package 클라이언트;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Login extends JFrame {

    static public JPanel contentPane;
    static public JTextField ID_textField;
    static public JPasswordField Pwd_passwordField;
    static public JButton Login_btnNewButton;


    public Login(Socket socket, InputStream input, OutputStream output)throws IOException{

        //창닫기버튼클릭시 서버에 종료메세지보내고 종료
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    Protocol protocol = new Protocol(Protocol.EXIT);
                    output.write(protocol.getPacket());
                    input.close();
                    output.close();
                    socket.close();
                }catch (IOException a){
                }
                System.out.println("[클라이언트정상종료]");
                System.exit(0);
            }
        });

        //Frame ,contentPane 설정
        setVisible(true);
        setTitle("금오공과대학교 생활관 관리 시스템");
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        //label
        JLabel label = new JLabel(
                "금오공과대학교 생활관 관리 시스템");
        label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        label.setBounds(50,35,350,60);
        contentPane.add(label);

        //ID_label
        JLabel ID_label = new JLabel("ID");
        ID_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        ID_label.setBounds(60,85,80,60);
        contentPane.add(ID_label);

        //Pwd_label
        JLabel Pwd_label = new JLabel("Pwd");
        Pwd_label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        Pwd_label.setBounds(60,130,80,60);
        contentPane.add(Pwd_label);

        //ID_textField
        ID_textField = new JTextField();
        ID_textField.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pwd_passwordField.requestFocus();
            }
        });
        ID_textField.setBounds(120, 100, 180, 35);
        contentPane.add(ID_textField);

        //Pwd_passwordField
        Pwd_passwordField = new JPasswordField();
        Pwd_passwordField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login_btnNewButton.doClick();
            }
        });
        Pwd_passwordField.setBounds(120,150,180,35);
        contentPane.add(Pwd_passwordField);

        //Lgoin_Button
        Login_btnNewButton = new JButton("login");
        Login_btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)  {
                try {
                    if(ID_textField.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "아이디를 입력하세요.");
                    }else if(Pwd_passwordField.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.");
                    }else {
                        //로그인 인증요청 메세지 전송
                        Protocol protocol = new Protocol(Protocol.TYPE1_LOGIN_REQ);
                        String idpwd = ID_textField.getText() + "/" + Pwd_passwordField.getText();
                        protocol.setString(idpwd);
                        output.write(protocol.getPacket());

                        //로그인 인증요청에 대한 응답
                        protocol = new Protocol();
                        byte[] header = protocol.getPacket();
                        input.read(header);
                        int protocolType = header[0];
                        int protocolCode = header[1];

                        if (protocolType == Protocol.TYPE2_LOGIN_RES && protocolCode == Protocol.T2_CD0_STUDENT) {
                            Onestop_Student secondFrame = new Onestop_Student(socket, input, output, ID_textField.getText());
                        } else if (protocolType == Protocol.TYPE2_LOGIN_RES && protocolCode == Protocol.T2_CD1_ADMIN) {
                            Onestop_Admin secondFrame = new Onestop_Admin(socket, input, output,ID_textField.getText());
                        } else if (protocolType == Protocol.TYPE2_LOGIN_RES && protocolCode == Protocol.T2_CD2_FAIL) {
                            JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호가 틀렸습니다.");
                        } else {
                            JOptionPane.showMessageDialog(null, "5회 실패로 프로그램을 종료합니다.");
                            protocol = new Protocol(Protocol.EXIT);
                            output.write(protocol.getPacket());
                            input.close();
                            output.close();
                            socket.close();
                            System.exit(0);
                        }
                    }
                }catch (IOException a){

                }
            }
        });
        Login_btnNewButton.setBounds(320,100,70,35);
        contentPane.add(Login_btnNewButton);
    }
}
