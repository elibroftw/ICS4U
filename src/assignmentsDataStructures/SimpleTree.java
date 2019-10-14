package assignmentsDataStructures;

import java.util.ArrayList;
import java.util.Stack;

import dataStructures.ISimpleTree;
import dataStructures.TreeNode;

public class SimpleTree<E extends Comparable<? super E>> implements ISimpleTree<E> {

	private TreeNode<E> root = null;

	@Override
	public void insert(E element) {
		// Box the element in a node
		TreeNode<E> newNode = new TreeNode<E>(element);

		// Determine the position of the element by comparing tree elements.
		if (root == null) {
			// Tree is empty. New node is root.
			root = newNode;
			return;
		}

		TreeNode<E> n = root;
		while (true) { // Keep looking until we find an empty node.
			if (element.compareTo(n.getData()) < 0) {
				// If the new item is "less than" the item in the tree,
				// our new object belongs on the left
				if (n.getLeft() == null) {
					// our node is empty on the left, add it to the node
					n.setLeft(newNode);
					return;
				}
				// Keep going!
				n = n.getLeft();
			} else {
				// If the new item is "greater than or equal to" the item in the tree,
				// our new object belongs on the right
				if (n.getRight() == null) {
					// our node is empty on the right, add it to the node
					n.setRight(newNode);
					return;
				}
				// Keep going!
				n = n.getRight();
			}
		}
	}

	@Override
	public void clear() {
		root = null;
	}

	@Override
	public boolean delete(E element) {
		// TODO NOT IMPLEMENTED YET
		return false;
	}

	@Override
	public int size() {
		// TODO Implement later
		return 0;
	}

	public boolean depthFirstSearch(String element) {
		TreeNode<E> n = root;
		Stack<TreeNode<E>> parents = new Stack<TreeNode<E>>();
		Stack<TreeNode<E>> visited = new Stack<TreeNode<E>>();
		while (true) {
			if (n == null) { // tree is empty
				return false;
			} else if (n.getData().equals(element)) { // found the item
				return true;
			}

			if (n.getLeft() != null && n.getLeft() != visited.peek()) {
				parents.add(n);
				n = n.getLeft();
			} else if (n.getRight() != null && n.getRight() != visited.peek()) {
				parents.add(n);
				n = n.getRight();
			} else {
				if (parents.size() == 0) { // when all nodes have been searched so the search returns to root node
					return false;
				}
				visited.add(n);
				n = parents.pop();
			}

		}

	}

	public boolean breadthFirstSearch(String element) {
		TreeNode<E> n = root;
		ArrayList<TreeNode<E>> parents = new ArrayList<>();
		ArrayList<TreeNode<E>> temp = new ArrayList<>();
		if (n == null) { // base case of root being null
			return false;
		} else if (n.getData().equals(element)) { // base case of root being the element
			return true;
		}
		parents.add(n);
		while (true) {
			temp = parents;
			parents.clear();
			for (TreeNode<E> parent : temp) {
				if (parent.getLeft() != null) {
					if (parent.getLeft().getData().equals(element)) {
						return true;
					}
					parents.add(parent.getLeft());
				}
				if (parent.getRight() != null) {
					if (parent.getRight().getData().equals(element)) {
						return true;
					}
					parents.add(parent.getRight());
				}
			}
		}
	}
}
