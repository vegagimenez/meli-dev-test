package com.meli.dna.test;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.meli.dna.validator.worker.GuanineFinderWorker;
import com.meli.dna.validator.worker.IFinderWorker;


public class GuanineFinderTest {

	@Test
	public void mutantGuanineSequence() throws InterruptedException, ExecutionException {
		String[] dna = {"ATGCGA","TAGTGC","TTGTGT","AGGAGG","AAAATA","TCGGGG"};
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		IFinderWorker guanineWorker = new GuanineFinderWorker(dna);
		Future<Integer> futureG = executor.submit(guanineWorker);
		
		assertTrue("We have found a mutant Erik!",futureG.get() > 0);
	}
	
	@Test
	public void humanGuanineSequence() throws InterruptedException, ExecutionException {
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CAAATA","TCGCTG"};
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		IFinderWorker guanineWorker = new GuanineFinderWorker(dna);
		Future<Integer> futureG = executor.submit(guanineWorker);
		
		assertTrue("Keep it your loved human: Charles.",futureG.get() < 2);
	}

}
