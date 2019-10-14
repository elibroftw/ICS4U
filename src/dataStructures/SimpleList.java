package dataStructures;

import assignmentsDataStructures.ISimpleList;

public class SimpleList<E> implements ISimpleList<E> {

	private final int DEFAULT_SIZE = 0;
    @SuppressWarnings("unchecked")
	private E[] myData = (E[]) new Object[DEFAULT_SIZE];
    private int emptyIndex = 0;

	@SuppressWarnings("unchecked")
	@Override
	public boolean add(E e) {
		Object[] tempData = myData;
		myData = (E[]) new Object[emptyIndex];
		for(int i = 0; i < emptyIndex; i++) {
			myData[i] = (E) tempData[i];
		}
		myData[emptyIndex] = e;
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean insert(int i, E e) {
		E[] tempData = myData;
		myData = (E[]) new Object[emptyIndex];
		for(int j = 0; j < i; j++) {
			myData[j] = (E) tempData[j];
		}
		myData[i] = e;
		for(int j = i+1; j < emptyIndex; j++) {
			myData[j] = (E) tempData[j];
		}
		return false;
	}

	@Override
	public E replace(int i, E e) {
		E temp = (E) myData[i];
		myData[i] = e;
		return temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E remove(int i) {
		emptyIndex--;
		E[] tempData = myData;
		myData = (E[]) new Object[emptyIndex];
		for(int j = 0; j < i; j++) {
			myData[j] = (E) tempData[j];
		}
		for(int j = i; j < emptyIndex; j++) {
			myData[j] = (E) tempData[j+1];
		}
		return null;
	}

	@Override
	public E get(int index) {
		return (E) myData[index];
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		myData = (E[]) new Object[DEFAULT_SIZE];
	}

	@Override
	public int size() {
		return myData.length;
	}

	@Override
	public boolean isEmpty() {
		if(myData.length == 0) return true;
		else return false;
	}

	@Override
	public SimpleList<E> subList(int startIndex, int endIndex) {
		E[] tempData = myData;
		SimpleList<E> sendData = new SimpleList<E>();
		for(int i = startIndex; i < endIndex + 1; i++) {
			sendData.add(tempData[i]);
		}
		return sendData;
	}

}
