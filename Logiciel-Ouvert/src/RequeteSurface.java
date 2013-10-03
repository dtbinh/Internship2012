import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class RequeteSurface extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Statement stmt = null;
	//RequeteCominerRasterFenetrePixel habit = new RequeteCominerRasterFenetrePixel();
	//	InsertSQL insert;
	FenetreTraitement fen;
	private String nomBase;
	private String port;
	private String nomUtilisateur;
	private String motDePasse;
	private String hote;
	private static boolean activer=false;

	public static boolean isActiver() {
		return activer;
	}

	public static void setActiver(boolean activer) {
		RequeteSurface.activer = activer;
	}

	private double surfaceTotale;
	private static ArrayList <String> shapes = new ArrayList <String>();
	private static ArrayList <String> shapes1 = new ArrayList <String>();
	private static ArrayList <String> srids = new ArrayList <String>();
	private static ArrayList <String> srids1 = new ArrayList <String>();

	public static ArrayList<String> getSrids() {
		return srids;
	}

	public static void setSrids(ArrayList<String> srids) {
		RequeteSurface.srids = srids;
	}

	private static ArrayList <String> tailles = new ArrayList <String>();


	public static ArrayList<String> getTailles() {
		return tailles;
	}

	public static void setTailles(ArrayList<String> tailles) {
		RequeteSurface.tailles = tailles;
	}

	ConnexionBaseDonnees connex;
	public RequeteSurface() throws SQLException {


		shapes=FenetreTraitement.getNomShape2();
		srids=FenetreTraitement.getSridss2();

		System.out.println("shapessize: "+shapes.size());

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


			for(int j=0;j<shapes.size();j++){
				if (con!=null){
					try{
						Statement st = con.createStatement();

						//st.execute("ALTER TABLE batiments DROP COLUMN surface_total ");
						System.out.println("ALTER TABLE "+shapes.get(j)+" ADD COLUMN surfpaluuudisme double precision");

						st.execute("ALTER TABLE "+shapes.get(j)+" ADD COLUMN surfpaluuudisme double precision");
						System.out.println("UPDATE "+shapes.get(j)+" set surfpaluuudisme=ST_AREA("+srids.get(j)+")");

						st.executeUpdate("UPDATE "+shapes.get(j)+" set surfpaluuudisme=ST_AREA("+srids.get(j)+")");

						stmt = con.createStatement();

						String query = "select SUM(surfpaluuudisme) from "+shapes.get(j)+" as sum";
						ResultSet rs = stmt.executeQuery(query);
						while (rs.next()) {

							//  String coffeeName = rs.getString("abc");
							//  int test = rs.getInt("sum");
							this.setSurfaceTotale(rs.getDouble("sum"));
							//	String surface = String.valueOf(this.getSurfaceTotale());
							tailles.add(String.valueOf(this.getSurfaceTotale()));
							if(!shapes1.contains(shapes.get(j))){
							shapes1.add(shapes.get(j));
							srids1.add(srids.get(j));
							}
							//float test = rs.getFloat("sum");
							//float price = rs.getFloat("PRICE");
							// int sales = rs.getInt("SALES");
							// int total = rs.getInt("TOTAL");
							System.out.println("surface pour: "+ shapes.get(j)+" = "+this.getSurfaceTotale());
						}

						st.execute("ALTER TABLE "+shapes.get(j)+" DROP COLUMN surfpaluuudisme");

						ConnexionTraitement.getArrayy().remove(shapes.get(j));
						ConnexionTraitement.getGeomms().remove(srids.get(j));
						System.out.println("nouvelle size: "+ConnexionTraitement.getArrayy().size());
					
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

		RequeteSurface.setActiver(true);
		ZFenetre.getTaillePixel().setEnabled(true);

		//System.out.println(ConnexionTraitement.getArrayy().size());
		//for (int abb=(ConnexionTraitement.getArrayy().size()-1);abb>=0;abb--){
			//System.out.println(ConnexionTraitement.getArrayy().size());
			//ConnexionTraitement.getArrayy().remove(abb);
			//System.out.println(abb);
			
			
		//}
		
		//FenetreTraitement.setNomShape1(FenetreTraitement.getNomShape2());
		//FenetreTraitement.setSridss(FenetreTraitement.getSridss2());


	}




	public static ArrayList<String> getShapes() {
		return shapes;
	}

	public static void setShapes(ArrayList<String> shapes) {
		RequeteSurface.shapes = shapes;
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

	public static ArrayList<String> getShapes1() {
		return shapes1;
	}

	public static void setShapes1(ArrayList<String> shapes1) {
		RequeteSurface.shapes1 = shapes1;
	}

	public static ArrayList<String> getSrids1() {
		return srids1;
	}

	public static void setSrids1(ArrayList<String> srids1) {
		RequeteSurface.srids1 = srids1;
	}




}



