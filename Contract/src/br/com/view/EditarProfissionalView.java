package br.com.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.controller.DadosController;
import br.com.controller.SessaoController;
import br.com.controller.ValidaController;
import br.com.model.CadastroProfissional;
import br.com.model.Sessao;

@ManagedBean(name = "editaProfissional")
@ViewScoped
public class EditarProfissionalView {
	private int codigo;
	private String nome;
	private String Categoria = "Profissional";
	private String cpf;
	private String telFixo;
	private String telMovel;
	private String apelido;
	private String email;
	private String senha;
	private String descricao;
	private String tipo;
	private String avaliacoes = "0";
	private String nota;
	private String cidade;
	private String estado;
	private String segunda;
	private String hora_inicial_segunda = "00:00";
	private String hora_final_segunda = "00:00";
	private String terca;
	private String hora_inicial_terca = "00:00";
	private String hora_final_terca = "00:00";
	private String quarta;
	private String hora_inicial_quarta = "00:00";
	private String hora_final_quarta = "00:00";
	private String quinta;
	private String hora_inicial_quinta = "00:00";
	private String hora_final_quinta = "00:00";
	private String sexta;
	private String hora_inicial_sexta = "00:00";
	private String hora_final_sexta = "00:00";
	private String sabado;
	private String hora_inicial_sabado = "00:00";
	private String hora_final_sabado = "00:00";
	private String domingo;
	private String hora_inicial_domingo = "00:00";
	private String hora_final_domingo = "00:00";
	private String encodedBytes = null;
	private String file;
	private String resenha;
	private String marido = "";
	private String faxineiro = "";
	private String limpeza = "";
	private String garcom = "";
	private String bartender = "";
	private String cozinheiro ="";
	private String churrasqueiro = "";
	private String confeteiro = "";
	private String fotografias = "";
	private String manicure = "";
	private String maquiagem = "";
	private String baba = "";
	private String marketing = "";
	private String contabeis = "";
	private String motorista = "";
	private Boolean aux1 = false;
	private String cep;
	static List<CadastroProfissional> listaPro;
	private int inicio;
	CadastroProfissional novo = new CadastroProfissional();
	
	
	
public void bntCep() throws IOException {
		
		
		ValidaController c = new ValidaController();
		c.CepService(this.cep);
		this.cidade = c.getCidade();
		this.estado = c.getUf();
		this.listaPro.get(0).setCidade(this.cidade);
		this.listaPro.get(0).setEstado(this.estado);

	}
	

	public void BuscarDados() throws IOException {
		new SessaoController();
		List<Sessao> aux = SessaoController.buscaSessao(new SessaoController().sessaoAtual());

		if (aux.isEmpty() == false) {

			if (new SessaoController().sessaoAtual().equals(aux.get(aux.size() - 1).getSessaoAtual())) {

				this.inicio = aux.get(aux.size() - 1).getIdLogin();

			}
		
		this.listaPro  = new ArrayList<CadastroProfissional>();
		//this.inicio = LogarView.getCodigoUsuario1();
		
		EditarProfissionalView.listaPro = DadosController.listprofissional(this.inicio);
		this.cidade = this.listaPro.get(0).getCidade();
		this.estado = this.listaPro.get(0).getEstado();
		
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			ValidaController.addMessage("Sessão Expirada!!!");
			context.getExternalContext().redirect("login.xhtml");
		}
	
	}


	public boolean bntAlterarPro() throws IOException {
		

		
		//CadastroProfissional novo = new CadastroProfissional();
				
				this.segunda = listaPro.get(0).getSegunda();
				this.hora_inicial_segunda = this.listaPro.get(0).getHora_inicial_segunda();
				this.hora_final_segunda = this.listaPro.get(0).getHora_final_segunda();
				this.terca = this.listaPro.get(0).getTerca();
				this.hora_inicial_terca = this.listaPro.get(0).getHora_inicial_terca();
				this.hora_final_terca = this.listaPro.get(0).getHora_final_terca();
				this.quarta = this.listaPro.get(0).getQuarta();
				this.hora_inicial_quarta = this.listaPro.get(0).getHora_inicial_quarta();
				this.hora_final_quarta = this.listaPro.get(0).getHora_final_quarta();
				this.quinta = this.listaPro.get(0).getQuinta();
				this.hora_inicial_quinta = this.listaPro.get(0).getHora_inicial_quinta();
				this.hora_final_quinta = this.listaPro.get(0).getHora_final_quinta();
				this.sexta = this.listaPro.get(0).getSexta();
				this.hora_inicial_sexta = this.listaPro.get(0).getHora_inicial_sexta();
				this.hora_final_sexta = this.listaPro.get(0).getHora_final_sexta();
				this.sabado = this.listaPro.get(0).getSabado();
				this.hora_inicial_sabado = this.listaPro.get(0).getHora_inicial_sabado();
				this.hora_final_sabado = this.listaPro.get(0).getHora_final_sabado();
				this.domingo = this.listaPro.get(0).getDomingo();
				this.hora_inicial_domingo = this.listaPro.get(0).getHora_inicial_domingo();
				this.hora_final_domingo = this.listaPro.get(0).getHora_final_domingo();
				
				this.marido = this.listaPro.get(0).getMarido();
				this.faxineiro = this.listaPro.get(0).getFaxineiro();
				this.limpeza = this.listaPro.get(0).getLimpeza();
				this.garcom = this.listaPro.get(0).getGarcom();
				this.bartender = this.listaPro.get(0).getBartender();
				this.cozinheiro = this.listaPro.get(0).getCozinheiro();
				this.churrasqueiro = this.listaPro.get(0).getChurrasqueiro();
				this.confeteiro = this.listaPro.get(0).getConfeteiro();
				this.fotografias = this.listaPro.get(0).getFotografias();
				this.manicure = this.listaPro.get(0).getManicure();
				this.maquiagem = this.listaPro.get(0).getMaquiagem();
				this.baba = this.listaPro.get(0).getBaba();
				this.marketing = this.listaPro.get(0).getMarketing();
				this.contabeis = this.listaPro.get(0).getContabeis();
				this.motorista = this.listaPro.get(0).getMotorista();
				this.cpf = this.listaPro.get(0).getCpf();
				
				if (ValidaController.isCNPJ(this.cpf) == false && this.cpf.length() > 13) {

					
					FacesContext context = FacesContext.getCurrentInstance();
					context.getExternalContext().getFlash().setKeepMessages(true);
					
					ValidaController.addMessage("Digite um CNPJ Válido");
					context.getExternalContext().redirect("editarprofissional.xhtml");
					
					return false;
				}
				if (ValidaController.isCPF(this.cpf) == false && this.cpf.length() < 12) {

					FacesContext context = FacesContext.getCurrentInstance();
					context.getExternalContext().getFlash().setKeepMessages(true);
					
					ValidaController.addMessage("Digite um CPF Válido");
					context.getExternalContext().redirect("editarprofissional.xhtml");
					
					return false;
				}
		
		
		
		novo.setNome(this.listaPro.get(0).getNome());
		novo.setCodigo(this.listaPro.get(0).getCodigo());
		
		novo.setCpf(this.listaPro.get(0).getCpf());
		novo.setTelFixo(this.listaPro.get(0).getTelFixo());
		novo.setTelMovel(this.listaPro.get(0).getTelMovel());
		novo.setApelido(this.listaPro.get(0).getApelido());
		novo.setEmail(this.listaPro.get(0).getEmail());
		novo.setDescricao(this.listaPro.get(0).getDescricao());
		novo.setTipo(this.listaPro.get(0).getTipo());
		novo.setEstado(this.estado);
		novo.setCidade(this.cidade);
		novo.setAvaliacoes(this.listaPro.get(0).getAvaliacoes());
		novo.setSenha(this.listaPro.get(0).getSenha());
		novo.setEmail(listaPro.get(0).getEmail());
		
		
		//---------------------------------------------Dias e horas disponiveis--------------------------------------
		
		
		//se for escolhido indisponivel atribui indisponivel em horas e se for disponivel atribui somente as hora
		//novo.setSegunda(this.segunda);
		if(this.segunda == "") {
			novo.setSegunda(this.segunda = "Indisponivel");
			novo.setHora_inicial_segunda(this.hora_inicial_segunda = "Indisponivel");
			novo.setHora_final_segunda(this.hora_final_segunda = "Indisponivel");
		}
		else {
			novo.setSegunda(this.segunda);
			novo.setHora_inicial_segunda(this.hora_inicial_segunda);
			String xpto = this.hora_inicial_segunda.substring(11, 16);
			novo.setHora_inicial_segunda(xpto);
			
			novo.setHora_final_segunda(this.hora_final_segunda);
			String xpto1 = this.hora_final_segunda.substring(11, 16);
			novo.setHora_final_segunda(xpto1);
		}
			
		//novo.setTerca(this.terca);
		if(this.terca == "") {
			novo.setTerca(this.terca = "Indisponivel");
			novo.setHora_inicial_terca(this.hora_inicial_terca = "Indisponivel");
			novo.setHora_final_terca(this.hora_final_terca = "Indisponivel");
		}
		else {
			novo.setTerca(this.terca);
			novo.setHora_inicial_terca(this.hora_inicial_terca);
			String spto2 = this.hora_inicial_terca.substring(11, 16);
			novo.setHora_inicial_terca(spto2);

			novo.setHora_final_terca(this.hora_final_terca);
			String xpto3 = this.hora_final_terca.substring(11, 16);
			novo.setHora_final_terca(xpto3);
		}

		
		//novo.setQuarta(this.quarta);
		if(this.quarta == "") {
			novo.setQuarta(this.quarta = "Indisponivel");
			novo.setHora_inicial_quarta(this.hora_inicial_quarta = "Indisponivel");
			novo.setHora_final_quarta(this.hora_final_quarta = "Indisponivel");
		}
		else {
			novo.setQuarta(this.quarta);
			novo.setHora_inicial_quarta(this.hora_inicial_quarta);
			String xpto4 = this.hora_inicial_quarta.substring(11, 16);
			novo.setHora_inicial_quarta(xpto4);

			novo.setHora_final_quarta(this.hora_final_quarta);
			String xpto5 = this.hora_final_segunda.substring(11, 16);
			novo.setHora_final_quarta(xpto5);
		}
		

		//novo.setQuinta(this.quinta);
		if(this.quinta == "") {
			novo.setQuinta(this.quinta = "Indisponivel");
			novo.setHora_inicial_quinta(this.hora_inicial_quinta = "Indisponivel");
			novo.setHora_final_quinta(this.hora_final_quinta = "Indisponivel");
		}
		else {
		novo.setQuinta(this.quinta);
		novo.setHora_inicial_quinta(this.hora_inicial_quinta);
		String xpto6 = this.hora_inicial_quinta.substring(11, 16);
		novo.setHora_inicial_quinta(xpto6);

		novo.setHora_final_quinta(this.hora_final_quinta);
		String xpto7 = this.hora_final_quinta.substring(11, 16);
		novo.setHora_final_quinta(xpto7);
		}
		
		
		//novo.setSexta(this.sexta);
		if(this.sexta == "") {
			novo.setSexta(this.sexta = "Indisponivel");
			novo.setHora_inicial_sexta(this.hora_inicial_sexta = "Indisponivel");
			novo.setHora_final_sexta(this.hora_final_sexta = "Indisponivel");
		}
		else {
		novo.setSexta(this.sexta);
		novo.setHora_inicial_sexta(this.hora_inicial_sexta);
		String xpto8 = this.hora_inicial_sexta.substring(11, 16);
		novo.setHora_inicial_sexta(xpto8);

		novo.setHora_final_sexta(this.hora_final_sexta);
		String xpto9 = this.hora_final_sexta.substring(11, 16);
		novo.setHora_final_sexta(xpto9);
		}
		

		//novo.setSabado(this.sabado);
		if(this.sabado == "") {
			novo.setSabado(this.sabado = "Indisponivel");
			novo.setHora_inicial_sabado(this.hora_inicial_sabado = "Indisponivel");
			novo.setHora_final_sabado(this.hora_final_sabado = "Indisponivel");
		}
		else {
		novo.setSabado(this.sabado);
		novo.setHora_inicial_sabado(this.hora_inicial_sabado);
		String xpto10 = this.hora_inicial_sabado.substring(11, 16);
		novo.setHora_inicial_sabado(xpto10);

		novo.setHora_final_sabado(this.hora_final_sabado);
		String xpto11 = this.hora_final_sabado.substring(11, 16);
		novo.setHora_final_sabado(xpto11);
		}
		

		//novo.setDomingo(this.domingo);
		if(this.domingo == "") {
			novo.setDomingo(this.domingo = "Indisponivel");
			novo.setHora_inicial_domingo(this.hora_inicial_domingo = "Indisponivel");
			novo.setHora_final_domingo(this.hora_final_domingo = "Indisponivel");
		}
		else {
		novo.setDomingo(this.domingo);
		novo.setHora_inicial_domingo(this.hora_inicial_domingo);
		String xpto12 = this.hora_inicial_domingo.substring(11, 16);
		novo.setHora_inicial_domingo(xpto12);

		novo.setHora_final_domingo(this.hora_final_domingo);
		String xpto13 = this.hora_final_domingo.substring(11, 16);
		novo.setHora_final_domingo(xpto13);
		}

		
		
		
		
		//--------------------------------------------Areas de atuações----------------------------------------
		
		if(marido == "") {
			novo.setMarido(this.marido = "nao");
		}
		else {
			novo.setMarido(this.marido);
		}
		
		
		if(faxineiro == "") {
			novo.setFaxineiro(this.faxineiro = "nao");
		}
		else {
			novo.setFaxineiro(this.faxineiro);
		}
		
		if(limpeza == "") {
			novo.setLimpeza(this.limpeza = "nao");
		}
		else {
			novo.setLimpeza(this.limpeza);
		}
		
		if(garcom == "") {
			novo.setGarcom(this.garcom = "nao");
		}
		else {
			novo.setGarcom(this.garcom);
		}
		
		if(bartender == "") {
			novo.setBartender(this.bartender = "nao");
		}
		else {
			novo.setBartender(this.bartender);
		}
		
		if(churrasqueiro == "") {
			novo.setChurrasqueiro(this.churrasqueiro = "nao");
		}
		else {
			novo.setChurrasqueiro(this.churrasqueiro);
		}
		
		if(confeteiro == "") {
			novo.setConfeteiro(this.confeteiro = "nao");
		}
		else {
			novo.setConfeteiro(this.confeteiro);
		}
		
		if(fotografias == "") {
			novo.setFotografias(this.fotografias = "nao");
		}
		else {
			novo.setFotografias(this.fotografias);
		}
		
		if(manicure == "") {
			novo.setManicure(this.manicure = "nao");
		}
		else {
			novo.setManicure(this.manicure);
		}
		
		
		if(maquiagem == "") {
			novo.setMaquiagem(this.maquiagem = "nao");
		}
		else {
			novo.setMaquiagem(this.maquiagem);
		}
		
		if(baba == "") {
			novo.setBaba(this.baba = "nao");
		}
		else {
			novo.setBaba(this.baba);
		}
		
		if(marketing == "") {
			novo.setMarketing(this.marketing = "nao");
		}
		else {
			novo.setMarketing(this.marketing);
		}
		
		if(contabeis == "") {
			novo.setContabeis(this.contabeis = "nao");
		}
		else {
			novo.setContabeis(this.contabeis);
		}
		
		
		if(motorista == "") {
			novo.setMotorista(this.motorista = "nao");
		}
		else {
			novo.setMotorista(this.motorista);
		}
		if(this.cozinheiro == "") {
			novo.setCozinheiro(this.cozinheiro = "nao");
		}
		else {
			novo.setCozinheiro(this.cozinheiro);
		}


		
		
		DadosController.AlterarProfissional(novo);
		
		
		
		/* este trecho persiste a mensagem para proxima tela */
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		
		ValidaController.addMessage("Salvo com sucesso!");
		context.getExternalContext().redirect("paginaprofissional.xhtml");
		return false;
		
		
	}

	
	

	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public int getInicio() {
		return inicio;
	}


	public void setInicio(int inicio) {
		this.inicio = inicio;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCategoria() {
		return Categoria;
	}


	public void setCategoria(String categoria) {
		Categoria = categoria;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getTelFixo() {
		return telFixo;
	}


	public void setTelFixo(String telFixo) {
		this.telFixo = telFixo;
	}


	public String getTelMovel() {
		return telMovel;
	}


	public void setTelMovel(String telMovel) {
		this.telMovel = telMovel;
	}


	public String getApelido() {
		return apelido;
	}


	public void setApelido(String apelido) {
		this.apelido = apelido;
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


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getAvaliacoes() {
		return avaliacoes;
	}


	public void setAvaliacoes(String avaliacoes) {
		this.avaliacoes = avaliacoes;
	}


	public String getNota() {
		return nota;
	}


	public void setNota(String nota) {
		this.nota = nota;
	}


	public String getCidade() {
		return cidade;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getSegunda() {
		return segunda;
	}


	public void setSegunda(String segunda) {
		this.segunda = segunda;
	}


	public String getHora_inicial_segunda() {
		return hora_inicial_segunda;
	}


	public void setHora_inicial_segunda(String hora_inicial_segunda) {
		this.hora_inicial_segunda = hora_inicial_segunda;
	}


	public String getHora_final_segunda() {
		return hora_final_segunda;
	}


	public void setHora_final_segunda(String hora_final_segunda) {
		this.hora_final_segunda = hora_final_segunda;
	}


	public String getTerca() {
		return terca;
	}


	public void setTerca(String terca) {
		this.terca = terca;
	}


	public String getHora_inicial_terca() {
		return hora_inicial_terca;
	}


	public void setHora_inicial_terca(String hora_inicial_terca) {
		this.hora_inicial_terca = hora_inicial_terca;
	}


	public String getHora_final_terca() {
		return hora_final_terca;
	}


	public void setHora_final_terca(String hora_final_terca) {
		this.hora_final_terca = hora_final_terca;
	}


	public String getQuarta() {
		return quarta;
	}


	public void setQuarta(String quarta) {
		this.quarta = quarta;
	}


	public String getHora_inicial_quarta() {
		return hora_inicial_quarta;
	}


	public void setHora_inicial_quarta(String hora_inicial_quarta) {
		this.hora_inicial_quarta = hora_inicial_quarta;
	}


	public String getHora_final_quarta() {
		return hora_final_quarta;
	}


	public void setHora_final_quarta(String hora_final_quarta) {
		this.hora_final_quarta = hora_final_quarta;
	}


	public String getQuinta() {
		return quinta;
	}


	public void setQuinta(String quinta) {
		this.quinta = quinta;
	}


	public String getHora_inicial_quinta() {
		return hora_inicial_quinta;
	}


	public void setHora_inicial_quinta(String hora_inicial_quinta) {
		this.hora_inicial_quinta = hora_inicial_quinta;
	}


	public String getHora_final_quinta() {
		return hora_final_quinta;
	}


	public void setHora_final_quinta(String hora_final_quinta) {
		this.hora_final_quinta = hora_final_quinta;
	}


	public String getSexta() {
		return sexta;
	}


	public void setSexta(String sexta) {
		this.sexta = sexta;
	}


	public String getHora_inicial_sexta() {
		return hora_inicial_sexta;
	}


	public void setHora_inicial_sexta(String hora_inicial_sexta) {
		this.hora_inicial_sexta = hora_inicial_sexta;
	}


	public String getHora_final_sexta() {
		return hora_final_sexta;
	}


	public void setHora_final_sexta(String hora_final_sexta) {
		this.hora_final_sexta = hora_final_sexta;
	}


	public String getSabado() {
		return sabado;
	}


	public void setSabado(String sabado) {
		this.sabado = sabado;
	}


	public String getHora_inicial_sabado() {
		return hora_inicial_sabado;
	}


	public void setHora_inicial_sabado(String hora_inicial_sabado) {
		this.hora_inicial_sabado = hora_inicial_sabado;
	}


	public String getHora_final_sabado() {
		return hora_final_sabado;
	}


	public void setHora_final_sabado(String hora_final_sabado) {
		this.hora_final_sabado = hora_final_sabado;
	}


	public String getDomingo() {
		return domingo;
	}


	public void setDomingo(String domingo) {
		this.domingo = domingo;
	}


	public String getHora_inicial_domingo() {
		return hora_inicial_domingo;
	}


	public void setHora_inicial_domingo(String hora_inicial_domingo) {
		this.hora_inicial_domingo = hora_inicial_domingo;
	}


	public String getHora_final_domingo() {
		return hora_final_domingo;
	}


	public void setHora_final_domingo(String hora_final_domingo) {
		this.hora_final_domingo = hora_final_domingo;
	}


	public String getEncodedBytes() {
		return encodedBytes;
	}


	public void setEncodedBytes(String encodedBytes) {
		this.encodedBytes = encodedBytes;
	}


	public String getFile() {
		return file;
	}


	public void setFile(String file) {
		this.file = file;
	}


	public String getResenha() {
		return resenha;
	}


	public void setResenha(String resenha) {
		this.resenha = resenha;
	}


	public String getMarido() {
		return marido;
	}


	public void setMarido(String marido) {
		this.marido = marido;
	}


	public String getFaxineiro() {
		return faxineiro;
	}


	public void setFaxineiro(String faxineiro) {
		this.faxineiro = faxineiro;
	}


	public String getLimpeza() {
		return limpeza;
	}


	public void setLimpeza(String limpeza) {
		this.limpeza = limpeza;
	}


	public String getGarcom() {
		return garcom;
	}


	public void setGarcom(String garcom) {
		this.garcom = garcom;
	}


	public String getBartender() {
		return bartender;
	}


	public void setBartender(String bartender) {
		this.bartender = bartender;
	}


	public String getCozinheiro() {
		return cozinheiro;
	}


	public void setCozinheiro(String cozinheiro) {
		this.cozinheiro = cozinheiro;
	}


	public String getChurrasqueiro() {
		return churrasqueiro;
	}


	public void setChurrasqueiro(String churrasqueiro) {
		this.churrasqueiro = churrasqueiro;
	}


	public String getConfeteiro() {
		return confeteiro;
	}


	public void setConfeteiro(String confeteiro) {
		this.confeteiro = confeteiro;
	}


	public String getFotografias() {
		return fotografias;
	}


	public void setFotografias(String fotografias) {
		this.fotografias = fotografias;
	}


	public String getManicure() {
		return manicure;
	}


	public void setManicure(String manicure) {
		this.manicure = manicure;
	}


	public String getMaquiagem() {
		return maquiagem;
	}


	public void setMaquiagem(String maquiagem) {
		this.maquiagem = maquiagem;
	}


	public String getBaba() {
		return baba;
	}


	public void setBaba(String baba) {
		this.baba = baba;
	}


	public String getMarketing() {
		return marketing;
	}


	public void setMarketing(String marketing) {
		this.marketing = marketing;
	}


	public String getContabeis() {
		return contabeis;
	}


	public void setContabeis(String contabeis) {
		this.contabeis = contabeis;
	}


	public String getMotorista() {
		return motorista;
	}


	public void setMotorista(String motorista) {
		this.motorista = motorista;
	}


	public Boolean getAux1() {
		return aux1;
	}


	public void setAux1(Boolean aux1) {
		this.aux1 = aux1;
	}


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	public List<CadastroProfissional> getListaPro() {
		return listaPro;
	}


	public void setListaPro(List<CadastroProfissional> listaPro) {
		this.listaPro = listaPro;
	}
	
}
