import java.rmi.*;
import java.net.*;
import java.rmi.registry.*;
import java.util.*;
import java.rmi.server.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class RMIServer extends UnicastRemoteObject implements RMIinterface,ActionListener{

    Registry reg;
	JFrame jf1=new JFrame();
	JButton btn=new JButton("Stop Server");
	
	/*RMIServer constructor*/
	Process p;
    public RMIServer()throws RemoteException,Exception { 
        try {
			p = Runtime.getRuntime().exec("rmiregistry");
        	//BufferedReader br = new BufferedReader(
           // new InputStreamReader(p.getInputStream()));
			try{
				Naming.rebind("hello", this);
				System.out.println("OK");
        	} catch (Exception es) {
				System.out.println(es);
			}
			jf1.setSize(200,138);
			jf1.setTitle("RMI Server Developed by VIDHU EDAVANA");
			
			jf1.setLayout(null);
			jf1.add(btn);
			btn.setBounds(0,0,200,100);
			btn.addActionListener(this);
			jf1.setVisible(true);
			jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			System.out.println("server started");
		} catch (Exception e) {
			System.out.println(e);
		}
    }

   	/*Sharing Reverse method*/
    public String stringReverse(String got) throws RemoteException {
     	System.out.println("got "+got);
		String rev="";
		for(int i=got.length()-1;i>=0;i--){
			rev+=got.charAt(i);
		}
       	 	return rev;
    	}

	/*Sharing UpperCase method*/
    public String stringToUpper(String got) throws RemoteException {
       	 	return got.toUpperCase();
    }

	/*Sharing LowerCase method*/
    public String stringToLower(String got) throws RemoteException {
      	return got.toLowerCase();
    }

	/*Sharing Concatination method*/
    public String stringConcat(String got1,String got2) throws RemoteException {
     	return got1+got2;
    }
	
	/*Sharing Compare method*/
    public String stringCompare(String got1,String got2) throws RemoteException {
    	if(got1.equals(got2)){
			return "equal Strings";
		}else if(got1.equalsIgnoreCase(got2)){
			return "Diffrent Case";
		}else{
			return "Not Equal";
		}
    }


	/*main method*/
	public static void main(String[] args) throws RemoteException,Exception{
        new RMIServer();
	}
	public void actionPerformed(ActionEvent e){
	  try{
            System.out.println ("exit: " + p.exitValue());
            p.destroy();
		} catch (Exception es) {
			System.out.println(es);
		}
		System.exit(0);
	}
}
