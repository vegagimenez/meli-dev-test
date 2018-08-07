package com.meli.dna.test;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.meli.dna.validator.worker.CytosineFinderWorker;
import com.meli.dna.validator.worker.IFinderWorker;


public class CytosineFinderTest {

	@Test
	public void mutantCytosineSequence() throws InterruptedException, ExecutionException {
		String[] dna = {"CCCCGA","TAGTGC","TTGTGC","AGGAGC","AAAATC","TCGGGG"};
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		IFinderWorker cytosineWorker = new CytosineFinderWorker(dna);
		Future<Integer> futureC = executor.submit(cytosineWorker);
		
		assertTrue("We have found a mutant Erik!",futureC.get() > 0);
	}
	
	@Test
	public void humanCytosineSequence() throws InterruptedException, ExecutionException {
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CAAATA","TCGCTG"};
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		IFinderWorker cytosineWorker = new CytosineFinderWorker(dna);
		Future<Integer> futureC = executor.submit(cytosineWorker);
		
		assertTrue("Keep it your loved human: Charles.",futureC.get() < 2);
	}

}
