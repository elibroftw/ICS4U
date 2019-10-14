package dataStructures;

import java.util.EmptyStackException;

public class SimpleStack<E> implements ISimpleStack<E> {

	@SuppressWarnings("unchecked")
	private E[] data = (E[]) new Object[10];
	private int n = 0;
	
	@SuppressWarnings("unchecked")
	@Override
	public void push(E item) {
		if (n >= data.length) {
			E[] tempData = data;
			int l = tempData.length;
			data = (E[]) new Object[l * 2];
			for (int i = 0; i < l; i++) {
				data[i] = tempData[i];
			}
		}
		data[n] = item;
		n ++;
	}

	@Override
	public E pop() throws EmptyStackException{
		n --;
		E item = data[n];
		data[n] = null;
		return item;
	}

	@Override
	public E peek() throws EmptyStackException{
		return data[n-1];
	}

	@Override
	public boolean isEmpty() {
		return n <= 0;
	}

	@Override
	public int Size() {
		return n;
	}


}
