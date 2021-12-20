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

            while(!messageToServer.equals("END")){
                // send message to server
                System.out.print("Send to Server: ");
                messageToServer = bufferedReader.readLine();
                dataOutputStream.writeUTF(messageToServer);

                if(messageToServer.equals("ls")){
                    System.out.println("\nList of files:");
                    while(true){
                        String list = dataInputStream.readUTF();
                        if(list.equals("FINISHED")){
                            break;
                        }
                        System.out.println("\t"+list);
                    }
                }

                //read message from client
                messageFromServer = dataInputStream.readUTF();
                System.out.println("From Server: "+ messageFromServer);
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