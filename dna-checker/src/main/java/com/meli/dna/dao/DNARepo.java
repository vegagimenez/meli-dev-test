package com.meli.dna.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.meli.dna.model.DNA;

@RepositoryRestResource(exported = false)
public interface DNARepo extends JpaRepository<DNA, Long> {
	
}
