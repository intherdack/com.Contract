package br.com.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.eclipse.persistence.jpa.jpql.parser.MaxFunctionFactory;

import br.com.controller.DadosController;
import br.com.controller.SessaoController;
import br.com.controller.ValidaController;
import br.com.model.CadastroEmpregador;
import br.com.model.CadastroProfissional;
import br.com.model.Chat;
import br.com.model.Sessao;

@ManagedBean(name = "chatprofissional")
@ViewScoped
public class ChatProfissionalView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Chat> mensagens;
	private List<CadastroProfissional> prof;
	private List<CadastroEmpregador> empreg;
	static int codigoProfissional1;
	static int codigoEmpregador1;
	static int codigoServicos1;
	private String recDescProfissional;
	private String recDescEmpregador;
	private String mensagemTela;
	private List<Chat> listcaras;
	static List<Chat> msgPro;
	static List<Chat> listConversas;
	
	

	public void buscachatProfissional(int codigoEmp) throws Exception {
		listcaras = new ArrayList();
		int codigoPro = 0;
		List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

		if (aux.isEmpty() == false) {

			if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {
				codigoPro = aux.get(aux.size() - 1).getIdLogin();

			}
		msgPro = new ArrayList();
		mensagens = DadosController.buscaCHAT();
			for (int i = 0; i < mensagens.size(); i++) {

				if (codigoEmp == this.mensagens.get(i).getIdOriginadorEmpregador() && this.mensagens.get(i).getIdOriginadorProfissional() == codigoPro) {
					msgPro.add(mensagens.get(i));
					
				}		
		}
			
			for (int i = 0; i < mensagens.size(); i++) {

				if (codigoEmp == this.mensagens.get(i).getIdOriginadorEmpregador() && this.mensagens.get(i).getIdOriginadorProfissional() == codigoPro && this.mensagens.get(i).getObs() != null) {
					listcaras.add(mensagens.get(i));
					
				}		
		}
			
			this.prof = new ArrayList();
			this.empreg = new DadosController().listempregador(codigoEmp);
			
			if(msgPro.isEmpty() == true) {
				this.prof = new DadosController().listprofissional(codigoPro);
				Chat d = new Chat();
				d.setEnviado("Mensagem automática");
				d.setIdOriginadorEmpregador(codigoEmp);
				d.setIdOriginadorProfissional(codigoPro);
				d.setMensagem("Bem vindo, deixe sua mensagem que responderei em breve :)");
				d.setTime(ValidaController.pegaHora());
				d.setObs(this.prof.get(0).getApelido() + " / " + this.empreg.get(0).getApelido());
				
				new DadosController().SalvarChat(d);
				buscachatProfissional(codigoEmp);
			}
			
			
		// FacesContext context = FacesContext.getCurrentInstance();
		// context.getExternalContext().redirect("chat.xhtml");
			
			
		FacesContext.getCurrentInstance().getExternalContext().redirect("chatProfissional.xhtml");
		
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			ValidaController.addMessage("Sessão Expirada!!!");
			context.getExternalContext().redirect("login.xhtml");
		}
		
	}

	public void SalvaMSGProfissional(int emp) throws Exception {
		 int pro = 0;
		List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

		if (aux.isEmpty() == false) {

			if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {

				pro = aux.get(aux.size() - 1).getIdLogin();

			}
		mensagens = DadosController.buscaCHAT();
		
		
		new DadosController();
		
		this.prof = DadosController.listprofissional(pro);
		
		String aux1 = this.prof.get(0).getApelido();
		System.out.println(aux);

		Chat e = new Chat();
		e.setEnviado(aux1);
		e.setIdOriginadorEmpregador(emp);
		e.setIdOriginadorProfissional(pro);
		e.setMensagem(this.mensagemTela);
		e.setTime(ValidaController.pegaHora());

		new DadosController().SalvarChat(e);
		buscachatProfissional(emp);
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("chatProfissional.xhtml");
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			ValidaController.addMessage("Sessão Expirada!!!");
			context.getExternalContext().redirect("login.xhtml");
		}
		
		}
	
	
	
	public void conversaProfissional() throws Exception {
		listcaras = new ArrayList();
		int codigoPro = 0;
		List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

		if (aux.isEmpty() == false) {

			if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {
				codigoPro = aux.get(aux.size() - 1).getIdLogin();

			}
		msgPro = new ArrayList();
		mensagens = DadosController.buscaCHAT();
			for (int i = 0; i < mensagens.size(); i++) {

				if (this.mensagens.get(i).getIdOriginadorProfissional() == codigoPro && this.mensagens.get(i).getObs() != null) {
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
	
	

	public static List<Chat> getListConversas() {
		return listConversas;
	}

	public static void setListConversas(List<Chat> listConversas) {
		ChatProfissionalView.listConversas = listConversas;
	}

	public static int getCodigoServicos1() {
		return codigoServicos1;
	}

	public static void setCodigoServicos1(int codigoServicos1) {
		ChatProfissionalView.codigoServicos1 = codigoServicos1;
	}

	public List<Chat> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Chat> mensagens) {
		this.mensagens = mensagens;
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

	public List<Chat> getListcaras() {
		return listcaras;
	}

	public void setListcaras(List<Chat> listcaras) {
		this.listcaras = listcaras;
	}

	public List<Chat> getMsgPro() {
		return msgPro;
	}

	public void setMsgPro(List<Chat> msgPro) {
		ChatProfissionalView.msgPro = msgPro;
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
