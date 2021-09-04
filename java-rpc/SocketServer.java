import java.net.ServerSocket;
import java.io.*;

public class SocketServer {
    ServerSocket serverSocket;
    public SocketServer(int portNumber) throws IOException {
        serverSocket = new ServerSocket(portNumber);
    }
}
