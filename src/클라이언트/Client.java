package 클라이언트;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("localhost", 5000);
		OutputStream output = socket.getOutputStream();
		InputStream input = socket.getInputStream();
		Login lg = new Login(socket, input, output);
	}
}
