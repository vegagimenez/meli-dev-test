package com.meli.dna.controller;

import java.util.stream.Stream;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meli.dna.model.DNA;
import com.meli.dna.model.Stats;
import com.meli.dna.service.MutantService;
import com.meli.dna.validator.MutantDNAVerificator;

/**
 * Rest controller for the Company Entity
 *
 * @author xcharles - lerik
 */
@RestController
@Log4j2
public class MutantRestController {

	@Autowired
	MutantDNAVerificator verificator;
	@Autowired
	MutantService service;
	
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.OPTIONS }, path = "/mutant")
	public ResponseEntity<?> checkMutantDNA(@RequestBody BodyDNA body) {
		
		log.info("Received request to check dna: " + body);
		DNA dna = new DNA();
		StringBuffer sb = new StringBuffer();
		Stream<String> stream = Stream.of(body.dna);
        stream.forEach(x -> sb.append(x));
		dna.setChain(sb.toString());
		
		boolean isMutant = verificator.isMutant(body.dna);
		dna.setMutant(isMutant);
		saveStats(dna);
		
		if(isMutant){
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
	}
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.OPTIONS }, path = "/stats")
	public ResponseEntity<Stats> getStats() {
		log.info("Received request to check get stats");
		Stats stats = service.getStats();
		stats.ratio = (null == stats.count_human_dna|| 0 == stats.count_human_dna)?null:new Double(stats.count_mutant_dna/(double)stats.count_human_dna);
		return new ResponseEntity<Stats>(stats, HttpStatus.OK);
	}
	
	private void saveStats(DNA dna){
		try {
			service.saveDNA(dna);
		} catch (Exception e) {
			log.error("Error saving data,duplicate row?",e);
		}
	}
}
