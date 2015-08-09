package gui;

import gui.tableModel.EmployeeComboBoxModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import database.DBConnection;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MainPanel mainPanel;
	private DBConnection connect;
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		connect = new DBConnection("time_tracking.db");
		connect.init();
		
		setTitle("Time tracking");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(300, 380));
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		mainPanel = new MainPanel(connect);
		mainPanel.init();
		add(mainPanel, BorderLayout.CENTER);
		
		//connect.finalize();
		
	}
	
	public DBConnection getConnect(){
		return connect;
	}
	
	class MainPanel extends JPanel implements Runnable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -4118100197599717089L;
		
		private EmployeeComboBoxModel employeeComboBoxModel = new EmployeeComboBoxModel();
		private JLabel titleLabel = new JLabel("Выберите карту сотрудника:");
		private JComboBox<String> employeeComboBox = new JComboBox<String>(employeeComboBoxModel);
		private JLabel employeeLabel = new JLabel();
		private JButton addButton = new JButton("Добавить сотрудника");
		private JButton inButton = new JButton("IN");
		private JButton outButton = new JButton("OUT");
		private JButton exitButton = new JButton("Выход");
		
		private DBConnection connect;
		
		public MainPanel(){
					
		}

		public MainPanel(DBConnection connect){
			this.connect = connect;
			setLayout(new MigLayout("insets 15%", "[left]10%[right]", "[][]5%[]5%[]10%[]10%[]"));
			
			(new Thread(this)).start();
					
		}

		public void init(){
			
			add(titleLabel, "wrap, span 2, center");
			
			employeeComboBoxModel.addElement(connect);
			employeeComboBox.setMaximumRowCount(5);
			employeeComboBox.setSelectedIndex(0);
			
			add(employeeComboBox, "wrap, span 2, center");
			
			add(employeeLabel, "wrap, span 2, center");
			
			add(addButton, "wrap, span 2, center");
			addButton.addActionListener(new AddButtonActionListener());
			
			add(inButton);
			inButton.addActionListener(new inButtonActionListener());
			
			add(outButton, "wrap");
			outButton.addActionListener(new outButtonActionListener());
			
			add(exitButton, "span 2, center");
			exitButton.addActionListener(new exitButtonActionListener());
			
		}
		
		public String getEmployeeName(DBConnection connect){
			String employeeName = "";
			ResultSet rs = connect.resultSetQuery("SELECT name, lastname FROM employee, time_card WHERE card_id = " 
													+  Integer.parseInt((String) employeeComboBoxModel.getSelectedItem())
													+ " and time_card.employee_id = employee.employee_id;");
			try {
				while (rs.next()){
					employeeName = rs.getString("name") + " " + rs.getString("lastname");
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return employeeName;
		}

		@Override
		public void run() {
			while (true) {
				try {
					repaint();
					Thread.sleep(1000);
					employeeLabel.setText(getEmployeeName(connect));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		class AddButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
				AddEmployeeFrame addEmployeeFrame = new AddEmployeeFrame(connect);
				addEmployeeFrame.setVisible(true);
				addEmployeeFrame.init();
				//addEmployeeFrame.pack();
			}
		}
		
		class inButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				connect.sqlQuery("INSERT INTO time_tracking(card_id, time_in) VALUES (" 
								+ Integer.parseInt((String) employeeComboBoxModel.getSelectedItem())
								+ ", datetime('now'));");
				
					
				

			}
		}
		
		class outButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				connect.sqlQuery("UPDATE time_tracking SET time_out = datetime('now') WHERE card_id = " 
								+ Integer.parseInt((String) employeeComboBoxModel.getSelectedItem())
								+ " AND time_out IS NULL;");

			}
		}
		
		class exitButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				connect.finalize();
				System.exit(0);

			}
		}
		
		
	}


}
