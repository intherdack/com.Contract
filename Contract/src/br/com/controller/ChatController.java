package br.com.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.eclipse.persistence.jpa.jpql.parser.MaxFunctionFactory;

import br.com.model.CadastroEmpregador;
import br.com.model.CadastroProfissional;
import br.com.model.Chat;

@ManagedBean(name = "C")
@ViewScoped
public class ChatController extends DadosController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Chat> mensagens;
	static List<Chat> mensagensEmpregador;
	private List<CadastroProfissional> prof;
	private List<CadastroEmpregador> empreg;
	static int codigoProfissional;
	static int codigoEmpregador;
	static int codigoServicos;
	private String recDescProfissional;
	private String recDescEmpregador;
	private String mensagemTela;
	static List<Chat> mensagensProfissional;
	private List<Chat> listcaras;
	
	

	
	public void listarProfissionais(int codigoserviço, int codigoEmp, int codigoProf) {
		
		ChatController.codigoServicos = codigoserviço;
		ChatController.codigoEmpregador = codigoEmp;
		ChatController.codigoProfissional = codigoProf;
		
		mensagensEmpregador = new ArrayList();
		
		listcaras = new ArrayList();

		mensagens = DadosController.buscaCHAT();
		for (int i = 0; i < mensagens.size(); i++) {

			if (ChatController.codigoEmpregador == this.mensagens.get(i).getIdOriginadorProfissional()) {
				ChatController.mensagensEmpregador.add(mensagens.get(i));

			}
		}
		
	int e = 0;
		
		for (int i = 0; i < ChatController.mensagensEmpregador.size(); i++) {
			
			String auxnomes = ChatController.mensagensEmpregador.get(i).getEnviado();
			
			do {
				if(auxnomes.equals(ChatController.mensagensEmpregador.get(e).getEnviado())){
					this.listcaras.add(ChatController.mensagensEmpregador.get(e));
					e++;					
				}else {
					e++;
				}				
			}while(e < ChatController.mensagensEmpregador.size());
			

		}
		
		
		
		
		
		
	}

	

	public void buscachatEmpregador(int codigoserviço, int codigoEmp, int codigoProf) throws IOException {

		mensagensEmpregador = new ArrayList();

		ChatController.codigoServicos = codigoserviço;
		ChatController.codigoEmpregador = codigoEmp;
		ChatController.codigoProfissional = codigoProf;

		mensagens = DadosController.buscaCHAT();
		for (int i = 0; i < mensagens.size(); i++) {

			if (ChatController.codigoEmpregador == this.mensagens.get(i).getIdOriginadorProfissional()) {
				ChatController.mensagensEmpregador.add(mensagens.get(i));

			}
		}
		// FacesContext context = FacesContext.getCurrentInstance();
		// context.getExternalContext().redirect("chat.xhtml");
		FacesContext.getCurrentInstance().getExternalContext().redirect("chatEmpregador.xhtml");

	}

	public void buscachatProfissional(int codigoserviço, int codigoEmp, int codigoProf) throws IOException {

		mensagensProfissional = new ArrayList();

		

		ChatController.codigoServicos = codigoserviço;
		ChatController.codigoEmpregador = codigoEmp;
		ChatController.codigoProfissional = codigoProf;

		mensagens = DadosController.buscaCHAT();
		for (int i = 0; i < mensagens.size(); i++) {

			if (ChatController.codigoProfissional == this.mensagens.get(i).getIdOriginadorEmpregador()) {
				ChatController.mensagensProfissional.add(mensagens.get(i));

			}
		}
		// FacesContext context = FacesContext.getCurrentInstance();
		// context.getExternalContext().redirect("chat.xhtml");
		FacesContext.getCurrentInstance().getExternalContext().redirect("chatEmpregador.xhtml");
		

	}

	public void SalvaMSG() throws Exception {

		this.prof = listprofissional(ChatController.codigoProfissional);

		Chat e = new Chat();
		e.setEnviado(prof.get(0).getApelido());
		e.setIdOriginadorEmpregador(ChatController.codigoEmpregador);
		e.setIdOriginadorProfissional(ChatController.codigoProfissional);
		e.setMensagem(this.mensagemTela);
		e.setTime(ValidaController.pegaHora());

		SalvarChat(e);
	}

	public void SalvaMSGEmpregador() throws Exception {

		this.empreg = listempregador(ChatController.codigoEmpregador);

		Chat e = new Chat();
		e.setEnviado(empreg.get(0).getApelido());
		e.setIdOriginadorEmpregador(ChatController.codigoEmpregador);
		e.setIdOriginadorProfissional(ChatController.codigoProfissional);
		e.setMensagem(this.mensagemTela);
		e.setTime(ValidaController.pegaHora());

		SalvarChat(e);

		buscachatProfissional(ChatController.codigoServicos, ChatController.codigoEmpregador,
				ChatController.codigoProfissional);
	}

	public List<Chat> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Chat> mensagens) {
		this.mensagens = mensagens;
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

	public List<Chat> getMensagensEmpregador() {
		return mensagensEmpregador;
	}

	public void setMensagensEmpregador(List<Chat> mensagensEmpregador) {
		ChatController.mensagensEmpregador = mensagensEmpregador;
	}

	public String getMensagemTela() {
		return mensagemTela;
	}

	public void setMensagemTela(String mensagemTela) {
		this.mensagemTela = mensagemTela;
	}

	public int getCodigoProfissional() {
		return codigoProfissional;
	}

	public void setCodigoProfissional(int codigoProfissional) {
		ChatController.codigoProfissional = codigoProfissional;
	}

	public int getCodigoEmpregador() {
		return codigoEmpregador;
	}

	public void setCodigoEmpregador(int codigoEmpregador) {
		ChatController.codigoEmpregador = codigoEmpregador;
	}

	public int getCodigoServicos() {
		return codigoServicos;
	}

	public void setCodigoServicos(int codigoServicos) {
		ChatController.codigoServicos = codigoServicos;
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

	public static List<Chat> getMensagensProfissional() {
		return mensagensProfissional;
	}

	public static void setMensagensProfissional(List<Chat> mensagensProfissional) {
		ChatController.mensagensProfissional = mensagensProfissional;
	}



	public List<Chat> getListcaras() {
		return listcaras;
	}



	public void setListcaras(List<Chat> listcaras) {
		this.listcaras = listcaras;
	}

	

	

}
