package br.com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.model.CadastroProfissional;
import br.com.model.Sessao;
import br.com.view.PerfilView;

@ManagedBean(name = "pdf")
@ViewScoped
public class PdfUploadController {
	
	private UploadedFile file;
	private int aux10;
	public List<CadastroProfissional> lista;
	 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload(String login) throws Exception {
    	
        if(file != null) {
        	
        	
        	File dir = new File("C:\\Users\\SERVER\\eclipse-workspace\\Contract\\WebContent\\pdf\\" + login);
			if (dir.mkdir()) {
				System.out.println("Diretório criado com sucesso!");
			}
			file.write("C:\\Users\\SERVER\\eclipse-workspace\\Contract\\WebContent\\pdf\\" + login +"\\"+login+".pdf");
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
		
    }
     
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    private StreamedContent file1;
    
    public void FileDownloadView() throws IOException {    
    	List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

		if (aux.isEmpty() == false) {

			if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {

				this.aux10 = aux.get(aux.size() - 1).getIdLogin();

			}
	
		this.lista = DadosController.listprofissional(PerfilView.codigoProfissional);
    	
    	
		String login = this.lista.get(0).getEmail();
    	
		FileInputStream stream = new FileInputStream("C:\\Users\\SERVER\\eclipse-workspace\\Contract\\WebContent\\pdf\\" + login +"\\"+login+".pdf");
        file1 = (StreamedContent) stream;
        
		}
    }
 
    public StreamedContent getFile1() {
        return file1;
    }

	public int getAux10() {
		return aux10;
	}

	public void setAux10(int aux10) {
		this.aux10 = aux10;
	}

	public List<CadastroProfissional> getLista() {
		return lista;
	}

	public void setLista(List<CadastroProfissional> lista) {
		this.lista = lista;
	}
}


