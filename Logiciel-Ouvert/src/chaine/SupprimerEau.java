package chaine;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class SupprimerEau {

	ChoixChampEau choixchamps;
	private ArrayList <String> selection = new ArrayList<String>();
	private String nomBase;
	private String port;
	private String nomUtilisateur;
	private String motDePasse;
	private String hote;

	public SupprimerEau(ChoixChampEau choixchamps) throws SQLException{

		System.out.println("test");

		this.choixchamps=choixchamps;
		choixchamps.dispose();
		choixchamps.setVisible(false);

		Statement stmt = null;

		this.selection=choixchamps.getSelection();

		//System.out.println("Test");
		this.setNomBase(ConnexionBaseDonnees.getNomBase());
		this.setHote(ConnexionBaseDonnees.getHote());
		this.setPort(ConnexionBaseDonnees.getPort());
		this.setNomUtilisateur(ConnexionBaseDonnees.getUser());
		this.setMotDePasse(ConnexionBaseDonnees.getPswd());

		Connection con = null;
		try{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://"+this.getHote()+":"+this.getPort()+"/"+this.getNomBase(),this.getNomUtilisateur(),this.getMotDePasse());


			for(String f:selection){
				System.out.println(f);
				if (con!=null){
					try{

						//st.execute("ALTER TABLE batiments DROP COLUMN surface_total ");
						//st.execute("ALTER TABLE eaux ADD COLUMN surface double precision");
						//st.executeUpdate("UPDATE eaux set surface=ST_AREA(geom_eaux)");

						stmt = con.createStatement();

						String query = "DELETE FROM eaux WHERE ID =\'"+f+"\'";;
						stmt.executeUpdate(query);

						//   while (rs.next()) {

						//   String test2 = rs.getString(this.selectedItem);
						//lignes.add(test2);

						//  int test = rs.getInt("sum");
						//  double test2 = rs.getDouble("sum");
						//float test = rs.getFloat("sum");
						//float price = rs.getFloat("PRICE");
						// int sales = rs.getInt("SALES");
						// int total = rs.getInt("TOTAL");
						//  System.out.println(test2);

						//  int test = rs.getInt("sum");
						//  double test2 = rs.getDouble("sum");
						//float test = rs.getFloat("sum");
						//float price = rs.getFloat("PRICE");
						// int sales = rs.getInt("SALES");
						// int total = rs.getInt("TOTAL");
						//  System.out.println(test2);




						//System.out.println(nomColonne.size());



						//System.out.println("Base de donn�es "+database+" cr�e");
					}
					catch (SQLException s){
						s.printStackTrace();// JDBCTutorialUtilities.printSQLException(s);
					}
				}
			}
		}
		catch (Exception b){
			b.printStackTrace();
			//JOptionPane.showMessageDialog(this,"Erreur : "+b,"Titre : exception",JOptionPane.ERROR_MESSAGE);
		}
		
		new EauxBuffers();
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

	public String getHote() {
		return hote;
	}

	public void setHote(String hote) {
		this.hote = hote;
	}

}


