package dataStructures;

public class SimpleQueue<E> implements ISimpleQueue<E> {

	public static void main(String[] args) {
		SimpleQueue<Integer> testQueue = new SimpleQueue<Integer>();
		for (int i = 0; i < 10; i++) {
			testQueue.enqueue(i);
		}
		for (int i = 0; i < 4; i++) {
			System.out.println(testQueue.dequeue());
		}
		for (int i = 0; i < 4; i++) {
			testQueue.enqueue(i);
		}
		System.out.println("ASDF");
	}

	@SuppressWarnings("unchecked")
	private E[] data = (E[]) new Object[10];
	private int front = 0;
	private int back = 0;

	@SuppressWarnings("unchecked")
	@Override
	public void enqueue(E item) {
		if (size() == data.length - 1) {
			// Array is currently nearly full (is at length -1)
			E[] old = data;
			data = (E[]) new Object[old.length * 2];
			int size = size();

			for (int i = 0; i < size(); i++) {
				data[i] = old[(front + i) % old.length];
			}

			back = size;
			front = 0;
		}

		data[back] = item;
		back = (back + 1) % data.length;
	}

	@Override
	public E dequeue() {
		E toReturn = data[front];
		data[front] = null;
		front = (front + 1) % data.length;
		return toReturn;
	}

	@Override
	public E peek() {
		return data[front];
	}

	@Override
	public boolean isEmpty() {
		return back == front + 1;
	}

	@Override
	public int size() {
		int size = back - front;
		if (size < 0) {
			size += data.length;
		}
		// Two scenarios:
		// if front is before back
		// if front is after back
		// if front == back -->
		return size;
	}
}
