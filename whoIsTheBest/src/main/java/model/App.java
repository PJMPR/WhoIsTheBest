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

public class App 
{
    public static void main( String[] args )
    {
    	
    	String url = "jdbc:hsqldb:hsql://localhost/workdb";
    	try {
			Connection connection = DriverManager.getConnection(url);
			PlayerRepository playerRepo = new PlayerRepository(connection);
			StatisticsRepository statisticsRepo = new StatisticsRepository(connection);
			TeamRepository teamRepo = new TeamRepository(connection);
			
			Player shanger = new Player();
			shanger.setName("Shanger");
			shanger.setAge(20);
			shanger.setCountry("Poland");
			shanger.setTeam("FragNation");
			shanger.setExperience(5);

	        System.out.println( "Zapisuje Shangera" );
			playerRepo.add(shanger);
			
			
			Statistics stats = new Statistics();
			stats.setKills(20);
			stats.setDeaths(10);
			stats.setKdRatio(2.00);

	        System.out.println( "Zapisuje statsy" );
			statisticsRepo.add(stats);
			
			
			Team frag = new Team();
			frag.setName("FragNation");
			frag.setCountry("Poland");


	        System.out.println( "Zapisuje team" );
			teamRepo.add(frag);

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
        System.out.println( "Koniec" );
        
    }
}
