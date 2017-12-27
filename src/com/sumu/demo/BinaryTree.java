package com.sumu.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import com.sumu.demo.BinaryTree.TreeNode;

public class BinaryTree {
	private TreeNode root;
	
	public BinaryTree() {
		// TODO Auto-generated constructor stub
		root=new TreeNode(1, "A");
	}
	
	/**
	 * 创建一个二叉树
	 * 				A
	 * 		B				C
	 * D		E				F  
	 */
	public void createBinaryTree() {
		TreeNode b=new TreeNode(2, "B");
		TreeNode c=new TreeNode(3, "C");
		TreeNode d=new TreeNode(4, "D");
		TreeNode e=new TreeNode(5, "E");
		TreeNode f=new TreeNode(6, "F");
		
		root.leftNode=b;
		root.rightNode=c;
		b.leftNode=d;
		b.rightNode=e;
		c.rightNode=f;
	}
	
	/**
	 * 通过前序二叉树反向生成二叉树
	 * @param data
	 * ABD##E##C#F##
	 */
	public void creatBinaryTreePre(List<String> data) {
		createBinaryTree(data.size(),data);
	}
	
	private TreeNode createBinaryTree(int size, List<String> data) {
		// TODO Auto-generated method stub
		TreeNode node=null;
		if (data.size()==0) {
			return node;
		}
		int index=size-data.size();
		String nodeLetter = data.get(0);
		if (nodeLetter.equals("#")) {
			data.remove(0);
			return node;
		}
		node=new TreeNode(index, nodeLetter);
		if (index==0) {
			root=node;
		}
		data.remove(0);
		node.leftNode=createBinaryTree(size, data);
		node.rightNode=createBinaryTree(size, data);	
		return node;
	}

	/**
	 * 获取二叉树的高度
	 * @return
	 */
	public int getHeight() {
		return getHeight(root);
	}
	
	private int getHeight(TreeNode rootNode) {
		// TODO Auto-generated method stub
		if (rootNode==null) {
			return 0;
		}else {
			int i=getHeight(rootNode.leftNode)+1;
			int j=getHeight(rootNode.rightNode)+1;
			return Integer.max(i, j);
		}
	}
	
	/**
	 * 获取二叉树的结点数
	 * @return
	 */
	public int getSize() {
		return getSize(root);
	}

	private int getSize(TreeNode rootNode) {
		// TODO Auto-generated method stub
		if (rootNode==null) {
			return 0;
		}else {
			int leftSize=getSize(rootNode.leftNode)+1;
			int rightSize=getSize(rootNode.rightNode)+1;
			return leftSize+rightSize-1;
		}
	}
	/**
	 * 前序遍历--递归(根左右)
	 * ABDECF
	 */
	public void proOlder(TreeNode root) {
		if (root==null) {
			return;
		}else {
			System.out.println("proOlder:"+root.getData());
			proOlder(root.leftNode);
			proOlder(root.rightNode);
		}
	}
	
	/**
	 * 前序遍历--非递归(根左右)
	 * ABDECF
	 */
	public void noProOlder(TreeNode root) {
		if (root==null) {
			return;
		}else {
			Stack<TreeNode> stack=new Stack<>();
			stack.push(root);
			while (!stack.isEmpty()) {
				TreeNode pop = stack.pop();
				System.out.println("noProOlder:"+pop.getData());
				if (pop.rightNode!=null) {
					stack.push(pop.rightNode);
				}
				if (pop.leftNode!=null) {
					stack.push(pop.leftNode);
				}
			}
		}
	}
	
	/**
	 * 中序遍历--递归(左根右)
	 * DBEACF
	 */
	public void midOlder(TreeNode root) {
		if (root==null) {
			return;
		}else {
			midOlder(root.leftNode);
			System.out.println("midOlder:"+root.getData());
			midOlder(root.rightNode);
		}
	}
	
	/**
	 * 中序遍历--非递归(左根右)
	 * DBEACF
	 */
	public void noMidOlder(TreeNode root) {
		TreeNode node=root;
		if (node==null) {
			return;
		}else {
			Stack<TreeNode> nodes=new Stack<>();
			while(node!=null||!nodes.isEmpty()) {
				while (node!=null) {
					nodes.push(node);
					node=node.leftNode;
				}
				if (!nodes.isEmpty()) {
					TreeNode pop = nodes.pop();
					System.out.println("noMidOlder:"+pop.getData());
					node=pop.rightNode;
				}
			}
		}
	}
	
	/**
	 * 后序遍历--递归(左右根)
	 * DEBFCA
	 */
	public void postOlder(TreeNode root) {
		if (root==null) {
			return;
		}else {
			postOlder(root.leftNode);
			postOlder(root.rightNode);
			System.out.println("postOlder:"+root.getData());
		}
	}
	
	/**
	 * 后序遍历--非递归(左右根)
	 * DEBFCA
	 */
	public void noPostOlder(TreeNode root) {
		TreeNode node=root;
		if (root==null) {
			return;
		}else {
			Stack<TreeNode> nodes=new Stack<>();
			Stack<Integer> flags=new Stack<>();//0代表往左，1代表往右
			while (node!=null||!nodes.isEmpty()) {
				while (node!=null) {
					nodes.push(node);
					flags.push(0);
					node=node.leftNode;
				}
				
				while (!nodes.isEmpty() && flags.peek()==1) {
					flags.pop();
					System.out.println("noPostOlder:"+nodes.pop().getData());
				}
				
				if (!nodes.isEmpty()) {
					flags.pop();
					flags.push(1);
					node=nodes.peek();
					node=node.rightNode;
				}	
			}
		}
	}



	public class TreeNode{
		private int index;
		private String data;
		private TreeNode leftNode;
		private TreeNode rightNode;
		
		public TreeNode(int index,String data) {
			// TODO Auto-generated constructor stub
			this.index=index;
			this.data=data;
			leftNode=null;
			rightNode=null;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}
		
		
	}
	public static void main(String[] args) {
		BinaryTree binaryTree=new BinaryTree();
		//binaryTree.createBinaryTree();
		//int height = binaryTree.getHeight();
		//System.out.println("二叉树高度:"+height);
		//int size = binaryTree.getSize();
		//System.out.println("二叉树结点数:"+size);
		//binaryTree.proOlder(binaryTree.root);
		//binaryTree.midOlder(binaryTree.root);
		//binaryTree.postOlder(binaryTree.root);
		//binaryTree.noProOlder(binaryTree.root);
		//binaryTree.noMidOlder(binaryTree.root);
		//binaryTree.noPostOlder(binaryTree.root);
		String[] data=new String[] {"A","B","D","#","#","E","#","#","C","#","F","#","#"};
		
		binaryTree.creatBinaryTreePre(new ArrayList<>(Arrays.asList(data)));
		binaryTree.proOlder(binaryTree.root);
	}
}


