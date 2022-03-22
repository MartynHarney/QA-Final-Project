package com.qa.qaFootball.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.qaFootball.model.FootballObjects;

@Service
public class ServicesDB {

	private Repo repo;

	public ServicesDB(Repo repo) {
		super();
		this.repo = repo;
	}
	
	public FootballObjects createPlayer(FootballObjects player) {
		FootballObject savedObject = repo.save(player);
		return savedObject;
	}
	
	public List<FootballObjects> getPlayers(){
		return repo.findAll();
	}
	
	public boolean update(long id, FootballObjects player) {
		FootballObjects oldPlayer = getById(id);
		
		oldPlayer.setName(player.getName());
		oldPlayer.setPrice(player.getPrice());
		oldPlayer.setAge(player.getAge());
		oldPlayer.setStarPlayer(player.isStarPlayer());
		
		FootballObjects updatedPlayer = oldPlayer;
		repo.save(updatedPlayer);
		return true;
	}
	
	public boolean remove(long id) {
		repo.deleteById(id);
		return true;
	}
}
