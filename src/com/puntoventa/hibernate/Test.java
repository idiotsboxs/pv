package com.puntoventa.hibernate;

// Generated 10/09/2015 10:56:49 PM by Hibernate Tools 3.4.0.CR1

/**
 * Test generated by hbm2java
 */
public class Test implements java.io.Serializable {

	private int idTest;
	private String des;
	private Integer idPrueba;

	public Test() {
	}

	public Test(int idTest) {
		this.idTest = idTest;
	}

	public Test(int idTest, String des, Integer idPrueba) {
		this.idTest = idTest;
		this.des = des;
		this.idPrueba = idPrueba;
	}

	public int getIdTest() {
		return this.idTest;
	}

	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}

	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Integer getIdPrueba() {
		return this.idPrueba;
	}

	public void setIdPrueba(Integer idPrueba) {
		this.idPrueba = idPrueba;
	}

}