package com.meli.dna.validator.worker;


/**
 * Adenine nucleotide verification Worker
 * this class receives the String[] dnaChain
 * and run every verification:
 * - Horizontal
 * - Vertical
 * - Oblique
 * 
 * Sums up total matches to determine if dnaChain 
 * is or not from a Mutant.
 * 
 * @author xcharles - lerik
 **/

public class AdenineFinderWorker extends FinderWorker {

	public static final String A_CHAIN = "AAAA";
	
	public AdenineFinderWorker(String[] dnaChain){
		super(dnaChain); 
	}
	
	@Override
	public String getNucleotideChainToFind() {
		return AdenineFinderWorker.A_CHAIN;
	}
}
