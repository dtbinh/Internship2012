import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class RequeteRenommerRaster extends JFrame implements ActionListener{
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
	private static ArrayList <String> srids = new ArrayList <String>();
	public boolean b = false;



	private ArrayList <String[][]> tableaux = new ArrayList<String[][]>();
	private ArrayList <String> nouveauNom = new ArrayList<String>();

	private ArrayList <String> taillePixel = new ArrayList<String>();
	private ArrayList <String> nomCouches = new ArrayList<String>();
	private static ArrayList <String> comparaison = new ArrayList<String>();

	public static ArrayList<String> getComparaison() {
		return comparaison;
	}


	public static void setComparaison(ArrayList<String> comparaison) {
		RequeteRenommerRaster.comparaison = comparaison;
	}


	ConnexionBaseDonnees connex;
	public RequeteRenommerRaster() throws SQLException {

		tableaux.clear();
		srids.clear();
		taillePixel.clear();
		nomCouches.clear();
		nouveauNom.clear();

		tableaux=FenetreRenommerRaster.getTableauTailles();

		//	comparaison=ConnexionVector2Points.getArrayy4();



		for(int fg=0;fg<tableaux.size();fg++){
			String test34[][]=tableaux.get(fg);
			String taillePixels=test34[fg][1];
			String nouveau=taillePixels.replaceAll("\\W", "").toLowerCase();
			System.out.println(nouveau);
			nouveauNom.add(nouveau);
			String nomCouche=test34[fg][0].toString();
			nomCouches.add(nomCouche);
		}
		srids=FenetreRenommerRaster.getSrids();

		for(String bla:srids){
			System.out.println("srids"+bla);
		}

		//	for(int fg=0;fg<comparaison.size();fg++){
		//		System.out.println("comapraison: "+comparaison.get(fg));
		//	}


		//	for(int siz=0;siz<ConnexionVector2Points.getGeomms4().size();siz++){
		//		System.out.println("siz22 "+ConnexionVector2Points.getGeomms4().get(siz));
		//	}

		/*		for(int i=0;i<nomCouches.size();i++){


			String trouver="";
			b=false;
			String trouve = nomCouches.get(i);
			System.out.println("nomcouches: "+trouve);

			if(comparaison.contains(trouve)){
				b=true;
				if(b=true){
					trouver=nomCouches.get(i);
					System.out.println("trouver: "+trouver);

					for(int j=0;j<comparaison.size();j++){
						if(comparaison.get(j).contains(trouver)){
						srids.add(ConnexionVector2Points.getGeomms4().get(j));
					}
					}


					//srids.add(ConnexionRaster.getGeomms().get(comparaison.indexOf(trouver)));
				}
			}

		}*/

		System.out.println("renommer size: "+srids.size());

		for(String siz : srids){
			System.out.println("siz "+siz);
		}

		System.out.println("renommer size2: "+nomCouches.size());


		for(int i=0;i<srids.size();i++){

			System.out.println("renommer : "+ srids.get(i) +" "+ nomCouches.get(i));
		}
		//shapes=ConnexionUnion.getArrayy();


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


			for(int j=0;j<nomCouches.size();j++){
				if (con!=null){
					try{
						Statement st = con.createStatement();

						//st.execute("ALTER TABLE batiments DROP COLUMN surface_total ");
						System.out.println("ALTER TABLE "+nomCouches.get(j)+" RENAME TO "+ nouveauNom.get(j));

						st.execute("ALTER TABLE "+nomCouches.get(j)+" RENAME TO "+ nouveauNom.get(j));

						//					stmt = con.createStatement();
						//
						//					String query = "select SUM(surfpalu) from "+shapes.get(j)+" as sum";
						//					ResultSet rs = stmt.executeQuery(query);
						//					while (rs.next()) {
						//
						//						//  String coffeeName = rs.getString("abc");
						//						//  int test = rs.getInt("sum");
						//						this.setSurfaceTotale(rs.getDouble("sum"));
						//						//float test = rs.getFloat("sum");
						//						//float price = rs.getFloat("PRICE");
						//						// int sales = rs.getInt("SALES");
						//						// int total = rs.getInt("TOTAL");
						//						System.out.println("surface totalefeneraster pour:"+ shapes.get(j)+" "+this.getSurfaceTotale());
						//					}
						//					
						//					st.execute("ALTER TABLE "+shapes.get(j)+" DROP COLUMN surfpalu");


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
			JOptionPane.showMessageDialog(this,"Erreur : "+b,"Titre : exception",JOptionPane.ERROR_MESSAGE);
		}


		//	System.out.println("Surface: "+this.getSurfaceTotale());

		//new RequeteRasterVector2Points(fen);
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
		RequeteRenommerRaster.srids = srids;
	}





}



