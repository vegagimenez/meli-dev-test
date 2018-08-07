package com.meli.dna.validator.worker;


/**
 * Guanine nucleotide verification Worker
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
public class GuanineFinderWorker extends FinderWorker {

	public static final String G_CHAIN = "GGGG";
	
	public GuanineFinderWorker(String[] dnaChain){
		super(dnaChain); 
	}
	
	@Override
	public String getNucleotideChainToFind() {
		return GuanineFinderWorker.G_CHAIN;
	}
}