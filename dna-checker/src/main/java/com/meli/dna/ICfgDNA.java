package com.meli.dna;

public interface ICfgDNA {

	public String getHibernateHbm2ddlAuto();
	public void setHibernateHbm2ddlAuto(String hibernateHbm2ddlAuto);
	public String getHibernateDialect();
	public void setHibernateDialect(String hibernateDialect);
	public String getHibernateShowSQL();
	public void setHibernateShowSQL(String hibernateShowSQL);
	public String getDatasourceJNDIName();
	public void setDatasourceJNDIName(String datasourceJNDIName);
	public String getJdbcDriver();
	public void setJdbcDriver(String jdbcDriver);
	public String getJdbcUser();
	public void setJdbcUser(String jdbcUser);
	public String getJdbcPass();
	public void setJdbcPass(String jdbcPass);
	public String getJdbcUrl();
	public void setJdbcUrl(String jdbcUrl);
}