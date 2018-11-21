package br.com.view;

import java.io.IOException; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.controller.DadosController;
import br.com.controller.SessaoController;
import br.com.controller.ValidaController;
import br.com.model.CadastroEmpregador;
import br.com.model.Sessao;

@ManagedBean(name = "editaEmpregador")
@ViewScoped
public class EditarEmpregadorView {
	public List<CadastroEmpregador> listaPro;
	private int codigo;
	private String nome;
	private String Categoria;
	private String telMovel;
	private String descricao;
	private String telFixo;
	private String apelido;
	private String email;
	private String senha;
	private String foto;
	private String qtdAvaliacoes;
	private String nota;
	private String totalPublicados;
	private String cidade;
	private String estado;
	private String resenha;
	private String cpf;
	private String cep;
	private long auxCPF;
	


	private int inicio;
	
	CadastroEmpregador novo = new CadastroEmpregador();
	
	public void bntCep() throws IOException {
		
		ValidaController c = new ValidaController();
		c.CepService(this.cep);
		this.cidade = c.getCidade();
		this.estado = c.getUf();
		this.listaPro.get(0).setCidade(this.cidade);
		this.listaPro.get(0).setEstado(this.estado);

	}
	


	
public void BuscarDados() throws IOException {
	
	new SessaoController();
	List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

	if (aux.isEmpty() == false) {

		if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {

			this.inicio = aux.get(aux.size() - 1).getIdLogin();

		}
	
	
	this.listaPro  = new ArrayList<CadastroEmpregador>();
	//this.inicio = LogarView.getCodigoUsuario2();
	
	listaPro = DadosController.listempregador(this.inicio);
	this.cidade = this.listaPro.get(0).getCidade();
	this.estado = this.listaPro.get(0).getEstado();
	this.cpf = this.listaPro.get(0).getCpf();
	
	
	
	
} else {
	FacesContext context = FacesContext.getCurrentInstance();
	context.getExternalContext().getFlash().setKeepMessages(true);
	ValidaController.addMessage("Sessão Expirada!!!");
	context.getExternalContext().redirect("login.xhtml");
}
	
	
	
	}

public boolean bntAlterarEmp() throws IOException {
	
	this.nome = this.listaPro.get(0).getNome();
	this.Categoria = this.listaPro.get(0).getCategoria();
	this.telMovel = this.listaPro.get(0).getTelMovel();
	this.descricao = this.listaPro.get(0).getDescricao();
	this.telFixo = this.listaPro.get(0).getTelFixo();
	this.apelido = this.listaPro.get(0).getApelido();
	this.email = this.listaPro.get(0).getEmail();
			
	this.foto = this.listaPro.get(0).getFoto();
	this.qtdAvaliacoes = this.listaPro.get(0).getQtdAvaliacoes();
	this.nota = this.listaPro.get(0).getNota();
	this.totalPublicados = this.listaPro.get(0).getTotalPublicados();
	this.cidade = this.listaPro.get(0).getCidade();
	this.estado = this.listaPro.get(0).getEstado();
	this.cpf = this.listaPro.get(0).getCpf();
	
	

	if (ValidaController.isCNPJ(this.cpf) == false && this.cpf.length() > 13) {

		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		ValidaController.addMessage("Digite um CNPJ Válido");
		context.getExternalContext().redirect("editarempregador.xhtml");
		
		return false;
	}
	if (ValidaController.isCPF(this.cpf) == false && this.cpf.length() < 12) {

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		ValidaController.addMessage("Digite um CPF Válido");
		context.getExternalContext().redirect("editarempregador.xhtml");
		
		return false;
	}
	
	
	
	
	
	novo.setCodigo(this.listaPro.get(0).getCodigo());
	novo.setTelFixo(this.listaPro.get(0).getTelFixo());
	novo.setApelido(this.listaPro.get(0).getApelido());
	novo.setNome(this.listaPro.get(0).getNome());
	novo.setCategoria(this.listaPro.get(0).getCategoria());
	novo.setTelMovel(this.listaPro.get(0).getTelMovel());
	novo.setEmail(this.listaPro.get(0).getEmail());
	novo.setSenha(this.listaPro.get(0).getSenha());
	novo.setFoto(this.listaPro.get(0).getFoto());
	novo.setQtdAvaliacoes(this.listaPro.get(0).getQtdAvaliacoes());
	novo.setNota(this.listaPro.get(0).getNota());
	novo.setCidade(this.listaPro.get(0).getCidade());
	novo.setCpf(this.listaPro.get(0).getCpf());
	novo.setTotalPublicados(this.listaPro.get(0).getTotalPublicados());
	novo.setEstado(this.listaPro.get(0).getEstado());
	

	//String newsenha = ValidaController.sha(this.senha);
	//novo.setSenha(newsenha);
	novo.setDescricao(this.descricao);
	

	
	DadosController.AlterarEmpregador(novo);
	
	
	
	/* este trecho persiste a mensagem para proxima tela */
	FacesContext context = FacesContext.getCurrentInstance();
	context.getExternalContext().getFlash().setKeepMessages(true);
	
	ValidaController.addMessage("Salvo com sucesso!");
	context.getExternalContext().redirect("paginaempregador.xhtml");
	return false;
	
			
}


public int getCodigo() {
	return codigo;
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


public String getDescricao() {
	return descricao;
}


public void setDescricao(String descricao) {
	this.descricao = descricao;
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


public String getTotalPublicados() {
	return totalPublicados;
}


public void setTotalPublicados(String totalPublicados) {
	this.totalPublicados = totalPublicados;
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


public String getCep() {
	return cep;
}


public void setCep(String cep) {
	this.cep = cep;
}


public List<CadastroEmpregador> getlistaPro() {
	return listaPro;
}


public void setlistaPro(List<CadastroEmpregador> listaPro) {
	this.listaPro = listaPro;
}


public int getInicio() {
	return inicio;
}


public void setInicio(int inicio) {
	this.inicio = inicio;
}


public CadastroEmpregador getNovo() {
	return novo;
}


public void setNovo(CadastroEmpregador novo) {
	this.novo = novo;
}

public List<CadastroEmpregador> getListaPro() {
	return listaPro;
}




public void setListaPro(List<CadastroEmpregador> listaPro) {
	this.listaPro = listaPro;
}




public long getAuxCPF() {
	return auxCPF;
}




public void setAuxCPF(long auxCPF) {
	this.auxCPF = auxCPF;
}




}
