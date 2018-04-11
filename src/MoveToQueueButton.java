import java.awt.Color;

/**
 * @author Amy Larson amlarson10@dmacc.edu 3/19/2018 updated 4/2/18 This class
 *         creates a conditional button that handles adding or moving an item to
 *         a queue. The button may change appearance based on characteristics of
 *         the queue.
 */

public class MoveToQueueButton extends ConditionalButton {
	private static final long serialVersionUID = -2258682931403914373L; // compiler prompted this
	private Queue toQueue;
	private Queue thisQueue;
	private boolean movePossible;

	/**
	 * Constructor for MoveToQueueButton
	 * 
	 * @param onTextPrefix
	 *            When button is active, put phrase before name of queue
	 * @param onColor
	 *            When button is active, use this color
	 * @param thisQueue
	 *            queue item is currently in
	 * @param toQueue
	 *            destination queue for item
	 */
	public MoveToQueueButton(String onTextPrefix, Color onColor, Queue thisQueue, Queue toQueue) {
		super();
		this.toQueue = toQueue;
		this.thisQueue = thisQueue;
		setOnText(onTextPrefix);
		setOffText();
		setOnColor(onColor);
		toggleBtnEnable();
	}

	/**
	 * sets the on color for the active button
	 * 
	 * @see ConditionalButton#setOnColor(java.awt.Color)
	 * @param onColor
	 */
	public void setOnColor(Color onColor) {
		super.setOnColor(onColor);

	}

	/**
	 * moves an item from one queue to a different queue, if possible
	 * 
	 * @return true if move is possible
	 */
	public boolean moveToQueue() {
		if (!isMovePossible()) {
			return false; // will not move to new queue
		}
		boolean done = toQueue.enqueue(thisQueue.dequeue());// take the job that is dequeued and add it to another
															// queue.
		toggleBtnEnable();
		return done;
	}

	/**
	 * adds to item to a queue
	 * 
	 * @param newItem
	 * @return true if successful, false if unable to add
	 */
	public boolean addToQueue(JobListing newItem) {
		boolean done = toQueue.enqueue(newItem);
		toggleBtnEnable();
		return done;
	}

	/**
	 * checks if button should be active and updates look, text and enable of
	 * button
	 */
	public void toggleBtnEnable() {
		if (!isMovePossible()) {// sets button to the 'off' look
			super.toggleBtnEnable(false);
		} else {// sets button to it's 'on' look
			super.toggleBtnEnable(true);
		}
	}

	/**
	 * sets the text that will appear when butten is enabled
	 * @param onTextPrefix will prefix the queue name
	 */
	public void setOnText(String onTextPrefix) {
		super.setOnText(onTextPrefix + toQueue.getName());
	}

	/**
	 * rechecks if move is possible and returns movePossible
	 * @return true if move is possible, false if not possible
	 */
	public boolean isMovePossible() {
		setMovePossible();
		return movePossible;
	}

	/**
	 * checks if the destination queue has room and if moving from and existing
	 * queue, that there is something to move
	 */
	public void setMovePossible() {
		if (toQueue.isFull()) {// check that destination queue has room
			this.movePossible = false;
		} else {
			if (thisQueue != null && thisQueue.isEmpty()) {// if moving from an existing queue, check that there is
															// something to move
				this.movePossible = false;
			} else {
				this.movePossible = true;
			}
		}
	}

}