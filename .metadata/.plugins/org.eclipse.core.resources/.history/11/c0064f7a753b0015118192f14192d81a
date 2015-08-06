package gui;

import gui.tableModel.EmployeeTableModel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import database.DBConnection;

public class EmployeePanel extends JPanel implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1699771474484445351L;
	
	private EmployeeTableModel tableModel = new EmployeeTableModel();
	private JTable employeeTable = new JTable(tableModel);
	private JScrollPane employeeTableScrollPane = new JScrollPane(employeeTable);
	private JButton addButton = new JButton("Добавить");
	private JButton deleteButton = new JButton("Удалить");
	private JButton clearButton = new JButton("Очистить");
	private JButton changeButton = new JButton("Изменить");
	/*
	private JTextField cardIdTextField = new JTextField("card number");
	private JButton inButton  = new JButton("Войти");
	private JButton outButton = new JButton("Выйти");
	*/
	private DBConnection connect;
	
	public EmployeePanel(){
		
	}
	
	public EmployeePanel(DBConnection connect){
		this.connect = connect;
		setLayout(new GridBagLayout());
		
		(new Thread(this)).start();
	}
	
	public void init(){
		
		add(employeeTableScrollPane, new GridBagConstraints(0, 0, 4, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.BOTH, 
				new Insets(1, 1, 1, 1), 0, 0));
		
		add(addButton, new GridBagConstraints(0, 1, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		
		add(deleteButton, new GridBagConstraints(1, 1, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		
		deleteButton.addActionListener(new deleteButtonActionListener());
		
		add(changeButton, new GridBagConstraints(2, 1, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		
		add(clearButton, new GridBagConstraints(3, 1, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 
				new Insets(1, 1, 1, 1), 0, 0));
		/*
		add(cardIdTextField, new GridBagConstraints(0, 2, 4, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.CENTER, 
				new Insets(1, 1, 1, 1), 0, 0));
		
		add(inButton, new GridBagConstraints(1, 4, 2, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.CENTER, 
				new Insets(1, 1, 1, 1), 0, 0));
		
		add(outButton, new GridBagConstraints(2, 4, 2, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.CENTER, 
				new Insets(1, 1, 1, 1), 0, 0));
				*/
	}

	@Override
	public void run() {
		while (true) {
			try {
				tableModel.addData(connect);
				repaint();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	class deleteButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			int id = 1;
			connect.sqlQuery("DELETE FROM employee WHERE employee_id = " + id + ";");
			
		}
		
	}
	
}
