package DequeWeek2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] array;
	private int n;

	private class RandomizedQueueIterator implements Iterator<Item> {
		private int i = 0;
		private Item[] temp;

		public RandomizedQueueIterator() {
			temp = (Item[]) new Object[n];

			for (int j = 0; j < n; j++) {
				temp[j] = array[j];
			}
		}

		public boolean hasNext() {
			return i < n;
		}

		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			int k = (int) (Math.random() * (n - i));
			Item item = temp[k];
			temp[k] = temp[n - (++i)];
			temp[n - i] = null;
			return item;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public RandomizedQueue() {
		// construct an empty randomized queue
		array = (Item[]) new Object[1];
		n = 0;

	}

	public boolean isEmpty() {
		// is the queue empty?
		return n == 0;
	}

	public int size() {
		// return the number of items on the queue
		return n;
	}

	public void enqueue(Item item) {
		// add the item
		if (item == null)
			throw new NullPointerException();
		if (n >= array.length)
			resize();
		array[n] = item;
		n++;
		return;
	}

	private void resize() {
		Item[] holder = (Item[]) new Object[n * 2];
		for (int i = 0; i < n; i++) {
			holder[i] = array[i];
		}
		array = holder;
		return;

	}

	public Item dequeue() {
		// remove and return a random item
		if (n == 0)
			throw new NoSuchElementException();
		Random random = new Random();
		int target = random.nextInt(n);
		Item item = array[target];
		if(target == (n-1)){
			array[target] = null;
			return item;
		}
		for (int i = target; i < n; i++) {
			array[i] = array[i + 1];
		}
		array[n] = null;
		n--;
		return item;
	}

	public Item sample() {
		// return (but do not remove) a random item
		if (n == 0)
			throw new NoSuchElementException();
		Random random = new Random();
		int target = random.nextInt(n);
		return array[target];
	}

	public Iterator<Item> iterator() {
		// return an independent iterator over items in random order
		return new RandomizedQueueIterator();
	}

	public static void main(String[] args) {

	}
}