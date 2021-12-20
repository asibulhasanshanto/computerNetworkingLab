import java.io.*;  
import java.net.*;  
public class Client{
    public static void main(String[] args){
        try{
            //connect to the socket
            Socket socket = new Socket("localhost",9000);

            //create data input,output stream and a buffer
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            //create strings to store and send messages
            String messageFromServer="",messageToServer="";

            while(!messageFromServer.equals("END")){
                // send message to server
                System.out.print("Send to Server: ");
                messageFromServer = bufferedReader.readLine();
                dataOutputStream.writeUTF(messageFromServer);

                //read message from client
                messageToServer = dataInputStream.readUTF();
                System.out.println("From Server: "+ messageToServer);
            }

            //close the socket
            dataOutputStream.flush();
            dataOutputStream.close();
            socket.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}