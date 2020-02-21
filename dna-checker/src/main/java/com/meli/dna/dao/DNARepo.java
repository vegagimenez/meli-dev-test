package com.meli.dna.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.meli.dna.model.DNA;
import com.meli.dna.model.Stats;

@RepositoryRestResource(exported = false)
public interface DNARepo extends JpaRepository<DNA, Long> {
	
	@Query(value = "SELECT new com.meli.dna.model.Stats("
			+" SUM(CASE WHEN isMutant=1 THEN 1 ELSE 0 END) as count_mutant_dna,"
			+" SUM(CASE WHEN isMutant=0 THEN 1 ELSE 0 END) as count_human_dna,"
			+" 0D as ratio)"
			+" FROM DNA",
			nativeQuery = false)
	Stats getStats();
}
