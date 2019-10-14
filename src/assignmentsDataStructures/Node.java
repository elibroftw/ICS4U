package assignmentsDataStructures;

public class Node<E> {

    private E data;
    private Node<E> next;
    private Node<E> before;

    public Node(E data) {
        this.data = data;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }
    
    public  Node<E> getBefore() {
        return before;
    }
    
    public void setBefore(Node<E> before) {
        this.before = before;
    }

    public E getData() {
        return data;
    }
    
    public void setData(E e) {
    	data = e;
    }
}

