package br.com.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.controller.DadosController;
import br.com.controller.SessaoController;
import br.com.controller.ValidaController;
import br.com.model.CadastroProfissional;
import br.com.model.Negociacao;
import br.com.model.Servicos;
import br.com.model.Sessao;




@ManagedBean(name = "candidatos")
@ViewScoped
public class VisualizarCandidatosView {
	
	private int codigoEmpregador;
	private String nome;
	private String data;
	private String candidatos;
	private String nomeVaga;

	
	public List<Servicos> lstServicos ;
	public List<Servicos> lstServicosFiltrado ;
	public List<Negociacao> lstServicos2 ;
	public List<Negociacao> lstNegocicao ;
	public List<Negociacao> lstNegocicaoFiltrada ;
	public List<Servicos> listaAux;
	public List<CadastroProfissional> lstProfissional;
	public List<Negociacao> lstN;
	static List<CadastroProfissional> lstProfissionalFiltrada;
	static int codigoServico;
	
	
	
	
	
public List<Servicos> vagas() throws IOException {
		
		new SessaoController();
		List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

		if (aux.isEmpty() == false) {

			if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {

				this.codigoEmpregador = aux.get(aux.size() - 1).getIdLogin();

			}
		 
		
				
		lstServicos = new ArrayList<Servicos>();
		lstNegocicao = new ArrayList<Negociacao>();
		lstNegocicaoFiltrada = new ArrayList<Negociacao>();
		lstServicosFiltrado = new ArrayList<Servicos>();
		
		
		
		lstNegocicao = DadosController.listaNegociacao(this.codigoEmpregador);
		lstServicos = DadosController.listaServicosPublicados(this.codigoEmpregador);
		
		
				
					for (int b = 0; b < lstServicos.size(); b++)
			        {
					
							
							if(lstServicos.get(b).getStatus().equals("aberto") || lstServicos.get(b).getStatus().equals("solicitado")) {
								this.lstServicosFiltrado.add(lstServicos.get(b));
							
						}
			}
		listaAux = new ArrayList<Servicos>();
		listaAux = this.lstServicosFiltrado;
		
		
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			ValidaController.addMessage("Sessão Expirada!!!");
			context.getExternalContext().redirect("login.xhtml");
		}
		return lstServicos;
	}

	
	public void Candidatos(int codigo) throws IOException {
	codigoServico = codigo;
	
	lstN = new ArrayList<Negociacao>();
	
		// aqui é onde eu puxo os dados da vaga -----------------------------------------------------------------
		ArrayList<Servicos> lstVaga = new ArrayList<Servicos>();
		lstVaga =  (ArrayList<Servicos>) DadosController.listaServicosId(codigoServico);
		this.nomeVaga = lstVaga.get(0).getNomeTrabalho();
		//---------------------------------------------------------------------------------------------------------
		
		
		lstProfissional = new ArrayList<CadastroProfissional>();
		lstProfissionalFiltrada = new ArrayList<CadastroProfissional>();
		lstN = new ArrayList<Negociacao>();
		lstServicos2 = new ArrayList<Negociacao>();
		
		lstProfissional =  DadosController.listaProfissional();
		
		DadosController negoc = new DadosController();
		lstServicos2 = negoc.listaNegociacoes();
		
		
		
		//puxa a linha da negociacao
		for (int i = 0; i < lstServicos2.size(); i++){
			
			Object s = lstServicos2.get(i).getCodigoServico();
			
			if(s.equals(codigoServico)) {
				lstN.add(lstServicos2.get(i));
				
			}
			
			
		}
		
		
		// puxa todos profiisionais que tem enteresse em sua vaga
		for (int i = 0; i < this.lstN.size(); i++){
			
			int aux = lstN.get(i).getCodigoProfissional();
			this.lstProfissionalFiltrada = DadosController.listprofissional(aux);
			
		}
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		context.getExternalContext().redirect("candidatos.xhtml");
	}
		
		
	
	public List<Servicos> getListaAux() {
		return listaAux;
	}


	public void setListaAux(List<Servicos> listaAux) {
		this.listaAux = listaAux;
	}


	public String getNomeVaga() {
		return nomeVaga;
	}


	public List<Negociacao> getLstN() {
		return lstN;
	}


	public void setLstN(List<Negociacao> lstN) {
		this.lstN = lstN;
	}


	public void setNomeVaga(String nomeVaga) {
		this.nomeVaga = nomeVaga;
	}





	public List<Negociacao> getLstServicos2() {
		return lstServicos2;
	}


	public void setLstServicos2(List<Negociacao> lstServicos2) {
		this.lstServicos2 = lstServicos2;
	}


	public List<CadastroProfissional> getLstProfissional() {
		return lstProfissional;
	}


	public void setLstProfissional(List<CadastroProfissional> lstProfissional) {
		this.lstProfissional = lstProfissional;
	}


	public List<CadastroProfissional> getLstProfissionalFiltrada() {
		return lstProfissionalFiltrada;
	}


	public void setLstProfissionalFiltrada(List<CadastroProfissional> lstProfissionalFiltrada) {
		this.lstProfissionalFiltrada = lstProfissionalFiltrada;
	}


	public int getCodigoEmpregador() {
		return codigoEmpregador;
	}




	public void setCodigoEmpregador(int codigoEmpregador) {
		this.codigoEmpregador = codigoEmpregador;
	}




	public List<Servicos> getLstServicosFiltrado() {
		return lstServicosFiltrado;
	}




	public void setLstServicosFiltrado(List<Servicos> lstServicosFiltrado) {
		this.lstServicosFiltrado = lstServicosFiltrado;
	}




	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public String getCandidatos() {
		return candidatos;
	}


	public void setCandidatos(String candidatos) {
		this.candidatos = candidatos;
	}


	public List<Servicos> getLstServicos() {
		return lstServicos;
	}


	public void setLstServicos(List<Servicos> lstServicos) {
		this.lstServicos = lstServicos;
	}


	public List<Negociacao> getLstNegocicao() {
		return lstNegocicao;
	}


	public void setLstNegocicao(List<Negociacao> lstNegocicao) {
		this.lstNegocicao = lstNegocicao;
	}


	public List<Negociacao> getLstNegocicaoFiltrada() {
		return lstNegocicaoFiltrada;
	}


	public void setLstNegocicaoFiltrada(List<Negociacao> lstNegocicaoFiltrada) {
		this.lstNegocicaoFiltrada = lstNegocicaoFiltrada;
	}


	public static int getCodigoServico() {
		return codigoServico;
	}


	public static void setCodigoServico(int codigoServico) {
		VisualizarCandidatosView.codigoServico = codigoServico;
	}


	
}
