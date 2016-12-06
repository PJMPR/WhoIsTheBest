package dao;

public interface IRepositoryCatalog {

	public IPlayerRepository player();
	public IStatisticsRepository statistics();
	public ITeamRepository team();
	
	public void saveAndClose();
}
