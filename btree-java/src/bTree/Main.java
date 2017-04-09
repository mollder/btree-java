package bTree;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Btree tree = new Btree(3);
		tree.insert(1);
		tree.insert(2);
		tree.insert(3);
		tree.insert(4);
		tree.insert(5);

		System.out.println(tree.getRootNode().getKeyOne(0));
		System.out.println(tree.getRootNode().getKeyOne(1));
		System.out.println(tree.getRootNode().getBnodeOne(0).getKeyOne(0));
		System.out.println(tree.getRootNode().getBnodeOne(1).getKeyOne(0));
		System.out.println(tree.getRootNode().getBnodeOne(2).getKeyOne(0));
	}
}
