
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

class Vertex{
    int name;
    public Vertex(int name){
        this.name=name;
    }
    public String toString(){
        return name+"";
    }

    
}
class Edge {//implements Comparable<Edge>{
    Vertex u,v;
    int weight;
    Edge(Vertex v,Vertex u,int weight){
        this.u=u;
        this.v=v;
        this.weight=weight;
    }
//    public int compareTo(Edge next) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
class Graph{
     BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    ArrayList<Vertex> vertex=new ArrayList<>();
    ArrayList<Edge> edge=new ArrayList<>();
    
    public void AddVertex(){
        try {
            System.out.println("Enter No of Vertices");
            	String st=br.readLine();
            for(int i=1;i<=toInt(st);i++)
            	vertex.add(new Vertex(i));
        } catch (IOException ex) {
            System.out.println("Error : "+ex);
        }
    }
    
    
    public int toInt(String str){
    	return Integer.parseInt(str);
    }
    public Vertex getVertex(String name){// throws Exception{
         Iterator<Vertex> it=vertex.iterator();
         Vertex v=null;
        while(it.hasNext()){
            v=it.next();
           // System.out.println(v);
            if(v.toString().equals(name))
                break;
            else
                v=null;
            
            
        }
        return v;
        //throw new Exception("No suitable vertex found");
    }
    public void AddEdge(){
        try {
            System.out.println("Enter First Vertex Name");
            Vertex first=getVertex(br.readLine());
            System.out.println("Enter Second Vertex Name");
            Vertex sec=getVertex(br.readLine());
            System.out.println("Enter Edge weight");
            edge.add(new Edge(first,sec,Integer.parseInt(br.readLine())));
        } catch (IOException ex) {
            System.out.println("Error : "+ex);
        } catch (Exception ex) {
                        System.out.println("Error : "+ex);
        }
    }
    
    public void displyVertex(){
        System.out.println("Vertices are");
        Iterator<Vertex> it=vertex.iterator();
        while(it.hasNext()){
            System.out.print(" "+it.next());
        }
    }
    
    public void displyEdge(){
        System.out.println("Edges are");
        Iterator<Edge> it=edge.iterator();
        while(it.hasNext()){
            Edge e=it.next();
            System.out.println(e.v+" <----- "+e.weight+" -----> "+e.u);
        }
    }
    
    
    
    public String shortestPath(){
        String path="";
         try {
             Vertex source,dest;
             do{
                System.out.println("Enter source node");
                source=getVertex(br.readLine());
                if(source==null){
                    System.out.println("Sorry no Vertex with this name not found");
                    continue;
                }
                break;
             }while(true);
             do{
                System.out.println("Enter Destination node");
                dest=getVertex(br.readLine());
                if(dest==null){
                   System.out.println("Sorry no Vertex with this name not found");
                   continue;
                }
                break;
             }while(true);
             path="From '"+source+"' to '"+dest+"' is :";
            findPath(source, dest);
         } catch (IOException ex) {
            System.out.println("error"+ex);
         } catch (Exception ex) {
            System.out.println("error"+ex);
         }
          return path;
    }
    
    public int getWeight(int s,int d){
    	int dist=9999;
    	 Iterator<Edge> it=edge.iterator();
         while(it.hasNext()){
             Edge e=it.next();
             System.out.println(e.v+" <----- "+e.weight+" -----> "+e.u+" //  "+s+" "+d);
             if((e.v==getVertex(s+"")&&e.u==getVertex(d+""))||(e.u==getVertex(s+"")&&e.v==getVertex(d+""))){
            	 System.out.println("sdsjhdhh");
            	 dist=e.weight;
            	 break;
             }
            
         }
         return dist;
    }
    
    int mat[][];int next[][];
    public String findPath(Vertex s,Vertex d){
    	String path;
    	int size=vertex.size();
    	mat=new int[size][size];
    	next=new int[size][size];
    	for(int i=0;i<size;i++){
    		for(int j=0;j<size;j++){
    			
    			if(i==j){
    				mat[i][j]=0;
    				next[i][j]=0;
    				continue;
    			}
    			mat[i][j]=getWeight(i+1, j+1);
    			if(getWeight(i+1, j+1)==9999)
    				next[i][j]=0;
    			else
    				next[i][j]=j+1;
    		}
    	}
    	
    	 for (int k=0;k<size;k++)
    	   for(int i=0;i<size;i++)
    	        for (int j=0;j<size;j++){
    	           if (mat[i][j] > mat[i][k] + mat[k][j]) {
    	              mat[i][j] =mat[i][k] + mat[k][j];
	              	  next[i][j] = next[i][k];
    	           }
    	 }
    	path=Path(toInt(s.toString()), toInt(d.toString()));
    	mprint(mat);
    	
    	return path;
    }
    
    public void mprint(int[][] a){
    	int rows = a.length;
    	int cols = a[0].length;
    	System.out.println("array["+rows+"]["+cols+"] = {");
    	for (int i=0; i< rows; i++){
    	 	System.out.print("{");
    	 	for (int j=0; j< cols; j++)
    	    		System.out.print(" " + a[i][j] + ",");
    	 	System.out.println("},");
    	 
    	}
    	System.out.println(":;");
         }   
    

    public String Path(int u, int v){
    if (next[u-1][v-1] ==0)
        return "";
    String path = u+"";
    while (u != v){
        u = next[u-1][v-1];
        path+="->"+u;
    }
    return path;
    }
}


public class SPF {
    
   
    
    public Graph CreateGraph(){
        return new Graph();
    } 
    
   
    public static void main(String[] args) {
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            SPF s=new SPF();
            Graph g=null;
            int menu;
            do{
            
            if(g==null){
                System.out.println("Please Create Graph befor use");
                System.out.println("\n\tMenu\n1. Create Graph");
                 menu=Integer.parseInt(br.readLine());
            }
            else{
                System.out.println("\n\tMenu\n1. Reset Graph\n2. Add Vertex\n3. Add Edge\n4. Display vertex\n5. Display Edge"
                        + "\n6. Find Shortest path\n7. exit");
                menu=Integer.parseInt(br.readLine());
            }
            switch(menu){
                case 1:g=s.CreateGraph();break;
                case 2:g.AddVertex();break;
                case 3:g.AddEdge();break;
                case 4:g.displyVertex();break;
                case 5:g.displyEdge();break;
                case 6:System.out.println("Shortest Path "+g.shortestPath());break;
            }   
            }while(menu!=7);
        } catch (IOException ex) {
            System.out.println("Error : "+ex);
        }
    }
}

