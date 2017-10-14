
public class Tree3<T>{
	class Node<T>{
		private T value;
		private Node<T> parent,
						firstChild,
						nextSibling;
		public Node(T value){
			this.value=value;
			this.parent=null;
			this.firstChild=null;
			this.nextSibling=null;
		}
		public Node<T> getParent(){
			return this.parent;
		}
		public Node<T> getFirstChild(){
			return this.firstChild;
		}
		public Node<T> getNextSibling(){
			return this.nextSibling;
		}
	}
	
	/////////////////////////////////
	private Node<T> first,
				    current;
	private String space;
	public Tree3(){
		this.space="";
	}
	public void addFirstChild(T parent, T value){
		Node<T> newNode=new Node<T>(value);
		if (first==null){
			first=newNode;
		}
		else{
			search(parent,this.first);
			if(current.firstChild!=null){
				Node<T> tmp=current.firstChild;
				current.firstChild=newNode;
				newNode.nextSibling=tmp;
				newNode.parent=current;
			}
			else{
				current.firstChild=newNode;
				newNode.parent=current;
			}
		}
	}
	public void addNextSibling(T sibling, T value){
		Node<T> newNode=new Node<T>(value);
		search(sibling,first);
		System.out.println("current es:"+current.value);
		if(current.nextSibling==null){
			current.nextSibling=newNode;
			newNode.parent=current.parent;
		}
		else{
			Node<T> tmp=current.nextSibling;
			current.nextSibling=newNode;
			newNode.nextSibling=tmp;
			newNode.parent=current.parent;
		}
	}
	
	public void search(T value, Node<T> tmp){
//		System.out.println("tmp is:"+tmp.value+". value is: "+value);
		if(value.equals(tmp.value)){
			current=tmp;
		}
		tmp=tmp.firstChild;
		while(tmp!=null){
			this.search(value,tmp);
			tmp=tmp.nextSibling;
		} 
	}
	public Node<T> getNode(T value){
		search(value,first);
		return current;
	}
	public void listAll(Node<T> node){
		System.out.println(this.space+node.value);
		Node<T> child=node.firstChild;
		while(child!=null){
			this.space+="     ";
			listAll(child);
			child=child.nextSibling;
			this.space=this.space.substring(0, space.lastIndexOf(" ")-5+1);
		}
	}
	public Node<T> getFirst(){
		return this.first;
	}
	public boolean isEmpty(){
		return first==null;
	}

	//addfirstchild, add next sibling getvalue, getFirstChild, getNextSibling, getParent, listAll
	
	public static void main(String[] args){
		Tree3<String> tree=new Tree3<String>();
		tree.addFirstChild(null, "Oscar");
		tree.addFirstChild("Oscar","Alejandro");
		tree.addFirstChild("Alejandro","Sierra");
		tree.addNextSibling("Sierra", "Valdez");
		
		tree.addNextSibling("Alejandro","Alejandro2");
		tree.addNextSibling("Alejandro", "Alejandro3");
		tree.addFirstChild("Alejandro2", "Sierra2");
		tree.addFirstChild("Sierra2", "blabla");
		tree.addFirstChild("Alejandro3", "blablablabla");
		tree.addFirstChild("Alejandro", "Sierra2");
		tree.addFirstChild("Oscar", "FirstChild");
		tree.addNextSibling("FirstChild", "Sibling");
		System.out.println("lsadlsaldsladlasldsl");
		tree.addNextSibling("Oscar", "Oscar2");
		System.out.println(tree.getNode("Oscar2").value);
		System.out.println(tree.getNode("Oscar2").parent);
		System.out.println(tree.getNode("Oscar2").firstChild);
		tree.listAll(tree.getFirst());
	}
}
