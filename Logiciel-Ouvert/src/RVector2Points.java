import java.io.IOException;
import java.sql.SQLException;

import rcaller.RCaller;
import rcaller.RCode;

public class RVector2Points {
	ConnexionBaseDonnees connexion;
	DensitePopulation densite;

	
	private String nomBase;
	private String port;
	private String nomUtilisateur;
	private String motDePasse;
	private String hote;
	private double surfaceTotale;
	private double taillePixel; 

	public RVector2Points(){
		
		this.setNomBase(ConnexionBaseDonnees.getNomBase());
		this.setPort(ConnexionBaseDonnees.getPort());
		this.setNomUtilisateur(ConnexionBaseDonnees.getUser());
		this.setMotDePasse(ConnexionBaseDonnees.getPswd());
		this.setHote(ConnexionBaseDonnees.getHote());
		this.setTaillePixel(DensitePopulation.getTaillePixel());
		
		System.out.println("RVectorPoints");

		
		System.out.println("pixels"+RequeteRasterVector2Points.getTaillePixel2().size());


		for(int i=0;i<RequeteRasterVector2Points.getNomCouches2().size();i++){
		
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
		code.addRCode("layers <- readGDAL(\"PG:host="+this.hote+" user="+this.nomUtilisateur+" dbname="+this.nomBase+" password="+this.motDePasse+" port="+this.port+" table="+RequeteRasterVector2Points.getNomRaster2().get(i)+"\")");
		System.out.println("layers <- readGDAL(\"PG:host="+this.hote+" user="+this.nomUtilisateur+" dbname="+this.nomBase+" password="+this.motDePasse+" port="+this.port+" table="+RequeteRasterVector2Points.getNomRaster2().get(i)+"\")");

		code.addRCode("layers <-raster(layers)");
		code.addRCode("points <- rasterToPoints(layers,fun=function(x){x>0},spatial=TRUE)");
		code.addRCode("pts <- as(points, \"ppp\")");
	
		code.addRCode("dsty <- density.ppp(pts,eps="+RequeteRasterVector2Points.getTaillePixel2().get(i)+","+RequeteRasterVector2Points.getTaillePixel2().get(i)+",adjust="+RequeteRasterVector2Points.getTaillePixel2().get(i)+")");
		code.addRCode("ab <- as(dsty,\"SpatialGridDataFrame\")");
		code.addRCode("ras <- raster(ab)");
		
		code.addRCode("minim <- minValue(ras)");
		code.addRCode("m <- c(minim-10,-0.000000000000000000000000000000000000000000000000000000000000000000000000001,0)");
		code.addRCode("rclmat <- matrix(m, ncol=3, byrow=TRUE)");
		code.addRCode("rc <- reclass(ras,rclmat)");
		code.addRCode("rc.sp <- as(rc, \"SpatialPixelsDataFrame\")");			
		code.addRCode("writeGDAL(rc.sp,"+"\""+RequeteRasterVector2Points.getNomRaster2().get(i)+"_densite.tif\", drivername=\"GTiff\")");
		System.out.println("writeGDAL(rc.sp,"+"\""+RequeteRasterVector2Points.getNomRaster2().get(i)+"_densite.tif\", drivername=\"GTiff\")");


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

		caller.setRCode(code);
		//rcaller.setRCode(code);
		
		  caller.runOnly();

		 // caller.getParser().getAsStringArray("result");
		  
//		  code.showPlot(file2);
//		  
//			code.endPlot();
//
		}
		
		
		try {
			new Raster2pgsqlVector2Points();
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

