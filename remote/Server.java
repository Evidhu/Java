import java.net.*;
import java.io.*;


public class Server extends Thread
{
   private ServerSocket serverSocket;
   
   public Server(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
     
   }

   public void run()
   {
     
	
		while(true){
	String str="";Socket server=null;
DataOutputStream out =null;
         try
         {
		
		System.out.println("Waiting for client on port " +
		serverSocket.getLocalPort() + "...");
		server = serverSocket.accept();
		System.out.println("Just connected to "
			  + server.getRemoteSocketAddress());
		out= new DataOutputStream(server.getOutputStream());
		String[]cmd1 = new String [3]; 
		cmd1[0]= "/bin/sh";
		cmd1[1]= "-c";
		//cmd1[2]= "cd ~/Java; ls";

		Process p =null;//= Runtime.getRuntime().exec(cmd1);
		DataInputStream in = new DataInputStream(server.getInputStream());
		String cmd=in.readUTF();
		//if(cmd.contains("cd ")){
			cmd1[2]=cmd;//+"; ls -l" ;
			 p = Runtime.getRuntime().exec(cmd1);
		//}else{

		//	    p = Runtime.getRuntime().exec(cmd);
		//}
    	 	p.waitFor();
 
          	BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
 
    		
		String line = "";			
    		while ((line = reader.readLine())!= null) {
			//sb.append(line + "\n");
			str+=line + "\n";
    		}
		
         }catch(SocketTimeoutException s)
         {
         	System.out.println("Socket timed out!");
            
         }catch(Exception e)
         {
         	// e.printStackTrace();
		str=e.getMessage();//new String(sb));
           		
		           
         }
	finally{
		try
         	{
			out.writeUTF(str);//new String(sb));
           		server.close();
		}catch(Exception e)
         	{
          		e.printStackTrace();
            
         	}
	}
	}
      
   }
   public static void main(String [] args)
   {
      int port = Integer.parseInt(args[0]);
      try
      {
         Thread t = new Server(port);
         t.start();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}
