import ip.vidhu.ipconfiguration.IpConfiguration;
import ip.vidhu.ipconfiguration.MyNetException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
public class Device extends JFrame implements Runnable,ActionListener{

    private JButton btn_brow_switch;
    private JButton btn_con_swi;
    private JButton btn_send;
    private JButton btn_start;
    private JComboBox jComboBox1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private JLabel lbl_myport;
    private JLabel lbl_urport;
    private JLabel lbl_swport;
    private JTextField txt_myip;
    private JTextField txt_mymac;
    private JTextField txt_myport;
    private JTextField txt_urport;
    private JTextField txt_swport;
    private JTextField txt_urip;
    private JTextField txt_urmac;
       
    ArrayList<String[]> cache=new ArrayList<>();
    String address[];

	String myport,myip,mymac,switchip,switchport,urport,urip,urmac;
        Thread t1;
   
    public Device() {
        try {
            makeFrame();
            t1=new Thread(this);
            switchs=new ArrayList();
            ArrayList<String[]> mac = new IpConfiguration().getAddress();
            Iterator<String[]> itr=mac.iterator();
            while(itr.hasNext()){
                address=itr.next();
              //  cache.add(address);
                // this.mac=addr[0];
                //ip=addr[1];
                txt_mymac.setText(address[0]);
                 txt_myip.setText(address[1]);
            }
        } catch (MyNetException ex) {
           ex.printStackTrace();
        }
        //t1.start();
    }

    private void makeFrame() {

        jPanel1 = new JPanel();
        txt_myport = new JTextField();
        lbl_myport = new JLabel();
        jLabel1 = new JLabel();
        txt_myip = new JTextField();
        jLabel2 = new JLabel();
        txt_mymac = new JTextField();
        jLabel3 = new JLabel();
        jPanel2 = new JPanel();
        jLabel4 = new JLabel();
        lbl_urport = new JLabel();
        txt_urport = new JTextField();
        lbl_swport = new JLabel();
        txt_swport = new JTextField();
        jLabel5 = new JLabel();
        txt_urip = new JTextField();
        jLabel6 = new JLabel();
        txt_urmac = new JTextField();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        btn_send = new JButton();
        jLabel9 = new JLabel();
        jPanel3 = new JPanel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jComboBox1 = new JComboBox();
        btn_brow_switch = new JButton();
        btn_con_swi = new JButton();
        btn_start = new JButton();
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Device");
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(600, 500));
        setPreferredSize(new java.awt.Dimension(600, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(null);

        txt_myport.setHorizontalAlignment(JTextField.CENTER);
        txt_myport.setText("8000");
        jPanel1.add(txt_myport);
        txt_myport.setBounds(90, 60, 50, 27);

        lbl_myport.setText("Port");
        jPanel1.add(lbl_myport);
        lbl_myport.setBounds(40, 60, 50, 30);

        jLabel1.setText("IP");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(170, 60, 20, 30);

        txt_myip.setEditable(false);
        txt_myip.setHorizontalAlignment(JTextField.CENTER);
        jPanel1.add(txt_myip);
        txt_myip.setBounds(200, 60, 110, 27);

        jLabel2.setText("HardWare");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(340, 60, 80, 30);

        txt_mymac.setEditable(false);
        txt_mymac.setHorizontalAlignment(JTextField.CENTER);
        jPanel1.add(txt_mymac);
        txt_mymac.setBounds(420, 60, 130, 27);

        jLabel3.setText("My Device");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 20, 120, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 10, 580, 120);

        jPanel2.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(null);

        jLabel4.setText("Other Device");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(20, 20, 100, 20);

        lbl_urport.setText("Port");
        jPanel2.add(lbl_urport);
        lbl_urport.setBounds(40, 60, 50, 30);

        txt_urport.setHorizontalAlignment(JTextField.CENTER);
        txt_urport.setText("8000");
        jPanel2.add(txt_urport);
        txt_urport.setBounds(90, 60, 50, 27);

        jLabel5.setText("IP");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(170, 60, 20, 30);

        txt_urip.setHorizontalAlignment(JTextField.CENTER);
        jPanel2.add(txt_urip);
        txt_urip.setBounds(200, 60, 110, 27);

        jLabel6.setText("HardWare");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(340, 60, 80, 30);

        txt_urmac.setHorizontalAlignment(JTextField.CENTER);
        jPanel2.add(txt_urmac);
        txt_urmac.setBounds(420, 60, 130, 27);
	txt_urmac.setText("ff:ff:ff:ff:ff:ff");
	txt_urmac.setEditable(false);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(150, 100, 300, 130);

        btn_send.setText("Send");
        jPanel2.add(btn_send);
        btn_send.setBounds(480, 150, 70, 29);
        btn_send.addActionListener(this);

        jLabel9.setText("Message");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(40, 140, 90, 30);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(10, 240, 580, 250);

        jPanel3.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(null);

        jLabel7.setText("Switch");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(13, 13, 47, 17);

        jLabel8.setText("Connect to");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(40, 40, 80, 20);

        //jComboBox1.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setToolTipText("");
        jPanel3.add(jComboBox1);
        jComboBox1.setBounds(120, 40, 220, 27);

        btn_brow_switch.setText("Browse");
        jPanel3.add(btn_brow_switch);
        btn_brow_switch.setBounds(350, 40, 90, 29);
        btn_brow_switch.addActionListener(this);

        btn_con_swi.setText("Connect");
        jPanel3.add(btn_con_swi);
        btn_con_swi.setBounds(450, 40, 100, 29);
        btn_con_swi.addActionListener(this);
        
        txt_swport.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_swport.setText("8002");
        jPanel3.add(txt_swport);
        txt_swport.setBounds(140, 10, 50, 27);

        lbl_swport.setText("Port");
        jPanel3.add(lbl_swport);
        lbl_swport.setBounds(90, 10, 50, 30);
        
        btn_start.setText("Start");
        jPanel1.add(btn_start);
        btn_start.setBounds(130, 20, 90, 29);
        btn_start.addActionListener(this);
        
        getContentPane().add(jPanel3);
        jPanel3.setBounds(10, 140, 580, 90);

    }
    public static void main(String args[]) {
           
        new Device().setVisible(true);
           
    }
   
    public void sendData(String msg,String ip){
	try {
	    DatagramSocket dss=new DatagramSocket();
	    DatagramPacket dps;
	    byte a[]=new byte[255];
	 	
		a=(msg).getBytes();
		//System.out.println(new String(a));
		dps=new DatagramPacket(a,a.length,InetAddress.getByName(ip),Integer.parseInt(txt_swport.getText()));
		dss.send(dps);
	 
		
	} catch (SocketException ex) {
	    System.out.println(ex);
	} catch (IOException ex) {
	    System.out.println(ex);
	 ex.printStackTrace();
	}
    }
    
   ArrayList switchs;
    public void run() {
	if(Thread.currentThread()==t1){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
            }
        while(true){
		try {
		    //str="";
		    DatagramSocket ds=new DatagramSocket(Integer.parseInt(txt_myport.getText()));
		    byte data[];
		    DatagramPacket dp=new DatagramPacket(new byte[255], 255);
		    ds.receive(dp);
		    data=dp.getData();
                    String rec=new String(data).trim();
		    System.out.println(rec);
		
		    String st[]=rec.split("::");
		    if(st.length>1){
                        if(!switchs.contains(st[0]))
                            switchs.add(st[0]);
		    	//cache.add(st);
		    	//System.out.println("new node mac: "+st[0]);
		        //System.out.println("new node ip : "+st[1]);
			//System.out.println("new node port : "+st[2]);
		        //ips.add(st[1]);
                        String sws[]=new String[switchs.size()+1];
                        Iterator it=switchs.iterator();
                        int i=0;
                        sws[i++]="Select Switch";
                        while(it.hasNext()){
                            sws[i++]=(String)it.next();
                        }
                        jComboBox1.setModel(new DefaultComboBoxModel(sws));
		    }
                     st=rec.split(">>");
		    if(st.length==6){
                        txt_urmac.setText("ff:ff:ff:ff:ff:ff");
                        cache.add(new String[]{st[0],st[1],st[4]});
//                        String msg=mymac+">>"+myip+">>"+urmac+">>"+urip+">>"+myport+">>"+urport+">>"+jTextArea1.getText();
//                        sendData(msg,switchip);
                    }else if(st.length==7){
                        jTextArea1.setText(st[6]);
                    }
		
		 ds.close();
		    
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	}
	}
		 
    }

    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==btn_brow_switch){
           
           System.out.println("Browse Button Presses");
           String ip[]=txt_myip.getText().split("\\.");
            int last=Integer.parseInt(ip[3]);
            String msg=txt_mymac.getText()+"++"+txt_myip.getText()+"++"+txt_myport.getText();
            for(int i=0;i<256;i++){
                sendData(msg,ip[0]+"."+ip[1]+"."+ip[2]+"."+i);
            }
       }else if(e.getSource()==btn_con_swi){
           System.out.println("Connect Button Presses");
           myip=txt_myip.getText();
           myport=txt_myport.getText();
           mymac=txt_mymac.getText();
           switchport=txt_swport.getText();
           switchip=jComboBox1.getSelectedItem().toString();
       }else if(e.getSource()==btn_send){
           System.out.println("Send Button Presses");
           urip=txt_urip.getText();
           urport=txt_urport.getText();
          
            Iterator it=cache.iterator();
            String ip="";
            String cach[];
            while(it.hasNext()){
                cach=(String[])it.next();
                if(cach[1].equals(urip)&&cach[2].equals(urport)){
                     txt_urmac.setText(cach[0]);
                }
            }
            urmac=txt_urmac.getText();
           if(urmac.equals("ff:ff:ff:ff:ff:ff")){
               String msg=mymac+">>"+myip+">>"+urmac+">>"+urip+">>"+myport+">>"+urport;
                sendData(msg,switchip);
           }else{
                String msg=mymac+">>"+myip+">>"+urmac+">>"+urip+">>"+myport+">>"+urport+">>"+jTextArea1.getText();
                sendData(msg,switchip);
           }
           
       }else if(e.getSource()==btn_start){
           System.out.println("start Button Presses");
           t1.start();
       }
       
    }
}
