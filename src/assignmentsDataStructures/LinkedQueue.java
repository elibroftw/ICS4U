package assignmentsDataStructures;

import java.util.LinkedList;

import dataStructures.ISimpleQueue;

public class LinkedQueue<E> implements ISimpleQueue<E> {

	private LinkedList<E> data = new LinkedList<>();

	@Override
	public void enqueue(E item) {
		data.addLast(item);
	}
	
	@Override
	public E dequeue() {
		return data.removeFirst();
	}

	@Override
	public E peek() {
		return data.getFirst();
	}

	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}

	@Override
	public int size() {
		return data.size();
	}

}
