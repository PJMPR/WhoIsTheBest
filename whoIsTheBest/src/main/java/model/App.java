package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import model.Player;
import model.Statistics;
import model.Team;

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

			catalog.player().add(shanger);
			
			List<Player> player = catalog.player().byTeam("fragnation");
			
			System.out.println( "Zapisuje Shangera" );
			
			Player lockjaw = new Player();
			lockjaw.setName("Lockjaw");
			lockjaw.setAge(24);
			lockjaw.setCountry("Poland");
			lockjaw.setTeam("FragNation");
			lockjaw.setExperience(3);
			lockjaw.setPlayerStatistics(1);
			lockjaw.setTeamId(0);

			catalog.player().add(lockjaw);
			
			List<Player> player1 = catalog.player().byTeam("fragnation");
			
	        System.out.println( "Zapisuje Lockjawa" );

			
			Statistics stats = new Statistics();
			stats.setKills(21);
			stats.setDeaths(10);
			stats.getKdRatio();

			catalog.statistics().add(stats);
			
			List<Statistics> statistics = catalog.statistics().byId(0);
			
	        System.out.println( "Zapisuje statsy" );

			
			Statistics stats1 = new Statistics();
			stats1.setKills(50);
			stats1.setDeaths(10);
			stats1.getKdRatio();

			catalog.statistics().add(stats1);
			
			List<Statistics> statistics1 = catalog.statistics().byId(1);
			
	        System.out.println( "Zapisuje statsy1" );
			

			Team frag = new Team();
			frag.setName("FragNation");
			frag.setCountry("Poland");

			catalog.team().add(frag);
			
			List<Team> team = catalog.team().withName("fragnation");
			
	        System.out.println( "Zapisuje team" );


			 catalog.saveAndClose();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
        System.out.println( "Koniec" );
        
    }
}
