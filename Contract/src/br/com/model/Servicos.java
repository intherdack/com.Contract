package br.com.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Servicos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	private String status;
	private int codigoEmp;
	private String nomeTrabalho;
	private String cep;
	private String cidade;
	private String uf;
	private String valor;
	private String descricao;
	private String tipo;
	private String selecionado;
	/*
	private String marido;
	private String faxineiro;
	private String limpeza;
	private String garcom;
	private String bartender;
	private String cozinheiro;
	private String churrasqueiro;
	private String confeteiro;
	private String fotografias;
	private String manicure;
	private String maquiagem;
	private String baba;
	private String marketing;
	private String contabeis;
	private String motorista;*/
	
	private String hora_inicial;
	private String hora_final;
	private String dia_inicial;
	private String dia_final ;
	
	
	
	
	//-------------------------------chaves estrangeiras-------------------------------------------------
	
	

	public int getCodigo() {
		return codigo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNomeTrabalho() {
		return nomeTrabalho;
	}
	public void setNomeTrabalho(String nomeTrabalho) {
		this.nomeTrabalho = nomeTrabalho;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/*public String getMarido() {
		return marido;
	}
	public void setMarido(String marido) {
		this.marido = marido;
	}
	public String getFaxineiro() {
		return faxineiro;
	}
	public void setFaxineiro(String faxineiro) {
		this.faxineiro = faxineiro;
	}
	public String getLimpeza() {
		return limpeza;
	}
	public void setLimpeza(String limpeza) {
		this.limpeza = limpeza;
	}
	public String getGarcom() {
		return garcom;
	}
	public void setGarcom(String garcom) {
		this.garcom = garcom;
	}
	public String getBartender() {
		return bartender;
	}
	public void setBartender(String bartender) {
		this.bartender = bartender;
	}
	public String getCozinheiro() {
		return cozinheiro;
	}
	public void setCozinheiro(String cozinheiro) {
		this.cozinheiro = cozinheiro;
	}
	public String getChurrasqueiro() {
		return churrasqueiro;
	}
	public void setChurrasqueiro(String churrasqueiro) {
		this.churrasqueiro = churrasqueiro;
	}
	public String getConfeteiro() {
		return confeteiro;
	}
	public void setConfeteiro(String confeteiro) {
		this.confeteiro = confeteiro;
	}
	public String getFotografias() {
		return fotografias;
	}
	public void setFotografias(String fotografias) {
		this.fotografias = fotografias;
	}
	public String getManicure() {
		return manicure;
	}
	public void setManicure(String manicure) {
		this.manicure = manicure;
	}
	public String getMaquiagem() {
		return maquiagem;
	}
	public void setMaquiagem(String maquiagem) {
		this.maquiagem = maquiagem;
	}
	public String getBaba() {
		return baba;
	}
	public void setBaba(String baba) {
		this.baba = baba;
	}
	public String getMarketing() {
		return marketing;
	}
	public void setMarketing(String marketing) {
		this.marketing = marketing;
	}
	public String getContabeis() {
		return contabeis;
	}
	public void setContabeis(String contabeis) {
		this.contabeis = contabeis;
	}
	public String getMotorista() {
		return motorista;
	}
	public void setMotorista(String motorista) {
		this.motorista = motorista;
	}*/
	public String getHora_inicial() {
		return hora_inicial;
	}
	public void setHora_inicial(String hora_inicial) {
		this.hora_inicial = hora_inicial;
	}
	public String getHora_final() {
		return hora_final;
	}
	public void setHora_final(String hora_final) {
		this.hora_final = hora_final;
	}
	public String getDia_inicial() {
		return dia_inicial;
	}
	public void setDia_inicial(String dia_inicial) {
		this.dia_inicial = dia_inicial;
	}
	public String getDia_final() {
		return dia_final;
	}
	public void setDia_final(String dia_final) {
		this.dia_final = dia_final;
	}
	public int getCodigoEmp() {
		return codigoEmp;
	}
	public void setCodigoEmp(int codigoEmp) {
		this.codigoEmp = codigoEmp;
	}
	public String getSelecionado() {
		return selecionado;
	}
	public void setSelecionado(String selecionado) {
		this.selecionado = selecionado;
	}
	
	
	
	


	
	
}
