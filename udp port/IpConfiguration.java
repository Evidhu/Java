import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    
    public String returnOS(){

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
    *   get MAC Address of your system
    */
    public String getPhysicalAddress()throws MyNetException{
         
            if(returnOS().equals(WIN_OS)){
                try {
                    boolean ether = false;
                    while ((line = br.readLine()) != null) {
                        if (line.contains("Ethernet adapter Local Area Connection")) {
                            ether = true;
                        }
                        if (ether) {
                            if (line.contains("Physical Address. . . . . . . . . :")) {
                               return (line.split("Physical Address. . . . . . . . . :")[1].trim());
                            }
                        }
                      }
                } catch (IOException ex) {
                    Logger.getLogger(IpConfiguration.class.getName()).log(Level.SEVERE, null, ex);
                }

            }else  if(returnOS().equals(LINUX)){
                try {            
                    while ((line = br.readLine()) != null) {                 
                        if (line.contains("Ethernet  HWaddr")) {
                            return (line.split("HWaddr")[1].trim());
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(IpConfiguration.class.getName()).log(Level.SEVERE, null, ex);
                }

            }       
            throw new MyNetException("No Physical Address Found");
    }
   
     /*
    *   get IPV4 Address of your system
    */
    public String getIPV4Address()throws MyNetException{
         //System.out.println("sshsj");
            if(returnOS().equals(WIN_OS)){
                try {
                    boolean ether = false;
                    while ((line = br.readLine()) != null) {
                        if (line.contains("Ethernet adapter Local Area Connection")) {
                            ether = true;
                        }
                        if (ether) {
                            if (line.contains("IPv4 Address. . . . . . . . . . . :")) {
                               return (line.substring(line.indexOf(":")+1,line.indexOf("(")).trim());
                            }
                        }
                      }
                } catch (IOException ex) {
                    Logger.getLogger(IpConfiguration.class.getName()).log(Level.SEVERE, null, ex);
                }

            }else  if(returnOS().equals(LINUX)){
                try {            
                    
                         boolean ether = false;
                        while ((line = br.readLine()) != null) {
                            if (line.contains("Ethernet")) {
                                ether = true;
                               
                            }
                            if (ether) {
                          
                                if (line.contains("inet addr")) {
                                    return (line.substring(line.indexOf(":")+1,line.indexOf("Bc")).trim());
                                }
                            }
                       
                    }
                } catch (IOException ex) {
                    Logger.getLogger(IpConfiguration.class.getName()).log(Level.SEVERE, null, ex);
                }

            }       
            throw new MyNetException("No IPV4 Address Found");
    }
    
    
}
