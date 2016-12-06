package dao;

import java.util.List;

import model.Player;

public interface IPlayerRepository extends IRepository<Player> {
	public List<Player> withName(String name);
}
