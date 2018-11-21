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
import br.com.model.CadastroProfissional;
import br.com.model.Negociacao;
import br.com.model.Sessao;

@ManagedBean(name = "avRecebidas")
@ViewScoped
public class AvaliacoesRecebidasView {

	public List<Negociacao> negociacao;
	public List<Negociacao> negociacao2;
	public List<CadastroEmpregador> lstEmpregador;
	public List<CadastroProfissional> lstProfissional;
	private int codigoEmpregador;
	public List<Negociacao> lstAvaliacoes;
	public List<Negociacao> lstAvaliacoes2;
	private int codigoProfissional;

	public void empregador() throws IOException {

		new SessaoController();
		List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

		if (aux.isEmpty() == false) {

			if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {

				this.codigoEmpregador = aux.get(aux.size() - 1).getIdLogin();

			}

			////// trocar static /////////////////////////////////////

			// this.codigoEmpregador = LogarView.getCodigoUsuario2();

			///////////////////////////////////////////////////////////
			
			lstProfissional = new ArrayList<CadastroProfissional>();
		/*	DadosController p = new DadosController();
			this.lstProfissional = p.listprofissional(this.codigoProfissional);*/

			

			negociacao = new ArrayList<Negociacao>();
			lstEmpregador = new ArrayList<CadastroEmpregador>();
			lstAvaliacoes = new ArrayList<Negociacao>();

			DadosController e = new DadosController();
			lstEmpregador = e.listempregador(this.codigoEmpregador);

			DadosController n = new DadosController();

			negociacao = n.listaNegociacoes();

			for (int i = 0; i < negociacao.size(); i++) {
			

				Object o = lstEmpregador.get(0).getCodigo();
			
			
				if (negociacao.get(i).getAprovadoProfissional().equals("finalizado")) {
					int b = Integer.parseInt(negociacao.get(i).getFechadoProfissional());

					if (o.equals(b)) {
						if (negociacao.get(i).getAprovadoProfissional().equals("finalizado")) {
							this.lstProfissional = DadosController.listprofissional(negociacao.get(i).getCodigoProfissional());

							lstAvaliacoes.add(negociacao.get(i));
						}
					}
				}
				

			}
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			ValidaController.addMessage("Sessão Expirada!!!");
			context.getExternalContext().redirect("login.xhtml");
		}

	}

	public void profissional() throws IOException {
		lstEmpregador = new ArrayList<CadastroEmpregador>();
		new SessaoController();
		List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

		if (aux.isEmpty() == false) {

			if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {

				this.codigoProfissional = aux.get(aux.size() - 1).getIdLogin();

			}

			////// trocar static /////////////////////////////////////

			// this.codigoProfissional = LogarView.getCodigoUsuario1();

			///////////////////////////////////////////////////////////

			negociacao2 = new ArrayList<Negociacao>();
			lstProfissional = new ArrayList<CadastroProfissional>();
			lstAvaliacoes2 = new ArrayList<Negociacao>();

			DadosController p = new DadosController();
			this.lstProfissional = p.listprofissional(this.codigoProfissional);

			DadosController n = new DadosController();

			negociacao2 = n.listaNegociacoes();

			for (int i = 0; i < negociacao2.size(); i++) {

				Object o = lstProfissional.get(0).getCodigo();
				
				if (negociacao2.get(i).getAprovadoProfissional().equals("finalizado")) {
				int b = Integer.parseInt(negociacao2.get(i).getFechadoProfissional());

				if (o.equals(b)) {
					if (negociacao2.get(i).getAprovadoProfissional().equals("finalizado")) {

						lstAvaliacoes2.add(negociacao2.get(i));
						this.lstEmpregador = DadosController.listempregador(negociacao2.get(i).getCodigoEmpregador());
					}
				}
				}

			}

		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			ValidaController.addMessage("Sessão Expirada!!!");
			context.getExternalContext().redirect("login.xhtml");
		}

	}

	public List<Negociacao> getNegociacao2() {
		return negociacao2;
	}

	public void setNegociacao2(List<Negociacao> negociacao2) {
		this.negociacao2 = negociacao2;
	}

	public List<CadastroProfissional> getLstProfissional() {
		return lstProfissional;
	}

	public void setLstProfissional(List<CadastroProfissional> lstProfissional) {
		this.lstProfissional = lstProfissional;
	}

	public List<Negociacao> getLstAvaliacoes2() {
		return lstAvaliacoes2;
	}

	public void setLstAvaliacoes2(List<Negociacao> lstAvaliacoes2) {
		this.lstAvaliacoes2 = lstAvaliacoes2;
	}

	public int getCodigoProfissional() {
		return codigoProfissional;
	}

	public void setCodigoProfissional(int codigoProfissional) {
		this.codigoProfissional = codigoProfissional;
	}

	public List<Negociacao> getNegociacao() {
		return negociacao;
	}

	public void setNegociacao(List<Negociacao> negociacao) {
		this.negociacao = negociacao;
	}

	public List<CadastroEmpregador> getLstEmpregador() {
		return lstEmpregador;
	}

	public void setLstEmpregador(List<CadastroEmpregador> lstEmpregador) {
		this.lstEmpregador = lstEmpregador;
	}

	public int getCodigoEmpregador() {
		return codigoEmpregador;
	}

	public void setCodigoEmpregador(int codigoEmpregador) {
		this.codigoEmpregador = codigoEmpregador;
	}

	public List<Negociacao> getLstAvaliacoes() {
		return lstAvaliacoes;
	}

	public void setLstAvaliacoes(List<Negociacao> lstAvaliacoes) {
		this.lstAvaliacoes = lstAvaliacoes;
	}

}
