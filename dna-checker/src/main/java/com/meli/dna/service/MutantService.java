package com.meli.dna.service;

import com.meli.dna.model.DNA;
import com.meli.dna.model.Stats;

public interface MutantService {

	void saveDNA(DNA dna);
	Stats getStats();
}
