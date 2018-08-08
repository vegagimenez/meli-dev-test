package com.meli.dna.service;

import javax.transaction.Transactional;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.dna.dao.DNARepo;
import com.meli.dna.model.DNA;
import com.meli.dna.model.Stats;

@Transactional
@Service
@Log4j2
public class MutantServiceImpl implements MutantService {

	@Autowired
	DNARepo repo;
	
	@Override
	public void saveDNA(DNA dna) {
		log.info("saving dna: " + dna.toString());
		try {
			repo.save(dna);
		} catch (Exception e) {
			log.info("Exception saving dna.");
			e.printStackTrace();
		}
	}

	@Override
	public Stats getStats() {
		return repo.getStats();
	}

}
