import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import java.awt.Insets;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

/**
 * Creates a panel for managing the queue through a GUI
 */
public class GUIManageQueue extends JPanel {

	private static final long serialVersionUID = 5301135605853993852L; //compiler prompted adding this
	
	//the following variables are declared here so that they are in scope for the methods that use them for updating the panel.
	private JTextField textStatus;
	private JTextArea textAreaJobPeek;
	private Queue thisQueue;
	private Queue otherQueue1; //possible destination queue
	private Queue otherQueue2; //possitble destination queue
	private MoveToQueueButton btnMove1; //button to move to otherQueue1
	private MoveToQueueButton btnMove2; //button to move to otherQueue2
	private	ConditionalButton btnDeleteListing;

/**
* refreshes the panel by rechecking the enable status of buttons and updating text
*/
public void updatePanel() {
	setTextAreaJobPeek();
	setTextStatus();
	btnDeleteListing.toggleBtnEnable(!thisQueue.isEmpty());
	btnMove1.toggleBtnEnable();
	btnMove2.toggleBtnEnable();
}

/**
 *getter for otherQueue1
 * @return
 */
public Queue getOtherQueue1() {
	return otherQueue1;
}

/**
 *getter for otherQueue2
 * @return
 */
public Queue getOtherQueue2() {
	return otherQueue2;
}

/**
 *sets the text to show the job at the head of the queue
 */
private void setTextAreaJobPeek() {
	String showJob = "start";
	if (thisQueue.isEmpty()) {
		showJob = "There are no job listings yet.";
	} else {
		showJob = thisQueue.peek().toString();
	}
	textAreaJobPeek.setText(showJob);
}

/**
 *sets the text to show the current size of the queue
 */
private void setTextStatus() {
	int nItems = thisQueue.size();// gets current number of items
	String status = "";
	if (thisQueue.getMaxSize() > 0) {
		status = thisQueue.getName() + " has " + nItems + " out of " + thisQueue.getMaxSize() + " items.";

		if (thisQueue.isFull()) {
			status += " No new items can be added.";
		}
	} else {
		status = (thisQueue.getName() + " has " + nItems );
		if (nItems == 1) {
			status+=" item.";
		}
		else {
			status+=" items.";
		}
	}
	textStatus.setText(status);
}
	


	/**
	 *Constructor for GUIManageQueue 
	 *Creates the panel, updates text and buttons
	 *Two destination queues are required
	 * @param thisQueue
	 * @param otherQueue1
	 * @param otherQueue2
	 */
	public GUIManageQueue(Queue thisQueue, Queue otherQueue1, Queue otherQueue2) {
		this.thisQueue = thisQueue;
		this.otherQueue1 = otherQueue1;
		this.otherQueue2 = otherQueue2;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 299, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblManageGeneralTo = new JLabel("Manage " + thisQueue.getName());
		GridBagConstraints gbc_lblManageGeneralTo = new GridBagConstraints();
		gbc_lblManageGeneralTo.insets = new Insets(0, 0, 5, 0);
		gbc_lblManageGeneralTo.gridx = 0;
		gbc_lblManageGeneralTo.gridy = 0;
		add(lblManageGeneralTo, gbc_lblManageGeneralTo);

		JToolBar toolBar = new JToolBar();
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.anchor = GridBagConstraints.WEST;
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 2;
		add(toolBar, gbc_toolBar);

		//create button to delete listing
		btnDeleteListing = new ConditionalButton("Delete Job Listing",UIManager.getColor("Button.light"),  false);
		btnDeleteListing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!thisQueue.isEmpty()) {
					thisQueue.dequeue();
				}
				updatePanel();
			}
		});
		toolBar.add(btnDeleteListing);
		
		//create button to move items to another queue
		btnMove1 = new MoveToQueueButton("Move to ", UIManager.getColor("Button.light"), thisQueue, otherQueue1);
		btnMove1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnMove1.moveToQueue();// take the job that is dequeued from this and add it to another queue.
				updatePanel();
			}
		});
		toolBar.add(btnMove1);

		//create button to move items to another queue
		btnMove2 = new MoveToQueueButton("Move to ", UIManager.getColor("Button.light"), thisQueue, otherQueue2);
		btnMove2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnMove2.moveToQueue();// take the job that is dequeued from this and add it to another queue.
				updatePanel();
			}
		});
		toolBar.add(btnMove2);
	
		textStatus = new JTextField();
		GridBagConstraints gbc_textStatus = new GridBagConstraints();
		gbc_textStatus.insets = new Insets(0, 0, 5, 0);
		gbc_textStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_textStatus.gridx = 0;
		gbc_textStatus.gridy = 1;
		add(textStatus, gbc_textStatus);
		textStatus.setColumns(10);
		setTextStatus();
		
		textAreaJobPeek = new JTextArea();
		textAreaJobPeek.setLineWrap(true);
		textAreaJobPeek.setWrapStyleWord(true);
		GridBagConstraints gbc_textAreaJobPeek = new GridBagConstraints();
		gbc_textAreaJobPeek.fill = GridBagConstraints.BOTH;
		gbc_textAreaJobPeek.gridx = 0;
		gbc_textAreaJobPeek.gridy = 3;
		add(textAreaJobPeek, gbc_textAreaJobPeek);
		setTextAreaJobPeek();

	}
	

}
