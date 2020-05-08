package 서버;

import javax.xml.transform.Result;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VIEW_HANDLER {

	InputStream input;
	OutputStream output;
	Connection conn;

	VIEW_HANDLER(InputStream input, OutputStream output, Connection conn) {
		this.input = input;
		this.output = output;
		this.conn = conn;
	}

	void CODE00(Protocol protocol) throws IOException, SQLException {
	}

	void CODE01(Protocol protocol) throws IOException, SQLException {// 생활관비 조회
		int temp = 0;
		String result = "";
		// string값 나누기
		String[] value = protocol.getString().split("/");
		if (value[1].equals("한학기")) {
			String query = "SELECT * FROM 입사금액 WHERE 관구분 = \"" + value[0] + "\" AND 신청기간구분 = \"" + value[1]
					+ "\" AND 식비구분 = \"" + value[2] + "\";";// 생활관비 조회쿼리문(생활관비)
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				if (value[2].equals("식사안함")) {
					temp = rs.getInt("생활관비");
					result = Integer.toString(temp);
					protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD2_생활관비조회승인);
					protocol.setString(result);
					output.write(protocol.getPacket());
					System.out.println("[조회요청에 대한 메세지를 보냄]");
				} else {
					temp = rs.getInt("생활관비") + rs.getInt("식비");
					result = Integer.toString(temp);
					protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD2_생활관비조회승인);
					protocol.setString(result);
					output.write(protocol.getPacket());
					System.out.println("[조회요청에 대한 메세지를 보냄]");
				}
			}
		} else {
			String query = "SELECT * FROM 입사금액 WHERE 관구분 = \"" + value[0] + "\" AND 신청기간구분 = \"" + "1년"
					+ "\" AND 식비구분 = \"" + value[2] + "\";";// 생활관비 조회쿼리문(생활관비)
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				if (value[2].equals("식사안함")) {
					temp = rs.getInt("생활관비");
					result = Integer.toString(temp);
					protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD2_생활관비조회승인);
					protocol.setString(result);
					output.write(protocol.getPacket());
					System.out.println("[조회요청에 대한 메세지를 보냄]");
				} else {
					temp = rs.getInt("생활관비") + rs.getInt("식비");
					result = Integer.toString(temp);
					protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD2_생활관비조회승인);
					protocol.setString(result);
					output.write(protocol.getPacket());
					System.out.println("[조회요청에 대한 메세지를 보냄]");
				}
			}
			query = "SELECT * FROM 입사금액 WHERE 관구분 = \"" + value[0] + "\" AND 신청기간구분 = \"" + "1년(방학)"
					+ "\" AND 식비구분 = \"" + value[3] + "\";";// 생활관비 조회쿼리문(생활관비)
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				if (value[3].equals("식사안함")) {
					temp = rs.getInt("생활관비");
					result = Integer.toString(temp);
					protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD2_생활관비조회승인);
					protocol.setString(result);
					output.write(protocol.getPacket());
					System.out.println("[조회요청에 대한 메세지를 보냄]");
				} else {
					temp = rs.getInt("생활관비") + rs.getInt("식비");
					result = Integer.toString(temp);
					protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD2_생활관비조회승인);
					protocol.setString(result);
					output.write(protocol.getPacket());
					System.out.println("[조회요청에 대한 메세지를 보냄]");
				}
			}
		}
	}

	void CODE02(Protocol protocol, int StudentNumber) throws IOException, SQLException {
		String selection_result = null; // 선발결과
		String payment_status = null; // 납부여부
		String food_expenses = null; // 식비 구분
		String barracks = null;// 생활관구분
		String room_number = null; // 호실번호
		String bed_number = null; // 침대번호
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from 생활관신청내역, 호실배정내역  where 생활관신청내역.학번 ='" + StudentNumber + "'");
		if (rs.next()) {
			selection_result = rs.getString("선발결과");
			payment_status = rs.getString("납부여부");
			food_expenses = rs.getString("합격생활관식사1");
			barracks = rs.getString("합격생활관");
			room_number = rs.getString("호실");
			bed_number = rs.getString("침대번호");
			// 조회결과를 클라이언트에게 전송
			protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD4_호실조회승인);
			String room_check_result = selection_result + "/" + payment_status + "/" + food_expenses + "/" + barracks
					+ "/" + room_number + "/" + bed_number;
			protocol.setString(room_check_result);
			output.write(protocol.getPacket());
		} else {// 호실조회가 불가능한 모든 경우
			protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD5_호실조회거절);
			output.write(protocol.getPacket());
		}
		rs.close();
		stmt.close();
	}

	// 해당학번의 입사지원내역을 조회하는 기능
	void CODE03(Protocol protocol, int StudentNumber) throws IOException, SQLException {
		Statement stmt = conn.createStatement();
		// 해당학번의 입사지원내역을 가져온다.
		ResultSet rs = stmt.executeQuery(
				"SELECT 1년신청생활관,식비구분_1년1,식비구분_1년2,1지망생활관,식비구분_학기1,2지망생활관,식비구분_학기2,3지망생활관,식비구분_학기3 FROM 생활관신청내역 WHERE 학번="
						+ StudentNumber);

		if (rs.next()) {// 입사지원내역이 있다면 클라이언트에게 보낸다.
			String result = rs.getString("1년신청생활관") + "/" + rs.getString("식비구분_1년1") + "/" + rs.getString("식비구분_1년2")
					+ "/" + rs.getString("1지망생활관") + "/" + rs.getString("식비구분_학기1") + "/" + rs.getString("2지망생활관") + "/"
					+ rs.getString("식비구분_학기2") + "/" + rs.getString("3지망생활관") + "/" + rs.getString("식비구분_학기3");
			protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD6_입사지원내역조회승인);
			protocol.setString(result);
			output.write(protocol.getPacket());
		} else {// 없다면 거절메세지를 보낸다.
			protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD7_입사지원내역조회거절);
			output.write(protocol.getPacket());
		}
		rs.close();
		stmt.close();
	}

	// 해당학번의 선발결과를 조회하는 기능
	void CODE04(Protocol protocol, int StudentNumber) throws IOException, SQLException {
		Statement stmt = conn.createStatement();
		// 해당학번의 입사기간, 합격지망구분, 합격생활관,합격생활관식사1,2 를가져온다.
		ResultSet rs = stmt
				.executeQuery("SELECT 입사기간,합격지망구분,합격생활관,합격생활관식사1,합격생활관식사2,선발결과 FROM 생활관신청내역 WHERE 학번=" + StudentNumber);

		if (rs.next()) {// 합격내역이 있다면 클라이언트에게 보낸다
			String result = rs.getString("입사기간") + "/" + rs.getString("합격지망구분") + "/" + rs.getString("합격생활관") + "/"
					+ rs.getString("합격생활관식사1") + "/" + rs.getString("합격생활관식사2") + "/" + rs.getString("선발결과");
			protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD8_선발결과조회승인);
			protocol.setString(result);
			output.write(protocol.getPacket());
		} else {// 합격내역이 없으면 거절메세지를 보낸다.
			protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD9_선발결과조회거절);
			output.write(protocol.getPacket());
		}
		rs.close();
		stmt.close();
	}

	void CODE05(Protocol protocol, int StudentNumber) throws IOException, SQLException {
		// 해당 디렉토리 내 전체 파일 확인
		String path = "C:\\결핵진단서\\";
		File dirFile = new File(path);
		File[] fileList = dirFile.listFiles();
		String studentFileName1 = Integer.toString(StudentNumber) + ".jpg";
		String studentFileName2 = Integer.toString(StudentNumber) + ".JPG";
		boolean exist = false;

		// 해당 경로에 파일이 있을 때
		if (fileList.length > 0) {
			// 학번과 이름이 같은 파일이 있는지 확인
			for (int i = 0; i < fileList.length; i++) {
				// 학번과 파일 명이 같으면 결핵진단서 존재
				if (studentFileName1.equals(fileList[i].getName()) || studentFileName2.equals(fileList[i].getName()))
					exist = true;
			}

			// 결핵진단서 존재
			if (exist) {
				String sql = "select 제출일시, 진단일시, 파일명, 서버파일경로 from 결핵진단서 where 학번=\"" + StudentNumber + "\"";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				String result = null;
				while (rs.next()) {
					String submission = rs.getString("제출일시");
					String diagnosis = rs.getString("진단일시");
					String name = rs.getString("파일명");
					String filePath = rs.getString("서버파일경로");
					if (diagnosis == null)
						result = submission + "/" + name + "/" + filePath;
					else
						result = submission + "/" + diagnosis + "/" + name + "/" + filePath;
				}
				protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD10_해당결핵진단서존재);
				if (result != null)
					protocol.setString(result);
			}

			// 결핵진단서 존재하지않음
			else {
				protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD11_해당결핵진단서존재하지않음);
			}
			output.write(protocol.getPacket());
		}

		// 해당 경로에 파일이 없을 때
		else {
			protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD11_해당결핵진단서존재하지않음);
			output.write(protocol.getPacket());
		}
	}

	void CODE06(Protocol protocol) throws IOException, SQLException {
	}

	void CODE07(Protocol protocol) throws IOException, SQLException {
		String[] condition_result = protocol.getString().split("/");
		String year = condition_result[0]; // 년도
		String semester = condition_result[1]; // 학기
		String university = condition_result[2]; // 대학구분
		String dormitory = condition_result[3]; // 생활관
		String count_dormitory = condition_result[3];
		String wish = condition_result[4]; // 지망
		String meal = condition_result[5]; // 식사
		String studentNumber = condition_result[6]; // 학번
		// 결과표에 보여줄 추가적인 정보
		String level = null; // 학년
		String department = null; // 학과
		String name = null;// 성명
		// 성적처리에 대한 정보
		float grade = 0;
		String address = null; // 학부모 주소
		// 콤보박스 처리에 대한 DB조건
		Statement stmt = (Statement) conn.createStatement();
		String sql = "select * from 생활관신청내역 where";

		// 콤보박스 조건에 대한 sql문 생성
		if (year.equals("전체") && semester.equals("전체") && university.equals("전체") && dormitory.equals("전체")
				&& wish.equals("전체") && meal.equals("전체") && studentNumber.equals(" ")) {// 모두 전체일때
			sql = sql.substring(0, sql.length() - 5);
		}
		if (!year.equals("전체")) { // 년도 콤보박스
			sql += " 년도 = '" + year + "' and ";
		}

		if (!semester.equals("전체")) { // 학기 콤보박스
			if (semester.equals("1학기")) {
				sql += " 학기 = '1' and ";
			} else {
				sql += " 학기 = '2' and ";
			}
		}
		if (!university.equals("전체")) { // 대학구분 콤보박스
			sql += " 대학구분 = '" + university + "' and ";
		}
		if (!dormitory.equals("전체")) { // 생활관 콤보박스
			sql += " (1년신청생활관 = '" + dormitory + "' or 1지망생활관 = '" + dormitory + "' or 2지망생활관 = '" + dormitory
					+ "' or 3지망생활관 = '" + dormitory + "') and ";
		}
		if (!wish.equals("전체")) { // 지망 콤보박스
			if (dormitory.equals("전체")) {// 1)기숙사가 전체인경우
				if (wish.equals("1년")) {
					sql += " 1년신청생활관 is not null" + " and ";
				} else if (wish.equals("1지망")) {
					sql += " 1지망생활관 is not null" + " and ";
				} else if (wish.equals("2지망")) {
					sql += " 2지망생활관 is not null" + " and ";
				} else if (wish.equals("3지망")) {
					sql += " 3지망생활관 is not null" + " and ";
				}
			} else { // 2)기숙사명이 존재하여 기숙사명에 대한 지망구분과 일치하는경우
				if (wish.equals("1년")) {
					sql += " 1년신청생활관 = '" + dormitory + "' and ";
				} else if (wish.equals("1지망")) {
					sql += " 1지망생활관 = '" + dormitory + "' and ";
				} else if (wish.equals("2지망")) {
					sql += " 2지망생활관 = '" + dormitory + "' and ";
				} else if (wish.equals("3지망")) {
					sql += " 3지망생활관 = '" + dormitory + "' and ";
				}
			}
		}
		if (!meal.equals("전체")) { // 식사 콤보박스
			sql += " (식비구분_1년1 = '" + meal + "' or 식비구분_1년2 = '" + meal + "' or 식비구분_학기1 = '" + meal
					+ "' or 식비구분_학기2 = '" + meal + "' or 식비구분_학기3 = '" + meal + "') and ";
		}
		if (!studentNumber.equals(" ")) { // 학번 콤보박스
			sql += " 학번 = " + studentNumber;
		} else {
			studentNumber = null;
		}
		if (sql.substring(sql.length() - 4, sql.length()).equals("and ")) { // 마지막 and인 경우
			sql = sql.substring(0, sql.length() - 4);
		}
		if (sql.substring(sql.length() - 5, sql.length()).equals("where")) { // 마지막 and인 경우
			sql = sql.substring(0, sql.length() - 5);
		}
		ResultSet rs = stmt.executeQuery(sql);

		String result = ""; // 검색 결과
		int cnt = 0;// 결과표에서 해당정보까지의 행을 만들기 위한 변수
		// 콤보박스에 대해 나온 결과의 sql에 대한 정보를 찾는 결과
		if ((year.equals("전체") && semester.equals("전체") && university.equals("전체") && dormitory.equals("전체")
				&& wish.equals("전체") && meal.equals("전체") && studentNumber == null)
				|| (!year.equals("전체") || !semester.equals("전체") || !university.equals("전체"))) {
			while (rs.next()) {
				if (rs.getString("1년신청생활관") != null) { // 1년 신청 정보가 있다
					year = rs.getString("년도");
					semester = rs.getString("학기");
					university = rs.getString("대학구분");
					department = rs.getString("학과명");
					level = rs.getString("학년");
					studentNumber = rs.getString("학번");
					name = rs.getString("성명");
					wish = "1년";
					dormitory = rs.getString("1년신청생활관");
					meal = rs.getString("식비구분_1년1");
					address = rs.getString("보호자주소");
					grade = rs.getFloat("성적");
					result += year + "/" + semester + "/" + university + "/" + department + "/" + level + "/"
							+ studentNumber + "/" + name + "/" + wish + "/" + dormitory + "/" + meal + "/" + address
							+ "/" + grade + "/";
					cnt++;
				}
				if (rs.getString("1지망생활관") != null) {
					year = rs.getString("년도");
					semester = rs.getString("학기");
					university = rs.getString("대학구분");
					department = rs.getString("학과명");
					level = rs.getString("학년");
					studentNumber = rs.getString("학번");
					name = rs.getString("성명");
					wish = "1지망";
					dormitory = rs.getString("1지망생활관");
					meal = rs.getString("식비구분_학기1");
					address = rs.getString("보호자주소");
					grade = rs.getFloat("성적");
					result += year + "/" + semester + "/" + university + "/" + department + "/" + level + "/"
							+ studentNumber + "/" + name + "/" + wish + "/" + dormitory + "/" + meal + "/" + address
							+ "/" + grade + "/";
					cnt++;
				}
				if (rs.getString("2지망생활관") != null) {
					year = rs.getString("년도");
					semester = rs.getString("학기");
					university = rs.getString("대학구분");
					department = rs.getString("학과명");
					level = rs.getString("학년");
					studentNumber = rs.getString("학번");
					name = rs.getString("성명");
					wish = "2지망";
					dormitory = rs.getString("2지망생활관");
					meal = rs.getString("식비구분_학기2");
					address = rs.getString("보호자주소");
					grade = rs.getFloat("성적");
					result += year + "/" + semester + "/" + university + "/" + department + "/" + level + "/"
							+ studentNumber + "/" + name + "/" + wish + "/" + dormitory + "/" + meal + "/" + address
							+ "/" + grade + "/";
					cnt++;
				}
				if (rs.getString("3지망생활관") != null) {
					year = rs.getString("년도");
					semester = rs.getString("학기");
					university = rs.getString("대학구분");
					department = rs.getString("학과명");
					level = rs.getString("학년");
					studentNumber = rs.getString("학번");
					name = rs.getString("성명");
					wish = "3지망";
					dormitory = rs.getString("3지망생활관");
					meal = rs.getString("식비구분_학기3");
					address = rs.getString("보호자주소");
					grade = rs.getFloat("성적");
					result += year + "/" + semester + "/" + university + "/" + department + "/" + level + "/"
							+ studentNumber + "/" + name + "/" + wish + "/" + dormitory + "/" + meal + "/" + address
							+ "/" + grade + "/";
					cnt++;
				}
			}
		} else {
			if (studentNumber != null) {
				if (wish.equals("전체")) { // 조건에 대해서 모든 지망들을 알려줘야한다.
					while (rs.next()) {
						if (rs.getString("1년신청생활관") != null) {// 1년신청생활관 정보가 있다.
							year = rs.getString("년도");
							semester = rs.getString("학기");
							university = rs.getString("대학구분");
							department = rs.getString("학과명");
							level = rs.getString("학년");
							studentNumber = rs.getString("학번");
							name = rs.getString("성명");
							wish = "1년";
							dormitory = rs.getString("1년신청생활관");
							meal = rs.getString("식비구분_1년1");
							address = rs.getString("보호자주소");
							grade = rs.getFloat("성적");
							result += year + "/" + semester + "/" + university + "/" + department + "/" + level + "/"
									+ studentNumber + "/" + name + "/" + wish + "/" + dormitory + "/" + meal + "/"
									+ address + "/" + grade + "/";
							cnt++;
						}
						if (rs.getString("1지망생활관") != null) {// 1지망생활관 정보가 있다.
							year = rs.getString("년도");
							semester = rs.getString("학기");
							university = rs.getString("대학구분");
							department = rs.getString("학과명");
							level = rs.getString("학년");
							studentNumber = rs.getString("학번");
							name = rs.getString("성명");
							wish = "1지망";
							dormitory = rs.getString("1지망생활관");
							meal = rs.getString("식비구분_학기1");
							address = rs.getString("보호자주소");
							grade = rs.getFloat("성적");
							result += year + "/" + semester + "/" + university + "/" + department + "/" + level + "/"
									+ studentNumber + "/" + name + "/" + wish + "/" + dormitory + "/" + meal + "/"
									+ address + "/" + grade + "/";
							cnt++;
						}
						if (rs.getString("2지망생활관") != null) {// 2지망생활관 정보가 있다.
							year = rs.getString("년도");
							semester = rs.getString("학기");
							university = rs.getString("대학구분");
							department = rs.getString("학과명");
							level = rs.getString("학년");
							studentNumber = rs.getString("학번");
							name = rs.getString("성명");
							wish = "2지망";
							dormitory = rs.getString("2지망생활관");
							meal = rs.getString("식비구분_학기2");
							address = rs.getString("보호자주소");
							grade = rs.getFloat("성적");
							result += year + "/" + semester + "/" + university + "/" + department + "/" + level + "/"
									+ studentNumber + "/" + name + "/" + wish + "/" + dormitory + "/" + meal + "/"
									+ address + "/" + grade + "/";
							cnt++;
						}
						if (rs.getString("3지망생활관") != null) {// 3지망생활관 정보가 있다.
							year = rs.getString("년도");
							semester = rs.getString("학기");
							university = rs.getString("대학구분");
							department = rs.getString("학과명");
							level = rs.getString("학년");
							studentNumber = rs.getString("학번");
							name = rs.getString("성명");
							wish = "3지망";
							dormitory = rs.getString("3지망생활관");
							meal = rs.getString("식비구분_학기3");
							address = rs.getString("보호자주소");
							grade = rs.getFloat("성적");
							result += year + "/" + semester + "/" + university + "/" + department + "/" + level + "/"
									+ studentNumber + "/" + name + "/" + wish + "/" + dormitory + "/" + meal + "/"
									+ address + "/" + grade + "/";
							cnt++;
						}
						break;
					}
				}
			} else if (!wish.equals("전체")) {
				while (rs.next()) {
					if (wish.equals("1년")) {
						year = rs.getString("년도");
						semester = rs.getString("학기");
						university = rs.getString("대학구분");
						department = rs.getString("학과명");
						level = rs.getString("학년");
						studentNumber = rs.getString("학번");
						name = rs.getString("성명");
						wish = "1년";
						dormitory = rs.getString("1년신청생활관");
						meal = rs.getString("식비구분_1년1");
						address = rs.getString("보호자주소");
						grade = rs.getFloat("성적");
						result += year + "/" + semester + "/" + university + "/" + department + "/" + level + "/"
								+ studentNumber + "/" + name + "/" + wish + "/" + dormitory + "/" + meal + "/" + address
								+ "/" + grade + "/";
						cnt++;
					} else if (wish.equals("1지망")) {
						year = rs.getString("년도");
						semester = rs.getString("학기");
						university = rs.getString("대학구분");
						department = rs.getString("학과명");
						level = rs.getString("학년");
						studentNumber = rs.getString("학번");
						name = rs.getString("성명");
						wish = "1지망";
						dormitory = rs.getString("1지망생활관");
						meal = rs.getString("식비구분_학기1");
						address = rs.getString("보호자주소");
						grade = rs.getFloat("성적");
						result += year + "/" + semester + "/" + university + "/" + department + "/" + level + "/"
								+ studentNumber + "/" + name + "/" + wish + "/" + dormitory + "/" + meal + "/" + address
								+ "/" + grade + "/";
						cnt++;
					} else if (wish.equals("2지망")) {
						year = rs.getString("년도");
						semester = rs.getString("학기");
						university = rs.getString("대학구분");
						department = rs.getString("학과명");
						level = rs.getString("학년");
						studentNumber = rs.getString("학번");
						name = rs.getString("성명");
						wish = "2지망";
						dormitory = rs.getString("2지망생활관");
						meal = rs.getString("식비구분_학기2");
						address = rs.getString("보호자주소");
						grade = rs.getFloat("성적");
						result += year + "/" + semester + "/" + university + "/" + department + "/" + level + "/"
								+ studentNumber + "/" + name + "/" + wish + "/" + dormitory + "/" + meal + "/" + address
								+ "/" + grade + "/";
						cnt++;
					} else if (wish.equals("3지망")) {
						year = rs.getString("년도");
						semester = rs.getString("학기");
						university = rs.getString("대학구분");
						department = rs.getString("학과명");
						level = rs.getString("학년");
						studentNumber = rs.getString("학번");
						name = rs.getString("성명");
						wish = "3지망";
						dormitory = rs.getString("3지망생활관");
						meal = rs.getString("식비구분_학기2");
						address = rs.getString("보호자주소");
						grade = rs.getFloat("성적");
						result += year + "/" + semester + "/" + university + "/" + department + "/" + level + "/"
								+ studentNumber + "/" + name + "/" + wish + "/" + dormitory + "/" + meal + "/" + address
								+ "/" + grade + "/";
						cnt++;
					}
				}
			} else {
				while (rs.next()) {
					if (rs.getString("1년신청생활관") == count_dormitory) {
						year = rs.getString("년도");
						semester = rs.getString("학기");
						university = rs.getString("대학구분");
						department = rs.getString("학과명");
						level = rs.getString("학년");
						studentNumber = rs.getString("학번");
						name = rs.getString("성명");
						wish = "1년";
						dormitory = rs.getString("1년신청생활관");
						meal = rs.getString("식비구분_1년1");
						address = rs.getString("보호자주소");
						grade = rs.getFloat("성적");
						result += year + "/" + semester + "/" + university + "/" + department + "/" + level + "/"
								+ studentNumber + "/" + name + "/" + wish + "/" + dormitory + "/" + meal + "/" + address
								+ "/" + grade + "/";
						cnt++;
					} else if (rs.getString("1지망생활관") == count_dormitory) {
						year = rs.getString("년도");
						semester = rs.getString("학기");
						university = rs.getString("대학구분");
						department = rs.getString("학과명");
						level = rs.getString("학년");
						studentNumber = rs.getString("학번");
						name = rs.getString("성명");
						wish = "1지망";
						dormitory = rs.getString("1지망생활관");
						meal = rs.getString("식비구분_학기1");
						address = rs.getString("보호자주소");
						grade = rs.getFloat("성적");
						result += year + "/" + semester + "/" + university + "/" + department + "/" + level + "/"
								+ studentNumber + "/" + name + "/" + wish + "/" + dormitory + "/" + meal + "/" + address
								+ "/" + grade + "/";
						cnt++;
					} else if (rs.getString("2지망생활관") == count_dormitory) {
						year = rs.getString("년도");
						semester = rs.getString("학기");
						university = rs.getString("대학구분");
						department = rs.getString("학과명");
						level = rs.getString("학년");
						studentNumber = rs.getString("학번");
						name = rs.getString("성명");
						wish = "2지망";
						dormitory = rs.getString("2지망생활관");
						meal = rs.getString("식비구분_학기2");
						address = rs.getString("보호자주소");
						grade = rs.getFloat("성적");
						result += year + "/" + semester + "/" + university + "/" + department + "/" + level + "/"
								+ studentNumber + "/" + name + "/" + wish + "/" + dormitory + "/" + meal + "/" + address
								+ "/" + grade + "/";
						cnt++;
					} else if (rs.getString("3지망생활관") == count_dormitory) {
						year = rs.getString("년도");
						semester = rs.getString("학기");
						university = rs.getString("대학구분");
						department = rs.getString("학과명");
						level = rs.getString("학년");
						studentNumber = rs.getString("학번");
						name = rs.getString("성명");
						wish = "3지망";
						dormitory = rs.getString("3지망생활관");
						meal = rs.getString("식비구분_학기2");
						address = rs.getString("보호자주소");
						grade = rs.getFloat("성적");
						result += year + "/" + semester + "/" + university + "/" + department + "/" + level + "/"
								+ studentNumber + "/" + name + "/" + wish + "/" + dormitory + "/" + meal + "/" + address
								+ "/" + grade + "/";
						cnt++;
					}
				}
			}
		}
		// 기숙사 선택에 의한 자동체크 기능 구현을 위한 sql문, 생활관구분을 선택한 경우에만 자동체크 기능을 수행함
		int number = 0;
		rs = stmt.executeQuery("SELECT * FROM 생활관별_호실현황"); // 생활관 총 인원수를 확인
		while (rs.next()) {
			if (rs.getString("생활관").equals(count_dormitory)) { // 선택한 생활관명과 DB생활관 명이 같은 경우의 인원들을 체크해준다
				number += rs.getInt("인원");
			}
		}
		result = number + "/" + result;
		if (cnt == 0) {// 조회했으나 아무도 안나옴
			protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD15_입사신청자조회거절);
			output.write(protocol.getPacket());
		}
		if (result.substring(result.length(), result.length()).equals("/")) { // 마지막 and인 경우
			result = result.substring(0, result.length() - 1);
		}
		protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD14_입사신청자조회승인);
		protocol.setString(result);
		output.write(protocol.getPacket());
	}

	void CODE08(Protocol protocol) throws IOException, SQLException, ClassNotFoundException {
		System.out.println("안녕 난 코드8");
		String group_type = null; // 대학구분
		String department = null; // 학과명
		String studentLevel = null; // 학년
		String studentNumber = null; // 학번
		String studentName = null; // 성명
		String dormitory_type = null; // 생활관구분
		String selection_result = null; // 선발결과
		String tuberMCU = null; // 결핵진단서제출여부
		String money = null; // 입금여부
		int cnt = 0;
		String sql = "";

		try {
			// 클라이언트 쪽에서 콤보박스 데이터 or 학번 데이터 넘겨받아서 / 기준으로 split
			String[] temp = protocol.getString().split("/");
			Statement stmt = (Statement) conn.createStatement();
			// 결핵진단서에 학번이 있는지 없는지 검사 (결핵진단서 제출여부 검사)
			String s = "select 학번 from 결핵진단서";
			ResultSet r = stmt.executeQuery(s);
			ResultSet rs;
			String s2 = ""; // s2는 결핵진단서에 학번이 있으면 저장되고 없으면 ""
			while (r.next()) {
				s2 = r.getString("학번");
			}

			// 학번만 입력(넘겨)받은 경우 (콤보박스 선택 무시)
			if (temp.length == 1) {
				String 학번 = temp[0];

				if (s2 == "") {
					// 결핵진단서에 학번이 없는 경우엔 결핵제출일시가 X임
					sql = "select 대학구분, 학과명, 학년, 생활관신청내역.학번, 성명, 합격생활관, 선발결과, 납부여부 from 생활관신청내역 WHERE dormitory.생활관신청내역.선발결과 = \"합격\" and 학번='"
							+ 학번 + "'";
					rs = stmt.executeQuery(sql);
					tuberMCU = "X";
				} else {
					// 결핵진단서 학번 있는 경우엔 결핵제출일시 넘겨줌
					sql = "SELECT dormitory.생활관신청내역.대학구분, dormitory.생활관신청내역.학과명, dormitory.생활관신청내역.학년, dormitory.생활관신청내역.학번, dormitory.생활관신청내역.성명, dormitory.생활관신청내역.합격생활관, dormitory.생활관신청내역.선발결과, dormitory.결핵진단서.제출일시, dormitory.생활관신청내역.납부여부 "
							+ "FROM dormitory.생활관신청내역, dormitory.결핵진단서 "
							+ "WHERE dormitory.생활관신청내역.선발결과 = \"합격\" and dormitory.생활관신청내역.학번 = \"" + 학번 + "\" "
							+ " and dormitory.결핵진단서.학번 = \"" + 학번 + "\" "
							+ " GROUP BY dormitory.생활관신청내역.학번 order by dormitory.생활관신청내역.학번 asc";
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						tuberMCU = rs.getString("결핵진단서.제출일시");
					}
				}

				// result에 넘겨줄 데이터 넣어줌
				String result = "";
				while (rs.next()) {

					group_type = rs.getString("생활관신청내역.대학구분");
					department = rs.getString("생활관신청내역.학과명");
					studentLevel = rs.getString("생활관신청내역.학년");
					studentNumber = rs.getString("생활관신청내역.학번");
					studentName = rs.getString("생활관신청내역.성명");
					dormitory_type = rs.getString("생활관신청내역.합격생활관");
					selection_result = rs.getString("생활관신청내역.선발결과");
					money = rs.getString("생활관신청내역.납부여부");

					String selected_student = group_type + "/" + department + "/" + studentLevel + "/" + studentNumber
							+ "/" + studentName + "/" + dormitory_type + "/" + selection_result + "/" + tuberMCU + "/"
							+ money;

					result += selected_student;
					cnt++;

				}
				if (cnt == 0) {
					// 조회 못할때 거절 메시지 전송
					protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD17_입사선발자조회거절);
					output.write(protocol.getPacket());
				} else {
					// 조회 승인 -> 조회 결과를 클라이언트한테 전송
					protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD16_입사선발자조회승인);
					System.out.println(result);
					protocol.setString(result);
					output.write(protocol.getPacket());
				}

			} else {

				// 아래는 콤보박스만 선택된 경우, 알고리즘은 위와 동일
				String 년도 = temp[0];
				String 학기 = temp[1];
				String 대학구분 = temp[2];
				String 학과 = temp[3];
				String 결핵여부 = temp[4];
				String 납부구분 = temp[5];
				String 생활관구분 = temp[6];

				if (s2 == "") {
					sql = "select 대학구분, 학과명, 학년, 학번, 성명, 합격생활관, 선발결과, 납부여부 from 생활관신청내역 WHERE dormitory.생활관신청내역.선발결과 = \"합격\" ";
					tuberMCU = "X";
					if (결핵여부.equals("제출")) {
						sql += "and dormitory.생활관신청내역.학번 IN (SELECT dormitory.결핵진단서.학번 FROM dormitory.결핵진단서) ";
					} else if (결핵여부.equals("미제출")) {
						sql += "and dormitory.생활관신청내역.학번 NOT IN (SELECT dormitory.결핵진단서.학번 FROM dormitory.결핵진단서) ";
					} else {
						sql += "";
					}
				} else {
					sql = "SELECT dormitory.생활관신청내역.대학구분, dormitory.생활관신청내역.학과명, dormitory.생활관신청내역.학년, dormitory.생활관신청내역.학번, dormitory.생활관신청내역.성명, dormitory.생활관신청내역.합격생활관, dormitory.생활관신청내역.선발결과, dormitory.결핵진단서.제출일시, dormitory.생활관신청내역.납부여부  "
							+ "FROM dormitory.생활관신청내역, dormitory.결핵진단서  " + "WHERE dormitory.생활관신청내역.선발결과 = \"합격\" ";
					if (결핵여부.equals("제출")) {
						sql += "and dormitory.생활관신청내역.학번 = dormitory.결핵진단서.학번 ";
					} else if (결핵여부.equals("미제출")) {
						sql += "and dormitory.생활관신청내역.학번 NOT IN (SELECT dormitory.결핵진단서.학번 FROM dormitory.결핵진단서) ";
					} else {
						sql += "";
					}
				}
				if (!생활관구분.equals("전체")) {
					sql += "and dormitory.생활관신청내역.합격생활관 = " + "\"" + 생활관구분 + "\" ";
				} else {
					sql += "and dormitory.생활관신청내역.합격생활관 is not null ";
				}

				if (!대학구분.equals("전체")) {
					sql += "and dormitory.생활관신청내역.대학구분 = " + "\"" + 대학구분 + "\" ";
				} else {
					sql += "and dormitory.생활관신청내역.대학구분 is not null ";
				}

				if (!납부구분.equals("전체")) {
					sql += "and dormitory.생활관신청내역.납부여부 = " + "\"" + 납부구분 + "\" ";
				} else {
					sql += "and dormitory.생활관신청내역.납부여부 is not null ";
				}

				sql += "GROUP BY dormitory.생활관신청내역.학번 order by dormitory.생활관신청내역.학번 asc";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);

				String result = "";
				while (rs.next()) {

					group_type = rs.getString("생활관신청내역.대학구분");
					department = rs.getString("생활관신청내역.학과명");
					studentLevel = rs.getString("생활관신청내역.학년");
					studentNumber = rs.getString("생활관신청내역.학번");
					studentName = rs.getString("생활관신청내역.성명");
					dormitory_type = rs.getString("생활관신청내역.합격생활관");
					selection_result = rs.getString("생활관신청내역.선발결과");
					if (tuberMCU != "X")
						tuberMCU = rs.getString("결핵진단서.제출일시");
					money = rs.getString("생활관신청내역.납부여부");

					String selected_student = group_type + "/" + department + "/" + studentLevel + "/" + studentNumber
							+ "/" + studentName + "/" + dormitory_type + "/" + selection_result + "/" + tuberMCU + "/"
							+ money;

					// 사람이 여러명일 경우 %로 구분
					result += selected_student + "%";
					cnt++;

				}
				if (cnt == 0) {
					// 조회 못할때 거절 메시지 전송
					protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD17_입사선발자조회거절);
					output.write(protocol.getPacket());
				} else {
					// 조회 승인 -> 조회 결과를 클라이언트한테 전송
					protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD16_입사선발자조회승인);
					System.out.println(result);
					protocol.setString(result);
					System.out.println("test");
					output.write(protocol.getPacket());
					System.out.println("test2");
				}
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}

	void CODE09(Protocol protocol) throws IOException, SQLException {
		String group_type = null; // 대학구분
		String department = null; // 학과명
		String studentLevel = null; // 학년
		String studentNumber = null; // 학번
		String studentName = null; // 성명
		String dormitory_type = null; // 생활관구분
		String room = null; // 호실번호
		String bed = null; // 침대정보
		int cnt = 0;
		String sql = "";

		try {
			// 클라이언트 쪽에서 콤보박스 데이터 or 학번 데이터 넘겨받아서 / 기준으로 split
			String[] temp = protocol.getString().split("/");

			if (temp.length == 1) {
				// 클라이언트 쪽에서 넘겨받은 데이터가 학번일 경우
				String 학번 = temp[0];

				Statement stmt = (Statement) conn.createStatement();

				sql = "SELECT dormitory.학생.대학구분, dormitory.학생.학과명, dormitory.학생.학년, dormitory.학생.학번, dormitory.학생.성명, dormitory.생활관신청내역.합격생활관, dormitory.호실배정내역.호실, dormitory.호실배정내역.침대번호 "
						+ "FROM dormitory.생활관신청내역, dormitory.학생, dormitory.결핵진단서, dormitory.호실배정내역 "
						+ "WHERE dormitory.생활관신청내역.선발결과 = \"합격\" and dormitory.학생.학번 = dormitory.생활관신청내역.학번  and dormitory.학생.학번 = dormitory.호실배정내역.학번 "
						+ "and dormitory.생활관신청내역.합격생활관 is not null " + "and dormitory.생활관신청내역.대학구분 is not null "
						+ "and dormitory.학생.학과명 = dormitory.생활관신청내역.학과명 "
						+ "and dormitory.생활관신청내역.학번 = dormitory.결핵진단서.학번 " + "and dormitory.생활관신청내역.납부여부 = \"납부\" "
						+ "and dormitory.학생.학번 = " + 학번 + " ";
				sql += "GROUP BY dormitory.학생.학번 order by dormitory.생활관신청내역.학번 asc ";
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				String result = "";

				while (rs.next()) {
					group_type = rs.getString("학생.대학구분");
					department = rs.getString("학생.학과명");
					studentLevel = rs.getString("학생.학년");
					studentNumber = rs.getString("학생.학번");
					studentName = rs.getString("학생.성명");
					dormitory_type = rs.getString("생활관신청내역.합격생활관");
					room = rs.getString("호실배정내역.호실");
					bed = rs.getString("호실배정내역.침대번호");

					String selected_student2 = group_type + "/" + department + "/" + studentLevel + "/" + studentNumber
							+ "/" + studentName + "/" + dormitory_type + "/" + room + "/" + bed;
					// 사람이 여러명이면 %으로 구분
					result += selected_student2 + "%";
					cnt++;
				}
				if (cnt == 0) {
					// 조회 못할때 거절 메시지 전송
					protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD19_입사자조회거절);
					output.write(protocol.getPacket());
				} else {
					// 조회 승인 -> 조회 결과를 클라이언트한테 전송
					protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD18_입사자조회승인);

					System.out.println(result);
					protocol.setString(result);
					output.write(protocol.getPacket());
				}

			} else {
				// 클라이언트 쪽에서 넘겨받은 데이터가 학번이 아닌 콤보박스 선택일 경우
				String 년도 = temp[0];
				String 학기 = temp[1];
				String 대학구분 = temp[2];
				String 학과 = temp[3];
				String 결핵여부 = temp[4];
				String 납부구분 = temp[5];
				String 생활관구분 = temp[6];

				Statement stmt = (Statement) conn.createStatement();
				sql = "SELECT dormitory.학생.대학구분, dormitory.학생.학과명, dormitory.학생.학년, dormitory.학생.학번, dormitory.학생.성명, dormitory.생활관신청내역.합격생활관, dormitory.호실배정내역.호실, dormitory.호실배정내역.침대번호 "
						+ "FROM dormitory.생활관신청내역, dormitory.학생, dormitory.결핵진단서, dormitory.호실배정내역 "
						+ "WHERE dormitory.생활관신청내역.선발결과 = \"합격\" and dormitory.학생.학번 = dormitory.생활관신청내역.학번  and dormitory.학생.학번 = dormitory.호실배정내역.학번 ";

				if (!생활관구분.equals("전체")) {
					sql += "and dormitory.생활관신청내역.합격생활관 = " + "\"" + 생활관구분 + "\" ";
				} else {
					sql += "and dormitory.생활관신청내역.합격생활관 is not null ";
				}

				if (!대학구분.equals("전체")) {
					sql += "and dormitory.생활관신청내역.대학구분 = " + "\"" + 대학구분 + "\" ";
				} else {
					sql += "and dormitory.생활관신청내역.대학구분 is not null ";
				}

				if (학과.equals("전체") || 학과.equals("컴퓨터소프트웨어공학과")) {
					sql += "and dormitory.학생.학과명 = dormitory.생활관신청내역.학과명  ";
				}

				if (결핵여부.equals("제출")) {
					sql += "and dormitory.생활관신청내역.학번 = dormitory.결핵진단서.학번 ";
				}

				if (!납부구분.equals("납부")) {
					sql += "and dormitory.생활관신청내역.납부여부 = \"납부\" ";
				}

				sql += "GROUP BY dormitory.학생.학번 order by dormitory.생활관신청내역.학번 asc";

				ResultSet rs = stmt.executeQuery(sql);
				String result = "";

				while (rs.next()) {
					group_type = rs.getString("학생.대학구분");
					department = rs.getString("학생.학과명");
					studentLevel = rs.getString("학생.학년");
					studentNumber = rs.getString("학생.학번");
					studentName = rs.getString("학생.성명");
					dormitory_type = rs.getString("생활관신청내역.합격생활관");
					room = rs.getString("호실배정내역.호실");
					bed = rs.getString("호실배정내역.침대번호");

					String selected_student2 = group_type + "/" + department + "/" + studentLevel + "/" + studentNumber
							+ "/" + studentName + "/" + dormitory_type + "/" + room + "/" + bed;

					// 사람이 여러명이면 %으로 구분
					result += selected_student2 + "%";
					cnt++;
				}
				if (cnt == 0) {
					// 조회 못할때 거절 메시지 전송
					protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD19_입사자조회거절);
					output.write(protocol.getPacket());
				} else {
					// 조회 승인 -> 조회 결과를 클라이언트한테 전송
					protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD18_입사자조회승인);

					protocol.setString(result);
					output.write(protocol.getPacket());
				}
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}

	// 결핵진단서 제출현황 조회
	void CODE10(Protocol protocol) throws IOException, SQLException {

		// String 결과들을 /로 나누어 배열에 저장
		String[] temp = protocol.getString().split("/"); // 학번/년도/학기/생활관/제출여부/제출기간1/제출기간2
		String stdnum = temp[0]; // 학번
		String year = temp[1]; // 년도
		String semester = temp[2]; // 학기
		String dor = temp[3]; // 생활관 구분
		String whether = temp[4];// 제출여부

		String sql = null;
		String result = "";

		Statement stmt = conn.createStatement();
		ResultSet rs = null;

		// 학번이 없는경우
		if (stdnum.equals("")) {

			// 제출한사람
			if (whether.equals("제출")) {
				sql = "SELECT DISTINCT a.년도,a.학기,a.학년,a.학번,a.성명,a.합격생활관,b.제출서류구분,b.파일명,b.제출일시,b.진단일시,c.학생전화번호  \n"
						+ "FROM 생활관신청내역 a JOIN 결핵진단서 b JOIN 학생 c ON a.학번=b.학번 AND a.학번=c.학번 \n"
						+ "WHERE a.합격생활관 is not null and b.제출서류구분=\"결핵진단서\" AND a.년도=" + year
						+ " AND a.년도=b.년도 AND a.학기=" + semester + " AND a.학기=b.학기";

				// 생활관별로 조회
				if (!dor.equals("전체")) {
					sql += " AND a.합격생활관=\"" + dor + "\"";
				}

				// 특정 기간동안 제출한사람만 조회
				if (temp.length > 5) {
					String term1 = temp[5]; // 제출기간1
					String term2 = temp[6]; // 제출기간2

					sql += "AND b.제출일시 BETWEEN '" + term1 + "' AND '" + term2 + "'";
				}

				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					result += rs.getString("년도") + "/" + rs.getString("학기") + "/" + rs.getString("학년") + "/"
							+ rs.getString("학번") + "/" + rs.getString("성명") + "/" + rs.getString("합격생활관") + "/"
							+ rs.getString("제출서류구분") + "/" + rs.getString("파일명") + "/" + rs.getString("제출일시") + "/"
							+ rs.getString("진단일시") + "/" + rs.getString("학생전화번호") + "%";
				}

				// 제출 안한사람
			} else if (whether.equals("미제출")) {
				sql = "SELECT DISTINCT a.년도,a.학기,a.학년,a.학번,a.성명,a.합격생활관, c.학생전화번호 \n"
						+ "FROM 생활관신청내역 a JOIN 학생 c ON a.학번=c.학번 \n"
						+ "WHERE  a.합격생활관 is not null and NOT EXISTS ( SELECT * FROM 결핵진단서 b WHERE a.학번 = b.학번 ) AND a.년도="
						+ year + " AND a.학기=" + semester;

				if (!dor.equals("전체")) {
					sql += " AND a.합격생활관=\"" + dor + "\"";
				}

				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					result += rs.getString("년도") + "/" + rs.getString("학기") + "/" + rs.getString("학년") + "/"
							+ rs.getString("학번") + "/" + rs.getString("성명") + "/" + rs.getString("합격생활관")
							+ "/null/null/null/null/" + rs.getString("학생전화번호") + "%";
				}
			}

			// 학번이 있는경우 ==> 해당 학번 한명만 조회
		} else {

			sql = "SELECT DISTINCT a.년도,a.학기,a.학년,a.학번,a.성명,a.합격생활관,b.제출서류구분,b.파일명,b.제출일시,b.진단일시,c.학생전화번호  \n"
					+ "FROM 생활관신청내역 a JOIN 결핵진단서 b JOIN 학생 c ON a.학번=b.학번 AND a.학번=c.학번 \n"
					+ "WHERE  a.합격생활관 is not null and b.제출서류구분=\"결핵진단서\" AND a.년도=" + year + " AND a.년도=b.년도 AND a.학기="
					+ semester + " AND a.학기=b.학기 AND a.학번=" + temp[0];

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				result += rs.getString("년도") + "/" + rs.getString("학기") + "/" + rs.getString("학년") + "/"
						+ rs.getString("학번") + "/" + rs.getString("성명") + "/" + rs.getString("합격생활관") + "/"
						+ rs.getString("제출서류구분") + "/" + rs.getString("파일명") + "/" + rs.getString("제출일시") + "/"
						+ rs.getString("진단일시") + "/" + rs.getString("학생전화번호") + "%";
			}

		}
		protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD20_결핵진단서제출현황조회승인);
		if (result.equals(null)) {
			protocol.setString("");
		} else {
			protocol.setString(result);
		}
		output.write(protocol.getPacket());
		rs.close();
		stmt.close();
	}

	void CODE11(Protocol protocol) throws IOException, SQLException {// 사용료 조회
		String temp = "";
		String query = "SELECT DISTINCT 관구분, 신청기간구분, 생활관비 FROM 입사금액";// 생활관비 조회쿼리문
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		// 관구분, 신청기간구분, 생활관비순으로 temp에 추가
		while (rs.next()) {
			temp += rs.getString("관구분") + "/" + rs.getString("신청기간구분") + "/" + rs.getString("생활관비") + "/";
		}
		temp = temp.substring(0, temp.length() - 1);
		if (!temp.equals("")) {
			protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD22_사용료조회승인);
			protocol.setString(temp);
		} else {
			protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD23_사용료조회거절);
		}
		output.write(protocol.getPacket());
	}

	void CODE12(Protocol protocol) throws IOException, SQLException {// 급식비 조회
		String temp = "";
		String query = "SELECT 관구분, 신청기간구분, 식비구분, 식비 FROM 입사금액 WHERE NOT 식비구분 = \"식사안함\";";// 생활관비 조회쿼리문
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		// 관구분, 신청기간구분, 생활관비순으로 temp에 추가
		while (rs.next()) {
			temp += rs.getString("관구분") + "/" + rs.getString("신청기간구분") + "/" + rs.getString("식비구분") + "/"
					+ rs.getString("식비") + "/";
		}
		temp = temp.substring(0, temp.length() - 1);
		if (!temp.equals("")) {
			protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD24_급식비조회승인);
			protocol.setString(temp);
		} else {
			protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD25_급식비조회거절);
		}
		output.write(protocol.getPacket());
	}

	void CODE13(Protocol protocol) throws IOException, SQLException {
		String sql = "select * from 선발일정 order by 개시일, 종료일 asc";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		String result = null;
		// 쿼리 결과 받음
		while (rs.next()) {
			String s1 = rs.getString("일정구분");
			String s2 = rs.getString("개시일");
			String s3 = rs.getString("종료일");
			String s4 = rs.getString("안내");
			String tmp = s1 + "/" + s2 + "/" + s3 + "/" + s4;
			result = result + "/" + tmp;
		}
		// 조회된 제이터 전송
		protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD26_선발일정조회승인);
		protocol.setString(result);
		output.write(protocol.getPacket());
		rs.close();
		stmt.close();
	}

	// 고지서 조회 승인, 거절 - 고지서안에 들어가는 내용을 보내준다.
	void CODE14(Protocol protocol, int StudentNumber) throws IOException, SQLException {

		Statement stmt = conn.createStatement();
		// 해당학생의 생활관신청내역을 모두 가져온다.
		ResultSet rs = stmt.executeQuery("select * "
				+ "from dormitory.생활관신청내역, dormitory.학생 where 생활관신청내역.학번 = 학생.학번 and 생활관신청내역.학번=" + StudentNumber);

		if (rs.next()) {
			// 합격한 생활관 입사기간 식사를 가져온다. 식사가 null이면 식사안함으로 바꾼다.
			String 합격생활관 = rs.getString("합격생활관");
			String 합격생활관식사1 = rs.getString("합격생활관식사1");
			String 합격생활관식사2 = rs.getString("합격생활관식사2");
			if (합격생활관식사1 == null) {
				합격생활관식사1 = "식사안함";
			}
			if (합격생활관식사2 == null) {
				합격생활관식사2 = "식사안함";
			}
			String 입사기간 = rs.getString("입사기간");

			// 입사기간이 한학기인경우의 생활관비와 식비를 가져와 sum 에 더하고 클라이언트에게 보내준다.
			if (입사기간.equals("한학기")) {
				Statement stmt1 = conn.createStatement();
				ResultSet rs1 = stmt1.executeQuery(
						"select 생활관비,식비 from 입사금액 where 입사금액.관구분='" + 합격생활관 + "'and 입사금액.식비구분 = '" + 합격생활관식사1 + "'");
				if (rs1.next()) {
					int sum = rs1.getInt("생활관비") + rs1.getInt("식비");
					protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD28_고지서조회승인);
					String result = rs.getString("대학구분") + "/" + rs.getString("학과명") + "/" + rs.getString("학번") + "/"
							+ rs.getString("성명") + "/" + rs.getString("합격생활관") + "/" + Integer.toString(sum);
					protocol.setString(result);
					output.write(protocol.getPacket());
				} else {
					System.out.println("실패1");
				}
			} else if (입사기간.equals("1년")) { // 입사기간이 1년인경우의 생활관비와 학기식비, 방학식비를 가져와 sum 에 더하고 클라이언트에게 보내준다.
				Statement stmt1 = conn.createStatement();
				System.out.println(합격생활관);
				System.out.println(합격생활관식사1);
				System.out.println(합격생활관식사2);
				ResultSet rs1 = stmt1.executeQuery("SELECT 생활관비, 식비 from 입사금액 where (관구분='" + 합격생활관
						+ "'and 입사금액.신청기간구분='1년'and 입사금액.식비구분='" + 합격생활관식사1 + "')or (관구분='" + 합격생활관
						+ "'and 입사금액.신청기간구분 = '1년(방학)' and 입사금액.식비구분='" + 합격생활관식사2 + "');");

				if (rs1.next()) {
					System.out.println(rs1.getRow());
					int sum = rs1.getInt("생활관비") + rs1.getInt("식비");
					rs1.next();
					sum += rs1.getInt("식비");
					System.out.println(sum);
					protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD28_고지서조회승인);
					String result = rs.getString("대학구분") + "/" + rs.getString("학과명") + "/" + rs.getString("학번") + "/"
							+ rs.getString("성명") + "/" + rs.getString("합격생활관") + "/" + Integer.toString(sum);
					protocol.setString(result);
					output.write(protocol.getPacket());
				}
			}
		} else {// 해당학생의 신청내역이 없으면 조회거절을 한다.
			protocol = new Protocol(Protocol.TYPE8_VIEW_RES, Protocol.T8_CD29_고지서조회거절);
			output.write(protocol.getPacket());
		}
		rs.close();
		stmt.close();
	}
}