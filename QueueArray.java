import java.util.NoSuchElementException;

public class QueueArray <N>{
    private N[] queue;
    private int tail;
    private int head;
    
    public QueueArray(){
    	this.tail=0;
        this.head=0;
        queue=(N[]) new Object[10];  
    }
    
    public N peek(){
        return this.queue[this.head];
    }
    
    public N dequeue(){
        if(!this.isEmpty()){
            N tmp=this.queue[head%this.queue.length];
            this.queue[head%this.queue.length]=null;
            this.head++;
            return tmp;
        }else{
            throw new NoSuchElementException();
        }
    }
    
    public void enqueue(N value){
        if((this.tail % this.queue.length) != (this.head % this.queue.length) || this.isEmpty()){
            this.queue[this.tail % this.queue.length] = value;
            this.tail++;
        }else{
            // Queue lleno
            N[] tmp = (N[]) new Object[this.queue.length*2];
            int pos = 0;       // New position of new array
            for(int i = head; i < this.tail; i++){
                tmp[pos] = this.queue[i%this.queue.length];
                pos++;
            }
            tail = tail - head;
            this.head = 0;
            this.queue = tmp;
            this.queue[this.tail] = value;
            this.tail++;
        }
    }
    
    public boolean isEmpty(){
        return this.head == this.tail;
    }
    
    public int size(){
        return this.tail - this.head;
    }
    
    public boolean contains(N value){
        for(int i = this.head; i < this.tail; i++){
            if(this.queue[i%this.queue.length] == value){
                return true;
            }
        }
        return false;
    }
    
    public String toString(){
        String result = "[";
        for(int i = this.head; i < this.tail; i++){
            result += this.queue[i%this.queue.length] + ", ";
        }
        result+= "]";
        return result;
    }

}

