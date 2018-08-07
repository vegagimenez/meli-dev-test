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
@Table(name = "DNA")
public class DNA implements Serializable {

	private static final long serialVersionUID = 1204405782441626318L;
	@Column(nullable = false, unique = true)
	private String[] chain;
}
