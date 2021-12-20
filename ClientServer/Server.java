import java.io.*;
import java.net.*;

public class Server{
    public static void main(String[] args){
        try{
            //create a server socket and a socket
            ServerSocket serverSocket = new ServerSocket(9000);
            Socket socket = serverSocket.accept();
            
            //create data input,output stream and a buffer
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            //create strings to store and send messages
            String messageFromClient="",messageToClient="";

            //two way message passing
            while(!messageFromClient.equals("END")){
                //read message from client and print
                messageFromClient = dataInputStream.readUTF();
                System.out.println("From Client: "+ messageFromClient);

                //sending the list of files in the directory
                if(messageFromClient.equals("ls")){
                    File file = new File("../ClientServer");
                    File[] files = file.listFiles();
                    for(int i=0;i<files.length;i++){
                        dataOutputStream.writeUTF(files[i].getName());
                    }
                    dataOutputStream.writeUTF("FINISHED");
                }

                //send message to client
                System.out.print("Send to Client: ");
                messageToClient = bufferedReader.readLine();
                dataOutputStream.writeUTF(messageToClient);
            }

            

            //close the socket and data input and output streams
            serverSocket.close();
            dataOutputStream.flush();
            dataOutputStream.close();
            dataInputStream.close();
            
        }catch(Exception e){
            System.out.println(e);
        }

    }
}