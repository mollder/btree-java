package bTree;

import java.util.HashMap;

public class Btree {
	private int order;
	private int depth;
	private Bnode rootNode;
	private HashMap<Bnode, Integer> searchHash;

	public Btree() {

	}

	public Btree(int order) {
		this.order = order;
		this.depth = 0;
		searchHash = new HashMap<Bnode, Integer>();
	}

	public boolean insert(int value) {
		int index;
		Bnode insertNode;

		if (rootNode == null) {
			Bnode newNode = new Bnode(this.order);
			newNode.setKey(value, 0);
			this.rootNode = newNode;
			this.depth = 1;
			return true;
		}

		search(value);
		index = this.searchHash.values().iterator().next();
		insertNode = this.searchHash.keySet().iterator().next();
		this.searchHash.clear();

		if (insertNode.getKeyOne(order - 2) != 0) {
			split(insertNode, value, index);
		} else {

			for (int i = index + 1; i < insertNode.getKey().length - 1 && insertNode.getKeyOne(i) != 0; i++) {
				insertNode.setKey(insertNode.getKeyOne(i), i + 1);
			}

			insertNode.setKey(value, index);
		}
		return true;
	}

	// 중복 안되게 삽입 ( 중복 방지 메소드 )
	public boolean searchEqual(int value) {
		Bnode searchNode = rootNode;
		return true;
		/*
		 * while(true) { for() }
		 */
	}

	public void search(int value) {
		Bnode searchNode = this.rootNode;
		int index = 0;

		while (searchNode.getBnodeOne(0) != null) {
			for (int i = 0; i < searchNode.getKey().length && searchNode.getKeyOne(i) != 0; i++) {
				if (value > searchNode.getKeyOne(i)) {
					index += 1;
				} else {
					break;
				}
			}
			searchNode = searchNode.getBnodeOne(index);
			index = 0;
		} // 여기까지 insertNode 를 찾는 코드

		for (int i = 0; i < searchNode.getKey().length && searchNode.getKeyOne(i) != 0; i++) {
			if (value > searchNode.getKeyOne(i)) {
				index += 1;
			} else {
				break;
			}
		}
		this.searchHash.put(searchNode, index);
	}

	public boolean split(Bnode splitNode, int value, int index) {
		if (splitNode.getParent() == null) {
			Bnode parentNode = new Bnode(this.order);
			Bnode rightNode = new Bnode(this.order);
			Bnode leftNode = new Bnode(this.order);

			int temp[] = new int[order];
			int j = 0;

			for (int i = 0; i < this.order && splitNode.getKeyOne(i) < value; i++) {
				temp[i] = splitNode.getKeyOne(i);
				j = i;
			}

			temp[j + 1] = value;

			for (int k = j + 1; k < this.order; k++) {
				temp[k] = splitNode.getKeyOne(k);
			} // 여기까지 소팅

			parentNode.setKey(temp[this.order / 2], 0);

			for (int i = 0; i < this.order / 2; i++) {
				leftNode.setKey(temp[i], i);
			}

			for (int i = (this.order / 2) + 1; i < this.order; i++) {
				rightNode.setKey(temp[i], i - ((this.order / 2) + 1));
			} // 여기까지 값 세팅

			parentNode.setBnode(leftNode, 0);
			parentNode.setBnode(rightNode, 1);

			leftNode.setParent(parentNode);
			rightNode.setParent(parentNode); // 부모노드 생성 및 자식 부모 세팅

			this.rootNode = parentNode; // 루트노드 세팅
			this.depth += 1;
			return true;
		}
		return true;
	}

	public boolean delete(int value) {
		return true;
	}

	public void printOneNode(Bnode printNode) {
		for (int i = 0; i < printNode.getKey().length && printNode.getKeyOne(i) != 0; i++) {
			System.out.print(printNode.getKeyOne(i) + " ");
		}
	}

	public void printAllNode(Bnode node) {
		Bnode printNode = node;

		for (int j = 0; j < this.order && printNode.getBnodeOne(j) != null; j++) {
			this.printOneNode(printNode.getBnodeOne(j));
			System.out.print("     ");
		}

		for (int j = 0; j < this.order && printNode.getBnodeOne(j) != null; j++) {
			this.printAllNode(printNode.getBnodeOne(j));
		}
	}

	public void printBtree() {
		this.printOneNode(this.rootNode);
		printAllNode(this.rootNode);
	}

	public Bnode getRootNode() {
		return rootNode;
	}

	public void setRootNode(Bnode rootNode) {
		this.rootNode = rootNode;
	}
}