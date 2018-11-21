package br.com.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.Annotations;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.model.Cep;
import br.com.model.CepServiceVO;




@ManagedBean(name = "valida")
@ViewScoped
public class ValidaController {
	
	private static List listaDados;
	private String uf;
	private String cidade;
	
	
	public static List getListaDados() {
		return listaDados;
	}

	public static void setListaDados(List listaDados) {
		ValidaController.listaDados = listaDados;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public static void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
		PrimeFaces.current().dialog().showMessageDynamic(message);
	}
		public static boolean isCNPJ(String CNPJ) {
		// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
		    if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
		        CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
		        CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
		        CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
		        CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
		       (CNPJ.length() != 14))
		       return(false);
		 
		    char dig13, dig14;
		    int sm, i, r, num, peso;
		 
		// "try" - protege o código para eventuais erros de conversao de tipo (int)
		    try {
		// Calculo do 1o. Digito Verificador
		      sm = 0;
		      peso = 2;
		      for (i=11; i>=0; i--) {
		// converte o i-ésimo caractere do CNPJ em um número:
		// por exemplo, transforma o caractere '0' no inteiro 0
		// (48 eh a posição de '0' na tabela ASCII)
		        num = (int)(CNPJ.charAt(i) - 48);
		        sm = sm + (num * peso);
		        peso = peso + 1;
		        if (peso == 10)
		           peso = 2;
		      }
		 
		      r = sm % 11;
		      if ((r == 0) || (r == 1))
		         dig13 = '0';
		      else dig13 = (char)((11-r) + 48);
		 
		// Calculo do 2o. Digito Verificador
		      sm = 0;
		      peso = 2;
		      for (i=12; i>=0; i--) {
		        num = (int)(CNPJ.charAt(i)- 48);
		        sm = sm + (num * peso);
		        peso = peso + 1;
		        if (peso == 10)
		           peso = 2;
		      }
		 
		      r = sm % 11;
		      if ((r == 0) || (r == 1))
		         dig14 = '0';
		      else dig14 = (char)((11-r) + 48);
		 
		// Verifica se os dígitos calculados conferem com os dígitos informados.
		      if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
		         return(true);
		      else return(false);
		    } catch (InputMismatchException erro) {
		        return(false);
		    }
		  }

	public static boolean isCPF(String CPF) {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	public static void refresh() {

		FacesContext context = FacesContext.getCurrentInstance();

		Application application = context.getApplication();

		ViewHandler viewHandler = application.getViewHandler();

		UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());

		context.setViewRoot(viewRoot);

		context.renderResponse();

	}

	
	public static boolean isValidateEmail(String email) {
		Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher matcher = pattern.matcher(email);
		return matcher.find();
	}
	
	public static  String sha(String argv){

		String value = argv;

		String sha1 = "";
		
		// With the java libraries
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
	        digest.reset();
	        digest.update(value.getBytes("utf8"));
	        sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
	     
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return sha1;
	}
	
	/*private Date getEndOfDay(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    int year = calendar.get(Calendar.YEAR);
	    int month = calendar.get(Calendar.MONTH);
	    int day = calendar.get(Calendar.DATE);
	    calendar.set(year, month, day, 23, 59, 59);
	    return calendar.getTime();
	}
*/

	
	public class InputTextareaView {
	     
	    public List<String> completeArea(String query) {
	        List<String> results = new ArrayList<String>();
	         
	        if(query.equals("PrimeFaces")) {
	            results.add("PrimeFaces Rocks!!!");
	            results.add("PrimeFaces has 100+ components.");
	            results.add("PrimeFaces is lightweight.");
	            results.add("PrimeFaces is easy to use.");
	            results.add("PrimeFaces is developed with passion!");
	        }
	        else {
	            for(int i = 0; i < 10; i++) {
	                results.add(query + i);
	            }
	        }
	         
	        return results;
	    }
	}



		public Object CepService(String cep2) throws IOException {
			
			
			String cep;
			//String[] ;
			
			if(cep2 == "" || cep2 == null) {
				 cep = "00000000";
			}
			else {
			
			cep = cep2;
			}

			// Define o CEP
			 
			
			// a string da url		
			String urlString = "http://cep.republicavirtual.com.br/web_cep.php";

			// os parametros a serem enviados
			Properties parameters = new Properties();
			parameters.setProperty("cep", cep);
			parameters.setProperty("formato", "xml");

			// o iterador, para criar a URL
			Iterator i = parameters.keySet().iterator();
			// o contador
			int counter = 0;

			// enquanto ainda existir parametros
			while (i.hasNext()) {

				// pega o nome
				String name = (String) i.next();
				// pega o valor
				String value = parameters.getProperty(name);

				// adiciona com um conector (? ou &)
				// o primeiro é ?, depois são &
				urlString += (++counter == 1 ? "?" : "&") + name + "=" + value;
				
			}

			
				// cria o objeto url
				URL url = new URL(urlString);

				// cria o objeto httpurlconnection
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();

				// seta o metodo
				connection.setRequestProperty("Request-Method", "GET");

				// seta a variavel para ler o resultado
				connection.setDoInput(true);
				connection.setDoOutput(false);

				// conecta com a url destino
				connection.connect();

				// abre a conexão pra input
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				// le ate o final
				StringBuffer newData = new StringBuffer();
				String s = "";
				while (null != ((s = br.readLine()))) {
					newData.append(s);
				}
				br.close();

				// Controi classe a partir do XML 
				XStream xstream = new XStream(new DomDriver());
				Annotations.configureAliases(xstream, CepServiceVO.class);
				xstream.alias("webservicecep", CepServiceVO.class);
				CepServiceVO cepServiceVO = (CepServiceVO) xstream.fromXML(newData.toString());
				
				StringBuffer teste = newData;
				
				// Imprime Resuntado Final 
				System.out.println(new String(newData));
				int inicio,fim;
				
				
				String aux3 = "<uf>",aux4 = "</uf>";
				
				inicio = newData.toString().lastIndexOf(aux3) + 4;
				fim = newData.toString().lastIndexOf(aux4);
				
				
					
				this.uf = newData.toString().substring(inicio, fim);
				
				String aux1 = "</cidade>",aux2 = "<cidade>";
				
				
				
				inicio = newData.toString().lastIndexOf(aux2) + 8;
				
				fim = newData.toString().lastIndexOf(aux1);
				
				
				
				this.cidade = newData.toString().substring(inicio, fim);
				
				
				Cep c = new Cep();
				c.setCidade(this.cidade);
				c.setUf(this.uf);
				
			System.out.println(this.cidade);
			System.out.println(this.uf);
				
						
			
		
			return c;

		}
		
	
		public static void SalvaIMG(String login, BufferedImage file) throws IOException {
		
	        File dir = new File("C:\\Users\\SERVER\\eclipse-workspace\\Contract\\WebContent\\profile\\" + login);
			if (dir.mkdir()) {
				System.out.println("Diretório criado com sucesso!");
			}
			ImageIO.write(file, "jpg",new File("C:\\Users\\SERVER\\eclipse-workspace\\Contract\\WebContent\\profile\\" + login +"\\"+login+".jpg"));
			
			
		}
		
		public static DefaultStreamedContent buscaIMG(String login) {
			
			//new File("C:\\Users\\SERVER\\eclipse-workspace\\Contract\\WebContent\\profile\\" + login +"\\"+login+".jpg");
			
			FacesContext context = FacesContext.getCurrentInstance();  
		    final InputStream io = context.getExternalContext().getResourceAsStream("//resources//profile//" + login +"//"+login+".jpg");
			
			
			
			return new DefaultStreamedContent(io, "image/png", login);  
		}
		
		public static String pegaHora() {
			String hora = null;
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			hora = sdf.format(new Date()).toString();
			return hora;
		}
		
		
	
}

