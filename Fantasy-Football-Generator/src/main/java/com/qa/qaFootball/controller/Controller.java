package com.qa.qaFootball.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.qaFootball.model.FootballObjects;

@RestController
public class Controller {
	
	private ServiceDB service;

	public Controller(ServiceDB service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/createPlayer")
	public ResponseEntity<String> createPlayer(@RequestBody FootballObjects player){
		service.createPlayer(player);
		ResponseEntity<String> response = new ResponseEntity<String>("Player created with: " + player.getId(), HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/getPlayers")
	public ResponseEntity<ArrayList<FootballObjects>> getPlayers(){
		ArrayList<FootballObjects> response = (ArrayList<FootballObjects>) service.getPlayers();
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateById(@PathVariable("id")long id, @RequestBody FootballObjects player){
		service.update(id, player);
		String response = "Updating player of id: " + id;
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteById(@PathVariable("id") long id) {
		service.remove(id);
		String response = "Player of id: " + id + "has been deleted";
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		
	}
}
