
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class ConnexionVector2Points extends JFrame{

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
		ConnexionVector2Points.arrayy2 = arrayy2;
	}

	public static ArrayList<String> getGeomms2() {
		return geomms2;
	}

	public static void setGeomms2(ArrayList<String> geomms2) {
		ConnexionVector2Points.geomms2 = geomms2;
	}

	public static ArrayList<String> getGeomms() {
		return geomms;
	}

	public static void setGeomms(ArrayList<String> geomms) {
		ConnexionVector2Points.geomms = geomms;
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
		ConnexionVector2Points.arrayy = arrayy;
	}



	public static ArrayList<String> getArrayy3() {
		return arrayy3;
	}

	public static void setArrayy3(ArrayList<String> arrayy3) {
		ConnexionVector2Points.arrayy3 = arrayy3;
	}

	public ConnexionVector2Points(){

		arrayy=new ArrayList<String>();
		geomms=new ArrayList<String>();

		Connection con = null;

		try{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://"+ConnexionBaseDonnees.getHote()+":"+ConnexionBaseDonnees.getPort()+"/"+ConnexionBaseDonnees.getNomBase(),ConnexionBaseDonnees.getUser(),ConnexionBaseDonnees.getPswd());

			if (con!=null){
				try{

					Statement stmt = con.createStatement();

					String query = "SELECT f_table_name as tables, f_geometry_column as geom FROM geometry_columns where f_table_name not like \'spatial_ref_sys\' and f_table_name not like \'%_raster%\' and f_table_name not like \'%_union%'";
					ResultSet rs = stmt.executeQuery(query);
					if(arrayy3.size()==0){

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

							if(!arrayy2.contains(tablename)){
								arrayy2.add(tablename);
								geomms2.add(geom);
								System.out.println("tablename2"+ tablename);

							}
							if(!arrayy3.contains(tablename)){

								arrayy3.add(tablename);
								System.out.println("tablename3"+ tablename);

							}

							if(!arrayy4.contains(tablename)){

								arrayy4.add(tablename);
								geomms4.add(geom);
								System.out.println("tablename4"+ tablename);

							}




							arrayy.removeAll(RequeteRasterVector2Points.getShapes1());
							geomms.removeAll(RequeteRasterVector2Points.getSrids1());
							//double test2 = rs.getDouble("sum");
							//float test = rs.getFloat("sum");
							//float price = rs.getFloat("PRICE");
							// int sales = rs.getInt("SALES");
							// int total = rs.getInt("TOTAL");
							//    System.out.println("test"+tablename);
						}

						for (String ef:arrayy){
							System.out.println("ConnexRaster: "+ef);
						}
						for (String ef:arrayy2){
							System.out.println("ConnexRaster2: "+ef);
						}
						for (String ef:arrayy3){
							System.out.println("ConnexRaster3: "+ef);
						}
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

		arrayy3.removeAll(RequeteRasterVector2Points.getShapes1());

		FenetreVector2Points fenevect = new FenetreVector2Points();
		fenevect.setVisible(true);		



		arrayy3= new ArrayList<String>();


	}

	public static ArrayList<String> getArrayy4() {
		return arrayy4;
	}

	public static void setArrayy4(ArrayList<String> arrayy4) {
		ConnexionVector2Points.arrayy4 = arrayy4;
	}

	public static ArrayList<String> getGeomms4() {
		return geomms4;
	}

	public static void setGeomms4(ArrayList<String> geomms4) {
		ConnexionVector2Points.geomms4 = geomms4;
	}

}