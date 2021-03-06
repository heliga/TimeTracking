package gui.tableModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListDataListener;

import database.DBConnection;

public class EmployeeComboBoxModel extends DefaultComboBoxModel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5280793957314699166L;
	
	

	public void addElement(DBConnection connect){
		
		ResultSet rs = connect.resultSetQuery("SELECT card_id FROM time_card;");
		try {
			while (rs.next()){
				addElement(rs.getString("card_id"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

	

}
