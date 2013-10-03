import java.sql.*;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class ModifSrid extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	public JTextField saisie1;
	


	private JLabel label1 = new JLabel("Projection actuelle: "+ConnexionBaseDonnees.getProjection());
	private JLabel label2 = new JLabel("Nouvelle Projection");
	

	private JButton bouton;

	public static boolean visibile;

	public static String newProjection;
	

	public ArrayList <String> shapes = OuvrirFenetre.pathArray();
	private ArrayList <String> array = new ArrayList <String>();
	private ArrayList <String> geoms = new ArrayList <String>();

	private ArrayList <String> array2 = new ArrayList <String>();
	private ArrayList <String> geoms2 = new ArrayList <String>();
	ModifSrid modif;




	
	public ModifSrid(){

		this.setTitle("Modifier SRID de la base de données");
		this.setLocationRelativeTo(null);

		this.setSize(200, 150);
		//this.setSize(this.getPreferredSize());
		//this.setMaximumSize(getPreferredSize());
		JPanel contenu = new JPanel();
		//Container contenu = getContentPane();
		contenu.setLayout(new BoxLayout(contenu, BoxLayout.Y_AXIS));
		saisie1 = new JTextField ("",6);
		


		//contenu.setLayout(new FlowLayout());
		//	label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setHorizontalAlignment(SwingConstants.CENTER);

		contenu.add(label1);

		contenu.add(label2);

		contenu.add(saisie1);



		saisie1.addActionListener(this);
		
		bouton = new JButton("OK");
		contenu.add(bouton);
		bouton.addActionListener(this);

		newProjection = saisie1.getText();
		

		this.getContentPane().add(contenu, BorderLayout.CENTER);


	}

	public synchronized void actionPerformed(ActionEvent e) {

		if (e.getSource() == bouton){
			ModifSrid.setNewProjection(saisie1.getText());
			ConnexionBaseDonnees.setProjection(saisie1.getText());
		}

	

		System.out.println("Modification SRID");
		Connection con = null;

		try{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://"+ConnexionBaseDonnees.getHote()+":"+ConnexionBaseDonnees.getPort()+"/"+ConnexionBaseDonnees.getNomBase(),ConnexionBaseDonnees.getUser(),ConnexionBaseDonnees.getPswd());
			//con2 = DriverManager.getConnection("jdbc:postgresql://"+ConnexionBaseDonnees.getHote()+":"+ConnexionBaseDonnees.getPort()+"/"+ConnexionBaseDonnees.getNomBase(),ConnexionBaseDonnees.getUser(),ConnexionBaseDonnees.getPswd());

			if (con!=null){
				try{

					Statement stmt = con.createStatement();

					String query = "SELECT f_table_name as tables, f_geometry_column as geom FROM geometry_columns where f_table_name not like \'spatial_ref_sys\'";
					ResultSet rs = stmt.executeQuery(query);
					while (rs.next()) {

						String tablename = rs.getString("tables");
						String geom = rs.getString("geom");


						array.add(tablename);
						geoms.add(geom);
						//  int test = rs.getInt("sum");
						//double test2 = rs.getDouble("sum");
						//float test = rs.getFloat("sum");
						//float price = rs.getFloat("PRICE");
						// int sales = rs.getInt("SALES");
						// int total = rs.getInt("TOTAL");
						//    System.out.println("test"+tablename);
					}
					rs.close();

					for (String ef:array){
						System.out.println("Connex: "+ef);
					}

					for (String eff:geoms){
						System.out.println("SRID: "+eff);
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
		
		new ModifSrid(this);

		this.dispose();

		this.setVisible(false);


	
		



	}

	public ModifSrid(ModifSrid modif){

		this.modif = modif;
		
		System.out.println("bonjour");

		Connection con2 = null;


		array2=modif.getArray();
		geoms2=modif.getGeoms();

		System.out.println("size: "+array2.size());

		for(int i=0;i<array2.size();i++){

			try{
				Class.forName("org.postgresql.Driver");

				con2 = DriverManager.getConnection("jdbc:postgresql://"+ConnexionBaseDonnees.getHote()+":"+ConnexionBaseDonnees.getPort()+"/"+ConnexionBaseDonnees.getNomBase(),ConnexionBaseDonnees.getUser(),ConnexionBaseDonnees.getPswd());
				if (con2!=null){

					Statement stmt2 = con2.createStatement();

					String query2 = "SELECT ST_SRID("+geoms2.get(i)+") as srid FROM "+array2.get(i)+" LIMIT 1";
					//String query2 = "SELECT f_table_name as tables, f_geometry_column as geom FROM geometry_columns where f_table_name not like \'spatial_ref_sys\' and f_table_name not like \'%_raster%\' and f_table_name not like \'%_union%'";

					System.out.println(query2);

					ResultSet rs2 = stmt2.executeQuery(query2);
					while (rs2.next()) {

						int tablename3 = (int)rs2.getDouble("srid");
						String tablename1 = String.valueOf(tablename3);

						System.out.println(tablename1);
						//String geom = rs.getString("geom");

						if(!tablename1.equals(ModifSrid.getNewProjection())){
							System.out.println("Changement du SRID vers: "+ModifSrid.getNewProjection());
							Statement stmt3 = con2.createStatement();

							String query3 = "select updategeometrysrid(\'"+array2.get(i)+"\', \'"+geoms2.get(i)+"\', "+ModifSrid.getNewProjection()+")";
							stmt3.executeQuery(query3);
						}
						else{
							System.out.println("SRID ok pour "+array2.get(i));
						}
						

					}

				}


			}
			catch (SQLException s){
				System.out.println("SQL statement is not executed!");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static String getNewProjection() {
		return newProjection;
	}

	public static void setNewProjection(String newProjection) {
		ModifSrid.newProjection = newProjection;
	}

	public ArrayList<String> getGeoms() {
		return geoms;
	}

	public void setGeoms(ArrayList<String> geoms) {
		this.geoms = geoms;
	}

	public ArrayList<String> getArray2() {
		return array2;
	}

	public void setArray2(ArrayList<String> array2) {
		this.array2 = array2;
	}

	public ArrayList<String> getGeoms2() {
		return geoms2;
	}

	public void setGeoms2(ArrayList<String> geoms2) {
		this.geoms2 = geoms2;
	}

	public ArrayList<String> getArray() {
		return array;
	}

	public void setArray(ArrayList<String> array) {
		this.array = array;
	}
}