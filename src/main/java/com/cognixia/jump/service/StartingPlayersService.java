//package com.cognixia.jump.service;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import com.cognixia.jump.model.StartingPlayers;
//
//@Service
//public class StartingPlayersService {
//	
//	private static List<StartingPlayers>players=new ArrayList<>();
//	
//	static {
//		
//      players.add(new StartingPlayers(1, "Alisson", "Becker", LocalDate.of(1992, 10, 2), "Brazil", 28, 0, 0));
//      players.add(new StartingPlayers(4, "Virgil", "Van Dijk", LocalDate.of(1991, 07, 8), "Holland", 37, 4, 1));
//      players.add(new StartingPlayers(12, "Joe", "Gomez", LocalDate.of(1997, 05, 23), "England", 27, 0, 0));
//      players.add(new StartingPlayers(26, "Andy", "Robertson", LocalDate.of(1994, 03, 11), "Scotland", 35, 2, 11));
//      players.add(new StartingPlayers(66, "Trent", "Arnold", LocalDate.of(1998, 10, 7), "England", 37, 4, 13));
//      players.add(new StartingPlayers(5, "Georginio", "Wijnaldum", LocalDate.of(1990, 11, 11), "Holland", 36, 4, 0));
//      players.add(new StartingPlayers(14, "Jordan", "Henderson", LocalDate.of(1990, 06, 17), "England", 30, 4, 5));
//      players.add(new StartingPlayers(3, "Fabio", "Tavares", LocalDate.of(1993, 10, 23), "Brazil", 28, 2, 3));
//      players.add(new StartingPlayers(9, "Roberto", "Firmino", LocalDate.of(1991, 10, 2), "Brazil", 37, 9, 7));
//      players.add(new StartingPlayers(10, "Sadio", "Mane", LocalDate.of(1992, 04, 10), "Senegal", 34, 17, 7));
//      players.add(new StartingPlayers(11, "Mohamed", "Salah", LocalDate.of(1992, 06, 15), "Egypt", 33, 19, 10));
//	}
//
//	
//	public List<StartingPlayers>getAllPlayers(){
//		return players;
//	}
//	
//	public StartingPlayers getPlayerByJersetNumber(int jerseyNumber) {
//		for (int i = 0; i < players.size(); i++) {
//			if (players.get(i).getJerseyNumber()==jerseyNumber) {
//				return players.get(i);
//				
//			}
//		}
//		return new StartingPlayers();
//	}
//	
//	public List<StartingPlayers> getPlayerByCountry(String country) {
//		List<StartingPlayers>playersCountry=new ArrayList<>();
//		for (int i = 0; i < players.size(); i++) {
//			if (players.get(i).getCountry().equalsIgnoreCase( country.trim())) {
//				playersCountry.add(players.get(i));
//				
//				
//			}
//		}
//		return playersCountry;
//	}
//	
//	public StartingPlayers getPlayerByLastName(String lastname) {
//		for (int i = 0; i < players.size(); i++) {
//			if (players.get(i).getLastname().equalsIgnoreCase(lastname)) {
//				return players.get(i);
//				
//				
//			}
//		}
//		return new StartingPlayers();
//	}
//	public StartingPlayers deletePlayers(int number) {
//		StartingPlayers deletedPlayer= getPlayerByJersetNumber(number);
//		players.remove(deletedPlayer);
//		return deletedPlayer;
//	}
//	
//	public StartingPlayers updatePlayer(StartingPlayers player) {
//		StartingPlayers playerToUpdate=getPlayerByLastName(player.getLastname());
//		playerToUpdate.setJerseyNumber(player.getJerseyNumber());
//		playerToUpdate.setGamesPlayed(player.getGamesPlayed());
//		playerToUpdate.setGoals(player.getGoals());
//		playerToUpdate.setAssists(player.getAssists());
//		
//		return playerToUpdate;
//	}
//	
//	public List<StartingPlayers> addPlayer(StartingPlayers newPlayer) {
//		players.add(newPlayer);
//		return players;
//	}
//}
