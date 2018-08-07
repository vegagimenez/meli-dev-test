package com.meli.dna.service;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meli.dna.validator.MutantDNAVerificator;

/**
 * Rest controller for the Company Entity
 *
 * @author xcharles - lerik
 */
@RestController
@RequestMapping("/mutant")
@Log4j2
public class MutantRestService {

	@Autowired
	MutantDNAVerificator verificator;
	/**
	 * Creates a company
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.OPTIONS })
	public ResponseEntity<?> checkMutantDNA(@RequestBody BodyDNA body) {
		log.info("Received request to check dna: " + body);
		if(verificator.isMutant(body.dna)){
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
	}
}
