package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.mappers.IMapResultSetIntoEntity;
import model.IHaveId;

public abstract class RepositoryBase<TEntity extends IHaveId> implements IRepository<TEntity> {

	protected Connection connection;
	protected Statement createTable;
	protected PreparedStatement insert;
	protected PreparedStatement delete;
	protected PreparedStatement update;
	protected PreparedStatement selectById;
	protected PreparedStatement selectAll;
	
	protected IMapResultSetIntoEntity<TEntity> mapper;

	protected RepositoryBase(Connection connection, 
			IMapResultSetIntoEntity<TEntity> mapper) {
		this.connection = connection;
		this.mapper = mapper;
		try {
			createTable = connection.createStatement();
			createTableIfNotExists();
			insert = connection.prepareStatement(insertSql());
			delete = connection.prepareStatement(deleteSql());	
			update = connection.prepareStatement(updateSql());
			selectById = connection.prepareStatement(selectByIdSql());
			selectAll = connection.prepareStatement(selectAllSql());	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public TEntity get(int playerId){
		try{
			selectById.setInt(1, playerId);
			ResultSet rs = selectById.executeQuery();
			while(rs.next()){
				return mapper.map(rs);
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}

	public List<TEntity> getAll(){
		try{
			List<TEntity> result = new ArrayList<TEntity>();
			ResultSet rs = selectAll.executeQuery();
			while(rs.next()){
				result.add(mapper.map(rs));
			}
			return result;
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}

	public void add(TEntity entity){
		try{
			setupInsert(entity);
			insert.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
	}

	public void update(TEntity entity){
		try{
			setupUpdate(entity);
			update.executeUpdate();		
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}

	public void delete(TEntity entity){
		try{
			delete.setInt(1, entity.getId());
			delete.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	private void createTableIfNotExists()
			throws SQLException {
		boolean tableExists = false;
		ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
		while(rs.next()){
			if(rs.getString("TABLE_NAME").equalsIgnoreCase(tableName())){
				tableExists=true;
				break;
			}
		}
		if(!tableExists)
			createTable.executeUpdate(createTableSql());
	}
	

	protected String deleteSql() {
		return "DELETE FROM "
				+ tableName()
				+ " WHERE id = ?";
	}
	
	protected String selectByIdSql() {
		return "SELECT * FROM "
				+ tableName()
				+ " WHERE id=?";
	}

	protected String selectAllSql() {
		return "SELECT * FROM " + tableName();
	}

	protected abstract void setupInsert(TEntity entity) throws SQLException;
	protected abstract void setupUpdate(TEntity entity) throws SQLException;
	protected abstract String tableName();
	protected abstract String createTableSql();
	protected abstract String insertSql();
	protected abstract String updateSql();
}

