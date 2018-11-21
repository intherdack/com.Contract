package br.com.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.controller.DadosController;
import br.com.controller.SessaoController;
import br.com.controller.ValidaController;
import br.com.model.CadastroEmpregador;
import br.com.model.CadastroProfissional;
import br.com.model.Chat;
import br.com.model.Sessao;

@ManagedBean(name = "chatempregador")
@ViewScoped
public class ChatEmpregadorView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Chat> mensagens;
	static List<Chat> mensagensEmpregador;
	private List<CadastroProfissional> prof;
	private List<CadastroEmpregador> empreg;
	static int codigoProfissional1;
	static int codigoEmpregador1;
	static int codigoServicos1;
	private String recDescProfissional;
	private String recDescEmpregador;
	private String mensagemTela;
	static List<Chat> mensagensProfissional;
	private List<Chat> listcaras;
	private String auxnomes;
	static List<Chat> msgEmp;
	


	public void buscachatEmpregador(int codigoPro) throws Exception {
		int codigoEmp = 0;
		new SessaoController();
		List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

		if (aux.isEmpty() == false) {

			if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {

				codigoEmp = aux.get(aux.size() - 1).getIdLogin();

			}

		msgEmp = new ArrayList();

		listcaras = new ArrayList();
		mensagens = DadosController.buscaCHAT();
	
		
			for (int i = 0; i < mensagens.size(); i++) {

				if (codigoEmp == this.mensagens.get(i).getIdOriginadorEmpregador() && this.mensagens.get(i).getIdOriginadorProfissional() == codigoPro) {
					this.msgEmp.add(mensagens.get(i));
				
				}	
		}
			for (int i = 0; i < mensagens.size(); i++) {

				if (this.mensagens.get(i).getIdOriginadorProfissional() == codigoPro && this.mensagens.get(i).getObs() != null) {
					this.listcaras.add(mensagens.get(i));
				
				}	
				}
				this.prof = new ArrayList();
				this.prof = new DadosController().listprofissional(codigoPro);
				if(msgEmp.isEmpty() == true) {
				this.empreg = new DadosController().listempregador(codigoEmp);
				Chat d = new Chat();
				d.setEnviado("Mensagem automática");
				d.setIdOriginadorEmpregador(codigoEmp);
				d.setIdOriginadorProfissional(codigoPro);
				d.setMensagem("Bem vindo, deixe sua mensagem que responderei em breve :) ");
				d.setTime(ValidaController.pegaHora());
				d.setObs(this.prof.get(0).getApelido() + " / " + this.empreg.get(0).getApelido());
				
				new DadosController().SalvarChat(d);
				buscachatEmpregador(codigoPro);
			}
			
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			ValidaController.addMessage("Sessão Expirada!!!");
			context.getExternalContext().redirect("login.xhtml");
		}
			
			
		// FacesContext context = FacesContext.getCurrentInstance();
		// context.getExternalContext().redirect("chat.xhtml");
		FacesContext.getCurrentInstance().getExternalContext().redirect("chatEmpregador.xhtml");
		
	}
	
	
	

public void SalvaMSGEmpregador(int codigoPro) throws Exception {
		
		new SessaoController();
		List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

		if (aux.isEmpty() == false) {

			if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {

				codigoEmpregador1 = aux.get(aux.size() - 1).getIdLogin();

			}
		
		mensagens = DadosController.buscaCHAT();
		
		
		//codigoEmpregador1 = LogarView.getCodigoUsuario2();
		new DadosController();
		this.empreg = DadosController.listempregador(codigoEmpregador1);
		String aux1 = this.empreg.get(0).getApelido();

		Chat e = new Chat();
		e.setEnviado(aux1);
		e.setIdOriginadorEmpregador(codigoEmpregador1);
		e.setIdOriginadorProfissional(codigoPro);
		e.setMensagem(this.mensagemTela);
		e.setTime(ValidaController.pegaHora());

		new DadosController().SalvarChat(e);
		buscachatEmpregador(codigoPro);
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("chatEmpregador.xhtml");
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			ValidaController.addMessage("Sessão Expirada!!!");
			context.getExternalContext().redirect("login.xhtml");
		}
		}

public void conversaEmpregador() throws Exception {
	listcaras = new ArrayList();
	int codigoEmp = 0;
	List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

	if (aux.isEmpty() == false) {

		if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {
			codigoEmp = aux.get(aux.size() - 1).getIdLogin();

		}
	msgEmp = new ArrayList();
	mensagens = DadosController.buscaCHAT();
		for (int i = 0; i < mensagens.size(); i++) {

			if (this.mensagens.get(i).getIdOriginadorEmpregador() == codigoEmp && this.mensagens.get(i).getObs() != null) {
				listcaras.add(mensagens.get(i));
				
			}		
	}
		if(listcaras.isEmpty()) {
			ValidaController.addMessage("Nenhuma conversação iniciada!");
		}
	} else {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		ValidaController.addMessage("Sessão Expirada!!!");
		context.getExternalContext().redirect("login.xhtml");
	}
	
}

	public List<Chat> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Chat> mensagens) {
		this.mensagens = mensagens;
	}

	public static List<Chat> getMensagensEmpregador() {
		return mensagensEmpregador;
	}

	public static void setMensagensEmpregador(List<Chat> mensagensEmpregador) {
		ChatEmpregadorView.mensagensEmpregador = mensagensEmpregador;
	}

	public List<CadastroProfissional> getProf() {
		return prof;
	}

	public void setProf(List<CadastroProfissional> prof) {
		this.prof = prof;
	}

	public List<CadastroEmpregador> getEmpreg() {
		return empreg;
	}

	public void setEmpreg(List<CadastroEmpregador> empreg) {
		this.empreg = empreg;
	}

	public static int getCodigoProfissional() {
		return codigoProfissional1;
	}

	public static void setCodigoProfissional(int codigoProfissional) {
		ChatEmpregadorView.codigoProfissional1 = codigoProfissional;
	}

	public static int getCodigoEmpregador() {
		return codigoEmpregador1;
	}

	public static void setCodigoEmpregador(int codigoEmpregador) {
		ChatEmpregadorView.codigoEmpregador1 = codigoEmpregador;
	}

	public static int getCodigoServicos() {
		return codigoServicos1;
	}

	public static void setCodigoServicos(int codigoServicos) {
		ChatEmpregadorView.codigoServicos1 = codigoServicos;
	}

	public String getRecDescProfissional() {
		return recDescProfissional;
	}

	public void setRecDescProfissional(String recDescProfissional) {
		this.recDescProfissional = recDescProfissional;
	}

	public String getRecDescEmpregador() {
		return recDescEmpregador;
	}

	public void setRecDescEmpregador(String recDescEmpregador) {
		this.recDescEmpregador = recDescEmpregador;
	}

	public String getMensagemTela() {
		return mensagemTela;
	}

	public void setMensagemTela(String mensagemTela) {
		this.mensagemTela = mensagemTela;
	}

	public static List<Chat> getMensagensProfissional() {
		return mensagensProfissional;
	}

	public static void setMensagensProfissional(List<Chat> mensagensProfissional) {
		ChatEmpregadorView.mensagensProfissional = mensagensProfissional;
	}

	public List<Chat> getListcaras() {
		return listcaras;
	}

	public void setListcaras(List<Chat> listcaras) {
		this.listcaras = listcaras;
	}

	public List<Chat> getMsgEmp() {
		return msgEmp;
	}

	public void setMsgEmp(List<Chat> msgEmp) {
		ChatEmpregadorView.msgEmp = msgEmp;
	}
	public static int getCodigoProfissional1() {
		return codigoProfissional1;
	}

	public static void setCodigoProfissional1(int codigoProfissional1) {
		ChatEmpregadorView.codigoProfissional1 = codigoProfissional1;
	}

	public static int getCodigoEmpregador1() {
		return codigoEmpregador1;
	}

	public static void setCodigoEmpregador1(int codigoEmpregador1) {
		ChatEmpregadorView.codigoEmpregador1 = codigoEmpregador1;
	}

}
