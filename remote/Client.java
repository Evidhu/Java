import java.net.*;
import java.io.*;
import java.util.*;

public class Client
{
   public static void main(String [] args)
   {
	Scanner sc=new Scanner(System.in);
      String serverName = args[0];
      int port = Integer.parseInt(args[1]);
      try
      {
         System.out.println("Connecting to " + serverName
                             + " on port " + port);
         
	do{
		Socket client = new Socket(serverName, port);
		 System.out.println("\nEnter the command to execute \nPress Ctrl+c to stop\n");
		 OutputStream outToServer = client.getOutputStream();
		 DataOutputStream out =
		               new DataOutputStream(outToServer);
		String cmd=sc.nextLine();
		 out.writeUTF(cmd);
		 InputStream inFromServer = client.getInputStream();
		 DataInputStream in =
		                new DataInputStream(inFromServer);
		 System.out.println(in.readUTF());
 		client.close();
	}while(true);
        
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}
