import java.io.*;
import java.net.*;

public class Server{
    public static void main(String[] args){
        try{
            //create a server socket and a socket
            ServerSocket serverSocket = new ServerSocket(9000);
            Socket socket = serverSocket.accept();
            
            //wait for message from client and print
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String str = (String)dis.readUTF();
            System.out.println("message="+str);

            //close the socket
            serverSocket.close();
        }catch(Exception e){
            System.out.println(e);
        }

    }
}