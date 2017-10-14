import java.math.BigInteger;
import java.util.Random;

class Item<K,V>{
	K key;//stores key
	V value;//stores value
	int aux;//stores aux, if used !=0
	public Item(K key, V value){
		this.aux=0;
		this.key=key;
		this.value=value;
	}
	public K getKey(){
		return this.key;
	}
	public V getValue(){
		return this.value;
	}
	public int getAux(){
		return this.aux;
	}
	public void setAux(int aux){
		this.aux=aux;
	}
	public void setKey(K key){
		this.key=key;
	}
	public void setValue(V value){
		this.value=value;
	}
}

public class UniversalHash<K,V>{
	private Item<K,V>[] array; //where keys and values are stored
	private long a, //random number
				 b, //random number
				 p, //prime number
				 n, //values in the table
				 m; //size
	private int aux; //auxiliar variable
	public UniversalHash(){
		Random random=new Random();
		m=10;
		n=0;
		aux=0;
		a=new BigInteger(Integer.toString(random.nextInt(100000000))).nextProbablePrime().longValue();
		b=new BigInteger(Integer.toString(random.nextInt(100000000))).nextProbablePrime().longValue();
		p=new BigInteger(Long.toString(m)).nextProbablePrime().longValue();
		array=(Item[]) new Item[(int)m];
	}
	
	//Math: h(k)=(((ak+b)%p)%m)
	private int hash(K key, int aux){
		int code = (int) ((((((a*key.hashCode() & 0x7FFFFFF)+b) %p))+aux) %m);
		return code;
	}
	
	public boolean contains(K key){
		aux=0;
		int h=hash(key,aux);
		int initial=h;
		if(array[h]==null){
			return false;
		}
		while(array[h].getKey().equals(key)==false){
			h=(int) ((h+1) % m);
			if(array[h]==null||h==initial){
				return false;
			}
		}
		return true;
	}
	
	
	public void insert(K key,V value){
		if(n>=m){
			resize();
		}
		Item<K,V> item=new Item<K, V>(key,value);
		if(isEmpty(hash(key,aux))){
			array[hash(key,aux)]=item;
			n++;	
//			System.out.println("guardado en: " + hash(key,aux));
//			System.out.println("key: "+item.getKey()+" value:"+item.getValue());
			item.setAux(aux);
			aux=0;
		}
		else{
			//Detect collisions h(k,i)=h(k)+i%m	
			if(array[hash(key,aux)].getKey().equals(key)){
				array[hash(key,aux)]=item;
				return;
			}
			aux++;
			insert(key,value);
		}
	}
	
	public void delete(V value){
		for(int i=0;i<array.length;i++){
			if(array[i]!=null){
				if(value.equals(array[i].getValue())){
					array[i]=null;
					n--;
					return;
				}
			}
		}
	}
	public V search(K key){
		for(int aux=0;aux<array.length;aux++){
			if(isEmpty(hash(key,aux))==false){
				Item<K,V> item;
				item=array[hash(key,aux)];
				if(item.getKey().equals(key)){
					aux=0;
					return item.getValue();
				}
			}
		}
		return null;
	}
	public void traverse(){
		for(int i=0;i<array.length;i++){
			if(array[i]==null){
				System.out.println(i+": "+"null");
			}
			else{
				System.out.println(i+": "+array[i].getValue());
			}
		}
//		System.out.println(n);
	}	
	private boolean isEmpty(int position){
		if(array[position]==null){
			return true;
		}
		else{
			return false;
		}
	}
	private void resize(){
		Item<K,V>[] arraySave=(Item[]) new Item[(int)m];
		System.arraycopy(array, 0, arraySave, 0, (int)m);
		m*=2;
		n=0;
		p=new BigInteger(Long.toString(m)).nextProbablePrime().longValue();
		array=new Item[(int)m];
//		System.out.println(array.length);
		for(int i=0;i<arraySave.length;i++){
			if(arraySave[i]!=null){
				this.insert(arraySave[i].getKey(),arraySave[i].getValue());
			}
		}
	}
}
