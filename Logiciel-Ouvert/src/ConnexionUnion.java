
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class ConnexionUnion extends JFrame{

	private static final long serialVersionUID = 1L;

	public JTextField saisie1;
	public JTextField saisie2;
	public JTextField saisie3;
	public JTextField saisie4;
	public JTextField saisie6;
	public JTextField saisie7;



	public static boolean visibile;

	public static String hote;
	public static String user;
	public static String pswd;
	public static String nomBase;
	public static String port;
	public static String projection;

	public ArrayList <String> shapes = OuvrirFenetre.pathArray();
	private static ArrayList <String> arrayy = new ArrayList <String>();
	private static ArrayList <String> geomms = new ArrayList <String>();
	private static ArrayList <String> arrayy2 = new ArrayList <String>();
	private static ArrayList <String> geomms2 = new ArrayList <String>();
	private static ArrayList <String> geomms4 = new ArrayList <String>();



	private static ArrayList <String> arrayy3 = new ArrayList <String>();
	private static ArrayList <String> arrayy4 = new ArrayList <String>();


	public static ArrayList<String> getArrayy2() {
		return arrayy2;
	}

	public static void setArrayy2(ArrayList<String> arrayy2) {
		ConnexionUnion.arrayy2 = arrayy2;
	}

	public static ArrayList<String> getGeomms2() {
		return geomms2;
	}

	public static void setGeomms2(ArrayList<String> geomms2) {
		ConnexionUnion.geomms2 = geomms2;
	}

	public static ArrayList<String> getGeomms() {
		return geomms;
	}

	public static void setGeomms(ArrayList<String> geomms) {
		ConnexionUnion.geomms = geomms;
	}

	public static String getHote() {
		return hote;
	}

	public static void setHote(String hote) {
		ConnexionBaseDonnees.hote = hote;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		ConnexionBaseDonnees.user = user;
	}

	public static String getPswd() {
		return pswd;
	}

	public static void setPswd(String pswd) {
		ConnexionBaseDonnees.pswd = pswd;
	}

	public static String getNomBase() {
		return nomBase;
	}

	public static void setNomBase(String nomBase) {
		ConnexionBaseDonnees.nomBase = nomBase;
	}

	public static String getPort() {
		return port;
	}

	public static void setPort(String port) {
		ConnexionBaseDonnees.port = port;
	}

	public static String getProjection() {
		return projection;
	}

	public static void setProjection(String projection) {
		ConnexionBaseDonnees.projection = projection;
	}

	public static ArrayList<String> getArrayy() {
		return arrayy;
	}

	public static void setArrayy(ArrayList<String> arrayy) {
		ConnexionUnion.arrayy = arrayy;
	}



	public static ArrayList<String> getArrayy3() {
		return arrayy3;
	}

	public static void setArrayy3(ArrayList<String> arrayy3) {
		ConnexionUnion.arrayy3 = arrayy3;
	}

	public ConnexionUnion(){

		arrayy=new ArrayList<String>();
		geomms=new ArrayList<String>();

		arrayy.clear();
		geomms.clear();

		Connection con = null;

		try{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://"+ConnexionBaseDonnees.getHote()+":"+ConnexionBaseDonnees.getPort()+"/"+ConnexionBaseDonnees.getNomBase(),ConnexionBaseDonnees.getUser(),ConnexionBaseDonnees.getPswd());

			if (con!=null){
				try{

					Statement stmt = con.createStatement();

					String query = "SELECT f_table_name as tables, f_geometry_column as geom FROM geometry_columns where f_table_name not like \'spatial_ref_sys\' and f_table_name not like \'%_raster%\' and f_table_name not like \'%_union%'";
					ResultSet rs = stmt.executeQuery(query);

					System.out.println("testttt22");

					while (rs.next()) {

						System.out.println("testttt");


						String tablename = rs.getString("tables");
						String geom = rs.getString("geom");

						if(!arrayy.contains(tablename)){
							arrayy.add(tablename);
							geomms.add(geom);
							System.out.println("tablename"+ tablename);

						}


					}

					for (String ef:arrayy){
						System.out.println("ConnexRaster: "+ef);
					}


				}
				catch (SQLException s){
					System.out.println("SQL statement is not executed!");
				}
			}
		}
		catch (Exception b){
			b.printStackTrace();
			JOptionPane.showMessageDialog(this,"Erreur : "+b,"Titre : exception",JOptionPane.ERROR_MESSAGE);
		}


		FenetreUnion fenun = new FenetreUnion();
		fenun.setVisible(true);

	}

	public static ArrayList<String> getArrayy4() {
		return arrayy4;
	}

	public static void setArrayy4(ArrayList<String> arrayy4) {
		ConnexionUnion.arrayy4 = arrayy4;
	}

	public static ArrayList<String> getGeomms4() {
		return geomms4;
	}

	public static void setGeomms4(ArrayList<String> geomms4) {
		ConnexionUnion.geomms4 = geomms4;
	}

}