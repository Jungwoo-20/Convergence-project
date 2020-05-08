package 서버;

import java.nio.ByteBuffer;

public class Protocol {

   public static final int UNDEFINED = -1;
   public static final int EXIT = 0;

   // 타입
   public static final int TYPE1_LOGIN_REQ = 1;
   public static final int TYPE2_LOGIN_RES = 2;
   public static final int TYPE3_FILE_SEND_REQ = 3;
   public static final int TYPE4_FILE_SEND_RES = 4;
   public static final int TYPE5_FILE_SEND = 5;
   public static final int TYPE6_FILE_RES = 6;
   public static final int TYPE7_VIEW_REQ = 7;
   public static final int TYPE8_VIEW_RES = 8;
   public static final int TYPE9_RENEW_REQ = 9;
   public static final int TYPE10_RENEW_RES = 10;
   public static final int TYPE11_FILE_RECEIVE_REQ = 11;

   // 타입1의코드 없음
   // 타입2의코드
   public static final int T2_CD0_STUDENT = 0;
   public static final int T2_CD1_ADMIN = 1;
   public static final int T2_CD2_FAIL = 2;
   public static final int T2_CD3_5FAIL = 3;
   // 타입3의코드
   public static final int T3_CD0_GYEOLHAEK = 0;
   public static final int T3_CD1_IPSASEOYAKSEO = 1;
   // 타입4의코드
   public static final int T4_CD0_ACCEPT = 0;
   public static final int T4_CD1_DECLINE = 1;
   // 타입5의코드
   public static final int T5_CD0_GYEOLHAEK = 0;
   public static final int T5_CD1_IPSASEOYAKSEO = 1;
   // 타입6의코드
   public static final int T6_CD0_SUCCESS = 0;
   public static final int T6_CD1_FAIL = 1;
   // 타입7의코드
   public static final int T7_CD0_학적내역조회요청 = 0;
   public static final int T7_CD1_생활관비조회요청 = 1;
   public static final int T7_CD2_호실조회요청 = 2;
   public static final int T7_CD3_입사지원내역조회요청 = 3;
   public static final int T7_CD4_선발결과조회요청 = 4;
   public static final int T7_CD5_해당결핵진단서존재여부조회요청 = 5;
   public static final int T7_CD6_입사신청자세부내역조회요청 = 6;
   public static final int T7_CD7_입사신청자조회요청 = 7;
   public static final int T7_CD8_입사선발자조회요청 = 8;
   public static final int T7_CD9_입사자조회요청 = 9;
   public static final int T7_CD10_결핵진단서제출현황조회요청 = 10;
   public static final int T7_CD11_사용료조회 = 11;
   public static final int T7_CD12_급식비조회 = 12;
   public static final int T7_CD13_선발일정조회 = 13;
   public static final int T7_CD14_고지서조회요청 = 14;

   // 타입8의코드
   public static final int T8_CD0_학적내역조회승인 = 0;
   public static final int T8_CD1_학적내역조회거절 = 1;
   public static final int T8_CD2_생활관비조회승인 = 2;
   public static final int T8_CD3_생활관비조회거절 = 3;
   public static final int T8_CD4_호실조회승인 = 4;
   public static final int T8_CD5_호실조회거절 = 5;
   public static final int T8_CD6_입사지원내역조회승인 = 6;
   public static final int T8_CD7_입사지원내역조회거절 = 7;
   public static final int T8_CD8_선발결과조회승인 = 8;
   public static final int T8_CD9_선발결과조회거절 = 9;
   public static final int T8_CD10_해당결핵진단서존재 = 10;
   public static final int T8_CD11_해당결핵진단서존재하지않음 = 11;
   public static final int T8_CD12_입사신청자세부내역조회승인 = 12;
   public static final int T8_CD13_입사신청자세부내역조회거절 = 13;
   public static final int T8_CD14_입사신청자조회승인 = 14;
   public static final int T8_CD15_입사신청자조회거절 = 15;
   public static final int T8_CD16_입사선발자조회승인 = 16;
   public static final int T8_CD17_입사선발자조회거절 = 17;
   public static final int T8_CD18_입사자조회승인 = 18;
   public static final int T8_CD19_입사자조회거절 = 19;
   public static final int T8_CD20_결핵진단서제출현황조회승인 = 20;
   public static final int T8_CD21_결핵진단서제출현황조회거절 = 21;
   public static final int T8_CD22_사용료조회승인 = 22;
   public static final int T8_CD23_사용료조회거절 = 23;
   public static final int T8_CD24_급식비조회승인 = 24;
   public static final int T8_CD25_급식비조회거절 = 25;
   public static final int T8_CD26_선발일정조회승인 = 26;
   public static final int T8_CD27_선발일정조회거절 = 27;
   public static final int T8_CD28_고지서조회승인 = 28;
   public static final int T8_CD29_고지서조회거절 = 29;
   // 타입9의코드
   public static final int T9_CD0_입사신청요청 = 0;
   public static final int T9_CD1_입사신청자를입사선발자로등록요청 = 1;
   public static final int T9_CD2_입사선발자를입사자로등록요청 = 2;
   public static final int T9_CD3_진단일시등록요청 = 3;
   public static final int T9_CD4_입사신청자를목록에서삭제요청 = 4;
   public static final int T9_CD5_입사선발자를목록에서삭제요청 = 5;
   public static final int T9_CD6_입사자를목록에서삭제요청 = 6;
   public static final int T9_CD7_진단일시삭제요청 = 7;
   public static final int T9_CD8_선발일정등록요청 = 8;
   public static final int T9_CD9_사용료등록요청 = 9;
   public static final int T9_CD10_급식비등록요청 = 10;
   public static final int T9_CD11_납부 = 11;
   // 타입10의코드
   public static final int T10_CD0_입사신청성공 = 0;
   public static final int T10_CD1_입사신청실패 = 1;
   public static final int T10_CD2_입사신청자를입사선발자로등록성공 = 2;
   public static final int T10_CD3_입사신청자를입사선발자로등록실패 = 3;
   public static final int T10_CD4_입사선발자를입사자로등록성공 = 4;
   public static final int T10_CD5_입사선발자를입사자로등록실패 = 5;
   public static final int T10_CD6_진단일시등록성공 = 6;
   public static final int T10_CD7_진단일시등록실패 = 7;
   public static final int T10_CD8_입사신청자를목록에서삭제성공 = 8;
   public static final int T10_CD9_입사신청자를목록에서삭제실패 = 9;
   public static final int T10_CD10_입사선발자를목록에서삭제성공 = 10;
   public static final int T10_CD11_입사선발자를목록에서삭제실패 = 11;
   public static final int T10_CD12_입사자를목록에서삭제성공 = 12;
   public static final int T10_CD13_입사자를목록에서삭제실패 = 13;
   public static final int T10_CD14_진단일시삭제성공 = 14;
   public static final int T10_CD15_진단일시삭제실패 = 15;
   public static final int T10_CD16_선발일정등록성공 = 16;
   public static final int T10_CD17_선발일정등록실패 = 17;
   public static final int T10_CD18_사용료등록성공 = 18;
   public static final int T10_CD19_사용료등록실패 = 19;
   public static final int T10_CD20_급식비등록성공 = 20;
   public static final int T10_CD21_급식비등록실패 = 21;
   public static final int T10_CD22_납부성공 = 22;
   public static final int T10_CD23_납부실패 = 23;

   // 타입11의코드
   public static final int T11_CD0_GYEOLHAEK = 0;
   public static final int T11_CD1_IPSASEOYAKSEO = 1;

   // 랭스
   public static final int LEN_HEADER = 8;
   public static final int LEN_FILE = 10000;

   // ==========================================================================================================
   // 멤버변수
   public byte[] header;
   public byte[] body;

   // 생성자
   // 생성하면 타입, 코드, 바디랭스, flag, last, seq_num을 저장하는 byte[8]짜리배열을 만든다.
   public Protocol() {
      header = new byte[LEN_HEADER];
   }

   public Protocol(int protocolType) {
      this();
      header[0] = (byte) protocolType;
   }

   public Protocol(int protocolType, int protocolCode) {
      this(protocolType);
      header[1] = (byte) protocolCode;
   }

   //데이터의 길이만큼 배열을 생성하고, 헤더의 바디랭스를 데이터의길이로 바꿔줌, 데이터를 body에 복사.
   public Protocol(int protocolType, int protocolCode, String data) {
      this(protocolType, protocolCode);
      body = new byte[data.getBytes().length];
      intToByte(data.getBytes().length, header, 2);
      System.arraycopy(data.getBytes(), 0, body, 0, data.getBytes().length);
   }

   //파일 데이터의 길이만큼 배열을 생성하고, 헤더의 바디랭스를 데이터의길이로 바꿔줌, 데이터를 body에 복사.
   public Protocol(int protocolType, int protocolCode, byte[] file) {
      this(protocolType, protocolCode);
      body = new byte[file.length];
      intToByte(file.length, header, 2);
      System.arraycopy(file, 0, body, 0, file.length);
   }

   public Protocol(int protocolType, int protocolCode, int flag, int last, int seqnum, String data) {
      this(protocolType, protocolCode, data);
      header[4] = (byte) flag;
      header[5] = (byte) last;
      intToByte(seqnum, header, 6);
   }

   public Protocol(int protocolType, int protocolCode, int flag, int last, int seqnum, byte[] file) {
      this(protocolType, protocolCode, file);
      header[4] = (byte) flag;
      header[5] = (byte) last;
      intToByte(seqnum, header, 6);
   }

   //int bd값을 header의 바디랭스와 시퀀스넘버에 넣을때 사용하는 int->byte[]변환함수
   public void intToByte(int bd, byte[] header, int offset) {
      short body = (short) bd;
      byte[] temp = ByteBuffer.allocate(2).putShort(body).array();
      for (int i = 0; i < 2; i++)
         header[offset + i] = temp[i];
   }
   //헤더의 바디랭스와 시퀀스넘버의 값을 byte[]->int로 변환해주는함수
   public int byteToInt(byte[] header, int offset) {
      int result = 0;
      result = (header[offset] & 0xff) << 8 | (header[offset + 1] & 0xff);
      return result;
   }

   // Type get&set
   public int getType() {
      return (int) header[0];
   }
   public void setType(int Type) {
      header[0] = (byte) Type;
   }

   // Code get&set
   public int getCode() {
      return (int) header[1];
   }
   public void setCode(int Code) {
      header[1] = (byte) Code;
   }

   // length get&set
   public int getLength() {
      return byteToInt(header, 2);
   }
   public void setLength(int Length) {
      intToByte(Length, header, 2);
   }

   // flag get&set
   public int getFlag() {
      return (int) header[4];
   }
   public void setFlag(int Flag) {
      header[4] = (byte) Flag;
   }

   // last get&set
   public int getLast() {
      return (int) header[5];
   }
   public void setLast(int Last) {
      header[5] = (byte) Last;
   }

   // seq get&set
   public int getSeqnum() {
      return byteToInt(header, 6);
   }
   public void setSeqnum(int Seqnum) {
      intToByte(Seqnum, header, 6);
   }

   // Packet get&set
   public byte[] getPacket() {
      if (body != null) {
         byte[] temp = new byte[header.length + body.length];
         System.arraycopy(header, 0, temp, 0, LEN_HEADER);
         System.arraycopy(body, 0, temp, LEN_HEADER, body.length);
         return temp;
      } else {
         return header;
      }
   }
   //헤더와 바디를 set
   public void setPacket(byte[] header, byte[] body) {
      this.header = header;
      this.body = body;
      intToByte(body.length, header, 2);
   }

   // header get&set
   public byte[] getHeader() {
      return header;
   }
   public void setHeader(byte[] header) {
      this.header = header;
   }

   // string get&set - String값을 byte[]로 변환해서 body에 넣는다. byte[]값을 String으로 변환해서 리턴한다.
   public String getString() {
      return new String(body);
   }
   public void setString(String data) {
      body = new byte[data.getBytes().length];
      System.arraycopy(data.getBytes(), 0, body, 0, data.getBytes().length);
      intToByte(data.getBytes().length, header, 2);
   }

   // 파일전송을 위한 get set File , byte[]타입으로 set get 을 한다.
   public byte[] getFile() {
      return body;
   }
   public void setFile(byte[] file) {
      body = file;
      intToByte(file.length, header, 2);
   }
}