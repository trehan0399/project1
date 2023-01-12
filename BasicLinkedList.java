package listClasses;

import java.util.*;

//import myLinkedList.MyLinkedList.Node;

public class BasicLinkedList<T> implements Iterable<T> {

	/* Node definition */
	protected class Node {
		protected T data;
		protected Node next;

		protected Node(T data) {
			this.data = data;
			next = null;
		}
	}

	/* We have both head and tail */
	protected Node head, tail;

	/* size */
	protected int listSize;

	public int getSize() {
		return listSize;
	}

	// adds node to the end
	public BasicLinkedList<T> addToEnd(T data) {
		Node newNode = new Node(data);
		if (tail != null) {
			tail.next = newNode;
		}
		tail = newNode;
		if (head == null) {
			head = tail;
		}
		listSize++;
		return this;
	}

	// adds node to the front
	public BasicLinkedList<T> addToFront(T data) {
		Node newNode = new Node(data);
		if (head != null) {
			newNode.next = head;
		}
		head = newNode;
		if (tail == null) {
			tail = head;
		}
		listSize++;
		return this;
	}

	// gets first node data
	public T getFirst() {
		return head == null ? null : head.data;

	}

	// gets last node data
	public T getLast() {
		return tail == null ? null : tail.data;

	}

	// gets and removes the first element
	public T retrieveFirstElement() {
		if (head != null) {
			T newData = head.data;
			if (listSize == 1) {
				tail = null;
			}
			head = head.next;
			listSize--;
			return newData;
		} else {
			return null;
		}
	}

	// gets and removes the last element
	public T retrieveLastElement() {
		T newData = null;
		if (head == null) {
			return newData;
		}
		Node curr = head;
		if (listSize == 1) {
			newData = tail.data;
			head = null;
			tail = null;
			listSize--;
			return newData;
		}
		while (curr.next != tail) {
			curr = curr.next;
		}
		newData = tail.data;
		curr.next = null;
		tail = curr;
		listSize--;
		return newData;
	}

	// removes all instances of targetData
	public BasicLinkedList<T> remove(T data, Comparator<T> comparator) {
		Node next = head;
		Node prev = null;
		while (next != null) {
			if (comparator.compare(next.data, data) == 0) {
				listSize--;
				if (prev != null) {
					prev.next = next.next;
				} else {
					head = next.next;
				}
				if (next == tail) {
					tail = prev;
				}
			}
			prev = next;
			next = next.next;
		}
		return this;
	}

	// reverse arraylist of all the nodes data
	public ArrayList<T> getReverseArrayList() {
		ArrayList<T> answer = new ArrayList<>();
		if (head == null) {
			return answer;
		} else {
			reverse(head, answer);
			return answer;
		}
	}

// helper method for reverse arraylist<T>
	private void reverse(Node headAux, ArrayList<T> answer) {
		if (headAux != null) {
			answer.add(0, headAux.data);
			reverse(headAux.next, answer);
		}
	}

// reverse the linked list
	public BasicLinkedList<T> getReverseList() {
		BasicLinkedList<T> reverse = new BasicLinkedList<T>();
		if (head != null) {
			reverseBasic(head, reverse);
		}
		return reverse;
	}

// helper method for reverseList<T>
	private void reverseBasic(Node headAux, BasicLinkedList<T> answer) {
		if (headAux != null) {
			answer.addToFront(headAux.data);
			reverseBasic(headAux.next, answer);
		}
	}

// iterator method
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node curr = head;

			public boolean hasNext() {
				return curr != null;
			}

			public T next() {
				T toReturn = curr.data;

				curr = curr.next;
				return toReturn;
			}
		};
	}

}
