package bTree;

public class Bnode {
	private int[] key;
	private Bnode[] child;
	private Bnode parent;
	
	public Bnode() {
		
	}
	
	public Bnode(int value) {
		this.key = new int[value-1];
		this.child = new Bnode[value];
		
		for(int i = 0; i < this.key.length; i++) {
			this.key[i] = 0;
		}
		
		for(int i = 0; i < this.child.length; i++) {
			this.child[i] = null;
		}
		parent = null;
	}
	
	public void setKey(int key, int index) {
		this.key[index] = key;
	}
	
	public int getKeyOne(int index) {
		return this.key[index];
	}
	
	public void setBnode(Bnode bNode, int index) {
		this.child[index] = bNode;
	}
	
	public Bnode getBnodeOne(int index) {
		return this.child[index];
	}
	
	public Bnode[] getBnode() {
		return this.child;
	}
	
	public int[] getKey() {
		return this.key;
	}

	public Bnode getParent() {
		return parent;
	}

	public void setParent(Bnode parent) {
		this.parent = parent;
	}
}