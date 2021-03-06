package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Statistics;

public class StatisticsRepository {
private Connection connection;
	
	private String createTableSql = "CREATE TABLE statistics("
			+ "id bigint GENERATED BY DEFAULT AS IDENTITY,"
			+ "kills INT,"
			+ "deaths INT,"
			+ "kdRatio DECIMAL(2,2),"
			+ ")";
	
	private Statement createTable;
	
	
	private String insertSql = "INSERT INTO statistics(kills,deaths,kdRatio) VALUES(?,?,?)";
	private String deleteSql = "DELETE FROM statistics WHERE id = ?";
	private String updateSql = "UPDATE statistics set kills=?, deaths=?, kdRatio=? WHERE id=?";
	private String selectByIdSql = "SELECT * FROM statistics WHERE id=?";
	private String selectAllSql = "SELECT * FROM statistics";
	
	private PreparedStatement insert;
	private PreparedStatement delete;
	private PreparedStatement update;
	private PreparedStatement selectById;
	private PreparedStatement selectAll;
	
	public StatisticsRepository(Connection connection) {
		this.connection = connection;
		
		try {
			createTable = connection.createStatement();
			
			boolean tableExists = false;
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			while(rs.next()){
				if(rs.getString("TABLE_NAME").equalsIgnoreCase("statistics")){
					tableExists=true;
					break;
				}
			}
			if(!tableExists)
			createTable.executeUpdate(createTableSql);
			insert = connection.prepareStatement(insertSql);
			delete = connection.prepareStatement(deleteSql);	
			update = connection.prepareStatement(updateSql);
			selectById = connection.prepareStatement(selectByIdSql);
			selectAll = connection.prepareStatement(selectAllSql);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Statistics get(int statisticsId){
		try{
			
			selectById.setInt(1, statisticsId);
			ResultSet rs = selectById.executeQuery();
			while(rs.next()){
				Statistics result = new Statistics();
				result.setId(statisticsId);
				result.setKills(rs.getInt("kills"));
				result.setDeaths(rs.getInt("deaths"));
				result.setKdRatio(rs.getDouble("kdRatio"));
				return result;
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}

	public List<Statistics> getAll(){
		try{
			List<Statistics> result = new ArrayList<Statistics>();
			ResultSet rs = selectAll.executeQuery();
			while(rs.next()){
				Statistics p = new Statistics();
				
				p.setId(rs.getInt("id"));
				p.setKills(rs.getInt("kills"));
				p.setDeaths(rs.getInt("deaths"));
				p.setKdRatio(rs.getDouble("kdRatio"));
				result.add(p);
			}
			return result;
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public void delete(Statistics p){
		try{
			delete.setInt(1, p.getId());
			delete.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public void add(Statistics p){
		try{	
			insert.setInt(1, p.getKills());
			insert.setInt(2, p.getDeaths());
			insert.setDouble(3, p.getKdRatio());
			insert.executeUpdate();
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
	}
	
	public void update(Statistics p){
		try{
			update.setInt(1, p.getKills());
			update.setInt(2, p.getDeaths());
			update.setDouble(3, p.getKdRatio());
			update.setInt(4, p.getId());
			update.executeUpdate();
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
	}
}
