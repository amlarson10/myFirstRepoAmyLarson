/**
 * @author Amy Larson amlarson10@dmacc.edu CIS152
 *3-20-18 updated Apr 2, 2018 
 *
 */

	import java.awt.BorderLayout;
	import java.awt.EventQueue;
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JTextField;
	import javax.swing.JTextArea;
	import javax.swing.JToolBar;
	import javax.swing.JButton;
	import java.awt.Color;
	import java.awt.GridBagLayout;
	import java.awt.GridBagConstraints;
	import java.awt.Insets;
	import java.awt.SystemColor;
	import java.awt.Font;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import java.time.LocalDate;
	import javax.swing.JTabbedPane;
	import javax.swing.JSeparator;
	import java.awt.Dimension;
	import java.awt.Component;
	import javax.swing.border.LineBorder;
	import java.awt.event.FocusAdapter;
	import java.awt.event.FocusEvent;
	import javax.swing.event.ChangeListener;
	import javax.swing.event.ChangeEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;

	/**
	 * This is the main screen of the program
	 */
	public class GUIJobTrackerMain extends JFrame {

		private static final long serialVersionUID = -4742712061010972655L; //compiler prompted to add this
	
		private JPanel contentPane;
		private JTextArea textAreaNotes;
		private JTextField textJobTitle;
		private JTextField textCompany;
		private JTextField textLocation;
		private JTextField textFoundDate;
		private Font fontButton = new Font("Tahoma", Font.PLAIN, 12);
		private Font fontText = new Font("Tahoma", Font.PLAIN, 13);
		private GUIManageQueue panelManagePri;
		private GUIManageQueue panelManageGen;

		//this variable is to assist with generating test data
		private int testCount = 0;

		private static ListQueue genQueue = new ListQueue("General To Do");
		private static ListQueue complQueue = new ListQueue("Completed");
		private final static int MAX_SIZE = 5;// this is declares max size for the priority queue.
		private static ArrayQueue priQueue = new ArrayQueue("Urgent To Do", MAX_SIZE);
		private static MoveToQueueButton btnCompleted;
		private static MoveToQueueButton btnAddPri;
		private static MoveToQueueButton btnAddGen;
	
		/**
		 * setter for foundDate
		 *sets found date field to today's date 
		 */
		private void setFoundDate() {
			textFoundDate.setText(LocalDate.now().toString()); // sets found date field to today's date
		}

		/**
		 * clears form or shows error message
		 * 
		 * @param successful
		 */
		private void checkNewSaved(boolean successful) {
			if (successful) {
				clearAddJobForm();
			} else {
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "Unable to save.", "Alert", JOptionPane.WARNING_MESSAGE);
				// didn't save to queue, add alert box);
			}
		}

		/**
		 * clears fields by setting to null, and sets found date to default
		 */
		private void clearAddJobForm() {
			textJobTitle.setText(null);
			textCompany.setText(null);
			textLocation.setText(null);
			textAreaNotes.setText(null);
			setFoundDate();// automatically defaults to today's date
		}

		/**
		 * This method handles the collection of information from the Add Job Listing
		 * form's fields. It is encapsulated so that all types of save to queues will
		 * follow these same steps.
		 * 
		 * @return JobListing
		 */
		private JobListing saveJobFormFields() {
			LocalDate addDate = LocalDate.parse(textFoundDate.getText());// convert from string to localdate
			JobListing newJob = new JobListing(textCompany.getText(), textJobTitle.getText(), textLocation.getText(),
					addDate, textAreaNotes.getText());
			return newJob;
		}

		/**
		 *checks whether buttons should be enabled and updates
		 */
		public static void updateButtons() {
			btnAddGen.toggleBtnEnable();
			btnAddPri.toggleBtnEnable();
			btnCompleted.toggleBtnEnable();
		};

		/**
		 *refreshes panels and buttons
		 */
		private void updateScreen() {
			panelManagePri.updatePanel();
			panelManageGen.updatePanel();
			updateButtons();

		}

		/**
		 * Launch the application.
		 */
		public static void main(@SuppressWarnings("javadoc") String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						GUIJobTrackerMain frame = new GUIJobTrackerMain();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

		}

		/**
		 * Create the frame
		 */
		public GUIJobTrackerMain() {
			setTitle("Job Listing Tracker");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 750, 502);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);

			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.WEST);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 0, 75, 259, 0, 301, 0 };
			gbl_panel.rowHeights = new int[] { 78, 75, 20, 20, 20, 20, 33, 20, 0, 0, 0, 0, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, 0.0 };
			gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
					Double.MIN_VALUE };
			panel.setLayout(gbl_panel);

			JTextArea textArea = new JTextArea();
			textArea.setRows(3);
			textArea.setFont(fontText);
			textArea.setText(
					"Fill in the information about a job listing. \r\nIf you are going to apply later, add it to one of your To Do Lists.\r\nIf you are applying to the job listing now, click Add to Completed List.\r\n");
			textArea.setLineWrap(true);
			textArea.setForeground(Color.BLACK);
			textArea.setEditable(false);
			textArea.setBackground(SystemColor.scrollbar);
			GridBagConstraints gbc_textArea = new GridBagConstraints();
			gbc_textArea.gridwidth = 5;
			gbc_textArea.insets = new Insets(0, 0, 5, 5);
			gbc_textArea.fill = GridBagConstraints.BOTH;
			gbc_textArea.gridx = 0;
			gbc_textArea.gridy = 1;
			panel.add(textArea, gbc_textArea);

			JLabel label = new JLabel("Job Title");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.anchor = GridBagConstraints.EAST;
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = 2;
			panel.add(label, gbc_label);

			textJobTitle = new JTextField();
			GridBagConstraints gbc_textJobTitle = new GridBagConstraints();
			gbc_textJobTitle.insets = new Insets(0, 0, 5, 5);
			gbc_textJobTitle.fill = GridBagConstraints.HORIZONTAL;
			gbc_textJobTitle.gridx = 2;
			gbc_textJobTitle.gridy = 2;
			panel.add(textJobTitle, gbc_textJobTitle);
			textJobTitle.setColumns(10);

			JLabel lblNotes = new JLabel("Notes");
			GridBagConstraints gbc_lblNotes = new GridBagConstraints();
			gbc_lblNotes.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblNotes.insets = new Insets(0, 0, 5, 5);
			gbc_lblNotes.gridx = 3;
			gbc_lblNotes.gridy = 2;
			panel.add(lblNotes, gbc_lblNotes);

			textAreaNotes = new JTextArea();
			textAreaNotes.setLineWrap(true);
			textAreaNotes.setWrapStyleWord(true);
			textAreaNotes.setBorder(new LineBorder(new Color(0, 0, 0)));
			GridBagConstraints gbc_textAreaNotes = new GridBagConstraints();
			gbc_textAreaNotes.gridheight = 4;
			gbc_textAreaNotes.insets = new Insets(0, 0, 5, 5);
			gbc_textAreaNotes.fill = GridBagConstraints.BOTH;
			gbc_textAreaNotes.gridx = 4;
			gbc_textAreaNotes.gridy = 2;
			panel.add(textAreaNotes, gbc_textAreaNotes);

			JLabel label_1 = new JLabel("Company");
			GridBagConstraints gbc_label_1 = new GridBagConstraints();
			gbc_label_1.anchor = GridBagConstraints.EAST;
			gbc_label_1.insets = new Insets(0, 0, 5, 5);
			gbc_label_1.gridx = 1;
			gbc_label_1.gridy = 3;
			panel.add(label_1, gbc_label_1);
			textJobTitle.setColumns(10);

			textCompany = new JTextField();
			GridBagConstraints gbc_textCompany = new GridBagConstraints();
			gbc_textCompany.insets = new Insets(0, 0, 5, 5);
			gbc_textCompany.fill = GridBagConstraints.HORIZONTAL;
			gbc_textCompany.gridx = 2;
			gbc_textCompany.gridy = 3;
			panel.add(textCompany, gbc_textCompany);
			textCompany.setColumns(10);

			JLabel label_2 = new JLabel("Location");
			GridBagConstraints gbc_label_2 = new GridBagConstraints();
			gbc_label_2.anchor = GridBagConstraints.EAST;
			gbc_label_2.insets = new Insets(0, 0, 5, 5);
			gbc_label_2.gridx = 1;
			gbc_label_2.gridy = 4;
			panel.add(label_2, gbc_label_2);

			textLocation = new JTextField();
			GridBagConstraints gbc_textLocation = new GridBagConstraints();
			gbc_textLocation.insets = new Insets(0, 0, 5, 5);
			gbc_textLocation.fill = GridBagConstraints.HORIZONTAL;
			gbc_textLocation.gridx = 2;
			gbc_textLocation.gridy = 4;
			panel.add(textLocation, gbc_textLocation);
			textLocation.setColumns(10);

			JLabel label_3 = new JLabel("Found Date");
			GridBagConstraints gbc_label_3 = new GridBagConstraints();
			gbc_label_3.anchor = GridBagConstraints.EAST;
			gbc_label_3.insets = new Insets(0, 0, 5, 5);
			gbc_label_3.gridx = 1;
			gbc_label_3.gridy = 5;
			panel.add(label_3, gbc_label_3);

			textFoundDate = new JTextField();
			textFoundDate.setEditable(false);
			GridBagConstraints gbc_textFoundDate = new GridBagConstraints();
			gbc_textFoundDate.anchor = GridBagConstraints.NORTH;
			gbc_textFoundDate.insets = new Insets(0, 0, 5, 5);
			gbc_textFoundDate.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFoundDate.gridx = 2;
			gbc_textFoundDate.gridy = 5;
			panel.add(textFoundDate, gbc_textFoundDate);
			textFoundDate.setColumns(10);
			setFoundDate();// automatically defaults to today's date

			JToolBar toolBar = new JToolBar();
			GridBagConstraints gbc_toolBar = new GridBagConstraints();
			gbc_toolBar.anchor = GridBagConstraints.WEST;
			gbc_toolBar.insets = new Insets(0, 0, 5, 5);
			gbc_toolBar.gridwidth = 5;
			gbc_toolBar.gridx = 0;
			gbc_toolBar.gridy = 6;
			panel.add(toolBar, gbc_toolBar);

			JSeparator separatorAddJob = new JSeparator();
			separatorAddJob.setAlignmentY(Component.TOP_ALIGNMENT);
			separatorAddJob.setSize(new Dimension(28, 10));
			separatorAddJob.setOpaque(true);
			separatorAddJob.setForeground(Color.BLACK);
			separatorAddJob.setBackground(Color.BLACK);
			GridBagConstraints gbc_separatorAddJob = new GridBagConstraints();
			gbc_separatorAddJob.anchor = GridBagConstraints.NORTH;
			gbc_separatorAddJob.fill = GridBagConstraints.HORIZONTAL;
			gbc_separatorAddJob.ipady = 10;
			gbc_separatorAddJob.ipadx = 10;
			gbc_separatorAddJob.weightx = 1.0;
			gbc_separatorAddJob.weighty = 1.0;
			gbc_separatorAddJob.gridwidth = 5;
			gbc_separatorAddJob.insets = new Insets(0, 0, 5, 5);
			gbc_separatorAddJob.gridx = 0;
			gbc_separatorAddJob.gridy = 7;
			panel.add(separatorAddJob, gbc_separatorAddJob);

			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

			//this button will generate data helpful for testing- would remove before release to users
			JButton btnTest = new JButton("Test");
			btnTest.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					textJobTitle.setText("testJobName" + testCount );
					textCompany.setText("testCompany" + testCount);
					textLocation.setText("Des Moines, IA");
					testCount+=1;
					if (testCount % 3 == 0) {
						textAreaNotes.setText("url is www.jobapplication.com. Need to create a custom cover letter.");
					} else
						textAreaNotes.setText(
								"Existing resume is close, but should emphasize training experience more. No cover letters accepted. Good commute. url is www.jobapplication.com");
					}
				});
			
			GridBagConstraints gbc_btnTest = new GridBagConstraints();
			gbc_btnTest.insets = new Insets(0, 0, 5, 5);
			gbc_btnTest.gridx = 4;
			gbc_btnTest.gridy = 8;
			panel.add(btnTest, gbc_btnTest);
			GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
			gbc_tabbedPane.gridheight = 2;
			gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
			gbc_tabbedPane.anchor = GridBagConstraints.NORTH;
			gbc_tabbedPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_tabbedPane.gridx = 1;
			gbc_tabbedPane.gridy = 9;
			gbc_tabbedPane.gridwidth = 5;
			panel.add(tabbedPane, gbc_tabbedPane);

			btnAddGen = new MoveToQueueButton("Add to ", new Color(0, 128, 0), null, genQueue); // add to
																											// genQueue
			btnAddGen.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// confirms that new queue was saved, if yes will clear form
					checkNewSaved(btnAddGen.addToQueue(saveJobFormFields()));
					updateScreen();
				}
			});
			btnAddGen.setFont(fontButton);
			toolBar.add(btnAddGen);

			btnAddPri = new MoveToQueueButton("Add to ", Color.GREEN, null, priQueue);
			btnAddPri.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// confirms that new queue was saved, if so will clear form
					checkNewSaved(btnAddPri.addToQueue(saveJobFormFields()));
					updateScreen();
				}
			});
			btnAddPri.setFont(fontButton);
			toolBar.add(btnAddPri);

			JButton btnClearJobForm = new JButton("    Clear Form    ");
			btnClearJobForm.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					clearAddJobForm();
					updateScreen();
				}
			});
			btnClearJobForm.setFont(fontButton);
			btnClearJobForm.setBackground(Color.LIGHT_GRAY);
			toolBar.add(btnClearJobForm);

			btnCompleted = new MoveToQueueButton("Mark ", new Color(0, 128, 0), null,complQueue);
			btnCompleted.setFont(fontButton);
			btnClearJobForm.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					//move to completed queue???
					clearAddJobForm();
					updateScreen();
				}
			});
			toolBar.add(btnCompleted);

			panelManageGen = new GUIManageQueue(genQueue, priQueue, complQueue);
			panelManageGen.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					updateScreen();
				}
			});
			tabbedPane.addTab("Manage "+genQueue.getName(), null, panelManageGen, null);

			panelManagePri = new GUIManageQueue(priQueue, genQueue, complQueue);
			panelManagePri.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					updateScreen();
				}
			});
			tabbedPane.addTab("Manage "+priQueue.getName(), null, panelManagePri, null);
			tabbedPane.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					updateScreen();
				}
			});

		}
	}
