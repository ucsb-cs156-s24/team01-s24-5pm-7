package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ucsb.cs156.spring.backenddemo.services.CollegeSubredditQueryService;
import edu.ucsb.cs156.spring.backenddemo.services.JokeQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name="Jokes from https://v2.jokeapi.dev/ ")
@Slf4j
@RestController
@RequestMapping("/api/jokes")
public class JokeController{

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    JokeQueryService jokeQueryService;

    @Operation(summary = "Get jokes for a given category and amount")
    @GetMapping("/get")
    public ResponseEntity<String> getJoke(
        @Parameter(name="category", description="category of joke", example="Programming") @RequestParam String category,
        @Parameter(name="numJokes", description="amount of jokes to get", example="1") @RequestParam String numJokes
    ) throws JsonProcessingException {
        //not change
        log.info("getJoke: category={} amount={}", category, numJokes);
        String result = jokeQueryService.getJSON(category, numJokes);
        return ResponseEntity.ok().body(result);
    }

}