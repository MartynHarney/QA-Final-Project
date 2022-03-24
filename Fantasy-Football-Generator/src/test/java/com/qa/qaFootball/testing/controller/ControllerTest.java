package com.qa.qaFootball.testing.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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

@SpringBootTest
//(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = {"classpath:testplayers.sql"}, executionPhase  = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("dev")
public class ControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	FootballObjects a = new FootballObjects("test1", 1, 1, true);
	FootballObjects b = new FootballObjects(1l, "player1", 1, 25, true);
	FootballObjects c = new FootballObjects(2l, "player2", 3, 32, false);
	
	
	
	@Test
	public void testCreate() throws Exception {
		String createJSON = this.mapper.writeValueAsString(a);
		RequestBuilder mockRequest = post("/createPlayer").contentType(MediaType.APPLICATION_JSON).content(createJSON);
		ResultMatcher matchStatus = status().isCreated();
		ResultMatcher matchBody = content().string("Player created with ID: 3");
		mvc.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}
	

	@Test
	public void testRead() throws Exception {
		
		List<FootballObjects> db = List.of(b, c);
		String dbJSON = this.mapper.writeValueAsString(db);
		RequestBuilder  mockReq = get("/getPlayers");
		ResultMatcher status = status().isAccepted();
		ResultMatcher body = content().json(dbJSON);
		mvc.perform(mockReq).andExpect(status).andExpect(body);
	}

	@Test
	public void testUpdate() throws Exception {
		FootballObjects updatedObject  = new FootballObjects("new player", 5, 5, true);
		String updatedJson = mapper.writeValueAsString(updatedObject);
		RequestBuilder req = put("/update/1").contentType(MediaType.APPLICATION_JSON).content(updatedJson);
		ResultMatcher status = status().isOk();
		ResultMatcher body = content().string("Updating player of id: 1");
		mvc.perform(req).andExpect(status).andExpect(body);
	}
	
	@Test
	public void testDelete()throws Exception {
		RequestBuilder req = delete("/delete/1");
		ResultMatcher status = status().isAccepted();
		ResultMatcher body = content().string("Player of id: 1 has been deleted");
		mvc.perform(req).andExpect(status).andExpect(body);
	}
}
