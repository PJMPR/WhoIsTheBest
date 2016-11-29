package dao;

import java.sql.Connection;
import java.sql.SQLException;

import dao.mappers.IMapResultSetIntoEntity;
import model.Player;

public class PlayerRepository extends RepositoryBase<Player> {
	
	public PlayerRepository(Connection connection, IMapResultSetIntoEntity<Player> mapper) {
		super(connection, mapper);
	}
	
	@Override
	protected String tableName() {
		return "player";
	}
	
	@Override
	protected String createTableSql() {
		return "CREATE TABLE player("
				+ "id bigint GENERATED BY DEFAULT AS IDENTITY,"
				+ "name VARCHAR(20),"
				+ "age INT,"
				+ "country VARCHAR(20),"
				+ "team VARCHAR(20),"
				+ "experience INT,"
				+ ")";
	}
	
	@Override
	protected String insertSql() {
		return "INSERT INTO player(name,age,country,team,experience) VALUES(?,?,?,?,?)";
	}
	
	@Override
	protected String deleteSql() {
		return "DELETE FROM player WHERE id = ?";
	}
	
	@Override
	protected String updateSql() {
		return "UPDATE player set name=?, age=?, country=?, team=?, experience=? WHERE id=?";
	}
	
	@Override
	protected String selectByIdSql() {
		return "SELECT * FROM player WHERE id=?";
	}

	@Override
	protected String selectAllSql() {
		return "SELECT * FROM player";
	}
	
	@Override
	protected void setupInsert(Player entity) throws SQLException {
		insert.setString(1, entity.getName());
		insert.setInt(2, entity.getAge());
		insert.setString(3, entity.getCountry());
		insert.setString(4, entity.getTeam());
		insert.setInt(5, entity.getExperience());
	}
	
	@Override
	protected void setupUpdate(Player entity) throws SQLException {
		update.setString(1, entity.getName());
		update.setInt(2, entity.getAge());
		update.setString(3, entity.getCountry());
		update.setString(4, entity.getTeam());
		update.setInt(5, entity.getExperience());
		update.setInt(6, entity.getId());	
	}
}
