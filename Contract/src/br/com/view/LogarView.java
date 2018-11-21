
package br.com.view;

import java.io.IOException;
import java.util.Date;
import java.util.List; 
import javax.faces.context.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;


import br.com.controller.LoginController;
import br.com.controller.SessaoController;
import br.com.controller.ValidaController;
import br.com.model.CadastroEmpregador;
import br.com.model.CadastroProfissional;
import br.com.model.Sessao;



@ManagedBean(name = "loga")
@ViewScoped
public class LogarView {
	
	
	private String email;
	private String senha;
	private String senha2;
	private String email2;
	private String comparaSenha;
	private String comparaSenha2;
	private int codigo;
	private int codigo2;
	
	
	public List<CadastroProfissional> listaProfissional = null;
	public List<CadastroEmpregador> listaEmpregador = null;

	
	
	
	public void logarProfissional() throws Exception {
		
	
		new LoginController();
		this.listaProfissional = LoginController.realizalogProfissional();
			
				
				for (int i = 0; i < this.listaProfissional.size(); i++) {
					
				
					this.comparaSenha = this.listaProfissional.get(i).getSenha();
					String aux = this.listaProfissional.get(i).getEmail();
					String aux2 = ValidaController.sha(this.senha);
						
						if((this.email.equals(aux) == true) && (this.comparaSenha.equals(aux2) == true)){
							
							this.codigo = this.listaProfissional.get(i).getCodigo();
							//LogarView.codigoUsuario1 = this.codigo;
							
							Sessao s = new Sessao();
							Date data = new Date();
							
							s.setIdLogin(this.codigo);
							s.setData(data.toString());
							s.setPerfil(this.listaProfissional.get(i).getCategoria());
							s.setSessaoAtual(new SessaoController().sessaoAtual());
							
							new SessaoController().Salvar(s);
							
							
							
							FacesContext.getCurrentInstance().getExternalContext().redirect("paginaprofissional.xhtml");
							
						//HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
						//session.setAttribute("dados", this.nome);
							
						}
				}
			
				ValidaController.addMessage("usuário e/ou senha incorretas");
			
	}	
	
	
	public void logarEmpregador() throws Exception {
		
		new LoginController();
		this.listaEmpregador = LoginController.realizalogEmpregador();
			
		
				for (int i = 0; i < this.listaEmpregador.size(); i++) {
					
					this.comparaSenha2 = this.listaEmpregador.get(i).getSenha();
					String aux = this.listaEmpregador.get(i).getEmail();
					String aux2 = ValidaController.sha(this.senha2);
					
						
						if((this.email2.equals(aux) == true) && (this.comparaSenha2.equals(aux2) == true)){
							
							this.codigo2 = this.listaEmpregador.get(i).getCodigo();
							//this.codigoUsuario2 = this.codigo2;
							
							Sessao s = new Sessao();
							Date data = new Date();
							
							s.setIdLogin(this.codigo2);
							s.setData(data.toString());
							s.setPerfil(this.listaEmpregador.get(i).getCategoria());
							s.setSessaoAtual(new SessaoController().sessaoAtual());
							
							new SessaoController().Salvar(s);
							
							FacesContext.getCurrentInstance().getExternalContext().redirect("paginaempregador.xhtml");
							
					
						}
				}
				
				ValidaController.addMessage("usuário e/ou senha incorretas");
	}
	
	
	public void inicio() throws IOException {
	
		//codigoUsuario2 = PaginaEmpregadorView.getInicio();					
						
	FacesContext.getCurrentInstance().getExternalContext().redirect("paginaempregador.xhtml");
	
	}
	
	
	
	
	public int getCodigo2() {
		return codigo2;
	}


	public void setCodigo2(int codigo2) {
		this.codigo2 = codigo2;
	}


	public String getSenha2() {
		return senha2;
	}


	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}


	public String getEmail2() {
		return email2;
	}


	public void setEmail2(String email2) {
		this.email2 = email2;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getComparaSenha() {
		return comparaSenha;
	}

	public void setComparaSenha(String comparaSenha) {
		this.comparaSenha = comparaSenha;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public List<CadastroProfissional> getListaProfissional() {
		return listaProfissional;
	}

	public void setListaProfissional(List<CadastroProfissional> listaProfissional) {
		this.listaProfissional = listaProfissional;
	}

	public List<CadastroEmpregador> getListaEmpregador() {
		return listaEmpregador;
	}

	public void setListaEmpregador(List<CadastroEmpregador> listaEmpregador) {
		this.listaEmpregador = listaEmpregador;
	}



	
	
}
	
	