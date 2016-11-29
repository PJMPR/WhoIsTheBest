package dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Statistics;

public class StatisticsMapper implements IMapResultSetIntoEntity<Statistics>{
	
	public Statistics map(ResultSet rs) throws SQLException {
		Statistics p = new Statistics();
		
		p.setId(rs.getInt("id"));
		p.setKills(rs.getInt("kills"));
		p.setDeaths(rs.getInt("deaths"));
		p.setKdRatio(rs.getDouble("kdRatio"));
		return p;
	}
}
