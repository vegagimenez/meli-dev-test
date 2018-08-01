package com.meli.dev.test.dna.worker;

public interface IFinderWorker extends Runnable{

	public boolean haveNucleotideSequence();
	public int checkHorizontalSequence();
	public int checkVerticalSequence();
	public int checkObliqueSequence();
	public void checkStopWorker();
}
