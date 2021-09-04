import java.io.IOException;
import java.net.Socket;
import java.io.*;

public class server {
    public static void main(String args[]) throws IOException{
        SocketServer ss = new SocketServer(1222);
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        //specify port number
        // ServerSocket s1 = new ServerSocket(1222);
        
        try {
        //accepts incoming requests
        socket = ss.serverSocket.accept();
        //print out what client is sending
        inputStreamReader = new InputStreamReader(socket.getInputStream());
        outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        bufferedWriter = new BufferedWriter(outputStreamWriter);
        while (true) {
            String msgFromClient = bufferedReader.readLine();
            System.out.println("Client " + msgFromClient);
            if (msgFromClient.equalsIgnoreCase("bye")) {
                bufferedWriter.write("Server received: bye. Shutting down...");
                bufferedWriter.newLine();
                bufferedWriter.flush();
                break;
            }
            bufferedWriter.write("Server received: " + msgFromClient);
            bufferedWriter.newLine();
            bufferedWriter.flush(); 
            }
        } finally {
            ss.serverSocket.close();
            socket.close();
            inputStreamReader.close();
            outputStreamWriter.close();
            bufferedReader.close();
            bufferedWriter.close();
        }
    }

    
}