package com.meli.dev.test.dna.worker;

import org.apache.commons.lang3.StringUtils;


public abstract class FinderWorker implements IFinderWorker{

	protected final String[] chainToCheck;
	protected int dnaCounter = 0;
	private static final int OBLI_MIN_SIZE = 4;
	
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
	public boolean haveNucleotideSequence() {
		return (dnaCounter > 1)?true:false;
	}
	
	@Override
	public int checkHorizontalSequence(){
		int hCounter = 0;
		for (int i = 0; i < chainToCheck.length; i++) {
			String row = chainToCheck[i];
			hCounter =+ StringUtils.countMatches(row, getNucleotideChainToFind());
		}
		return hCounter;
	}
	
	@Override
	public int checkVerticalSequence(){
		int vCounter = 0;
		
		//this only work for nxn matrix, when columns size = rows size
		for (int i = 0; i < chainToCheck.length; i++) {// i = index of columns		
			String col = getMatrixCol(i);
			vCounter =+ StringUtils.countMatches(col, getNucleotideChainToFind());
		}
		return vCounter;
	}
	
	@Override
	public int checkObliqueSequence(){
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
			oCounter =+ StringUtils.countMatches(obl, getNucleotideChainToFind());
			
			//this gets the remainder of the division
			int remainder = chainToCheck.length % OBLI_MIN_SIZE;

			if(remainder > 0){
				
				obl = new StringBuffer();
				//gets remainder elements of uppers oblique.
				for (int i = 0; i < remainder; i++) {
					for (int j = i; j < chainToCheck.length; j++) {
						obl.append(chainToCheck[j].charAt(j+1));
					}
					oCounter =+ StringUtils.countMatches(obl, getNucleotideChainToFind());
				}
				
				obl = new StringBuffer();
				//gets remainder elements of lowers oblique.
				for (int i = 1; i < remainder; i++) {
					for (int j = i; j < chainToCheck.length; j++) {
						obl.append(chainToCheck[j].charAt(--j));
					}
					oCounter =+ StringUtils.countMatches(obl, getNucleotideChainToFind());
				}
			}
		}
		
		return oCounter;
	}
	
	@Override
	public void checkStopWorker(){
		if(dnaCounter > 1){
			Thread.currentThread().interrupt();
	        return;
		}
	}	
}
