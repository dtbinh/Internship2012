import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import rcaller.RCaller;
import rcaller.RCode;

public class RreclassificationParametre {
	ConnexionBaseDonnees connexion;
	DensitePopulation densite;


	private String nomBase;
	private String port;
	private String nomUtilisateur;
	private String motDePasse;
	private String hote;
	private double surfaceTotale;


	private static ArrayList <String> nomCouches = new ArrayList <String> ();
	private static ArrayList <String> nombreClasses = new ArrayList <String> ();
	private static ArrayList <String> maxim = new ArrayList <String> ();
	private static ArrayList <String> minim = new ArrayList <String> ();

	private static ArrayList <String[][]> tableaux = new ArrayList <String[][]> ();
	private static ArrayList <String[][]> tableaux2 = new ArrayList <String[][]> ();

	private static ArrayList <String> maximCouches = new ArrayList <String> ();


	public RreclassificationParametre(){



		this.setNomBase(ConnexionBaseDonnees.getNomBase());
		this.setPort(ConnexionBaseDonnees.getPort());
		this.setNomUtilisateur(ConnexionBaseDonnees.getUser());
		this.setMotDePasse(ConnexionBaseDonnees.getPswd());
		this.setHote(ConnexionBaseDonnees.getHote());
		tableaux.clear();
		tableaux2.clear();

		tableaux=FenetreReclassificationParametresClasses.getTableauTailles();
		tableaux2=FenetreReclassificationParametresClasses.getTableaux();


		for(int fg=0;fg<tableaux.size();fg++){
			String test34[][]=tableaux.get(fg);
			String taillePixels=test34[fg][1];
			minim.add(taillePixels);
			String nomCouche=test34[fg][0].toString();
			nomCouches.add(nomCouche);
			String maxi=test34[fg][2].toString();
			maxim.add(maxi);
		}


		for(int fg=0;fg<tableaux2.size();fg++){
			String test34[][]=tableaux2.get(fg);
			String nombreClassess=test34[fg][1];
			nombreClasses.add(nombreClassess);
		}

		System.out.println("reclassification333");



		for(int i=0;i<FenetreReclassificationParametresClasses.getNomCouches().size();i++){
			int ii=0;
			int jj=0;
			double iiii=Double.parseDouble(nombreClasses.get(i));
			int iii=(int)iiii;
			double[] numberss = new double[iii*3];
			for(ii=0,jj=0;ii<(numberss.length)&&jj<maxim.size();ii=ii+3,jj++){
				System.out.println("ii"+ii);
				System.out.println("jj"+jj);
				numberss[ii]=Double.parseDouble(minim.get(jj));
				numberss[ii+1]=Double.parseDouble(maxim.get(jj));
				numberss[ii+2]=jj+1;

			}

			for(ii=0;ii<numberss.length;ii++){
				System.out.println("numberss "+numberss[ii]);
			}

			RCaller caller = new RCaller();
			caller.setRscriptExecutable("/usr/bin/Rscript");

			RCode code = new RCode();

			code.addRCode("library(RPostgreSQL)");

			code.addRCode("library(rgdal)");
			code.addRCode("library(raster)");
			code.addRCode("library(maptools)");
			code.addRCode("library(sp)");
			code.addRCode("library(spatstat)");
			code.addRCode("layers <- readGDAL(\"PG:host="+this.hote+" user="+this.nomUtilisateur+" dbname="+this.nomBase+" password="+this.motDePasse+" port="+this.port+" table="+FenetreReclassificationParametresClasses.getNomCouches().get(i)+"\")");
			System.out.println("layers <- readGDAL(\"PG:host="+this.hote+" user="+this.nomUtilisateur+" dbname="+this.nomBase+" password="+this.motDePasse+" port="+this.port+" table="+FenetreReclassificationParametresClasses.getNomCouches().get(i)+"\")");

			code.addRCode("ras <-raster(layers)");

			//	code.addRCode("m <- c(minim-1,-0.0000001,0,0.0001, sixieme,1, sixieme,tiers,2, tiers,maxim,3)");
			code.addDoubleArray("m", numberss);
			code.addRCode("rclmat <- matrix(m, ncol=3, byrow=TRUE)");
			code.addRCode("rc <- reclass(ras,rclmat)");
			code.addRCode("rc.sp <- as(rc, \"SpatialPixelsDataFrame\")");
			code.addRCode("writeGDAL(rc.sp,"+"\""+FenetreReclassificationParametresClasses.getNomCouches().get(i)+".tif\", drivername=\"GTiff\")");




			caller.setRCode(code);
			caller.runOnly();


			//caller.runAndReturnResult("minim");

			//	caller.runAndReturnResult("my.mean");
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

		try {
			new Raster2pgsqlReclassification();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



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
		RreclassificationParametre.nomCouches = nomCouches;
	}

	public static ArrayList<String> getMaximCouches() {
		return maximCouches;
	}

	public static void setMaximCouches(ArrayList<String> maximCouches) {
		RreclassificationParametre.maximCouches = maximCouches;
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

