package com.meli.dna.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Stats {

	public Long count_mutant_dna;
	public Long count_human_dna;
	public Double ratio;
}
