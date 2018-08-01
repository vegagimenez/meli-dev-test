package com.meli.dev.test.dna.worker;


public class ThymineFinderWorker extends FinderWorker {

	public static final String T_CHAIN = "TTTT";
	
	public ThymineFinderWorker(String[] dnaChain){
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
		return ThymineFinderWorker.T_CHAIN;
	}
}