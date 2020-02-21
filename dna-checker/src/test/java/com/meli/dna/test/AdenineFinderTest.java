package com.meli.dna.test;
import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.meli.dna.validator.worker.AdenineFinderWorker;
import com.meli.dna.validator.worker.IFinderWorker;


public class AdenineFinderTest {

	@Test
	public void mutantAdenineSequence() throws InterruptedException, ExecutionException {
		String[] dna = {"ATGCGA","TAGTGC","TTATGT","AGAAGG","AAAATA","TCACTG"};
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		IFinderWorker adenineWorker = new AdenineFinderWorker(dna);
		Future<Integer> futureA = executor.submit(adenineWorker);
		
		assertTrue("We have found a mutant Erik!",futureA.get() > 0);
	}
	
	@Test
	public void humanAdenineSequence() throws InterruptedException, ExecutionException {
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CAAATA","TCGCTG"};
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		IFinderWorker adenineWorker = new AdenineFinderWorker(dna);
		Future<Integer> futureA = executor.submit(adenineWorker);
		
		assertTrue("Keep it your loved human: Charles.",futureA.get() < 2);
	}

}
