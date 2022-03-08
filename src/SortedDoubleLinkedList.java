import java.util.Comparator;

/**
 * This is a child class of the Basic Double Linked List.
 * 
 * @author Yei Thek Wang
 *
 * @param <T>
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	Comparator<T> comp;

	public SortedDoubleLinkedList(Comparator<T> comp) {
		super();
		this.comp = comp;
	}

	/**
	 * An unsupported method that throws an exception
	 * 
	 * @param data Data to be added into the list
	 */
	public void addToFront(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}

	/**
	 * An unsupported method that throws an exception
	 * 
	 * @param data Data to be added into the list
	 */
	public void addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}

	/**
	 * This method go through the nodes and adds the data in when the inserted
	 * data is less than the data in the nodes.
	 * 
	 * @param data Data to be added into the list
	 */
	public void add(T data) {
		if (size == 0) {
			Node<T> single = new Node<T>(data);
			head = single;
			tail = single;
			size++;
		} else {
			Node<T> check = head;
			while (check != null) {
				if (comp.compare(data, check.data) < 0) {
					Node<T> n = new Node<T>(data, check.prev, check);
					if (n.prev == null) {
						head = n;
					} else {
						n.prev.next = n;
					}
					check.prev = n;
					size++;
					return;
				}
				check = check.next;
			}
			Node<T> endNode = new Node<T>(data, tail, null);
			tail.next = endNode;
			tail = endNode;
			size++;
		}
	}

	/**
	 * Returns a List Iterator at the start of the list
	 * 
	 * @return ListIterator<T> a List Iterator initialized at the beginning of the
	 *         list
	 */
	public DoubleLinkedListIterator<T> iterator() {
		return super.iterator();
	}

	/**
	 * This method go through every nodes in the list until it finds a match,
	 * then remove that node from the list.
	 * 
	 * @param targetData The data to be matched and then removed
	 * @param comparator The comparator used to check whether the data is a match
	 * @return BasicDoubleLinkedList<T> A reference to the list, either changed,
	 *         unchanged, or empty
	 */
	public SortedDoubleLinkedList<T> remove(T targetData, java.util.Comparator<T> comparator) {
		return (SortedDoubleLinkedList<T>) super.remove(targetData, comparator);
	}
}