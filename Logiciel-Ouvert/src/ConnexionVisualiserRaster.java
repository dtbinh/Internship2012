
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class ConnexionVisualiserRaster extends JFrame{

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




	public static ArrayList<String> getGeomms() {
		return geomms;
	}

	public static void setGeomms(ArrayList<String> geomms) {
		ConnexionVisualiserRaster.geomms = geomms;
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
		ConnexionVisualiserRaster.arrayy = arrayy;
	}

	public ConnexionVisualiserRaster(){

		arrayy.clear();
		geomms.clear();
		Connection con = null;

		try{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://"+ConnexionBaseDonnees.getHote()+":"+ConnexionBaseDonnees.getPort()+"/"+ConnexionBaseDonnees.getNomBase(),ConnexionBaseDonnees.getUser(),ConnexionBaseDonnees.getPswd());

			if (con!=null){
				try{

					Statement stmt = con.createStatement();
					String query = "SELECT r_table_name as tables, r_raster_column as geom FROM raster_columns";
					ResultSet rs = stmt.executeQuery(query);
					if(arrayy.size()==0&&geomms.size()==0){

						while (rs.next()) {

							String tablename = rs.getString("tables");
							String geom = rs.getString("geom");

							arrayy.add(tablename);
							geomms.add(geom);

						}

						for (String ef:arrayy){
							System.out.println("ConnexRaster: "+ef);
						}
					}
				}
				catch (SQLException s){
					System.out.println("SQL Raster statement is not executed!");
				}
			}
		}
		catch (Exception b){
			b.printStackTrace();
			JOptionPane.showMessageDialog(this,"Erreur : "+b,"Titre : exception",JOptionPane.ERROR_MESSAGE);
		}

		this.dispose();

		this.setVisible(false);	

	}

}