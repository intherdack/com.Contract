package br.com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Negociacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int codigoEmpregador;
	private int codigoProfissional;
	private int codigoServico;
	private String fechadoProfissional;
	private String status;
	private String situacao;
	private int avaliacaoE;
	private int avaliacaoP;
	private String DescE;
	private String DescP;
	private String aprovadoProfissional;
	private String aprovadoempregador;
	

	//=========================================== Geter and Seters ==============================================
	
	
	
	public int getId() {
		return id;
	}
	public int getAvaliacaoP() {
		return avaliacaoP;
	}
	public void setAvaliacaoP(int avaliacaoP) {
		this.avaliacaoP = avaliacaoP;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCodigoEmpregador() {
		return codigoEmpregador;
	}
	public void setCodigoEmpregador(int codigoEmpregador) {
		this.codigoEmpregador = codigoEmpregador;
	}
	public int getCodigoProfissional() {
		return codigoProfissional;
	}
	public void setCodigoProfissional(int codigoProfissional) {
		this.codigoProfissional = codigoProfissional;
	}
	public int getCodigoServico() {
		return codigoServico;
	}
	public void setCodigoServico(int codigoServico) {
		this.codigoServico = codigoServico;
	}
	public String getFechadoProfissional() {
		return fechadoProfissional;
	}
	public void setFechadoProfissional(String fechadoProfissional) {
		this.fechadoProfissional = fechadoProfissional;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public int getAvaliacaoE() {
		return avaliacaoE;
	}
	public void setAvaliacaoE(int avaliacaoE) {
		this.avaliacaoE = avaliacaoE;
	}
	public String getDescE() {
		return DescE;
	}
	public void setDescE(String descE) {
		DescE = descE;
	}
	public String getDescP() {
		return DescP;
	}
	public void setDescP(String descP) {
		DescP = descP;
	}
	public String getAprovadoProfissional() {
		return aprovadoProfissional;
	}
	public void setAprovadoProfissional(String aprovadoProfissional) {
		this.aprovadoProfissional = aprovadoProfissional;
	}
	public String getAprovadoempregador() {
		return aprovadoempregador;
	}
	public void setAprovadoempregador(String aprovadoempregador) {
		this.aprovadoempregador = aprovadoempregador;
	}
	
}
