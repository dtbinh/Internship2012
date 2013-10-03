import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class RequeteSupprimerCombinaisonRaster extends JFrame implements ActionListener{
	/**
	 * 
	 */
	FenetreVector2Points fen;
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
	private static ArrayList <String> nomRaster = new ArrayList <String>();
	private static ArrayList <String> nomRaster2 = new ArrayList <String>();

	private static ArrayList <String> srids = new ArrayList <String>();
	private static ArrayList <String> srids1 = new ArrayList <String>();
	private static ArrayList <String> nomCouches2 = new ArrayList<String>();
	private static ArrayList <String> shapes1 = new ArrayList <String>();

	private ArrayList <String[][]> tableaux = new ArrayList<String[][]>();

	private static ArrayList <String> taillePixel = new ArrayList<String>();
	private static ArrayList <String> taillePixel2 = new ArrayList<String>();

	private ArrayList <String> nomCouches = new ArrayList<String>();

	ConnexionBaseDonnees connex;
	public RequeteSupprimerCombinaisonRaster() throws SQLException {




		this.setNomBase(ConnexionBaseDonnees.getNomBase());
		this.setHote(ConnexionBaseDonnees.getHote());
		this.setPort(ConnexionBaseDonnees.getPort());
		this.setNomUtilisateur(ConnexionBaseDonnees.getUser());
		this.setMotDePasse(ConnexionBaseDonnees.getPswd());
		
		nomCouches=RequeteCombinerRasterTaillePixel.getNomCouches2();


		//System.out.println("Test");
		Connection con = null;
		try{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://"+this.getHote()+":"+this.getPort()+"/"+this.getNomBase(),this.getNomUtilisateur(),this.getMotDePasse());


			for(int j=0;j<nomCouches.size();j++){
				if (con!=null){
					Statement st = con.createStatement();


	
					st.execute("DROP TABLE if exists "+nomCouches.get(j)+"_combinaiiison");
					st.execute("DROP TABLE if exists "+nomCouches.get(j)+"_extent");

			


				}
			}
		}


		catch (Exception b){
			b.printStackTrace();
			JOptionPane.showMessageDialog(this,"Erreur : "+b,"Titre : exception",JOptionPane.ERROR_MESSAGE);
		}


	

	}

	public static ArrayList<String> getNomRaster() {
		return nomRaster;
	}

	public static void setNomRaster(ArrayList<String> nomRaster) {
		RequeteSupprimerCombinaisonRaster.nomRaster = nomRaster;
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

	public static ArrayList<String> getSrids() {
		return srids;
	}

	public static void setSrids(ArrayList<String> srids) {
		RequeteSupprimerCombinaisonRaster.srids = srids;
	}

	public static ArrayList<String> getSrids1() {
		return srids1;
	}

	public static void setSrids1(ArrayList<String> srids1) {
		RequeteSupprimerCombinaisonRaster.srids1 = srids1;
	}

	public static ArrayList<String> getNomCouches2() {
		return nomCouches2;
	}

	public static void setNomCouches2(ArrayList<String> nomCouches2) {
		RequeteSupprimerCombinaisonRaster.nomCouches2 = nomCouches2;
	}

	public static ArrayList<String> getShapes1() {
		return shapes1;
	}

	public static void setShapes1(ArrayList<String> shapes1) {
		RequeteSupprimerCombinaisonRaster.shapes1 = shapes1;
	}

	public ArrayList<String[][]> getTableaux() {
		return tableaux;
	}

	public void setTableaux(ArrayList<String[][]> tableaux) {
		this.tableaux = tableaux;
	}



	public static ArrayList<String> getTaillePixel() {
		return taillePixel;
	}

	public static void setTaillePixel(ArrayList<String> taillePixel) {
		RequeteSupprimerCombinaisonRaster.taillePixel = taillePixel;
	}

	public static ArrayList<String> getTaillePixel2() {
		return taillePixel2;
	}

	public static void setTaillePixel2(ArrayList<String> taillePixel2) {
		RequeteSupprimerCombinaisonRaster.taillePixel2 = taillePixel2;
	}

	public ArrayList<String> getNomCouches() {
		return nomCouches;
	}

	public void setNomCouches(ArrayList<String> nomCouches) {
		this.nomCouches = nomCouches;
	}

	public static ArrayList<String> getNomRaster2() {
		return nomRaster2;
	}

	public static void setNomRaster2(ArrayList<String> nomRaster2) {
		RequeteSupprimerCombinaisonRaster.nomRaster2 = nomRaster2;
	}






}



