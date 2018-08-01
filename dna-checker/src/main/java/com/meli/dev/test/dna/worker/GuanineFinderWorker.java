package com.meli.dev.test.dna.worker;


public class GuanineFinderWorker extends FinderWorker {

	public static final String G_CHAIN = "GGGG";
	
	public GuanineFinderWorker(String[] dnaChain){
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
		return GuanineFinderWorker.G_CHAIN;
	}
}