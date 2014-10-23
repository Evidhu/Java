import java.rmi.Remote;

import java.rmi.RemoteException;


public interface RMIinterface extends Remote{

    public String stringReverse(String s)throws RemoteException;
    public String stringToUpper(String s)throws RemoteException;
    public String stringToLower(String s)throws RemoteException;
    public String stringConcat(String s1,String s2)throws RemoteException;
    public String stringCompare(String s1,String s2)throws RemoteException;

}
