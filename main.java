public class main {
	public static void main(String[] args){
		DoubleLinkedList<String> dll=new DoubleLinkedList<String>();
		String barco="barco";
		String avion="avion";
		String perro="perro";
		String gato="gato";
		String gatos="gatos";
		String mascota="mascota";
		System.out.println("respuestas:"
				+ "true "
				+ "0 "
				+ ""
				+ ""
				+ ""
				+ ""
				+ "");
		System.out.println("----------------------------------------------");
		System.out.println(dll.isEmpty());
		System.out.println(dll.size());
//		System.out.println(dll.contains(barco));
		dll.addFirst(barco);
		dll.addFirst(avion);
		dll.addLast(perro);
		System.out.println("sdadsads");
		System.out.println(dll.size());
		dll.addFirst(gato);
		dll.addFirst(gatos);
//		System.out.println(dll.contains(barco));
		System.out.println(dll.contains(gatos));
		System.out.println(dll.get(0));
		System.out.println(dll.indexOf(gato));
		System.out.println(dll.size());
		dll.add(5, "perro2");
		System.out.println(dll.get(5));
		System.out.println(dll.indexOf(dll.getLast()));
		System.out.println("--------");
//		dll.listAll();
		System.out.println(dll.toString());
		System.out.println("--------");
		System.out.println(dll.getLast());
		System.out.println(dll.getFirst());
		System.out.println(dll.remove(0));
		System.out.println(dll.contains(barco));
		
		System.out.println("Checks add:");
		DoubleLinkedList<String> dll2=new DoubleLinkedList<String>();
		dll2.addLast("1");
		dll2.addLast("2");
		dll2.addLast("3");
		dll2.addFirst("0");
		dll2.addFirst("-1");
		dll2.add(0, "-1.5");
		dll2.addFirst("-2");
		
		System.out.println("Size is: "+dll2.size());
		dll2.listAll();
		System.out.println("-------------------------");
		System.out.println(dll2.get(7));
		System.out.println("-------------------------");
		System.out.println(dll2.indexOf(""));
		System.out.println("-------------------------");
//		System.out.println(dll2.removeLast());
		System.out.println("-------------------------");
		dll2.listAll();
		System.out.println("-------------------------");
		System.out.println(dll2.getFirst());
		System.out.println(dll2.getLast());
		}
}
