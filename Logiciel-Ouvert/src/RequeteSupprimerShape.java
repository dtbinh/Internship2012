import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class RequeteSupprimerShape extends JFrame implements ActionListener{
	/**
	 * 
	 */
	FenetreRaster fen;
	private static final long serialVersionUID = 1L;
	Statement stmt = null;
	//RequeteCominerRasterFenetrePixel habit = new RequeteCominerRasterFenetrePixel();
	//	InsertSQL insert;
	private String nomBase;
	private String port;
	private String nomUtilisateur;
	private String motDePasse;
	private String hote;

	private double surfaceTotale;
	private ArrayList <String> shapes = new ArrayList <String>();


	private ArrayList <String> nomCouches = new ArrayList<String>();

	ConnexionBaseDonnees connex;
	public RequeteSupprimerShape() throws SQLException {

		nomCouches=FenetreSupprimerShape.getNomShape1();


		System.out.println("pooop");


		this.setNomBase(ConnexionBaseDonnees.getNomBase());
		this.setHote(ConnexionBaseDonnees.getHote());
		this.setPort(ConnexionBaseDonnees.getPort());
		this.setNomUtilisateur(ConnexionBaseDonnees.getUser());
		this.setMotDePasse(ConnexionBaseDonnees.getPswd());

		Connection con = null;
		try{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://"+this.getHote()+":"+this.getPort()+"/"+this.getNomBase(),this.getNomUtilisateur(),this.getMotDePasse());


			for(int j=0;j<nomCouches.size();j++){
				if (con!=null){
					Statement st = con.createStatement();

					System.out.println(nomCouches.get(j));

					st.execute("DROP TABLE if exists "+nomCouches.get(j));


				}
			}
		}



		catch (Exception b){
			b.printStackTrace();
			JOptionPane.showMessageDialog(this,"Erreur : "+b,"Titre : exception",JOptionPane.ERROR_MESSAGE);
		}


		
		ConnexionSupprimer.getArrayy().removeAll(ConnexionSupprimer.getArrayy());
		FenetreSupprimerShape.getNomShape1().removeAll(FenetreSupprimerShape.getNomShape1());
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

	public ArrayList<String> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<String> shapes) {
		this.shapes = shapes;
	}


	public ArrayList<String> getNomCouches() {
		return nomCouches;
	}

	public void setNomCouches(ArrayList<String> nomCouches) {
		this.nomCouches = nomCouches;
	}




}



