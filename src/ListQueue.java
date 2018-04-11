
/**
 * @author Amy Amy Larson amlarson10@dmacc.edu 2018-02-15 updated 4/2/18 CIS152 This class
 *         creates a queue that uses a linked list
 */
public class ListQueue extends Queue {
	private LinkedListForQueue myList;

	/**
	 * Constructor for ListQueue. max size is automatically set to 0, indicating no limit.
	 * @param queueName
	 */
	public ListQueue(String queueName) {
		super(queueName, 0);
		myList = new LinkedListForQueue();
	}


	/**
	 * adds a new item to the queue, queue is modified
	 * @see Queue#enqueue(JobListing)
	 * @param item
	 * @return
	 */
	public boolean enqueue(JobListing item) {
		myList.insertLast(item);
		return true;
	}

	/**
	 * removes and returns an item, queue is modified.
	 * @see Queue#dequeue()
	 * @return
	 */
	public JobListing dequeue() {
		Link1 link = myList.deleteFirst(); // get link and remove from List
		return link.getLink();
	}

	/**
	 * returns a boolean and tests for an empty queue, queue is not modified.
	 * @see Queue#isEmpty()
	 * @return
	 */
	public boolean isEmpty() { // true if queue is empty
		return myList.isEmpty();
	}

	/**
	 * returns the int size of the queue, queue is not modified
	 * @see Queue#size()
	 * @return
	 */
	public int size() {
		return myList.size();
	}

	/**
	 * prints the queue from front to rear, queue is not modified.
	 * @see Queue#print()
	 */
	public void print() {
		System.out.println("Display queue front to back:");
		myList.displayListLastToFirst();
	}

	/**
	 * gets the front element, queue is not modified.
	 * @see Queue#peek()
	 * @return
	 */
	public JobListing peek() {
		Link1 temp = myList.getFirst();
		return temp.item;
	}

	/**
	 *Always returns false, linked list never full 
	 * @see Queue#isFull()
	 * @return
	 */
	public boolean isFull() {
		return false; // list queue is never full
	}

}
