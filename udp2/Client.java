import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Random;

public class Client {
    DatagramPacket dp;
DataInputStream dis;
    String str="";
    String serip;
    public Client(String ip){
        serip=ip;
    }
    public void sendData(int port){
        try {
            DatagramSocket ds=new DatagramSocket();
          
            byte a[]=new byte[255];
         
                a=str.getBytes();
                dp=new DatagramPacket(a,a.length,InetAddress.getByName(serip),port);
                ds.send(dp);
         
                
        } catch (SocketException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            
            System.out.println(ex);
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
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
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

	

	private int readInt(){
		do{
		try{
			 int m=Integer.parseInt(dis.readLine());
			return m;
		}catch(NumberFormatException ex){
			System.out.println("Please Enter a number");
		}catch (IOException ex) {
		    System.out.println(ex);
		}
		}while(true);
	}
    private void readMatrix() {
       	str="";
            dis=new DataInputStream(System.in);
            System.out.println("enter number of rows of first matrix");
            int m=readInt();//Integer.parseInt(dis.readLine());
            str+=m+":";
            System.out.println("enter number of columns of first matrix");
            int n=readInt();//Integer.parseInt(dis.readLine());
            str+=n+":";
            System.out.println("enter the elements of first matrix");
            for(int i=0;i<m;i++)
                for(int j=0;j<n;j++)
                    str+=readInt()+":";
            
            System.out.println("enter number of rows of 2nd matrix");
            int p=readInt();
            str+=p+":";
            System.out.println("enter number of columns of 2nd matrix");
            int q=readInt();
            str+=q+":";
            System.out.println("enter the elements of 2nd matrix");
            for(int i=0;i<p;i++)
                for(int j=0;j<q;j++)
                    str+=readInt()+":";
           
    }
    

	/*for running this project 
	 *
	 *java Client serverip client port server1port server2port
	 *
	 *java Client 127.0.0.1 8002 8000 8001
	 *
	*/
    public static void main(String[] args) {
        
        Client cli=new Client(args[0]);
        while(true){

	    Random r = new Random();
	    int fourDigit = 1000 + r.nextInt(100000);
	    System.out.println(fourDigit);
	    
            cli.readMatrix();
	    cli.str+=args[1];
            cli.sendData(Integer.parseInt(args[2+fourDigit%2]));
            cli.receiveData(Integer.parseInt(args[1]));
        }
    }
    
}
