package 서버;

import java.io.*;
import java.sql.*;

public class LOGIN_HANDLER {
    InputStream input;
    OutputStream output;
    Connection conn;
    int StudentNumber = -1;

    LOGIN_HANDLER(InputStream input, OutputStream output, Connection conn){
        this.input = input;
        this.output = output;
        this.conn = conn;
    }

    public int getStudentNum() {
        return StudentNumber;
    }


    void login(Protocol protocol,int LoginFailCnt)throws IOException, SQLException {
        String[] temp = protocol.getString().split("/");
        String id = temp[0];
        String pwd = temp[1];

        Statement stmt1 = conn.createStatement();
        Statement stmt2 = conn.createStatement();

        String sql = "SELECT 학번,password FROM 학생";
        ResultSet rs = stmt1.executeQuery(sql);
        sql = "SELECT id,password FROM 관리자";
        ResultSet rs2 = stmt2.executeQuery(sql);

        boolean isstudent = false;
        while(rs.next()) {
            if(id.equals(rs.getString("학번"))&&pwd.equals(rs.getString("password"))){isstudent = true;}
        }

        boolean isadmin = false;
        while(rs2.next()){
            if(id.equals(rs2.getString("id"))&&pwd.equals(rs2.getString("password"))){isadmin = true;}
        }

        if (isstudent) {
            protocol = new Protocol(Protocol.TYPE2_LOGIN_RES, Protocol.T2_CD0_STUDENT);
            output.write(protocol.getPacket());
            StudentNumber = Integer.parseInt(id);
            LoginFailCnt = 0;
        } else if (isadmin) {
            protocol = new Protocol(Protocol.TYPE2_LOGIN_RES, Protocol.T2_CD1_ADMIN);
            output.write(protocol.getPacket());
            LoginFailCnt = 0;
        } else {
            LoginFailCnt++;
            if (LoginFailCnt >= 5) {
                protocol = new Protocol(Protocol.TYPE2_LOGIN_RES, Protocol.T2_CD3_5FAIL);
                output.write(protocol.getPacket());
            } else {
                protocol = new Protocol(Protocol.TYPE2_LOGIN_RES, Protocol.T2_CD2_FAIL);
                output.write(protocol.getPacket());
            }
        }
    }
}
