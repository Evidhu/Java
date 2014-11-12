import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

public class Client {
    DatagramPacket dp;
    String str="";
    String serip;

    int[][] fir,sec;
    int m,n,p,q;
    public Client(String ip){
        serip=ip;
    }
    public void sendData(int port){
        try {
            DatagramSocket ds=new DatagramSocket();
          
            //byte a[]=new byte[255];

		  MyClass C1= new MyClass(m,n,p,q,fir,sec);
		  ByteArrayOutputStream baos = new ByteArrayOutputStream();
		  ObjectOutputStream oos = new ObjectOutputStream(baos);
		  oos.writeObject(C1);
		  oos.flush();
		  byte[] a= baos.toByteArray();
		  //packet = new DatagramPacket(Buf, Buf.length, client, port);
		 // socket.send(packet);
         
                //a=str.getBytes();
                dp=new DatagramPacket(a,a.length,InetAddress.getByName(serip),port);
                ds.send(dp);
         
                
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
      public void receiveData(int port){
        try {
            DatagramSocket ds=new DatagramSocket(port);
            byte data[]=new byte[255];
            String str;
         
                DatagramPacket dp=new DatagramPacket(new byte[255], 255);
                ds.receive(dp);
                
                data=dp.getData();
                str=new String(data);
                
		String st[]=str.trim().split(":");
		if(st.length>1){
			int m=Integer.parseInt(st[0]);
		        int n=Integer.parseInt(st[1]);
		        int a[][]=new int[m][n];
			int k=2;
		        for(int i=0;i<m;i++){
		            for(int j=0;j<n;j++){
		                a[i][j]=Integer.parseInt(st[k++]);
		            }
		        }
			mprint(a);
		}else{
			System.out.println("'"+str.trim()+"'");
		        
            	}
                ds.close();
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        
        Client cli=new Client(args[0]);
        while(true){

	    Random r = new Random();
	    int fourDigit = 1000 + r.nextInt(100000);
	    System.out.println(fourDigit);
	    
            cli.readMatrix();
            cli.sendData(Integer.parseInt(args[1+fourDigit%2]));
            cli.receiveData(8001);
        }
    }
	
 public void mprint(int[][] a){
      int rows = a.length;
      int cols = a[0].length;
      System.out.println("array["+rows+"]["+cols+"] = {");
      for (int i=0; i< rows; i++){
         System.out.print("{");
         for (int j=0; j< cols; j++)
            System.out.print(" " + a[i][j] + ",");
         System.out.println("},");
         
      }
      System.out.println(":;");
   }   
    private void readMatrix() {
        try {
	str="";
            DataInputStream dis=new DataInputStream(System.in);
            System.out.println("enter number of rows of first matrix");
            m=Integer.parseInt(dis.readLine());
            //str+=m+":";
            System.out.println("enter number of columns of first matrix");
            n=Integer.parseInt(dis.readLine());
            //str+=n+":";
	    fir=new int[m][n];
            System.out.println("enter the elements of first matrix");
            for(int i=0;i<m;i++)
                for(int j=0;j<n;j++)
			fir[i][j]=Integer.parseInt(dis.readLine());
                    //str+=dis.readLine()+":";
            
            System.out.println("enter number of rows of 2nd matrix");
            p=Integer.parseInt(dis.readLine());
            //str+=p+":";
            System.out.println("enter number of columns of 2nd matrix");
            q=Integer.parseInt(dis.readLine());
            //str+=q+":";
	     sec=new int[p][q];
            System.out.println("enter the elements of 2nd matrix");
            for(int i=0;i<p;i++)
                for(int j=0;j<q;j++)
			sec[i][j]=Integer.parseInt(dis.readLine());
                    //str+=dis.readLine()+":";
           
        }  catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
