package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Player;

public class PlayerRepository {
	private Connection connection;
	
	private String createTableSql = "CREATE TABLE player("
			+ "id bigint GENERATED BY DEFAULT AS IDENTITY,"
			+ "name VARCHAR(20),"
			+ "age INT,"
			+ "country VARCHAR(20),"
			+ "team VARCHAR(20),"
			+ "experience INT,"
			+ ")";
	
	private Statement createTable;
	
	
	private String insertSql = "INSERT INTO player(name,age,country,team,experience) VALUES(?,?,?,?,?)";
	private String deleteSql = "DELETE FROM player WHERE id = ?";
	private String updateSql = "UPDATE player set name=?, age=?, country=?, team=?, experience=? WHERE id=?";
	private String selectByIdSql = "SELECT * FROM player WHERE id=?";
	private String selectAllSql = "SELECT * FROM player";
	
	private PreparedStatement insert;
	private PreparedStatement delete;
	private PreparedStatement update;
	private PreparedStatement selectById;
	private PreparedStatement selectAll;
	
	public PlayerRepository(Connection connection) {
		this.connection = connection;
		
		try {
			createTable = connection.createStatement();
			
			boolean tableExists = false;
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			while(rs.next()){
				if(rs.getString("TABLE_NAME").equalsIgnoreCase("player")){
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

	public Player get(int playerId){
		try{
			
			selectById.setInt(1, playerId);
			ResultSet rs = selectById.executeQuery();
			while(rs.next()){
				Player result = new Player();
				result.setId(playerId);
				result.setName(rs.getString("name"));
				result.setAge(rs.getInt("age"));
				result.setCountry(rs.getString("country"));
				result.setTeam(rs.getString("team"));
				result.setExperience(rs.getInt("experience"));
				return result;
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}

	public List<Player> getAll(){
		try{
			List<Player> result = new ArrayList<Player>();
			ResultSet rs = selectAll.executeQuery();
			while(rs.next()){
				Player p = new Player();
				
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setAge(rs.getInt("age"));
				p.setCountry(rs.getString("country"));
				p.setTeam(rs.getString("team"));
				p.setExperience(rs.getInt("experience"));
				result.add(p);
			}
			return result;
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public void delete(Player p){
		try{
			delete.setInt(1, p.getId());
			delete.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public void add(Player p){
		try{	
			insert.setString(1, p.getName());
			insert.setInt(2, p.getAge());
			insert.setString(3, p.getCountry());
			insert.setString(4, p.getTeam());
			insert.setInt(5, p.getExperience());
			insert.executeUpdate();
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
	}
	
	public void update(Player p){
		try{
			update.setString(1, p.getName());
			update.setInt(2, p.getAge());
			update.setString(3, p.getCountry());
			update.setString(4, p.getTeam());
			update.setInt(5, p.getExperience());
			update.setInt(6, p.getId());
			update.executeUpdate();
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
	}
	
}
