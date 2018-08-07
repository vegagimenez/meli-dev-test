package com.meli.dna.test;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.meli.dna.validator.MutantDNAVerificator;
import com.meli.dna.validator.worker.AdenineFinderWorker;
import com.meli.dna.validator.worker.IFinderWorker;

public class MutantDNAVerificatorTest {

	/**
	 * Test with horizontal nucleotides
	 ***/
	@Test
	public void mutant1() {
		String[] dna = {"ATGCGA","TAGTGC","TCCCCT","AGAAGG","AAAATA","TCACTG"};
		MutantDNAVerificator dnaVerificator = new MutantDNAVerificator();
		
		assertTrue("Mutant with horizontal nucleotides.", dnaVerificator.isMutant(dna));
	}
	
	/**
	 * Test with vertical nucleotides
	 ***/
	@Test
	public void mutant2() {
		String[] dna = {"ATGCGA","AAGGTC","ACCCTC","AGAATA","ATAATA","TCACTG"};
		MutantDNAVerificator dnaVerificator = new MutantDNAVerificator();
		
		assertTrue("Mutant with vertical nucleotides.", dnaVerificator.isMutant(dna));
	}
	
	/**
	 * Test with oblique nucleotides
	 ***/
	@Test
	public void mutant3() {
		String[] dna = {"ATGCGA","CATGTC","ACATTC","AGAATA","ATCATA","TCACCG"};
		MutantDNAVerificator dnaVerificator = new MutantDNAVerificator();
		
		assertTrue("Mutant with oblique nucleotides.", dnaVerificator.isMutant(dna));
	}
	
	/**
	 * Test with human nucleotides
	 ***/
	@Test
	public void human() {
		String[] dna = {"AAGCGA","CATGTC","ACAGTC","AGATTA","ATCATA","TCACCG"};
		MutantDNAVerificator dnaVerificator = new MutantDNAVerificator();
		
		assertFalse("Human DNA.", dnaVerificator.isMutant(dna));
	}

	@Test
	public void notNxNChain() throws InterruptedException, ExecutionException {
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CAAATA"};
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		IFinderWorker adenineWorker = new AdenineFinderWorker(dna);
		Future<Integer> futureA = executor.submit(adenineWorker);
		
		assertTrue("Only NxN matrix would be process.",futureA.get() < 0);
	}

}
