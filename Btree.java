import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class Btree {
	private int t;
	private BNode root;
//-------------------NODE CLASS------------------------------//
	private class BNode{
		boolean ifLeaf;
		int n;
		int[]x;
		int[]c;

		public BNode(int t) {
			this.ifLeaf=true;
			this.n=0;
			this.x= new int[2*t];	//keys
			this.c= new int[2*t+1];	//children
		}
	}
//--------------------END OF NODE CLASS--------------------//
	public Btree(int t) {
		this.t=t;
		this.root=new BNode(this.t);
//		write_disk(this.root,this.root.x[0]+".ptr");
	}

	public boolean isEmpty() {
		return this.root.n==0;
	}

	private File write_disk(BNode nodo, String name) {
		BufferedWriter bw = null;
		File f= new File("C:\\Users\\oscar\\Desktop\\pruebaBTree\\"+name);
		try {
			bw = new BufferedWriter(new FileWriter(f));
			if(nodo.ifLeaf) {
				bw.write("1");
			}
			else{
				bw.write("0");
			}
			bw.newLine();
			bw.write(nodo.n+"");
			bw.newLine();
			bw.write(nodo.c[0]+".ptr");
			bw.newLine();
			for(int i=0;i<nodo.n+1;i++) {
				bw.write(nodo.x[i]+".key");
				bw.newLine();
				bw.write(nodo.c[i+1]+".ptr");
				bw.newLine();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			try {
				bw.close();
			} 
			catch (Exception e) {
			}
		}
		return f;
	}

	private BNode read_disk(String archivo) {
		int[] x_tmp=new int[this.t*2];	//keys...key
		int[] c_tmp=new int[this.t*2+1];	//children...ptr
		boolean isLeaf_tmp = false;
		int n_tmp = 0;
		String linea="";

		try {
			BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\oscar\\Desktop\\pruebaBTree\\"+archivo));
			int i=0;
			int even=0;
			int odd=0;
			String []separada=new String[10];
			while((linea=bf.readLine())!=null){
				//System.out.println("Linea: "+i+"---- "+linea);

				if(i==0) {
					if(Integer.parseInt(linea)==0) {
						isLeaf_tmp=false;
					}
					else if(Integer.parseInt(linea)==1) {
						isLeaf_tmp=true;
					}
				}
				if(i==1) {
					n_tmp=Integer.parseInt(linea);
				}
				if(i>1) {

					if (i%2==0) {//even
						separada=linea.split(".ptr");
						//System.out.println("Separated .ptr: "+Arrays.toString(separada));
						c_tmp[even]=Integer.parseInt(separada[0]);
						even++;
					}
					else if(i%2!=0) {//odd
						separada=linea.split(".key");
						//System.out.println("Separated .key: "+Arrays.toString(separada));
						x_tmp[odd]=Integer.parseInt(separada[0]);
						odd++;
					}
				}
				i++;
			}
			System.out.println(".ptr: "+Arrays.toString(c_tmp));
			System.out.println(".key: "+Arrays.toString(x_tmp));
			BNode nodo= new BNode(this.t);
			nodo.c=c_tmp;
			nodo.x=x_tmp;
			nodo.ifLeaf=isLeaf_tmp;
			nodo.n=n_tmp;
			bf.close();
			return nodo;
		} 
		catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado"+e);
			e.printStackTrace();
		} 
		catch (IOException e) {
			System.out.println("Error al intentar leer el archivo"+e);
		}

		return null;
	}

	public void search(int k) {
		if(!isEmpty()) {
			System.out.println(search(this.root, k));;
		}
		else {
			System.out.println("Empty tree");
		}
	}

	private String search(BNode x,int k) {
		int i = 0;
		while((i<x.n)&&(k>x.x[i])) {
			i++;
		}
		if((i<=x.n)&&(k==x.x[i])) {
			return k+".key";
		}
		else if(x.ifLeaf) {
			return "";
		}
		else {
			return search(this.read_disk(x.c[i]+".ptr"), k);
		}
	}

	public void splitChild(BNode x, int i) {
		System.out.println("In split");
		//creates node z
		BNode z = new BNode(t);
//		System.out.println(Arrays.toString(x.c));
//		System.out.println(i);
		BNode y = this.read_disk(x.c[i-1]+".ptr");
		
		z.ifLeaf=y.ifLeaf;
		z.n=t-1;
		System.out.println(z.x.length);
//		for(int q=0;q<z.x.length;q++){
//			System.out.println(z.x[q]);
//		}
		//end creation
		
		//copia a z los valores extremos izquierdos
		for(int j=0;j<t-1;j++) {
			z.x[j]=y.x[j+t];
			y.x[j+t]=0; //agregado por mi
		}
		System.out.println("z es en x :");
		for(int q=0;q<z.x.length;q++){
			System.out.println(z.x[q]);
		}
		
		
		//no lo puedo hacer con el ejemplo prueba		
		if(!y.ifLeaf) {
			for(int j=0;j<t;j++) {
				z.c[j]=y.c[j+t];
			}
		}
		
		
		y.n=t-1;
		
		System.out.println("y es en x :");
		for(int q=0;q<y.x.length;q++){
			System.out.println(y.x[q]);
		}
		System.out.println("y length:" +y.x.length);
		System.out.println("dsadopwqkdopwqkalñsdksalñ");
		
		//makes the space to the pointer from the y node to the node x
		//no lo puedo revisar con mi ejemplo
		for(int j=x.n-1;j>i+1;j--) {
			x.c[j+1]=x.c[j];
		}
		
		System.out.println("x en x es:");
		for(int q=0;q<x.x.length;q++){
			System.out.println(x.c[q]);
		}
		
		//makes space for the key from the node y to the node x
		x.c[i+1]=z.x[0];
		for(int j=x.n-1;j>i+1;j--) {
			x.x[j+1]=y.x[j];
		}
		
		//hasta aquí va bien
		
		//copia la key a x
		x.x[i-1]=y.x[t-1];
		x.n++;
		
		System.out.println("en x es x:");
		for(int q=0;q<x.x.length;q++){
			System.out.println(x.x[q]);
		}
		

		write_disk(y,y.x[0]+".ptr");
		write_disk(z,z.x[0]+".ptr");
		write_disk(x,x.x[0]+".ptr");
	}

	private void insertNonfull(BNode x, int k) {
		int i = x.n;
		
		if(x.ifLeaf) {
//			if(x.n==0){
//				x.x[0]=k;
//				x.n=1;	
//			}
//			else {
				while(i>=1 && k<x.x[i-1]) {
					x.x[i]=x.x[i-1];
					i--;
				}
				x.x[i]=k;
				x.n=x.n+1;
//			}
		write_disk(x, x.x[0]+".ptr");
		}
		else {
			while(i>=0 && k<x.x[i]) {
				i--;
			}
			i++;
			BNode n=read_disk(x.c[i]+".ptr");
			if(n.n==2*t-1) {
				splitChild(x, i);
				if(k>x.x[i]) {
					i++;
				}
			}
			insertNonfull(n, k);
		}		
	}

	public void insert(int k) {
		BNode r=this.root;
		//if it's full
		if(r.n==2*t-1) {
			BNode s=new BNode(t);
			this.root=s;
			s.ifLeaf=false;
			s.n=0;
			s.c[0]=r.x[0];
			splitChild(s, 1);
			insertNonfull(s, k);

			System.out.println("---------------S--------------");
			System.out.println("Children: "+Arrays.toString(s.c));
			System.out.println("Keys: "+Arrays.toString(s.x));

			System.out.println("---------------Root--------------");
			System.out.println("Children: "+Arrays.toString(root.c));
			System.out.println("Keys: "+Arrays.toString(root.x));
		}
		
		else {
			insertNonfull(r, k);

			System.out.println("---------------Root--------------");
			System.out.println("Children: "+Arrays.toString(root.c));
			System.out.println("Keys: "+Arrays.toString(root.x));
		}
//		write_disk(this.root, "root.ptr");
	}

	public static void main(String[] args) {
		Btree bt= new Btree(2);
		bt.insert(20);
		bt.insert(35);
		bt.insert(40);
		System.out.println("------------------");
//		bt.insert(19);
		bt.insert(50);
//		bt.insert(2);		
//		bt.insert(4);
//		bt.insert(5);
//		bt.insert(6);
		//System.out.println("Children: "+Arrays.toString(bt.root.c));
		//System.out.println("Keys: "+Arrays.toString(bt.root.x));
	}

}