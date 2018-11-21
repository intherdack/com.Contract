package br.com.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sessao {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String sessaoAtual;
	int idLogin;
	String perfil;
	String data;
	String obs;
	
	
	public int getIdLogin() {
		return idLogin;
	}
	public void setIdLogin(int idLogin) {
		this.idLogin = idLogin;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSessaoAtual() {
		return sessaoAtual;
	}
	public void setSessaoAtual(String sessaoAtual) {
		this.sessaoAtual = sessaoAtual;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	
}
