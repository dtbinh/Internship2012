import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class RequeteRasterVector2Points extends JFrame implements ActionListener{
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
	public RequeteRasterVector2Points(FenetreVector2Points fen) throws SQLException {

		srids=RequeteUnionVector2Points.getSrids();
		this.fen=fen;
		tableaux=fen.getTableauTailles();


		for(int fg=0;fg<tableaux.size();fg++){
			String test34[][]=tableaux.get(fg);
			String taillePixels=test34[fg][1];
			taillePixel.add(taillePixels);

			String nomCouche=test34[fg][0];
			nomCouches.add(nomCouche);
		}

		for(int i=0;i<srids.size();i++){
			System.out.println("Dernier srdi: "+srids.get(i));
		}
		//shapes=ConnexionTraitement.getArrayy();


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


			for(int j=0;j<taillePixel.size();j++){
				if (con!=null){
					Statement st = con.createStatement();

					//st.execute("ALTER TABLE batiments DROP COLUMN surface_total ");

					System.out.println(nomCouches.get(j));
					/*						st.execute("CREATE TABLE "+nomCouches.get(j)+"2 as SELECT ST_BUFFER(geom_"+nomCouches.get(j)+",0.0) as geom_"+nomCouches.get(j)+" from "+nomCouches.get(j)+"");
						st.execute("CREATE TABLE "+nomCouches.get(j)+"_union as SELECT ST_UNION(geom_"+nomCouches.get(j)+") as geom_"+nomCouches.get(j)+" from "+nomCouches.get(j)+"2");
						st.execute("ALTER TABLE "+nomCouches.get(j)+"_union ADD COLUMN gid serial primary key");
					 */
					st.execute("DROP TABLE if exists "+nomCouches.get(j)+"_rasterpoints");

					st.execute("CREATE TABLE "+nomCouches.get(j)+"_rasterpoints as SELECT ST_asraster("+srids.get(j)+","+taillePixel.get(j)+",-"+taillePixel.get(j)+") as rast from "+nomCouches.get(j)+"_unionraster");
					st.execute("ALTER TABLE "+nomCouches.get(j)+"_rasterpoints ADD COLUMN rid serial primary key");

					st.execute("DROP TABLE "+nomCouches.get(j)+"_unionraster");

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

					nomRaster.add(nomCouches.get(j));
					srids1.add(srids.get(j));

					//System.out.println("Base de donn�es "+database+" cr�e");





					if(!nomCouches2.contains(nomCouches.get(j))){
						System.out.println("srids11111 "+ nomCouches.get(j));
						System.out.println("srids11111 "+ srids.get(j));
						nomCouches2.add(nomCouches.get(j));
						srids1.add(srids.get(j));
					}


					if(!shapes1.contains(nomCouches.get(j))){
						shapes1.add(nomCouches.get(j));
						//				srids1.add(srids.get(ff));

					}
					
					if(!nomRaster2.contains(nomCouches.get(j)+"_rasterpoints")){
						nomRaster2.add(nomCouches.get(j)+"_rasterpoints");
					}
					
					
					if(!taillePixel2.contains(taillePixel.get(j))){
						taillePixel2.add(taillePixel.get(j));
					}
				}
			}
		}



		catch (Exception b){
			b.printStackTrace();
			JOptionPane.showMessageDialog(this,"Erreur : "+b,"Titre : exception",JOptionPane.ERROR_MESSAGE);
		}


		ConnexionVector2Points.getArrayy().remove(nomRaster);
		ConnexionVector2Points.getGeomms().remove(srids1);
	//	DensitePopulation.getNomCouches2().removeAll(nomRaster);
	//	DensitePopulation.getSrids1().removeAll(srids1);


		nomCouches = new ArrayList<String>();
		shapes = new ArrayList<String>();
		srids = new ArrayList<String>();
		tableaux.removeAll(tableaux);

		RequeteUnionVector2Points.getSrids().removeAll(RequeteUnionVector2Points.getSrids());
		
		
		new RVector2Points();
		

		//		ZFenetre.getVisualiserRaster().setEnabled(true);

	}

	public static ArrayList<String> getNomRaster() {
		return nomRaster;
	}

	public static void setNomRaster(ArrayList<String> nomRaster) {
		RequeteRasterVector2Points.nomRaster = nomRaster;
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
		RequeteRasterVector2Points.srids = srids;
	}

	public static ArrayList<String> getSrids1() {
		return srids1;
	}

	public static void setSrids1(ArrayList<String> srids1) {
		RequeteRasterVector2Points.srids1 = srids1;
	}

	public static ArrayList<String> getNomCouches2() {
		return nomCouches2;
	}

	public static void setNomCouches2(ArrayList<String> nomCouches2) {
		RequeteRasterVector2Points.nomCouches2 = nomCouches2;
	}

	public static ArrayList<String> getShapes1() {
		return shapes1;
	}

	public static void setShapes1(ArrayList<String> shapes1) {
		RequeteRasterVector2Points.shapes1 = shapes1;
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
		RequeteRasterVector2Points.taillePixel = taillePixel;
	}

	public static ArrayList<String> getTaillePixel2() {
		return taillePixel2;
	}

	public static void setTaillePixel2(ArrayList<String> taillePixel2) {
		RequeteRasterVector2Points.taillePixel2 = taillePixel2;
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
		RequeteRasterVector2Points.nomRaster2 = nomRaster2;
	}






}



