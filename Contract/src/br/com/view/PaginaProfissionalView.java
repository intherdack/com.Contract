package br.com.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException; 
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.primefaces.model.DefaultStreamedContent;

import br.com.controller.DadosController;
import br.com.controller.SessaoController;
import br.com.controller.ValidaController;
import br.com.model.CadastroEmpregador;
import br.com.model.CadastroProfissional;
import br.com.model.Servicos;
import br.com.model.Sessao;


@ManagedBean(name = "profissionalView")
@ViewScoped
public class PaginaProfissionalView {
	public List<CadastroProfissional> lista;
	
	public List<Servicos> servicos ;
	public List<Servicos> todosServicos ;
	private List<CadastroEmpregador> listaEmpregador;
	private int codigo;
	public List<Servicos> servicosSelect ;

	private String apelido;
	private String nota;
	private String avaliacao;
	private String estado;
	private String cidade;
	private String aux;
	private String aux2;
	static int inicio;
	private String exibeIMG;
	//private List vagas;
	private DefaultStreamedContent imagem;
	public String vazio;
	public String semServ;
	
	
	

public void Pesquisa() throws IOException {
	

		servicos = new ArrayList();
		todosServicos = new ArrayList();
		servicosSelect  = new ArrayList();
		
		new SessaoController();
		List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

		if (aux.isEmpty() == false) {

			if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {

				this.codigo = aux.get(aux.size() - 1).getIdLogin();

			}
		this.inicio = this.codigo;
		this.lista = DadosController.listprofissional(codigo);
		

		try {
			FileInputStream fileInputStream = new FileInputStream("C:\\Users\\SERVER\\eclipse-workspace\\Contract\\WebContent\\profile\\"+ this.lista.get(0).getEmail() + "\\" + this.lista.get(0).getEmail() + ".jpg");  
			setImagem(new DefaultStreamedContent(fileInputStream, "image/jpeg")); 
			}catch(FileNotFoundException e ) {
				
				FileInputStream fileInputStream1 = new FileInputStream("C:\\Users\\SERVER\\eclipse-workspace\\Contract\\WebContent\\img\\perfil.jpg");  
				setImagem(new DefaultStreamedContent(fileInputStream1, "image/jpeg")); 
				
				//C:\\Users\\WCKD\\Documents\\Contract

				
			}

		this.apelido = this.lista.get(0).getApelido();
		this.aux2 = this.lista.get(0).getAvaliacoes();
		this.aux = this.lista.get(0).getNota();
		
		int x = Integer.parseInt(this.aux2);
		
		if(x <= 10) {
			this.nota = "Sua Pontuação: Nivel 1";
		}
		else {
			this.nota = "Sua Pontuação: " + this.aux;
		}
		this.avaliacao = aux2 + " Avaliações";	
		this.estado = "Estado - " + (this.lista.get(0).getEstado());
		this.cidade = "Cidade - " + (this.lista.get(0).getCidade());
		
		
		List<Servicos> dadosList = DadosController.listaServicos();
		
		
		//int empregador = dadosList.get(0).getCodigoEmp();

		
		for (int i = 0; i < dadosList.size(); i++)
        {
			if(dadosList.get(i).getStatus().equals("aberto")) {
				
			//================================ Puxa todas as vagas ===========================================
				todosServicos.add(dadosList.get(i));
			//================================================================================================
				
				if(this.lista.get(0).getEstado().equals(dadosList.get(i).getUf())) {
					
						if(this.lista.get(0).getCidade().equals(dadosList.get(i).getCidade())) {
					
							if(this.lista.get(0).getBaba().equals(dadosList.get(i).getSelecionado()) && this.lista.get(0).getBaba().equals("baba")) {
								this.servicos.add(dadosList.get(i));
							}
							if(this.lista.get(0).getBartender().equals(dadosList.get(i).getSelecionado()) && this.lista.get(0).getBartender().equals("bartender")) {
								this.servicos.add(dadosList.get(i));
							}
							if(this.lista.get(0).getChurrasqueiro().equals(dadosList.get(i).getSelecionado()) && this.lista.get(0).getChurrasqueiro().equals("churrasqueiro")) {
								this.servicos.add(dadosList.get(i));
							}
							if(this.lista.get(0).getConfeteiro().equals(dadosList.get(i).getSelecionado()) && this.lista.get(0).getConfeteiro().equals("confeteiro")) {
								this.servicos.add(dadosList.get(i));
							}
							if(this.lista.get(0).getContabeis().equals(dadosList.get(i).getSelecionado()) && this.lista.get(0).getContabeis().equals("contabeis")) {
								this.servicos.add(dadosList.get(i));
							}
							if(this.lista.get(0).getCozinheiro().equals(dadosList.get(i).getSelecionado()) && this.lista.get(0).getCozinheiro().equals("cozinheiro")) {
								this.servicos.add(dadosList.get(i));
							}
							if(this.lista.get(0).getFaxineiro().equals(dadosList.get(i).getSelecionado()) && this.lista.get(0).getFaxineiro().equals("faxineiro")) {
								this.servicos.add(dadosList.get(i));
							}
							if(this.lista.get(0).getFotografias().equals(dadosList.get(i).getSelecionado()) && this.lista.get(0).getFotografias().equals("fotografias")) {
								this.servicos.add(dadosList.get(i));
							}
							if(this.lista.get(0).getGarcom().equals(dadosList.get(i).getSelecionado()) && this.lista.get(0).getGarcom().equals("garcom")) {
								this.servicos.add(dadosList.get(i));
							}
							if(this.lista.get(0).getLimpeza().equals(dadosList.get(i).getSelecionado()) && this.lista.get(0).getLimpeza().equals("limpeza")) {
								this.servicos.add(dadosList.get(i));
							}
							
							if(this.lista.get(0).getManicure().equals(dadosList.get(i).getSelecionado()) && this.lista.get(0).getManicure().equals("manicure")) {
								this.servicos.add(dadosList.get(i));
							}
							if(this.lista.get(0).getMaquiagem().equals(dadosList.get(i).getSelecionado()) && this.lista.get(0).getMaquiagem().equals("maquiagem")) {
								this.servicos.add(dadosList.get(i));
							}
							if(this.lista.get(0).getMarido().equals(dadosList.get(i).getSelecionado()) && this.lista.get(0).getMarido().equals("marido")) {
								this.servicos.add(dadosList.get(i));
							}
							if(this.lista.get(0).getMarketing().equals(dadosList.get(i).getSelecionado()) && this.lista.get(0).getMarketing().equals("marketing")) {
								this.servicos.add(dadosList.get(i));
							}
							if(this.lista.get(0).getMotorista().equals(dadosList.get(i).getSelecionado()) && this.lista.get(0).getMotorista().equals("motorista")) {
								this.servicos.add(dadosList.get(i));
							}
						}
				}
			}
			
       }
		if(this.servicos.isEmpty()) {
			this.vazio = "Não temos vagas cadastradas em "+this.lista.get(0).getCidade() +" - "+this.lista.get(0).getEstado()+" neste momento!"; 
		}
		if(this.todosServicos.isEmpty()) {
			this.semServ = "Não temos vagas disponível neste momento!"; 
		}
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			ValidaController.addMessage("Sessão Expirada!!!");
			context.getExternalContext().redirect("login.xhtml");
		}
		
		
		
	}







public void bntEmpregador() throws IOException {
	List<Servicos> dadosList = DadosController.listaServicos();
	int empregador = dadosList.get(0).getCodigoEmp();
	this.listaEmpregador = DadosController.listempregador(empregador);
	
}







public List<Servicos> getTodosServicos() {
	return todosServicos;
}







public List<Servicos> getServicosSelect() {
	return servicosSelect;
}







public void setServicosSelect(List<Servicos> servicosSelect) {
	this.servicosSelect = servicosSelect;
}







public void setTodosServicos(List<Servicos> todosServicos) {
	this.todosServicos = todosServicos;
}







public static int getInicio() {
	return inicio;
}

public static void setInicio(int inicio) {
	PaginaProfissionalView.inicio = inicio;
}

public String getAux2() {
	return aux2;
}


public void setAux2(String aux2) {
	this.aux2 = aux2;
}


public String getAux() {
	return aux;
}


public void setAux(String aux) {
	this.aux = aux;
}


public List<CadastroProfissional> getLista() {
	return lista;
}


public void setLista(List<CadastroProfissional> lista) {
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


public List<Servicos> getServicos() {
	return servicos;
}

public void setServicos(List<Servicos> servicos) {
	this.servicos = servicos;
}









public String getExibeIMG() {
	return exibeIMG;
}









public void setExibeIMG(String exibeIMG) {
	this.exibeIMG = exibeIMG;
}

public List<CadastroEmpregador> getListaEmpregador() {
	return listaEmpregador;
}

public void setListaEmpregador(List<CadastroEmpregador> listaEmpregador) {
	this.listaEmpregador = listaEmpregador;
}







public DefaultStreamedContent getImagem() {
	return imagem;
}







public void setImagem(DefaultStreamedContent imagem) {
	this.imagem = imagem;
}







public String getVazio() {
	return vazio;
}







public void setVazio(String vazio) {
	this.vazio = vazio;
}







public String getSemServ() {
	return semServ;
}







public void setSemServ(String semServ) {
	this.semServ = semServ;
}














}
