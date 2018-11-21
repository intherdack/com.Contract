package br.com.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.model.CadastroEmpregador;
import br.com.model.CadastroProfissional;


public class LoginController {
	
	static EntityManagerFactory manager = Persistence.createEntityManagerFactory("contract");
	
//======================================== Gera a List Profissional =====================================================
	
public static List<CadastroProfissional> realizalogProfissional() {
		
		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<CadastroProfissional> query = null;
		

		query = entitymanager.createQuery(
				"select d from CadastroProfissional d", CadastroProfissional.class);
		
	return query.getResultList();

	}


//======================================== Gera a List Empregador =====================================================


public static List<CadastroEmpregador> realizalogEmpregador() {
	
	EntityManager entitymanager = manager.createEntityManager();

	TypedQuery<CadastroEmpregador> query = null;
	

	query = entitymanager.createQuery(
			"select d from CadastroEmpregador d", CadastroEmpregador.class);
	
return query.getResultList();

}


}