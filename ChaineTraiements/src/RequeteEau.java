import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;



public class RequeteEau extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Statement stmt = null;
	ConnexionBaseDonnees connexion;

	private String nomBase;
	private String port;
	private String nomUtilisateur;
	private String motDePasse;
	private String hote;

	private ArrayList <String> nomColonne = new ArrayList <String>();

	public RequeteEau() throws SQLException {
		//this.connexion=connexion;
		this.setNomBase(ConnexionBaseDonnees.getNomBase());
		this.setHote(ConnexionBaseDonnees.getHote());
		this.setPort(ConnexionBaseDonnees.getPort());
		this.setNomUtilisateur(ConnexionBaseDonnees.getUser());
		this.setMotDePasse(ConnexionBaseDonnees.getPswd());

		Connection con = null;
		try{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://"+this.getHote()+":"+this.getPort()+"/"+this.getNomBase(),this.getNomUtilisateur(),this.getMotDePasse());


			if (con!=null){
				try{


					//st.execute("ALTER TABLE batiments DROP COLUMN surface_total ");
					//st.execute("ALTER TABLE eaux ADD COLUMN surface double precision");
					//st.executeUpdate("UPDATE eaux set surface=ST_AREA(geom_eaux)");

					stmt = con.createStatement();

					String query = "select column_name as colonnes from information_schema.columns where table_name=\'eaux\'";
					ResultSet rs = stmt.executeQuery(query);
					while (rs.next()) {

						String test2 = rs.getString("colonnes");
						nomColonne.add(test2);

						//  int test = rs.getInt("sum");
						//  double test2 = rs.getDouble("sum");
						//float test = rs.getFloat("sum");
						//float price = rs.getFloat("PRICE");
						// int sales = rs.getInt("SALES");
						// int total = rs.getInt("TOTAL");
						//  System.out.println(test2);
					}



					System.out.println(nomColonne.size());
					for (String e:nomColonne){

						System.out.println(e);
					}
					new ListeDerou(this);

					//System.out.println("Base de donn�es "+database+" cr�e");
				}
				catch (SQLException s){
					s.printStackTrace();// JDBCTutorialUtilities.printSQLException(s);
				}
			}
		}
		catch (Exception b){
			b.printStackTrace();
			//JOptionPane.showMessageDialog(this,"Erreur : "+b,"Titre : exception",JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public ArrayList<String> getNomColonne() {
		return nomColonne;
	}

	public void setNomColonne(ArrayList<String> nomColonne) {
		this.nomColonne = nomColonne;
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



