package DequeWeek2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node<Item> first = null;
	private Node<Item> last = null;
	private int n;

	private class Node<Item> {
		Item item;
		Node<Item> next;
		Node<Item> previous;
	}

	private class DequeIterator implements Iterator<Item> {
		private Node<Item> current;

		public DequeIterator() {
			this.current = first;
		}

		public boolean hasNext() {
			return current != null;
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public Deque() {
		// construct an empty deque
		n = 0;
	}

	public boolean isEmpty() {
		// is the deque empty?
		return first == null;
	}

	public int size() {
		// return the number of items on the deque
		return n;
	}

	public void addFirst(Item item) {
		// add the item to the front
		if (item == null)
			throw new NullPointerException();
		if (n == 0) {
			addToEmpty(item);
			return;
		}
		Node<Item> oldFirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldFirst;
		oldFirst.previous = first;
		n++;
	}

	private void addToEmpty(Item item) {
		Node<Item> newNode = new Node<>();
		newNode.item = item;
		newNode.next = null;
		newNode.previous = null;
		first = newNode;
		last = newNode;
		n++;
		return;
	}

	public void addLast(Item item) {
		// add the item to the end
		if (item == null)
			throw new NullPointerException();
		if (n == 0) {
			addToEmpty(item);
			return;
		}
		Node<Item> newLast = new Node<>();
		newLast.item = item;
		newLast.previous = last;
		last.next = newLast;
		last = newLast;
		last.next = null;
		n++;
	}

	public Item removeFirst() {
		// remove and return the item from the front
		if (n == 0)
			throw new NoSuchElementException();
		Item item = first.item;
		if (n == 1){
			first = null;
			last = null;
		}
		else{
			first = first.next;
			first.previous = null;
		}
		n--;
		return item;
	}

	public Item removeLast() {
		// remove and return the item from the end
		if (n == 0)
			throw new NoSuchElementException();
		Item item = last.item;
		if (n == 1){
			first = null;
			last = null;
		}
		else{
			last = last.previous;
			last.next = null;
		}
		n--;
		return item;
	}

	public Iterator<Item> iterator() {
		// return an iterator over items in order from front to end
		return new DequeIterator();
	}

	public static void main(String[] args) {

	}
}