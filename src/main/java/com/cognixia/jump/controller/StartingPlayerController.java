package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.StartingPlayers;
import com.cognixia.jump.repository.StartingPlayerRepository;



@RequestMapping("/team")
@RestController
public class StartingPlayerController {
	
	@Autowired
	StartingPlayerRepository repository;
	
	@GetMapping("/firstteam")
	public List<StartingPlayers> getAllPlayer(){
		return repository.findAll();
	}
	
	@GetMapping("/{number}")
	public StartingPlayers getPlayer(@PathVariable String number) {
		int jerseyNumber=Integer.parseInt(number);
		Optional<StartingPlayers>playerOptional= repository.findById(jerseyNumber);
        if (playerOptional.isPresent()) {
        	return playerOptional.get();
			
		}
        return new StartingPlayers();
	}
	
//	@GetMapping("/firstteam/{lastname}")
//	public StartingPlayers getPlayerByLastName(@PathVariable String lastname) {
//		return repository.(lastname);
//	}
//	
//	@GetMapping("/firstteam/country/{country}") 
//	public List<StartingPlayers> getPlayersCountry(@PathVariable String country){
//		return service.getPlayerByCountry(country);
//	}
	
	@DeleteMapping("/deletePlayer/{number}")
	public ResponseEntity<String> deletePlayer(@PathVariable int number) {
		Optional<StartingPlayers>deletedOptional=repository.findById(number);
		if (deletedOptional.isPresent()) {
			repository.deleteById(number);
			return ResponseEntity.status(200).body("The player is out of the roster");
		}
		return ResponseEntity.status(404).body("The player don't want to leave!");
	}
	
	@PutMapping("/updatePlayer")
	public @ResponseBody String updatePlayer(@RequestBody StartingPlayers player) {
		 Optional<StartingPlayers>updatedPlayer=repository.findById(player.getJerseyNumber());
		 if (updatedPlayer.isPresent()) {
			 repository.save(player);
			 return "The player is updated" + player.toString();
			
		}
		 
		 return "The player " +player.getLastname()+ " doesn't like to change";
	}
	
	@PostMapping("/addPlayer")
	public List<StartingPlayers> addPlayer(@RequestBody StartingPlayers newPlayer) {
        repository.save(newPlayer);
        return repository.findAll();
	}

}
