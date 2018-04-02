/**
 * @author Amy Larson amlarson10@dmacc.edu 2018-03-15 CIS152 updated 4-2-2018
 *         This class contains a single link of strings to be included in a
 *         JobListing linked list
 */
public class Link1 {

	/**
	 * the job listing that is this link
	 */
	public JobListing item;// key
	/**
	 * the next link in the list
	 */
	public Link1 next; // reference to next link


	/**
	 * constructor creates a new link
	 * 
	 * @param newItem JobListing - use a JobListing constructor
	 */
	public Link1(JobListing newItem) { 
		item = newItem; // initialize data
		setNext(null); // (automatically set to null) but I set it for clarity
	}

	/**
	 * prints to the Console the toString information from this link
	 */
	public void displayLink() {
		System.out.println(item.toString());
	}
	
	/**
	 *getter for Link
	 * @return item the JobListing at this link
	 */
	public JobListing getLink() {
		return item;
	}

	/**
	 *getter for next
	 * @return next link
	 */
	public Link1 getNext() {
		return next;
	}

	/**
	 *setter for next
	 * @param next next link
	 */
	public void setNext(Link1 next) {
		this.next = next;
	}

}
