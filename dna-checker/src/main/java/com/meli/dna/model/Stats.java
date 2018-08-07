package com.meli.dna.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "Stats")
public class Stats implements Serializable {

	private static final long serialVersionUID = 1204408582441626318L;
	
	@Column(nullable = false)
	public int count_mutant_dna;
	@Column(nullable = false)
	public int count_human_dna;
	@Column(nullable = false)
	public int ratio;
}
