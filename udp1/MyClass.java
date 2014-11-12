import java.net.InetAddress;
import java.io.Serializable;
public class MyClass implements Serializable{
    int m,n,p,q;
    int[][] a,b;
    InetAddress ip;
    boolean succ=true;
    String message;
    int [][]c;
	
    public MyClass(int m,int n,int p,int q,int[][]a,int[][]b) {
        this.a=a;
        this.b=b;
        this.m=m;
        this.n=n;
        this.p=p;
        this.q=q;
       // this.ip=ip;
    }

    

    public void addIp(InetAddress ip){
	this.ip=ip;
    }

    public void addRes(int[][]c){
	this.c=c;
    }

    public void addSucc(String msg){
	message=msg;
	succ=false;	
    }
    
}
