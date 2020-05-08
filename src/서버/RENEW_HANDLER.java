package 서버;

import com.mysql.jdbc.exceptions.MySQLDataException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class RENEW_HANDLER {

	InputStream input;
	OutputStream output;
	Connection conn;

	RENEW_HANDLER(InputStream input, OutputStream output, Connection conn) {
		this.input = input;
		this.output = output;
		this.conn = conn;
	}

	void CODE00(Protocol protocol, int StudentNumber) throws IOException, SQLException {// 입사신청요청
		double grade;
		String[] value = protocol.getString().split("/");
		// 재학생인지 확인.
		String query = "SELECT * FROM dormitory.학생 WHERE 학번 = " + StudentNumber + " AND 학적상태 = \"재학생\"; ";

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		// 재학생이라면
		if (rs.next()) {
			if (!rs.getString("학적상태").equals("재학생")) {
				protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD1_입사신청실패);
				output.write(protocol.getPacket());
				return;
			}

			String 일년신청생활관 = null;
			String 식비구분_1년1 = null;
			String 식비구분_1년2 = null;
			String 일지망생활관 = null;
			String 식비구분_학기1 = null;
			String 이지망생활관 = null;
			String 식비구분_학기2 = null;
			String 삼지망생활관 = null;
			String 식비구분_학기3 = null;

			// 1년, 1년1지망, 1년1지망2지망, 1년1지망2지망3지망, 1지망, 1지망2지망, 1지망2지망3지망
			// case번호
			// 3 5 7 9 2 4 6
			// case는 data를 split으로 나눈 value의 length이다.
			switch (value.length) {
			case 3:
				일년신청생활관 = value[0];
				식비구분_1년1 = value[1];
				식비구분_1년2 = value[2];
				// 남<->여 생활관 잘못 체크했는지
				if (rs.getString("성별").equals("남")) {
					if (일년신청생활관.equals("푸름관3동")) {
						protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD1_입사신청실패);
						protocol.setString("1");
						output.write(protocol.getPacket());
						return;
					}
				} else if (rs.getString("성별").equals("여")) {
					if (일년신청생활관.equals("푸름관2동")) {
						protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD1_입사신청실패);
						protocol.setString("1");
						output.write(protocol.getPacket());
						return;
					}
				}
				break;
			case 5:
				일년신청생활관 = value[0];
				식비구분_1년1 = value[1];
				식비구분_1년2 = value[2];
				일지망생활관 = value[3];
				식비구분_학기1 = value[4];
				// 남<->여 생활관 잘못 체크했는지
				if (rs.getString("성별").equals("남")) {
					if (일년신청생활관.equals("푸름관3동") || 일지망생활관.equals("오름관1동") || 일지망생활관.equals("푸름관3동")
							|| 일지망생활관.equals("푸름관3동 탑층")) {
						protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD1_입사신청실패);
						protocol.setString("1");
						output.write(protocol.getPacket());
						return;
					}
				} else if (rs.getString("성별").equals("여")) {
					if (!일년신청생활관.equals("푸름관3동")
							|| !(일지망생활관.equals("오름관1동") || 일지망생활관.equals("푸름관3동") || 일지망생활관.equals("푸름관3동 탑층"))) {
						protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD1_입사신청실패);
						protocol.setString("1");
						output.write(protocol.getPacket());
						return;
					}
				}
				break;
			case 7:
				일년신청생활관 = value[0];
				식비구분_1년1 = value[1];
				식비구분_1년2 = value[2];
				일지망생활관 = value[3];
				식비구분_학기1 = value[4];
				이지망생활관 = value[5];
				식비구분_학기2 = value[6];
				// 남<->여 생활관 잘못 체크했는지
				if (rs.getString("성별").equals("남")) {
					if (일년신청생활관.equals("푸름관3동") || 일지망생활관.equals("오름관1동") || 일지망생활관.equals("푸름관3동")
							|| 일지망생활관.equals("푸름관3동 탑층") || 이지망생활관.equals("오름관1동") || 이지망생활관.equals("푸름관3동")
							|| 이지망생활관.equals("푸름관3동 탑층")) {
						protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD1_입사신청실패);
						protocol.setString("1");
						output.write(protocol.getPacket());
						return;
					}
				} else if (rs.getString("성별").equals("여")) {
					if (!일년신청생활관.equals("푸름관3동")
							|| !(일지망생활관.equals("오름관1동") || 일지망생활관.equals("푸름관3동") || 일지망생활관.equals("푸름관3동 탑층"))
							|| !(이지망생활관.equals("오름관1동") || 이지망생활관.equals("푸름관3동") || 이지망생활관.equals("푸름관3동 탑층"))) {
						protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD1_입사신청실패);
						protocol.setString("1");
						output.write(protocol.getPacket());
						return;
					}
				}
				break;
			case 9:
				일년신청생활관 = value[0];
				식비구분_1년1 = value[1];
				식비구분_1년2 = value[2];
				일지망생활관 = value[3];
				식비구분_학기1 = value[4];
				이지망생활관 = value[5];
				식비구분_학기2 = value[6];
				삼지망생활관 = value[7];
				식비구분_학기3 = value[8];
				// 남<->여 생활관 잘못 체크했는지
				if (rs.getString("성별").equals("남")) {
					if (일년신청생활관.equals("푸름관3동") || 일지망생활관.equals("오름관1동") || 일지망생활관.equals("푸름관3동")
							|| 일지망생활관.equals("푸름관3동 탑층") || 이지망생활관.equals("오름관1동") || 이지망생활관.equals("푸름관3동")
							|| 이지망생활관.equals("푸름관3동 탑층") || 삼지망생활관.equals("오름관1동") || 삼지망생활관.equals("푸름관3동")
							|| 삼지망생활관.equals("푸름관3동 탑층")) {
						protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD1_입사신청실패);
						protocol.setString("1");
						output.write(protocol.getPacket());
						return;
					}
				} else if (rs.getString("성별").equals("여")) {
					if (!일년신청생활관.equals("푸름관3동")
							|| !(일지망생활관.equals("오름관1동") || 일지망생활관.equals("푸름관3동") || 일지망생활관.equals("푸름관3동 탑층"))
							|| !(이지망생활관.equals("오름관1동") || 이지망생활관.equals("푸름관3동") || 이지망생활관.equals("푸름관3동 탑층"))
							|| !(삼지망생활관.equals("오름관1동") || 삼지망생활관.equals("푸름관3동") || 삼지망생활관.equals("푸름관3동 탑층"))) {
						protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD1_입사신청실패);
						protocol.setString("1");
						output.write(protocol.getPacket());
						return;
					}
				}
				break;
			case 2:
				일지망생활관 = value[0];
				식비구분_학기1 = value[1];
				// 남<->여 생활관 잘못 체크했는지
				if (rs.getString("성별").equals("남")) {
					if (일지망생활관.equals("오름관1동") || 일지망생활관.equals("푸름관3동") || 일지망생활관.equals("푸름관3동 탑층")) {
						protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD1_입사신청실패);
						protocol.setString("1");
						output.write(protocol.getPacket());
						return;
					}
				} else if (rs.getString("성별").equals("여")) {
					if (!(일지망생활관.equals("오름관1동") || 일지망생활관.equals("푸름관3동") || 일지망생활관.equals("푸름관3동 탑층"))) {
						protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD1_입사신청실패);
						protocol.setString("1");
						output.write(protocol.getPacket());
						return;
					}
				}
				break;
			case 4:
				일지망생활관 = value[0];
				식비구분_학기1 = value[1];
				이지망생활관 = value[2];
				식비구분_학기2 = value[3];
				// 남<->여 생활관 잘못 체크했는지
				if (rs.getString("성별").equals("남")) {
					if (일지망생활관.equals("오름관1동") || 일지망생활관.equals("푸름관3동") || 일지망생활관.equals("푸름관3동 탑층")
							|| 이지망생활관.equals("오름관1동") || 이지망생활관.equals("푸름관3동") || 이지망생활관.equals("푸름관3동 탑층")) {
						protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD1_입사신청실패);
						protocol.setString("1");
						output.write(protocol.getPacket());
						return;
					}
				} else if (rs.getString("성별").equals("여")) {
					if (!(일지망생활관.equals("오름관1동") || 일지망생활관.equals("푸름관3동") || 일지망생활관.equals("푸름관3동 탑층"))
							|| !(이지망생활관.equals("오름관1동") || 이지망생활관.equals("푸름관3동") || 이지망생활관.equals("푸름관3동 탑층"))) {
						protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD1_입사신청실패);
						protocol.setString("1");
						output.write(protocol.getPacket());
						return;
					}
				}
				break;
			case 6:
				일지망생활관 = value[0];
				식비구분_학기1 = value[1];
				이지망생활관 = value[2];
				식비구분_학기2 = value[3];
				삼지망생활관 = value[4];
				식비구분_학기3 = value[5];
				// 남<->여 생활관 잘못 체크했는지
				if (rs.getString("성별").equals("남")) {
					if (일지망생활관.equals("오름관1동") || 일지망생활관.equals("푸름관3동") || 일지망생활관.equals("푸름관3동 탑층")
							|| 이지망생활관.equals("오름관1동") || 이지망생활관.equals("푸름관3동") || 이지망생활관.equals("푸름관3동 탑층")
							|| 삼지망생활관.equals("오름관1동") || 삼지망생활관.equals("푸름관3동") || 삼지망생활관.equals("푸름관3동 탑층")) {
						protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD1_입사신청실패);
						protocol.setString("1");
						output.write(protocol.getPacket());
						return;
					}
				} else if (rs.getString("성별").equals("여")) {
					if (!(일지망생활관.equals("오름관1동") || 일지망생활관.equals("푸름관3동") || 일지망생활관.equals("푸름관3동 탑층"))
							|| !(이지망생활관.equals("오름관1동") || 이지망생활관.equals("푸름관3동") || 이지망생활관.equals("푸름관3동 탑층"))
							|| !(삼지망생활관.equals("오름관1동") || 삼지망생활관.equals("푸름관3동") || 삼지망생활관.equals("푸름관3동 탑층"))) {
						protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD1_입사신청실패);
						protocol.setString("1");
						output.write(protocol.getPacket());
						return;
					}
				}
				break;
			}

			// insert
			Calendar cal = Calendar.getInstance();
			String 년도 = "2020";
			String 학기 = "1";

			// 쿼리문에서 값 받아옴
			String 대학구분 = rs.getString("대학구분");
			String 학번 = rs.getString("학번");
			String 성명 = rs.getString("성명");
			String 학과명 = rs.getString("학과명");
			String 학년 = rs.getString("학년");
			String 주야구분 = rs.getString("주야구분");
			String 학생우편번호 = rs.getString("학생우편번호");
			String 학생주소 = rs.getString("학생주소");
			String 학생전화번호 = rs.getString("학생전화번호");
			String 보호자성명 = rs.getString("보호자성명");
			String 보호자우편번호 = rs.getString("보호자우편번호");
			String 보호자주소 = rs.getString("보호자주소");
			String 보호자전화번호 = rs.getString("보호자전화번호");
			String 보호자와의관계 = rs.getString("보호자와의관계");
			String 신청일 = 년도 + "-" + "1" + "-" + "1";
			// 성적받아옴
			grade = SetGrade(StudentNumber);
			String 성적 = Double.toString(grade);

			String 내용물 = "";
			// 내용물은 query문안에 들어갈 values, case는 위의 이성생활관신청확인과 같음
			// 같은 학생이 다른 케이스를 신청할 경우를 대비해 자신이 선택하지 않은 식비구분과 생활관은 null로 update함.
			switch (value.length) {
			case 3:
				내용물 = 년도 + "," + 학기 + ",\"" + 대학구분 + "\"," + 학번 + ",\"" + 성명 + "\",\"" + 학과명 + "\"," + 학년 + "," + 주야구분
						+ "," + 학생우편번호 + ",\"" + 학생주소 + "\",\"" + 학생전화번호 + "\",\"" + 보호자성명 + "\"," + 보호자우편번호 + ",\""
						+ 보호자주소 + "\",\"" + 보호자전화번호 + "\",\"" + 보호자와의관계 + "\"," + 성적 + ",\"" + 일년신청생활관 + "\"," + 일지망생활관
						+ "," + 이지망생활관 + "," + 삼지망생활관 + ",\"" + 식비구분_1년1 + "\",\"" + 식비구분_1년2 + "\"," + 식비구분_학기1 + ","
						+ 식비구분_학기2 + "," + 식비구분_학기3 + ",\"" + 신청일 + "\"";
				query = "INSERT INTO 생활관신청내역(년도, 학기, 대학구분, 학번, 성명, 학과명, 학년, 주야구분, 학생우편번호, 학생주소, "
						+ "학생전화번호, 보호자성명, 보호자우편번호, 보호자주소, 보호자전화번호, 보호자와의관계, 성적, "
						+ "1년신청생활관, 1지망생활관, 2지망생활관, 3지망생활관, 식비구분_1년1, 식비구분_1년2, 식비구분_학기1, 식비구분_학기2, 식비구분_학기3,"
						+ "신청일) VALUES (" + 내용물 + ")"
						+ " ON DUPLICATE KEY UPDATE 1년신청생활관 = VALUES(1년신청생활관), 식비구분_1년1 = VALUES(식비구분_1년1), 식비구분_1년2 = VALUES(식비구분_1년2),  1지망생활관 = VALUES(1지망생활관), 식비구분_학기1 = VALUES(식비구분_학기1),  2지망생활관 = VALUES(2지망생활관),  식비구분_학기2 = VALUES(식비구분_학기2),  3지망생활관 = VALUES(3지망생활관),  식비구분_학기3 = VALUES(식비구분_학기3);";
				break;
			case 5:
				내용물 = 년도 + "," + 학기 + ",\"" + 대학구분 + "\"," + 학번 + ",\"" + 성명 + "\",\"" + 학과명 + "\"," + 학년 + "," + 주야구분
						+ "," + 학생우편번호 + ",\"" + 학생주소 + "\",\"" + 학생전화번호 + "\",\"" + 보호자성명 + "\"," + 보호자우편번호 + ",\""
						+ 보호자주소 + "\",\"" + 보호자전화번호 + "\",\"" + 보호자와의관계 + "\"," + 성적 + ",\"" + 일년신청생활관 + "\",\""
						+ 일지망생활관 + "\"," + 이지망생활관 + "," + 삼지망생활관 + ",\"" + 식비구분_1년1 + "\",\"" + 식비구분_1년2 + "\",\""
						+ 식비구분_학기1 + "\"," + 식비구분_학기2 + "," + 식비구분_학기3 + ",\"" + 신청일 + "\"";
				query = "INSERT INTO 생활관신청내역(년도, 학기, 대학구분, 학번, 성명, 학과명, 학년, 주야구분, 학생우편번호, 학생주소, "
						+ "학생전화번호, 보호자성명, 보호자우편번호, 보호자주소, 보호자전화번호, 보호자와의관계, 성적, "
						+ "1년신청생활관, 1지망생활관, 2지망생활관, 3지망생활관, 식비구분_1년1, 식비구분_1년2, 식비구분_학기1, 식비구분_학기2, 식비구분_학기3,"
						+ "신청일) VALUES (" + 내용물 + ")"
						+ " ON DUPLICATE KEY UPDATE 1년신청생활관 = VALUES(1년신청생활관), 식비구분_1년1 = VALUES(식비구분_1년1), 식비구분_1년2 = VALUES(식비구분_1년2),  1지망생활관 = VALUES(1지망생활관), 식비구분_학기1 = VALUES(식비구분_학기1),  2지망생활관 = VALUES(2지망생활관),  식비구분_학기2 = VALUES(식비구분_학기2),  3지망생활관 = VALUES(3지망생활관),  식비구분_학기3 = VALUES(식비구분_학기3);";
				break;
			case 7:
				내용물 = 년도 + "," + 학기 + ",\"" + 대학구분 + "\"," + 학번 + ",\"" + 성명 + "\",\"" + 학과명 + "\"," + 학년 + "," + 주야구분
						+ "," + 학생우편번호 + ",\"" + 학생주소 + "\",\"" + 학생전화번호 + "\",\"" + 보호자성명 + "\"," + 보호자우편번호 + ",\""
						+ 보호자주소 + "\",\"" + 보호자전화번호 + "\",\"" + 보호자와의관계 + "\"," + 성적 + ",\"" + 일년신청생활관 + "\",\""
						+ 일지망생활관 + "\",\"" + 이지망생활관 + "\"," + 삼지망생활관 + ",\"" + 식비구분_1년1 + "\",\"" + 식비구분_1년2 + "\",\""
						+ 식비구분_학기1 + "\",\"" + 식비구분_학기2 + "\"," + 식비구분_학기3 + ",\"" + 신청일 + "\"";
				query = "INSERT INTO 생활관신청내역(년도, 학기, 대학구분, 학번, 성명, 학과명, 학년, 주야구분, 학생우편번호, 학생주소, "
						+ "학생전화번호, 보호자성명, 보호자우편번호, 보호자주소, 보호자전화번호, 보호자와의관계, 성적, "
						+ "1년신청생활관, 1지망생활관, 2지망생활관, 3지망생활관, 식비구분_1년1, 식비구분_1년2, 식비구분_학기1, 식비구분_학기2, 식비구분_학기3,"
						+ "신청일) VALUES (" + 내용물 + ")"
						+ " ON DUPLICATE KEY UPDATE 1년신청생활관 = VALUES(1년신청생활관), 식비구분_1년1 = VALUES(식비구분_1년1), 식비구분_1년2 = VALUES(식비구분_1년2),  1지망생활관 = VALUES(1지망생활관), 식비구분_학기1 = VALUES(식비구분_학기1),  2지망생활관 = VALUES(2지망생활관),  식비구분_학기2 = VALUES(식비구분_학기2),  3지망생활관 = VALUES(3지망생활관),  식비구분_학기3 = VALUES(식비구분_학기3);";
				break;
			case 9:
				내용물 = 년도 + "," + 학기 + ",\"" + 대학구분 + "\"," + 학번 + ",\"" + 성명 + "\",\"" + 학과명 + "\"," + 학년 + "," + 주야구분
						+ "," + 학생우편번호 + ",\"" + 학생주소 + "\",\"" + 학생전화번호 + "\",\"" + 보호자성명 + "\"," + 보호자우편번호 + ",\""
						+ 보호자주소 + "\",\"" + 보호자전화번호 + "\",\"" + 보호자와의관계 + "\"," + 성적 + ",\"" + 일년신청생활관 + "\",\""
						+ 일지망생활관 + "\",\"" + 이지망생활관 + "\",\"" + 삼지망생활관 + "\",\"" + 식비구분_1년1 + "\",\"" + 식비구분_1년2
						+ "\",\"" + 식비구분_학기1 + "\",\"" + 식비구분_학기2 + "\",\"" + 식비구분_학기3 + "\",\"" + 신청일 + "\"";
				query = "INSERT INTO 생활관신청내역(년도, 학기, 대학구분, 학번, 성명, 학과명, 학년, 주야구분, 학생우편번호, 학생주소, "
						+ "학생전화번호, 보호자성명, 보호자우편번호, 보호자주소, 보호자전화번호, 보호자와의관계, 성적, "
						+ "1년신청생활관, 1지망생활관, 2지망생활관, 3지망생활관, 식비구분_1년1, 식비구분_1년2, 식비구분_학기1, 식비구분_학기2, 식비구분_학기3,"
						+ "신청일) VALUES (" + 내용물 + ")"
						+ " ON DUPLICATE KEY UPDATE 1년신청생활관 = VALUES(1년신청생활관), 식비구분_1년1 = VALUES(식비구분_1년1), 식비구분_1년2 = VALUES(식비구분_1년2),  1지망생활관 = VALUES(1지망생활관), 식비구분_학기1 = VALUES(식비구분_학기1),  2지망생활관 = VALUES(2지망생활관),  식비구분_학기2 = VALUES(식비구분_학기2),  3지망생활관 = VALUES(3지망생활관),  식비구분_학기3 = VALUES(식비구분_학기3);";
				break;
			case 2:
				내용물 = 년도 + "," + 학기 + ",\"" + 대학구분 + "\"," + 학번 + ",\"" + 성명 + "\",\"" + 학과명 + "\"," + 학년 + "," + 주야구분
						+ "," + 학생우편번호 + ",\"" + 학생주소 + "\",\"" + 학생전화번호 + "\",\"" + 보호자성명 + "\"," + 보호자우편번호 + ",\""
						+ 보호자주소 + "\",\"" + 보호자전화번호 + "\",\"" + 보호자와의관계 + "\"," + 성적 + "," + 일년신청생활관 + ",\"" + 일지망생활관
						+ "\"," + 이지망생활관 + "," + 삼지망생활관 + "," + 식비구분_1년1 + "," + 식비구분_1년2 + ",\"" + 식비구분_학기1 + "\","
						+ 식비구분_학기2 + "," + 식비구분_학기3 + ",\"" + 신청일 + "\"";
				query = "INSERT INTO 생활관신청내역(년도, 학기, 대학구분, 학번, 성명, 학과명, 학년, 주야구분, 학생우편번호, 학생주소, "
						+ "학생전화번호, 보호자성명, 보호자우편번호, 보호자주소, 보호자전화번호, 보호자와의관계, 성적, "
						+ "1년신청생활관, 1지망생활관, 2지망생활관, 3지망생활관, 식비구분_1년1, 식비구분_1년2, 식비구분_학기1, 식비구분_학기2, 식비구분_학기3,"
						+ "신청일) VALUES (" + 내용물 + ")"
						+ " ON DUPLICATE KEY UPDATE 1년신청생활관 = VALUES(1년신청생활관), 식비구분_1년1 = VALUES(식비구분_1년1), 식비구분_1년2 = VALUES(식비구분_1년2),  1지망생활관 = VALUES(1지망생활관), 식비구분_학기1 = VALUES(식비구분_학기1),  2지망생활관 = VALUES(2지망생활관),  식비구분_학기2 = VALUES(식비구분_학기2),  3지망생활관 = VALUES(3지망생활관),  식비구분_학기3 = VALUES(식비구분_학기3);";
				break;
			case 4:
				내용물 = 년도 + "," + 학기 + ",\"" + 대학구분 + "\"," + 학번 + ",\"" + 성명 + "\",\"" + 학과명 + "\"," + 학년 + "," + 주야구분
						+ "," + 학생우편번호 + ",\"" + 학생주소 + "\",\"" + 학생전화번호 + "\",\"" + 보호자성명 + "\"," + 보호자우편번호 + ",\""
						+ 보호자주소 + "\",\"" + 보호자전화번호 + "\",\"" + 보호자와의관계 + "\"," + 성적 + "," + 일년신청생활관 + ",\"" + 일지망생활관
						+ "\",\"" + 이지망생활관 + "\"," + 삼지망생활관 + "," + 식비구분_1년1 + "," + 식비구분_1년2 + ",\"" + 식비구분_학기1
						+ "\",\"" + 식비구분_학기2 + "\"," + 식비구분_학기3 + ",\"" + 신청일 + "\"";
				query = "INSERT INTO 생활관신청내역(년도, 학기, 대학구분, 학번, 성명, 학과명, 학년, 주야구분, 학생우편번호, 학생주소, "
						+ "학생전화번호, 보호자성명, 보호자우편번호, 보호자주소, 보호자전화번호, 보호자와의관계, 성적, "
						+ "1년신청생활관, 1지망생활관, 2지망생활관, 3지망생활관, 식비구분_1년1, 식비구분_1년2, 식비구분_학기1, 식비구분_학기2, 식비구분_학기3,"
						+ "신청일) VALUES (" + 내용물 + ")"
						+ " ON DUPLICATE KEY UPDATE 1년신청생활관 = VALUES(1년신청생활관), 식비구분_1년1 = VALUES(식비구분_1년1), 식비구분_1년2 = VALUES(식비구분_1년2),  1지망생활관 = VALUES(1지망생활관), 식비구분_학기1 = VALUES(식비구분_학기1),  2지망생활관 = VALUES(2지망생활관),  식비구분_학기2 = VALUES(식비구분_학기2),  3지망생활관 = VALUES(3지망생활관),  식비구분_학기3 = VALUES(식비구분_학기3);";
				break;
			case 6:
				내용물 = 년도 + "," + 학기 + ",\"" + 대학구분 + "\"," + 학번 + ",\"" + 성명 + "\",\"" + 학과명 + "\"," + 학년 + "," + 주야구분
						+ "," + 학생우편번호 + ",\"" + 학생주소 + "\",\"" + 학생전화번호 + "\",\"" + 보호자성명 + "\"," + 보호자우편번호 + ",\""
						+ 보호자주소 + "\",\"" + 보호자전화번호 + "\",\"" + 보호자와의관계 + "\"," + 성적 + "," + 일년신청생활관 + ",\"" + 일지망생활관
						+ "\",\"" + 이지망생활관 + "\",\"" + 삼지망생활관 + "\"," + 식비구분_1년1 + "," + 식비구분_1년2 + ",\"" + 식비구분_학기1
						+ "\",\"" + 식비구분_학기2 + "\",\"" + 식비구분_학기3 + "\",\"" + 신청일 + "\"";
				query = "INSERT INTO 생활관신청내역(년도, 학기, 대학구분, 학번, 성명, 학과명, 학년, 주야구분, 학생우편번호, 학생주소, "
						+ "학생전화번호, 보호자성명, 보호자우편번호, 보호자주소, 보호자전화번호, 보호자와의관계, 성적, "
						+ "1년신청생활관, 1지망생활관, 2지망생활관, 3지망생활관, 식비구분_1년1, 식비구분_1년2, 식비구분_학기1, 식비구분_학기2, 식비구분_학기3,"
						+ "신청일) VALUES (" + 내용물 + ")"
						+ " ON DUPLICATE KEY UPDATE 1년신청생활관 = VALUES(1년신청생활관), 식비구분_1년1 = VALUES(식비구분_1년1), 식비구분_1년2 = VALUES(식비구분_1년2),  1지망생활관 = VALUES(1지망생활관), 식비구분_학기1 = VALUES(식비구분_학기1),  2지망생활관 = VALUES(2지망생활관),  식비구분_학기2 = VALUES(식비구분_학기2),  3지망생활관 = VALUES(3지망생활관),  식비구분_학기3 = VALUES(식비구분_학기3);";
				break;
			}

			stmt.executeUpdate(query);
			protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD0_입사신청성공);
			output.write(protocol.getPacket());
		} else {
			protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD1_입사신청실패);
			output.write(protocol.getPacket());
		}

	}

	// 입사신청자 합격
	void CODE01(Protocol protocol) throws IOException, SQLException {
		Vector<Student2> slist = new Vector<Student2>();
		String[] result = protocol.getString().split("/"); // 클라이언트로 부터 결과 받음
		String sql = "select * from 생활관별_호실현황";
		String year = null;
		String semester = null;
		String university = null;
		String studentNumber = null;
		String wish = null;
		String dormitory = null;
		String meal = null;
		Statement stmt = (Statement) conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql); // 생활관별로 합격인원을 파악하여 들어갈수 있는지 확인
		int number = 0; // 결과 값을 보내주고 다시 초기화 해야함
		int rs2 = 0;
		int cnt = 0;
		for (int i = 0; i < result.length;) {
			year = result[i];
			i++;
			semester = result[i];
			i++;
			university = result[i];
			i++;
			studentNumber = result[i];
			i++;
			wish = result[i];
			i++;
			dormitory = result[i];
			i++;
			meal = result[i];
			i++;
			// 학생정보를 리스트에 저장
			slist.add(new Student2(year, semester, university, studentNumber, wish, dormitory, meal));
			while (rs.next()) {
				if (rs.getString("생활관").equals(dormitory)) { // 선택한 생활관명과 DB생활관 명이 같은 경우의 인원들을 체크해준다
					number += rs.getInt("인원");
				}
			}
		}
		// 리스트만큼 생활관 합격을 시킨다.
		for (int i = 0; i < slist.size(); i++) {
			if (slist.elementAt(i).wish.equals("1년")) {// 1년입사인경우
				String sql3 = "update 생활관신청내역 set 선발결과 = '합격', 납부여부 = '미납' , 입사기간 = '1년', 합격지망구분 = '"
						+ slist.elementAt(i).wish + "', 합격생활관 = '" + slist.elementAt(i).dormitory + "', 합격생활관식사1 = '"
						+ slist.elementAt(i).meal + "' where (년도 = '" + slist.elementAt(i).year + "') and (학기 = '"
						+ slist.elementAt(i).semester + "') and (대학구분 = '" + slist.elementAt(i).university
						+ "') and (학번 = '" + slist.elementAt(i).studentNumber + "')";
				System.out.println(sql3);
				rs2 = stmt.executeUpdate(sql3);
			} else {
				String sql3 = "update 생활관신청내역 set 선발결과 = '합격', 납부여부 = '미납', 입사기간 = '한학기', 합격지망구분 = '"
						+ slist.elementAt(i).wish + "', 합격생활관 = '" + slist.elementAt(i).dormitory + "', 합격생활관식사1 = '"
						+ slist.elementAt(i).meal + "' where (년도 = '" + slist.elementAt(i).year + "') and (학기 = '"
						+ slist.elementAt(i).semester + "') and (대학구분 = '" + slist.elementAt(i).university
						+ "') and (학번 = '" + slist.elementAt(i).studentNumber + "')";
				System.out.println(sql3);
				rs2 = stmt.executeUpdate(sql3);
			}
		}
		protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD2_입사신청자를입사선발자로등록성공);
		output.write(protocol.getPacket());
	}

	void CODE02(Protocol protocol) throws IOException, SQLException {
		String[] stdnumlist = protocol.getString().split("/");
		// 학생수 만큼 반복
		for (int i = 0; i < stdnumlist.length; i++) {
			// 학생정보가져오기
			Statement stmt2 = conn.createStatement();
			ResultSet std = stmt2
					.executeQuery("SELECT 생활관신청내역.대학구분, 생활관신청내역.학번, 학생.성별, 생활관신청내역.학년, 생활관신청내역.입사기간, 생활관신청내역.합격생활관"
							+ " FROM 생활관신청내역, 학생" + " WHERE 생활관신청내역.학번=학생.학번 and 생활관신청내역.학번 = '" + stdnumlist[i]
							+ "' and " + "NOT EXISTS ( SELECT 학번 FROM 호실배정내역 WHERE 생활관신청내역.학번 = 호실배정내역.학번 )");
			// 해당학생의 합격생활관 호실정보 가져오기
			if (std.next()) {
				String hsn = std.getString("합격생활관");
				System.out.println(hsn);
				if (std.getString("입사기간").equals("1년")) {
					hsn += "(1년)";
				}
				Statement stmt1 = conn.createStatement();
				ResultSet hs = stmt1.executeQuery("SELECT 호실,인원,현재인원 FROM 생활관별_호실현황 WHERE 생활관별_호실현황.생활관='" + hsn + "'");
				// 학생이랑 호실이 있다면 생활관배정하기.
				if (hs.next()) {
					// 비어있는호실찾기
					int HS = hs.getInt("호실");
					int to = hs.getInt("인원");
					int cur = hs.getInt("현재인원");
					while (to == cur) {
						hs.next();
						HS = hs.getInt("호실");
						to = hs.getInt("인원");
						cur = hs.getInt("현재인원");
					}
					char bed = (char) (cur + 'A');

					Statement stmt3 = conn.createStatement();
					int rs = stmt3.executeUpdate("INSERT INTO 호실배정내역 (년도, 학기, 대학구분, 학번, 성별, 학년, 입사기간, 생활관구분, 호실, 침대번호) "
							+ "VALUES ('2020','1','" + std.getString("대학구분") + "','" + std.getString("학번") + "','"
							+ std.getString("성별") + "','" + std.getString("학년") + "','" + std.getString("입사기간") + "','"
							+ hsn + "','" + HS + "','" + bed + "')");
					if (rs == 1) {
						System.out.println("호실배정성공");
						cur++;
						// 현재인원 업데이트
						int rrs = stmt3.executeUpdate("UPDATE 생활관별_호실현황 SET 현재인원 = '" + cur + "' WHERE 생활관='" + hsn
								+ "' and 호실='" + HS + "'");
						if (rrs == 1) {
							System.out.println("OK");
						} else {
							System.out.println("NO");
						}
					} else {
						System.out.println("호실배정실패");
						protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD5_입사선발자를입사자로등록실패);
						output.write(protocol.getPacket());
						return;
					}
				}
			} else {
				// 학생정보가없음
				System.out.println("이미배정된학생");
			}
		}

		protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD4_입사선발자를입사자로등록성공);
		output.write(protocol.getPacket());
		return;
	}

	// 결핵진단서 진단일시 등록
	void CODE03(Protocol protocol) throws IOException, SQLException { // T9_CD3_진단일시등록요청

		Statement stmt = conn.createStatement();
		System.out.println(protocol.getString());

		String[] temp1 = protocol.getString().split("%");
		int rs = 0;

		// 해당학번의 진단일시를 전송된 값으로 등록
		for (int i = 0; i < temp1.length; i++) {
			rs = 0;

			String[] temp2 = temp1[i].split("/");
			String stdnum = temp2[0];
			String diagDatetime = temp2[1];

			String sql = "UPDATE 결핵진단서 SET 진단일시='" + diagDatetime + "' WHERE 결핵진단서.학번=" + stdnum + ";\n";
			rs = stmt.executeUpdate(sql);
		}
		stmt.close();

		if (rs == 1) {
			protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD6_진단일시등록성공);
			output.write(protocol.getPacket());
		} else {
			protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD7_진단일시등록실패);
			output.write(protocol.getPacket());
		}

	}

	// 입사신청자 취소
	void CODE04(Protocol protocol) throws IOException, SQLException {
		Vector<Student2> slist = new Vector<Student2>();
		String[] result = protocol.getString().split("/"); // 클라이언트로 부터 결과 받음
		String sql = "select * from 생활관신청내역";
		String year = null;
		String semester = null;
		String university = null;
		String studentNumber = null;
		String wish = null;
		String dormitory = null;
		String meal = null;
		Statement stmt = (Statement) conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql); // 생활관별로 합격인원을 파악하여 들어갈수 있는지 확인
		int rs1 = 0;
		for (int i = 0; i < result.length;) {
			year = result[i];
			i++;
			semester = result[i];
			i++;
			university = result[i];
			i++;
			studentNumber = result[i];
			i++;
			wish = result[i];
			i++;
			dormitory = result[i];
			i++;
			meal = result[i];
			i++;
			// 리스트에 저장
			slist.add(new Student2(year, semester, university, studentNumber, wish, dormitory, meal));
		}
		// 리스트 사이즈 만큼 해당 학생들을 생활관 신청 취소
		for (int j = 0; j < slist.size(); j++) {
			if (wish.equals("1년")) {
				rs1 = stmt.executeUpdate("update 생활관신청내역 set 1년신청생활관 = null , 식비구분_1년1 = null where (년도 = '"
						+ slist.elementAt(j).year + "') and (학기 = '" + slist.elementAt(j).semester + "') and (대학구분 = '"
						+ slist.elementAt(j).university + "') and (학번 = '" + slist.elementAt(j).studentNumber + "')");

			} else if (wish.equals("1지망")) {
				rs1 = stmt.executeUpdate("update 생활관신청내역 set 1지망생활관 = null , 식비구분_학기1 = null where (년도 = '"
						+ slist.elementAt(j).year + "') and (학기 = '" + slist.elementAt(j).semester + "') and (대학구분 = '"
						+ slist.elementAt(j).university + "') and (학번 = '" + slist.elementAt(j).studentNumber + "')");
			} else if (wish.equals("2지망")) {
				rs1 = stmt.executeUpdate("update 생활관신청내역 set 2지망생활관 = null , 식비구분_학기2 = null where (년도 = '"
						+ slist.elementAt(j).year + "') and (학기 = '" + slist.elementAt(j).semester + "') and (대학구분 = '"
						+ slist.elementAt(j).university + "') and (학번 = '" + slist.elementAt(j).studentNumber + "')");
			} else if (wish.equals("3지망")) {
				rs1 = stmt.executeUpdate("update 생활관신청내역 set 3지망생활관 = null , 식비구분_학기3 = null where (년도 = '"
						+ slist.elementAt(j).year + "') and (학기 = '" + slist.elementAt(j).semester + "') and (대학구분 = '"
						+ slist.elementAt(j).university + "') and (학번 = '" + slist.elementAt(j).studentNumber + "')");
			}
		}
		protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD8_입사신청자를목록에서삭제성공);
		output.write(protocol.getPacket());
	}

	void CODE05(Protocol protocol) throws IOException, SQLException {
	}

	void CODE06(Protocol protocol) throws IOException, SQLException {
	}

	void CODE07(Protocol protocol) throws IOException, SQLException {
	}

	void CODE08(Protocol protocol) throws IOException, SQLException {
		String[] data = protocol.getString().split("/"); // 년도, 학기, 일정구분, 개시일, 종료일, 안내 순서대로 전달받은 데이터 구분
		String year = data[0];
		String semester = data[1];
		String schedule = data[2];
		String start = data[3];
		String end = data[4];
		String explain = ""; // 안내는 null일 수 있음

		if (schedule.equals("선택")) { // 일정구분을 선택하지 않은 경우
			protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD17_선발일정등록실패);
		} else if (start.compareTo(end) > 0) { // 개시일이 종료일보다 나중 날짜인 경우
			protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD17_선발일정등록실패);
		} else { // 예외처리 검사 후 정상적으로 insert
			String sql;
			if (data.length < 6) { // 안내를 입력하지 않은 경우
				sql = "insert into 선발일정  (일정구분,년도,학기,개시일,종료일) values(\"" + schedule + "\",\"" + year + "\",\""
						+ semester + "\",\"" + start + "\",\"" + end + "\") on duplicate key update 개시일=\"" + start
						+ "\",종료일=\"" + end + "\",안내=null";
			} else { // 년도, 학기, 일정구분, 개시일, 종료일, 안내 모두 입력한 경우
				explain = data[5];
				sql = "insert into 선발일정  (일정구분,년도,학기,개시일,종료일,안내) values(\"" + schedule + "\",\"" + year + "\",\""
						+ semester + "\",\"" + start + "\",\"" + end + "\",\"" + explain
						+ "\") on duplicate key update 개시일=\"" + start + "\",종료일=\"" + end + "\",안내=\"" + explain
						+ "\"";
			}
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD16_선발일정등록성공);
		}
		output.write(protocol.getPacket());
	}

	void CODE09(Protocol protocol) throws IOException, SQLException {// 사용료등록요청
		// 생활관비, 관구분, 신청기간구분을 value배열에 넣음.
		String[] value = protocol.getString().split("/");
		String query = "UPDATE 입사금액 SET 생활관비 = " + value[0] + " WHERE 관구분 = \"" + value[1] + "\" AND 신청기간구분 = \""
				+ value[2] + "\";";
		System.out.println(query);
		Statement stmt = conn.createStatement();
		int r = stmt.executeUpdate(query);
		// 결과발송
		if (r > 0) {
			protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD18_사용료등록성공);
			output.write(protocol.getPacket());
			System.out.println("[갱신요청 결과를 보냄]");
		} else {
			protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD19_사용료등록실패);
			output.write(protocol.getPacket());
			System.out.println("[갱신요청 결과를 보냄]");
		}
	}

	void CODE10(Protocol protocol) throws IOException, SQLException {// 급식비등록요청
		// 식비, 관구분, 신청기간구분, 식비구분을 value배열에 넣음.
		String[] value = protocol.getString().split("/");
		String query = "UPDATE 입사금액 SET 식비 = " + value[0] + " WHERE 관구분 = \"" + value[1] + "\" AND 신청기간구분 = \""
				+ value[2] + "\" AND 식비구분 = \"" + value[3] + "\";";
		System.out.println(query);
		Statement stmt = conn.createStatement();
		int r = stmt.executeUpdate(query);
		// 결과발송
		if (r > 0) {
			protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD20_급식비등록성공);
			output.write(protocol.getPacket());
			System.out.println("[갱신요청 결과를 보냄]");
		} else {
			protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD21_급식비등록실패);
			output.write(protocol.getPacket());
			System.out.println("[갱신요청 결과를 보냄]");
		}
	}

	// 납부기능
	void CODE11(Protocol protocol, int StudentNumber) throws IOException, SQLException {

		// 납부일자를 등록하기 위한 Date
		Date date = new Date();
		String dt = new SimpleDateFormat("yyyy-MM-dd").format(date);
		// 클라이언트에게 받는 money를 저장
		int money = Integer.parseInt(protocol.getString());
		// 납부일, 납부금액,납부여부를 갱신해준다.
		Statement stmt = conn.createStatement();
		int rs = stmt.executeUpdate("UPDATE `dormitory`.`생활관신청내역` SET `납부일` = '" + dt + "', 납부여부 = '납부' , `납부금액` = '"
				+ money + "' WHERE (`학번` = '" + StudentNumber + "')");
		System.out.println(rs);
		// 갱신성공시 납부성공메세지를 보낸다.
		if (rs == 1) {
			protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD22_납부성공);
			output.write(protocol.getPacket());
		} else {// 갱신실패시 납부실패메세지를 보낸다.
			protocol = new Protocol(Protocol.TYPE10_RENEW_RES, Protocol.T10_CD23_납부실패);
			output.write(protocol.getPacket());
		}
		stmt.close();
	}

	double SetGrade(int StudentNumber) {
		String grade = "";
		int cnt = 0;
		int GP = 0;
		double result = 0;
		try {
			// 성적계산(직전 1년성적 + 거리가산점) //얘도 studentNumber바꿔줘야돼
			String query = "SELECT * FROM dormitory.성적 WHERE 개설년도 = " + Calendar.getInstance().get(Calendar.YEAR)
					+ " AND 학번 = " + "20190001" + ";";
			// 1학년일때 어떡해 제낀다고 했었나 직전성적은 다 있다고 가정할게
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				grade = rs.getString("성적등급");
				GP = rs.getInt("학점");
				switch (grade) {
				case "A+":
					result += (double) GP * 4.5;
					cnt += GP;
					break;
				case "A":
					result += (double) GP * 4;
					cnt += GP;
					break;
				case "B+":
					result += (double) GP * 3.5;
					cnt += GP;
					break;
				case "B":
					result += (double) GP * 3;
					cnt += GP;
					break;
				case "C+":
					result += (double) GP * 2.5;
					cnt += GP;
					break;
				case "C":
					result += (double) GP * 2;
					cnt += GP;
					break;
				case "D+":
					result += (double) GP * 1.5;
					cnt += GP;
					break;
				case "D":
					result += (double) GP * 1;
					cnt += GP;
					break;
				case "F":
					result += (double) GP * 0;
					cnt += GP;
					break;
				}
			}
			result /= cnt;
		} catch (SQLException e) {

		}
		return result;
	}
}

class Student2 {
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

	Student2(String year, String semester, String university, String studentNumber, String wish, String dormitory,
			String meal) {
		this.year = year;
		this.semester = semester;
		this.university = university;
		this.studentNumber = studentNumber;
		this.wish = wish;
		this.dormitory = dormitory;
		this.meal = meal;
	}
}