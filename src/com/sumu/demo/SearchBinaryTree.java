package com.sumu.demo;

import com.sumu.demo.SearchBinaryTree.TreeNode;

public class SearchBinaryTree {

	public SearchBinaryTree() {
		// TODO Auto-generated constructor stub
	}

	private TreeNode rootNode;

	/**
	 * 创建查找二叉树，添加结点
	 * 
	 * @param data
	 * @return
	 */
	public TreeNode put(int data) {
		TreeNode node = null;
		TreeNode parent = null;
		if (rootNode == null) {
			rootNode = new TreeNode(data);
			return rootNode;
		}
		node = rootNode;
		boolean isLeft = false;
		while (node != null) {
			parent = node;
			if (data > node.data) {
				node = node.rightNode;
				isLeft = false;
			} else if (data < node.data) {
				node = node.leftNode;
				isLeft = true;
			} else {
				return node;
			}
		}
		node = new TreeNode(data);
		if (isLeft) {
			parent.leftNode = node;
		} else {
			parent.rightNode = node;
		}
		node.parentNode = parent;
		return node;
	}

	/**
	 * 查找某一个指定的结点
	 * 
	 * @param data
	 * @return
	 */
	public TreeNode search(int data) {
		TreeNode node = rootNode;
		while (node != null) {
			if (data > node.data) {
				node = node.rightNode;
			} else if (data < node.data) {
				node = node.leftNode;
			} else {
				return node;
			}
		}
		return null;
	}

	/**
	 * 删除某一个指定的结点
	 * 
	 * @param data
	 * @return
	 */
	public boolean delete(int data) {
		TreeNode deleteNode = search(data);
		if (deleteNode != null) {
			TreeNode parentNode = deleteNode.parentNode;
			TreeNode deleteLeftNode = deleteNode.leftNode;
			TreeNode deleteRightNode = deleteNode.rightNode;
			if (deleteNode.leftNode != null && deleteNode.rightNode != null) {
				//如果删除的结点，有两个子结点
				//1.首先将deleteNode的parentNode指向deleteNode的rightNode
				//rightNode的parentNode指向deleteNode的parentNode
				//因为leftNode<deleteNode,rightNode>deleteNode
				//所以只能将deleteNode的rightNode，大于leftNode的，替换掉原来deleteNode的位置
				if (parentNode.data > deleteNode.data) {
					parentNode.leftNode = deleteRightNode;
				} else {
					parentNode.rightNode = deleteRightNode;
				}
				deleteRightNode.parentNode=parentNode;
				//2.因为deleteNode.leftNode下的所有的结点肯定是小于deleteNode.rightNode结点的
				//所以只需将deleteNode.leftNode该结点指向deleteNode.rightNode下最小的结点即可
				TreeNode minNode=deleteRightNode.leftNode;
				TreeNode minParentNode=deleteRightNode;
				while (minNode!=null) {
					minParentNode=minNode.parentNode;
					minNode=minNode.leftNode;
				}
				minParentNode.leftNode=deleteLeftNode;
				deleteLeftNode.parentNode=minParentNode;
			} else {
				// 删除的结点，只有一个子结点或者没有子结点
				// 1.如果deleteNode没有子结点，只需将parentNode的leftNode或者rightNode置为空
				// 2.如果deleteNode只有子结点,则将parentNode的leftNode或者rightNode指向deleteNode的子结点
				TreeNode deleteNodeChlid = null;
				if (deleteLeftNode != null) {
					deleteNodeChlid = deleteLeftNode;
				} else if (deleteRightNode != null) {
					deleteNodeChlid = deleteRightNode;
				}
				if (parentNode.data > deleteNode.data) {
					parentNode.leftNode = deleteNodeChlid;
				} else {
					parentNode.rightNode = deleteNodeChlid;
				}
				deleteNodeChlid.parentNode=parentNode;
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 获取后继结点(左树找最大，右树找最小)
	 * @param node
	 */
	public TreeNode getNextNode(TreeNode node) {
		if (node==null) {
			return null;
		}
		if (node.rightNode!=null) {
			//1.如果该结点有右结点，则它的后继结点就是右子树的最左结点，也就是最小的
			TreeNode minNode = node.rightNode;
			while (minNode.leftNode!=null) {
				minNode=minNode.leftNode;
			}
			return minNode;
		}else{
			//2.如果该结点无右结点并且该结点是父结点的左儿子，则它的后继结点就是父结点
			TreeNode parentNode = node.parentNode;
			if (parentNode.leftNode==node) {
				return parentNode;
			}
			
			//3.如果该结点无右结点并且该结点是父结点的右儿子，则一直向上寻找，直到找到一个结点是它父结点的左儿子为止，则该结点的父结点就是后继结点。
			TreeNode parentNode2=node.parentNode;
			while (parentNode2.parentNode!=null&&parentNode2.leftNode!=node) {
				node=parentNode2;
				parentNode2=node.parentNode;
			}
			return parentNode2;
		}
	}

	/**
	 * 中序遍历
	 */
	public void proOlder(TreeNode root) {
		if (root == null) {
			return;
		} else {
			proOlder(root.leftNode);
			System.out.print(root.data + " ");
			proOlder(root.rightNode);
		}
	}

	public static void main(String[] args) {
		SearchBinaryTree binaryTree = new SearchBinaryTree();
		//int[] intArray = new int[] { 6, 54, 24, 64, 23, 43, 65, 4, 5, 6, 99 ,55};
		int[] intArray = new int[] {32,44,50,46,60,55,52,51,56};
		for (int i : intArray) {
			binaryTree.put(i);
		}
		binaryTree.proOlder(binaryTree.rootNode);
		System.out.println();
		TreeNode search = binaryTree.search(43);
		if (search == null) {
			System.out.println("当前结点不存在");
		} else {
			System.out.println(search.toString());
		}

		binaryTree.delete(64);
		binaryTree.proOlder(binaryTree.rootNode);
	}

	class TreeNode {
		private int data;
		private TreeNode leftNode;
		private TreeNode rightNode;
		private TreeNode parentNode;

		public TreeNode(int data) {
			// TODO Auto-generated constructor stub
			this.data = data;
			this.leftNode = null;
			this.rightNode = null;
			this.parentNode = null;
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public TreeNode getLeftNode() {
			return leftNode;
		}

		public void setLeftNode(TreeNode leftNode) {
			this.leftNode = leftNode;
		}

		public TreeNode getRightNode() {
			return rightNode;
		}

		public void setRightNode(TreeNode rightNode) {
			this.rightNode = rightNode;
		}

		public TreeNode getParentNode() {
			return parentNode;
		}

		public void setParentNode(TreeNode parentNode) {
			this.parentNode = parentNode;
		}

		@Override
		public String toString() {
			return "TreeNode data=" + data;
		}

	}
}
