package dao;

import java.sql.Connection;
import java.sql.SQLException;

import dao.mappers.PlayerMapper;
import dao.mappers.StatisticsMapper;
import dao.mappers.TeamMapper;
import dao.uow.IUnitOfWork;


public class RepositoryCatalog implements IRepositoryCatalog{
	
	IUnitOfWork uow;
	Connection connection;
	
	
	public RepositoryCatalog(IUnitOfWork uow, Connection connection) {
		super();
		this.uow = uow;
		this.connection = connection;
	}

	
	public IPlayerRepository player() {
		return new PlayerRepository(connection, new PlayerMapper(), uow);
	}

	public IStatisticsRepository statistics() {
		return new StatisticsRepository(connection, new StatisticsMapper(), uow);
	}

	public ITeamRepository team() {
		return new TeamRepository(connection, new TeamMapper(), uow);
	}

	public void saveAndClose() {
		try{
		uow.commit();
		connection.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
	}
}
