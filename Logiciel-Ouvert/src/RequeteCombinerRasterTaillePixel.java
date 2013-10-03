import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;



public class RequeteCombinerRasterTaillePixel {
	ConnexionBaseDonnees connexion;
	DensitePopulation densite;


	private String nomBase;
	private String port;
	private String nomUtilisateur;
	private String motDePasse;
	private String hote;
	
	private static String laplusgrande;

	private double surfaceTotale;


	private static ArrayList <String> nomCouches = new ArrayList <String> ();
	public static ArrayList<String> getNomCouches2() {
		return nomCouches2;
	}





	public static void setNomCouches2(ArrayList<String> nomCouches2) {
		RequeteCombinerRasterTaillePixel.nomCouches2 = nomCouches2;
	}





	private static ArrayList <String> nomCouches2 = new ArrayList <String> ();

	private static ArrayList <Double> tailles = new ArrayList <Double> ();



	private static ArrayList <String> maximCouches = new ArrayList <String> ();


	public RequeteCombinerRasterTaillePixel() throws SQLException{



		this.setNomBase(ConnexionBaseDonnees.getNomBase());
		this.setPort(ConnexionBaseDonnees.getPort());
		this.setNomUtilisateur(ConnexionBaseDonnees.getUser());
		this.setMotDePasse(ConnexionBaseDonnees.getPswd());
		this.setHote(ConnexionBaseDonnees.getHote());


		//		tableaux=FenetreReclassificationParametresClasses.getTableauTailles();
		//		tableaux2=FenetreReclassificationParametresClasses.getTableaux();

		nomCouches=FenetreCombinerRaster.getNomShape1();

		


			for(int ab=0;ab<nomCouches.size();ab++){
				


				//System.out.println("Test");
				Connection con = null;
				try{
					Class.forName("org.postgresql.Driver");
					con = DriverManager.getConnection("jdbc:postgresql://"+this.getHote()+":"+this.getPort()+"/"+this.getNomBase(),this.getNomUtilisateur(),this.getMotDePasse());

					if (con!=null){
						try{
							Statement st = con.createStatement();

							//st.execute("ALTER TABLE batiments DROP COLUMN surface_total ");
							
							st.execute("drop table if exists "+nomCouches.get(ab)+"_combinaiiison");
							
							st.execute("drop table if exists "+nomCouches.get(ab)+"_extent");


							st.execute("create table "+nomCouches.get(ab)+"_combinaiiison as select rid as rid, ST_Rescale(rast,"+RequeteCominerRasterFenetrePixel.getNombreHabitantsString()+",-"+RequeteCominerRasterFenetrePixel.getNombreHabitantsString()+") as rast from "+nomCouches.get(ab));

							System.out.println("create table "+nomCouches.get(ab)+"_combinaiiison as select rid as rid, ST_Rescale(rast,"+RequeteCominerRasterFenetrePixel.getNombreHabitantsString()+",-"+RequeteCominerRasterFenetrePixel.getNombreHabitantsString()+") as rast from "+nomCouches.get(ab));
				
							st.execute("create table "+nomCouches.get(ab)+"_extent as select st_envelope(rast) as st_envelope from "+nomCouches.get(ab)+"_combinaiiison");
							
							
							
							Statement stmt = con.createStatement();

							String query = "select st_area(st_envelope) as sum from "+nomCouches.get(ab)+"_extent";
							
							ResultSet rs = stmt.executeQuery(query);
							while (rs.next()) {

								//  String coffeeName = rs.getString("abc");
								//  int test = rs.getInt("sum");
								this.setSurfaceTotale(rs.getDouble("sum"));
								//	String surface = String.valueOf(this.getSurfaceTotale());
								tailles.add(surfaceTotale);
								nomCouches2.add(nomCouches.get(ab));
								
								//float test = rs.getFloat("sum");
								//float price = rs.getFloat("PRICE");
								// int sales = rs.getInt("SALES");
								// int total = rs.getInt("TOTAL");
								System.out.println("surface pour: "+ nomCouches2.get(ab)+" = "+tailles.get(ab));
							}

							st.execute("drop table "+nomCouches.get(ab)+"_extent");

							
							
						}
						catch (SQLException s){
							s.printStackTrace();// JDBCTutorialUtilities.printSQLException(s);
						}
					}
				}
			
			catch (Exception b){
				b.printStackTrace();
			}


			//	System.out.println("Surface: "+this.getSurfaceTotale());

			//new RequeteRasterVector2Points(fen);
		}

			 Object obj = Collections.max(tailles);
			    
			  //  String test = obj.toString();
			    Double test2 = Double.parseDouble(obj.toString());
			    System.out.println(test2);   
			    
			    for(int ab =0 ; ab<tailles.size();ab++){
			    	if(tailles.get(ab).compareTo(test2)==0){
			    		
					    laplusgrande=nomCouches.get(ab);
					    System.out.println(laplusgrande); 

			    	}
			    }

			    nomCouches.remove(laplusgrande);
			
				
				new RequeteCombinerRaster();
				
			
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

//		try {
//			new Raster2pgsqlReclassification();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}



	

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
		RequeteCombinerRasterTaillePixel.nomCouches = nomCouches;
	}

	public static ArrayList<String> getMaximCouches() {
		return maximCouches;
	}

	public static void setMaximCouches(ArrayList<String> maximCouches) {
		RequeteCombinerRasterTaillePixel.maximCouches = maximCouches;
	}





	public static String getLaplusgrande() {
		return laplusgrande;
	}





	public static void setLaplusgrande(String laplusgrande) {
		RequeteCombinerRasterTaillePixel.laplusgrande = laplusgrande;
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

