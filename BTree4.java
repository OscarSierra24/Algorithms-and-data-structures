import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BTree4 {
//---------------BNode-------------//
	private class BNode{
		boolean isLeaf;				//if it's leaf or not
		int n;						//number of keys stored
		String[] x;					// keys stored
		String[] c;					//childs
		
		public BNode(int t){
			this.isLeaf=false;
			this.n=0;
			this.x=new String[2*t]; //node value
			this.c=new String[2*t]; //node childs
			
		}
	}	
//------------END OF BNode---------//
	
	private BNode root;
	private int t;
	
	public BTree4(int t){
		this.t=t;
		this.create();
	}
	private File write(BNode node) throws IOException{
		File file=new File("C:\\Users\\oscar\\Desktop\\pruebaBTree\\"+node.x[0]+".ptr");
		PrintWriter pw=new PrintWriter(new FileWriter(file));
		pw.println(node.isLeaf?true:false);
		pw.println(node.n);
		for(int i=0;i<node.n;i++){
			pw.println(node.c[i]);
			pw.println(node.x[i]);
		}
		pw.println(node.c[node.n]);
		pw.close();
		return file;
	}
	
	public BNode read(File file) throws IOException{
		BufferedReader br=new BufferedReader(new FileReader(file));
		BNode node=new BNode(t);
		node.isLeaf=((br.readLine()).compareTo("1")==0? true:false);
		node.n=Integer.parseInt(br.readLine());
		boolean isKey=false;
		String linea;
		int i=0;
		while((linea=br.readLine())!=null){
			if(!isKey){
				node.c[i]=linea;
				isKey=true;
			}
			else{
				node.x[i]=linea;
				i++;
			}
		}
		br.close();
		return node;
	}
	public void splitChild(BNode x,int i) throws IOException{
		int j;
		BNode z=this.allocateNode();
		BNode y=this.read(new File(x.c[i])); //warning
		z.isLeaf=y.isLeaf;
		z.n=this.t-1;
		
		for(j=1;j==this.t-1;j++){
			z.x[j]=y.x[j+t];
		}
		
		if(!y.isLeaf){
			for(j=1;j==t;j++){
				z.c[j]=y.c[j+t];
			}
		}
		y.n=t-1;
		
		for(j=x.n+1;j==i+1;j--){
			x.c[j+1]=x.c[j];
		}
		BNode tmp=read(new File(x.c[i+1])); //warning
		tmp=z;
		
		for(j=x.n;j==i;j--){
			x.x[j+1]=x.x[j];
		}
		x.x[i]=y.x[t];
		x.n=x.n+1;
		this.write(y);
		this.write(z);
		this.write(x);
	}
	public void insert(String k) throws IOException{
		BNode r=this.root;
		if (r.n==2*t-1){
			BNode s=allocateNode();
			this.root=s;
			s.isLeaf=false;
			s.n=0;
			String aux = this.write(r).toString(); //warning
			s.c[1]=aux;
			this.splitChild(s, 1);
			this.insertNonFull(s,k);
		}
		else{
			this.insertNonFull(r,k);
		}
	}
	
	public void insertNonFull(BNode x, String k) throws IOException{
		int i=x.n;
		if(x.isLeaf){
			while(i>=1 && k.compareTo(x.x[i])>0){
				x.x[i+1]=x.x[i];
				i=i-1;
				x.x[i+1]=k;
				x.n=x.n+1;
				this.write(x);
			}
		}
		else{ 
			while(i>=1 && k.compareTo(x.x[i])<0){
				i=i-1;
			}
			i++;
			BNode tmp=this.read(new File(x.c[i]));	//warning
			if(tmp.n==2*t-1){
				this.splitChild(x, i);
				if(k.compareTo(x.x[i])>0){
					i++;
				}
				tmp=this.read(new File(x.c[i]));
				this.insertNonFull(tmp, k);
			}
		}
	}
	
	public String search(BNode x,String key){
		int i=1;
		while(i<=x.n && key.compareTo(x.x[i])>0){
			i++;
		}
		if(i<=x.n && key.compareTo(x.x[i])==0){
			return x.x[i];
		}
		else if(x.isLeaf){
			return null;
		}
		else{
			try {
				return search(this.read(new File(x.c[i])),key);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
	public BNode allocateNode(){
		return new BNode(this.t);
	}
	public void create(){
		BNode x=allocateNode();
		x.isLeaf=true;
		x.n=0;
		try {
			this.write(x);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.root=x;
	}
	public static void main(String[] args){
		BTree4 tree=new BTree4(2);
		try {
			tree.insert("12");
			tree.insert("13");
			tree.insert("14");
			tree.insert("15");
			tree.insert("16");
			tree.insert("17");
			System.out.println(tree.search(tree.root, "12"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
