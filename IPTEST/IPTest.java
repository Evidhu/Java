import ipconfiguration.IpConfiguration;
import ipconfiguration.MyNetException;

public class IPTest {
     public static void main(String[] args) {
        try {
            System.out.println("MAC  : "+new IpConfiguration().getPhysicalAddress());
            System.out.println("IPV4 : "+new IpConfiguration().getIPV4Address());
        } catch (MyNetException ex) {
            ex.getMessage();
        }
    }
}
