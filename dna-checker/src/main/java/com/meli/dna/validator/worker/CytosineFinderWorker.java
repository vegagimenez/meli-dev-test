package com.meli.dna.validator.worker;



/**
 * Cytosine nucleotide verification Worker
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
public class CytosineFinderWorker extends FinderWorker {

	public static final String C_CHAIN = "CCCC";
	
	public CytosineFinderWorker(String[] dnaChain){
		super(dnaChain); 
	}
	
	@Override
	public String getNucleotideChainToFind() {
		return CytosineFinderWorker.C_CHAIN;
	}
}
