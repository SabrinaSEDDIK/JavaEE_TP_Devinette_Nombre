package fr.eni.tprecherchernombre;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */

@WebServlet(
		urlPatterns = "/Servlet",
		initParams = {
				@WebInitParam(description = "borne inférieure",
						name = "BORNE_INF",
						value = "3"),
				@WebInitParam(description = "borne supérieure",
				name = "BORNE_SUP",
				value = "20")
		})


public class Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private int nbTentatives = 0;
	private String borneSup;
	private String borneInf;
	
	@Override
    public void init() throws ServletException {
    	this.borneInf = this.getInitParameter("BORNE_INF");
    	this.borneSup = this.getInitParameter("BORNE_SUP");
    	super.init();
    }
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		nbTentatives++;
		String nombre = request.getParameter("nombre");
		System.out.println("nombre user : " + nombre + " tentative " + nbTentatives); //affichage console pour vérifier
		
		/* Redirection temporaire vers page echec --> Réussie
		response.sendRedirect("/TPRechercherNombre/echec.html");*/
		
		/*Redirection temporaire vers la bonne page*/
		response.sendRedirect(adresseURL(nombre));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String adresseURL(String nbUser) {
		int nbRand = genererInt(Integer.parseInt(borneInf), Integer.parseInt(borneSup));
		System.out.println("nombre rand : " + nbRand + " tentative " + nbTentatives);  //affichage console pour vérifier
		if(Integer.parseInt(nbUser) == nbRand) {
			return "/TPRechercherNombre/victoire.html";
		}else {
			return "/TPRechercherNombre/echec.html";
		}
	}
	
	private int genererInt(int borneInf, int borneSup){
		   Random random = new Random();
		   int nbRand = borneInf+random.nextInt(borneSup-borneInf+1);
		   return nbRand;
		}
}
