package gui;


import java.awt.BorderLayout;
import java.awt.Dimension;



import javax.swing.JFrame;


import database.DBConnection;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EmployeePanel employeePanel;
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
		setPreferredSize(new Dimension(600, 400));
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		mainPanel = new MainPanel(connect);
		mainPanel.init();
		add(mainPanel, BorderLayout.CENTER);
		
		//connect.finalize();
		
	}

}
