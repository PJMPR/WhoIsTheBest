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

public class App 
{
    public static void main( String[] args )
    {
    	String url = "jdbc:hsqldb:hsql://localhost/workdb";
    	try {
			Connection connection = DriverManager.getConnection(url);
			
    		IMapResultSetIntoEntity<Player> playerMapper = new PlayerMapper();
			IMapResultSetIntoEntity<Statistics> statisticsMapper = new StatisticsMapper();
			IMapResultSetIntoEntity<Team> teamMapper = new TeamMapper();
			
			PlayerRepository playerRepo = new PlayerRepository(connection, playerMapper);
			StatisticsRepository statisticsRepo = new StatisticsRepository(connection, statisticsMapper);
			TeamRepository teamRepo = new TeamRepository(connection, teamMapper);
			
			Player shanger = new Player();
			shanger.setName("Shanger");
			shanger.setAge(20);
			shanger.setCountry("Poland");
			shanger.setTeam("FragNation");
			shanger.setExperience(5);

	        System.out.println( "Zapisuje Shangera" );
			playerRepo.add(shanger);
			
			
			Statistics stats = new Statistics();
			stats.setKills(21);
			stats.setDeaths(10);
			stats.setKdRatio(2.10); ////////////////////chce automatyczne wyliczenie kd z metody wlozyc do tabeli

	        System.out.println( "Zapisuje statsy" );
			statisticsRepo.add(stats);
			
			
			Team frag = new Team();
			frag.setName("FragNation");
			frag.setCountry("Poland");
			////////////////////////////////////////////id graczy do tabeli

	        System.out.println( "Zapisuje team" );
			teamRepo.add(frag);

		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
        System.out.println( "Koniec" );
        
    }
}
