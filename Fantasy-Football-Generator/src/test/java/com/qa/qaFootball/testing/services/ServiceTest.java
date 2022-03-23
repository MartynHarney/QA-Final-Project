package com.qa.qaFootball.testing.services;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.qaFootball.model.FootballObjects;
import com.qa.qaFootball.repo.Repo;
import com.qa.qaFootball.services.ServicesDB;

@SpringBootTest
public class ServiceTest {
	
	@MockBean
	private Repo repo;
	
	@Autowired
	private ServicesDB service;
	
	FootballObjects player1 = new FootballObjects("Ronaldo", 10, 37, true);
	FootballObjects player2 = new FootballObjects("Fred", 3, 29, false);
	
	FootballObjects player1Id = new FootballObjects(1l, "Ronaldo", 10, 37, true);
	FootballObjects player2Id = new FootballObjects(2l, "Fred", 3, 29, false);

	@Test
	public void testCreate() {
		Mockito.when(repo.save(player1)).thenReturn(player1Id);
		FootballObjects result = service.createPlayer(player1);
		Assertions.assertEquals(player1Id, result);
		Mockito.verify(repo, Mockito.times(1)).save(player1);
	}
	
	@Test
	public void testDelete() {
		repo.deleteById(1l);
		boolean result = service.remove(1l);
		Assertions.assertTrue(result);
	}
	
	@Test
	public void testUpdate() {
		FootballObjects newPlayer = new FootballObjects("Salah", 9, 31, true);
		Mockito.when(repo.findById(1l)).thenReturn(Optional.of(player1Id));
		Mockito.when(repo.save(player1Id)).thenReturn(newPlayer);
		boolean result = service.update(1l, newPlayer);
		Assertions.assertTrue(result);
	}
	
}
