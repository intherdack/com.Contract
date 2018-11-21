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
import br.com.model.Servicos;
import br.com.model.Sessao;

@ManagedBean(name = "avaliacao")
@ViewScoped
public class AvaliacaoView {

	private int codigoEmpregador;
	private int codigoProfissional;
	private int nota;
	private int avaliacao;
	public List<CadastroProfissional> lstFiltrada;
	public List<CadastroProfissional> lstPro;
	public List<CadastroEmpregador> lstEmp;
	public List<Negociacao> lstSer;
	public List<Negociacao> lstNegociacao;
	public List<Negociacao> lstFinalServ;
	public List<Negociacao> lstNegFinal;
	public List<Servicos> lstServico;
	public List<Servicos> lstServicoFinal;
	public List<CadastroEmpregador> lstEmpregador;
	public List<Servicos>lstServico2;
	
	
	
	public String avaliacaoE;
	public String avaliacaoP;
	public String DescP;
	public String DescE;
	public int codigoServico;
	
	
	//================================= gera a list que o empregador tem para ele avaliar ======================
	public void empregador() throws IOException {
		
		new SessaoController();
		List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

		if (aux.isEmpty() == false) {

			if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {

				this.codigoEmpregador = aux.get(aux.size() - 1).getIdLogin();

			}
		
		////// trocar static /////////////////////////////////////
		
			//	this.codigoEmpregador = LogarView.getCodigoUsuario2();
		
		///////////////////////////////////////////////////////////
		
		
		
		lstFiltrada = new ArrayList<CadastroProfissional>();
		lstSer = new ArrayList<Negociacao>();
		lstServico =  new ArrayList<Servicos>();
		lstFinalServ = new ArrayList<Negociacao>();
		lstServicoFinal =  new ArrayList<Servicos>();
		
		
		DadosController lstServicos = new DadosController();
		lstSer = lstServicos.NegociacaoAprovado(this.codigoEmpregador);
		
		
		for (int i = 0; i < lstSer.size(); i++){
		
			if(lstSer.get(i).getStatus().equals("aprovado") && lstSer.get(i).getAprovadoempregador().equals("aberto")) {
				
				DadosController dadosPro = new DadosController();
				lstFiltrada = dadosPro.listprofissional(lstSer.get(i).getCodigoProfissional());
				
				DadosController dadosServ = new DadosController();
				lstServico = dadosServ.listaServicosId(lstSer.get(i).getCodigoServico());
				
				
			}
		}
			if(lstFiltrada.isEmpty()) {
				ValidaController.addMessage("Sem profissionais para avaliar!");
			}
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			ValidaController.addMessage("Sessão Expirada!!!");
			context.getExternalContext().redirect("login.xhtml");
		}
		
	}
	
	
//============================ Empregador avalia profissional ==============================================

public void Avaliar(int codigo) throws IOException {
		
		this.codigoServico = codigo;
		
		lstPro =  new ArrayList<CadastroProfissional>();
		
		DadosController linha = new DadosController();
		lstFinalServ = linha.listaN(this.codigoServico);
		
		Negociacao dados = new Negociacao();
		
		//dados.setAvaliacaoE(lstFinalServ.get(0).getAvaliacaoE());
		dados.setAvaliacaoP(lstFinalServ.get(0).getAvaliacaoP());
		dados.setCodigoEmpregador(lstFinalServ.get(0).getCodigoEmpregador());
		dados.setCodigoProfissional(lstFinalServ.get(0).getCodigoProfissional());
		dados.setCodigoServico(lstFinalServ.get(0).getCodigoServico());
		dados.setFechadoProfissional(lstFinalServ.get(0).getFechadoProfissional());
		dados.setId(lstFinalServ.get(0).getId());
		dados.setSituacao(lstFinalServ.get(0).getSituacao());
		dados.setStatus("finalizado");	
		dados.setDescE(this.DescE);
		dados.setDescP(lstFinalServ.get(0).getDescP());
		dados.setAprovadoempregador("finalizado");
		dados.setAprovadoProfissional(lstFinalServ.get(0).getAprovadoempregador());
		
		
		this.codigoProfissional = lstFinalServ.get(0).getCodigoProfissional();
		DadosController pro = new DadosController();
		lstPro = pro.listprofissional(this.codigoProfissional);
		
		//------------- calculo para nota e avaliacao --------------------------------------------------
		
		this.avaliacao = Integer.parseInt(lstPro.get(0).getAvaliacoes()) + 1;
		
		if(this.avaliacao > 10) {
			
			this.nota = (Integer.parseInt(lstPro.get(0).getNota()) + Integer.parseInt(this.avaliacaoE))/ this.avaliacao;
			
		}else {
			
			this.nota = Integer.parseInt(lstPro.get(0).getNota()) + Integer.parseInt(this.avaliacaoE);
		
		}
		
		dados.setAvaliacaoE(Integer.parseInt(this.avaliacaoE));

		System.out.println(this.avaliacaoE);
		
		//-------------------- atualizando nota e avaliacao do profissional e status do servico------------------------------
		
		
		CadastroProfissional dadosPro = new CadastroProfissional();
		
		dadosPro.setApelido(lstPro.get(0).getApelido());
		dadosPro.setBaba(lstPro.get(0).getBaba());
		dadosPro.setGarcom(lstPro.get(0).getGarcom());
		dadosPro.setBartender(lstPro.get(0).getBartender());
		dadosPro.setConfeteiro(lstPro.get(0).getConfeteiro());
		dadosPro.setContabeis(lstPro.get(0).getContabeis());
		dadosPro.setCozinheiro(lstPro.get(0).getCozinheiro());
		dadosPro.setChurrasqueiro(lstPro.get(0).getChurrasqueiro());
		dadosPro.setCategoria(lstPro.get(0).getCategoria());
		dadosPro.setCidade(lstPro.get(0).getCidade());
		dadosPro.setCodigo(lstPro.get(0).getCodigo());
		dadosPro.setConfeteiro(lstPro.get(0).getConfeteiro());
		dadosPro.setContabeis(lstPro.get(0).getContabeis());
		dadosPro.setCozinheiro(lstPro.get(0).getCozinheiro());
		dadosPro.setFaxineiro(lstPro.get(0).getFaxineiro());
		dadosPro.setFotografias(lstPro.get(0).getFotografias());
		dadosPro.setLimpeza(lstPro.get(0).getLimpeza());
		dadosPro.setManicure(lstPro.get(0).getManicure());
		dadosPro.setMaquiagem(lstPro.get(0).getMaquiagem());
		dadosPro.setMarido(lstPro.get(0).getMarido());
		dadosPro.setMarketing(lstPro.get(0).getMarketing());
		dadosPro.setMotorista(lstPro.get(0).getMotorista());
		dadosPro.setCpf(lstPro.get(0).getCpf());
		dadosPro.setDescricao(lstPro.get(0).getDescricao());
		dadosPro.setEmail(lstPro.get(0).getEmail());
		dadosPro.setEstado(lstPro.get(0).getEstado());
		dadosPro.setHora_final_domingo(lstPro.get(0).getHora_final_domingo());
		dadosPro.setHora_final_quarta(lstPro.get(0).getHora_final_quarta());
		dadosPro.setHora_final_quinta(lstPro.get(0).getHora_final_quinta());
		dadosPro.setHora_final_sabado(lstPro.get(0).getHora_final_sabado());
		dadosPro.setHora_final_segunda(lstPro.get(0).getHora_final_segunda());
		dadosPro.setHora_final_sexta(lstPro.get(0).getHora_final_sexta());
		dadosPro.setHora_final_terca(lstPro.get(0).getHora_final_terca());
		dadosPro.setHora_inicial_segunda(lstPro.get(0).getHora_inicial_segunda());
		dadosPro.setHora_inicial_terca(lstPro.get(0).getHora_inicial_terca());
		dadosPro.setHora_inicial_quarta(lstPro.get(0).getHora_inicial_quarta());
		dadosPro.setHora_inicial_quinta(lstPro.get(0).getHora_inicial_quinta());
		dadosPro.setHora_inicial_sexta(lstPro.get(0).getHora_inicial_sexta());
		dadosPro.setHora_inicial_sabado(lstPro.get(0).getHora_inicial_sabado());
		dadosPro.setHora_inicial_domingo(lstPro.get(0).getHora_inicial_domingo());
		dadosPro.setSegunda(lstPro.get(0).getSegunda());
		dadosPro.setTerca(lstPro.get(0).getTerca());
		dadosPro.setQuarta(lstPro.get(0).getQuarta());
		dadosPro.setQuinta(lstPro.get(0).getQuinta());
		dadosPro.setSexta(lstPro.get(0).getSexta());
		dadosPro.setSabado(lstPro.get(0).getSabado());
		dadosPro.setDomingo(lstPro.get(0).getDomingo());
		dadosPro.setSenha(lstPro.get(0).getSenha());
		dadosPro.setTelFixo(lstPro.get(0).getTelFixo());
		dadosPro.setTelMovel(lstPro.get(0).getTelMovel());
		dadosPro.setTipo(lstPro.get(0).getTipo());
		dadosPro.setNome(lstPro.get(0).getNome());
		dadosPro.setAvaliacoes(Integer.toString(this.avaliacao));
		dadosPro.setNota(Integer.toString(this.nota));
		
		DadosController pf = new DadosController();
		pf.AlterarProfissional(dadosPro);
		
		
		
		DadosController Dados = new DadosController();
		Dados.AlteraNegociacao(dados);
		
		
		
		DadosController serv = new DadosController();
		lstServicoFinal = serv.listaServicosId(lstFinalServ.get(0).getCodigoServico());
		
		
		Servicos dadosServ = new Servicos();
		
		dadosServ.setCep(lstServicoFinal.get(0).getCep());
		dadosServ.setCidade(lstServicoFinal.get(0).getCidade());
		dadosServ.setCodigo(lstServicoFinal.get(0).getCodigo());
		dadosServ.setCodigoEmp(lstServicoFinal.get(0).getCodigoEmp());
		dadosServ.setDescricao(lstServicoFinal.get(0).getDescricao());
		dadosServ.setDia_final(lstServicoFinal.get(0).getDia_final());
		dadosServ.setDia_inicial(lstServicoFinal.get(0).getDia_inicial());
		dadosServ.setHora_final(lstServicoFinal.get(0).getHora_final());
		dadosServ.setHora_inicial(lstServicoFinal.get(0).getHora_inicial());
		dadosServ.setNomeTrabalho(lstServicoFinal.get(0).getNomeTrabalho());
		dadosServ.setSelecionado(lstServicoFinal.get(0).getSelecionado());
		dadosServ.setStatus("Finalizado");
		dadosServ.setTipo(lstServicoFinal.get(0).getTipo());
		dadosServ.setUf(lstServicoFinal.get(0).getUf());
		dadosServ.setValor(lstServicoFinal.get(0).getValor());
		
		
		DadosController obj = new DadosController();
		obj.AlteraServico(dadosServ);
		
		
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		ValidaController.addMessage("Profissional avaliado com sucesso!");
		context.getExternalContext().redirect("paginaempregador.xhtml");
		
		
	}


	
	
	//gera a lista para o profissional avaliar
	
	public void profissional() throws IOException {
		
		
		
		new SessaoController();
		List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

		if (aux.isEmpty() == false) {

			if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {

				this.codigoProfissional = aux.get(aux.size() - 1).getIdLogin();

			}
		}
	
		lstNegociacao = new ArrayList<Negociacao>();
		lstNegFinal = new ArrayList<Negociacao>();
		lstEmpregador = new ArrayList<CadastroEmpregador>();
		lstServico2 = new ArrayList<Servicos>();
		
			
		DadosController dados = new DadosController();
		lstNegociacao = dados.listaNpro(this.codigoProfissional);
		
		
		for (int i = 0; i < lstNegociacao.size(); i++){
		
			if(lstNegociacao.get(i).getSituacao().equals("profissional") && lstNegociacao.get(i).getFechadoProfissional().equals(Integer.toString(this.codigoProfissional))) {
			
				lstNegFinal.add(lstNegociacao.get(i));
				
			}
		}
		
		for (int i = 0; i < lstNegFinal.size(); i++){
			
			DadosController dadosEmp = new DadosController();
			int codigo2 = lstNegFinal.get(i).getCodigoEmpregador();
			lstEmpregador = dadosEmp.listempregador(codigo2);
			
			DadosController dadosServico = new DadosController();
			lstServico2 = dadosServico.listaServicosId(lstNegFinal.get(i).getCodigoServico());
		}
		
			
	}
	

	//============================ Profissional avalia Empregador ==============================================

		public void AvaliarProfissional(int codigo) throws IOException {
			
			lstEmp = new ArrayList<CadastroEmpregador>();
		
			this.codigoServico = codigo;
			
			lstSer = new ArrayList<Negociacao>();
			
			DadosController linha = new DadosController();
			lstSer = linha.listaN(this.codigoServico);
			
			Negociacao dados = new Negociacao();
			
			dados.setAvaliacaoE(lstSer.get(0).getAvaliacaoE());
			//dados.setAvaliacaoP(lstSer.get(0).getAvaliacaoP());
			dados.setCodigoEmpregador(lstSer.get(0).getCodigoEmpregador());
			dados.setCodigoProfissional(lstSer.get(0).getCodigoProfissional());
			dados.setCodigoServico(lstSer.get(0).getCodigoServico());
			dados.setFechadoProfissional(lstSer.get(0).getFechadoProfissional());
			dados.setId(lstSer.get(0).getId());
			dados.setSituacao("finalizado");
			dados.setStatus(lstSer.get(0).getStatus());		
			dados.setDescP(this.DescP);
			dados.setDescE(lstSer.get(0).getDescE());
			dados.setAprovadoempregador(lstSer.get(0).getAprovadoempregador());
			dados.setAprovadoProfissional("finalizado");
			
			
			this.codigoEmpregador = lstSer.get(0).getCodigoEmpregador();
			DadosController emp = new DadosController();
			lstEmp = emp.listempregador(this.codigoEmpregador);
			
			
			//------------- calculo para nota e avaliacao --------------------------------------------------
			
			this.avaliacao = Integer.parseInt(lstEmp.get(0).getQtdAvaliacoes()+1);
			
			if(this.avaliacao > 10) {
				
				this.nota = (Integer.parseInt(lstEmp.get(0).getNota()) + Integer.parseInt(this.avaliacaoP))/ this.avaliacao;
				
			}else {
				
				this.nota = Integer.parseInt(lstEmp.get(0).getNota()) + Integer.parseInt(this.avaliacaoP);
			
			}
			
			dados.setAvaliacaoP(Integer.parseInt(this.avaliacaoP));

			System.out.println(this.avaliacaoP);
			
			//-------------------- atualizando nota e avaliacao do profissional e status do servico------------------------------
			
			CadastroEmpregador dadosEmp = new CadastroEmpregador();
			dadosEmp.setApelido(lstEmp.get(0).getApelido());
			dadosEmp.setCategoria(lstEmp.get(0).getCategoria());
			dadosEmp.setCidade(lstEmp.get(0).getCidade());
			dadosEmp.setCodigo(lstEmp.get(0).getCodigo());
			dadosEmp.setCpf(lstEmp.get(0).getCpf());
			dadosEmp.setDescricao(lstEmp.get(0).getDescricao());
			dadosEmp.setEmail(lstEmp.get(0).getEmail());
			dadosEmp.setEstado(lstEmp.get(0).getEstado());
			dadosEmp.setFoto(lstEmp.get(0).getFoto());
			dadosEmp.setNome(lstEmp.get(0).getNome());
			dadosEmp.setNota(Integer.toString(this.nota));
			dadosEmp.setQtdAvaliacoes(Integer.toString(this.avaliacao));
			dadosEmp.setSenha(lstEmp.get(0).getSenha());
			dadosEmp.setTelFixo(lstEmp.get(0).getTelFixo());
			dadosEmp.setTelMovel(lstEmp.get(0).getTelMovel());
			dadosEmp.setTotalPublicados(lstEmp.get(0).getTotalPublicados());
			
			
			DadosController pf = new DadosController();
			pf.AlterarEmpregador(dadosEmp);
			
			
			
			DadosController Dados = new DadosController();
			Dados.AlteraNegociacao(dados);
			
			
			
			
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			
			ValidaController.addMessage("Empregador avaliado com sucesso!");
			context.getExternalContext().redirect("paginaprofissional.xhtml");
			
			
		}




	public String getAvaliacaoP() {
			return avaliacaoP;
		}


		public void setAvaliacaoP(String avaliacaoP) {
			this.avaliacaoP = avaliacaoP;
		}


	public List<CadastroEmpregador> getLstEmp() {
			return lstEmp;
		}


		public void setLstEmp(List<CadastroEmpregador> lstEmp) {
			this.lstEmp = lstEmp;
		}


		public String getDescP() {
			return DescP;
		}


		public void setDescP(String descP) {
			DescP = descP;
		}


	public List<CadastroEmpregador> getLstEmpregador() {
		return lstEmpregador;
	}


	public void setLstEmpregador(List<CadastroEmpregador> lstEmpregador) {
		this.lstEmpregador = lstEmpregador;
	}


	public List<Servicos> getLstServico2() {
		return lstServico2;
	}


	public void setLstServico2(List<Servicos> lstServico2) {
		this.lstServico2 = lstServico2;
	}


	public List<Negociacao> getLstNegociacao() {
		return lstNegociacao;
	}


	public void setLstNegociacao(List<Negociacao> lstNegociacao) {
		this.lstNegociacao = lstNegociacao;
	}


	public List<Negociacao> getLstNegFinal() {
		return lstNegFinal;
	}


	public void setLstNegFinal(List<Negociacao> lstNegFinal) {
		this.lstNegFinal = lstNegFinal;
	}


	public List<Servicos> getLstServicoFinal() {
		return lstServicoFinal;
	}


	public void setLstServicoFinal(List<Servicos> lstServicoFinal) {
		this.lstServicoFinal = lstServicoFinal;
	}


	public int getNota() {
		return nota;
	}


	public void setNota(int nota) {
		this.nota = nota;
	}


	public int getAvaliacao() {
		return avaliacao;
	}


	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}


	public List<CadastroProfissional> getLstPro() {
		return lstPro;
	}


	public void setLstPro(List<CadastroProfissional> lstPro) {
		this.lstPro = lstPro;
	}




	public int getCodigoProfissional() {
		return codigoProfissional;
	}


	public void setCodigoProfissional(int codigoProfissional) {
		this.codigoProfissional = codigoProfissional;
	}


	public List<Negociacao> getLstFinalServ() {
		return lstFinalServ;
	}


	public void setLstFinalServ(List<Negociacao> lstFinalServ) {
		this.lstFinalServ = lstFinalServ;
	}


	public int getCodigoServico() {
		return codigoServico;
	}




	public void setCodigoServico(int codigoServico) {
		this.codigoServico = codigoServico;
	}




	public String getAvaliacaoE() {
		return avaliacaoE;
	}




	public void setAvaliacaoE(String avaliacaoE) {
		this.avaliacaoE = avaliacaoE;
	}




	public String getDescE() {
		return DescE;
	}




	public void setDescE(String descE) {
		DescE = descE;
	}




	public List<Servicos> getLstServico() {
		return lstServico;
	}



	public void setLstServico(List<Servicos> lstServico) {
		this.lstServico = lstServico;
	}



	public int getCodigoEmpregador() {
		return codigoEmpregador;
	}



	public void setCodigoEmpregador(int codigoEmpregador) {
		this.codigoEmpregador = codigoEmpregador;
	}



	public List<CadastroProfissional> getLstFiltrada() {
		return lstFiltrada;
	}



	public void setLstFiltrada(List<CadastroProfissional> lstFiltrada) {
		this.lstFiltrada = lstFiltrada;
	}



	public List<Negociacao> getLstSer() {
		return lstSer;
	}



	public void setLstSer(List<Negociacao> lstSer) {
		this.lstSer = lstSer;
	}



}
