package com.sumu.demo;

import com.sumu.demo.SearchBinaryTree.TreeNode;

public class SearchBinaryTree {

	public SearchBinaryTree() {
		// TODO Auto-generated constructor stub
	}

	private TreeNode rootNode;

	/**
	 * �������Ҷ���������ӽ��
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
	 * ����ĳһ��ָ���Ľ��
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
	 * ɾ��ĳһ��ָ���Ľ��
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
				//���ɾ���Ľ�㣬�������ӽ��
				//1.���Ƚ�deleteNode��parentNodeָ��deleteNode��rightNode
				//rightNode��parentNodeָ��deleteNode��parentNode
				//��ΪleftNode<deleteNode,rightNode>deleteNode
				//����ֻ�ܽ�deleteNode��rightNode������leftNode�ģ��滻��ԭ��deleteNode��λ��
				if (parentNode.data > deleteNode.data) {
					parentNode.leftNode = deleteRightNode;
				} else {
					parentNode.rightNode = deleteRightNode;
				}
				deleteRightNode.parentNode=parentNode;
				//2.��ΪdeleteNode.leftNode�µ����еĽ��϶���С��deleteNode.rightNode����
				//����ֻ�轫deleteNode.leftNode�ý��ָ��deleteNode.rightNode����С�Ľ�㼴��
				TreeNode minNode=deleteRightNode.leftNode;
				TreeNode minParentNode=deleteRightNode;
				while (minNode!=null) {
					minParentNode=minNode.parentNode;
					minNode=minNode.leftNode;
				}
				minParentNode.leftNode=deleteLeftNode;
				deleteLeftNode.parentNode=minParentNode;
			} else {
				// ɾ���Ľ�㣬ֻ��һ���ӽ�����û���ӽ��
				// 1.���deleteNodeû���ӽ�㣬ֻ�轫parentNode��leftNode����rightNode��Ϊ��
				// 2.���deleteNodeֻ���ӽ��,��parentNode��leftNode����rightNodeָ��deleteNode���ӽ��
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
	 * ��ȡ��̽��(�����������������С)
	 * @param node
	 */
	public TreeNode getNextNode(TreeNode node) {
		if (node==null) {
			return null;
		}
		if (node.rightNode!=null) {
			//1.����ý�����ҽ�㣬�����ĺ�̽������������������㣬Ҳ������С��
			TreeNode minNode = node.rightNode;
			while (minNode.leftNode!=null) {
				minNode=minNode.leftNode;
			}
			return minNode;
		}else{
			//2.����ý�����ҽ�㲢�Ҹý���Ǹ���������ӣ������ĺ�̽����Ǹ����
			TreeNode parentNode = node.parentNode;
			if (parentNode.leftNode==node) {
				return parentNode;
			}
			
			//3.����ý�����ҽ�㲢�Ҹý���Ǹ������Ҷ��ӣ���һֱ����Ѱ�ң�ֱ���ҵ�һ��������������������Ϊֹ����ý��ĸ������Ǻ�̽�㡣
			TreeNode parentNode2=node.parentNode;
			while (parentNode2.parentNode!=null&&parentNode2.leftNode!=node) {
				node=parentNode2;
				parentNode2=node.parentNode;
			}
			return parentNode2;
		}
	}

	/**
	 * �������
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
			System.out.println("��ǰ��㲻����");
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
