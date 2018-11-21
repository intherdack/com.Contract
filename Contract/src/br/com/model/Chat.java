package br.com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Chat {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idChat;
	private int idOriginadorProfissional;
	private int idOriginadorEmpregador;
	private String enviado;
	private String mensagem;
	private String time;
	private String obs;
	
	
	
	public int getIdChat() {
		return idChat;
	}

	public int getIdOriginadorProfissional() {
		return idOriginadorProfissional;
	}

	public void setIdOriginadorProfissional(int idOriginadorProfissional) {
		this.idOriginadorProfissional = idOriginadorProfissional;
	}

	public int getIdOriginadorEmpregador() {
		return idOriginadorEmpregador;
	}

	public void setIdOriginadorEmpregador(int idOriginadorEmpregador) {
		this.idOriginadorEmpregador = idOriginadorEmpregador;
	}

	

	public void setIdChat(int idChat) {
		this.idChat = idChat;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	

	public String getEnviado() {
		return enviado;
	}

	public void setEnviado(String enviado) {
		this.enviado = enviado;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	
}
