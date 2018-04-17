

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
	import java.awt.Component;
	import javax.swing.border.LineBorder;
	import javax.swing.event.ChangeListener;
	import javax.swing.event.ChangeEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

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
		private static JButton btnClearJobForm;
	
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
			updateScreen();
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
		private void addSubmit(MoveToQueueButton btn) {
			JobListing info = saveJobFormFields();
			boolean saved = btn.addToQueue(info);
			checkNewSaved(saved);
			updateScreen();
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
			setBounds(100, 100, 750, 735);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);

			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.WEST);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 0, 75, 259, 0, 301, 0 };
			gbl_panel.rowHeights = new int[] { 3, 0, -11, 80, 75, 20, 20, 20, 20, 33, 0, 0, 0, 0, 63, 0 };
			gbl_panel.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, 1.0, 0.0 };
			gbl_panel.rowWeights = new double[] { 0.0, 2.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0,
					Double.MIN_VALUE };
			panel.setLayout(gbl_panel);
			
			JFormattedTextField frmtdtxtfldAddAJob = new JFormattedTextField();
			frmtdtxtfldAddAJob.setText("Add a Job Listing");
			frmtdtxtfldAddAJob.setHorizontalAlignment(SwingConstants.CENTER);
			frmtdtxtfldAddAJob.setFont(new Font("Tahoma", Font.BOLD, 15));
			frmtdtxtfldAddAJob.setEditable(false);
			frmtdtxtfldAddAJob.setColumns(5);
			frmtdtxtfldAddAJob.setBackground(Color.WHITE);
			GridBagConstraints gbc_frmtdtxtfldAddAJob = new GridBagConstraints();
			gbc_frmtdtxtfldAddAJob.anchor = GridBagConstraints.SOUTH;
			gbc_frmtdtxtfldAddAJob.gridwidth = 4;
			gbc_frmtdtxtfldAddAJob.insets = new Insets(0, 0, 5, 5);
			gbc_frmtdtxtfldAddAJob.fill = GridBagConstraints.HORIZONTAL;
			gbc_frmtdtxtfldAddAJob.gridx = 1;
			gbc_frmtdtxtfldAddAJob.gridy = 3;
			panel.add(frmtdtxtfldAddAJob, gbc_frmtdtxtfldAddAJob);

			JTextArea textAreaInstructions = new JTextArea();
			textAreaInstructions.setRows(3);
			textAreaInstructions.setFont(fontText);
			textAreaInstructions.setText(
					"Fill in the information about a job listing. \r\nIf you are going to apply later, add it to one of your To Do Lists.\r\nIf you are applying to the job listing now, click Add to Completed List.\r\n");
			textAreaInstructions.setLineWrap(true);
			textAreaInstructions.setForeground(Color.BLACK);
			textAreaInstructions.setEditable(false);
			textAreaInstructions.setBackground(SystemColor.scrollbar);
			GridBagConstraints gbc_textAreaInstructions = new GridBagConstraints();
			gbc_textAreaInstructions.anchor = GridBagConstraints.NORTH;
			gbc_textAreaInstructions.gridwidth = 4;
			gbc_textAreaInstructions.insets = new Insets(0, 0, 5, 5);
			gbc_textAreaInstructions.fill = GridBagConstraints.HORIZONTAL;
			gbc_textAreaInstructions.gridx = 1;
			gbc_textAreaInstructions.gridy = 4;
			panel.add(textAreaInstructions, gbc_textAreaInstructions);

			JLabel lblJobTitle = new JLabel("Job Title");
			GridBagConstraints gbc_lblJobTitle = new GridBagConstraints();
			gbc_lblJobTitle.anchor = GridBagConstraints.EAST;
			gbc_lblJobTitle.insets = new Insets(0, 0, 5, 5);
			gbc_lblJobTitle.gridx = 1;
			gbc_lblJobTitle.gridy = 5;
			panel.add(lblJobTitle, gbc_lblJobTitle);

			textJobTitle = new JTextField();
			GridBagConstraints gbc_textJobTitle = new GridBagConstraints();
			gbc_textJobTitle.insets = new Insets(0, 0, 5, 5);
			gbc_textJobTitle.fill = GridBagConstraints.HORIZONTAL;
			gbc_textJobTitle.gridx = 2;
			gbc_textJobTitle.gridy = 5;
			panel.add(textJobTitle, gbc_textJobTitle);
			textJobTitle.setColumns(10);

			JLabel lblNotes = new JLabel("Notes");
			GridBagConstraints gbc_lblNotes = new GridBagConstraints();
			gbc_lblNotes.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblNotes.insets = new Insets(0, 0, 5, 5);
			gbc_lblNotes.gridx = 3;
			gbc_lblNotes.gridy = 5;
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
			gbc_textAreaNotes.gridy = 5;
			panel.add(textAreaNotes, gbc_textAreaNotes);

			JLabel lblCompany = new JLabel("Company");
			GridBagConstraints gbc_lblCompany = new GridBagConstraints();
			gbc_lblCompany.anchor = GridBagConstraints.EAST;
			gbc_lblCompany.insets = new Insets(0, 0, 5, 5);
			gbc_lblCompany.gridx = 1;
			gbc_lblCompany.gridy = 6;
			panel.add(lblCompany, gbc_lblCompany);
			textJobTitle.setColumns(10);

			textCompany = new JTextField();
			GridBagConstraints gbc_textCompany = new GridBagConstraints();
			gbc_textCompany.insets = new Insets(0, 0, 5, 5);
			gbc_textCompany.fill = GridBagConstraints.HORIZONTAL;
			gbc_textCompany.gridx = 2;
			gbc_textCompany.gridy = 6;
			panel.add(textCompany, gbc_textCompany);
			textCompany.setColumns(10);

			JLabel lblLocation = new JLabel("Location");
			GridBagConstraints gbc_lblLocation = new GridBagConstraints();
			gbc_lblLocation.anchor = GridBagConstraints.EAST;
			gbc_lblLocation.insets = new Insets(0, 0, 5, 5);
			gbc_lblLocation.gridx = 1;
			gbc_lblLocation.gridy = 7;
			panel.add(lblLocation, gbc_lblLocation);

			textLocation = new JTextField();
			GridBagConstraints gbc_textLocation = new GridBagConstraints();
			gbc_textLocation.insets = new Insets(0, 0, 5, 5);
			gbc_textLocation.fill = GridBagConstraints.HORIZONTAL;
			gbc_textLocation.gridx = 2;
			gbc_textLocation.gridy = 7;
			panel.add(textLocation, gbc_textLocation);
			textLocation.setColumns(10);

			JLabel lblFoundDate = new JLabel("Found Date");
			GridBagConstraints gbc_lblFoundDate = new GridBagConstraints();
			gbc_lblFoundDate.anchor = GridBagConstraints.EAST;
			gbc_lblFoundDate.insets = new Insets(0, 0, 5, 5);
			gbc_lblFoundDate.gridx = 1;
			gbc_lblFoundDate.gridy = 8;
			panel.add(lblFoundDate, gbc_lblFoundDate);

			textFoundDate = new JTextField();
			textFoundDate.setEditable(false);
			GridBagConstraints gbc_textFoundDate = new GridBagConstraints();
			gbc_textFoundDate.anchor = GridBagConstraints.NORTH;
			gbc_textFoundDate.insets = new Insets(0, 0, 5, 5);
			gbc_textFoundDate.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFoundDate.gridx = 2;
			gbc_textFoundDate.gridy = 8;
			panel.add(textFoundDate, gbc_textFoundDate);
			textFoundDate.setColumns(10);
			setFoundDate();// automatically defaults to today's date

			JToolBar toolBarAddJobButtons = new JToolBar();
			GridBagConstraints gbc_toolBarAddJobButtons = new GridBagConstraints();
			gbc_toolBarAddJobButtons.anchor = GridBagConstraints.WEST;
			gbc_toolBarAddJobButtons.insets = new Insets(0, 0, 5, 5);
			gbc_toolBarAddJobButtons.gridwidth = 4;
			gbc_toolBarAddJobButtons.gridx = 1;
			gbc_toolBarAddJobButtons.gridy = 9;
			panel.add(toolBarAddJobButtons, gbc_toolBarAddJobButtons);

			JTabbedPane tabbedPaneManageQueues = new JTabbedPane(JTabbedPane.TOP);
					
							//this button will generate data helpful for testing- remove before release to users
							JButton btnTest = new JButton("Test");
							btnTest.setToolTipText("This button is for testing purposes only. It will generate test data in the form.");
							btnTest.setForeground(new Color(0, 0, 0));
							btnTest.setBackground(new Color(255, 255, 255));
							btnTest.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									textJobTitle.setText("testJobName" + testCount );
									textCompany.setText("testCompany" + testCount);
									textLocation.setText("Des Moines, IA");
									testCount+=1;
									if (testCount % 3 == 0) {
										textAreaNotes.setText("www.hardjobapplication.com. This is a priority. Create a cover letter and customize resume.");
									} else
										textAreaNotes.setText("Apply online at www.easyjobapplication.com. No deadline listed.");
									}
								});
							
							GridBagConstraints gbc_btnTest = new GridBagConstraints();
							gbc_btnTest.insets = new Insets(0, 0, 5, 5);
							gbc_btnTest.gridx = 1;
							gbc_btnTest.gridy = 10;
							panel.add(btnTest, gbc_btnTest);
							panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textJobTitle, textCompany, textLocation, textFoundDate, textAreaNotes, btnAddGen, btnAddPri, btnClearJobForm, btnCompleted, btnTest, panelManageGen, panelManagePri}));
							setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textJobTitle, textCompany, textLocation, textFoundDate, btnAddGen, btnAddPri, btnClearJobForm, btnCompleted, btnTest, panelManageGen, panelManagePri}));
					
					JFormattedTextField frmtdtxtfldManageToDo = new JFormattedTextField();
					frmtdtxtfldManageToDo.setHorizontalAlignment(SwingConstants.CENTER);
					frmtdtxtfldManageToDo.setFont(new Font("Tahoma", Font.BOLD, 15));
					frmtdtxtfldManageToDo.setBackground(Color.WHITE);
					frmtdtxtfldManageToDo.setColumns(5);
					frmtdtxtfldManageToDo.setEditable(false);
					frmtdtxtfldManageToDo.setText("Manage To Do Lists");
					GridBagConstraints gbc_frmtdtxtfldManageToDo = new GridBagConstraints();
					gbc_frmtdtxtfldManageToDo.anchor = GridBagConstraints.SOUTH;
					gbc_frmtdtxtfldManageToDo.fill = GridBagConstraints.HORIZONTAL;
					gbc_frmtdtxtfldManageToDo.gridwidth = 4;
					gbc_frmtdtxtfldManageToDo.insets = new Insets(0, 0, 5, 5);
					gbc_frmtdtxtfldManageToDo.gridx = 1;
					gbc_frmtdtxtfldManageToDo.gridy = 11;
					panel.add(frmtdtxtfldManageToDo, gbc_frmtdtxtfldManageToDo);
			GridBagConstraints gbc_tabbedPaneManageQueues = new GridBagConstraints();
			gbc_tabbedPaneManageQueues.gridheight = 2;
			gbc_tabbedPaneManageQueues.insets = new Insets(0, 0, 5, 5);
			gbc_tabbedPaneManageQueues.anchor = GridBagConstraints.NORTH;
			gbc_tabbedPaneManageQueues.fill = GridBagConstraints.HORIZONTAL;
			gbc_tabbedPaneManageQueues.gridx = 1;
			gbc_tabbedPaneManageQueues.gridy = 12;
			gbc_tabbedPaneManageQueues.gridwidth = 4;
			panel.add(tabbedPaneManageQueues, gbc_tabbedPaneManageQueues);

			btnAddGen = new MoveToQueueButton("Add to ", new Color(0, 128, 0), null, genQueue); // add to
			btnAddGen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnAddGen.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					addSubmit(btnAddGen);
				}
			});

																							// genQueue
			btnAddGen.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					addSubmit(btnAddGen);
				}
			});
			btnAddGen.setFont(fontButton);
			btnAddGen.setToolTipText("Add this job to the general to do list and clear the form.");
			toolBarAddJobButtons.add(btnAddGen);

			btnAddPri = new MoveToQueueButton("Add to ", Color.GREEN, null, priQueue);
			btnAddPri.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					addSubmit(btnAddPri);
				}
			});
			btnAddPri.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					addSubmit(btnAddPri);
				}
			});
			btnAddPri.setFont(fontButton);
			btnAddPri.setToolTipText("Add this job to the priority to do list and clear the form.");
			toolBarAddJobButtons.add(btnAddPri);

			btnClearJobForm = new JButton("    Clear Form    ");
			btnClearJobForm.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					clearAddJobForm();
				}
			});
			btnClearJobForm.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					clearAddJobForm();
				}
			});
			btnClearJobForm.setFont(fontButton);
			btnClearJobForm.setBackground(Color.LIGHT_GRAY);
			btnClearJobForm.setToolTipText("Clear the form without saving.");
			toolBarAddJobButtons.add(btnClearJobForm);

			btnCompleted = new MoveToQueueButton("Mark ", new Color(0, 128, 0), null,complQueue);
			btnCompleted.setFont(fontButton);
			btnClearJobForm.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					addSubmit(btnCompleted);
				}
			});
			btnCompleted.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					addSubmit(btnCompleted);
				}
			});
			btnCompleted.setToolTipText("Mark this job listing completed and clear the form.");
			toolBarAddJobButtons.add(btnCompleted);

			panelManageGen = new GUIManageQueue(genQueue, priQueue, complQueue);
			tabbedPaneManageQueues.addTab("Manage "+genQueue.getName(), null, panelManageGen, null);

			panelManagePri = new GUIManageQueue(priQueue, genQueue, complQueue);
			tabbedPaneManageQueues.addTab("Manage "+priQueue.getName(), null, panelManagePri, null);
			tabbedPaneManageQueues.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					updateScreen();
				}
			});

		}
	}
