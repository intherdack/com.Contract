package br.com.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.imageio.ImageIO;
import org.primefaces.model.UploadedFile;

import com.thoughtworks.xstream.core.util.Base64Encoder;

import br.com.model.ImagemBase64;
import br.com.model.ImagemBase64_Empregador;
import bwmorg.bouncycastle.util.encoders.Base64;


@ManagedBean(name = "base64MB")
@ViewScoped
public class Base64ManagedBean<Persist> {

	private UploadedFile imagem;

	@ManagedProperty(value = "#{persist}")
	private Persist per;

	public void salvar(UploadedFile file, int cod) throws Exception {

		imagem = file;

		DadosController bo = new DadosController();
		String formato = imagem.getContentType();
		String nome = imagem.getFileName();
		byte[] imageAsByte = new byte[(int) imagem.getSize()];		
		try {
			imagem.getInputstream().read(imageAsByte);
			ImagemBase64 ib4 = new ImagemBase64();
			String base64AsString = new String(Base64.encode(imageAsByte));
			ib4.setB64(base64AsString);
			
			ib4.setFormato(formato);
			ib4.setNome(nome);
			ib4.setCodigosecundario(cod);
			bo.save(ib4);

			System.out.println(ib4.getB64());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void salvarEmpregador(UploadedFile file, int cod) throws Exception {

		imagem = file;

		DadosController bo = new DadosController();
		String formato = imagem.getContentType();
		String nome = imagem.getFileName();
		byte[] imageAsByte = new byte[(int) imagem.getSize()];
	
		
		try {
			imagem.getInputstream().read(imageAsByte);
			ImagemBase64_Empregador ib4 = new ImagemBase64_Empregador();
			String base64AsString = new String(Base64.encode(imageAsByte));
			ib4.setB64(base64AsString);
			ib4.setFormato(formato);
			ib4.setNome(nome);
			ib4.setCodigosecundario(cod);
			bo.saveEmpregador(ib4);

			System.out.println(ib4.getB64());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            Base64Encoder decoder = new Base64Encoder();
            imageByte = decoder.decode(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

	public UploadedFile getImagem() {
		return imagem;
	}

	public void setImagem(UploadedFile imagem) {
		this.imagem = imagem;
	}

	public Persist getPer() {
		return per;
	}

	public void setPer(Persist per) {
		this.per = per;
	}

}