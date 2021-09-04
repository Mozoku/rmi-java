import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.UnknownHostException;
import java.io.*;

public class client implements ActionListener{
    private static int index = 0;
    static String[] data = new String[]{"number", "name", "faculty", "course", "a thanks", "bye", ""};
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private static JLabel serverLabel;
    private static JTextField userText;

    static Scanner scanner = new Scanner(System.in);
    static InputStreamReader inputStreamReader = null;
    static OutputStreamWriter outputStreamWriter = null;
    static BufferedReader bufferedReader = null;
    static BufferedWriter bufferedWriter = null;
    
    public client() {
        frame = new JFrame();
        panel = new JPanel();
        JButton button = new JButton("Next Q");
        button.setBounds(10, 50, 100, 25);
        button.addActionListener(this);
        panel.add(button);

        label = new JLabel("Press button to start");
        label.setBounds(10, 20, 165, 25);
        panel.add(label);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        serverLabel = new JLabel("From the server");
        serverLabel.setBounds(10, 80, 250, 25);
        panel.add(serverLabel);

        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(null);
        
        frame.setSize(350, 200);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Questionos for you");
        frame.pack();
        frame.setVisible(true);

    }
    public static void main(String args[]) throws UnknownHostException, IOException{
        SocketClient sc = new SocketClient("localhost", 1222);
        
       
        scanner = new Scanner(System.in);
        inputStreamReader = null;
        outputStreamWriter = null;
        bufferedReader = null;
        bufferedWriter = null;
        new client();
        try {
        //accept the result from the server
        inputStreamReader = new InputStreamReader(sc.s.getInputStream());
        outputStreamWriter = new OutputStreamWriter(sc.s.getOutputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        bufferedWriter = new BufferedWriter(outputStreamWriter);
        //to allow us to take keyboard input
        //Scanner scanner1 = new Scanner(sc.s.getInputStream());
        while (index <= data.length) {
           //keep alive before reaching finnally block
        }
        } finally {
            scanner.close();
            sc.s.close();
            inputStreamReader.close();
            outputStreamWriter.close();
            bufferedReader.close();
            bufferedWriter.close();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) { 
        label.setText("Enter " + data[index]);
        String msgToSend = userText.getText();
        try {
            bufferedWriter.write(msgToSend);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            serverLabel.setText(bufferedReader.readLine());
        } catch (IOException e1) {
            e1.printStackTrace();
        } 
        index++;
    }
    
}
