import ip.vidhu.ipconfiguration.IpConfiguration;
import ip.vidhu.ipconfiguration.MyNetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.event.*;
public class Switch extends JFrame implements Runnable,ActionListener{

    
    private JButton btn_brow_switch;
    private JButton btn_con_swi;
    private JButton btn_start;
    private JComboBox jComboBox1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JPanel jPanel1;
    private JPanel jPanel3;
    private JLabel lbl_myport;
    private JLabel lbl_swport;
    private JTextField txt_myip;
    private JTextField txt_mymac;
    private JTextField txt_myport;
    private JTextField txt_swport;
    
    
    ArrayList<String[]> cache=new ArrayList<>();
    String address[];
    
    Thread t1,t2,t3;

	String myport,myip,switchip,switchport;

    public Switch() {
         try {
             makeFrame();
             
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
             Logger.getLogger(Switch.class.getName()).log(Level.SEVERE, null, ex);
         }
	t1=new Thread(this);
	t2=new Thread(this);
	t3=new Thread(this);
    }

    private void makeFrame() {

        jPanel3 = new JPanel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jComboBox1 = new JComboBox();
        btn_brow_switch = new JButton();
        btn_con_swi = new JButton();
        txt_swport = new JTextField();
        lbl_swport = new JLabel();
        jPanel1 = new JPanel();
        txt_myport = new JTextField();
        lbl_myport = new JLabel();
        jLabel1 = new JLabel();
        txt_myip = new JTextField();
        jLabel2 = new JLabel();
        txt_mymac = new JTextField();
        jLabel3 = new JLabel();
        btn_start=new JButton();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Switch");
        setMaximumSize(new java.awt.Dimension(595, 505));
        setMinimumSize(new java.awt.Dimension(595, 505));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel3.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(null);

        jLabel7.setText("Switch");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(13, 13, 47, 17);

        jLabel8.setText("Connect to");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(40, 40, 80, 20);

        jComboBox1.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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

        txt_swport.setHorizontalAlignment(JTextField.CENTER);
        txt_swport.setText("8000");
        jPanel3.add(txt_swport);
        txt_swport.setBounds(140, 10, 50, 27);

        lbl_swport.setText("Port");
        jPanel3.add(lbl_swport);
        lbl_swport.setBounds(90, 10, 50, 30);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(10, 140, 580, 90);

        jPanel1.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(null);

        txt_myport.setHorizontalAlignment(JTextField.CENTER);
        txt_myport.setText("8002");
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

        jLabel3.setText("My Details");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 20, 120, 30);

        btn_start.setText("Start");
        jPanel1.add(btn_start);
        btn_start.setBounds(130, 20, 90, 29);
	btn_start.addActionListener(this);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 10, 580, 120);

        
    }
    
    public static void main(String args[]) {
      
        new Switch().setVisible(true);
        
    }

    public void run() {
        if(Thread.currentThread()==t1){
		
	    while(true){
		try {
		    //str="";
		    DatagramSocket ds=new DatagramSocket(myport);
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
			try {
			    DatagramSocket dss=new DatagramSocket();
			    DatagramPacket dps;
			    byte a[]=new byte[255];
			 	
				a=("ok").getBytes();
				//System.out.println(new String(a));
				dps=new DatagramPacket(a,a.length,InetAddress.getByName(t[1]),Integer.parseInt(st[2]));
				dss.send(dps);
			 
				
			} catch (SocketException ex) {
			    System.out.println(ex);
			} catch (IOException ex) {
			    System.out.println(ex);
			 ex.printStackTrace();
			} 
		    }

		   /* st=new String(data).trim().split("::");
		    if(st.length>0){
		   
			//System.out.println("new node mac: "+st[0]);
		        System.out.println("data to : "+st[1]);
			if(st[1].equals(address[3])){
				System.out.println("message is: "+st[0]);
			}
		    }*/
		    ds.close();
		    
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	     }


	} else if(Thread.currentThread()==t2){
		
	} else if(Thread.currentThread()==t2){
		
	}
    }

    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==btn_brow_switch){
           System.out.println("Browse Button Presses");
       }else if(e.getSource()==btn_con_swi){
           System.out.println("Connect Button Presses");
       }else if(e.getSource()==btn_start){
           System.out.println("Send Button Presses");
	   myport=txt_myport.getText();
	   myip=txt_myip.getText();
	   t1.start();
       }
       
    }
    
}
