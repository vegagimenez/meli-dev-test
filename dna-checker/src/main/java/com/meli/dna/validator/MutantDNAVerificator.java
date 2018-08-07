package com.meli.dna.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Component;

import com.meli.dna.validator.worker.AdenineFinderWorker;
import com.meli.dna.validator.worker.CytosineFinderWorker;
import com.meli.dna.validator.worker.GuanineFinderWorker;
import com.meli.dna.validator.worker.IFinderWorker;
import com.meli.dna.validator.worker.ThymineFinderWorker;

/**
 * Class to start verification process of 
 * mutant conditions in dna chain.
 * 
 * @author xcharles - lerik
 **/
@Component
public class MutantDNAVerificator {
	
	public boolean isMutant(String[] dnaChain){
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
		
		IFinderWorker adenineWorker = new AdenineFinderWorker(dnaChain);
		Future<Integer> futureA = executor.submit(adenineWorker);
		futures.add(futureA);
		
		IFinderWorker guanineWorker = new GuanineFinderWorker(dnaChain);
		Future<Integer> futureG = executor.submit(guanineWorker);
		futures.add(futureG);
		
		IFinderWorker cytosineWorker = new CytosineFinderWorker(dnaChain);
		Future<Integer> futureC = executor.submit(cytosineWorker);
		futures.add(futureC);
		
		IFinderWorker thymineWorker = new ThymineFinderWorker(dnaChain);
		Future<Integer> futureT = executor.submit(thymineWorker);
		futures.add(futureT);
		
		int result=0;
		for(Future<Integer> future:futures){
            try {
            	result += future.get();
            } catch (Exception e) {                                               
                e.printStackTrace();
            } 
        }   
        
		return (result > 1)?Boolean.TRUE:Boolean.FALSE;
	}
}
