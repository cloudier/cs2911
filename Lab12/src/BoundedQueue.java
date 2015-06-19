import java.util.concurrent.locks.*;

/**
 * A first-in, first-out bounded collection of objects.
 * 
 * Use a re-entrant lock with two conditions to make the implementation threadsafe
 * The add and remove methods should acquire the lock before executing, then release the lock when finished
 * When the queue is full, the add method should wait for space available, and signal value available before returning
 * When the queue is empty, the remove method should wait for value available, and signal space available before returning
 */
public class BoundedQueue<E> {
	private Object[] elements;
	private int head;
	private int tail;
	private int size;
	private boolean debug;
	
	private final ReentrantLock lock;
	private final Condition spaceAvail;
	private final Condition valueAvail;

	/**
	 * Constructs an empty queue.
	 * 
	 * @param capacity
	 *            the maximum capacity of the queue
	 */
	public BoundedQueue(int capacity) {
		elements = new Object[capacity];
		head = 0;
		tail = 0;
		size = 0;
		lock = new ReentrantLock();
		spaceAvail = lock.newCondition();
		valueAvail = lock.newCondition();
	}

	/**
	 * Appends an object at the tail.
	 * 
	 * @param newValue
	 *            the object to be appended
	 * @throws InterruptedException 
	 * @precondition !isFull();
	 */
	public void add(E newValue) throws InterruptedException {
		lock.lock(); // 1 acquire lock
		try {
			if (size == elements.length) { // 2 checks if queue is full
				if (debug) System.out.print("queue full ");
				spaceAvail.await(); // 3 since queue is full, wait for space available
				tail = 0;
			}
			if (debug) System.out.print("add ");
			elements[tail] = newValue;
			tail = (tail + 1) % 10;
			size++;
			if (debug) System.out.println("head = " + head + ", tail = " + tail + ", size = " + size);
			valueAvail.signal(); // 4 signal value available before returning
		} finally {
			lock.unlock(); // 5 release lock when finished
		}
	}

	/**
	 * Removes the object at the head.
	 * 
	 * @return the object that has been removed from the queue
	 * @throws InterruptedException 
	 * @precondition !isEmpty()
	 */
	@SuppressWarnings("unchecked")
	public E remove() throws InterruptedException {
		lock.lock(); // 1 acquire lock
		try {
			if (size == 0) { // 2 checks if queue is empty
				if (debug) System.out.println("queue empty ");
				valueAvail.await(); // 3 since queue is empty, wait for value available
				head = 0;
			}
			if (debug) System.out.print("remove ");
			E r = (E) elements[head];
			head = (head + 1) % 10;
			size--;
			if (debug) System.out.println("head = " + head + ", tail = " + tail + ", size = " + size);
			spaceAvail.signal(); // 4 signal space available before returning
			return r;
		} finally {
			lock.unlock(); // 5 release lock when finished
		}
	}

	public boolean isFull() {
		return size == elements.length;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void setDebug(boolean newValue) {
		debug = newValue;
	}
}
