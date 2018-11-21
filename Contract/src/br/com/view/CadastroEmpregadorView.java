package br.com.view;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.eclipse.persistence.annotations.Property;

import br.com.controller.DadosController;
import br.com.controller.LoginController;
import br.com.controller.ValidaController;
import br.com.model.CadastroEmpregador;

@ManagedBean(name = "empregador")
@ViewScoped
public class CadastroEmpregadorView {

	private String nome;
	private String Categoria;
	private String telMovel;
	private String descricao;
	private String telFixo;
	private String apelido;
	private String email;
	private String senha;
	private String foto;
	private String qtdAvaliacoes = "0";
	private String nota = "0";
	private String totalPublicados = "0";
	private String cidade;
	private String estado;
	private String resenha;
	private String cpf;
	private String cep;
	private List<CadastroEmpregador> listaEmpregador;
	String aux;
	 
	/* sobrepondo metodo equals , sem isso fica pegando referencia de memoria */
	public boolean equals(Object o) 
	{
		if(this.email == o) {
			return true;
		}else {
			return false;
		}
		
		
		
	   
	}

	public boolean btcadastrar() throws Exception {

		CadastroEmpregador novo = new CadastroEmpregador();

		novo.setTelFixo(this.telFixo);
		novo.setApelido(this.apelido);
		novo.setNome(this.nome);
		novo.setCategoria("Empregador");
		novo.setTelMovel(this.telMovel);
		novo.setEmail(this.email);
		novo.setSenha(this.senha);
		novo.setFoto(this.foto);
		novo.setQtdAvaliacoes(this.qtdAvaliacoes);
		novo.setNota(this.nota);
		novo.setCidade(this.cidade);
		novo.setEstado(this.estado);
		novo.setCpf(this.cpf);
		novo.setTotalPublicados(this.totalPublicados);
		

		String newsenha = ValidaController.sha(this.senha);
		novo.setSenha(newsenha);
		novo.setDescricao(this.descricao);

		// ============================================= Validação de E-mail
		// ================================================

		new LoginController();
		this.listaEmpregador = LoginController.realizalogEmpregador();

		for (int i = 0; i < this.listaEmpregador.size(); i++) {
			aux = this.listaEmpregador.get(i).getEmail();

		}

		if (ValidaController.isCNPJ(this.cpf) == false && this.cpf.length() > 13) {

			ValidaController.addMessage("Digite um CNPJ Válido");
			
			return false;
		}
		if (ValidaController.isCPF(this.cpf) == false && this.cpf.length() < 12) {

			ValidaController.addMessage("Digite um CPF Válido");
			
			return false;
		}
		

		if (ValidaController.isValidateEmail(this.email) == false || this.email.equals(aux) == true) {

			
			if (ValidaController.isValidateEmail(this.email) == false) {

				ValidaController.addMessage("Digite um E-mail Válido");
			
			}
			if (this.email.equals(aux) == true) {
				ValidaController.addMessage("Este e-mail já está cadastrado!");
			}

		} else {

			// DadosController Dados = new DadosController();
			DadosController.Salvar2(novo);

			// ValidaController.addMessage("Salvo com sucesso!");
			//ValidaController.refresh();
			 //FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
			 

			/* este trecho persiste a mensagem para proxima tela */
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		ValidaController.addMessage("Salvo com sucesso!");
		context.getExternalContext().redirect("login.xhtml");

			
		}
		return false;
	}

	public void bntCep() throws IOException {

		ValidaController c = new ValidaController();
		c.CepService(this.cep);
		this.cidade = c.getCidade();
		this.estado = c.getUf();

	}

//--------------------------------------------------Getter and Setter--------------------------

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return Categoria;
	}

	public void setCategoria(String categoria) {
		Categoria = categoria;
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

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
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

	public String getQtdAvaliacoes() {
		return qtdAvaliacoes;
	}

	public void setQtdAvaliacoes(String qtdAvaliacoes) {
		this.qtdAvaliacoes = qtdAvaliacoes;
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

	public String getResenha() {
		return resenha;
	}

	public void setResenha(String resenha) {
		this.resenha = resenha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTotalPublicados() {
		return totalPublicados;
	}

	public void setTotalPublicados(String totalPublicados) {
		this.totalPublicados = totalPublicados;
	}

	
}
