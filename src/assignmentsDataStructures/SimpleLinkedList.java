package assignmentsDataStructures;

public class SimpleLinkedList<E> implements ISimpleList<E> {

	private Node<E> root = null;
	private Node<E> tail = null;

	@Override
	public boolean add(E e) {
		// 1. Wrap element in a node
		Node<E> element = new Node<E>(e);
		// 2. Traverse to end of list
		if (root == null) {
			root = element;
		} else {
			tail.setNext(element);
			element.setBefore(tail);
		}
		tail = element;
		return true;
	}

	@Override
	public boolean insert(int i, E e) {
		Node<E> n = root;
		for (int j = 0; j < i - 1; j++) {
			n = n.getNext();
		}
		// 1. Wrap e in a Node
		Node<E> element = new Node<E>(e);
		// 2. Set n's next element to new node
		element.setNext(n.getNext());
		// 3. Set new node's before to n
		element.setBefore(n);
		// 4. Set the next element's before to the new node
		element.getNext().setBefore(element);
		// 5. Set n's old next to the new node
		n.setNext(element);
		return true;
	}

	@Override
	public E replace(int i, E e) {
		Node<E> n = root;
		for (int j = 0; j < i; j++) {
			n = n.getNext();
		}
		E oldData = n.getData();
		n.setData(e);
		return oldData;
	}

	@Override
	public E remove(int i) {
		Node<E> n = root;
		for (int j = 0; j < i - 1; ++j) {
			n = n.getNext();
		}
		Node<E> removed = n.getNext();// Gives us reference to the removed node.
		n.setNext(removed.getNext());// removes the node from the list

		return removed.getData();
	}

	@Override
	public E get(int index) {

		Node<E> n = root;

		for (int i = 0; i < index; i++) {
			n = n.getNext();
		}
		return n.getData();
	}

	@Override
	public void clear() {
		root = null;
		tail = null;
	}

	@Override
	public int size() {
		int s = 0;
		if (root != null) {
			Node<E> n = root;
			while (n.getNext() != null) {
				n = n.getNext();
				s++;
			}
		}
		return s;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public ISimpleList<E> subList(int startIndex, int endIndex) {
		Node<E> n = root;
		for (int j = 0; j < startIndex; j++) {
			n = n.getNext();
		}
		SimpleLinkedList<E> newList = new SimpleLinkedList<E>();
		for (int i = startIndex; i < endIndex; i++) {
			newList.add(n.getData());
			n = n.getNext();
		}
		return newList;
	}

	/**
	 * Returns a new SimpleLinkedList which contains the deleted elements starting
	 * at i inclusive and ending at n exclusive.
	 * 
	 * @param i
	 *            the beginning index of the new SimpleLinkedList, inclusive.
	 * @param n
	 *            the index at which to stop. The element at this index is not
	 *            included in the returned SimpleLinkedList.
	 * @return the new SimpleLinkedList
	 */
	public SimpleLinkedList<E> deleteRange(int i, int n) {
		Node<E> current = root;
		for (int j = 1; j < i; j++) {
			current = current.getNext();
		} 
		Node<E> temp = current.getNext();
		SimpleLinkedList<E> newList = new SimpleLinkedList<>();
		for (int x = i; x < n; x++) {
			newList.add(temp.getData());
			temp = temp.getNext();
		}
		current.setNext(temp);
		temp.setBefore(current);
		return newList;
	}

	/**
	 * Inserts a SimpleLinkedList at index i in the main list
	 * 
	 * @param i
	 *            index to insert at
	 * @param e
	 *            SimpleLinkedList to insert
	 */
	public void insertAll(int i, SimpleLinkedList<E> e) {
		for (int x = i; x < e.size(); x++) {
			insert(x, e.get(x - i));
		}
	}

	/**
	 * Reverses the order of the LinkedList
	 */
	public void reverse() {
		Node<E> temp = null;
		Node<E> n = root;

		while (n != null) {
			temp = n.getBefore();
			n.setBefore(n.getNext());
			n.setNext(temp);
			n = n.getBefore();
		}

		temp = root;
		root = tail;
		tail = temp;
	}
}
