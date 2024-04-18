package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ucsb.cs156.spring.backenddemo.services.CollegeSubredditQueryService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
public class JokeController {
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    CollegeSubredditQueryService collegeSubredditQueryService;

    @Operation(summary = "Get a list of college subreddits")
    @GetMapping("/get")
    public ResponseEntity<String> getSubreddits() throws JsonProcessingException {
        String result = collegeSubredditQueryService.getJSON();
        return ResponseEntity.ok().body(result);
    }
}
