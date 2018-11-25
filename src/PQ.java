
public class PQ<T> {
	private int size;
	private PQNode<T> head;

	public PQ() {
		head = null;
		size = 0;
	}

	public int length() {
		return size;
	}

	public boolean full() {
		return false;
	}

	public void enqueueMin(T e, int pty) {
		PQNode<T> tmp = new PQNode<T>(e, pty);
		if ((size == 0) || (pty <= head.priority)) {
			tmp.next = head;
			head = tmp;
		} else {
			PQNode<T> p = head;
			PQNode<T> q = null;
			while ((p != null) && (pty > p.priority)) {
				q = p;
				p = p.next;
			}
			tmp.next = p;
			q.next = tmp;
		}
		size++;
	}

	public void enqueueMax(T e, int pty) {
		PQNode<T> tmp = new PQNode<T>(e, pty);
		if ((size == 0) || (pty > head.priority)) {
			tmp.next = head;
			head = tmp;
		} else {
			PQNode<T> p = head;
			PQNode<T> q = null;
			while ((p != null) && (pty <= p.priority)) {
				q = p;
				p = p.next;
			}
			tmp.next = p;
			q.next = tmp;
		}
		size++;
	}

	public PQNode<T> serve() {
		PQNode<T> node = head;
		PQNode<T> pqe = new PQNode<T>(node.data, node.priority);
		head = head.next;
		size--;
		return pqe;
	}

	public void printall() {
		int i = 0 ;
		PQNode<T> temp = head;
		
		while (i <size) {
			System.out.println(temp.data + " , " + temp.priority);
			temp = temp.next;
			i++;

		}
	}

}