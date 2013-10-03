import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.regex.Pattern;


public class PostGIS extends Thread {

	ArrayList <String> Commande = new ArrayList <String>();
	ArrayList <String> CommandePostGIS = new ArrayList <String>();

	String nomBase = ConnexionBaseDonnees.getNomBase();
	String nomHost = ConnexionBaseDonnees.getHote();
	String nomUtil = ConnexionBaseDonnees.getUser();


	public String getNomHost() {
		return nomHost;
	}
	public void setNomHost(String nomHost) {
		this.nomHost = nomHost;
	}
	public String getNomUtil() {
		return nomUtil;
	}
	public void setNomUtil(String nomUtil) {
		this.nomUtil = nomUtil;
	}
	public String getNomBase() {
		return nomBase;
	}
	public void setNomBase(String nomBase) {
		this.nomBase = nomBase;
	}
	public PostGIS()  throws IOException  { 

		int i=0;
		String [] locatepostgis=null;
		locatepostgis= new String[2];
		locatepostgis[i++] = "locate";
		locatepostgis[i++] = "postgis.sql";

		ProcessBuilder launcherpostgis = new ProcessBuilder(locatepostgis);

		launcherpostgis.redirectErrorStream(true);

		Process ppostgis = launcherpostgis.start(); // And launch a new process

		BufferedReader outputpostgis = new BufferedReader(new InputStreamReader(ppostgis.getInputStream()));


		String linepostgis;
		String linex="";
		while ((linepostgis=outputpostgis.readLine()) != null){
			CommandePostGIS.add(linepostgis);
		}

		for(String x: CommandePostGIS){
			Pattern p1 = Pattern.compile("/");
			String [] split = p1.split(x);

			int length = split.length;


			String dernier = split[length-1];

			String detest=("postgis.sql");
			if(dernier.equals(detest)){

				linex=x;
			}


		}



		String[] PostSQL = null;
		for (i=0;i<8;i++){		  
			PostSQL = new String[9];
			PostSQL[i++] = "psql";
			PostSQL[i++] = "-h";
			PostSQL[i++] = this.getNomHost();
			PostSQL[i++] = "-U";
			PostSQL[i++] = this.getNomUtil();
			PostSQL[i++] = "-d";
			PostSQL[i++] = this.getNomBase().toLowerCase();
			PostSQL[i++] = "-f";
			PostSQL[i++] = linex;
		}

		for (i=0;i<=8;i++){
			System.out.println("test  "+PostSQL[i]);
		}

		ProcessBuilder launcher = new ProcessBuilder(PostSQL);

		launcher.redirectErrorStream(true);

		String directory="./";

		launcher.directory(new File(directory));

		Process p = launcher.start(); // And launch a new process

		BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));

		String line;
		while ((line = output.readLine()) != null){
			System.out.println(line);
		}
	}
}
