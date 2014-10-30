import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    DatagramPacket dp;
    String str="";
    String serip;
    public Client(String ip){
        serip=ip;
    }
    public void sendData(){
        try {
            DatagramSocket ds=new DatagramSocket();
          
            byte a[]=new byte[255];
         
                a=str.getBytes();
                dp=new DatagramPacket(a,a.length,InetAddress.getLocalHost(),8000);
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
                System.out.println("got : '"+str.trim()+"'");
            
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
            cli.readMatrix();
            cli.sendData();
            cli.receiveData(8001);
        }
    }

    private void readMatrix() {
        try {
            DataInputStream dis=new DataInputStream(System.in);
            System.out.println("enter number of rows of first matrix");
            int m=Integer.parseInt(dis.readLine());
            str+=m+":";
            System.out.println("enter number of columns of first matrix");
            int n=Integer.parseInt(dis.readLine());
            str+=n+":";
            System.out.println("enter the elements of first matrix");
            for(int i=0;i<m;i++)
                for(int j=0;j<n;j++)
                    str+=dis.readLine()+":";
            
            System.out.println("enter number of rows of 2nd matrix");
            int p=Integer.parseInt(dis.readLine());
            str+=p+":";
            System.out.println("enter number of columns of 2nd matrix");
            int q=Integer.parseInt(dis.readLine());
            str+=q+":";
            System.out.println("enter the elements of 2nd matrix");
            for(int i=0;i<p;i++)
                for(int j=0;j<q;j++)
                    str+=dis.readLine()+":";
            //str+=new IpConfiguration().getIPV4Address();
        }  catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
