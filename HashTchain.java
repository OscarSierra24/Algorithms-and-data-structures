import java.math.BigInteger;
import java.util.Random;


public class HashTchain <K,V>{	
	private int m,
				n;
	private static final int INIT_CAPACITY=1;
	private NodeH<K,V>[] table;

	public HashTchain(){
		this.m=this.INIT_CAPACITY;
		this.table=(NodeH<K,V>[]) new NodeH[m];
		this.n=0;
	}
	public HashTchain(int m){
		this.table=(NodeH<K,V>[]) new NodeH[m];
		this.m=m;
		this.n=0;
	}
	class NodeH<K,V>{
		private K k;
		private V v;
		private NodeH<K,V> next;
		public NodeH(){
			
		}	
	}	
	private int Hash(K k){
		long a,
			 b,
			 p;
		Random random=new Random();
		a=new BigInteger(Integer.toString(random.nextInt(100000000))).nextProbablePrime().longValue();
		b=new BigInteger(Integer.toString(random.nextInt(100000000))).nextProbablePrime().longValue();
		p=new BigInteger(Long.toString(m)).nextProbablePrime().longValue();
		return (int) ((((((a*k.hashCode() & 0x7FFFFFF)+b) %p))) %m);
	}
	public V get (K k){
		if(k==null){
			throw new NullPointerException("Invalid key");
		}
		int i=Hash(k);		
		NodeH<K,V> tmp=this.table[i];
		while(tmp!=null){
			if(tmp.k.equals(k)) return tmp.v;
		}
		return null;
	}
	public int size(){
		return n;
	}
	public boolean isEmpty(){
		return n==0;
	}
	public void put(K k, V v){
		if(k==null||v==null){
			throw new NullPointerException("Invalid key value");
		}
		int i=Hash(k);
		NodeH<K,V> tmp=this.table[i];
		if(this.table[i]==null){
			tmp.v=v;
		}
		else{
			
		}
	}
	
}
