package model;

public class Statistics {
	private int id;
	private int kills;
	private int deaths;
	private double kdRatio;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getKills() {
		return kills;
	}
	public void setKills(int kills) {
		this.kills = kills;
	}
	public int getDeaths() {
		return deaths;
	}
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	public double getKdRatio() {
		kdRatio = (double)kills/(double)deaths;
		return kdRatio;
	}
	public void setKdRatio(double kdRatio) {
		this.kdRatio = kdRatio;
	}
	
	
}
