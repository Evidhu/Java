import java.rmi.*;
import java.rmi.registry.*;
import javax.swing.*;
import java.awt.event.*;

public class RMIClient implements ActionListener{

    Registry reg;
    RMIinterface server;
	
    JPanel jf=new JPanel();
    JFrame jf1=new JFrame();
	
	JLabel lblip=new JLabel("Server IP");
    JLabel lbl1=new JLabel("First String");
    JLabel lbl2=new JLabel("Second String");
    JLabel lbl3=new JLabel("Result String");
	
    JTextField txtip=new JTextField();
    JTextField txt1=new JTextField();
    JTextField txt2=new JTextField();
    JTextField txt3=new JTextField();
	
    JButton btrev=new JButton("Reverse");
    JButton btup=new JButton("Upper");
    JButton btlow=new JButton("Lower");
    JButton btst=new JButton("Connect");
    JButton btadd=new JButton("Concat");
    JButton btcomp=new JButton("Compare");	

    public RMIClient() throws RemoteException{
		try{
			jf1.setSize(350,350);
			jf1.setTitle("RMI Client Developed by VIDHU EDAVANA");
			
			jf1.setLayout(null);
			jf1.add(jf);
			
			btst.setBounds(120,80,100,20);
			jf1.add(btst);
			btst.addActionListener(this);
			
			lblip.setBounds(50,45,100,20);
			jf1.add(lblip);
			
			txtip.setText("192.168.0.8");
			txtip.setBounds(170,45,100,20);
			jf1.add(txtip);
			
			jf.setBounds(50,100,300,350);
			jf.setVisible(true);
			jf.setLayout(null);
			
			lbl1.setBounds(10,10,100,20);
			jf.add(lbl1);
			txt1.setBounds(120,10,100,20);
			jf.add(txt1);
			
			lbl2.setBounds(10,35,100,20);
			jf.add(lbl2);
			txt2.setBounds(120,35,100,20);
			jf.add(txt2);

			lbl3.setBounds(10,60,100,20);
			jf.add(lbl3);
			txt3.setBounds(120,60,100,20);
			jf.add(txt3);

			btrev.setBounds(10,95,100,20);
			jf.add(btrev);
			btrev.addActionListener(this);

			btup.setBounds(120,95,100,20);
			jf.add(btup);
			btup.addActionListener(this);

			btlow.setBounds(10,120,100,20);
			jf.add(btlow);
			btlow.addActionListener(this);

			btadd.setBounds(120,120,100,20);
			jf.add(btadd);
			btadd.addActionListener(this);

			btcomp.setBounds(70,145,100,20);
			jf.add(btcomp);
			btcomp.addActionListener(this);
			
			jf1.setVisible(true);
			jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }catch(Exception ex){
			System.out.println(""+ex);
		}
    }

	public void actionPerformed(ActionEvent e){
		try{
			if(e.getSource()==btrev){ // Reverse Button
		 		String str=server.stringReverse(txt1.getText());
				txt3.setText(str);
				
			}else if(e.getSource()==btup){ //Upper case Button
		 		String str=server.stringToUpper(txt1.getText());
				txt3.setText(str);
				
			}else if(e.getSource()==btlow){ //Lower Case Button
		 		String str=server.stringToLower(txt1.getText());
				txt3.setText(str);
				
			}else if(e.getSource()==btadd){ //Conattination Button
		 		String str=server.stringConcat(txt1.getText(),txt2.getText());
				txt3.setText(str);
				
			}else if(e.getSource()==btcomp){ //Compare Button
		 		String str=server.stringCompare(txt1.getText(),txt2.getText());
				txt3.setText(str);
				
			}else if(e.getSource()==btst){	//Connect Button
				server=(RMIinterface)Naming.lookup("rmi://"+txtip.getText()+"/hello");
				JOptionPane.showMessageDialog(jf1,"Successfully Connected");
			}
		}catch(NullPointerException ex){
			JOptionPane.showMessageDialog(jf1,"Error in Connection");
		}catch(Exception ex){
			JOptionPane.showMessageDialog(jf1,"Some Error Occurred");
			System.out.println("error : "+ex);
		}
	}

    public static void main(String[] args) throws RemoteException, NotBoundException {
            new RMIClient();
    }

}
