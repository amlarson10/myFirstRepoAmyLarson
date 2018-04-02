
/**
 * @author Amy Larson amlarson10@dmacc.edu CIS152  4-2-2018
 * Abstract class for Queues within this project. This abstract class exists so the GUI can interact with Queues regardless of their implementation.
 */

public abstract class Queue {
	private String name;
	private int maxSize;
	
	/**
	 * Constructor for a queue. Requires a friendly name and a max size (may be null for unlimited)
	 * @param queueName The name that will appear in instructions and on buttons. No spaces before or after. May include "The " at the start
	 * @param maxSize Maximum size greater than 0 is required for arrays. Set to 0 for non-arrays with unlimited size.
	 */
	public Queue(String queueName, int maxSize) {
		this.name = queueName;
		this.maxSize = maxSize;
	}
	
	/**
	 *getter for name
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 *setter for name
	 * @param name Friendly name for queue
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * In case of arrays, set to array size. Set to 0 if no max size.
	 * @param maxSize In case of arrays, set to array size. Set to null if no max size.
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}
	
	/**
	 * gets maximum size of the queue in order to know how many items could be stored in the array.
	 * @return
	 */
	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * remove item at head of queue
	 * @return item at head of queue
	 */
	public abstract JobListing dequeue();

	/**
	 * add new item to the tail of the queue, check if possible and return false if item not added
	 * @param newListing
	 * @return true if item added, false if not able to add
	 */
	public abstract boolean enqueue(JobListing newListing);
	
	/**
	 * check if queue is empty
	 * @return true if no items in the queue
	 */
	public abstract boolean isEmpty();
	
	/**
	 * check if queue is full
	 * @return true if max size has been reached
	 */
	public abstract boolean isFull();

	/**
	 * returns item at head of queue without removing it
	 * @return  item at head of queue
	 */
	public abstract JobListing peek();
	
	/**
	 * prints information about the contents of the queue to the console
	 */
	public abstract void print();
	
	/**
	 * counts number of items in the queue
	 * @return number of items
	 */
	public abstract int size();
}
