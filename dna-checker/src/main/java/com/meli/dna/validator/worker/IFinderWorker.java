package com.meli.dna.validator.worker;

import java.util.concurrent.Callable;

public interface IFinderWorker extends Callable<Integer> {

	public int getNucleotideSequenceCounter();
	public void checkStopWorker();
}
