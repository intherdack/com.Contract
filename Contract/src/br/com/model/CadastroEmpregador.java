package br.com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class CadastroEmpregador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	private String Categoria;
	private String nome;
	private String telMovel;
	private String telFixo;
	private String apelido;
	private String email;
	private String senha;
	private String descricao;
	private String foto;
	private String qtdAvaliacoes = "0";
	private String nota;
	private String cidade;
	private String estado;
	private String cpf;
	private String totalPublicados;
	
	
	//-------------------------------Getters an setters-------------------------------------------------------------
	
	
	
	public int getCodigo() {
		return codigo;
	}

	public String getTotalPublicados() {
		return totalPublicados;
	}

	public void setTotalPublicados(String totalPublicados) {
		this.totalPublicados = totalPublicados;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getQtdAvaliacoes() {
		return qtdAvaliacoes;
	}

	public void setQtdAvaliacoes(String qtdAvaliacoes) {
		this.qtdAvaliacoes = qtdAvaliacoes;
	}

	public String getCategoria() {
		return Categoria;
	}

	public void setCategoria(String categoria) {
		Categoria = categoria;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelMovel() {
		return telMovel;
	}

	public void setTelMovel(String telMovel) {
		this.telMovel = telMovel;
	}

	public String getTelFixo() {
		return telFixo;
	}

	public void setTelFixo(String telFixo) {
		this.telFixo = telFixo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	
}
