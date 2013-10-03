import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.regex.Pattern;



public class SpatialRef extends Thread {


	ArrayList <String> CommandeSpatialRef = new ArrayList <String>();


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





	public SpatialRef() throws IOException   { 



		int i=0;
		String [] locatespatialref=null;
		locatespatialref= new String[2];
		locatespatialref[i++] = "locate";
		locatespatialref[i++] = "spatial_ref_sys.sql";

		ProcessBuilder launcherspatialref = new ProcessBuilder(locatespatialref);

		launcherspatialref.redirectErrorStream(true);

		Process pspatialref = launcherspatialref.start(); // And launch a new process

		BufferedReader outputspatialref = new BufferedReader(new InputStreamReader(pspatialref.getInputStream()));


		String linespatialref;
		String linex="";
		while ((linespatialref=outputspatialref.readLine()) != null){
			CommandeSpatialRef.add(linespatialref);
		}

		for(String x: CommandeSpatialRef){
			Pattern p1 = Pattern.compile("/");
			String [] split = p1.split(x);
			int length = split.length;
			String dernier = split[length-1];
			String detest=("spatial_ref_sys.sql");
			if(dernier.equals(detest)){

				linex=x;
			}


		}


		String[] SpatialREF = null;
		for (i=0;i<8;i++){		  
			SpatialREF = new String[9];
			SpatialREF[i++] = "psql";
			SpatialREF[i++] = "-h";
			SpatialREF[i++] = this.getNomHost();
			SpatialREF[i++] = "-U";
			SpatialREF[i++] = this.getNomUtil();
			SpatialREF[i++] = "-d";
			SpatialREF[i++] = this.getNomBase().toLowerCase();
			SpatialREF[i++] = "-f";
			SpatialREF[i++] = linex;
		}

		for (i=0;i<=8;i++){
			System.out.println("test  "+SpatialREF[i]);
		}

		ProcessBuilder launcherSpatial = new ProcessBuilder(SpatialREF);

		launcherSpatial.redirectErrorStream(true);

		String directory="./";

		launcherSpatial.directory(new File(directory));

		Process pSpatial = launcherSpatial.start(); // And launch a new process

		BufferedReader outputSpatial = new BufferedReader(new InputStreamReader(pSpatial.getInputStream()));

		String lineSpatial;
		while ((lineSpatial = outputSpatial.readLine()) != null){
			System.out.println(lineSpatial);
		}
	}
}
