package bTree;

import java.util.HashMap;

public class Btree {
	private int order;
	private Bnode rootNode;
	private HashMap<Bnode, Integer> searchHash;

	public Btree() {
		
	}

	public Btree(int order) {
		this.order = order;
		searchHash = new HashMap<Bnode, Integer>();
	}

	public boolean insert(int value) {
		int index;
		Bnode insertNode;

		if (rootNode == null) {
			Bnode newNode = new Bnode(this.order);
			newNode.setKey(value, 0);
			this.rootNode = newNode;
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
		Bnode rightNode = new Bnode(this.order);
		Bnode leftNode = new Bnode(this.order);
		
		if (splitNode.getParent() == null) {
			Bnode parentNode = new Bnode(this.order);

			int temp[] = new int[order];
			int j = 0;

			for(int i = 0; i < index; i ++) {
				temp[i] = splitNode.getKeyOne(i);
			}
			temp[index] = value;

			for(int i = index; i < splitNode.getKey().length && splitNode.getKeyOne(i) != 0; i++) {
				temp[i+1] = splitNode.getKeyOne(i);
			}

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
		}else {
			if(splitNode.getParent().getKeyOne(this.order-2) != 0) {
				// 만약 꽉차있을 경우 --> 부모도 스플릿 해줘야함
			}else {
				// 꽉차있지 않을 경우 --> 그냥 부모로 값올려보낸다음에 split
				Bnode parentNode = splitNode.getParent();

				int temp[] = new int[order];
				int j = 0;

				for(int i = 0; i < index-1; i ++) {
					temp[i] = splitNode.getKeyOne(i);
				}
				temp[index-1] = value;

				for(int i = index-1; i < splitNode.getKey().length && splitNode.getKeyOne(i) != 0; i++) {
					temp[i+1] = splitNode.getKeyOne(i);
				}

				int parentIndex = 0;
				for(int i = 0; i < parentNode.getKey().length && parentNode.getKeyOne(i) != 0; i++) {
					if(parentNode.getKeyOne(i) > temp[this.order / 2]) {
						parentIndex = i;
						break;
					}
				}
				
				int tempParent[] = new int[order-1];
				
				for(int i = parentIndex; i < parentNode.getKey().length && parentNode.getKeyOne(i) != 0; i++) {
					tempParent[i] = parentNode.getKeyOne(i);
				}
				
				parentNode.setKey(temp[this.order/2], parentIndex);
				
				for(int i = parentIndex; i < parentNode.getKey().length-1 && parentNode.getKeyOne(i) != 0; i++) {
					parentNode.setKey(tempParent[i], i+1);
				}
				
				Bnode tempChildNode[] = new Bnode[order];
				
				for(int i = parentIndex+1; i < parentNode.getBnode().length && parentNode.getBnodeOne(i) != null; i++) {
					tempChildNode[i] = parentNode.getBnodeOne(i);
				}
				
				for(int i = parentIndex+1; i < parentNode.getBnode().length-1 && parentNode.getBnodeOne(i) != null; i++) {
					parentNode.setBnode(tempChildNode[i], i+1);
				}

				for (int i = 0; i < this.order / 2; i++) {
					leftNode.setKey(temp[i], i);
				}

				for (int i = (this.order / 2) + 1; i < this.order; i++) {
					rightNode.setKey(temp[i], i - ((this.order / 2) + 1));
				}
				
				parentNode.setBnode(leftNode, parentIndex);
				parentNode.setBnode(rightNode, parentIndex+1);
			}
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

	/*public void printAllNode(Bnode node) {
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
	}*/

	public Bnode getRootNode() {
		return rootNode;
	}

	public void setRootNode(Bnode rootNode) {
		this.rootNode = rootNode;
	}
}