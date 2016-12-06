package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.PlayerRepository;
import dao.StatisticsRepository;
import dao.TeamRepository;

import model.Player;
import model.Statistics;
import model.Team;

import dao.mappers.PlayerMapper;
import dao.mappers.StatisticsMapper;
import dao.mappers.TeamMapper;
import dao.mappers.IMapResultSetIntoEntity;

import dao.IRepositoryCatalog;
import dao.RepositoryCatalog;
import dao.uow.UnitOfWork;

public class App 
{
    public static void main( String[] args )
    {
    	String url = "jdbc:hsqldb:hsql://localhost/workdb";
    	try {
			Connection connection = DriverManager.getConnection(url);
			
			IRepositoryCatalog catalog = new RepositoryCatalog(new UnitOfWork(connection), connection);
			
			Player shanger = new Player();
			shanger.setName("Shanger");
			shanger.setAge(20);
			shanger.setCountry("Poland");
			shanger.setTeam("FragNation");
			shanger.setExperience(5);
			shanger.setPlayerStatistics(0);
			shanger.setTeamId(0);

	        System.out.println( "Zapisuje Shangera" );
			catalog.player().add(shanger);
			
			Player lockjaw = new Player();
			lockjaw.setName("Lockjaw");
			lockjaw.setAge(24);
			lockjaw.setCountry("Poland");
			lockjaw.setTeam("FragNation");
			lockjaw.setExperience(3);
			lockjaw.setPlayerStatistics(1);
			lockjaw.setTeamId(0);

	        System.out.println( "Zapisuje Lockjawa" );
			catalog.player().add(lockjaw);
			
			Statistics stats = new Statistics();
			stats.setKills(21);
			stats.setDeaths(10);
			stats.getKdRatio();

	        System.out.println( "Zapisuje statsy" );
			catalog.statistics().add(stats);
			
			Statistics stats1 = new Statistics();
			stats1.setKills(50);
			stats1.setDeaths(10);
			stats1.getKdRatio();

	        System.out.println( "Zapisuje statsy1" );
			catalog.statistics().add(stats1);
			
			
			//Team frag = new Team();
			//frag.setName("FragNation");
			//frag.setCountry("Poland");
			////////////////////////////////////////////id graczy do tabeli

	        //System.out.println( "Zapisuje team" );
			//teamRepo.add(frag);

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
        System.out.println( "Koniec" );
        
    }
}
