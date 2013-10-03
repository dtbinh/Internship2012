package chaine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class RequeteSurface extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Statement stmt = null;
	//RequeteCominerRasterFenetrePixel habit = new RequeteCominerRasterFenetrePixel();
	DensitePopulation densite;

	private String nomBase;
	private String port;
	private String nomUtilisateur;
	private String motDePasse;
	private String hote;

	private double surfaceTotale;

	ConnexionBaseDonnees connex;
	public RequeteSurface(ConnexionBaseDonnees connex, DensitePopulation densite) throws SQLException {

		this.connex=connex;
		this.setNomBase(ConnexionBaseDonnees.getNomBase());
		this.setHote(ConnexionBaseDonnees.getHote());
		this.setPort(ConnexionBaseDonnees.getPort());
		this.setNomUtilisateur(ConnexionBaseDonnees.getUser());
		this.setMotDePasse(ConnexionBaseDonnees.getPswd());

		//System.out.println("Test");
		Connection con = null;
		try{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://"+this.getHote()+":"+this.getPort()+"/"+this.getNomBase(),this.getNomUtilisateur(),this.getMotDePasse());

			if (con!=null){
				try{
					Statement st = con.createStatement();

					//st.execute("ALTER TABLE batiments DROP COLUMN surface_total ");
					st.execute("CREATE TABLE bati_union as SELECT ST_UNION(geom_batiements) as geom_batiments from batiments");
					st.execute("ALTER TABLE bati_union ADD COLUMN gid serial primary key");

					st.execute("ALTER TABLE batiments ADD COLUMN surface_totale double precision");
					st.executeUpdate("UPDATE batiments set surface_totale=ST_AREA(geom_batiements)");

					stmt = con.createStatement();

					String query = "select SUM(surface_totale) from batiments as somme";
					ResultSet rs = stmt.executeQuery(query);
					while (rs.next()) {

						//  String coffeeName = rs.getString("abc");
						//  int test = rs.getInt("sum");
						this.setSurfaceTotale(rs.getDouble("sum"));
						//float test = rs.getFloat("sum");
						//float price = rs.getFloat("PRICE");
						// int sales = rs.getInt("SALES");
						// int total = rs.getInt("TOTAL");
						//System.out.println(this.getSurfaceTotale());
					}

					//System.out.println("Base de donn�es "+database+" cr�e");
				}
				catch (SQLException s){
					s.printStackTrace();// JDBCTutorialUtilities.printSQLException(s);
				}
			}
		}
		catch (Exception b){
			b.printStackTrace();
			JOptionPane.showMessageDialog(this,"Erreur : "+b,"Titre : exception",JOptionPane.ERROR_MESSAGE);
		}

		NombreHabitants habitat = new NombreHabitants(this);

		habitat.setVisible(true);
		System.out.println("Surface: "+this.getSurfaceTotale());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

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

	public String getHote() {
		return hote;
	}

	public void setHote(String hote) {
		this.hote = hote;
	}




}



