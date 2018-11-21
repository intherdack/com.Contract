package br.com.controller;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.model.CadastroEmpregador;
import br.com.model.CadastroProfissional;
import br.com.model.Chat;
import br.com.model.ImagemBase64;
import br.com.model.ImagemBase64_Empregador;
import br.com.model.Negociacao;
import br.com.model.Servicos;


public class DadosController {
	 static int codigo;

	
	
	static EntityManagerFactory manager = Persistence.createEntityManagerFactory("contract");
	//persiste o obj no banco
	public void Salvar(CadastroProfissional obj) throws Exception {

		try {
			EntityManager entitymanager = manager.createEntityManager();
			entitymanager.getTransaction().begin();
			entitymanager.persist(obj);
			entitymanager.getTransaction().commit();
		} catch (Exception ex) {
			throw ex;
		}

	}
	
	
	public static void Salvar2(CadastroEmpregador obj) throws Exception {

		try {
			EntityManager entitymanager = manager.createEntityManager();
			entitymanager.getTransaction().begin();
			entitymanager.persist(obj);
			entitymanager.getTransaction().commit();
		} catch (Exception ex) {
			throw ex;
		}

	}
	
	public void save(ImagemBase64 obj) throws Exception {

		try {
			EntityManager entitymanager = manager.createEntityManager();
			entitymanager.getTransaction().begin();
			entitymanager.persist(obj);
			entitymanager.getTransaction().commit();
		} catch (Exception ex) {
			throw ex;
		}

	}
	
	public void saveEmpregador(ImagemBase64_Empregador obj) throws Exception {

		try {
			EntityManager entitymanager = manager.createEntityManager();
			entitymanager.getTransaction().begin();
			entitymanager.persist(obj);
			entitymanager.getTransaction().commit();
		} catch (Exception ex) {
			throw ex;
		}

	}
	
	
	
	//persiste o obj no banco
		public void SalvarServicos(Servicos obj) throws Exception {

			try {
				EntityManager entitymanager = manager.createEntityManager();
				entitymanager.getTransaction().begin();
				entitymanager.persist(obj);
				entitymanager.getTransaction().commit();
			} catch (Exception ex) {
				throw ex;
			}

		}
		
		public void SalvarN(Negociacao obj) throws Exception {

			try {
				EntityManager entitymanager = manager.createEntityManager();
				entitymanager.getTransaction().begin();
				entitymanager.persist(obj);
				entitymanager.getTransaction().commit();
			} catch (Exception ex) {
				throw ex;
			}

		}
		
		public static List<ImagemBase64> buscaIMG(int codigo) {
			
			EntityManager entitymanager = manager.createEntityManager();

			TypedQuery<ImagemBase64> query = null;
			

			query = entitymanager.createQuery(
					"select d from ImagemBase64 d where d.codigosecundario = :codigo", ImagemBase64.class);
			query.setParameter("codigo", codigo);
			
		return query.getResultList();

		}
		
public static List<ImagemBase64_Empregador> buscaIMGEmpregador(int codigo) {
			
			EntityManager entitymanager = manager.createEntityManager();

			TypedQuery<ImagemBase64_Empregador> query = null;
			

			query = entitymanager.createQuery(
					"select d from ImagemBase64_Empregador d where d.codigosecundario = :codigo", ImagemBase64_Empregador.class);
			query.setParameter("codigo", codigo);
			
		return query.getResultList();

		}
		
		public static void excluirIMG(int codigo) {
			
			EntityManager entityManager = manager.createEntityManager();

			// Inicia uma transação com o banco de dados.
		      entityManager.getTransaction().begin();
		      // Consulta a pessoa na base de dados através do seu ID.
		      ImagemBase64 ib64 = entityManager.find(ImagemBase64.class, codigo);
		     // System.out.println("Excluindo os dados de: " + pessoa.getNome());
		      // Remove a pessoa da base de dados.
		      entityManager.remove(ib64);
		      // Finaliza a transação.
		      entityManager.getTransaction().commit();
		  
		      entityManager.close();
		

		}


		public static List<Chat> buscaCHAT() {
			EntityManager entitymanager = manager.createEntityManager();

			TypedQuery<Chat> query = null;
			

			query = entitymanager.createQuery(
					"select d from Chat d ", Chat.class);
			return query.getResultList();
		}
		
		public void SalvarChat(Chat obj) throws Exception {

			try {
				EntityManager entitymanager = manager.createEntityManager();
				entitymanager.getTransaction().begin();
				entitymanager.persist(obj);
				entitymanager.getTransaction().commit();
			} catch (Exception ex) {
				throw ex;
			}

		}
		
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

		
// ===================================Puxa o serviço com o codigo ===========================================

		
	public static List<Servicos> listaServicosId(int codigoServico) {
		
		EntityManager entitymanager = manager.createEntityManager();

		TypedQuery<Servicos> query = null;

		
	query = entitymanager.createQuery(
			"select d from Servicos d where d.codigo = :codigo", Servicos.class);
		query.setParameter("codigo", codigoServico);

	return query.getResultList();

	}
	
	
//----------------------------------------------------------------------------------------------------------------------
	
	
// ===================================gera a list de todas as vagas publicadas ===========================================
	
public static List<Servicos> listaServicos() {
		
		EntityManager entitymanager = manager.createEntityManager();
		TypedQuery<Servicos> query = null;

	query = entitymanager.createQuery(
			"SELECT d FROM Servicos d", Servicos.class);

	return query.getResultList();
	}
	


//=============================== gera a list dos servicos publicados pelo empregador==============================================

public static List<Servicos> listaServicosPublicados(int codigoEmp) {
	
	EntityManager entitymanager = manager.createEntityManager();

	TypedQuery<Servicos> query = null;

	
query = entitymanager.createQuery(
		"select d from Servicos d where d.codigoEmp = :codigoEmp", Servicos.class);
	query.setParameter("codigoEmp", codigoEmp);

return query.getResultList();

}


//=============================== Gera a list de profissionais =======================================================

public static List<CadastroProfissional> listaProfissional() {
	
	EntityManager entitymanager = manager.createEntityManager();
	TypedQuery<CadastroProfissional> query = null;

query = entitymanager.createQuery(
		"SELECT d FROM CadastroProfissional d", CadastroProfissional.class);

return query.getResultList();
}


//===================================gera a list de todas solicitações de interesse na vaga ===========================================

public static List<Negociacao> listaNegociacoes() {
		
		EntityManager entitymanager = manager.createEntityManager();
		TypedQuery<Negociacao> query = null;

	query = entitymanager.createQuery(
			"SELECT d FROM Negociacao d", Negociacao.class);

	return query.getResultList();
	}
	















//==================================== Altera o cadatro de profissional =============================================

public static void AlterarProfissional(CadastroProfissional obj){

	EntityManager entitymanager = manager.createEntityManager();
	entitymanager.getTransaction().begin();
	entitymanager.merge(obj);
	entitymanager.getTransaction().commit();
	entitymanager.close();

}

//=====================================================================================================================


//==================================== Altera o cadatro de profissional =============================================

public static void AlterarEmpregador(CadastroEmpregador obj){

	EntityManager entitymanager = manager.createEntityManager();
	entitymanager.getTransaction().begin();
	entitymanager.merge(obj);
	entitymanager.getTransaction().commit();
	entitymanager.close();

}

//=====================================================================================================================

/*
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
*/
//==================================== Altera os dados de servico =============================================

public static void AlteraServico(Object dadosServ){

	EntityManager entitymanager = manager.createEntityManager();
	entitymanager.getTransaction().begin();
	entitymanager.merge(dadosServ);
	entitymanager.getTransaction().commit();
	entitymanager.close();

}

//=====================================================================================================================


public static List<Negociacao> listaNpro(int codigoProfissional) {
	
	EntityManager entitymanager = manager.createEntityManager();
	TypedQuery<Negociacao> query = null;

query = entitymanager.createQuery(
"select d from Negociacao d where d.codigoProfissional = :codigoProfissional", Negociacao.class);
query.setParameter("codigoProfissional", codigoProfissional);

return query.getResultList();
}




/*
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
*/

//==================================== Altera os dados de Negociacao =============================================

public static void AlteraNegociacao(Object negociacao){

	EntityManager entitymanager = manager.createEntityManager();
	entitymanager.getTransaction().begin();
	entitymanager.merge(negociacao);
	entitymanager.getTransaction().commit();
	entitymanager.close();

}

//=====================================================================================================================


//===================================gera a list das vagas publicadas e que ja tem interessados ===========================================

public static List<Negociacao> listaNegociacao(int codigoEmpregador) {
		
		EntityManager entitymanager = manager.createEntityManager();
		TypedQuery<Negociacao> query = null;

	query = entitymanager.createQuery(
	"select d from Negociacao d where d.codigoEmpregador = :codigoEmpregador", Negociacao.class);
	query.setParameter("codigoEmpregador", codigoEmpregador);
	
	return query.getResultList();
	}

//==========================================================================================================================


//===================================gera a list dos profissionais interessados na vaga ===========================================

public static List<Negociacao> listaP(int codigoServico) {
		
		EntityManager entitymanager = manager.createEntityManager();
		TypedQuery<Negociacao> query = null;

	query = entitymanager.createQuery(
	"select d from Negociacao d where d.codigoServico = :codigoServico", Negociacao.class);
	query.setParameter("codigoServico", codigoServico);
	
	return query.getResultList();
	}


//===================================gera a list das vagas publicadas e que ja tem interessados ===========================================

public static List<Negociacao> listaN(int codigoServico) {
		
		EntityManager entitymanager = manager.createEntityManager();
		TypedQuery<Negociacao> query = null;

	query = entitymanager.createQuery(
	"select d from Negociacao d where d.codigoServico = :codigoServico", Negociacao.class);
	query.setParameter("codigoServico", codigoServico);
	
	return query.getResultList();
	}
//----------------------------------------------------------------------------------------------------------------------


public static List<Negociacao> NegociacaoAprovado(int codigoEmpregador) {
	
	EntityManager entitymanager = manager.createEntityManager();

	TypedQuery<Negociacao> query = null;

	
query = entitymanager.createQuery(
		"select d from Negociacao d where d.codigoEmpregador = :codigoEmpregador", Negociacao.class);
	query.setParameter("codigoEmpregador", codigoEmpregador);

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

	
	
}
