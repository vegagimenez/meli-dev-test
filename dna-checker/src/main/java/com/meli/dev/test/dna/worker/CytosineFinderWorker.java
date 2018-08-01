package com.meli.dev.test.dna.worker;


public class CytosineFinderWorker extends FinderWorker {

	public static final String C_CHAIN = "CCCC";
	
	public CytosineFinderWorker(String[] dnaChain){
		super(dnaChain); 
	}
	
	@Override
	public void run() {
		if(null != chainToCheck && chainToCheck.length > 0){
			dnaCounter =+ this.checkHorizontalSequence();
			this.checkStopWorker();
			dnaCounter =+ this.checkVerticalSequence();
			this.checkStopWorker();
			dnaCounter =+ this.checkObliqueSequence();
		}
	}

	@Override
	public String getNucleotideChainToFind() {
		return CytosineFinderWorker.C_CHAIN;
	}
}
