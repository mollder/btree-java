package bTree;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Btree tree = new Btree(3);
		tree.insert(1);
		tree.insert(2);
		tree.insert(3);
		
		tree.printBtree();
		//System.out.println(tree.getRootNode().getKeyOne(0));
		//System.out.println(tree.getRootNode().getKeyOne(1));
	}

}
