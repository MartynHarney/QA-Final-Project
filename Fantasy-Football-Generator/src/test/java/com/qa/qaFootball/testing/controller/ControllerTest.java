package com.qa.qaFootball.testing.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.qaFootball.model.FootballObjects;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"classpath:testplayers.sql"}, executionPhase  = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("dev")
public class ControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	FootballObjects testPlayer = new FootballObjects("player2", 3, 32, false);
	
	@Test
	public void testCreate() throws Exception {
		String playerJson = mapper.writeValueAsString(testPlayer);
		RequestBuilder req = post("/createPlayer").contentType(MediaType.APPLICATION_JSON).content(playerJson);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().string("Player created with ID: 2");
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	public void testGetId() throws Exception {
		FootballObjects testPlayerId = testPlayer;
		testPlayerId.setId(2l);
		String testPlayerIdJson = mapper.writeValueAsString(testPlayerId);
		RequestBuilder req = get("/getId/2");
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testPlayerIdJson);
		mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	

}
