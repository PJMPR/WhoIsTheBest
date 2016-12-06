package dao;

import java.sql.Connection;
import java.sql.SQLException;

import dao.mappers.IMapResultSetIntoEntity;
import model.Statistics;

public class StatisticsRepository extends RepositoryBase<Statistics> {
	
	public StatisticsRepository(Connection connection, IMapResultSetIntoEntity<Statistics> mapper) {
		super(connection, mapper);
	}
	
	@Override
	protected String tableName() {
		return "statistics";
	}
	
	@Override
	protected String createTableSql() {
		return "CREATE TABLE statistics("
				+ "id bigint GENERATED BY DEFAULT AS IDENTITY,"
				+ "kills INT,"
				+ "deaths INT,"
				+ "kdRatio DOUBLE,"
				+ ")";
	}
	
	@Override
	protected String insertSql() {
		return "INSERT INTO statistics(kills,deaths,kdRatio) VALUES(?,?,?)";
	}
	
	@Override
	protected String deleteSql() {
		return "DELETE FROM statistics WHERE id = ?";
	}
	
	@Override
	protected String updateSql() {
		return "UPDATE statistics set kills=?, deaths=?, kdRatio=? WHERE id=?";
	}
	
	@Override
	protected String selectByIdSql() {
		return "SELECT * FROM statistics WHERE id=?";
	}

	@Override
	protected String selectAllSql() {
		return "SELECT * FROM statistics";
	}
	
	@Override
	protected void setupInsert(Statistics entity) throws SQLException {
		insert.setInt(1, entity.getKills());
		insert.setInt(2, entity.getDeaths());
		insert.setDouble(3, entity.getKdRatio());
	}
	
	@Override
	protected void setupUpdate(Statistics entity) throws SQLException {
		update.setInt(1, entity.getKills());
		update.setInt(2, entity.getDeaths());
		update.setDouble(3, entity.getKdRatio());
		update.setInt(4, entity.getId());	
	}
}