package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;


import edu.ucsb.cs156.spring.backenddemo.services.TidesQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Tag(name="Tides Information from https://api.tidesandcurrents.noaa.gov/api/prod/")
@RestController
@RequestMapping("/api/tides")
public class TidesController {
    
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    TidesQueryService tidesQueryService;

    @Operation(summary = "Get water level for date range, in local time.")
    @GetMapping("/get")
    public ResponseEntity<String> getTides(
        @Parameter(name="startDate", example="beginDate in format yyyymmdd") @RequestParam String startDate,
        @Parameter(name="endDate", example="endDate in format yyyymmdd") @RequestParam String endDate,
        @Parameter(name="station", example="station, e.g. 9411340 for Santa Barbara") @RequestParam String station
    ) throws JsonProcessingException {
        String result = tidesQueryService.getJSON(startDate, endDate, station);
        return ResponseEntity.ok().body(result);
    }

}