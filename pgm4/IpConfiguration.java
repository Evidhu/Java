package ip.vidhu.ipconfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VIDHU-EDAVANA
 * M-Tech CSE
 * MALABAR INSTITUTE OF TECHNOLOGY
 * ANJARAKKANDY
 * MOB:+919539696027 / +919539696727
 * 
 */
public class IpConfiguration {

  
   
    private static String MAC_OS = "MAC";
    private static String WIN_OS = "WINDOWS";
    private static String LINUX = "LINUX";
    
    String line="";
    BufferedReader br;
    
    private String returnOS(){

        String currentOs = System.getProperty("os.name").toUpperCase();
        if( currentOs.contains(MAC_OS)){
            currentOs = MAC_OS;
        }
        else if( currentOs.contains(WIN_OS) ){
            currentOs = WIN_OS;
        }
        else if( (currentOs.indexOf("NIX") >= 0 || currentOs.indexOf("NUX") >= 0 || currentOs.indexOf("AIX") > 0 )){
            currentOs = LINUX;
        }
        else{
            currentOs = null;
        }
        return currentOs;
    }
        
        
    public IpConfiguration(){
        Process p=null;
         try 
         {
            if(returnOS().equals(WIN_OS)){
                p=Runtime.getRuntime().exec("ipconfig /all");
            }
            else  if(returnOS().equals(LINUX)){
                p=Runtime.getRuntime().exec("ifconfig -a");
                System.out.println("got");
            }
            br=new BufferedReader(new InputStreamReader(p.getInputStream()));        
        } catch (IOException ex) {
            Logger.getLogger(IpConfiguration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    *   get Address of your system private Method
    */
    private String[][] getPhysicalAddress()throws MyNetException{
        String mac[][]=new String[5][2];
        int i=0;
            if(returnOS().equals(WIN_OS)){
                try {
                    boolean ether = false;
                    while ((line = br.readLine()) != null) {
                       
                        if (line.contains("Ethernet adapter Local Area Connection")) {
                            ether = true;
                        }
                        if (ether) {
                            if (line.contains("Physical Address. . . . . . . . . :")) {
                               mac[i][0]=(line.split("Physical Address. . . . . . . . . :")[1].trim());
                            }
                            if (line.contains("IPv4 Address. . . . . . . . . . . :")) {
                                mac[i][1]=line.substring(line.indexOf(":")+1,line.indexOf("(")).trim();
                            }
                        }
                      }
                    return mac;
                } catch (IOException ex) {
                    Logger.getLogger(IpConfiguration.class.getName()).log(Level.SEVERE, null, ex);
                }

            }else  if(returnOS().equals(LINUX)){
                try {            
                    while ((line = br.readLine()) != null) {  
                       // System.out.println("line'"+line+"'");
                         if(line.trim().equals("")){
                            i++;
                             
                        }
                        if (line.contains("Ethernet  HWaddr")) {
                          //  System.out.println("i = "+i);
                            mac[i][0]=(line.split("HWaddr")[1].trim());
                        }
                        if (line.contains("inet addr")) {
                                    
                                  //  System.out.println("i = "+i);
                                 //   System.out.println(""+line);
                                    mac[i][1]=line.split(":")[1].split(" ")[0];//(line.substring(line.indexOf(":")+1,line.indexOf("Bc")).trim());
                                }
                        
                    }
                    return mac;
                } catch (IOException ex) {
                    Logger.getLogger(IpConfiguration.class.getName()).log(Level.SEVERE, null, ex);
                }

            }       
            throw new MyNetException("No Physical Address Found");
    }
    
    /*
    *   get Address of your system public method
    */
    public ArrayList getAddress() throws MyNetException {
        try {
            ArrayList<String[]> ar=new ArrayList<String[]>();
                String[][] mac = getPhysicalAddress();
                   int a = mac.length;
                   for (int i = 0; i < 5; i++) {
                      // int b = mac[i].length;
                      // for (int j = 0; j < 2; j++) {
                       if(mac[i][0]!=null && mac[i][1]!=null)
                           ar.add(mac[i]);
                       
                }
               if(ar.size()>0)
                    return ar;
               else
                   throw new MyNetException("Not connected to any network...");
        } catch (MyNetException ex) {
            throw ex;
        }
        
        
    }
    
    
}

