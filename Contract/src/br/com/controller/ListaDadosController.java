package br.com.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.model.CadastroEmpregador;
import br.com.model.CadastroProfissional;
import br.com.model.Servicos;

public class ListaDadosController {
	 static int codigo;

	static EntityManagerFactory manager = Persistence.createEntityManagerFactory("contract");
	
	
	public static List<CadastroProfissional> listprofissional(int codigo2) {
			
			EntityManager entitymanager = manager.createEntityManager();

			TypedQuery<CadastroProfissional> query = null;

			
		query = entitymanager.createQuery(
				"select d from CadastroProfissional d where d.codigo = :codigo", CadastroProfissional.class);
			query.setParameter("codigo", codigo2);

		return query.getResultList();

		}
	
	
	public static List<CadastroEmpregador> listempregador(int codigo3) {
		
		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<CadastroEmpregador> query = null;

		
	query = entitymanager.createQuery(
			"select d from CadastroEmpregador d where d.codigo = :codigo", CadastroEmpregador.class);
		query.setParameter("codigo", codigo3);

	return query.getResultList();

	}
	
public static List<Servicos> listaServicos() {
		
		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Servicos> query = null;

		
	query = entitymanager.createQuery(
			"select d from servicos d", Servicos.class);

	return query.getResultList();
	}
	

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public static EntityManagerFactory getManager() {
		return manager;
	}

	public static void setManager(EntityManagerFactory manager) {
		ListaDadosController.manager = manager;
	}
	
	
	
	
}
