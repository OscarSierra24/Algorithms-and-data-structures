import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<E> implements Iterable<E>{
    
	//Iterator class
	public Iterator<E> iterator() {
        return new DoubleLinkedListIterator<E>(this.first);
    }
	
	private static class DoubleLinkedListIterator<E> implements Iterator<E> {
        private Node<E> current,
        				lastReturn;
        
        public DoubleLinkedListIterator(Node<E> node){
            current=node;
            lastReturn=null;
        }
        public boolean hasNext(){
            return current!=null;
        }
        public E next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            lastReturn=current;
            current=current.next;
            return lastReturn.item;
        }
	}
	
	
//**************************************************************//
	
       static class Node<E>{
		E item;
		Node<E> next,
				prev;
		public Node(E item, Node<E> next, Node<E> prev){
			this.item=item;
			this.next=next;
			this.prev=prev;
		}
	}
       
//**************************************************************//
    //Double linked list Class
    private StringBuilder strB;
	private Node<E> first,
					last;
	private int size;
	
	/**
	 * @return constructor, size=0, first=null, last=null
	 */
	public DoubleLinkedList(){
		this.size=0;
		this.first=null;
		this.last=null;
		this.strB=new StringBuilder("");
		}
	
	/**
	 * 
	 * @return returns the size of the double linked list
	 */
	public boolean isEmpty(){
		return this.size==0;
	}
	
	/**
	 * @return returns the size of the list
	 */
	public int size(){
		return this.size;
	}
	/**
	 * 
	 * @param item
	 * @return true if the item is in the list and false if
	 * it's not
	 */
	public boolean contains(E item){
		for(E i:this){
			if(i.equals(item)){
					return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param index
	 * @return the element of the list that is at the index
	 * position
	 */
	public E get(int index){
		if(index==size){
			return this.last.item;
		}
		int count=0;
		for(E i:this){
			if(count==index){
				return i;
			}
			count++;
		}
		throw new NoSuchElementException();
	}
	
	/**
	 * 
	 * @param item
	 * @return returns the position of the object
	 */
	public int indexOf(E item){
		if(item.equals(this.last.item)){
			return this.size;
		}
		int count=0;
		for(E i:this){
			if(i.equals(item)){
				return count;
			}
			count++;
		}
		return -1;
	}
	
	/**
	 * 
	 * @param index
	 * @param item
	 * @return adds the item on the list at the index position,
	 * only if the index position is available and valid
	 */
	public void add(int index, E item){
		if(index>this.size || index<0){
			throw new IndexOutOfBoundsException();
		}
		if(index==0){
			this.addFirst(item);
		}
		else if(index==this.size){
			this.addLast(item);
		}
		else{
			Node<E> newNode=new Node<E>(item,null,null);
			Node<E> current=this.first;
			for(int i=0; i<index;i++){
				current=current.next;
			}
			newNode.next=current;
			newNode.prev=current.prev;
			current.prev.next=newNode;
			current.prev=newNode;
			this.size++;
		}
	}
	
	/**
	 * 
	 * @param index
	 * @return removes the element that is located at the index
	 * position, only if index is valid
	 */
	public E remove(int index){
		if(index<0 || index>this.size){
			throw new IndexOutOfBoundsException();
		}
		if(index==this.size){
			return this.removeLast();
		}
		if(index==0){
			return this.removeFirst();
		}
		Node <E> current=this.first;
		for(int i=0;i<index;i++){
			current=current.next;
		}
		E item=current.item;
		current.prev.next=current.next;
		current.next.prev=current.prev;
		current=null;
		this.size--;
		return item;
	}
	
	/**
	 * returns the elements of the list in a String
	 */
	public String toString(){
		for(E i:this){
			this.strB.append(i);
			this.strB.append(System.lineSeparator());
		}
		this.strB.delete(this.strB.length()-2, this.strB.length());
		return this.strB.toString();
	}
	
	/**
	 * 
	 * @param item
	 * adds the item as the first element of the list
	 */
	public void addFirst(E item){
		Node<E> node=new Node<E>(item, this.first, null);
		if(this.isEmpty()){
			this.last=node;
		}
		else{
			this.first.prev=node;
		}
		this.first=node;
		this.size++;
	}
	
	/**
	 * 
	 * @param item
	 * adds the item as the last element of the list
	 */
	public void addLast(E item){
		Node<E> node=new Node<E>(item, null, this.last);
		if(this.isEmpty()){
			this.first=node;
		}
		else{
			this.last.next=node;
		}
		this.last=node;
		this.size++;
	}
	
	/**
	 * removes the first element of the list
	 */
	public E removeFirst(){
		if(this.isEmpty()){
			throw new NoSuchElementException();
		}
		E item=this.first.item;
		this.first=this.first.next;
		this.size--;
		return item;
	}
	
	/**
	 * 
	 * @return removes the last element of the list
	 */
	public E removeLast(){
		if(this.isEmpty()){
			throw new NoSuchElementException();
		}
		E item=this.last.item;
		this.last=this.last.prev;
		this.last.next=null;
		this.size--;
		return item;
	}
	
	/**
	 * 
	 * @return returns the first element of the list
	 */
	public E getFirst(){
		if(this.isEmpty()){
			throw new NoSuchElementException();
		}
		return this.first.item;
	}
	/**
	 * 
	 * @return returns the last element of the list
	 */
	public E getLast(){
		if(this.isEmpty()){
			throw new NoSuchElementException();
		}
		return this.last.item;
	}
	
	
//***********************************************************//
	public void listAll(){
		for(E i:this){
			System.out.println(i);
		}
	}
}
