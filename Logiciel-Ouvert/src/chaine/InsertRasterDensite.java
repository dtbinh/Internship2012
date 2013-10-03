package chaine;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;


public class InsertRasterDensite {
	RasterShapeDensiteBatiments raster;
	ConnexionBaseDonnees connexion;
	
	private String pathtifsql;
	private String[] inserttifsql = null;
	private String nomServeur;
	private String port;
	private String utilisateur;
	private String nomBase;
	
	public InsertRasterDensite(RasterShapeDensiteBatiments raster) throws IOException{
		
		System.out.println("Start insert SQLTIF");
		this.raster=raster;
	//	this.connexion=connexion;
		this.setPathtifsql(raster.getRepertoireSQL()+"/densite_batiments_raster.sql");
		this.setNomBase(ConnexionBaseDonnees.getNomBase());
		this.setNomServeur(ConnexionBaseDonnees.getHote());
		this.setPort(ConnexionBaseDonnees.getPort());
		this.setUtilisateur(ConnexionBaseDonnees.getUser());
		int i=0;
		
		for (i=0;i<=10;i++){
			inserttifsql = new String[11];
			inserttifsql[i++]="psql";
			inserttifsql[i++]="-h";
			inserttifsql[i++]=this.getNomServeur();
			inserttifsql[i++]="-p";
			inserttifsql[i++]=this.getPort();
			inserttifsql[i++]="-U";
			inserttifsql[i++]=this.getUtilisateur();
			inserttifsql[i++]="-d";
			inserttifsql[i++]=this.getNomBase();
			inserttifsql[i++]="-f";
			inserttifsql[i++]=this.getPathtifsql();
		}
		for (i=0;i<=10;i++){	
			System.out.println(inserttifsql[i]);

		}
		


			ProcessBuilder launcherinsertSQL = new ProcessBuilder(inserttifsql);

			launcherinsertSQL.redirectErrorStream(true);

			Process pinsertSQL = launcherinsertSQL.start(); // And launch a new process

			BufferedReader outputinsertSQL = new BufferedReader(new InputStreamReader(pinsertSQL.getInputStream()));

			String lineinsertSQL = outputinsertSQL.readLine();

			while ((lineinsertSQL = outputinsertSQL.readLine())!= null){

							System.out.println(lineinsertSQL);
			}
			
			try {
				new RequeteEau();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		



	public String getPathtifsql() {
		return pathtifsql;
	}

	public void setPathtifsql(String pathtifsql) {
		this.pathtifsql = pathtifsql;
	}




	public String getNomServeur() {
		return nomServeur;
	}




	public void setNomServeur(String nomServeur) {
		this.nomServeur = nomServeur;
	}




	public String getPort() {
		return port;
	}




	public void setPort(String port) {
		this.port = port;
	}




	public String getUtilisateur() {
		return utilisateur;
	}




	public void setUtilisateur(String utilisateur) {
		this.utilisateur = utilisateur;
	}




	public String getNomBase() {
		return nomBase;
	}




	public void setNomBase(String nomBase) {
		this.nomBase = nomBase;
	}
	
	

}
