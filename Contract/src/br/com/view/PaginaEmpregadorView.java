package br.com.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;

import br.com.controller.Base64ManagedBean;
import br.com.controller.DadosController;
import br.com.controller.SessaoController;
import br.com.controller.ValidaController;
import br.com.model.CadastroEmpregador;
import br.com.model.CadastroProfissional;
import br.com.model.Servicos;
import br.com.model.Sessao;


@ManagedBean(name = "empregadorView")
@ViewScoped
public class PaginaEmpregadorView {

public List<CadastroEmpregador> lista;
public List<CadastroProfissional> lstProfissinal;
public List<Servicos> publicados;	
public List<CadastroProfissional> lstPr;	
	private int codigo;
	private String apelido;
	private String nota;
	private String avaliacao;
	private String estado;
	private String cidade;
	private String aux;
	private String aux2;
	//static int inicio;
	private String cep;
	private int codProf;
	private String titulo;
	private String totalPublicacoes;
	private DefaultStreamedContent imagem;
	public String vazia;
	public String profissionais;

public void Pesquisa() throws IOException {
	
	publicados = new ArrayList<Servicos>();
	lstPr = new ArrayList<CadastroProfissional>();
	lstProfissinal = new ArrayList<CadastroProfissional>();
	
	new SessaoController();
	List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

	if (aux.isEmpty() == false) {

		if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {

			this.codigo = aux.get(aux.size() - 1).getIdLogin();

		}
	
		new DadosController();
		this.lista = DadosController.listempregador(codigo);
		
		this.apelido = this.lista.get(0).getApelido();
		this.aux2 = this.lista.get(0).getQtdAvaliacoes();
		this.aux = this.lista.get(0).getNota();
		
		try {
			FileInputStream fileInputStream1 = new FileInputStream("C:\\Users\\SERVER\\eclipse-workspace\\Contract\\WebContent\\profile\\"+ this.lista.get(0).getEmail() + "\\" + this.lista.get(0).getEmail() + ".jpg");  
			setImagem(new DefaultStreamedContent(fileInputStream1, "image/jpeg")); 
			}catch(FileNotFoundException e ) {
				
				FileInputStream fileInputStream1 = new FileInputStream("C:\\Users\\SERVER\\eclipse-workspace\\Contract\\WebContent\\img\\perfil.jpg");  
				setImagem(new DefaultStreamedContent(fileInputStream1, "image/jpeg")); 

				
			}
		
		int b = Integer.parseInt(this.aux2);
		
		if(b <= 10) {
			this.nota = "Sua Pontuação: Nivel 1";
		}
		else {
			this.nota = "Sua Pontuação: " + this.aux;
		}
		this.avaliacao = aux2 + " Avaliações";	
		this.estado = "Estado - " + (this.lista.get(0).getEstado());
		this.cidade = "Cidade - " + (this.lista.get(0).getCidade());
		//PaginaEmpregadorView.inicio = this.codigo;
		
		
		// ============================ Retorna na tela todas as suas publicacoes =============================================
		this.totalPublicacoes = "Total de publicações: "+this.lista.get(0).getTotalPublicados();
		
		
		int p = Integer.parseInt(this.lista.get(0).getTotalPublicados());
		
		if(this.totalPublicacoes.equals("0")) {
			
		}else {
		
			this.publicados = DadosController.listaServicosPublicados(this.codigo);
			if(this.totalPublicacoes.equals("0")) {
				
			
				this.titulo = "selecionamos alguns profissionais para você!";
			}
					
				
			//=====================================================================================================================
			
			//============================ Retorna todos os profissionais =========================================================
			
				List<CadastroProfissional> dadosList2 = DadosController.listaProfissional();
				this.lstProfissinal = dadosList2;
				
				if(this.lstProfissinal.isEmpty()) {
					this.profissionais = "Não temos profissionais dispooníveis neste momento!";
				
				}

				
			 
			
			int count = 0;
	        for (int i = 0; i < this.publicados.size(); i++)
	        {
	            
	            count++;
	        }
			
			if(count >0) {
	        count = count -1;
			}
					
			
			try {
		
						
				for (int i = 0; i < dadosList2.size(); i++)
			    {
					
					if(this.publicados.get(count).getSelecionado().equals(dadosList2.get(i).getBaba()) && this.publicados.get(count).getSelecionado().equals("baba")) {
						this.lstPr.add(dadosList2.get(i));
					}
					if(this.publicados.get(count).getSelecionado().equals(dadosList2.get(i).getBartender()) && this.publicados.get(count).getSelecionado().equals("bartender")) {
						this.lstPr.add(dadosList2.get(i));
					}
					if(this.publicados.get(count).getSelecionado().equals(dadosList2.get(i).getChurrasqueiro()) && this.publicados.get(count).getSelecionado().equals("churrasqueiro")) {
						this.lstPr.add(dadosList2.get(i));
					}
					if(this.publicados.get(count).getSelecionado().equals(dadosList2.get(i).getConfeteiro()) && this.publicados.get(count).getSelecionado().equals("confeteiro")) {
						this.lstPr.add(dadosList2.get(i));
					}
					if(this.publicados.get(count).getSelecionado().equals(dadosList2.get(i).getContabeis()) && this.publicados.get(count).getSelecionado().equals("contabeis")) {
						this.lstPr.add(dadosList2.get(i));
					}
					if(this.publicados.get(count).getSelecionado().equals(dadosList2.get(i).getCozinheiro()) && this.publicados.get(count).getSelecionado().equals("cozinheiro")) {
						this.lstPr.add(dadosList2.get(i));
					}
					if(this.publicados.get(count).getSelecionado().equals(dadosList2.get(i).getFaxineiro()) && this.publicados.get(count).getSelecionado().equals("faxineiro")) {
						this.lstPr.add(dadosList2.get(i));
					}
					if(this.publicados.get(count).getSelecionado().equals(dadosList2.get(i).getFotografias()) && this.publicados.get(count).getSelecionado().equals("fotografias")) {
						this.lstPr.add(dadosList2.get(i));
					}
					if(this.publicados.get(count).getSelecionado().equals(dadosList2.get(i).getGarcom()) && this.publicados.get(count).getSelecionado().equals("garcom")) {
						this.lstPr.add(dadosList2.get(i));
					}
					if(this.publicados.get(count).getSelecionado().equals(dadosList2.get(i).getLimpeza()) && this.publicados.get(count).getSelecionado().equals("limpeza")) {
						this.lstPr.add(dadosList2.get(i));
					}
					
					if(this.publicados.get(count).getSelecionado().equals(dadosList2.get(i).getManicure()) && this.publicados.get(count).getSelecionado().equals("manicure")) {
						this.lstPr.add(dadosList2.get(i));
					}
					if(this.publicados.get(count).getSelecionado().equals(dadosList2.get(i).getMaquiagem()) && this.publicados.get(count).getSelecionado().equals("maquiagem")) {
						this.lstPr.add(dadosList2.get(i));
					}
					if(this.publicados.get(count).getSelecionado().equals(dadosList2.get(i).getMarido()) && this.publicados.get(count).getSelecionado().equals("marido")) {
						this.lstPr.add(dadosList2.get(i));
					}
					if(this.publicados.get(count).getSelecionado().equals(dadosList2.get(i).getMarketing()) && this.publicados.get(count).getSelecionado().equals("marketing")) {
						this.lstPr.add(dadosList2.get(i));
					}
					if(this.publicados.get(count).getSelecionado().equals(dadosList2.get(i).getMotorista()) && this.publicados.get(count).getSelecionado().equals("motorista")) {
						this.lstPr.add(dadosList2.get(i));
					}
			    }
			
			
			}catch(java.lang.ArrayIndexOutOfBoundsException e){
				
			//	ValidaController.addMessage("Cadastre uma vaga de emprego");
				
				if(lstPr.isEmpty()) {
					this.vazia = "Não temos indicações de profissionais neste momento!";
				
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


//=====================================================================================================================
		



public String getTotalPublicacoes() {
	return totalPublicacoes;
}


public String getVazia() {
	return vazia;
}


public void setVazia(String vazia) {
	this.vazia = vazia;
}


public void setTotalPublicacoes(String totalPublicacoes) {
	this.totalPublicacoes = totalPublicacoes;
}


public String getTitulo() {
	return titulo;
}


public void setTitulo(String titulo) {
	this.titulo = titulo;
}


public List<CadastroProfissional> getLstProfissinal() {
	return lstProfissinal;
}


public void setLstProfissinal(List<CadastroProfissional> lstProfissinal) {
	this.lstProfissinal = lstProfissinal;
}




public List<CadastroProfissional> getLstPr() {
	return lstPr;
}


public void setLstPr(List<CadastroProfissional> lstPr) {
	this.lstPr = lstPr;
}


public List<Servicos> getPublicados() {
	return publicados;
}


public void setPublicados(List<Servicos> publicados) {
	this.publicados = publicados;
}

/*
public static int getInicio() {
	return inicio;
}


public static void setInicio(int inicio) {
	PaginaEmpregadorView.inicio = inicio;
}
*/

public List<CadastroEmpregador> getLista() {
	return lista;
}


public void setLista(List<CadastroEmpregador> lista) {
	this.lista = lista;
}


public int getCodigo() {
	return codigo;
}


public void setCodigo(int codigo) {
	this.codigo = codigo;
}


public String getApelido() {
	return apelido;
}


public void setApelido(String apelido) {
	this.apelido = apelido;
}


public String getNota() {
	return nota;
}


public void setNota(String nota) {
	this.nota = nota;
}


public String getAvaliacao() {
	return avaliacao;
}


public void setAvaliacao(String avaliacao) {
	this.avaliacao = avaliacao;
}


public String getEstado() {
	return estado;
}


public void setEstado(String estado) {
	this.estado = estado;
}


public String getCidade() {
	return cidade;
}


public void setCidade(String cidade) {
	this.cidade = cidade;
}


public String getAux() {
	return aux;
}


public void setAux(String aux) {
	this.aux = aux;
}


public String getAux2() {
	return aux2;
}


public void setAux2(String aux2) {
	this.aux2 = aux2;
}


public int getCodProf() {
	return codProf;
}


public void setCodProf(int codProf) {
	this.codProf = codProf;
}


public DefaultStreamedContent getImagem() {
	return imagem;
}


public void setImagem(DefaultStreamedContent defaultStreamedContent) {
	this.imagem = defaultStreamedContent;
}

public String getCep() {
	return cep;
}


public void setCep(String cep) {
	this.cep = cep;
}


public String getProfissionais() {
	return profissionais;
}


public void setProfissionais(String profissionais) {
	this.profissionais = profissionais;
}


}
