package assignmentsDataStructures;

import java.util.LinkedList;

import dataStructures.ISimpleStack;

public class LinkedStack<E> implements ISimpleStack<E> {

	private LinkedList<E> data = new LinkedList<>();

	@Override
	public void push(E item) {
		data.addLast(item);
	}

	@Override
	public E pop() {
		return data.removeLast();
	}

	@Override
	public E peek() {
		return data.getLast();
	}

	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}

	@Override
	public int Size() {
		return data.size();
	}

}
