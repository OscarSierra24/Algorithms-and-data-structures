
public class BinarySearchTree<K extends Comparable<K>,V> {
	private ABBNode root;

//--------------------------------------------------------------------//		
	public boolean isEmpty(){
		return this.root==null;
	}
	
//--------------------------------------------------------------------//		
	public V get(K key){
		ABBNode node=this.root;
		while(node!=null){
			if(key.compareTo(node.key)==0){
				return node.val;
			}
			else if(key.compareTo(node.key)>0){
				node=node.right;
			}
			else{
				node=node.left;
			}
		}
		return null;
	}
	
//--------------------------------------------------------------------//		
	public void put(K key, V value){
		int comp;
		ABBNode node=this.root;
		ABBNode tmp=null;
		while(node!=null){
			tmp=node;
			comp=key.compareTo(node.key);
			if(comp<0){
				node=node.left;
			}
			else if(comp>0){
				node=node.right;
			}
			else{
				node.val=value;
			}
		}
		if(tmp==null){
			this.root=new ABBNode(key,value);
		}
		else{
			if(key.compareTo(tmp.key)>0){
				tmp.right=new ABBNode(key,value);
			}
			else{
				tmp.left=new ABBNode(key,value);
			}
		}
	}
//--------------------------------------------------------------------//		
	public void add(K key, V val){
		if(isEmpty()){
			this.root=new ABBNode(key,val);
		}
		else{
			add(key,val,this.root);
		}
	}

	private void add(K key, V val, ABBNode node){
		if(key.compareTo(node.key)>0){
			if(node.right==null){
				node.right=new ABBNode(key,val);
			}
			else{
				add(key,val,node.right);
			}
		}
		else if(key.compareTo(node.key)<0){
			if(node.left==null){
				node.left=new ABBNode(key,val);
			}
			else{
				add(key,val,node.left);
			}
		}
	}

//--------------------------------------------------------------------//	
	private Boolean contains(K key){
		ABBNode current=this.root;
		while(current!=null){
			if(key.compareTo(current.key)==0){
				return true;
			}
			else if(key.compareTo(current.key)>0){
				current=current.right; 
			}
			else if(key.compareTo(current.key)<0){
				current=current.left;
			}
		}
		return false;
	}

//--------------------------------------------------------------------//	
	public String inOrder(){
		if(this.root!=null){
			return inOrder(this.root);
		}
		else{
			return "";
		}
	}
	private String inOrder(ABBNode node){
		String salida="";
		if(node.left!=null){
			salida+= inOrder(node.left)+" ";
		}
		salida+=node;
		if(node.right!=null){
			salida += " " + inOrder(node.right); 
		}
		return salida;
	}
	
//--------------------------------------------------------------------//	
	
	public String preOrder(){
		if(this.root!=null){
			return preOrder(this.root);
		}
		else{
			return "";
		}
	}
	private String preOrder(ABBNode node){
		String salida="";
		if(node==null){
			return "";
		}
		else{
			salida+=node;
			salida+=preOrder(node.left);
			salida+=preOrder(node.right);
		}
		return salida;
	}
//--------------------------------------------------------------------//	
	public String preOrderIt(){
		ABBNode node=this.root;
		if(node==null){
			return "";
		}
		String salida="";
		StackArray<ABBNode> stack=new StackArray();
		stack.push(node);
		while(!stack.isEmpty()){
			node=stack.peek();
			salida += node;
			stack.pop();
			if(node.right!=null){
				stack.push(node.right);
			}
			if(node.left!=null){
				stack.push(node.left);
			}
		}
		return salida;
	}
//--------------------------------------------------------------------//	
	public String postOrder(){
		if(this.root==null){
			return "";
		}else{
			String salida="";
			return postOrder(this.root,salida);
		}
	}
	private String postOrder(ABBNode node, String salida){
		if(node==null){
			return "";
		}
		else{
			salida=postOrder(node.left, salida);
			salida+=postOrder(node.right, salida);
			salida+=node;
			return salida;
		}
	}
//--------------------------------------------------------------------//
	public String porNiveles(){
		ABBNode node=this.root;
		String salida="";
		QueueArray<ABBNode> queue=new QueueArray<ABBNode>();
		queue.enqueue(node);
		if(node==null){
			return "";
		}
		while(!queue.isEmpty()){
			node=queue.dequeue();
			salida+=node;
			if(node.left!=null){
				queue.enqueue(node.left);
			}
			if(node.right!=null){
				queue.enqueue(node.right);
			}
		}
		return salida;
	}
//--------------------------------------------------------------------//	

	public String descendente(){
		if(this.root==null){
			return "";
		}
		else{
			return descendente(this.root);
		}
	}
	private String descendente(ABBNode node){
		String salida="";
		if(node.right!=null){
			salida += descendente(node.right);	
		}
		salida+=node;
		if(node.left!=null){
			salida += descendente(node.left);	
		}
 		return salida;
	}

//--------------------------------------------------------------------//	
	public String cualesPorNivel(int n){
		
	}

//--------------------------------------------------------------------//
	public int mayoresA(K key){
		
	}
	public String cuantasHojas(){
		
	}
	public String hojas(){
		
	}
	public int altura(){
		
	}
	public int size(){
		
	}
	public V remove(K key){
		
	}
	
	private ABBNode sucesor(ABBNode node){
		if(node.right==null&&node.left==null){
			return null;
		}
		else if(node.right==null){
			node=node.left;
		}
		else if(node.left==null){
			node=node.right;
		}
		else{
			ABBNode current=node.right;
			ABBNode before=null;
			while(current.right!=null){
				before=current;
				current=current.left;
			}
			node.val=current.val;
			if(before==null){
				node.right=current.right;
			}
			else{
				before.left=current.right;
			}
		}
		return node;
	}
	
	
	
	
	
	
	
	
	
//---------------------------------------------------------//
	private class ABBNode{
		K key;
		V val;
		ABBNode left, right;
		
		public ABBNode(K key, V val){
			this.key=key;
			this.val=val;
		}
		public String toString(){
			return "["+key+"-"+ val +"]";
		}
	}
//---------------------------------------------------------//
	public static void main(String[] args){
		BinarySearchTree<Integer,Integer> bst=new BinarySearchTree<Integer,Integer>();
		BinarySearchTree<String,String> bst2=new BinarySearchTree<String,String>();
		bst.add(5, 5);
		bst.add(10, 10);
		bst.add(1212, 1212);
		bst.add(333, 333);
		bst.add(500, 500);
		bst.add(200, 200);
		

		bst2.add("F", "F");
		bst2.add("D", "D");
		bst2.add("J", "J");
		bst2.add("B", "B");
		bst2.add("E", "E");
		bst2.add("G", "G");
		bst2.add("K", "K");
		bst2.add("A", "A");
		bst2.add("C", "C");
		bst2.add("I", "I");
		
		System.out.println("I is:"+bst2.get("I"));
		String s=bst.inOrder();
		System.out.println(s);
		System.out.println("inorder es: "+bst2.inOrder());
		System.out.println("preorder es: "+bst2.preOrder());
		System.out.println("preorderIT es: "+bst2.preOrderIt());
		System.out.println("postorder es: "+bst2.postOrder());
		System.out.println("por niveles es: " +bst2.porNiveles());
		System.out.println("por descendente" +bst2.descendente());
		System.out.println("contiene a 4012:"+ bst.contains(4012));
		System.out.println("contiene a 5: "+bst.contains(5));
	}
}
