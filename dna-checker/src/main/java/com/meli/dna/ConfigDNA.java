package com.meli.dna;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class ConfigDNA implements ICfgDNA {

	@Value(("${hibernate.hbm2ddl.auto}"))
	private String hibernateHbm2ddlAuto;
	
	@Value(("${hibernate.dialect}"))
	private String hibernateDialect;

	@Value(("${hibernate.show_sql}"))
	private String hibernateShowSQL;
	
	@Value(("${spring.datasource.jndi-name}"))
	private String datasourceJNDIName;
	
	/**
	 * This properties are used on local profile
	 * for development purposes.
	 * */
	@Value(("${jdbcDriver}"))
	private String jdbcDriver;
	@Value(("${jdbcUser}"))
	private String jdbcUser;
	@Value(("${jdbcPass}"))
	private String jdbcPass;
	@Value(("${jdbcUrl}"))
	private String jdbcUrl;
}