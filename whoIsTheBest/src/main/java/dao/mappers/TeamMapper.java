package dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Team;

public class TeamMapper implements IMapResultSetIntoEntity<Team> {
	
	public Team map(ResultSet rs) throws SQLException {
		Team p = new Team();
		
		p.setId(rs.getInt("id"));
		p.setName(rs.getString("name"));
		p.setCountry(rs.getString("country"));
		return p;
	}
}
