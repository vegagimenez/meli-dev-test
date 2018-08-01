package com.meli.dev.test.dna.worker;


public class AdenineFinderWorker extends FinderWorker {

	public static final String A_CHAIN = "AAAA";
	
	public AdenineFinderWorker(String[] dnaChain){
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
		return AdenineFinderWorker.A_CHAIN;
	}
}
