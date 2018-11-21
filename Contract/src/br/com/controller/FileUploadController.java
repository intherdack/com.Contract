package br.com.controller;

import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import br.com.controller.Base64ManagedBean;
import br.com.model.CadastroEmpregador;
import br.com.model.CadastroProfissional;
import br.com.model.ImagemBase64;
import br.com.model.ImagemBase64_Empregador;
import br.com.model.Sessao;
import br.com.view.LogarView; 


@ManagedBean
@ViewScoped
public class FileUploadController {
     
    static UploadedFile file;
    private int codigo;
    private String auxImg;
	public BufferedImage file1;
	private List<ImagemBase64> listaIMG;
	private List<ImagemBase64_Empregador> listaIMGEmpregador;
	private String auxImgNome;
	private String exibeIMG;
	public List<CadastroProfissional> lista;
	public List<CadastroEmpregador> lista1;
	
	
 
 

	public void uploadProfissionalIMG() throws Exception {
		
		int auxcod = 0;
		
		new SessaoController();
		List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

		if (aux.isEmpty() == false) {

			if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {

				auxcod = aux.get(aux.size() - 1).getIdLogin();

			}
    	
    	this.setCodigo(auxcod);
    	Object o = "image/jpeg".toString();
    	if(file.getContentType().equals(o)){
    	
    	new Base64ManagedBean().salvar(file, codigo);	
    	
    	
    	/* --------------------------------------------------------------------*/
    	new DadosController();
		
		this.lista = DadosController.listprofissional(codigo);
		

		listaIMG = DadosController.buscaIMG(this.codigo);
		
		
		
		int count = 0;
        for (int i = 0; i < listaIMG.size(); i++)
        {
            
            count++;
        }
		
		
        count = count -1;
				auxImg = listaIMG.get(count).getB64();
					
		
		this.file1 =  Base64ManagedBean.decodeToImage(auxImg);
		
		// powered by Brilhador hahahah _/\_
		ValidaController.SalvaIMG(this.lista.get(0).getEmail(), this.file1);
		
		setExibeIMG("/profile/" + this.lista.get(0).getEmail() + "/" + this.lista.get(0).getEmail() + ".jpg");
		/* ---------------------------------------------------------------------------------------*/
		
	
    	
        if(file != null) {
            
           
            
            FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			
			ValidaController.addMessage("Sucesso" + file.getFileName() + " Foi enviada !!.");
			
			
			context.getExternalContext().redirect("paginaprofissional.xhtml");
        }
    	}else {
    		ValidaController.addMessage("Não é um arquivo de Imagem Válido, apenas arquivos .jpeg");
    		FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
    		context.getExternalContext().redirect("paginaprofissionalupload.xhtml");
    	}
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			ValidaController.addMessage("Sessão Expirada!!!");
			context.getExternalContext().redirect("login.xhtml");
		}
    }
	
public void uploadEmpregadorIMG() throws Exception {
	int auxcod2 = 0;

	new SessaoController();
	List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

	if (aux.isEmpty() == false) {

		if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {

			auxcod2 = aux.get(aux.size() - 1).getIdLogin();

		}
    	this.setCodigo(auxcod2);
    	
    	System.out.println(file.getContentType());
    	Object o = "image/jpeg".toString();
    	if(file.getContentType().equals(o)){
    	
    	
    	new Base64ManagedBean().salvarEmpregador(file, codigo);	
    	
    	
    	/* --------------------------------------------------------------------*/
    	new DadosController();
		
		this.lista1 = DadosController.listempregador(codigo);
		

		listaIMGEmpregador = DadosController.buscaIMGEmpregador(this.codigo);
		
		
		
		int count = 0;
        for (int i = 0; i < listaIMGEmpregador.size(); i++)
        {
            
            count++;
        }
		
		
        count = count -1;
				auxImg = listaIMGEmpregador.get(count).getB64();
					
		
		this.file1 =  Base64ManagedBean.decodeToImage(auxImg);
		
		// powered by Brilhador hahahah
		ValidaController.SalvaIMG(this.lista1.get(0).getEmail(), this.file1);
		
		setExibeIMG("/profile/" + this.lista1.get(0).getEmail() + "/" + this.lista1.get(0).getEmail() + ".jpg");
		
if(file != null) {
            
           
            
            FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			
			ValidaController.addMessage("Succesful" + file.getFileName() + " is uploaded.");
			
			
			context.getExternalContext().redirect("paginaempregador.xhtml");
        }
    	}else {
    		ValidaController.addMessage("Não é um arquivo de Imagem Válido, apenas arquivos .jpeg");
    		FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
    		context.getExternalContext().redirect("paginaempregadorupload.xhtml");
    	}
	} else {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		ValidaController.addMessage("Sessão Expirada!!!");
		context.getExternalContext().redirect("login.xhtml");
	}
}
    
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getAuxImgNome() {
		return auxImgNome;
	}

	public void setAuxImgNome(String auxImgNome) {
		this.auxImgNome = auxImgNome;
	}

	public String getExibeIMG() {
		return exibeIMG;
	}

	public void setExibeIMG(String exibeIMG) {
		this.exibeIMG = exibeIMG;
	}
	   public String getAuxImg() {
			return auxImg;
		}

		public void setAuxImg(String auxImg) {
			this.auxImg = auxImg;
		}

		public BufferedImage getFile1() {
			return file1;
		}

		public void setFile1(BufferedImage file1) {
			this.file1 = file1;
		}

		public List<ImagemBase64> getListaIMG() {
			return listaIMG;
		}

		public void setListaIMG(List<ImagemBase64> listaIMG) {
			this.listaIMG = listaIMG;
		}

		public List<CadastroProfissional> getLista() {
			return lista;
		}

		public void setLista(List<CadastroProfissional> lista) {
			this.lista = lista;
		}

		public List<ImagemBase64_Empregador> getListaIMGEmpregador() {
			return listaIMGEmpregador;
		}

		public void setListaIMGEmpregador(List<ImagemBase64_Empregador> listaIMGEmpregador) {
			this.listaIMGEmpregador = listaIMGEmpregador;
		}

		
}
