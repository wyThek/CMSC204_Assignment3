import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This generic double-linked implements the Java’s Iterable interface and
 * relies on a head and tail. Both the head and the tail are set to null when
 * the list is empty. Both point to the same element when there is only one
 * element in the list. A node structure has three fields: data, next and the
 * previous references.
 * 
 * @author Yei Thek Wang
 *
 * @param <T>
 */
public class BasicDoubleLinkedList<T> {
	protected Node<T> head = null;
	protected Node<T> tail = null;
	protected int size = 0;

	/**
	 * An inner class that defines the Nodes in the list. Nodes in doubly linked
	 * list contains the references of next and previous node
	 * 
	 * @author Yei Thek Wang
	 *
	 * @param <T>
	 */
	class Node<T> {
		protected Node prev;
		protected Node next;
		protected T data;

		public Node() {
			prev = null;
			next = null;
			data = null;
		}

		public Node(T dataNode) {
			data = dataNode;
		}

		public Node(T d, Node<T> p, Node<T> n) {
			data = d;
			prev = p;
			next = n;
		}
	}

	/**
	 * An inner class that defines the Iterator in the list. An object that can
	 * iterate through the nodes in a list.
	 * 
	 * @author Yei Thek Wang
	 *
	 * @param <T>
	 */
	class DoubleLinkedListIterator<T> implements ListIterator {
		protected Node<T> prev;
		protected Node<T> next;

		public DoubleLinkedListIterator() {
			prev = null;
			next = null;
		}

		public DoubleLinkedListIterator(Node<T> nodeInput) {
			prev = null;
			next = nodeInput;
		}

		/**
		 * Returns the data in the next node, or throws an error if you are at the end
		 * of the list
		 * 
		 * @return T the data contained by the next node
		 */
		public T next() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException(
						"Error, you've reached the end of the list. You can no longer go forward");
			}
			prev = next;
			next = next.next;
			return prev.data;
		}

		/**
		 * Simply check for the next node
		 * 
		 * @return boolean True if there is a node following the iterator, False if not
		 */
		public boolean hasNext() {
			if (next != null) {
				return true;
			}
			return false;
		}

		/**
		 * Returns the data in the previous node, or throws an exception if you are at
		 * the beginning of the list
		 * 
		 * @return T the data contained by the previous node
		 */
		public T previous() throws NoSuchElementException {
			if (!hasPrevious()) {
				throw new NoSuchElementException(
						"Error, you're at the beginning of the list. You can not move backward");
			}
			next = prev;
			prev = prev.prev;
			return next.data;
		}

		/**
		 * Simply check for a previous node
		 * 
		 * @return boolean True if there is a node before the iterator, False if not
		 */
		public boolean hasPrevious() {
			if (prev != null) {
				return true;
			}
			return false;
		}

		/**
		 * throws an UnsupportedOperationException
		 */
		@Override
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Error, this is an unsupported operation.");
		}

		/**
		 * throws an UnsupportedOperationException
		 */
		@Override
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Error, this is an unsupported operation.");
		}

		/**
		 * throws an UnsupportedOperationException
		 */
		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Error, this is an unsupported operation.");
		}

		/**
		 * throws an UnsupportedOperationException
		 */
		@Override
		public void set(Object e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException("Error, this is an unsupported operation.");
		}

		/**
		 * throws an UnsupportedOperationException
		 */
		@Override
		public void add(Object e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * This method adds a entry value to the end of the list
	 * 
	 * @param data Data that will be added to the list
	 */
	public void addToEnd(T data) {
		if (size == 0) {
			Node<T> single = new Node<T>(data);
			head = single;
			tail = single;
		} else {
			tail = new Node<T>(data, tail, null);
			tail.prev.next = tail;
		}
		size++;
	}

	/**
	 * This method adds a entry value to the front of the list
	 * 
	 * @param data Data that will be added to the list
	 */
	public void addToFront(T data) {
		if (size == 0) {
			Node<T> single = new Node<T>(data);
			head = single;
			tail = single;
		} else {
			head = new Node<T>(data, null, head);
			head.next.prev = head;
		}
		size++;
	}

	/**
	 * Returns the first data of the list
	 * 
	 * @return T the data held by the first node in the list
	 */
	public T getFirst() {
		return head.data;
	}

	/**
	 * Returns the last data of the list
	 * 
	 * @return T the data held by the last node in the list
	 */
	public T getLast() {
		return tail.data;
	}

	/**
	 * Returns the number of nodes contained in the list
	 * 
	 * @return int the size of the list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Returns a List Iterator at the start of the list
	 * 
	 * @return DoubleLinkedListIterator <T> a List Iterator initialized at the
	 *         beginning of the list
	 */
	public DoubleLinkedListIterator<T> iterator() {
		if (size == 0) {
			return new DoubleLinkedListIterator<T>();
		}
		return new DoubleLinkedListIterator<>(head);
	}

	/**
	 * This method cycles through every node on the list until it finds a match,
	 * then cuts that node out of the list. If it doesn't find a match, it doesn't
	 * change anything.
	 * 
	 * @param targetData The data to be matched and then removed
	 * @param comparator The comparator used to check whether the data is a match
	 * @return BasicDoubleLinkedList<T> A reference to the list, either changed,
	 *         unchanged, or empty
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, java.util.Comparator<T> comparator) {
		Node<T> check = head;
		while (check != null) {
			if (comparator.compare(check.data, targetData) == 0) {
				size--;
				if (check.prev != null) {
					check.prev.next = check.next;
				}
				if (check.next != null) {
					check.next.prev = check.prev;
				}
				if (head.equals(check)) {
					head = check.next;
				}
				if (tail.equals(check)) {
					tail = check.prev;
				}
				if (size == 0) {
					return null;
				}
				return this;
			}
			check = check.next;
		}
		return this;
	}

	/**
	 * Deletes and returns the first element of the list
	 * 
	 * @return T the first element in the list
	 */
	public T retrieveFirstElement() {
		if (size == 0 || head == null) {
			return null;
		}
		T result;
		result = head.data;
		head = head.next;
		size--;
		return result;
	}

	/**
	 * Deletes and returns the last element of the list
	 * 
	 * @return T the last element in the list
	 */
	public T retrieveLastElement() {
		if (size == 0 || tail == null) {
			return null;
		}
		T result;
		result = tail.data;
		tail = tail.prev;
		size--;
		return result;
	}

	/**
	 * Cycles through each node and adds the data to an Array List, then returns the
	 * Array List
	 * 
	 * @return ArrayList<T> An array list filled with the data in the list
	 */
	public java.util.ArrayList<T> toArrayList() {
		Node<T> check = head;
		ArrayList<T> result = new ArrayList<T>();
		while (check != null && check.prev != tail) {
			result.add(check.data);
			check = check.next;
		}
		return result;
	}
}