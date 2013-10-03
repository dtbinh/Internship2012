//import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class RasterShapeDensiteBatiments extends Thread{

	InfoShp2pgsql infoshp;
	ConnexionBaseDonnees connex;
	OuvrirFenetre ouvrir;
	ChoisirRepertoireSQL choix;
	private String nomShape;
	private static String repertoireSQL;
	private ArrayList <String> pathShape = OuvrirFenetre.pathArray();
	private String finalShp;
	private String pathtif;
	//	private ArrayList <String> nameSQL = new ArrayList <String>();
	//	private ArrayList <String> nomGeom = new ArrayList <String>();
	//	private ArrayList <String> nomTable = new ArrayList <String>();
	private ArrayList<String[]> shptoo= new ArrayList<String[]>();
	private ArrayList <String> pathssss = new ArrayList <String>();


	private String[] shp2pgsql = null;
	//	private int i=0;
	private ArrayList <String> pathSQL = new ArrayList <String>();
	private String nomBase = ConnexionBaseDonnees.getNomBase();
	private String nomGeomm;
	private ArrayList <String> shppgsql = new ArrayList <String>();
	//	private ArrayList <String> shapePath = new ArrayList <String>();

	private String pathSapes="";
	private String pathsqlll;


	private String nomgeoms;
	private String projections;
	private String nomTables;


	public String getPathsqlll() {
		return pathsqlll;
	}

	public void setPathsqlll(String pathsqlll) {
		this.pathsqlll = pathsqlll;
	}

	public String getPathSapes() {
		return pathSapes;
	}

	public void setPathSapes(String pathSapes) {
		this.pathSapes = pathSapes;
	}

	public String getNomgeoms() {
		return nomgeoms;
	}

	public void setNomgeoms(String nomgeoms) {
		this.nomgeoms = nomgeoms;
	}

	public String getProjections() {
		return projections;
	}

	public void setProjections(String projections) {
		this.projections = projections;
	}

	public String getNomTables() {
		return nomTables;
	}

	public void setNomTables(String nomTables) {
		this.nomTables = nomTables;
	}

	public String getFinalShp() {
		return finalShp;
	}

	public void setFinalShp(String finalShp) {
		this.finalShp = finalShp;
	}

	public String getNomShape() {
		return nomShape;
	}

	public void setNomShape(String nomShape) {
		this.nomShape = nomShape;
	}

	public String getRepertoireSQL() {
		return repertoireSQL;
	}

	public void setRepertoireSQL(String repertoireSQL) {
		RasterShapeDensiteBatiments.repertoireSQL = repertoireSQL;
	}


	public String getNomBase() {
		return nomBase;
	}

	public void setNomBase(String nomBase) {
		this.nomBase = nomBase;
	}


	public RasterShapeDensiteBatiments(	ConnexionBaseDonnees connex,ChoisirRepertoireSQL choix) throws IOException, SQLException   { 

		System.out.println("hello3");
		nomGeomm=ConnexionBaseDonnees.getProjection();
		RasterShapeDensiteBatiments.repertoireSQL=ChoisirRepertoireSQL.getPath();
		//	nomGeom=infoshp.getNomGeomArray();
		//	nomTable=infoshp.getNomTableArray();

		this.connex=connex;
		this.choix=choix;
		//		this.infoshp=infoshp;

		//shapePath=infoshp.getCorrespon();
		int i=0;
		String [] locateshppgsql=null;
		locateshppgsql= new String[2];
		locateshppgsql[i++] = "locate";
		locateshppgsql[i++] = "raster2pgsql";

		ProcessBuilder launchershppgsql = new ProcessBuilder(locateshppgsql);

		launchershppgsql.redirectErrorStream(true);

		Process pshppgsql = launchershppgsql.start(); // And launch a new process

		BufferedReader outputshppgsql = new BufferedReader(new InputStreamReader(pshppgsql.getInputStream()));


		String lineshppgsql;
		String linex="";
		while ((lineshppgsql=outputshppgsql.readLine()) != null){
			shppgsql.add(lineshppgsql);
		}

		for(String x: shppgsql){
			Pattern p1 = Pattern.compile("/");
			String [] split = p1.split(x);

			int length = split.length;


			String dernier = split[length-1];

			String detest=("raster2pgsql");
			if(dernier.equals(detest)){

				linex=x;
			}


		}



		//		System.out.println(nomTables);
		//		System.out.println(projections);
		//		System.out.println(nomgeoms);
		//		
		//		for (String e:pathShape){
		//			for (String f:nomTable){
		//				for (String h:nomGeom){
		//					for (String j:projection){

		//infoshppgsqll.setVisible(true);

		/*	for (int xy=0;xy<shapePath.size();xy++){

			this.setPathSapes(shapePath.get(xy));
			System.out.println("Path2"+this.shapePath.get(xy));

			System.out.println("Path"+this.getPathSapes());
			Pattern p = Pattern.compile("/");
			String [] split = p.split(pathSapes);

			int length = split.length;

			String dernier = split[length-1];

			Pattern point = Pattern.compile("\\.");

			String [] splitPoint = point.split(dernier);

			finalShp = splitPoint[0];

			nameSQL.add(finalShp);

			this.setPathsqlll(this.getRepertoireSQL()+"/"+this.nameSQL.get(xy)+".sql");
			pathssss.add(this.getPathsqlll());
			//		pathSQL.add(this.getRepertoireSQL()+"/"+this.getFinalShp()+".sql");

		}*/


		//for (String f:nameSQL){

		//	for(int j=0;j<pathShape.size();j++){

		pathtif=this.getRepertoireSQL()+"/densite_batiment.tif";

		for (i=0;i<=5;i++){		 

			shp2pgsql = new String[6];
			shp2pgsql[i++] = linex;
			shp2pgsql[i++] = "-d";
			shp2pgsql[i++] = "-s";
			shp2pgsql[i++] = this.nomGeomm;
			shp2pgsql[i++] = this.pathtif;
			shp2pgsql[i++] = "densite_batiment";

		}


		for (i=0;i<=5;i++){	
			System.out.print(shp2pgsql[i]);

		}
		System.out.println(""); 
		shptoo.add(shp2pgsql);
		System.out.println("size"+shptoo.size()); 




		ProcessBuilder launcherShp2pgsql = new ProcessBuilder(shp2pgsql);
		//ProcessBuilder launcherShp2pgsql2 = new ProcessBuilder()


		launcherShp2pgsql.redirectErrorStream(true);

		Process pShp2Pgsql = launcherShp2pgsql.start(); // And launch a new process

		BufferedReader outputShp2pgsql = new BufferedReader(new InputStreamReader(pShp2Pgsql.getInputStream()));

		BufferedWriter varName = new BufferedWriter(new FileWriter(this.getRepertoireSQL()+"/densite_batiments_raster.sql"));

		String lineShp2pgsql = outputShp2pgsql.readLine();
		while ((lineShp2pgsql = outputShp2pgsql.readLine())!= null){
			varName.write(lineShp2pgsql);
			varName.newLine();
			//System.out.println(lineShp2pgsql);
			//System.out.println(varName);

		}

		varName.close();
		System.out.println("testSQL");

		new InsertRasterDensite(this);

	}
	//InsertRasterDensite insertdensite = new InsertRasterDensite();




	//infoshppgsqll.setVisible(false);






	public ArrayList<String> pathSQL(){
		ArrayList <String> test = new ArrayList <String>();
		for (String e:pathSQL){
			test.add(e);
		}
		return test;
	}

	public ArrayList<String[]> shapetoo(){
		ArrayList <String[]> test = new ArrayList <String[]>();
		for (String[] e:shptoo){
			test.add(e);
		}
		return test;
	}
	public ArrayList<String>pathShape(){
		ArrayList <String> test = new ArrayList <String>();
		for (String e:pathShape){
			test.add(e);
		}
		return test;
	}

	public ArrayList<String>pathssss(){
		ArrayList <String> test = new ArrayList <String>();
		for (String e:pathssss){
			test.add(e);
		}
		return test;
	}

	public String getPathtif() {
		return pathtif;
	}

	public void setPathtif(String pathtif) {
		this.pathtif = pathtif;
	}


	//	public synchronized void ) {
	//
	//		
	//
	//	}



}



