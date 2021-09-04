import java.net.Socket;
import java.io.IOException;

public class SocketClient {
    Socket s;

    public SocketClient(String host, int newPort) throws IOException{
        s = new Socket("localhost", 1222);
    }
    
}
