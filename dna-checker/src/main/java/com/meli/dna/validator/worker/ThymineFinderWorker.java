package com.meli.dna.validator.worker;

/**
 * Thymine nucleotide verification Worker
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
public class ThymineFinderWorker extends FinderWorker {

	public static final String T_CHAIN = "TTTT";
	
	public ThymineFinderWorker(String[] dnaChain){
		super(dnaChain); 
	}
	
	@Override
	public String getNucleotideChainToFind() {
		return ThymineFinderWorker.T_CHAIN;
	}
}