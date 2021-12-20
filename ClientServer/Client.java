import java.io.*;  
import java.net.*;  
public class Client{
    public static void main(String[] args){
        try{
            //connect to the socket
            Socket socket = new Socket("localhost",9000);

            //write to server
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            dout.writeUTF("Hello server");
            dout.flush();
            dout.close();

            //close the socket
            socket.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}