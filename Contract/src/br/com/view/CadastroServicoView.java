package br.com.view;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PostRemove;

import br.com.controller.DadosController;
import br.com.controller.SessaoController;
import br.com.controller.ValidaController;
import br.com.model.CadastroEmpregador;
import br.com.model.CadastroProfissional;
import br.com.model.Servicos;
import br.com.model.Sessao;



@ManagedBean(name = "servico")
@ViewScoped
public class CadastroServicoView {
	
	private int codigoEmp;
	private String status;
	private String cep;
	private String cidade;
	private String UF;
	private String nomeTrabalho;
	private String valor;
	private String tipo;
	private String descricao;
	private List<CadastroEmpregador> buscanome;
	private String apemp;
	private boolean bandeira1 = false;
	private String hora_inicial;
	private String hora_final;
	private String dia_inicial;
	private String dia_final;
	private String funcao;
	private String value = "sem serviço selecionado";
	
	private List<CadastroEmpregador> lstEmp;
	
	public void buscanome() throws IOException {
		new SessaoController();
		List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

		if (aux.isEmpty() == false) {

			if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {

				this.codigoEmp = aux.get(aux.size() - 1).getIdLogin();
			}
				
			new DadosController();
			this.buscanome = DadosController.listempregador(this.codigoEmp);
			this. apemp = this.buscanome.get(0).getApelido();
		
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			ValidaController.addMessage("Sessão Expirada!!!");
			context.getExternalContext().redirect("login.xhtml");
		}
	}
	

	public void bntCep() throws IOException {
		ValidaController c = new ValidaController();
		c.CepService(this.cep);
		this.cidade = c.getCidade();
		this.UF = c.getUf();
		
	}
	
	
	/*
	public void setvalor() {
		
		this.value = getFuncao().toString();
		
		
	}*/
			
	
	public void btnSalvar() throws Exception {
	
		new SessaoController();
		List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

		if (aux.isEmpty() == false) {

			if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {

				this.codigoEmp = aux.get(aux.size() - 1).getIdLogin();
			}
		}
		
		//========estancia list de empregador para fazer a alteracao em qtd. de publicacoes ================
		lstEmp = new ArrayList<CadastroEmpregador>();
		
		DadosController alt = new DadosController();
		lstEmp = alt.listempregador(this.codigoEmp);
		
										//====================//	
		
		// faz o calculo
		
		int publicacoes = Integer.parseInt(lstEmp.get(0).getQtdAvaliacoes());
		
		publicacoes = publicacoes + 1;
									//==============//
		
		//============== altera o valor de publicacoes do empregador no banco ==============================
		CadastroEmpregador altEmp = new CadastroEmpregador();
		altEmp.setApelido(lstEmp.get(0).getApelido());
		altEmp.setCategoria(lstEmp.get(0).getCategoria());
		altEmp.setCidade(lstEmp.get(0).getCidade());
		altEmp.setCodigo(lstEmp.get(0).getCodigo());
		altEmp.setCpf(lstEmp.get(0).getCpf());
		altEmp.setDescricao(lstEmp.get(0).getDescricao());
		altEmp.setEmail(lstEmp.get(0).getEmail());
		altEmp.setEstado(lstEmp.get(0).getEstado());
		altEmp.setFoto(lstEmp.get(0).getFoto());
		altEmp.setNome(lstEmp.get(0).getNome());
		altEmp.setNota(lstEmp.get(0).getNota());
		altEmp.setQtdAvaliacoes(lstEmp.get(0).getQtdAvaliacoes());
		altEmp.setSenha(lstEmp.get(0).getSenha());
		altEmp.setTelFixo(lstEmp.get(0).getTelFixo());
		altEmp.setTelMovel(lstEmp.get(0).getTelMovel());
		altEmp.setTotalPublicados(Integer.toString(publicacoes));
		
		
		DadosController altera = new DadosController();
		altera.AlterarEmpregador(altEmp);
		////////////////////////////////////////////////////////////////////////
		
		
		
		//=================================== faz o cadastro de servico ==========================================
		Servicos novo = new Servicos();

	
		 novo.setStatus(this.status = "aberto");
		 novo.setCep(this.cep);
		 novo.setUf(this.UF);
		 novo.setCidade(this.cidade);
		 novo.setNomeTrabalho(this.nomeTrabalho);
		 novo.setValor(this.valor);
		 novo.setTipo(this.tipo);
		 novo.setDescricao(this.descricao);
		 
		 novo.setHora_inicial(this.hora_inicial);
		 String xpto1 = this.hora_inicial.substring(11, 16);
			novo.setHora_inicial(xpto1);
			
		 novo.setHora_final(this.hora_inicial);
		 String xpto2 = this.hora_final.substring(11, 16);
			novo.setHora_final(xpto2);
			
			String ano = this.dia_inicial.substring(25, 29).toString();
			String mes = this.dia_inicial.substring(4, 7).toString();
			Object a = this.dia_inicial.substring(7, 10).toString();
			
			
			String auxdia = a.toString()+"/"+mes+"/"+ano;
			novo.setDia_inicial(auxdia);
			
			String ano1 = this.dia_final.substring(25, 29).toString();
			String mes1 = this.dia_final.substring(4, 7).toString();
			Object a1 = this.dia_final.substring(7, 10).toString();
			
			
			String auxdia1 = a1.toString()+"/"+mes1+"/"+ano1;
			 novo.setDia_final(auxdia1);
			 
			 
		 novo.setCodigoEmp(this.codigoEmp);
		 novo.setSelecionado(this.funcao);
		
	
		 new DadosController().SalvarServicos(novo);
	
		 //redirecionando
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		ValidaController.addMessage("Salvo com sucesso!");
		context.getExternalContext().redirect("paginaempregador.xhtml");
	
	}
	
	//========================================================= geter and serter ==================================
	
	
	
	
	public String getCep() {
		return cep;
	}

	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public List<CadastroEmpregador> getLstEmp() {
		return lstEmp;
	}


	public void setLstEmp(List<CadastroEmpregador> lstEmp) {
		this.lstEmp = lstEmp;
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

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public String getNomeTrabalho() {
		return nomeTrabalho;
	}

	public void setNomeTrabalho(String nomeTrabalho) {
		this.nomeTrabalho = nomeTrabalho;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<CadastroEmpregador> getBuscanome() {
		return buscanome;
	}

	public void setBuscanome(List<CadastroEmpregador> buscanome) {
		this.buscanome = buscanome;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApemp() {
		return apemp;
	}

	public void setApemp(String apemp) {
		this.apemp = apemp;
	}

	public boolean isBandeira1() {
		return bandeira1;
	}

	public void setBandeira1(boolean bandeira1) {
		this.bandeira1 = bandeira1;
	}


	public String getFuncao() {
		return funcao;
	}


	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

}
