import ip.vidhu.ipconfiguration.IpConfiguration;
import ip.vidhu.ipconfiguration.MyNetException;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;



public class MyDevice implements Runnable{
    String address[];
    Thread tsend,tread,tdata;
    String my_port,ur_port;
    PriorityQueue<String>ips=new PriorityQueue<>();
    ArrayList <String[]>cache=new ArrayList<>();
    DataInputStream dis;
    String my_name;
    public MyDevice(String my_port,String ur_port,String my_name) {
    this.my_port=my_port;
    this.ur_port=ur_port;
    this.my_name=my_name;
         try {
            ArrayList<String[]> mac = new IpConfiguration().getAddress();
            Iterator<String[]> itr=mac.iterator();
            while(itr.hasNext()){
                String []address1=itr.next();
		address=new String[]{address1[0],address1[1],my_port,my_name};
                cache.add(address);
               // this.mac=addr[0];
                //ip=addr[1];
                System.out.println("Mac :"+address[0]);
                System.out.println("Ip :"+address[1]);
            }
	    getNodes();
	    dis=new DataInputStream(System.in);
            tsend=new Thread(this);
            tread=new Thread(this);
	    tdata=new Thread(this);
        //    System.out.println(new IpConfiguration().getIPV4Address());
        } catch (MyNetException ex) {
            System.out.println(""+ex);
        }
	
    }

    public void getNodes(){
        String ip[]=address[1].split("\\.");
            int last=Integer.parseInt(ip[3]);
	    String msg=(address[0]+"++"+address[1]+"++"+address[2]+"++"+address[3]);
            for(int i=0;i<256;i++){
                sendData(msg,ip[0]+"."+ip[1]+"."+ip[2]+"."+i, Integer.parseInt(ur_port));
            }
    }
    
    public void receiveData(int port){
        
        try {
            //str="";
            DatagramSocket ds=new DatagramSocket(port);
            byte data[];
            DatagramPacket dp=new DatagramPacket(new byte[255], 255);
            ds.receive(dp);
            data=dp.getData();
            String st[]=new String(data).trim().split("\\+\\+");
	    if(st.length>0){
            cache.add(st);
            
		System.out.println("new node mac: "+st[0]);
                System.out.println("new node ip : "+st[1]);
		System.out.println("new node port : "+st[2]);
                ips.add(st[1]);
	    }
	    st=new String(data).trim().split("::");
            if(st.length>0){
           
		//System.out.println("new node mac: "+st[0]);
                System.out.println("data to : "+st[1]);
		if(st[1].equals(address[3])){
			System.out.println("message is: "+st[0]);
		}
	    }
            ds.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        
    public void sendData(String msg,String serip,int port){
        try {
            DatagramSocket ds=new DatagramSocket();
            DatagramPacket dp;
            byte a[]=new byte[255];
         	
                a=msg.getBytes();
		//System.out.println(new String(a));
                dp=new DatagramPacket(a,a.length,InetAddress.getByName(serip),port);
                ds.send(dp);
         
                
        } catch (SocketException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
	 ex.printStackTrace();
        } 
    }
    
    public static void main(String[] args) {
        System.out.println("Client");
        MyDevice sc=new MyDevice(args[0],args[1],args[2]);
        sc.tsend.start();
         sc.tread.start();
	sc.tdata.start();
    }

    @Override
    public void run() {
        if(Thread.currentThread()==tsend){
            while(true){
               
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                   ex.printStackTrace();
                }
 		receiveData(Integer.parseInt(my_port));
            }
        }else if(Thread.currentThread()==tread){
            while(true){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
               // System.out.println("ips"+ips.size());
                if(ips.size()>0){
		     String msg=(address[0]+"++"+address[1]+"++"+address[2]+"++"+address[3]);
                    sendData(msg,ips.poll(), Integer.parseInt(ur_port));
                }
            }
        }else if(Thread.currentThread()==tdata){
            while(true){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
               // System.out.println("ips"+ips.size());
		try{
			String msg=readData();
		        if(ips.size()>0){
			     //String msg=(address[0]+"++"+address[1]+"++"+address[2]+"++"+address[4]);
		            sendData(msg,ips.poll(), Integer.parseInt(ur_port));
		        }
		} catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public String readData()throws IOException{
	dis.readLine();
	System.out.println("Enter the message to send");
	String msg=dis.readLine();
	System.out.println("Enter node to connect");
	msg+="::"+dis.readLine();
	return msg;
    }
}

