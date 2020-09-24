package com.cognixia.jump.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StartingPlayers implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1359287149306044149L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	
	private Integer id;
	@Column(unique = true,nullable = false)
	private Integer jerseyNumber;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String lastname;
	@Column(nullable = false)
	private LocalDate dob;
	@Column(nullable = false)
	private String country;
	@Column
	private int gamesPlayed;
	@Column
	private int goals;
	@Column
	private int assists;
	@Column(name = "image_path")
	private String imagePath;
	
	public StartingPlayers() {
		this(-1,-1,"N/A","N/A",LocalDate.now(),"N/A",-1,-1,-1,"N/A");
	}



	public StartingPlayers(Integer id, Integer jerseyNumber, String name, String lastname, LocalDate dob,
			String country, int gamesPlayed, int goals, int assists, String imagePath) {
		super();
		this.id = id;
		this.jerseyNumber = jerseyNumber;
		this.name = name;
		this.lastname = lastname;
		this.dob = dob;
		this.country = country;
		this.gamesPlayed = gamesPlayed;
		this.goals = goals;
		this.assists = assists;
		this.imagePath = imagePath;
	}



	public Integer getJerseyNumber() {
		return jerseyNumber;
	}

	public void setJerseyNumber(Integer jerseyNumber) {
		this.jerseyNumber = jerseyNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getImagePath() {
		return imagePath;
	}



	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}



	@Override
	public String toString() {
		return "StartingPlayers [id=" + id + ", jerseyNumber=" + jerseyNumber + ", name=" + name + ", lastname="
				+ lastname + ", dob=" + dob + ", country=" + country + ", gamesPlayed=" + gamesPlayed + ", goals="
				+ goals + ", assists=" + assists + ", imagePath=" + imagePath + "]";
	}
	
	

	

	
	
	

}
