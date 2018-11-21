package br.com.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ImagemBase64 {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(columnDefinition = "longtext")
	private String b64;
    private String formato;
    private String nome;
    private int codigosecundario;
    

   public String getB64() {
          return b64;
    }
    public void setB64(String b64) {
          this.b64 = b64;
    }

    public String getFormato() {
          return formato;
    }
    public void setFormato(String formato) {
          this.formato = formato;
    }
    public String getNome() {
          return nome;
    }
    public void setNome(String nome) {
          this.nome = nome;
    }
	public int getCodigosecundario() {
		return codigosecundario;
	}
	public void setCodigosecundario(int codigosecundario) {
		this.codigosecundario = codigosecundario;
	}      
}