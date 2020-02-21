package com.meli.dna.validator.worker;

import java.security.InvalidParameterException;

import org.apache.commons.lang3.StringUtils;

/**
 * Abstract class to template commons methods
 * in the process of dna verification
 * 
 * @author xcharles - lerik
 **/
public abstract class FinderWorker implements IFinderWorker{

	private final String[] chainToCheck;
	private int nucleotideCounter = 0;
	private static final int OBLI_MIN_SIZE = 4;

	@Override
	public Integer call() throws InvalidParameterException {
		
		if(null != chainToCheck && chainToCheck.length > 0){
			String row = chainToCheck[0];
			if(chainToCheck.length == row.length()){
				nucleotideCounter += this.checkHorizontalSequence();
				this.checkStopWorker();
				nucleotideCounter += this.checkVerticalSequence();
				this.checkStopWorker();
				nucleotideCounter += this.checkObliqueSequence();				
			} else {//NOT NxN matrix
				nucleotideCounter = -1;
			}
		}
		
		return getNucleotideSequenceCounter();
	}
	
	/**
	 * Gets the column string for a certain index.
	 **/
	private String getMatrixCol(int idxRowToReturn){	
		StringBuffer col = new StringBuffer();		
		for (int i = 0; i < chainToCheck.length; i++) {
			col.append(chainToCheck[i].charAt(idxRowToReturn));//appends all row[j]col[i] elements
		}		
		return col.toString();
	}
	
	public FinderWorker(String[] dnaChain){
		this.chainToCheck = dnaChain; 
	}
	
	public abstract String getNucleotideChainToFind();

	@Override
	public int getNucleotideSequenceCounter() {
		return nucleotideCounter;
	}
	
	private int checkHorizontalSequence(){
		int hCounter = 0;
		for (int i = 0; i < chainToCheck.length; i++) {
			String row = chainToCheck[i];
			hCounter += StringUtils.countMatches(row, getNucleotideChainToFind());
		}
		return hCounter;
	}
	
	private int checkVerticalSequence(){
		int vCounter = 0;
		
		//this only work for nxn matrix, when columns size = rows size
		for (int i = 0; i < chainToCheck.length; i++) {// i = index of columns		
			String col = getMatrixCol(i);
			vCounter += StringUtils.countMatches(col, getNucleotideChainToFind());
		}
		return vCounter;
	}
	
	private int checkObliqueSequence(){
		int oCounter = 0;
		//'cause doesn't have any logic to process with minor sizes
		if(chainToCheck.length >= OBLI_MIN_SIZE ){
			/* To resolve all oblique with the minimal 
			 * amount of elements to check, I split the problem in 3:
			 * - Middle
			 * - Upper
			 * - Lower
			 **/
			StringBuffer obl = new StringBuffer();
			// gets elements of middle oblique.
			for (int i = 0; i < chainToCheck.length; i++) {
				obl.append(chainToCheck[i].charAt(i));
			}
			oCounter += StringUtils.countMatches(obl, getNucleotideChainToFind());
			
			//this gets the remainder of the division
			int remainder = chainToCheck.length % OBLI_MIN_SIZE;

			if(remainder > 0){
				int initLength = chainToCheck.length -1;
				obl = new StringBuffer();
				//gets remainder elements of upper oblique.
				for (int i = 0; i < remainder; i++) {
					int oblLength =  initLength - i;
					for (int j = 0; j < oblLength; j++) {
						obl.append(chainToCheck[j].charAt(j+i+1));
					}
					oCounter += StringUtils.countMatches(obl, getNucleotideChainToFind());
					obl = new StringBuffer();
				}
				
				obl = new StringBuffer();
				int oblLength =  initLength;
				//gets remainder elements of lower oblique.
				for (int i = 0; i < remainder; i++) {
					for (int j = i+1; j < oblLength+1; j++) {
						obl.append(chainToCheck[j].charAt(j-i-1));
					}
					oCounter += StringUtils.countMatches(obl, getNucleotideChainToFind());
					obl = new StringBuffer();
					oblLength =  initLength - i;
				}
			}
		}
		
		return oCounter;
	}

	/**
	 * Because if already found more than 1 sequence of this nucleotide
	 * I don't have to continue my search for this one.
	 ***/
	@Override
	public void checkStopWorker(){
		if(nucleotideCounter > 1){
			Thread.currentThread().interrupt();
	        return;
		}
	}	
}
