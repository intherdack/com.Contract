package br.com.view;

import java.io.IOException;
import java.io.Serializable;
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
import br.com.model.Servicos;
import br.com.model.Sessao;

@ManagedBean(name = "perfil")
@ViewScoped
public class PerfilView{
	/**
	 * 
	 */
	/*private static final long serialVersionUID = 1L;*/
	public static int codigoProfissional;
	private String nome;
	private String cpf;
	private String estado;
	private String cidade;
	private String telMovel;
	private String telFixo;
	private String Apelido;
	private String email;
	private String tipo;
	private String segunda;
	private String terca;
	private String quarta;
	private String quinta;
	private String sexta;
	private String sabado;
	private String domingo;
	private String descricao;
	private String nota;
	private String avaliacao;
	private String codigoEmp;
	static int codigoempregador;
	public List<CadastroProfissional> listProf;
	public List<CadastroEmpregador> listEmp;
	public List<Servicos> publicados;

	public void Profissional() throws IOException {
		listProf = new ArrayList<CadastroProfissional>();
		List<CadastroProfissional> listProf = DadosController.listprofissional(this.codigoProfissional);
		this.nome = listProf.get(0).getNome();
		this.Apelido = listProf.get(0).getApelido();
		this.cpf = listProf.get(0).getCpf();
		this.telFixo = listProf.get(0).getTelFixo();
		this.telMovel = listProf.get(0).getTelMovel();
		this.estado = listProf.get(0).getEstado();
		this.cidade = listProf.get(0).getCidade();
		this.email = listProf.get(0).getEmail();
		this.tipo = "Estou disponivel como " + listProf.get(0).getTipo() + ".";
		this.descricao = listProf.get(0).getDescricao();

		if (listProf.get(0).getSegunda().equals("Disponivel")) {

			String horaInicial = listProf.get(0).getHora_inicial_segunda();
			String horaFinal = listProf.get(0).getHora_final_segunda();
			this.segunda = "Estou disponivel nas segundas feiras, das " + horaInicial + " as " + horaFinal + " horas!";
		} else {
			this.segunda = "Infelizmente não estou disponivel as segundas feiras!";
		}

		if (listProf.get(0).getTerca().equals("Disponivel")) {

			String horaInicial = listProf.get(0).getHora_inicial_terca();
			String horaFinal = listProf.get(0).getHora_final_terca();
			this.terca = "Estou disponivel nas terças feiras, das " + horaInicial + " as " + horaFinal + " horas!";
		} else {
			this.terca = "Infelizmente não estou disponivel as terças feiras!";
		}

		if (listProf.get(0).getQuarta().equals("Disponivel")) {

			String horaInicial = listProf.get(0).getHora_inicial_quarta();
			String horaFinal = listProf.get(0).getHora_final_quarta();
			this.quarta = "Estou disponivel nas quartas feiras, das " + horaInicial + " as " + horaFinal + " horas!";
		} else {
			this.quarta = "Infelizmente não estou disponivel as quartas feiras!";
		}

		if (listProf.get(0).getQuinta().equals("Disponivel")) {

			String horaInicial = listProf.get(0).getHora_inicial_quinta();
			String horaFinal = listProf.get(0).getHora_final_quinta();
			this.quinta = "Estou disponivel nas quintas feiras, das " + horaInicial + " as " + horaFinal + " horas!";
		} else {
			this.quinta = "Infelizmente não estou disponivel as quintas feiras!";
		}

		if (listProf.get(0).getSexta().equals("Disponivel")) {

			String horaInicial = listProf.get(0).getHora_inicial_sexta();
			String horaFinal = listProf.get(0).getHora_final_sexta();
			this.sexta = "Estou disponivel nas sextas feiras, das " + horaInicial + " as " + horaFinal + " horas!";
		} else {
			this.sexta = "Infelizmente não estou disponivel as sextas feiras!";
		}

		if (listProf.get(0).getSabado().equals("Disponivel")) {

			String horaInicial = listProf.get(0).getHora_inicial_sabado();
			String horaFinal = listProf.get(0).getHora_final_sabado();
			this.sabado = "Estou disponivel aos sabados, das " + horaInicial + " as " + horaFinal + " horas!";
		} else {
			this.sabado = "Infelizmente não estou disponivel aos sabados!";
		}

		if (listProf.get(0).getDomingo().equals("Disponivel")) {

			String horaInicial = listProf.get(0).getHora_inicial_domingo();
			String horaFinal = listProf.get(0).getHora_final_domingo();
			this.domingo = "Estou disponivel aos domingos, das " + horaInicial + " as " + horaFinal + " horas!";
		} else {
			this.domingo = "Infelizmente não estou disponivel aos domingos!";
		}

		if (Integer.parseInt(listProf.get(0).getAvaliacoes()) <= 10) {
			this.avaliacao = "Nivel 1, no momento este profissional não possui avaliações suficientes para ser pontuado!";
		} else {
			this.nota = listProf.get(0).getAvaliacoes();
		}

	}

	public void Empregador() throws IOException {

		this.publicados = new ArrayList<Servicos>();

		PaginaEmpregadorView p = new PaginaEmpregadorView();

		this.publicados = DadosController.listaServicosPublicados(this.codigoempregador);

		listEmp = new ArrayList<CadastroEmpregador>();

		DadosController l = new DadosController();
		listEmp = l.listempregador(this.codigoempregador);

	}

	public void ProfissionalCodigo(int codigo) throws IOException {

		PerfilView.codigoProfissional = codigo;
		 Profissional();
		
	}

	public void EmpregadorCodigo(int codigo2) throws IOException {
		PerfilView.codigoempregador = codigo2;
		Empregador();
		
		/*FacesContext.getCurrentInstance().getExternalContext().redirect("");*/
	}

	/**********************************************************************************************************/

	public String getCodigoEmp() {
		return codigoEmp;
	}

	public void setCodigoEmp(String codigoEmp) {
		this.codigoEmp = codigoEmp;
	}

	public List<Servicos> getPublicados() {
		return publicados;
	}

	public void setPublicados(List<Servicos> publicados) {
		this.publicados = publicados;
	}

	public int getCodigoempregador() {
		return codigoempregador;
	}

	public void setCodigoempregador(int codigoempregador) {
		this.codigoempregador = codigoempregador;
	}

	public List<CadastroEmpregador> getListEmp() {
		return listEmp;
	}

	public void setListEmp(List<CadastroEmpregador> listEmp) {
		this.listEmp = listEmp;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTerca() {
		return terca;
	}

	public void setTerca(String terca) {
		this.terca = terca;
	}

	public String getQuarta() {
		return quarta;
	}

	public void setQuarta(String quarta) {
		this.quarta = quarta;
	}

	public String getQuinta() {
		return quinta;
	}

	public void setQuinta(String quinta) {
		this.quinta = quinta;
	}

	public String getSexta() {
		return sexta;
	}

	public void setSexta(String sexta) {
		this.sexta = sexta;
	}

	public String getSabado() {
		return sabado;
	}

	public void setSabado(String sabado) {
		this.sabado = sabado;
	}

	public String getDomingo() {
		return domingo;
	}

	public void setDomingo(String domingo) {
		this.domingo = domingo;
	}

	public String getSegunda() {
		return segunda;
	}

	public void setSegunda(String segunda) {
		this.segunda = segunda;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCodigoProfissional() {
		return codigoProfissional;
	}

	public void setCodigoProfissional(int codigoProfissional) {
		codigoProfissional = codigoProfissional;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public String getTelMovel() {
		return telMovel;
	}

	public void setTelMovel(String telMovel) {
		this.telMovel = telMovel;
	}

	public String getTelFixo() {
		return telFixo;
	}

	public void setTelFixo(String telFixo) {
		this.telFixo = telFixo;
	}

	public String getApelido() {
		return Apelido;
	}

	public void setApelido(String apelido) {
		Apelido = apelido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<CadastroProfissional> getListProf() {
		return listProf;
	}

	public void setListProf(List<CadastroProfissional> listProf) {
		this.listProf = listProf;
	}
}
