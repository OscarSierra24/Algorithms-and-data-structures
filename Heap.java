
public class Heap<T> implements Comparable<T>{
	private int i; 
	private Node<T>[] array;

	class Node<T> extends Object{
		private T value;
		public Node(){
			super();
		}
	}
	public Heap(){	
		this.i=10;
		this.array=(Node<T>[])new Node[i];
	}
	public Heap(int i){
		this.i=i;
		this.array=(Node<T>[])new Node[i];
	}
	public Heap(Node<T>[] array){
		this.i=array.length;
		this.array=array;
	}
	public int parent(int i){
		return (int)Math.floor(i/2);
	}
	public int left(int i){
		return 2*i;
	}
	public int right(int i){
		return (2*i)+1;
	}
	public int heapSize(){
		for(int i=array.length-1;i>=0;i--){
			if(array[i]==null){
				return i;
			}
		}
		return array.length;
	}
//	A[parent(i)]>=A[i]
	public void maxHeap(Node<T>[] array,int i){
		int l,
			r;
		this.array=array;
		l=left(i);
		r=right(i);
		if(l<heapSize() && array[l].compareTo(array[i])){
			
		}
	}
	public void prueba(){
		String s="blablalb";
		Integer d=23232;
		if(s.compare)
	}
//	A[parent(i)]<=A[i]
	public void minHeap(){
		
	}
	public void setArray(Node<T>[] array){
		this.array=array;
	}
	public int compareTo(T o) {
		return 0;
	}
}
