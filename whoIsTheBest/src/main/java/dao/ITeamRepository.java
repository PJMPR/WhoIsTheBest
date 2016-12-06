package dao;

import java.util.List;

import model.Team;

public interface ITeamRepository extends IRepository<Team>{
	public List<Team> withName(String name);
}
