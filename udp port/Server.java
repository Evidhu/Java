import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.PriorityQueue;

public class Server implements Runnable{
    String str;
	InetAddress ip;
Thread t1,t2;
 PriorityQueue<MyClass> que=new PriorityQueue<MyClass>();
int port;
	public Server(int port){
		t1=new Thread(this);
		t2=new Thread(this);
this.port=port;
	}

	public void run(){
		while(true){
			
			if(Thread.currentThread()==t1){
				receiveData(port);
			}else{
				try{
					Thread.sleep(1000);
				}catch(Exception ex){
					System.out.println(ex);			
				}
				sendData();
			}
			
		}
	}
   
    public void  receiveData(int port){
 	int[][] a,b;
    int m,n,p,q;
   String str="";
        try {
            DatagramSocket ds=new DatagramSocket(port);
            byte data[]=new byte[255];
                DatagramPacket dp=new DatagramPacket(new byte[255], 255);
                ds.receive(dp);
		
                data=dp.getData();
		
                str=new String(data).trim();
		if(str.length()>0){
                System.out.println("got : '"+str.trim()+"'");
                String st[]=str.split(":");
                m=Integer.parseInt(st[0]);
                n=Integer.parseInt(st[1]);
                a=new int[m][n];
                for(int i=0;i<m;i++){
                    for(int j=0;j<n;j++){
                        a[i][j]=Integer.parseInt(st[i*m+j+2]);
                    }
                }
                p=Integer.parseInt(st[m*n+2]);
                q=Integer.parseInt(st[m*n+3]);
                System.out.println("p="+p+" q="+q);
                b=new int[p][q];
                for(int i=0;i<p;i++){
                    for(int j=0;j<q;j++){
                        b[i][j]=Integer.parseInt(st[i*p+j+m*n+4]);
                        System.out.println(""+b[i][j]);
                    }
                }
                String portc=st[m*n+p*q+4];
		ip=dp.getAddress();
		System.out.println(ip.toString());
                que.add(new MyClass(m, n, p, q, a, b,portc,ip));
              //  mprint(a);
              //  mprint(b);    
		}            
                ds.close();
        } catch (SocketException ex) {
           System.out.println(ex.getMessage());
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }
    
     public int[][] multiply(int[][] m1, int[][] m2){
      int m1rows = m1.length;
      int m1cols = m1[0].length;
      int m2rows = m2.length;
      int m2cols = m2[0].length;
      if (m1cols != m2rows){
        throw new IllegalArgumentException("matrices don't match: "+ m1cols + " != " + m2rows);		
      }
    else{ 
         int[][] result = new int[m1rows][m2cols];
         for (int i=0; i< m1rows; i++){
            for (int j=0; j< m2cols; j++){
               for (int k=0; k< m1cols; k++){
                  result[i][j] += m1[i][k] * m2[k][j];
                 
               }
            }
         }
  
          return result;
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

     public void sendData(){
        try {
	   if(que.size()>=1){
		System.out.println("Load on Server :"+que.size());
		MyClass mc=que.poll();
            	DatagramSocket ds=new DatagramSocket();
            	DataInputStream dis=new DataInputStream(System.in);
            	byte data[]=new byte[255];
            	try{
			int m=mc.m;
			int n=mc.n;
			int p=mc.p;
			int q=mc.q;
			int[][]a=mc.a;
			int[][]b=mc.b;
			int port=Integer.parseInt(mc.port);
			InetAddress ip=mc.ip;
		        int[][]c=multiply(a,b);
		       //  mprint(c);
		         str=n+":"+p;
		         for(int i=0;i<n;i++){
		             for(int j=0;j<p;j++)
		                 str+=":"+c[i][j];
		         }
                 
                }catch(IllegalArgumentException e){
                    System.out.println(e);
                }
               
                data=str.getBytes();
                DatagramPacket dp=new DatagramPacket(data,data.length,ip,port);
                ds.send(dp);
	    }

        } catch (SocketException ex) {
             System.out.println(ex.getMessage());
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        } 
    }

    public static void main(String[] args) {
        int port=Integer.parseInt(args[0]);
        Server ser=new Server(port);
 	ser.t1.start();
	ser.t2.start();

	 }
}
