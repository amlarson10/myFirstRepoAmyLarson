
/**
 * @author Amy Larson amlarson10@dmacc.edu 2018-03-15 updated 4-2-18 CIS152 
 * this class is an unsorted DOUBLE-ENDED list with the methods needed
 *         to work with a QUEUE
 */
public class LinkedListForQueue {
	private Link1 first; // ref to first Link1 on the list
	private Link1 last; // ref to last item Link1 on the list
	private int nItems;
	
	/**
	 * constructor
	 */
	public LinkedListForQueue() { // constructor
		first = null; // empty list
		last = null; // empty list
		nItems = 0;
	}

	/**
	 *getter for first
	 * @return link first in list
	 */
	public Link1 getFirst() {
		return first;
	}


	/**
	 *getter for last
	 * @return link last in list
	 */
	public Link1 getLast() {
		return last;
	}
	
	/**
	 * checks if list is empty
	 * 
	 * @return true if list is empty
	 */
	public boolean isEmpty() { // true if list is empty
		return (first == null);
	}

	/**
	 * inserts a Link1 at last of list (would be tail of queue)
	 * 
	 * @param newitem
	 */
	public void insertLast(JobListing newitem) {
		Link1 newLink1 = new Link1(newitem);
		if(isEmpty()) {
			first = newLink1;
		}
		else {
			last.next = newLink1;
		}
		last = newLink1;
		nItems++;	
	}
		

/**
 *returns and deletes the first link (would be head of queue
 * @return
 */
public Link1 deleteFirst() {
	if (first ==null) {
		return null;
	}
	Link1 temp = first;
	if (first.next == null){ 
		last = null;}
	first = first.next;
	nItems--;
	return temp;
}
	
	/**
	 * returns the number of Link1s in the list
	 * @return
	 */
	public int size() {
		return nItems;// gets number of items
	}

	/**
	 * displays list starting with last Link1 
	 */
	public void displayListLastToFirst() {
		Link1 current = last; // start at end of list 
		while (current != null) { // until out of list
			current.displayLink(); // print data
			current = current.next; // move to prior Link1
		}
	}
}
