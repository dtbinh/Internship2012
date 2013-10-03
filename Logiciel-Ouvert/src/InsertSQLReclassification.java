import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsertSQLReclassification {

	ConnexionBaseDonnees connexion;
	Raster2pgsqlReclassification shp2;


	private ArrayList <String> pathSQL = new ArrayList<String>();
	private ArrayList <String[]> shp2pgsql = new ArrayList<String[]>();

	private ArrayList <String> cheminshp = new ArrayList<String>();

	private String[] insertSQL = null;

	private String nomServeur = ConnexionBaseDonnees.getHote();
	private String port = ConnexionBaseDonnees.getPort();
	private String utilisateur = ConnexionBaseDonnees.getUser();
	private String nomBase = ConnexionBaseDonnees.getNomBase();
	private int i=0;



	public ArrayList<String> getPathSQL() {
		return pathSQL;
	}

	public void setPathSQL(ArrayList<String> pathSQL) {
		this.pathSQL = pathSQL;
	}

	public String getNomServeur() {
		return nomServeur;
	}

	public void setNomServeur(String nomServeur) {
		this.nomServeur = nomServeur;
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


	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public InsertSQLReclassification(Raster2pgsqlReclassification shp2) throws IOException, SQLException{

		System.out.println("insertsql");



		for(int j=0;j<FenetreReclassificationParametresClasses.getNomCouches().size();j++){


			for (i=0;i<=10;i++){
				insertSQL = new String[11];
				insertSQL[i++]="psql";
				insertSQL[i++]="-h";
				insertSQL[i++]=this.getNomServeur();
				insertSQL[i++]="-p";
				insertSQL[i++]=this.getPort();
				insertSQL[i++]="-U";
				insertSQL[i++]=this.getUtilisateur();
				insertSQL[i++]="-d";
				insertSQL[i++]=this.getNomBase();
				insertSQL[i++]="-f";
				insertSQL[i++]="./"+FenetreReclassificationParametresClasses.getNomCouches().get(j)+"_reclassifie.sql";
			}
			for (i=0;i<=10;i++){	
				System.out.println(insertSQL[i]);

			}
			shp2pgsql.add(insertSQL);
		}

		for(int b=0;b<shp2pgsql.size();b++){

			ProcessBuilder launcherinsertSQL = new ProcessBuilder(shp2pgsql.get(b));

			launcherinsertSQL.redirectErrorStream(true);

			Process pinsertSQL = launcherinsertSQL.start(); // And launch a new process

			BufferedReader outputinsertSQL = new BufferedReader(new InputStreamReader(pinsertSQL.getInputStream()));

			String lineinsertSQL = outputinsertSQL.readLine();

			while ((lineinsertSQL = outputinsertSQL.readLine())!= null){

				System.out.println(lineinsertSQL);
			}


		}
		//new RequeteSupprimerPoints();

	}

	public ArrayList <String> getCheminshp() {
		return cheminshp;
	}

	public void setCheminshp(ArrayList <String> cheminshp) {
		this.cheminshp = cheminshp;
	}

}


