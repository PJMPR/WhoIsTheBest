package dao;

import java.sql.Connection;
import java.sql.SQLException;

import dao.mappers.IMapResultSetIntoEntity;
import model.Team;

public class TeamRepository extends RepositoryBase<Team> {
	
	public TeamRepository(Connection connection, IMapResultSetIntoEntity<Team> mapper) {
		super(connection, mapper);
	}
	
	@Override
	protected String tableName() {
		return "team";
	}
	
	@Override
	protected String createTableSql() {
		return "CREATE TABLE team("
				+ "id bigint GENERATED BY DEFAULT AS IDENTITY,"
				+ "name VARCHAR(20),"
				+ "country VARCHAR(20),"
				+ ")";
	}
	
	@Override
	protected String insertSql() {
		return "INSERT INTO team(name,country) VALUES(?,?)";
	}
	
	@Override
	protected String deleteSql() {
		return "DELETE FROM team WHERE id = ?";
	}
	
	@Override
	protected String updateSql() {
		return "UPDATE team set name=?,country=? WHERE id=?";
	}
	
	@Override
	protected String selectByIdSql() {
		return "SELECT * FROM team WHERE id=?";
	}

	@Override
	protected String selectAllSql() {
		return "SELECT * FROM team";
	}
	
	@Override
	protected void setupInsert(Team entity) throws SQLException {
		insert.setString(1, entity.getName());
		insert.setString(2, entity.getCountry());
	}
	
	@Override
	protected void setupUpdate(Team entity) throws SQLException {
		update.setString(1, entity.getName());
		update.setString(2, entity.getCountry());
		update.setInt(3, entity.getId());
	}
}
