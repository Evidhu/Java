package ip.vidhu.ipconfiguration;
public class MyNetException extends Exception {
    String message;
    public MyNetException(String message) {
        this.message=message;
    }
    @Override
    public String toString(){
        return message;
    }
}

