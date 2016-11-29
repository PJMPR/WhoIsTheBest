package model;

public class Team  implements IHaveId{
	private int id;
	private String name;
	private String country;
	private Player players;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Player getPlayers() {
		return players;
	}
	public void setPlayers(Player players) {
		this.players = players;
	}
	
	
}
