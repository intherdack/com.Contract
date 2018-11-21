package br.com.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import br.com.model.Chat;
import br.com.model.ImagemBase64;
import br.com.model.Sessao;

@ManagedBean(name = "sessao")
@ViewScoped
public class SessaoController{



	static EntityManagerFactory manager = Persistence.createEntityManagerFactory("contract");

	// persiste o obj no banco
	public void Salvar(Sessao obj) throws Exception {

		try {
			EntityManager entitymanager = manager.createEntityManager();
			entitymanager.getTransaction().begin();
			entitymanager.persist(obj);
			entitymanager.getTransaction().commit();
		} catch (Exception ex) {
			throw ex;
		}

	}
	
	public String sessaoAtual() {
		
	
	FacesContext fc = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);	
	String teste = session.getId().toString();
	return teste;
	}
	
	public static List<Sessao> buscaSessao(String sessaoatual) throws IOException {
		List<Sessao> retorno = new ArrayList();;
		List<Sessao> base = new ArrayList();;
		
		EntityManager entitymanager = manager.createEntityManager();
		TypedQuery<Sessao> query = null;
		query = entitymanager.createQuery("select d from Sessao d", Sessao.class);
		 
		
		base = query.getResultList();
		
		
		
		for (int i = 0; i < base.size(); i++)
        {
			if(base.get(i).getSessaoAtual().equals(sessaoatual)) {
				
				retorno.add(base.get(i));
			}
        }
		
	
		
		
		return retorno;

	}
	
	public void encerraSessao() {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);	
		session.invalidate();
	
	}
	

	public String solicitaData(Date dataHoraAtual) {

		
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);

		return data;
	}

	public String solicitaHora(Date dataHoraAtual) {
		
		
		String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
		return hora;
	}

}
