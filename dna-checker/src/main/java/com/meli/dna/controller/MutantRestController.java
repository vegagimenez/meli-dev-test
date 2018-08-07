package com.meli.dna.controller;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meli.dna.model.DNA;
import com.meli.dna.service.MutantService;
import com.meli.dna.validator.MutantDNAVerificator;

/**
 * Rest controller for the Company Entity
 *
 * @author xcharles - lerik
 */
@RestController
@RequestMapping("/mutant")
@Log4j2
public class MutantRestController {

	@Autowired
	MutantDNAVerificator verificator;
	@Autowired
	MutantService service;
	
	/**
	 * Creates a company
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.OPTIONS })
	public ResponseEntity<?> checkMutantDNA(@RequestBody BodyDNA body) {
		log.info("Received request to check dna: " + body);
		if(verificator.isMutant(body.dna)){
			DNA dna = new DNA();
			dna.setChain(body.dna);
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
	}
}
