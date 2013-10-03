import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import rcaller.RCaller;
import rcaller.RCode;

public class RDensiteetalea {
	ConnexionBaseDonnees connexion;
	DensitePopulation densite;
	ChoisirRepertoireSQL choix;

	
	private String nomBase;
	private String port;
	private String nomUtilisateur;
	private String motDePasse;
	private String hote;
	private double surfaceTotale;
	private double taillePixel; 
	private String pathSQL;

	public RDensiteetalea() throws SQLException, IOException{
	//	this.connexion=connexion;
		//this.densite = densite;
		
	
		this.pathSQL=ChoisirRepertoireSQL.getPath();
		this.setNomBase(ConnexionBaseDonnees.getNomBase());
		this.setPort(ConnexionBaseDonnees.getPort());
		this.setNomUtilisateur(ConnexionBaseDonnees.getUser());
		this.setMotDePasse(ConnexionBaseDonnees.getPswd());
		this.setHote(ConnexionBaseDonnees.getHote());
		this.setTaillePixel(DensitePopulation.getTaillePixel());
		
		RCaller caller = new RCaller();
		caller.setRscriptExecutable("/usr/bin/Rscript");

		RCode code = new RCode();

		code.addRCode("library(RPostgreSQL)");
		code.addRCode("library(rgdal)");
		code.addRCode("library(raster)");
		code.addRCode("library(maptools)");
		code.addRCode("library(sp)");
		code.addRCode("library(spatstat)");
		System.out.println(this.getNomBase()+" "+this.getNomUtilisateur());
		code.addRCode("layers <- readGDAL(\"PG:host="+this.hote+" user="+this.nomUtilisateur+" dbname="+this.nomBase+" password="+this.motDePasse+" port="+this.port+" table=densite_batiment\")");
		System.out.println("layers <- readGDAL(\"PG:host="+this.hote+" user="+this.nomUtilisateur+" dbname="+this.nomBase+" password="+this.motDePasse+" port="+this.port+" table=densite_batiment\")");
		code.addRCode("layers400 <- readGDAL(\"PG:host="+this.hote+" user="+this.nomUtilisateur+" dbname="+this.nomBase+" password="+this.motDePasse+" port="+this.port+" table=raster4002\")");
		System.out.println("layers400 <- readGDAL(\"PG:host="+this.hote+" user="+this.nomUtilisateur+" dbname="+this.nomBase+" password="+this.motDePasse+" port="+this.port+" table=raster4002\")");
		code.addRCode("layers200 <- readGDAL(\"PG:host="+this.hote+" user="+this.nomUtilisateur+" dbname="+this.nomBase+" password="+this.motDePasse+" port="+this.port+" table=raster2002\")");
		System.out.println("layers200 <- readGDAL(\"PG:host="+this.hote+" user="+this.nomUtilisateur+" dbname="+this.nomBase+" password="+this.motDePasse+" port="+this.port+" table=raster2002\")");

		
		
		code.addRCode("layers <-raster(layers)");
		code.addRCode("layers400 <-raster(layers400)");
		code.addRCode("layers200 <-raster(layers200)");

		code.addRCode("res(layers) <-"+this.getTaillePixel());
		code.addRCode("res(layers400) <-"+this.getTaillePixel());
		code.addRCode("res(layers200) <-"+this.getTaillePixel());
		
		code.addRCode("layers400 <- reclass(layers400, c(-Inf,0.99,0, 0.9,1.1,2, 1,Inf,0))");
		code.addRCode("mos1 <- mosaic(layers,layers400,fun=sum)");
		code.addRCode("mos2 <- mosaic(mos1,layers200,fun=sum)");
		code.addRCode("mos3 <- mosaic(layers400,layers200,fun=sum)");
		
		code.addRCode("rc.sp1 <- as(mos2, \"SpatialPixelsDataFrame\")");
		//System.out.println(this.pathSQL);
		code.addRCode("writeGDAL(rc.sp1,"+"\""+this.pathSQL+"/"+"aleaETvulnerabilite.tif\", drivername=\"GTiff\")");
		

		//code.addRCode("");

		
		
		File file2 = code.startPlot();

		//code.addRCode("plot(points_arbres)");
		//System.out.println("Plot will be saved to : " + file2);
		//code.endPlot();

		
	//	code.addRCode("drv <- dbDriver(\"PostgreSQL\")");
		//code.addRCode("con <- dbConnect(drv, port=5432, dbname= \"testohmam\", user=\"Gilles\", password=\"959426G/e\")");
		//code.addRCode("rs <- dbSendQuery(con, \"select surf from batiments\")");
		//code.addRCode("data <- fetch(rs,n=-1)");
		//code.addRCode("plot(id,data$surf");
		
		//code.addRCode("plot(mos3)");

		code.addRCode("plot(mos3, main=\"Carte d'alea\",axes=FALSE,col=colorRampPalette(c(\"white\",\"yellow\", \"red\"))(30))");

		File file3 = code.startPlot();

		//code.addRCode("plot(mos2)");

		code.addRCode("plot(mos2, main=\"Carte de risque\",axes=FALSE,col=colorRampPalette(c(\"white\",\"yellow\", \"red\"))(30))");

		
		//code.addRCode("set.seed(123)");
		//code.addRCode("x<-rnorm(10)");
		//code.addRCode("y<-rnorm(10)");
		//code.addRCode("ols<-lm(y~x)");
		
		caller.setRCode(code);
		//rcaller.setRCode(code);
		
		  caller.runOnly();
		  code.showPlot(file2);
		  code.showPlot(file3);

		 
			
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

