package system;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class Library extends JFrame {
   	private static final long serialVersionUID = 1L;
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/librasystem";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private JPanel manageBookPanel, manageStudentPanel;
    private JTextField bookIdField, bookNameField, authorField;
    private JSpinner quantitySpinner;
    private JButton addBookButton;
    private JTable addbooktable;
    private JTextField studentIdField, studentNameField;
    private JComboBox<String> courseComboBox, branchComboBox;
    private JButton addStudentButton;
    private JTable studentTable;
	private JPanel issueBookPanel;
	private JTextField issueBookIdField;
	private JTextField issueStudentIdField;
	private JButton issueBookButton;
	private JLabel bookIdLabel, bookNameLabel,  authorLabel;
	private JPanel bookDetailsPanel_1;
	private JPanel studentDetailsPanel;
	private JLabel studentIdLabel;
	private JLabel studentNameLabel;
	private JLabel courseLabel;
	private JLabel branchLabel;
	private JTextField returnBookIdField;
	private JTextField returnStudentIdField;
	private JButton returnfindButton;
	private JButton returnBookButton;
	private JPanel returnbookPanel;
	private JLabel returnissueIdLabel;
	private JLabel returnbooknameLabel;
	private JLabel returnstudentnameLabel;
	private JLabel returnissuedateLabel;
	private JLabel returnduedateLabel;
	private JPanel DashboardPanel;
	private JLabel numberofbookslabel;
	private JLabel numberofstudentslabel;
	private JLabel numberofissuedbookslabel;
	private JLabel numberOfBooksLabel;
	private JLabel numberOfIssuedBooksLabel;
	private JPanel contentPane;
	private JPanel panel_2;
	private JLabel timelabel;
	private JLabel datelabel;
	private JPanel panel;
	private JLabel openorcloselabel;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_2_1;
	private JLabel lblNewLabel_7;
	private JPanel HomePanel;
	private JPanel viewrecordsPanel;
	private JPanel viewissuedbooksPanel;
	private JLabel lblNewLabel_8;
	private JTable dashboardbook;
	private JTable dashboardstudenttable;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_1;
	private JLabel lblNewLabel_10;
	private JPanel panel_6;
	private JLabel lblNewLabel_15;
	private JLabel lblNewLabel_19;
	private JLabel lblNewLabel_20;
	private JPanel panel_7;
	private JButton clearButton;
	private JButton updatebutton;
	private JLabel lblNewLabel_21;
	private JLabel lblNewLabel_22;
	private JLabel lblNewLabel_23;
	private JLabel lblNewLabel_24;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel lblNewLabel_19_5;
	private JLabel lblNewLabel_19_6;
	private JLabel lblNewLabel_19_7;
	private JLabel returnbooknameLabel_1;
	private JLabel returnbooknameLabel_2;
	private JTable issuedBooksTable;
	private JScrollPane scrollPane_2;
	private JDateChooser issuedate;
	private JDateChooser duedate;
	private JDateChooser searchIssueDateChooser;
	private JDateChooser searchDueDateChooser;
	private JTable table_2;
	private JLabel lblNewLabel_26;
	private JPanel searchbookpanel;
	private JScrollPane scrollPane_3;
	private JTable searchtable;
	private boolean searchPerformed = false;
	private JScrollPane scrollPane_4;
	private JTextField textField;
	private JLabel numberofbooksquantitylabel;
	private JButton btnNewButton_2;
	public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    // Load the JDBC driver
	                    Class.forName("com.mysql.cj.jdbc.Driver");
	                } catch (ClassNotFoundException e) {
	                    e.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "Error: JDBC driver not found!");
	                    System.exit(1);
	                }

	                try {
	                    Library frame = new Library();
	                    frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
		public void dateandtime() {
		    Thread clock = new Thread(new Runnable() {
		        public void run() {
		            try {
		                while (true) {
		                    Calendar cal = new GregorianCalendar();

		                    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		                    int hour = cal.get(Calendar.HOUR_OF_DAY);

		                    boolean isOpen = (dayOfWeek >= Calendar.MONDAY && dayOfWeek <= Calendar.FRIDAY) &&
		                            (hour >= 8 && hour < 17);

		                    int day = cal.get(Calendar.DAY_OF_MONTH);
		                    int month = cal.get(Calendar.MONTH) + 1;
		                    int year = cal.get(Calendar.YEAR);

		                    int second = cal.get(Calendar.SECOND);
		                    int minute = cal.get(Calendar.MINUTE);

		                    String formattedDay = String.format("%02d", day);
		                    String formattedMonth = String.format("%02d", month);

		                    String formattedHour = String.format("%02d", hour);
		                    String formattedMinute = String.format("%02d", minute);
		                    String formattedSecond = String.format("%02d", second);

		                    datelabel.setText("" + formattedMonth + " - " + formattedDay + " - " + year);

		                    if (isOpen) {
		                        timelabel.setText("" + formattedHour + " : " + formattedMinute + " : " + formattedSecond);
		                        openorcloselabel.setText("OPEN");
		                        openorcloselabel.setForeground(Color.GREEN);
		                    } else {
		                        timelabel.setText("" + formattedHour + " : " + formattedMinute + " : " + formattedSecond);
		                        openorcloselabel.setText("CLOSED");
		                        openorcloselabel.setForeground(Color.RED);
		                    }

		                    Thread.sleep(1000);
		                }
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
		    });
		    clock.start();
		}

	public Library() {

		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1550, 800);
	    contentPane = new JPanel();
	    contentPane.setBackground(new Color(16,56,92));
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    
	    setContentPane(contentPane);
        setLocationRelativeTo(null);
        setVisible(true);
        contentPane.setLayout(null);
        
        panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBorder(null);
        panel_2.setBackground(new Color(16,56,92));
        panel_2.setBounds(229, 0, 1300, 146);
        contentPane.add(panel_2);
        
        lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\USTP Logo against Dark Background (1) (2).png"));
        lblNewLabel_5.setBounds(58, -17, 214, 183);
        panel_2.add(lblNewLabel_5);
        
        lblNewLabel_2_1 = new JLabel("");
        lblNewLabel_2_1.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\nefklwef.jpg.png"));
        lblNewLabel_2_1.setBounds(203, 0, 913, 183);
        panel_2.add(lblNewLabel_2_1);
        
        lblNewLabel_7 = new JLabel("");
        lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\409564407_1594839384586776_902420426506798319_n-removebg-preview.png"));
        lblNewLabel_7.setBounds(772, -169, 538, 516);
        panel_2.add(lblNewLabel_7);
        
        btnNewButton_2 = new JButton("CLOSE ");
        btnNewButton_2.setForeground(new Color(255, 255, 255));
        btnNewButton_2.setBackground(new Color(16,56,92));
        btnNewButton_2.setOpaque(false);
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
        btnNewButton_2.setBorder(null);
        btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\macos_close_32px.png"));
        btnNewButton_2.setBounds(1200, 10, 95, 30);
        panel_2.add(btnNewButton_2);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) {
        		 if (tabbedPane.getSelectedIndex() == 6) {
                     // If a search was performed, reset and populate the table
                     if (searchPerformed) {
                         populateIssuedBooksTable(); // Populate the table with all records
                         searchPerformed = false;     // Reset the searchPerformed flag
                     }
                 }
             }
         });
        tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 19));
        tabbedPane.setBounds(229, 116, 1294, 674);
       

        contentPane.add(tabbedPane, BorderLayout.CENTER);
       
        HomePanel = new JPanel();
        HomePanel.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("New tab", null, HomePanel, null);
        HomePanel.setLayout(null);
        
        lblNewLabel_8 = new JLabel("");
        lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\360_F_596902955_4zzO8Y9KIPdeSxJcbz0nWbCY6moXTaOg (1).jpg"));
        lblNewLabel_8.setBounds(10, 10, 1290, 620);
        HomePanel.add(lblNewLabel_8);
        
        DashboardPanel = new JPanel();
        DashboardPanel.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("Dashboard", null, DashboardPanel, null);
        DashboardPanel.setLayout(null);
        
        numberofbookslabel = new JLabel("Number of Books :");
        numberofbookslabel.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\Books_32px.png"));
        numberofbookslabel.setBounds(108, -27, 323, 183);
        DashboardPanel.add(numberofbookslabel);
        numberofbookslabel.setFont(new Font("Tahoma", Font.BOLD, 15));

        updateNumberOfBooksLabel();
        
        numberofstudentslabel = new JLabel("Number of Students :");
        numberofstudentslabel.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\students_32px.png"));
        numberofstudentslabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        numberofstudentslabel.setBounds(960, -27, 356, 194);
        DashboardPanel.add(numberofstudentslabel);
     
        updateNumberOfStudentsLabel();
       
        
        numberofissuedbookslabel = new JLabel("Number of Issued Books :");
        numberofissuedbookslabel.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\borrow_book_26px.png"));
        numberofissuedbookslabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        numberofissuedbookslabel.setBounds(508, 26, 329, 263);
        DashboardPanel.add(numberofissuedbookslabel);
      
        updateNumberOfIssuedBooksLabel();
        getContentPane().setLayout(null);
        
        numberOfBooksLabel = new JLabel("");
        numberOfBooksLabel.setBounds(901, 5, 184, 183);
        DashboardPanel.add(numberOfBooksLabel);
        
        numberOfIssuedBooksLabel = new JLabel("");
        numberOfIssuedBooksLabel.setBounds(1078, 374, 184, 183);
        DashboardPanel.add(numberOfIssuedBooksLabel);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 241, 590, 336);
        DashboardPanel.add(scrollPane);
        
        dashboardbook = new JTable();
       
        scrollPane.setViewportView(dashboardbook);
     
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(671, 241, 590, 336);
        DashboardPanel.add(scrollPane_1);
        
        dashboardstudenttable = new JTable();
        scrollPane_1.setViewportView(dashboardstudenttable);
        
        JLabel lblNewLabel_9 = new JLabel("Books Details");
        lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_9.setBounds(275, 218, 106, 13);
        DashboardPanel.add(lblNewLabel_9);
        
        JLabel lblNewLabel_9_1 = new JLabel("Student Details");
        lblNewLabel_9_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_9_1.setBounds(931, 218, 106, 13);
        DashboardPanel.add(lblNewLabel_9_1);
        
        JPanel panel_3 = new JPanel();
        panel_3.setBounds(85, 10, 323, 112);
        DashboardPanel.add(panel_3);
        panel_3.setLayout(null);
        
        JPanel panel_8 = new JPanel();
        panel_8.setBackground(new Color(210,0,0));
        panel_8.setBounds(0, 0, 323, 26);
        panel_3.add(panel_8);
        
        numberofbooksquantitylabel = new JLabel("Number of Books Quantity:");
        numberofbooksquantitylabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        numberofbooksquantitylabel.setBounds(30, 64, 241, 26);
        panel_3.add(numberofbooksquantitylabel);
        
        panel_4 = new JPanel();
        panel_4.setBounds(919, 10, 323, 112);
        DashboardPanel.add(panel_4);
        panel_4.setLayout(null);
        
        JPanel panel_8_2 = new JPanel();
        panel_8_2.setBackground(new Color(210,0,0));
        panel_8_2.setBounds(0, 0, 323, 26);
        panel_4.add(panel_8_2);
        
        panel_5 = new JPanel();
        panel_5.setBounds(441, 94, 450, 112);
        DashboardPanel.add(panel_5);
        panel_5.setLayout(null);
        
        JPanel panel_8_1 = new JPanel();
        panel_8_1.setBackground(new Color(16,56,92));
        panel_8_1.setBounds(0, 0, 450, 26);
        panel_5.add(panel_8_1);
        updateNumberOfBooksQuantityLabel();
        
 
  
        // Panel 1: Manage Book
        manageBookPanel = createManageBookPanel();
        tabbedPane.addTab("Manage Book", manageBookPanel);

        // Panel 2: Manage Student
        manageStudentPanel = createManageStudentPanel();
        tabbedPane.addTab("Manage Student", manageStudentPanel);
        
        issueBookPanel = createIssueBookPanel();
        tabbedPane.addTab("Issue Book", issueBookPanel);

        returnbookPanel = createReturnBookPanel();
        tabbedPane.addTab("Return Book", returnbookPanel);
      
        getContentPane().add(tabbedPane);
        
        viewrecordsPanel = new JPanel();
        viewrecordsPanel.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("New tab", null, viewrecordsPanel, null);
        viewrecordsPanel.setLayout(null);
        
        JPanel panel_9 = new JPanel();
        panel_9.setBackground(new Color(16,54,92));
        panel_9.setBounds(10, 10, 1270, 209);
        viewrecordsPanel.add(panel_9);
        panel_9.setLayout(null);
        
        searchIssueDateChooser = new JDateChooser();
        searchIssueDateChooser.setDateFormatString("yyyy-MM-dd");
        searchIssueDateChooser.setBorder(new LineBorder(new Color(210,0,0), 1, true));
        searchIssueDateChooser.setBackground(new Color(210,0,0));
        searchIssueDateChooser.setBounds(211, 114, 304, 39);
        panel_9.add(searchIssueDateChooser);
        
        searchDueDateChooser = new JDateChooser();
        searchDueDateChooser.setDateFormatString("yyyy-MM-dd");
        searchDueDateChooser.setBorder(new LineBorder(new Color(210,0,0), 1, true));
        searchDueDateChooser.setBackground(new Color(210,0,0));
        searchDueDateChooser.setBounds(718, 114, 304, 39);
        panel_9.add(searchDueDateChooser);
        
        JLabel lblNewLabel_25 = new JLabel("Issue Date:");
        lblNewLabel_25.setForeground(new Color(255, 255, 255));
        lblNewLabel_25.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_25.setBackground(new Color(255, 255, 255));
        lblNewLabel_25.setBounds(122, 126, 79, 13);
        panel_9.add(lblNewLabel_25);
        
        JLabel lblNewLabel_25_1 = new JLabel("Due Date:");
        lblNewLabel_25_1.setForeground(Color.WHITE);
        lblNewLabel_25_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_25_1.setBackground(Color.WHITE);
        lblNewLabel_25_1.setBounds(646, 126, 79, 13);
        panel_9.add(lblNewLabel_25_1);
        
        JLabel lblNewLabel_25_2 = new JLabel("View All Records");
        lblNewLabel_25_2.setForeground(Color.WHITE);
        lblNewLabel_25_2.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_25_2.setBackground(Color.WHITE);
        lblNewLabel_25_2.setBounds(494, 28, 200, 33);
        panel_9.add(lblNewLabel_25_2);
        
        JButton btnNewButton = new JButton("Search");
        btnNewButton.setFocusPainted(false);
        btnNewButton.setBackground(new Color(210,0,0));
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnNewButton.setBounds(1070, 114, 98, 39);
        panel_9.add(btnNewButton);
        
        btnNewButton.addActionListener(e -> searchRecordsByDate());
        
        scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(115, 283, 1083, 255);
        viewrecordsPanel.add(scrollPane_2);
        
        issuedBooksTable = new JTable();
        issuedBooksTable.setFont(new Font("Tahoma", Font.BOLD, 10));
        scrollPane_2.setViewportView(issuedBooksTable);
        populateIssuedBooksTable();
        
        viewissuedbooksPanel = new JPanel();
        viewissuedbooksPanel.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("New tab", null, viewissuedbooksPanel, null);
        viewissuedbooksPanel.setLayout(null);
        
        scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(100, 210, 1098, 368);
        viewissuedbooksPanel.add(scrollPane_3);
        
        table_2 = new JTable();
        table_2.setFont(new Font("Tahoma", Font.BOLD, 10));
        scrollPane_3.setViewportView(table_2);
        populateViewIssuedBooksTable();
        
        lblNewLabel_26 = new JLabel("Borrowed Book List");
        lblNewLabel_26.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_26.setBounds(628, 67, 217, 73);
        viewissuedbooksPanel.add(lblNewLabel_26);
        
        searchbookpanel = new JPanel();
        searchbookpanel.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("New tab", null, searchbookpanel, null);
        searchbookpanel.setLayout(null);
        
        scrollPane_4 = new JScrollPane();
        scrollPane_4.setBounds(390, 49, 810, 520);
        searchbookpanel.add(scrollPane_4);
        
        searchtable = new JTable();
       
        searchtable.setFont(new Font("Tahoma", Font.BOLD, 10));
        scrollPane_4.setViewportView(searchtable);
        reloadTableData();
        
        textField = new JTextField();
        textField.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        textField.setBounds(111, 150, 191, 38);
        searchbookpanel.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_27 = new JLabel("Enter the Name of the Book:");
        lblNewLabel_27.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_27.setBounds(111, 127, 191, 13);
        searchbookpanel.add(lblNewLabel_27);
        
        JLabel lblNewLabel_28 = new JLabel("SEARCH BOOK");
        lblNewLabel_28.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_28.setBounds(134, 48, 146, 13);
        searchbookpanel.add(lblNewLabel_28);
        populateSearchBookTable();
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSearchResults();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSearchResults();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSearchResults();
            }
        });
        
        JButton btnHome = new JButton("Home");
        btnHome.setFocusPainted(false);
        btnHome.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnHome.setBackground(Color.RED);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		btnHome.setBackground(Color.WHITE);
        	}
        });
        btnHome.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tabbedPane.setSelectedIndex(0);
        	}
        });
        btnHome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        btnHome.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0))));
        btnHome.setBackground(Color.WHITE);
        btnHome.setBounds(10, 150, 209, 36);
        contentPane.add(btnHome);
        
        JButton btnDashboard = new JButton("Dashboard");
        btnDashboard.setFocusPainted(false);
        btnDashboard.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnDashboard.setBackground(Color.RED);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		btnDashboard.setBackground(Color.WHITE);
        	}
        });
        btnDashboard.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tabbedPane.setSelectedIndex(1);
        		updateNumberOfBooksLabel();
				updateNumberOfBooksQuantityLabel();
				updateNumberOfStudentsLabel();
				updateNumberOfIssuedBooksLabel();
        	}
        });
        btnDashboard.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        btnDashboard.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0))));
        btnDashboard.setBackground(Color.WHITE);
        btnDashboard.setBounds(10, 216, 209, 36);
        contentPane.add(btnDashboard);
        
        JButton form1 = new JButton("Manage Book");
        form1.setFocusPainted(false);
        form1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        	 form1.setBackground(Color.RED);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		form1.setBackground(Color.WHITE);
        	}
        });
        form1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tabbedPane.setSelectedIndex(2);
        	}
        });
        form1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        form1.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0))));
        form1.setBackground(Color.WHITE);
        form1.setBounds(10, 285, 209, 36);
        contentPane.add(form1);
        
        JLabel lblNewLabel_6 = new JLabel("Features");
        lblNewLabel_6.setForeground(Color.WHITE);
        lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_6.setBounds(10, 262, 64, 13);
        contentPane.add(lblNewLabel_6);
        
        JButton btnManageStudent = new JButton("Manage Student");
        btnManageStudent.setFocusPainted(false);
        btnManageStudent.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		btnManageStudent.setBackground(Color.RED);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		btnManageStudent.setBackground(Color.WHITE);
        	}
        });        btnManageStudent.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tabbedPane.setSelectedIndex(3);
        	}
        });
        btnManageStudent.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        btnManageStudent.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0))));
        btnManageStudent.setBackground(Color.WHITE);
        btnManageStudent.setBounds(10, 350, 209, 36);
        contentPane.add(btnManageStudent);
        
        JButton form2_1 = new JButton("Borrow Book");
        form2_1.setFocusPainted(false);
        form2_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		form2_1.setBackground(Color.RED);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		form2_1.setBackground(Color.WHITE);
        	}
        });
        form2_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tabbedPane.setSelectedIndex(4);
        	}
        });
        form2_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        form2_1.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0))));
        form2_1.setBackground(Color.WHITE);
        form2_1.setBounds(10, 414, 209, 36);
        contentPane.add(form2_1);
        
        JButton form2_2 = new JButton("Return Book");
        form2_2.setFocusPainted(false);
        form2_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		form2_2.setBackground(Color.RED);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		form2_2.setBackground(Color.WHITE);
        	}
        });
        form2_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tabbedPane.setSelectedIndex(5);
        	}
        });
        form2_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        form2_2.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0))));
        form2_2.setBackground(Color.WHITE);
        form2_2.setBounds(10, 480, 209, 36);
        contentPane.add(form2_2);
        
        JButton form2_2_1 = new JButton("View Records");
        form2_2_1.setFocusPainted(false);
       form2_2_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		form2_2_1.setBackground(Color.RED);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		form2_2_1.setBackground(Color.WHITE);
        	}
        });
        form2_2_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tabbedPane.setSelectedIndex(6);
        	}
        });
        form2_2_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        form2_2_1.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0))));
        form2_2_1.setBackground(Color.WHITE);
        form2_2_1.setBounds(10, 551, 209, 36);
        contentPane.add(form2_2_1);
        
        JButton form2_2_2 = new JButton("View Borrowed Books");
        form2_2_2.setFocusPainted(false);
        form2_2_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		form2_2_2.setBackground(Color.RED);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		form2_2_2.setBackground(Color.WHITE);
        	}
        });
        form2_2_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tabbedPane.setSelectedIndex(7);
        		recordsreloadTableData();
        	}
        });
        form2_2_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        form2_2_2.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0))));
        form2_2_2.setBackground(Color.WHITE);
        form2_2_2.setBounds(10, 618, 209, 36);
        contentPane.add(form2_2_2);
        
        JButton form2_2_3 = new JButton("Logout");
        form2_2_3.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		form2_2_3.setBackground(Color.RED);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		form2_2_3.setBackground(Color.WHITE);
        	}
        });
        form2_2_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tabbedPane.setSelectedIndex(8);
        	}
        });
        form2_2_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		  int result = JOptionPane.showConfirmDialog(Library.this, "Are you sure you want to log out?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);

  		        // Check user's choice
  		        if (result == JOptionPane.YES_OPTION) {
  		        	setVisible(false);
  		        	new Login().setVisible(true);
  		            JOptionPane.showMessageDialog(Library.this, "Logout successful");
  		            // You can perform logout operations here if needed
  		        } else {
  		            JOptionPane.showMessageDialog(Library.this, "Logout canceled");
  		        }
  		    }
  		});
        form2_2_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        form2_2_3.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0))));
        form2_2_3.setBackground(Color.WHITE);
        form2_2_3.setBounds(10, 751, 209, 36);
        contentPane.add(form2_2_3);
        
        timelabel = new JLabel(":");
        timelabel.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\time_32px.png"));
        timelabel.setForeground(new Color(255, 255, 255));
        timelabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        timelabel.setBounds(28, 95, 201, 37);
        contentPane.add(timelabel);
        
        datelabel = new JLabel(":");
        datelabel.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\calendar_32px.png"));
        datelabel.setForeground(new Color(255, 255, 255));
        datelabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        datelabel.setBackground(Color.WHITE);
        datelabel.setBounds(28, 67, 203, 26);
        contentPane.add(datelabel);
        
        panel = new JPanel();
        panel.setOpaque(false);
        panel.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
        panel.setBounds(82, 28, 80, 29);
        contentPane.add(panel);
        panel.setLayout(null);
        
        openorcloselabel = new JLabel("");
        openorcloselabel.setBounds(20, 0, 60, 29);
        panel.add(openorcloselabel);
        
        JButton form2_2_2_1 = new JButton("Search Book");
        form2_2_2_1.setFocusPainted(false);
        form2_2_2_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		form2_2_2_1.setBackground(Color.RED);
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		form2_2_2_1.setBackground(Color.WHITE);
        	}
        });
        form2_2_2_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tabbedPane.setSelectedIndex(8);
        		 reloadTableDatasearch();
        	}
        });
        form2_2_2_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
        form2_2_2_1.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0))));
        form2_2_2_1.setBackground(Color.WHITE);
        form2_2_2_1.setBounds(10, 683, 209, 36);
        contentPane.add(form2_2_2_1);
        setVisible(true);
        dateandtime();
    }
    private void updateNumberOfBooksLabel() {
        updateCountLabel("SELECT COUNT(*) AS bookCount FROM addbook", numberofbookslabel, "Number of Books: ");
    }
    private void updateNumberOfBooksQuantityLabel() {
        updateCountLabel("SELECT SUM(quantity) AS bookQuantity FROM addbook", numberofbooksquantitylabel, "Number of Books Quantity: ");
    }

    // Add this method to update the number of students label
    private void updateNumberOfStudentsLabel() {
        updateCountLabel("SELECT COUNT(*) AS studentCount FROM addstudent", numberofstudentslabel, "Number of Students: ");
    }

    // Add this method to update the number of issued books label
    private void updateNumberOfIssuedBooksLabel() {
        String query = "SELECT COUNT(*) AS issuedBookCount FROM issued_books WHERE status = 'Pending'";
        updateCountLabel(query, numberofissuedbookslabel, "Number of Issued Books (Pending): ");
    }
    private void updateCountLabel(String query, JLabel label, String labelText) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            try (PreparedStatement countStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = countStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        label.setText(labelText + count);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error getting count: " + ex.getMessage());
        }
    }

  
    private JPanel createManageBookPanel() {
        JPanel managebookpanel = new JPanel();
        managebookpanel.setBackground(new Color(255, 255, 255));
        addbooktable = new JTable();
        addbooktable.setBackground(new Color(255, 255, 255));
        addbooktable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                displaySelectedRowData();
            }
        });
        addbooktable.setCellSelectionEnabled(true);
        addbooktable.setDefaultEditor(Object.class, null);
        addbooktable.setFont(new Font("Tahoma", Font.BOLD, 10));

        // Fetch and display books from the database
       displayBooks();
       
        managebookpanel.setLayout(null);

        // Add the table to the panel
        JScrollPane scrollPane = new JScrollPane(addbooktable);
        scrollPane.setBounds(657, 160, 565, 283);
        managebookpanel.add(scrollPane);
        
        panel_1 = new JPanel();
        panel_1.setBackground(new Color(0, 64, 128));
        panel_1.setBounds(10, 10, 565, 615);
        managebookpanel.add(panel_1);
        panel_1.setLayout(null);
        addBookButton = new JButton("Add Book");
        addBookButton.setFocusPainted(false);
        addBookButton.setForeground(new Color(255, 255, 255));
        addBookButton.setBackground(new Color(210,0,0));
        addBookButton.setBounds(112, 480, 119, 37);
        panel_1.add(addBookButton);
        
                // Add components to the panel
                JLabel label = new JLabel("Book ID:");
                label.setForeground(new Color(255, 255, 255));
                label.setBounds(171, 82, 194, 128);
                panel_1.add(label);
                label.setFont(new Font("Tahoma", Font.BOLD, 12));
                JLabel label_1 = new JLabel("Book Name:");
                label_1.setForeground(new Color(255, 255, 255));
                label_1.setBounds(171, 116, 188, 212);
                panel_1.add(label_1);
                label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
                JLabel label_2 = new JLabel("Author:");
                label_2.setForeground(new Color(255, 255, 255));
                label_2.setBounds(171, 195, 148, 212);
                panel_1.add(label_2);
                label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
                JLabel label_3 = new JLabel("Quantity:");
                label_3.setForeground(new Color(255, 255, 255));
                label_3.setBounds(171, 323, 86, 98);
                panel_1.add(label_3);
                label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
                quantitySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
           
                quantitySpinner.setForeground(new Color(255, 255, 255));
                quantitySpinner.setBackground(new Color(0, 64, 128));
                quantitySpinner.setBounds(171, 395, 119, 26);
                panel_1.add(quantitySpinner);
                quantitySpinner.setFont(new Font("Tahoma", Font.BOLD, 12));
                authorField = new JTextField();
                authorField.setCaretColor(new Color(255, 255, 255));
                authorField.addKeyListener(new KeyAdapter() {
                	@Override
                	public void keyPressed(KeyEvent e) {
                		 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
             	            quantitySpinner.requestFocusInWindow();
             	        }
                	}
                });
                authorField.setFont(new Font("Tahoma", Font.BOLD, 12));
                authorField.setForeground(new Color(255, 255, 255));
                authorField.setBorder(null);
                authorField.setOpaque(false);
                authorField.setBounds(171, 308, 331, 43);
                panel_1.add(authorField);
                bookNameField = new JTextField();
                bookNameField.setCaretColor(new Color(255, 255, 255));
                bookNameField.addKeyListener(new KeyAdapter() {
                	@Override
                	public void keyPressed(KeyEvent e) {
                		 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	            authorField.requestFocusInWindow();
                	        }
                	    }
                	});
                bookNameField.setForeground(new Color(255, 255, 255));
                bookNameField.setFont(new Font("Tahoma", Font.BOLD, 12));
                bookNameField.setBorder(null);
                bookNameField.setOpaque(false);
                bookNameField.setBounds(171, 225, 331, 43);
                panel_1.add(bookNameField);
                
                        bookIdField = new JTextField();
                        bookIdField.setCaretColor(new Color(255, 255, 255));
                        bookIdField.setFont(new Font("Tahoma", Font.BOLD, 12));
                        bookIdField.setForeground(new Color(255, 255, 255));
                        bookIdField.setBorder(null);
                        bookIdField.setOpaque(false);
                        bookIdField.setBounds(171, 150, 331, 43);
                        panel_1.add(bookIdField);
                        
                        clearButton = new JButton("Clear");
                        clearButton.setFocusPainted(false);
                        clearButton.addActionListener(new ActionListener() {
                        	public void actionPerformed(ActionEvent e) {
                        		clearAddBookDetails();
                        	}
                        });
                        clearButton.setForeground(Color.WHITE);
                        clearButton.setBackground(new Color(210, 0, 0));
                        clearButton.setBounds(112, 534, 119, 37);
                        panel_1.add(clearButton);
                        
                        updatebutton = new JButton("Update\r\n");
                        updatebutton.setFocusPainted(false);
                        updatebutton.addActionListener(new ActionListener() {
                        	public void actionPerformed(ActionEvent e) {
                              updaterow();
                        	}
                        });
                        updatebutton.setForeground(Color.WHITE);
                        updatebutton.setBackground(new Color(210, 0, 0));
                        updatebutton.setBounds(306, 480, 119, 37);
                        panel_1.add(updatebutton);
                        
                        lblNewLabel_21 = new JLabel("");
                        lblNewLabel_21.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\identification_documents_32px.png"));
                        lblNewLabel_21.setForeground(new Color(210, 0, 0));
                        lblNewLabel_21.setFont(new Font("Tahoma", Font.BOLD, 15));
                        lblNewLabel_21.setBounds(112, 164, 49, 27);
                        panel_1.add(lblNewLabel_21);
                        
                        lblNewLabel_22 = new JLabel("");
                        lblNewLabel_22.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\informatics_book_32px.png"));
                        lblNewLabel_22.setForeground(new Color(210, 0, 0));
                        lblNewLabel_22.setFont(new Font("Tahoma", Font.BOLD, 15));
                        lblNewLabel_22.setBounds(112, 240, 49, 27);
                        panel_1.add(lblNewLabel_22);
                        
                        lblNewLabel_23 = new JLabel("");
                        lblNewLabel_23.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\writer_male_32px.png"));
                        lblNewLabel_23.setForeground(new Color(210, 0, 0));
                        lblNewLabel_23.setFont(new Font("Tahoma", Font.BOLD, 15));
                        lblNewLabel_23.setBounds(112, 323, 49, 27);
                        panel_1.add(lblNewLabel_23);
                        
                        lblNewLabel_24 = new JLabel("");
                        lblNewLabel_24.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\how_many_quest_32px.png"));
                        lblNewLabel_24.setForeground(new Color(210, 0, 0));
                        lblNewLabel_24.setFont(new Font("Tahoma", Font.BOLD, 15));
                        lblNewLabel_24.setBounds(112, 395, 49, 27);
                        panel_1.add(lblNewLabel_24);
                        
                        JSeparator separator = new JSeparator();
                        separator.setBounds(171, 195, 319, 26);
                        panel_1.add(separator);
                        
                        JSeparator separator_1 = new JSeparator();
                        separator_1.setBounds(171, 267, 319, 26);
                        panel_1.add(separator_1);
                        
                        JSeparator separator_2 = new JSeparator();
                        separator_2.setBounds(171, 348, 319, 26);
                        panel_1.add(separator_2);
                        
                        JButton deleteButton = new JButton("Delete");
                        deleteButton.setFocusPainted(false);
                        deleteButton.addActionListener(new ActionListener() {
                        	public void actionPerformed(ActionEvent e) {
                        		deleteRow();
                        	}
                        });
                        deleteButton.setForeground(Color.WHITE);
                        deleteButton.setBackground(new Color(210, 0, 0));
                        deleteButton.setBounds(306, 534, 119, 37);
                        panel_1.add(deleteButton);
                        
                        lblNewLabel_10 = new JLabel("Book Details");
                        lblNewLabel_10.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\book_32pxred.png"));
                        lblNewLabel_10.setForeground(new Color(210,0,0));
                        lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 15));
                        lblNewLabel_10.setBounds(850, 85, 169, 24);
                        managebookpanel.add(lblNewLabel_10);
                        
                        JSeparator separator_3 = new JSeparator();
                        separator_3.setBackground(new Color(255, 0, 0));
                        separator_3.setBounds(850, 119, 132, 26);
                        managebookpanel.add(separator_3);
                        bookIdField.addKeyListener(new KeyAdapter() {
                        	@Override
                        	public void keyTyped(KeyEvent e) {
                        		 char c = e.getKeyChar();

                        	        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c== KeyEvent.VK_ENTER ))) {
                        	            e.consume();
                        	            JOptionPane.showMessageDialog(null, "Please enter numbers only for Book ID.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                        	        }
                        	    }
                        	@Override
                        	public void keyPressed(KeyEvent e) {
                        		 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        	            bookNameField.requestFocusInWindow();
                        	        }
                        	    }
                        	});
        
                // Add Book Button ActionListener
                addBookButton.addActionListener(e -> addBook());

        return managebookpanel;
    }
    private void updaterow() {
        int selectedrow = addbooktable.getSelectedRow();
        if (selectedrow != -1) {
            String bookid = bookIdField.getText().trim();
            String bookname = bookNameField.getText().trim();
            String author = authorField.getText().trim();
            int quantity = (int) quantitySpinner.getValue();

            if (bookIdField.getText().isEmpty() || bookNameField.getText().isEmpty() || authorField.getText().isEmpty() || quantity == 0) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields, including the quantity", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DefaultTableModel model = (DefaultTableModel) addbooktable.getModel();

            // Check if the selected row index is within the bounds of the table model
            if (selectedrow >= 0 && selectedrow < model.getRowCount()) {
                // Update the values in the table model
                model.setValueAt(bookid, selectedrow, 0);
                model.setValueAt(bookname, selectedrow, 1);
                model.setValueAt(author, selectedrow, 2);
                model.setValueAt(quantity, selectedrow, 3);
            }

            try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
                String updateQuery = "UPDATE addbook SET bookname=?, author=?, quantity=? WHERE bookid=?";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                    updateStatement.setString(1, bookname);
                    updateStatement.setString(2, author);
                    updateStatement.setInt(3, quantity);
                    updateStatement.setString(4, bookid);

                    // Execute the update statement
                    updateStatement.executeUpdate();
                    clearAddBookDetails();
                    JOptionPane.showMessageDialog(null, "Book Updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
 private void displaySelectedRowData() {
        int selectedRow = addbooktable.getSelectedRow();

        if (selectedRow != -1) {
            // Retrieve data from the selected row
            String bookid = addbooktable.getValueAt(selectedRow, 0).toString();
            String bookname = addbooktable.getValueAt(selectedRow, 1).toString();
            String author = addbooktable.getValueAt(selectedRow, 2).toString();
            String quantity = addbooktable.getValueAt(selectedRow, 3).toString();
    
            // Update the text fields with the selected row data
     
     
            bookIdField.setText(bookid);
            bookNameField.setText(bookname);
            authorField.setText(author);
            quantitySpinner.setEditor(new JSpinner.DefaultEditor(quantitySpinner));
            quantitySpinner.setValue(Integer.parseInt(quantity));

       
   
        }
    }
   

	  private void addBook() {
	        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
	            String bookId = bookIdField.getText();
	            String bookName = bookNameField.getText();
	            String author = authorField.getText();
	            int quantity = (int) quantitySpinner.getValue(); // Get the quantity as an integer 
	            if(bookId.isEmpty() || bookName.isEmpty() || author.isEmpty()) {
	            	JOptionPane.showMessageDialog(null, "Please fill in all fields");
	            }
	            // Check if the quantity is valid (not zero or negative)
	            if (quantity <= 0) {
	                JOptionPane.showMessageDialog(this, "Invalid quantity. Please enter a quantity greater than zero.");
	                return; // Exit the method without adding the book
	            }

	            // Check if the book with the same ID already exists in the database
	            if (isBookExists(bookId)) {
	            	clearAddBookDetails();
	                JOptionPane.showMessageDialog(this, "Book with ID " + bookId + " already exists.");
	            } else {
	                // Insert the new book into the addbook table
	                String insertQuery = "INSERT INTO addbook (bookid, bookname, author, quantity) VALUES (?, ?, ?, ?)";
	                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
	                    preparedStatement.setString(1, bookId);
	                    preparedStatement.setString(2, bookName);
	                    preparedStatement.setString(3, author);
	                    preparedStatement.setInt(4, quantity);

	                    int rowsAffected = preparedStatement.executeUpdate();

	                    if (rowsAffected > 0) {
	                        JOptionPane.showMessageDialog(this, "Book added successfully!");
	                        reloadTableData(); // Refresh the book table data
	                        clearAddBookDetails(); // Clear the add book details after successful addition
	                    } else {
	                        JOptionPane.showMessageDialog(this, "Failed to add the book. Please try again.");
	                    }
	                }
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
	        }
	    }
	  
  
	  private void clearAddBookDetails() {
	    	bookIdField.setText("");
	    	bookNameField.setText("");
	    	authorField.setText("");
	    	quantitySpinner.setValue(0);
	 }
	  private void deleteRow() {
		    int selectedRow = addbooktable.getSelectedRow();
		    if (selectedRow != -1) {
		        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this book?", "Confirmation", JOptionPane.YES_NO_OPTION);
		        if (dialogResult == JOptionPane.YES_OPTION) {
		            String bookId = addbooktable.getValueAt(selectedRow, 0).toString();
		            
		            try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
		                String deleteQuery = "DELETE FROM addbook WHERE bookid=?";
		                try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
		                    deleteStatement.setString(1, bookId);

		                    // Execute the delete statement
		                    int rowsDeleted = deleteStatement.executeUpdate();

		                    if (rowsDeleted > 0) {
		                        JOptionPane.showMessageDialog(null, "Book deleted successfully!");
		                        reloadTableData();
		                        clearAddBookDetails();
		                    } else {
		                        JOptionPane.showMessageDialog(null, "Failed to delete the book. Please try again.");
		                    }
		                }
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
		            }
		        }
		    } else {
		        JOptionPane.showMessageDialog(null, "Please select a row to delete.");
		    }
		}

    private void displayBooks() {
    	 try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
 	        String query = "SELECT bookid, bookname, author, quantity FROM addbook";
 	        try (Statement statement = connection.createStatement();
 	             ResultSet resultSet = statement.executeQuery(query)) {

 	            // Get column names
 	            String[] columnNames = {"Book ID", "Book Name", "Author", "Quantity"};

 	            // Get table data
 	            List<Object[]> data = new ArrayList<>();
 	            while (resultSet.next()) {
 	                Object[] row = new Object[4]; // Number of columns
 	                row[0] = resultSet.getObject("bookid");
 	                row[1] = resultSet.getObject("bookname");
 	                row[2] = resultSet.getObject("author");
 	                row[3] = resultSet.getObject("quantity");
 	                data.add(row);
 	            }

 	            // Update the table model
 	            DefaultTableModel tableModel = new DefaultTableModel(data.toArray(new Object[0][]), columnNames) {
 	                @Override
 	                public boolean isCellEditable(int row, int column) {
 	                    return false; // Make the table uneditable
 	                }
 	            };

 	            // Set the table model
 	           
 	            addbooktable.setModel(tableModel);
 	           dashboardbook.setModel(tableModel);
 	            JTableHeader header = addbooktable.getTableHeader();
 	            header.setBackground(new Color(16, 56, 92));
 	            header.setForeground(Color.WHITE);
 	           JTableHeader header1 = dashboardbook.getTableHeader();
	            header1.setBackground(new Color(16, 56, 92));
	            header1.setForeground(Color.WHITE);
 	        }
 	    } catch (SQLException ex) {
 	        ex.printStackTrace();
 	        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
 	    }
 	}
    private JPanel createManageStudentPanel() {
        JPanel managestudentpanel = new JPanel();
        managestudentpanel.setBackground(new Color(255, 255, 255));
        studentTable = new JTable();
        studentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            	displaySelectedRowDatastudent();
            }
        });
        studentTable.setFont(new Font("Tahoma", Font.BOLD, 10));

        // Fetch and display students from the database
        displayStudents();
        managestudentpanel.setLayout(null);

        // Add the table to the panel
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(660, 166, 565, 284);
        managestudentpanel.add(scrollPane);
        
        panel_6 = new JPanel();
        panel_6.setBackground(new Color(0, 64, 128));
        panel_6.setBounds(10, 10, 565, 615);
        managestudentpanel.add(panel_6);
        panel_6.setLayout(null);
        courseComboBox = new JComboBox<>(new String[]{"BSIT", "BFPT", "BTLED"});
        courseComboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
        courseComboBox.setBounds(165, 313, 163, 41);
        panel_6.add(courseComboBox);
        studentNameField = new JTextField();
        studentNameField.setCaretColor(new Color(255, 255, 255));
        studentNameField.setForeground(new Color(255, 255, 255));
        studentNameField.setFont(new Font("Tahoma", Font.BOLD, 12));
        studentNameField.setBorder(null);
        studentNameField.setOpaque(false);
        studentNameField.setBounds(165, 235, 331, 42);
        panel_6.add(studentNameField);
        JLabel label_2 = new JLabel("Course:");
        label_2.setForeground(new Color(255, 255, 255));
        label_2.setBounds(165, 191, 94, 212);
        panel_6.add(label_2);
        label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        
                // Add components to the panel
                JLabel label = new JLabel("Student ID:");
                label.setForeground(new Color(255, 255, 255));
                label.setBounds(165, 74, 119, 166);
                panel_6.add(label);
                label.setFont(new Font("Tahoma", Font.BOLD, 12));
                JLabel label_1 = new JLabel("Student Name:");
                label_1.setForeground(new Color(255, 255, 255));
                label_1.setBounds(165, 182, 119, 106);
                panel_6.add(label_1);
                label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
                JLabel lblDepartment = new JLabel("Department:");
                lblDepartment.setForeground(new Color(255, 255, 255));
                lblDepartment.setBounds(165, 282, 94, 212);
                panel_6.add(lblDepartment);
                lblDepartment.setFont(new Font("Tahoma", Font.BOLD, 12));
                branchComboBox = new JComboBox<>(new String[]{"IT", "EDUC", "FPD"});
                branchComboBox.setModel(new DefaultComboBoxModel(new String[] {"DIT", "DTLED", "DFPT"}));
                branchComboBox.setBounds(165, 398, 163, 41);
                panel_6.add(branchComboBox);
                branchComboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
                addStudentButton = new JButton("Add Student");
                addStudentButton.setFocusPainted(false);
                addStudentButton.setForeground(new Color(255, 255, 255));
                addStudentButton.setBackground(new Color(210,0,0));
                addStudentButton.setFont(new Font("Tahoma", Font.BOLD, 10));
                addStudentButton.setBounds(111, 504, 119, 35);
                panel_6.add(addStudentButton);
                
                        studentIdField = new JTextField();
                        studentIdField.setCaretColor(new Color(255, 255, 255));
                        studentIdField.setFont(new Font("Tahoma", Font.BOLD, 12));
                        studentIdField.setOpaque(false);
                        studentIdField.setBorder(null);
                        studentIdField.setForeground(new Color(255, 255, 255));
                        studentIdField.setBounds(165, 162, 331, 42);
                        panel_6.add(studentIdField);
                        
                        JLabel label_3 = new JLabel("");
                        label_3.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\identification_documents_32px.png"));
                        label_3.setForeground(Color.WHITE);
                        label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
                        label_3.setBounds(111, 162, 55, 56);
                        panel_6.add(label_3);
                        
                        label_4 = new JLabel("");
                        label_4.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\writer_male_32px.png"));
                        label_4.setForeground(Color.WHITE);
                        label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
                        label_4.setBounds(111, 235, 55, 56);
                        panel_6.add(label_4);
                        
                        label_5 = new JLabel("");
                        label_5.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\course_assign_32px.png"));
                        label_5.setForeground(Color.WHITE);
                        label_5.setFont(new Font("Tahoma", Font.BOLD, 12));
                        label_5.setBounds(111, 308, 55, 56);
                        panel_6.add(label_5);
                        
                        label_6 = new JLabel("");
                        label_6.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\department_32px.png"));
                        label_6.setForeground(Color.WHITE);
                        label_6.setFont(new Font("Tahoma", Font.BOLD, 12));
                        label_6.setBounds(111, 396, 55, 56);
                        panel_6.add(label_6);
                        
                        JButton sclearbutton = new JButton("Clear");
                        sclearbutton.setFocusPainted(false);
                        sclearbutton.addActionListener(new ActionListener() {
                        	public void actionPerformed(ActionEvent e) {
                        		clearAddStudentDetails();
                        	}
                        });
                        sclearbutton.setForeground(new Color(255, 255, 255));
                        sclearbutton.setBackground(new Color(210,0,0));
                        sclearbutton.setFont(new Font("Tahoma", Font.BOLD, 10));
                        sclearbutton.setBounds(111, 549, 119, 35);
                        panel_6.add(sclearbutton);
                        
                        JButton btnUpdate = new JButton("Update");
                        btnUpdate.setFocusPainted(false);
                        btnUpdate.setForeground(new Color(255, 255, 255));
                        btnUpdate.setBackground(new Color(210, 0, 0));
                        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 10));
                        btnUpdate.addActionListener(new ActionListener() {
                        	public void actionPerformed(ActionEvent e) {
                        		updaterowstudent();
                        	}
                        });
                        btnUpdate.setBounds(298, 504, 119, 35);
                        panel_6.add(btnUpdate);
                        
                        JButton btnDelete = new JButton("Delete");
                        btnDelete.setFocusPainted(false);
                        btnDelete.addActionListener(new ActionListener() {
                        	public void actionPerformed(ActionEvent e) {
                        		deleteRowstudent();
                        	}
                        });
                        btnDelete.setForeground(new Color(255, 255, 255));
                        btnDelete.setBackground(new Color(210, 0, 0));
                        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 10));
                        btnDelete.setBounds(298, 549, 119, 35);
                        panel_6.add(btnDelete);
                        
                        JSeparator separator = new JSeparator();
                        separator.setBounds(165, 199, 319, 26);
                        panel_6.add(separator);
                        
                        JSeparator separator_1 = new JSeparator();
                        separator_1.setBounds(165, 277, 319, 26);
                        panel_6.add(separator_1);
                        studentIdField.addKeyListener(new KeyAdapter() {
                        	@Override
                        	public void keyTyped(KeyEvent e) {
                        		   char c = e.getKeyChar();
                        		   if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ENTER))) {
                        	            e.consume();
                        	        }
                        	}
                        	@Override
                        	public void keyPressed(KeyEvent e) {
                        		 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                     	            studentNameField.requestFocusInWindow();
                     	        }
                     	    }
                     	});
                
                lblNewLabel_15 = new JLabel("Student Details");
                lblNewLabel_15.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\schoolboy_at_a_desk_32pxred.png"));
                lblNewLabel_15.setForeground(new Color(210,0,0));
                lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 15));
                lblNewLabel_15.setBounds(858, 73, 169, 32);
                managestudentpanel.add(lblNewLabel_15);
                
                JSeparator separator_2 = new JSeparator();
                separator_2.setBackground(new Color(255, 0, 0));
                separator_2.setBounds(858, 115, 153, 26);
                managestudentpanel.add(separator_2);
                
                        // Add Student Button ActionListener
                        addStudentButton.addActionListener(e -> addStudent());

        return managestudentpanel;
    }

    private void addStudent() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String studentId = studentIdField.getText();
            String studentName = studentNameField.getText();
            String course = (String) courseComboBox.getSelectedItem();
            String branch = (String) branchComboBox.getSelectedItem();

            // Check if any of the fields is empty
            if (studentId.isEmpty() || studentName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all required fields");
                return;
            }

            // Check if the student with the same ID already exists in the database
            if (isStudentExists(studentId)) {
            	 clearAddStudentDetails(); 
                JOptionPane.showMessageDialog(this, "Student with ID " + studentId + " already exists.");
            } else {
                // Insert the new student into the addstudent table
                String insertQuery = "INSERT INTO addstudent (studentid, studentname, course, branch) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, studentId);
                    preparedStatement.setString(2, studentName);
                    preparedStatement.setString(3, course);
                    preparedStatement.setString(4, branch);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Student added successfully!");
                        displayStudents(); // Refresh the table after adding a student
                        clearAddStudentDetails(); // Clear the add student details after successful addition
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to add the student. Please try again.");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }


    private void displayStudents() {
    	 try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
 	        String query = "SELECT studentid, studentname, course, branch FROM addstudent";
 	        try (Statement statement = connection.createStatement();
 	             ResultSet resultSet = statement.executeQuery(query)) {

 	            // Get column names
 	            String[] columnNames = {"Student ID", "Student Name", "Course", "Department"};

 	            // Get table data
 	            List<Object[]> data = new ArrayList<>();
 	            while (resultSet.next()) {
 	                Object[] row = new Object[4]; // Number of columns
 	                row[0] = resultSet.getObject("studentid");
 	                row[1] = resultSet.getObject("studentname");
 	                row[2] = resultSet.getObject("course");
 	                row[3] = resultSet.getObject("branch");
 	                data.add(row);
 	            }

 	            // Update the table model
 	            DefaultTableModel tableModel = new DefaultTableModel(data.toArray(new Object[0][]), columnNames) {
 	                @Override
 	                public boolean isCellEditable(int row, int column) {
 	                    return false; // Make the table uneditable
 	                }
 	            };

 	            // Set the table model
 	           
 	            studentTable.setModel(tableModel);
 	            dashboardstudenttable.setModel(tableModel);
 	            JTableHeader header = studentTable.getTableHeader();
 	            header.setBackground(new Color(16, 56, 92));
 	            header.setForeground(Color.WHITE);
 	           JTableHeader header2 = dashboardstudenttable.getTableHeader();
	            header2.setBackground(new Color(16, 56, 92));
	            header2.setForeground(Color.WHITE);
 	        }
 	    } catch (SQLException ex) {
 	        ex.printStackTrace();
 	        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
 	    }
 	}
    private JPanel createIssueBookPanel() {
        JPanel issuebookpanel = new JPanel();
        issuebookpanel.setBackground(new Color(255, 255, 255));

        issueBookIdField = new JTextField();
        issueBookIdField.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		 char c = e.getKeyChar();

     	        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c== KeyEvent.VK_ENTER ))) {
     	            e.consume();
     	            JOptionPane.showMessageDialog(null, "Please enter numbers only for Book ID.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
     	        }
    	}
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
     	            issueStudentIdField.requestFocusInWindow();
     	        }
        	}
    });

        issueBookIdField.setOpaque(false);
        issueBookIdField.setBorder(null);
        issueBookIdField.setBounds(891, 174, 346, 38);

        // Add DocumentListener to issueBookIdField to automatically display book details
        issueBookIdField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                displayBookDetails(issueBookIdField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                clearBookDetails();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not applicable for plain text components
            }
        });
        issuebookpanel.setLayout(null);

        // Add components to the panel
        JLabel label = new JLabel("Book ID:");
        label.setForeground(new Color(210, 0, 0));
        label.setFont(new Font("Tahoma", Font.BOLD, 12));
        label.setBounds(798, 121, 125, 141);
        issuebookpanel.add(label);
        issuebookpanel.add(issueBookIdField);

        bookDetailsPanel_1 = new JPanel();
        bookDetailsPanel_1.setBackground(new Color(210, 0, 0));
        bookDetailsPanel_1.setBounds(10, 10, 370, 615);
        issuebookpanel.add(bookDetailsPanel_1);
        bookDetailsPanel_1.setLayout(null);
        
                // Add the book details panel to the main panel
            
        
                bookIdLabel = new JLabel("");
                bookIdLabel.setForeground(new Color(255, 255, 255));
                bookIdLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
                bookIdLabel.setBounds(143, 223, 227, 28);
                bookDetailsPanel_1.add(bookIdLabel);
                
                        bookNameLabel = new JLabel("");
                        bookNameLabel.setForeground(new Color(255, 255, 255));
                        bookNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
                        bookNameLabel.setBounds(143, 313, 227, 28);
                        bookDetailsPanel_1.add(bookNameLabel);
                        
                                authorLabel = new JLabel("");
                                authorLabel.setForeground(new Color(255, 255, 255));
                                authorLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
                                authorLabel.setBounds(143, 410, 227, 28);
                                bookDetailsPanel_1.add(authorLabel);
                                
                                lblNewLabel_19 = new JLabel("Book Details");
                                lblNewLabel_19.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\book_32px.png"));
                                lblNewLabel_19.setForeground(new Color(255, 255, 255));
                                lblNewLabel_19.setFont(new Font("Tahoma", Font.BOLD, 15));
                                lblNewLabel_19.setBounds(124, 78, 158, 28);
                                bookDetailsPanel_1.add(lblNewLabel_19);
                                
                                JSeparator separator_1 = new JSeparator();
                                separator_1.setBounds(124, 116, 130, 26);
                                bookDetailsPanel_1.add(separator_1);
                                
                                JLabel lblNewLabel_11 = new JLabel("Book ID :");
                                lblNewLabel_11.setForeground(new Color(255, 255, 255));
                                lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 12));
                                lblNewLabel_11.setBounds(26, 231, 67, 13);
                                bookDetailsPanel_1.add(lblNewLabel_11);
                                
                                JLabel lblNewLabel_11_1 = new JLabel("Book Name :");
                                lblNewLabel_11_1.setForeground(Color.WHITE);
                                lblNewLabel_11_1.setFont(new Font("Tahoma", Font.BOLD, 12));
                                lblNewLabel_11_1.setBounds(26, 322, 96, 13);
                                bookDetailsPanel_1.add(lblNewLabel_11_1);
                                
                                JLabel lblNewLabel_11_2 = new JLabel("Author :");
                                lblNewLabel_11_2.setForeground(Color.WHITE);
                                lblNewLabel_11_2.setFont(new Font("Tahoma", Font.BOLD, 12));
                                lblNewLabel_11_2.setBounds(26, 419, 67, 13);
                                bookDetailsPanel_1.add(lblNewLabel_11_2);

        studentDetailsPanel = new JPanel();
        studentDetailsPanel.setBackground(new Color(0, 64, 128));
        studentDetailsPanel.setBounds(390, 10, 370, 615);
        studentDetailsPanel.setLayout(null);
        issuebookpanel.add(studentDetailsPanel);

        studentIdLabel = new JLabel("");
        studentIdLabel.setForeground(new Color(255, 255, 255));
        studentIdLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        studentIdLabel.setBounds(142, 225, 218, 28);
        studentDetailsPanel.add(studentIdLabel);

        studentNameLabel = new JLabel("");
        studentNameLabel.setForeground(new Color(255, 255, 255));
        studentNameLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        studentNameLabel.setBounds(142, 299, 218, 28);
        studentDetailsPanel.add(studentNameLabel);

        courseLabel = new JLabel("");
        courseLabel.setForeground(new Color(255, 255, 255));
        courseLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        courseLabel.setBounds(142, 364, 218, 28);
        studentDetailsPanel.add(courseLabel);

        branchLabel = new JLabel("");
        branchLabel.setForeground(new Color(255, 255, 255));
        branchLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        branchLabel.setBounds(142, 427, 218, 28);
        studentDetailsPanel.add(branchLabel);
        
        lblNewLabel_20 = new JLabel("Student Details");
        lblNewLabel_20.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\schoolboy_at_a_desk_32px.png"));
        lblNewLabel_20.setForeground(new Color(255, 255, 255));
        lblNewLabel_20.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_20.setBounds(112, 80, 158, 28);
        studentDetailsPanel.add(lblNewLabel_20);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(112, 118, 158, 26);
        studentDetailsPanel.add(separator_2);
        
        JLabel lblNewLabel_11_3 = new JLabel("Student ID :");
        lblNewLabel_11_3.setForeground(Color.WHITE);
        lblNewLabel_11_3.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_11_3.setBounds(29, 234, 92, 13);
        studentDetailsPanel.add(lblNewLabel_11_3);
        
        JLabel lblNewLabel_11_4 = new JLabel("Student Name :");
        lblNewLabel_11_4.setForeground(Color.WHITE);
        lblNewLabel_11_4.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_11_4.setBounds(29, 308, 103, 13);
        studentDetailsPanel.add(lblNewLabel_11_4);
        
        JLabel lblNewLabel_11_5 = new JLabel("Course :");
        lblNewLabel_11_5.setForeground(Color.WHITE);
        lblNewLabel_11_5.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_11_5.setBounds(29, 373, 67, 13);
        studentDetailsPanel.add(lblNewLabel_11_5);
        
        JLabel lblNewLabel_11_6 = new JLabel("Department :");
        lblNewLabel_11_6.setForeground(Color.WHITE);
        lblNewLabel_11_6.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_11_6.setBounds(29, 436, 92, 13);
        studentDetailsPanel.add(lblNewLabel_11_6);
        
        panel_7 = new JPanel();
       
        panel_7.setBounds(770, 10, 505, 615);
        issuebookpanel.add(panel_7);
        panel_7.setLayout(null);
        
        JLabel lblNewLabel_19_2 = new JLabel("Borrow Book");
        lblNewLabel_19_2.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\borrow_book_32pxred.png"));
        lblNewLabel_19_2.setForeground(new Color(210,0,0));
        lblNewLabel_19_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_19_2.setBounds(188, 81, 158, 28);
        panel_7.add(lblNewLabel_19_2);
        JLabel label_1 = new JLabel("Student ID:");
        label_1.setBounds(28, 169, 109, 191);
        panel_7.add(label_1);
        label_1.setForeground(new Color(210, 0,0));
        label_1.setBackground(new Color(210, 0, 0));
        label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        issuedate = new JDateChooser(); 
        issuedate.setBorder(new LineBorder(new Color(210, 0, 0), 1, true));
        issuedate.setBounds(121, 319, 340, 41);
        panel_7.add(issuedate);
        issueStudentIdField = new JTextField();
        issueStudentIdField.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		 char c = e.getKeyChar();

     	        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c== KeyEvent.VK_ENTER ))) {
     	            e.consume();
     	            JOptionPane.showMessageDialog(null, "Please enter numbers only for Book ID.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
     	        }
    	}
    });
        issueStudentIdField.setOpaque(false);
        issueStudentIdField.setBounds(121, 247, 346, 38);
        panel_7.add(issueStudentIdField);
        issueStudentIdField.setBorder(null);
        issueStudentIdField.setForeground(new Color(0, 0, 0));
        

         duedate= new JDateChooser();
        duedate.setBorder(new LineBorder(new Color(210, 0, 0), 1, true));
        duedate.setBounds(121, 391, 340, 41);
        panel_7.add(duedate);
        issueBookButton = new JButton("Borrow Book");
        issueBookButton.setFocusPainted(false);
        issueBookButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
        issueBookButton.setForeground(new Color(255, 255, 255));
        issueBookButton.setBackground(new Color(210, 0, 0));
        issueBookButton.setBounds(211, 507, 145, 41);
        panel_7.add(issueBookButton);
        
        JLabel lblDueDate = new JLabel("Due Date:");
        lblDueDate.setForeground(new Color(210, 0, 0));
        lblDueDate.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblDueDate.setBounds(28, 401, 87, 15);
        panel_7.add(lblDueDate);
        
        JLabel lblIssueDate = new JLabel("Issue Date:");
        lblIssueDate.setForeground(new Color(210, 0, 0));
        lblIssueDate.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblIssueDate.setBounds(28, 332, 87, 15);
        panel_7.add(lblIssueDate);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(114, 223, 1, 2);
        panel_7.add(separator);
        
        JSeparator separator_3 = new JSeparator();
        separator_3.setBackground(new Color(255, 0, 0));
        separator_3.setForeground(new Color(255, 0, 0));
        separator_3.setBounds(121, 199, 319, 26);
        panel_7.add(separator_3);
        
        JSeparator separator_3_1 = new JSeparator();
        separator_3_1.setForeground(new Color(255, 0, 0));
        separator_3_1.setBackground(new Color(255, 0, 0));
        separator_3_1.setBounds(121, 282, 319, 26);
        panel_7.add(separator_3_1);
        
        JSeparator separator_3_2 = new JSeparator();
        separator_3_2.setBackground(new Color(255, 0, 0));
        separator_3_2.setBounds(180, 119, 145, 26);
        panel_7.add(separator_3_2);
        
                // Issue Book Button ActionListener
                issueBookButton.addActionListener(e -> issueBook());
        
                // Add DocumentListener to issueStudentIdField to automatically display student details
                issueStudentIdField.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        displayStudentDetails(issueStudentIdField.getText());
                    }
        
                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        clearStudentDetails();
                    }
        
                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        // Not applicable for plain text components
                    }
                });

        return issuebookpanel;
    }
    public void reloadTableDatastudent() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String selectQuery = "SELECT * FROM addstudent";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                 ResultSet resultSet = selectStatement.executeQuery()) {

                DefaultTableModel tableModel = (DefaultTableModel) studentTable.getModel();
                
                tableModel.setRowCount(0); // Clear existing rows

                // Populate the table model with data from the result set
                while (resultSet.next()) {
                    Object[] rowData = {
                        resultSet.getString("studentid"),
                        resultSet.getString("studentname"),
                        resultSet.getString("course"),
                        resultSet.getString("department")
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void displayStudentDetails(String studentId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT * FROM addstudent WHERE studentid = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, studentId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        studentIdLabel.setText("" + resultSet.getString("studentid"));
                        studentNameLabel.setText("" + resultSet.getString("studentname"));
                        courseLabel.setText("" + resultSet.getString("course"));
                        branchLabel.setText("" + resultSet.getString("branch"));
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

  

    private boolean isBookExists(String bookId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT * FROM addbook WHERE bookid = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, bookId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // If resultSet has next, book exists
                }
            }
        }
    }

    private boolean isStudentExists(String studentId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT * FROM addstudent WHERE studentid = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, studentId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // If resultSet has next, student exists
                }
            }
        }
    }
    private void displayBookDetails(String bookId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT * FROM addbook WHERE bookid = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, bookId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        bookIdLabel.setText("" + resultSet.getString("bookid"));
                        bookNameLabel.setText("" + resultSet.getString("bookname"));
                        authorLabel.setText("" + resultSet.getString("author"));
                    } else {
                        // Clear the book details if the book ID is not found
                        clearBookDetails();
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void clearBookDetails() {
        bookIdLabel.setText("");
        bookNameLabel.setText("");
        authorLabel.setText("");
    }
    private void clearStudentDetails() {
        studentIdLabel.setText("");
        studentNameLabel.setText("");
        courseLabel.setText("");
        branchLabel.setText("");
    }
    private void updaterowstudent() {
	    int selectedRow = studentTable.getSelectedRow();
	    if (selectedRow != -1) {
	        String studentId = studentIdField.getText().trim();
	        String studentName = studentNameField.getText().trim();
	        String course = (String) courseComboBox.getSelectedItem();
	        String department = (String) branchComboBox.getSelectedItem();

	        if (studentId.isEmpty() || studentName.isEmpty() || course == null || department == null) {
	            JOptionPane.showMessageDialog(null, "Please fill in all fields");
	            return;
	        }

	        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();

	        // Check if the selected row index is within the bounds of the table model
	        if (selectedRow >= 0 && selectedRow < model.getRowCount()) {
	            // Update the values in the table model
	            model.setValueAt(studentId, selectedRow, 0);
	            model.setValueAt(studentName, selectedRow, 1);
	            model.setValueAt(course, selectedRow, 2);
	            model.setValueAt(department, selectedRow, 3);
	        }

	        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
	            String updateQuery = "UPDATE addstudent SET studentname=?, course=?, department=? WHERE studentid=?";
	            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
	                updateStatement.setString(1, studentName);
	                updateStatement.setString(2, course);
	                updateStatement.setString(3, department);
	                updateStatement.setString(4, studentId);

	                // Execute the update statement
	                updateStatement.executeUpdate();
	                clearAddStudentDetails();
	                JOptionPane.showMessageDialog(null, "Student Updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}

    private void displaySelectedRowDatastudent() {
	    int selectedRow = studentTable.getSelectedRow();

	    if (selectedRow != -1) {
	        // Retrieve data from the selected row
	        String studentId = studentTable.getValueAt(selectedRow, 0).toString();
	        String studentName = studentTable.getValueAt(selectedRow, 1).toString();
	        String course = studentTable.getValueAt(selectedRow, 2).toString();
	        String department = studentTable.getValueAt(selectedRow, 3).toString();

	        // Update the text fields with the selected row data
	        studentIdField.setText(studentId);
	        studentNameField.setText(studentName);
	        courseComboBox.setSelectedItem(course);
	        branchComboBox.setSelectedItem(department);
	    }
	}

    private void clearAddStudentDetails() {
	 studentIdField.setText("");
	    studentNameField.setText("");
	    courseComboBox.setSelectedIndex(-1); // Clear the selection in the courseComboBox
	    branchComboBox.setSelectedIndex(-1);
 }
    private void deleteRowstudent() {
	    int selectedRow = studentTable.getSelectedRow();
	    if (selectedRow != -1) {
	        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this student?", "Confirmation", JOptionPane.YES_NO_OPTION);
	        if (dialogResult == JOptionPane.YES_OPTION) {
	            String studentId = studentTable.getValueAt(selectedRow, 0).toString();
	            
	            try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
	                String deleteQuery = "DELETE FROM addstudent WHERE studentid=?";
	                try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
	                    deleteStatement.setString(1, studentId);

	                    // Execute the delete statement
	                    int rowsDeleted = deleteStatement.executeUpdate();

	                    if (rowsDeleted > 0) {
	                        JOptionPane.showMessageDialog(null, "Student deleted successfully!");
	                        reloadTableDatastudent();
	                        clearAddStudentDetails();
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Failed to delete the student. Please try again.");
	                    }
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
	            }
	        }
	    } else {
	        JOptionPane.showMessageDialog(null, "Please select a row to delete.");
	    }
	}
    private JPanel createReturnBookPanel() {
        JPanel returnbookpanel = new JPanel();
        returnbookpanel.setBackground(new Color(255, 255, 255));

        returnBookIdField = new JTextField();
        returnBookIdField.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		  char c = e.getKeyChar();

        	        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ENTER))) {
        	            e.consume();
        	            JOptionPane.showMessageDialog(null, "Please enter numbers only for Book ID.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        	        }
        	    }
        	@Override
        	public void keyPressed(KeyEvent e) {
        		 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        	            returnStudentIdField.requestFocusInWindow();
        	        }
        	    }
    });
        returnBookIdField.setBorder(null);
        returnBookIdField.setOpaque(false);
        returnBookIdField.setBounds(766, 185, 346, 38);
        returnStudentIdField = new JTextField();
        returnStudentIdField.setBorder(null);
        returnStudentIdField.setOpaque(false);
        returnStudentIdField.setBounds(766, 290, 346, 38);
        returnStudentIdField.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		  char c = e.getKeyChar();

        		  if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ENTER))) {
        	            e.consume();
        	            JOptionPane.showMessageDialog(null, "Please enter numbers only for Book ID.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
        	        }
        	    }
        });
        returnfindButton = new JButton("Find");
        returnfindButton.setFocusPainted(false);
        returnfindButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		findBookDetails();
        	}
        });
        returnfindButton.setForeground(new Color(255, 255, 255));
        returnfindButton.setBackground(new Color(16,56,92));
        returnfindButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        returnfindButton.setBounds(808, 398, 207, 51);
        returnBookButton = new JButton("Return Book");
        returnBookButton.setFocusPainted(false);
        returnBookButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				returnBook();
				 // Clear the return book details after successful return
                clearReturnBookDetails();
				
			}
        });
        returnBookButton.setForeground(new Color(255, 255, 255));
        returnBookButton.setBackground(new Color(210,0,0));
        returnBookButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        returnBookButton.setBounds(808, 497, 207, 51);
        new JLabel("Book ID: ");
        new JLabel("Book Name: ");
        new JLabel("Student Name: ");
        new JLabel("Issue Date: ");
        new JLabel("Due Date: ");

   
        
        JLabel label = new JLabel("Book ID:");
        label.setForeground(new Color(210,0,0));
        label.setFont(new Font("Tahoma", Font.BOLD, 12));
        label.setBounds(660, 146, 82, 112);
        label.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		 char c = e.getKeyChar();
        	        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
        	            e.consume();
        	        }
        	}
        });
        returnbookpanel.setLayout(null);
        returnbookpanel.add(label);
        returnbookpanel.add(returnBookIdField);

        JLabel label_1 = new JLabel("Student ID:");
        label_1.setForeground(new Color(210,0,0));
        label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        label_1.setBounds(660, 221, 88, 172);
        returnbookpanel.add(label_1);
        returnbookpanel.add(returnStudentIdField);
        returnbookpanel.add(returnfindButton);
        returnbookpanel.add(returnBookButton);
        
        JPanel returnbookdetailsPanel = new JPanel();
        returnbookdetailsPanel.setBackground(new Color(210, 0, 0));
        returnbookdetailsPanel.setBounds(10, 10, 565, 615);
        returnbookpanel.add(returnbookdetailsPanel);
        returnbookdetailsPanel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Issue Date :");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBounds(68, 215, 202, 13);
        returnbookdetailsPanel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Book Name :");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(68, 267, 147, 13);
        returnbookdetailsPanel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Student Name :");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_2.setBounds(68, 321, 202, 13);
        returnbookdetailsPanel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Issue Date :");
        lblNewLabel_3.setForeground(new Color(255, 255, 255));
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_3.setBounds(68, 388, 221, 13);
        returnbookdetailsPanel.add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Due Date :");
        lblNewLabel_4.setForeground(new Color(255, 255, 255));
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_4.setBounds(68, 456, 235, 13);
        returnbookdetailsPanel.add(lblNewLabel_4);
        
        returnissueIdLabel = new JLabel("");
        returnissueIdLabel.setForeground(new Color(255, 255, 255));
        returnissueIdLabel.setBounds(188, 215, 162, 13);
        returnbookdetailsPanel.add(returnissueIdLabel);
        
        returnbooknameLabel = new JLabel("");
        returnbooknameLabel.setForeground(new Color(255, 255, 255));
        returnbooknameLabel.setBounds(188, 267, 172, 13);
        returnbookdetailsPanel.add(returnbooknameLabel);
        
        returnstudentnameLabel = new JLabel("");
        returnstudentnameLabel.setForeground(new Color(255, 255, 255));
        returnstudentnameLabel.setBounds(188, 321, 162, 13);
        returnbookdetailsPanel.add(returnstudentnameLabel);
        
        returnissuedateLabel = new JLabel("");
        returnissuedateLabel.setForeground(new Color(255, 255, 255));
        returnissuedateLabel.setBounds(188, 388, 162, 13);
        returnbookdetailsPanel.add(returnissuedateLabel);
        
        returnduedateLabel = new JLabel("");
        returnduedateLabel.setForeground(new Color(255, 255, 255));
        returnduedateLabel.setBounds(188, 456, 162, 13);
        returnbookdetailsPanel.add(returnduedateLabel);
        
        lblNewLabel_19_6 = new JLabel("Book Details");
        lblNewLabel_19_6.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\informatics_book_32px.png"));
        lblNewLabel_19_6.setForeground(new Color(255, 255, 255));
        lblNewLabel_19_6.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_19_6.setBounds(182, 96, 158, 28);
        returnbookdetailsPanel.add(lblNewLabel_19_6);
        
        JSeparator separator_3 = new JSeparator();
        separator_3.setBounds(188, 134, 127, 26);
        returnbookdetailsPanel.add(separator_3);
        
        lblNewLabel_19_5 = new JLabel("_____________");
        lblNewLabel_19_5.setForeground(new Color(210, 0, 0));
        lblNewLabel_19_5.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_19_5.setBounds(808, 118, 158, 28);
        returnbookpanel.add(lblNewLabel_19_5);
        
        lblNewLabel_19_7 = new JLabel("Return Book");
        lblNewLabel_19_7.setIcon(new ImageIcon("C:\\Users\\My Acer\\Downloads\\return_book_32pxred.png"));
        lblNewLabel_19_7.setForeground(new Color(210, 0, 0));
        lblNewLabel_19_7.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_19_7.setBounds(808, 99, 158, 28);
        returnbookpanel.add(lblNewLabel_19_7);
        
        returnbooknameLabel_1 = new JLabel("___________________________________________________________________");
        returnbooknameLabel_1.setBackground(new Color(210,0,0));
        returnbooknameLabel_1.setForeground(new Color(210,0,0));
        returnbooknameLabel_1.setBounds(766, 210, 393, 13);
        returnbookpanel.add(returnbooknameLabel_1);
        
        returnbooknameLabel_2 = new JLabel("___________________________________________________________________");
        returnbooknameLabel_2.setForeground(new Color(210, 0, 0));
        returnbooknameLabel_2.setBackground(new Color(210, 0, 0));
        returnbooknameLabel_2.setBounds(766, 315, 393, 13);
        returnbookpanel.add(returnbooknameLabel_2);

        return returnbookpanel;
    }
    private void findBookDetails() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String bookId = returnBookIdField.getText();
            String studentId = returnStudentIdField.getText();

            String query = "SELECT * FROM issued_books WHERE bookid = ? AND studentid = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, bookId);
                preparedStatement.setString(2, studentId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        returnissueIdLabel.setText("" + resultSet.getString("issueid"));
                        returnbooknameLabel.setText("" + resultSet.getString("bookname"));
                        returnstudentnameLabel.setText("" + resultSet.getString("studentname"));
                        returnissuedateLabel.setText("" + resultSet.getString("issuedate"));
                        returnduedateLabel.setText("" + resultSet.getString("duedate"));
                    } else {
                        // Clear labels if no record found
                        clearReturnBookDetails();
                        JOptionPane.showMessageDialog(this, "Book not issued to this student.");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
    private void issueBook() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String bookId = issueBookIdField.getText();
            String studentId = issueStudentIdField.getText();

            // Retrieve selected dates from JDateChooser
            Date issueDate = issuedate.getDate();
            Date dueDate = duedate.getDate();

            // Format dates as strings
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedIssueDate = dateFormat.format(issueDate);
            String formattedDueDate = dateFormat.format(dueDate);

            // Check if the book and student exist in the database
            if (isBookExists(bookId) && isStudentExists(studentId)) {
                // Check if there is at least 1 book in stock
                int currentQuantity = getCurrentBookQuantity(bookId);

                if (currentQuantity > 0) {
                    // Perform the book issue logic (e.g., update database records, etc.)

                    // Update the quantity of books in stock in the books table
                    updateBookQuantity(bookId, currentQuantity - 1);

                    // Display book details in the bookDetailsPanel
                    displayBookDetails(bookId);

                    // Display student details in the studentDetailsPanel
                    displayStudentDetails(studentId);

                    // Clear the issue book details after a successful issue
                    clearIssueBookDetails();

                    // Update the issued_books table with the issued quantity
                    int quantityToBorrow = 1; // Since we are issuing 1 book
                    if (issueBookToStudent(bookId, studentId, quantityToBorrow, formattedIssueDate, formattedDueDate)) {
                        reloadTableData(); // Refresh the book table data
                        populateIssuedBooksTable(); // Refresh the issued_books table data
                        JOptionPane.showMessageDialog(this, "Book issued successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update issued_books table.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No stock available for this book.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Book or student does not exist.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }


    public void reloadTableData() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String selectQuery = "SELECT * FROM addbook";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                 ResultSet resultSet = selectStatement.executeQuery()) {

                DefaultTableModel tableModel = (DefaultTableModel) addbooktable.getModel();
                
                tableModel.setRowCount(0); // Clear existing rows

                // Populate the table model with data from the result set
                while (resultSet.next()) {
                    Object[] rowData = {
                        resultSet.getString("bookid"),
                        resultSet.getString("bookname"),
                        resultSet.getString("author"),
                        resultSet.getString("quantity")
                    };
                    tableModel.addRow(rowData);
                }
                 

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Add this method to get the current quantity of books in stock
    private int getCurrentBookQuantity(String bookId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT quantity FROM addbook WHERE bookid = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, bookId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("quantity");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error getting book quantity: " + ex.getMessage());
        }
        return 0;
    }

    private void updateBookQuantity(String bookId, int quantity) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String updateQuery = "UPDATE addbook SET quantity = ? WHERE bookid = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setInt(1, quantity);
                updateStatement.setString(2, bookId);
                updateStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating book quantity: " + ex.getMessage());
        }
    }

    // Add this method to perform the book issuing logic
    private boolean issueBookToStudent(String bookId, String studentId, int issuedQuantity, String issueDate, String dueDate) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String insertQuery = "INSERT INTO issued_books(bookid, studentid, bookname, studentname, issuedate, duedate, status, quantity) VALUES (?, ?, ?, ?, ?, ?, 'Pending', ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, bookId);
                preparedStatement.setString(2, studentId);
                preparedStatement.setString(3, getBookName(bookId));
                preparedStatement.setString(4, getStudentName(studentId));
                preparedStatement.setString(5, issueDate);
                preparedStatement.setString(6, dueDate);
                preparedStatement.setInt(7, issuedQuantity);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error issuing book: " + ex.getMessage());
            return false;
        }
    }

    // Add this method to get the book name based on the book ID
    private String getBookName(String bookId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT bookname FROM addbook WHERE bookid = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, bookId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("bookname");
                    }
                }
            }
        }
        return "";
    }

    // Add this method to get the student name based on the student ID
    private String getStudentName(String studentId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT studentname FROM addstudent WHERE studentid = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, studentId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("studentname");
                    }
                }
            }
        }
        return "";
    }

    // Add this method to clear the issue book details
    private void clearIssueBookDetails() {
        issueBookIdField.setText("");
        issueStudentIdField.setText("");
      
        clearBookDetails();
        clearStudentDetails();
    }
    private void returnBook() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String bookId = returnBookIdField.getText();
            String studentId = returnStudentIdField.getText();

            // Check if the book and student exist in the database
            if (isBookExists(bookId) && isStudentExists(studentId)) {
                // Check if the book has already been returned
                if (isBookAlreadyReturned(bookId, studentId)) {
                	clearbookandstudent();
                    JOptionPane.showMessageDialog(this, "Book has already been returned.");
                    return;  // Exit the method without proceeding
                }

                // Perform the book returning logic (e.g., update database records, etc.)

                // Update the status of the returned book in the issued_books table
                updateReturnedBookStatus(bookId, studentId);

                // Get the quantity of books issued to the student
                int issuedQuantity = getIssuedBookQuantity(bookId, studentId);

                // Update the quantity of books in stock in the books table
                returnBookToStock(bookId, issuedQuantity);

                // Display book details in the bookDetailsPanel
                displayBookDetails(bookId);

                // Display student details in the studentDetailsPanel
                displayStudentDetails(studentId);

                // Clear the return book details after successful return
                clearReturnBookDetails();
                
                reloadTableData(); // Refresh the book table data
                populateIssuedBooksTable(); // Refresh the issued_books table data

                JOptionPane.showMessageDialog(this, "Book returned successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Book or student does not exist.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
    
    private boolean isBookAlreadyReturned(String bookId, String studentId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT * FROM issued_books WHERE bookid = ? AND studentid = ? AND status = 'Returned'";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, bookId);
                preparedStatement.setString(2, studentId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // If resultSet has next, book has already been returned
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // Handle the exception based on your application's requirements
        }
    }
    private void returnBookToStock(String bookId, int issuedQuantity) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            // Update the issued_books table
     

            // Update the addbook table
            String updateQuantityQuery = "UPDATE addbook SET quantity=quantity+? WHERE bookid=?";
            try (PreparedStatement updateQuantityStatement = connection.prepareStatement(updateQuantityQuery)) {
                updateQuantityStatement.setInt(1, issuedQuantity);
                updateQuantityStatement.setString(2, bookId);
                updateQuantityStatement.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error returning book.");
        }
    }

    private int getIssuedBookQuantity(String bookId, String studentId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String query = "SELECT quantity FROM issued_books WHERE bookid = ? AND studentid = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, bookId);
                preparedStatement.setString(2, studentId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("quantity");
                    }
                }
            }
        }
        return 0;
    }
    private void updateReturnedBookStatus(String bookId, String studentId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String updateQuery = "UPDATE issued_books SET status = 'Returned' WHERE bookid = ? AND studentid = ? AND status = 'Pending'";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setString(1, bookId);
                updateStatement.setString(2, studentId);
                updateStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating book status: " + ex.getMessage());
        }
    }
    // Add this method to clear the return book details
    private void clearReturnBookDetails() {
    	returnissueIdLabel.setText("");
    	returnbooknameLabel.setText("");
    	returnstudentnameLabel.setText("");
        returnissuedateLabel.setText("");
        returnduedateLabel.setText("");
    }
    private void clearbookandstudent() {
    	returnBookIdField.setText("");
    	returnStudentIdField.setText("");
    	
    	
    }
    private void searchRecordsByDate() {
    	 try {
             // Get the selected issue date and due date
             Date issueDate = searchIssueDateChooser.getDate();
             Date dueDate = searchDueDateChooser.getDate();

             // Format the dates to match the database date format
             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
             String formattedIssueDate = dateFormat.format(issueDate);
             String formattedDueDate = dateFormat.format(dueDate);

             // Perform a database query to fetch records where both issue date and due date match
             String query = "SELECT * FROM issued_books WHERE issuedate = ? AND duedate = ?";
             try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
                     PreparedStatement statement = connection.prepareStatement(query)) {
                 statement.setString(1, formattedIssueDate);
                 statement.setString(2, formattedDueDate);

                 ResultSet resultSet = statement.executeQuery();

                 // Update the table model with the new data
                 DefaultTableModel model = new DefaultTableModel();
                 model.addColumn("Issue ID");
                 model.addColumn("Book Name");
                 model.addColumn("Student Name");
                 model.addColumn("Issue Date");
                 model.addColumn("Due Date");
                 model.addColumn("Status");

                 while (resultSet.next()) {
                     model.addRow(new Object[]{
                         resultSet.getInt("issueid"),
                         resultSet.getString("bookname"),
                         resultSet.getString("studentname"),
                         resultSet.getString("issuedate"),
                         resultSet.getString("duedate"),
                         resultSet.getString("status")
                     });
                 }

                 // Set the updated model to the JTable
                 issuedBooksTable.setModel(model);

             } catch (SQLException ex) {
                 ex.printStackTrace();
             }
         } catch (Exception ex) {
             ex.printStackTrace();
             JOptionPane.showMessageDialog(null, "Error in searching records.", "Error", JOptionPane.ERROR_MESSAGE);
         }
     }
    private void populateIssuedBooksTable() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String selectQuery = "SELECT * FROM issued_books";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                 ResultSet resultSet = selectStatement.executeQuery()) {

                // Create a new table model
                DefaultTableModel tableModel = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false; // Make the table uneditable
                }
            };
                tableModel.setColumnIdentifiers(new Object[]{"Issue ID", "Book Name", "Student Name", "Issue Date", "Due Date", "Status"});

                // Populate the table model with data from the result set
                while (resultSet.next()) {
                    tableModel.addRow(new Object[]{
                            resultSet.getString("issueid"),
                            resultSet.getString("bookname"),
                            resultSet.getString("studentname"),
                            resultSet.getString("issuedate"),
                            resultSet.getString("duedate"),
                            resultSet.getString("status")
                    });
                }

                // Update the JTable with the new data
                issuedBooksTable.setModel(tableModel);
                JTableHeader header = issuedBooksTable.getTableHeader();
 	            header.setBackground(new Color(16, 56, 92));
 	            header.setForeground(Color.WHITE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void populateViewIssuedBooksTable() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String selectQuery = "SELECT * FROM issued_books WHERE status = 'Pending'";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                 ResultSet resultSet = selectStatement.executeQuery()) {

                // Create a new table model
                DefaultTableModel tableModel = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false; // Make the table uneditable
                }
            };
                tableModel.setColumnIdentifiers(new Object[]{"Issue ID", "Book Name", "Student Name", "Issue Date", "Due Date", "Status"});

                // Populate the table model with data from the result set
                while (resultSet.next()) {
                    tableModel.addRow(new Object[]{
                            resultSet.getString("issueid"),
                            resultSet.getString("bookname"),
                            resultSet.getString("studentname"),
                            resultSet.getString("issuedate"),
                            resultSet.getString("duedate"),
                            resultSet.getString("status")
                    });
                }

                // Update the JTable with the new data
                
                table_2.setModel(tableModel);
                reloadTableData();
                JTableHeader header = table_2.getTableHeader();
 	            header.setBackground(new Color(16, 56, 92));
 	            header.setForeground(Color.WHITE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
    public void recordsreloadTableData() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String selectQuery = "SELECT * FROM issued_books WHERE status = 'Pending'";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                 ResultSet resultSet = selectStatement.executeQuery()) {

                DefaultTableModel tableModel = (DefaultTableModel) table_2.getModel();
                
                tableModel.setRowCount(0); // Clear existing rows

                // Populate the table model with data from the result set
                while (resultSet.next()) {
                    Object[] rowData = {
                    		resultSet.getString("issueid"),
                        resultSet.getString("bookname"),
                        resultSet.getString("studentname"),
                        resultSet.getString("issuedate"),
                        resultSet.getString("duedate"),
                   
                        resultSet.getString("status"),
                    };
                    tableModel.addRow(rowData);
                }
                 

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void updateSearchResults() {
    String searchText = textField.getText().trim();

    // Perform a database query based on the search text
    // Update the searchResultTable with the results

    try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
        String query = "SELECT * FROM addbook WHERE bookname LIKE ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + searchText + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Populate the JTable with the result set
                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.setColumnIdentifiers(new Object[]{"Book ID", "Book Name", "Author", "Quantity"});

                while (resultSet.next()) {
                    tableModel.addRow(new Object[]{
                            resultSet.getString("bookid"),
                            resultSet.getString("bookname"),
                            resultSet.getString("author"),
                            resultSet.getString("quantity")
                    });
                }

                // Update the JTable with the new data
                searchtable.setModel(tableModel);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
}

    private void populateSearchBookTable() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String selectQuery = "SELECT * FROM addbook";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                 ResultSet resultSet = selectStatement.executeQuery()) {

                // Create a new table model
                DefaultTableModel tableModel = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false; // Make the table uneditable
                }
            };
                tableModel.setColumnIdentifiers(new Object[]{"Book ID", "Book Name", "Author","Quantity"});

                // Populate the table model with data from the result set
                while (resultSet.next()) {
                    tableModel.addRow(new Object[]{
                            resultSet.getString("bookid"),
                            resultSet.getString("bookname"),
                            resultSet.getString("author"),
                            resultSet.getString("quantity"),
                  
                    });
                }

                // Update the JTable with the new data
                searchtable.setModel(tableModel);
               
                JTableHeader header = searchtable.getTableHeader();
 	            header.setBackground(new Color(16, 56, 92));
 	            header.setForeground(Color.WHITE);
 	           reloadTableDatasearch();
           
              
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
}
    public void reloadTableDatasearch() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            String selectQuery = "SELECT * FROM addbook";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                 ResultSet resultSet = selectStatement.executeQuery()) {

                DefaultTableModel tableModel = (DefaultTableModel) searchtable.getModel();
                
                tableModel.setRowCount(0); // Clear existing rows

                // Populate the table model with data from the result set
                while (resultSet.next()) {
                    Object[] rowData = {
                        resultSet.getString("bookid"),
                        resultSet.getString("bookname"),
                        resultSet.getString("author"),
                        resultSet.getString("quantity")
                    };
                    tableModel.addRow(rowData);
                }
                 

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}