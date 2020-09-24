package com.cognixia.jump.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognixia.jump.model.StartingPlayers;

public interface StartingPlayerRepository extends JpaRepository<StartingPlayers, Integer> {

}
