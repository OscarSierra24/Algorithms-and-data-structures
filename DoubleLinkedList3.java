

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList3<V> implements Iterable<V> {

    @Override
    public Iterator<V> iterator() {
        return new DoubleListIterator<>(this.first);
    }
    
    private static class DoubleListIterator<T> implements Iterator<T> {

        private Node<T> current, lastReturn;
        
        public DoubleListIterator(Node<T> nodo){
            current=nodo;
            lastReturn=null;
        }
        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            lastReturn=current;
            current=current.next;
            return lastReturn.value;
        }

    }

    static class Node<V> {

        private V value;
        private Node<V> next, prev;

        public Node(V value, Node<V> next, Node<V> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<V> first;
    private Node<V> last;
    private int size;

    public DoubleLinkedList3() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void addFirst(V value) {
        Node<V> nodo = new Node<>(value, this.first, null);
        if (this.isEmpty()) {
            this.last = nodo;
        } else {
            this.first.prev = nodo;
        }
        this.first = nodo;
        this.size++;
    }

    public void addLast(V value) {
        Node<V> nodo = new Node<>(value, null, this.last);
        if (this.isEmpty()) {
            this.first = nodo;
        } else {
            this.last.next = nodo;
        }
        this.last = nodo;
        this.size++;
    }

    public void addAt(int index, V value) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            this.addLast(value);
        } else {
            Node<V> tmp = this.first;
            for (int i = 0; i < index; i++) {
                tmp = tmp.next;
            }
            Node<V> nodo = new Node<>(value, tmp, tmp.prev);
            tmp.prev.next = nodo;
            tmp.prev = nodo;
            this.size++;
        }

    }

    public int indexOf(V value) {
        return 0;
    }

    public V remove(int index) {
        return null;
    }

    public V removeFirst() {
        return null;
    }

    public V removeLast() {
        return null;
    }

    public V getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return this.first.value;
        }
    }

    public V getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return this.last.value;
        }
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        DoubleLinkedList3<Integer> ll = new DoubleLinkedList3<>();
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
//        DoubleListIterator li = new DoubleListIterator(ll.first);
//        while(li.hasNext()){
//            System.out.println(li.next());
//        }
        for(Integer i:ll){
            System.out.println(i);
        }
    }
}