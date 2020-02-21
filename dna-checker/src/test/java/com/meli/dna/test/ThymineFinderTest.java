package com.meli.dna.test;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.meli.dna.validator.worker.IFinderWorker;
import com.meli.dna.validator.worker.ThymineFinderWorker;


public class ThymineFinderTest {

	@Test
	public void mutantThymineSequence() throws InterruptedException, ExecutionException {
		String[] dna = {"CCCCGA","TAGTGC","TTGTGC","AGGTGC","AAAATC","ATTTTG"};
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		IFinderWorker thymineWorker = new ThymineFinderWorker(dna);
		Future<Integer> futureT = executor.submit(thymineWorker);
		
		assertTrue("We have found a mutant Erik!",futureT.get() > 0);
	}
	
	@Test
	public void humanThymineSequence() throws InterruptedException, ExecutionException {
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CAAATA","TCGCTG"};
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		IFinderWorker thymineWorker = new ThymineFinderWorker(dna);
		Future<Integer> futureT = executor.submit(thymineWorker);
		
		assertTrue("Keep it your loved human: Charles.",futureT.get() < 2);
	}

}
