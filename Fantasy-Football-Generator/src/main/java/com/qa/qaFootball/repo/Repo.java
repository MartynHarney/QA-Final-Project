package com.qa.qaFootball.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.qaFootball.model.FootballObjects;

public interface Repo extends JpaRepository<FootballObjects, Long> {
	
	

}
