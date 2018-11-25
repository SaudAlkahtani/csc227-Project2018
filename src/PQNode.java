

public class PQNode<T> {
	public T data;
	public int priority;
	public PQNode<T> next;
	
	public PQNode() {
		next = null;
	}
	
	public PQNode(T e, int p) {
		data = e;
		priority = p;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public PQNode<T> getNext() {
		return next;
	}

	public void setNext(PQNode<T> next) {
		this.next = next;
	}
	
	

}