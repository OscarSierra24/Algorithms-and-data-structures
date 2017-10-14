
public class Tree<N> {
	private N element;
	private Tree<N> parent,
				 firstChild,
				 nextSibling;
	private StringBuilder space;
	private String string;
	public Tree(N element){
		this.element=element;
		StringBuilder space=new StringBuilder();
		this.string="";
	}
	public Tree<N> addFirstChild(N element){
		if(this.firstChild!=null){
			Tree<N> tmp=this.firstChild;
			this.firstChild=new Tree<N>(element);
			this.firstChild.nextSibling=tmp;
			return this.firstChild;
		}
		else{
			this.firstChild=new Tree<N>(element);
			this.firstChild.parent=this;
			return this.firstChild;
		}
	}
	public Tree<N> addNextSibling(N element){
		if(this.nextSibling!=null){
			Tree<N> tmp=this.nextSibling;
			this.nextSibling=new Tree<N>(element);
			this.nextSibling.nextSibling=tmp;
			return this.nextSibling;
		}
		else{
			this.nextSibling=new Tree<N>(element);
			this.nextSibling.parent=this.parent;
			return this.nextSibling;
		}
	}
	public N getElement(){
		return this.element;
	}
	public void printElement(){
		System.out.println(this.element);
	}
	public Tree<N> getFirstChild(){
		return this.firstChild;
	}
	public Tree<N> getNextSibling(){
		return this.nextSibling;
	}
	public Tree<N> getParent(){
		return this.parent;
	}
	public void listAll(Tree<N> parent){
		System.out.println(this.string+parent.element);
		Tree<N> child=parent.firstChild;
		while(child!=null){
			this.string+="     ";
			listAll(child);
			child=child.nextSibling;
			this.string=this.string.substring(0, string.lastIndexOf(" ")-5+1);
			}
	}
}
