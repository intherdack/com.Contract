package br.com.model;
import javax.persistence.*;

@Entity
public class CadastroProfissional {
	private static final String String = null;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	private String Categoria = "Profissional";
	private String cpf;
	private String nome;
	private String telMovel;
	private String telFixo;
	private String Apelido;
	private String email;
	private String senha;
	private String descricao;
	private String tipo;
	private String avaliacoes;
	private String nota;
	private String cidade;
	private String estado;
	private String segunda;
	private String hora_inicial_segunda;
	private String hora_final_segunda;
	private String terca;
	private String hora_inicial_terca;
	private String hora_final_terca;
	private String quarta;
	private String hora_inicial_quarta;
	private String hora_final_quarta;
	private String quinta;
	private String hora_inicial_quinta;
	private String hora_final_quinta;
	private String sexta;
	private String hora_inicial_sexta;
	private String hora_final_sexta;
	private String sabado;
	private String hora_inicial_sabado;
	private String hora_final_sabado;
	private String domingo;
	private String hora_inicial_domingo;
	private String hora_final_domingo;
	private String marido;
	private String faxineiro;
	private String limpeza;
	private String garcom;
	private String bartender;
	private String cozinheiro;
	private String churrasqueiro;
	private String confeteiro;
	private String fotografias;
	private String manicure;
	private String maquiagem;
	private String baba;
	private String marketing;
	private String contabeis;
	private String motorista;
	

	
	//-------------------------------Getters an setters-------------------------------------------------------------


	public int getCodigo() {
		return codigo;
	}

	public String getCategoria() {
		return Categoria;
	}

	public void setCategoria(String categoria) {
		Categoria = categoria;
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

	public String getCozinheiro() {
		return cozinheiro;
	}

	public void setCozinheiro(String cozinheiro) {
		this.cozinheiro = cozinheiro;
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

	public String getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(String avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public static String getString() {
		return String;
	}



	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public java.lang.String getTelMovel() {
		return telMovel;
	}

	public void setTelMovel(java.lang.String telMovel) {
		this.telMovel = telMovel;
	}

	public java.lang.String getTelFixo() {
		return telFixo;
	}

	public void setTelFixo(java.lang.String telFixo) {
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	

}
