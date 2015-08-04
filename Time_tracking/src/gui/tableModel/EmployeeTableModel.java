package gui.tableModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import database.DBConnection;

public class EmployeeTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int columnCount = 3;
	private ArrayList<String []> dataArrayList;
	
	
	public EmployeeTableModel(){
		dataArrayList = new ArrayList<String []>();
		for (int i = 0; i < dataArrayList.size(); i++){
			dataArrayList.add(new String[getColumnCount()]);
		}
	}

	@Override
	public int getColumnCount() {
		return columnCount;
	}
	
	
	@Override
	public String getColumnName (int columnIndex){
		switch(columnIndex){
		case 0: return "# id";
		case 1: return "Lastname";
		case 2: return "Name";
		
		}
		return "";
		
	}

	@Override
	public int getRowCount() {
		return dataArrayList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String []rows = dataArrayList.get(rowIndex);
		return rows[columnIndex];
	}
	
	public void addData(String []row){
		String []rowTable = new String[getColumnCount()];
		rowTable = row;
		dataArrayList.add(rowTable);
	}
	
	public void addData(DBConnection connect){
		ResultSet rs = connect.resultSetQuery("SELECT * FROM employee;");
		dataArrayList.clear();
		try {
			while (rs.next()){
							
				String [] row = {
						rs.getString("employee_id"),
						rs.getString("lastname"),
						rs.getString("name")
				};
				addData(row);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
