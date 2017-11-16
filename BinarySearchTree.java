
public class BinarySearchTree<K extends Comparable<K>,V> {
//-----------NODE CLASS------------------------------------------//
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
//------------END OF NODE CLASS----------------------------------//	
		
	private ABBNode root;

//--------------------------------------------------------------------//		
	//returns a boolean that says if the tree is empty or not
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
		if(n==0){
			return this.root+"";
		}
		
		String salida="";
		ABBNode node=this.root;
		QueueArray<ABBNode> queue=new QueueArray<ABBNode>();
		queue.enqueue(this.root);
		int x;
		
		for(int i=0;i<n;i++){
			System.out.println(node);
			x=queue.size();
			while(x>0){			
				node=queue.dequeue();
				if(node.left!=null){
					queue.enqueue(node.left);
				}
				if(node.right!=null){
					queue.enqueue(node.right);
				}
				x--;
			}
		}
		while(!queue.isEmpty()){
			salida+=queue.dequeue();
		}
		return salida;
	}
	
//--------------------------------------------------------------------//
	public String mayoresA(K key){
		if(this.root==null){
			return null;
		}
		else{
			return this.mayoresA(this.root,key);
		}
	}
	private String mayoresA(ABBNode node,K key){
		String salida="";
		if(node==null){
			return "";
		}
		salida+=this.mayoresA(node.left, key);
		if(node.key.compareTo(key)>0){
			salida+=node;
		}
		else{
			salida+="";
		}
		salida+=this.mayoresA(node.right, key);
		return salida;
	}
//--------------------------------------------------------------------//
	public int cuantasHojas(){
		return	this.cuantasHojas(this.root);
	}
	private int cuantasHojas(ABBNode node){
		int n=0;
		if(node==null){
			return 0;
		}
		n += this.cuantasHojas(node.left);
		if(node.left==null && node.right==null){
			n++;
		}
		 n += this.cuantasHojas(node.right);
		 return n;
	}
//--------------------------------------------------------------------//	
	public String hojas(){
		return this.hojas(this.root);
	}
	private String hojas(ABBNode node){
		String salida="";
		if(node.left!=null){
			salida += this.hojas(node.left);
		}
		
		if(node.left==null && node.right==null){
			salida=node+"";
			return salida;
		}
		if(node.right!=null){
			salida += this.hojas(node.right);
		}
		return salida;
	}
//--------------------------------------------------------------------//
	public int altura(){
		String salida="";
		ABBNode node=this.root;
		QueueArray<ABBNode> queue=new QueueArray<ABBNode>();
		queue.enqueue(this.root);
		int x;
		int n=0;
		while(queue.size()>0){
			n++;
			x=queue.size();
			while(x>0){			
				node=queue.dequeue();
				if(node.left!=null){
					queue.enqueue(node.left);
				}
				if(node.right!=null){
					queue.enqueue(node.right);
				}
				x--;
			}
		}
		return n-1;
	}
//--------------------------------------------------------------------//

	public int size(){
		if(this.root!=null){
			return this.size(this.root);
		}
		else{
			return 0;
		}
	}
	private int size(ABBNode node){
		int salida=0;
		if(node.left!=null){
			salida += this.size(node.left);
		}
		salida ++;
		if(node.right!=null){
			salida += this.size(node.right);
		}
		return salida;
	}
//--------------------------------------------------------------------//
//	public V remove(K key){
//		ABBNode node=this.root;
//		V temp;
//		while(node!=null){
//			int cmp=key.compareTo(node.key);
//			if(cmp==0){
//				break;
//			}
//			else if(cmp<0){
//				node=node.left;
//			}
//			else if(cmp>0){
//				node=node.right;
//			}
//		}
//		if(node==null){
//			return null;
//		}
//		else{
//			temp=node.val;
//			this.delete(node);
//		}
//		return temp;
//	}
//	public void transplant(ABBNode u,ABBNode v){
//		if(this.sucesor(u)==null){
//			this.root=v;
//		}
//		else if(u==sucesor(u).left){
//			sucesor(u).left=v;
//		}
//		else{
//			sucesor(u).right=v;
//		}
//		if(v!=null){
////			sucesor(v)=sucesor(u);
//		}
//	}
//	public void delete(ABBNode z){
//		if(z.left==null){
//			this.transplant(z,z.right);
//		}
//		else if(z.right==null){
//			z=z.left;
//		}
//		else{
//			y=this.
//		}
//		
//	}

	public V remove(K key){
		ABBNode current=this.root;
		ABBNode before=this.root;

		boolean found=false;
		while(current!=null&&!found) {
			if(current.key.equals(key)) {
				found=true;
			}
			else {
				before=current;
				if(current.key.compareTo(key)>0) {
					current=current.left;
				}
				else {
					current=current.right;
				}
			}
		}
		if(current==null) {
			return null;
		}
		else if(found) {
			if(current==this.root) {
				ABBNode tmp=sucesor(this.root);
				if(tmp!=null) {
					if(tmp.key.compareTo(key)>0) {
						tmp.left=this.root.left;
					}
					this.root=tmp;
					tmp=null;
				}
				else {
					return null;
				}
			}
			else if(current.key.compareTo(key)>0) {
				current.left=sucesor(current);
			}
			else if(before.key.compareTo(key)<0) {
				before.right=sucesor(current);
			}
		}
		return current.val;
	}
	
//--------------------------------------------------------------------//
	
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

		BinarySearchTree<Integer,Integer> bst3 = new BinarySearchTree<Integer, Integer>();
		
		bst3.add(10, 10);
//		bst3.add(8, 8);
//		bst3.add(11, 11);
//		bst3.add(7, 7);
//		bst3.add(9,9);
//		bst3.add(12, 12);
//		bst3.add(18, 18);
		bst3.add(16, 16);
		bst3.add(19, 19);
		bst3.add(15, 15);
		bst3.add(14, 14);
		bst3.add(1, 1);
		bst3.add(2, 2);

		
		
		System.out.println("I is:"+bst2.get("I"));
		String s=bst.inOrder();
		System.out.println(s);
		System.out.println("inorder es: "+bst2.inOrder());
		System.out.println("preorder es: "+bst2.preOrder());
		System.out.println("preorderIT es: "+bst2.preOrderIt());
		System.out.println("postorder es: "+bst2.postOrder());
		System.out.println("por niveles es: " +bst2.porNiveles());
		System.out.println("por descendente es:" +bst2.descendente());
		System.out.println("por nivel en 1:" + bst2.cualesPorNivel(1));
		System.out.println("mayores a:"+ bst2.mayoresA("G"));
		
		System.out.println("bst3 inorder: " + bst3.inOrder());
		System.out.println("cuantas hojas?" + bst3.cuantasHojas());
		System.out.println("Hojas: " + bst3.hojas());
		System.out.println("cuanta altura? " + bst3.altura());
		System.out.println("size? " + bst3.size());
		System.out.println(bst3.remove(2));
		System.out.println(bst3.inOrder());
//		System.out.println("contiene a 4012:"+ bst.contains(4012));
//		System.out.println("contiene a 5: "+bst.contains(5));
	}
}
