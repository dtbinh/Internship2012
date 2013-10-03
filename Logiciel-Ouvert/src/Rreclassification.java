
import java.util.ArrayList;

import rcaller.RCaller;
import rcaller.RCode;

public class Rreclassification {
	ConnexionBaseDonnees connexion;
	DensitePopulation densite;


	private String nomBase;
	private String port;
	private String nomUtilisateur;
	private String motDePasse;
	private String hote;
	private double surfaceTotale;
	private double taillePixel; 

	private static ArrayList <String> nomCouches = new ArrayList <String> ();
	private static ArrayList <String> maximCouches = new ArrayList <String> ();
	private static ArrayList <String> minimCouches = new ArrayList <String> ();

	public Rreclassification(){
		
		nomCouches.clear();
		maximCouches.clear();
		
		

		this.setNomBase(ConnexionBaseDonnees.getNomBase());
		this.setPort(ConnexionBaseDonnees.getPort());
		this.setNomUtilisateur(ConnexionBaseDonnees.getUser());
		this.setMotDePasse(ConnexionBaseDonnees.getPswd());
		this.setHote(ConnexionBaseDonnees.getHote());

		System.out.println("reclassification");




		for(int i=0;i<FenetreReclassification.getNomShape1().size();i++){

			RCaller caller = new RCaller();
			caller.setRscriptExecutable("/usr/bin/Rscript");

			RCode code = new RCode();

			code.addRCode("library(RPostgreSQL)");
			code.addRCode("library(rgdal)");
			code.addRCode("library(raster)");
			code.addRCode("library(maptools)");
			code.addRCode("library(sp)");
			code.addRCode("library(spatstat)");
			code.addRCode("layers <- readGDAL(\"PG:host="+this.hote+" user="+this.nomUtilisateur+" dbname="+this.nomBase+" password="+this.motDePasse+" port="+this.port+" table="+FenetreReclassification.getNomShape1().get(i)+"\")");
			System.out.println("layers <- readGDAL(\"PG:host="+this.hote+" user="+this.nomUtilisateur+" dbname="+this.nomBase+" password="+this.motDePasse+" port="+this.port+" table="+FenetreReclassification.getNomShape1().get(i)+"\")");

			code.addRCode("layers <-raster(layers)");


			code.addRCode("maxim <- maxValue(layers)");
			code.addRCode("minim <- minValue(layers)");

			code.addRCode("my.all <- list(max=maxim, min=minim)");

			caller.setRCode(code);
			caller.runAndReturnResult("my.all");


			//caller.runAndReturnResult("minim");

			//	caller.runAndReturnResult("my.mean");


			double[] maxim;
			double[] minim;

			double maximum;
			double minimum;

			String maximString;
			String minimString;

			maxim = caller.getParser().getAsDoubleArray("max");
			minim = caller.getParser().getAsDoubleArray("min");


			maximum=maxim[0];
			maximString=Double.toString(maximum);
			System.out.println("maxim is "+maximString);

			minimum=minim[0];
			minimString=Double.toString(minimum);
			System.out.println("minim is "+minimString);


			if(maximum>minimum){
				nomCouches.add(FenetreReclassification.getNomShape1().get(i));
				maximCouches.add(maximString);
				minimCouches.add(minimString);
			}

			//System.out.println("resultat is "+resulat[0]);

			//code.addRCode("");


			//		
			//		File file2 = null;
			//		try {
			//			file2 = code.startPlot();
			//		} catch (IOException e1) {
			//			// TODO Auto-generated catch block
			//			e1.printStackTrace();
			//		}

			//code.addRCode("plot(points_arbres)");
			//System.out.println("Plot will be saved to : " + file2);
			//code.endPlot();


			//	code.addRCode("drv <- dbDriver(\"PostgreSQL\")");
			//code.addRCode("con <- dbConnect(drv, port=5432, dbname= \"testohmam\", user=\"Gilles\", password=\"959426G/e\")");
			//code.addRCode("rs <- dbSendQuery(con, \"select surf from batiments\")");
			//code.addRCode("data <- fetch(rs,n=-1)");
			//code.addRCode("plot(id,data$surf");


			////code.addRCode("plot(pts)");



			//code.addRCode("set.seed(123)");
			//code.addRCode("x<-rnorm(10)");
			//code.addRCode("y<-rnorm(10)");
			//code.addRCode("ols<-lm(y~x)");

			//rcaller.setRCode(code);


			// caller.getParser().getAsStringArray("result");

			//		  code.showPlot(file2);
			//		  
			//			code.endPlot();
			//


		}


		FenetreReclassificationParametres feneparam = new FenetreReclassificationParametres();
		feneparam.setVisible(true);
		//			try {
		//				new RasterShapeDensiteBatiments(connexion,choix);ee
		//			} catch (IOException e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//			} catch (SQLException e) {
		//				// TODO Auto-generated catch block
		//				e.printStackTrace();
		//			}		//  code.showPlot(file2);
		//		
		//caller.runAndReturnResult("fetch(rs,n=10)");

		//System.out.println("Available results from lm() object:");

		//String [] test2;
		//String test3="";

		//	code.endPlot();
		//test3=caller.getParser().getXMLFileAsString();
		//test3=caller.getParser().getXMLFileAsString();
		//caller.getParser().getAsStringArray(test3);
		//ArrayList<String> test = new ArrayList<String>();

		//test=caller.getParser().getNames();

		//double [] bla={};

		//System.out.println(test3);





	}

	public String getNomBase() {
		return nomBase;
	}

	public void setNomBase(String nomBase) {
		this.nomBase = nomBase;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public double getSurfaceTotale() {
		return surfaceTotale;
	}

	public void setSurfaceTotale(double surfaceTotale) {
		this.surfaceTotale = surfaceTotale;
	}

	public double getTaillePixel() {
		return taillePixel;
	}

	public void setTaillePixel(double taillePixel) {
		this.taillePixel = taillePixel;
	}

	public String getHote() {
		return hote;
	}

	public void setHote(String hote) {
		this.hote = hote;
	}

	public static ArrayList<String> getNomCouches() {
		return nomCouches;
	}

	public static void setNomCouches(ArrayList<String> nomCouches) {
		Rreclassification.nomCouches = nomCouches;
	}

	public static ArrayList<String> getMaximCouches() {
		return maximCouches;
	}

	public static void setMaximCouches(ArrayList<String> maximCouches) {
		Rreclassification.maximCouches = maximCouches;
	}

	public static ArrayList<String> getMinimCouches() {
		return minimCouches;
	}

	public static void setMinimCouches(ArrayList<String> minimCouches) {
		Rreclassification.minimCouches = minimCouches;
	} 



	/*
	 * public  ConnexionSQL() throws IllegalAccessException {


		RCaller rcaller = new RCaller();
		RCode code = new RCode();
		code.clear();
		rcaller.cleanRCode();
		//Globals.detect_current_rscript();
		//rcaller.setRscriptExecutable(Globals.Rscript_current);
	    rcaller.setRscriptExecutable("/usr/bin/Rscript");


		//code.addRCode("library(ddRPostgreSQL)");

		code.addRCode("library(RPostgreSQL)");
		code.addRCode("drv <- dbDriver(\"PostgreSQL\")");
		code.addRCode("con <- dbConnect(drv, port=5432, dbname= \"test2\", user=\"entringe\", password=\"959426G/e\")");

		rcaller.setRCode(code);
		//rcaller.setRCode(code);
		rcaller.runAndReturnResult("rs <- dbSendQuery(con, \"select surf from b_bati\")");

		System.out.println("Available results from lm() object:");

		System.out.println(rcaller.getParser().getNames());
		//System.out.println("test");


	}*/


}

