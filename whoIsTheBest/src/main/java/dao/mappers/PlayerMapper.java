package dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Player;

public class PlayerMapper implements IMapResultSetIntoEntity<Player>{

	public Player map(ResultSet rs) throws SQLException {
		Player p = new Player();
		
		p.setId(rs.getInt("id"));
		p.setName(rs.getString("name"));
		p.setAge(rs.getInt("age"));
		p.setCountry(rs.getString("country"));
		p.setTeam(rs.getString("team"));
		p.setExperience(rs.getInt("experience"));
		return p;
	}
}
