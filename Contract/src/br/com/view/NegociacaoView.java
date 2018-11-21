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
import br.com.model.Negociacao;
import br.com.model.Servicos;
import br.com.model.Sessao;

@ManagedBean(name = "negociacoes")
@ViewScoped
public class NegociacaoView {
	
	private int codigoEmpregador;
	private int codigoProfissional;
	private int codigoServico;
	private String fechadoProfissional = "";
	private String status = "aberto";
	private int codigoN;
	private int id;
	private String situacao = "aberto";
	private boolean valida = false;
	private int avaliacaoE;
	private int avaliacaoP;
	private String DescE;
	private String DescP;
	private String aprovadoProfissional;
	private String aprovadoempregador;
	private int count = 0;
	
	public List<Negociacao> negociacoes ;
	public List<Negociacao> negociacoesN ;
	public List<Negociacao> profissionais;
	public List<Negociacao> listServicos;
	public List<Negociacao> lstNeg;
	public List<Negociacao> lstAux;
	public List<Servicos> lstServ;
	public List<Servicos> lstFinal;
	public List<Negociacao> lstStatus;
	public List<Servicos>lstAlt;
	private List<CadastroEmpregador> emprega;
	public List<Servicos> lstS;
	
	
	public boolean Dados(int codigo1, int codigo2, int codigo3) throws Exception {
		
		new SessaoController();
		List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

		if (aux.isEmpty() == false) {

			if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {

				this.codigoProfissional = aux.get(aux.size() - 1).getIdLogin();

			}
		this.codigoProfissional = codigo3;
		this.codigoServico = codigo1;
		this.codigoEmpregador = codigo2;
		negociacoes = new ArrayList<Negociacao>();
		listServicos = new ArrayList<Negociacao>();
		
		
		negociacoes = DadosController.listaNegociacoes();
		
		
		for (int i = 0; i < negociacoes.size(); i++)
        {
			Object o = negociacoes.get(i).getCodigoProfissional();
			Object s = negociacoes.get(i).getCodigoServico();
			
			
			
			if(o.equals(this.codigoProfissional) && s.equals(this.codigoServico)) {
				//this.count = this.count + 1;
				//this.valida = true;
				ValidaController.addMessage("Esta solicitação já foi feita em um outro momento!");
				return false;
				
			}
        }
	
		
		
		
		if(this.valida == false) {
				
			if(negociacoes.isEmpty()) {
					
				
					
				
				
					listServicos = DadosController.listaP(this.codigoServico);
				
					Negociacao novo = new Negociacao();
					novo.setCodigoProfissional(this.codigoProfissional);
					novo.setCodigoServico(this.codigoServico);
					novo.setCodigoEmpregador(this.codigoEmpregador);
					
					DadosController Dados = new DadosController();
					novo.setFechadoProfissional("");
					novo.setStatus("Aguardando Empregador");
					novo.setSituacao("aberto");
					novo.setAvaliacaoE(0);
					novo.setDescE("");
					novo.setDescP("");
					novo.setAprovadoempregador("aberto");
					novo.setAprovadoProfissional("aberto");
					
					
					lstAlt = new ArrayList<Servicos>();
					
					DadosController serv = new DadosController();
					lstAlt = serv.listaServicosId(this.codigoServico);
					
					Servicos dados = new Servicos();
					
					
					dados.setCep(lstAlt.get(0).getCep());
					dados.setCidade(lstAlt.get(0).getCidade());
					dados.setCodigo(lstAlt.get(0).getCodigo());
					dados.setDescricao(lstAlt.get(0).getDescricao());
					dados.setDia_final(lstAlt.get(0).getDia_final());
					dados.setDia_inicial(lstAlt.get(0).getDia_inicial());
					dados.setHora_final(lstAlt.get(0).getHora_final());
					dados.setHora_inicial(lstAlt.get(0).getHora_inicial());
					dados.setNomeTrabalho(lstAlt.get(0).getNomeTrabalho());
					dados.setSelecionado(lstAlt.get(0).getSelecionado());
					dados.setStatus("solicitado");
					dados.setTipo(lstAlt.get(0).getTipo());
					dados.setUf(lstAlt.get(0).getUf());
					dados.setValor(lstAlt.get(0).getValor());
					dados.setCodigoEmp(lstAlt.get(0).getCodigoEmp());
					
					
					
					DadosController alt = new DadosController();
					alt.AlteraServico(dados);
					
		
					Dados.SalvarN(novo);
					FacesContext context = FacesContext.getCurrentInstance();
					context.getExternalContext().getFlash().setKeepMessages(true);
					
					ValidaController.addMessage("Solicitação enviada! Aguarde o empregador entrar em contato!");
					context.getExternalContext().redirect("paginaprofissional.xhtml");
				
					
		        	}
					else {
					
						Negociacao novo = new Negociacao();
						novo.setCodigoProfissional(this.codigoProfissional);
						novo.setCodigoServico(this.codigoServico);
						novo.setCodigoEmpregador(this.codigoEmpregador);
						
						DadosController Dados = new DadosController();
						novo.setFechadoProfissional("");
						novo.setStatus("Aguardando Empregador");
						novo.setAvaliacaoE(0);
						novo.setAvaliacaoP(0);
						novo.setDescE("");
						novo.setDescP("");
						novo.setSituacao("aberto");
						novo.setAprovadoempregador("aberto");
						novo.setAprovadoProfissional("aberto");
						
						lstAlt = new ArrayList<Servicos>();
						
						DadosController serv = new DadosController();
						lstAlt = serv.listaServicosId(this.codigoServico);
						
						Servicos dados = new Servicos();
						
						
						dados.setCep(lstAlt.get(0).getCep());
						dados.setCidade(lstAlt.get(0).getCidade());
						dados.setCodigo(lstAlt.get(0).getCodigo());
						dados.setDescricao(lstAlt.get(0).getDescricao());
						dados.setDia_final(lstAlt.get(0).getDia_final());
						dados.setDia_inicial(lstAlt.get(0).getDia_inicial());
						dados.setHora_final(lstAlt.get(0).getHora_final());
						dados.setHora_inicial(lstAlt.get(0).getHora_inicial());
						dados.setNomeTrabalho(lstAlt.get(0).getNomeTrabalho());
						dados.setSelecionado(lstAlt.get(0).getSelecionado());
						dados.setStatus("solicitado");
						dados.setTipo(lstAlt.get(0).getTipo());
						dados.setUf(lstAlt.get(0).getUf());
						dados.setValor(lstAlt.get(0).getValor());
						dados.setCodigoEmp(lstAlt.get(0).getCodigoEmp()); // alterado lukas
						
						
						DadosController alt = new DadosController();
						alt.AlteraServico(dados);
						
						
						
						
						Dados.SalvarN(novo);
						
						
						FacesContext context = FacesContext.getCurrentInstance();
						context.getExternalContext().getFlash().setKeepMessages(true);
						
						ValidaController.addMessage("Solicitação enviada! Aguarde o empregador entrar em contato!");
						context.getExternalContext().redirect("paginaprofissional.xhtml");
					}
			}
		
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			ValidaController.addMessage("Sessão Expirada!!!");
			context.getExternalContext().redirect("login.xhtml");
		}
		
		return valida;
	}

	
	
	/// aqui faz a aprovacao do profissional
	
	public void Aprovado(int codigoProf) throws Exception {
		this.codigoServico = VisualizarCandidatosView.getCodigoServico();
		this.codigoProfissional = codigoProf;
		this.status = "aprovado";
		
		Negociacao negociacao = new Negociacao();
		negociacoesN = new ArrayList<Negociacao>();
		DadosController lstN = new DadosController();
		negociacoesN = lstN.listaN(this.codigoServico);
		lstS = new ArrayList<Servicos>();
		
		
		
		profissionais = new ArrayList<Negociacao>();
		DadosController lstE = new DadosController();
		profissionais = lstE.listaP(this.codigoServico);
		
		
		//================================ altera serciço ==========================================
		
		DadosController serv = new DadosController();
		lstS = serv.listaServicosId(this.codigoServico);
		
		
		
		Servicos dados2 = new Servicos();
		
		dados2.setCep(lstS.get(0).getCep());
		dados2.setCidade(lstS.get(0).getCidade());
		dados2.setCodigo(lstS.get(0).getCodigo());
		dados2.setDescricao(lstS.get(0).getDescricao());
		dados2.setDia_final(lstS.get(0).getDia_final());
		dados2.setDia_inicial(lstS.get(0).getDia_inicial());
		dados2.setHora_final(lstS.get(0).getHora_final());
		dados2.setHora_inicial(lstS.get(0).getHora_inicial());
		dados2.setNomeTrabalho(lstS.get(0).getNomeTrabalho());
		dados2.setSelecionado(lstS.get(0).getSelecionado());
		dados2.setStatus("aprovado");
		dados2.setTipo(lstS.get(0).getTipo());
		dados2.setUf(lstS.get(0).getUf());
		dados2.setValor(lstS.get(0).getValor());
		dados2.setCodigoEmp(lstS.get(0).getCodigoEmp());
		
		DadosController alt = new DadosController();
		alt.AlteraServico(dados2);
		
		
		//=============================================================================================
		
		
		for (int i = 0; i < profissionais.size(); i++){
			
			Object o = profissionais.get(i).getCodigoProfissional();
			
			
			if(o.equals(this.codigoProfissional)) {
				
				this.codigoEmpregador = negociacoesN.get(0).getCodigoEmpregador();
				this.fechadoProfissional = Integer.toString(this.codigoProfissional);
				this.id = negociacoesN.get(0).getId();
				
				
				negociacao.setId(this.id);
				negociacao.setCodigoEmpregador(this.codigoEmpregador);
				negociacao.setCodigoProfissional(this.codigoProfissional);
				negociacao.setCodigoServico(this.codigoServico);
				negociacao.setFechadoProfissional(this.fechadoProfissional);
				negociacao.setStatus(this.status);
				negociacao.setSituacao("profissional");
				negociacao.setAvaliacaoE(0);
				negociacao.setAvaliacaoP(0);
				negociacao.setDescE("");
				negociacao.setAprovadoempregador("aberto");
				negociacao.setAprovadoProfissional("aberto");
				
				
				DadosController Dados = new DadosController();
				Dados.AlteraNegociacao(negociacao);
				
				
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().getFlash().setKeepMessages(true);
				ValidaController.addMessage("Aprovação efetuada com sucesso!");
				context.getExternalContext().redirect("paginaempregador.xhtml");
				
			}else {
				
				
				//========================================================================================================
				//                     Caso os codigo nao baterem o mesmo sera atribuido como nao aprovado
				
				
				this.id = profissionais.get(i).getId();
				this.codigoEmpregador = profissionais.get(i).getCodigoEmpregador();
				this.codigoProfissional = profissionais.get(i).getCodigoProfissional();
				this.codigoServico = profissionais.get(i).getCodigoServico();
				this.fechadoProfissional = "";
				this.status = "recusado";
				
				
				
				
				negociacao.setId(this.id);
				negociacao.setCodigoEmpregador(this.codigoEmpregador);
				negociacao.setCodigoProfissional(this.codigoProfissional);
				negociacao.setCodigoServico(this.codigoServico);
				negociacao.setFechadoProfissional(this.fechadoProfissional);
				negociacao.setStatus(this.status);
				negociacao.setSituacao(this.situacao);
				negociacao.setAvaliacaoE(0);
				negociacao.setAvaliacaoP(0);
				negociacao.setDescE("");
				negociacao.setDescP("");
				negociacao.setAprovadoempregador("aberto");
				negociacao.setAprovadoProfissional("aberto");
				
				DadosController Dados = new DadosController();
				Dados.AlteraNegociacao(negociacao);
				
			}
			
		}
		
		
		
	}
	
public void Status() throws IOException {
		
		new SessaoController();
		List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

		if (aux.isEmpty() == false) {

			if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {

				this.codigoProfissional = aux.get(aux.size() - 1).getIdLogin();
				
			}
			
		}
			
		lstNeg = new ArrayList<Negociacao>();
		lstServ = new ArrayList<Servicos>();
		lstFinal = new ArrayList<Servicos>();
		lstAux = new ArrayList<Negociacao>();
		lstStatus = new ArrayList<Negociacao>();
		emprega = new ArrayList<CadastroEmpregador>();
		
			DadosController d = new DadosController();
			lstNeg = d.listaNpro(this.codigoProfissional);
			
			for (int i = 0; i < lstNeg.size(); i++){
				
				int cdServico = lstNeg.get(i).getCodigoServico();
				
				DadosController s = new DadosController();
				lstServ = s.listaServicosId(cdServico);
				
				lstServ.get(0).setStatus(lstNeg.get(i).getStatus());
				
				
				emprega.addAll(new DadosController().listempregador(lstServ.get(0).getCodigoEmp()));
			}
	}
			
	
	public void Recusado(int codigoProf) throws Exception {
		this.codigoServico = VisualizarCandidatosView.getCodigoServico();
		this.codigoProfissional = codigoProf;
		this.status = "recusado";
		
		
		Negociacao negociacao = new Negociacao();
		negociacoesN = new ArrayList<Negociacao>();
		DadosController lstN = new DadosController();
		negociacoesN = lstN.listaN(this.codigoServico);
		
	
		
		this.codigoEmpregador = negociacoesN.get(0).getCodigoEmpregador();
		this.codigoServico = negociacoesN.get(0).getCodigoServico();
		this.fechadoProfissional = negociacoesN.get(0).getFechadoProfissional();
		this.id = negociacoesN.get(0).getId();
		
		negociacao.setId(this.id);
		negociacao.setCodigoEmpregador(this.codigoEmpregador);
		negociacao.setCodigoProfissional(this.codigoProfissional);
		negociacao.setCodigoServico(this.codigoServico);
		negociacao.setFechadoProfissional(this.fechadoProfissional = "");
		negociacao.setStatus(this.status);
		negociacao.setAvaliacaoE(0);
		negociacao.setAvaliacaoP(0);
		negociacao.setDescE("");
		negociacao.setDescP("");
		negociacao.setAprovadoempregador("aberto");
		negociacao.setAprovadoProfissional("aberto");
		negociacao.setSituacao("aberto");
		
		DadosController Dados = new DadosController();
		Dados.AlteraNegociacao(negociacao);
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		ValidaController.addMessage("Recusado com sucesso!");
		context.getExternalContext().redirect("paginaempregador.xhtml");
		
		//ValidaController.addMessage("Recusado com sucesso!");
		
		
	}
	
	



	public List<Negociacao> getLstStatus() {
		return lstStatus;
	}



	public void setLstStatus(List<Negociacao> lstStatus) {
		this.lstStatus = lstStatus;
	}



	public List<Negociacao> getLstAux() {
		return lstAux;
	}



	public void setLstAux(List<Negociacao> lstAux) {
		this.lstAux = lstAux;
	}



	public List<Servicos> getLstFinal() {
		return lstFinal;
	}



	public void setLstFinal(List<Servicos> lstFinal) {
		this.lstFinal = lstFinal;
	}



	public List<Negociacao> getLstNeg() {
		return lstNeg;
	}



	public void setLstNeg(List<Negociacao> lstNeg) {
		this.lstNeg = lstNeg;
	}



	public List<Servicos> getLstServ() {
		return lstServ;
	}



	public void setLstServ(List<Servicos> lstServ) {
		this.lstServ = lstServ;
	}



	public int getAvaliacaoP() {
		return avaliacaoP;
	}


	public void setAvaliacaoP(int avaliacaoP) {
		this.avaliacaoP = avaliacaoP;
	}


	public String getDescP() {
		return DescP;
	}


	public void setDescP(String descP) {
		DescP = descP;
	}


	public String getAprovadoProfissional() {
		return aprovadoProfissional;
	}


	public void setAprovadoProfissional(String aprovadoProfissional) {
		this.aprovadoProfissional = aprovadoProfissional;
	}


	public String getAprovadoempregador() {
		return aprovadoempregador;
	}


	public void setAprovadoempregador(String aprovadoempregador) {
		this.aprovadoempregador = aprovadoempregador;
	}


	public String getSituacao() {
		return situacao;
	}


	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}


	public int getAvaliacaoE() {
		return avaliacaoE;
	}


	public void setAvaliacaoE(int avaliacaoE) {
		this.avaliacaoE = avaliacaoE;
	}



	public String getDescE() {
		return DescE;
	}


	public void setDescE(String descE) {
		DescE = descE;
	}


	public boolean isValida() {
		return valida;
	}


	public void setValida(boolean valida) {
		this.valida = valida;
	}


	public List<Negociacao> getProfissionais() {
		return profissionais;
	}


	public void setProfissionais(List<Negociacao> profissionais) {
		this.profissionais = profissionais;
	}


	public List<Negociacao> getListServicos() {
		return listServicos;
	}


	public void setListServicos(List<Negociacao> listServicos) {
		this.listServicos = listServicos;
	}


	public int getCodigoEmpregador() {
		return codigoEmpregador;
	}

	public void setCodigoEmpregador(int codigoEmpregador) {
		this.codigoEmpregador = codigoEmpregador;
	}

	public int getCodigoProfissional() {
		return codigoProfissional;
	}

	public void setCodigoProfissional(int codigoProfissional) {
		this.codigoProfissional = codigoProfissional;
	}

	public int getCodigoServico() {
		return codigoServico;
	}

	public void setCodigoServico(int codigoServico) {
		this.codigoServico = codigoServico;
	}

	public String getFechadoProfissional() {
		return fechadoProfissional;
	}

	public void setFechadoProfissional(String fechadoProfissional) {
		this.fechadoProfissional = fechadoProfissional;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCodigoN() {
		return codigoN;
	}

	public void setCodigoN(int codigoN) {
		this.codigoN = codigoN;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Negociacao> getNegociacoes() {
		return negociacoes;
	}

	public void setNegociacoes(List<Negociacao> negociacoes) {
		this.negociacoes = negociacoes;
	}

	public List<Negociacao> getNegociacoesN() {
		return negociacoesN;
	}

	public void setNegociacoesN(List<Negociacao> negociacoesN) {
		this.negociacoesN = negociacoesN;
	}



	public List<CadastroEmpregador> getEmprega() {
		return emprega;
	}



	public void setEmprega(List<CadastroEmpregador> emprega) {
		this.emprega = emprega;
	}



	public List<Servicos> getLstS() {
		return lstS;
	}



	public void setLstS(List<Servicos> lstS) {
		this.lstS = lstS;
	}
	
	
	
	//======================================= get and set ============================================================================
	
	
	
}
