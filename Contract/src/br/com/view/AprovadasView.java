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
import br.com.model.Negociacao;
import br.com.model.Servicos;
import br.com.model.Sessao;

@ManagedBean(name = "aprovadas")
@ViewScoped
public class AprovadasView {
	
	public List<Negociacao> lstNegociacao;
	public List<Negociacao> lstAprovados;
	public List<Servicos> lstServico;
	public List<Servicos> lstIntermediaria;
	private int codigoProfissional;

	public void BuscaAprovados() throws IOException{
		
		new SessaoController();
		List<Sessao> aux1 = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

		if (aux1.isEmpty() == false) {

			if (new SessaoController().sessaoAtual().equals(aux1.get(aux1.size() - 1).getSessaoAtual())) {

				this.codigoProfissional = aux1.get(aux1.size() - 1).getIdLogin();

			}
	
		//////trocar static /////////////////////////////////////
	
		//this.codigoProfissional = LogarView.getCodigoUsuario1();
		
		///////////////////////////////////////////////////////////
	
		lstNegociacao = new ArrayList<Negociacao>();
		lstAprovados = new ArrayList<Negociacao>();
		lstServico = new ArrayList<Servicos>();
		lstIntermediaria = new ArrayList<Servicos>();
		
		DadosController vg = new DadosController();
		lstNegociacao = vg.listaNpro(this.codigoProfissional);
		
		for (int i = 0; i < lstNegociacao.size(); i++){
			
			if(lstNegociacao.get(i).getStatus().equals("aprovado")) {
				lstAprovados.add(lstNegociacao.get(i));
			}
		}
		
		
		DadosController dados = new DadosController();
		
		for (int i = 0; i < lstAprovados.size(); i++){
		
			int aux = lstAprovados.get(i).getCodigoServico();
			
			lstIntermediaria = dados.listaServicosId(aux);
			
			lstServico.addAll(lstIntermediaria);
		
		}
		
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			ValidaController.addMessage("Sessão Expirada!!!");
			context.getExternalContext().redirect("login.xhtml");
		}
	}

	public List<Servicos> getLstIntermediaria() {
		return lstIntermediaria;
	}

	public void setLstIntermediaria(List<Servicos> lstIntermediaria) {
		this.lstIntermediaria = lstIntermediaria;
	}

	public List<Negociacao> getLstNegociacao() {
		return lstNegociacao;
	}

	public void setLstNegociacao(List<Negociacao> lstNegociacao) {
		this.lstNegociacao = lstNegociacao;
	}

	public List<Negociacao> getLstAprovados() {
		return lstAprovados;
	}

	public void setLstAprovados(List<Negociacao> lstAprovados) {
		this.lstAprovados = lstAprovados;
	}

	public List<Servicos> getLstServico() {
		return lstServico;
	}

	public void setLstServico(List<Servicos> lstServico) {
		this.lstServico = lstServico;
	}

	public int getCodigoProfissional() {
		return codigoProfissional;
	}

	public void setCodigoProfissional(int codigoProfissional) {
		this.codigoProfissional = codigoProfissional;
	}
	
	
	
}
