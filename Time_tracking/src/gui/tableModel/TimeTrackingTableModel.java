package gui.tableModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import database.DBConnection;

public class TimeTrackingTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int columnCount = 4;
	private ArrayList<String []> dataArrayList;
	
	
	public TimeTrackingTableModel(){
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
			case 0: return "Lastname";
			case 1: return "Name";
			case 2: return "IN";
			case 3: return "OUT";
		
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
		ResultSet rs = connect.resultSetQuery("SELECT * FROM time_tracking_by_name;");
		dataArrayList.clear();
		try {
			while (rs.next()){
							
				String [] row = {
						rs.getString("lastname"),
						rs.getString("name"),
						rs.getString("time_in"),
						rs.getString("time_out")
				};
				addData(row);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
