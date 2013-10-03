import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ExporterVecteurs extends Thread{

	ConnexionBaseDonnees connex;
	Fenetre fen;
	InsererBase insert;
	private String nomShape;
	private String repertoireSQL="./";
	private String finalShp;
	private ArrayList <String> nameSQL = new ArrayList <String>();
	private ArrayList <String> nomGeom = new ArrayList <String>();
	private ArrayList <String> nomTable = new ArrayList <String>();
	private ArrayList<String[]> shptoo= new ArrayList<String[]>();
	private ArrayList <String> pathssss = new ArrayList <String>();


	private String[] shp2pgsql = null;
	private ArrayList <String> pathSQL = new ArrayList <String>();
	//private String nomBase = ConnexionBaseDonnees.getNomBase();
	private ArrayList <String> shppgsql = new ArrayList <String>();
	//private ArrayList <String> shapePath = new ArrayList <String>();

	private String pathSapes="";
	private String pathsqlll;


	private String nomgeoms;
	private String projections;
	private String nomTables;

	

	public ArrayList<String> getNomGeom() {
		return nomGeom;
	}

	public void setNomGeom(ArrayList<String> nomGeom) {
		this.nomGeom = nomGeom;
	}

	public ArrayList<String> getNomTable() {
		return nomTable;
	}

	public void setNomTable(ArrayList<String> nomTable) {
		this.nomTable = nomTable;
	}

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
		this.repertoireSQL = repertoireSQL;
	}

//
//	public String getNomBase() {
//		return nomBase;
//	}
//
//	public void setNomBase(String nomBase) {
//		this.nomBase = nomBase;
//	}


	public ExporterVecteurs() throws IOException, SQLException   { 

		System.out.println("hello3");

	
		
		
		
		nomTable=FenetreExporterVecteurs.getNomShape1();

	

		int i=0;
		String [] locateshppgsql=null;
		locateshppgsql= new String[2];
		locateshppgsql[i++] = "locate";
		locateshppgsql[i++] = "pgsql2shp";

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

			String detest=("pgsql2shp");
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




		//for (String f:nameSQL){

		for(int j=0;j<nomTable.size();j++){


			for (i=0;i<=12;i++){		 

				shp2pgsql = new String[13];
				shp2pgsql[i++] = linex;
				shp2pgsql[i++] = "-f";
				shp2pgsql[i++] = "./"+nomTable.get(j);
				shp2pgsql[i++] = "-h";
				shp2pgsql[i++] = ConnexionBaseDonnees.getHote();
				shp2pgsql[i++] = "-u";
				shp2pgsql[i++] = ConnexionBaseDonnees.getUser();
				shp2pgsql[i++] = "-p";
				shp2pgsql[i++] = ConnexionBaseDonnees.getPort();
				shp2pgsql[i++] = "-P";
				shp2pgsql[i++] = ConnexionBaseDonnees.getPswd();
				shp2pgsql[i++] = ConnexionBaseDonnees.getNomBase();
				shp2pgsql[i++] = "SELECT * from "+nomTable.get(j);

			}

			//		pgsql2shp -f "/path/to/jpnei" -h myserver -u apguser -P apgpassword mygisdb "SELECT neigh_name, the_geom FROM neighborhoods WHERE neigh_name = 'Jamaica Plain'"
			
			for (i=0;i<=12;i++){	
				System.out.print("hallo:"+shp2pgsql[i]);

			}
			System.out.println(""); 
			shptoo.add(shp2pgsql);
		}


		for (int ai=0;ai<nomTable.size();ai++){
			System.out.println("bijour");

			ProcessBuilder launcherShp2pgsql = new ProcessBuilder(shptoo.get(ai));
			//ProcessBuilder launcherShp2pgsql2 = new ProcessBuilder()


			launcherShp2pgsql.redirectErrorStream(true);

			Process pShp2Pgsql = launcherShp2pgsql.start(); // And launch a new process

			BufferedReader outputShp2pgsql = new BufferedReader(new InputStreamReader(pShp2Pgsql.getInputStream()));

			//BufferedWriter varName = new BufferedWriter(new FileWriter(this.getRepertoireSQL()+"/"+this.nameSQL.get(ai)+".sql"));

			String lineShp2pgsql = outputShp2pgsql.readLine();
			while ((lineShp2pgsql = outputShp2pgsql.readLine())!= null){
			//	varName.write(lineShp2pgsql);
			//	varName.newLine();
				System.out.println(lineShp2pgsql);
			}

			//varName.close();
			System.out.println("testSQL");

		}
//		try {
//			new InsertSQL(this);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
	}



	//infoshppgsqll.setVisible(false);


	

	public ArrayList<String> pathSQL(){
		ArrayList <String> test = new ArrayList <String>();
		for (String e:pathSQL){
			test.add(e);
		}
		return test;
	}

	public ArrayList<String> getNameSQL() {
		return nameSQL;
	}

	public void setNameSQL(ArrayList<String> nameSQL) {
		this.nameSQL = nameSQL;
	}

	public ArrayList<String[]> shapetoo(){
		ArrayList <String[]> test = new ArrayList <String[]>();
		for (String[] e:shptoo){
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


	//	public synchronized void ) {
	//

	//
	//	}



}



