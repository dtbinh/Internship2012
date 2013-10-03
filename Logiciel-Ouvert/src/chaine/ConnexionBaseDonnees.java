package chaine;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class ConnexionBaseDonnees extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	Fenetre3 fen;

	public JTextField saisie1;
	public JTextField saisie2;
	public JTextField saisie3;
	public JTextField saisie4;
	public JTextField saisie6;
	public JTextField saisie7;


	private JLabel label1 = new JLabel("hote");
	private JLabel label2 = new JLabel("Nom utilisateur");
	private JLabel label3 = new JLabel("Mot de passe");
	private JLabel label4 = new JLabel("Nom de la base de données");
	private JLabel label5 = new JLabel("");
	private JLabel label6 = new JLabel("Projection");
	private JLabel label7 = new JLabel("Port");

	private JButton bouton;

	public static boolean visibile;

	public static String hote;
	public static String user;
	public static String pswd;
	public static String nomBase;
	public static String port;
	public static String projection;

	public ArrayList <String> shapes = OuvrirFenetre.pathArray();

	private String pathDuShape="";
	private String finalShape;
	private String nomShapee="";


	private ArrayList <String> splitt = new ArrayList<String>();

	public ConnexionBaseDonnees(){

		this.setTitle("Information connexion base de donn�es");
		this.setLocationRelativeTo(null);

		this.setSize(250, 300);
		//this.setMaximumSize(getPreferredSize());
		Container contenu = getContentPane();
		contenu.setLayout(new FlowLayout());
		saisie1 = new JTextField ("localhost",20);
		saisie2 = new JTextField ("Gilles",20);
		saisie3 = new JTextField ("959426G/e",20);
		saisie4 = new JTextField ("test",20);
		saisie6 = new JTextField ("32360",6);
		saisie7 = new JTextField ("5432",4);

		contenu.setLayout(new FlowLayout());

		contenu.add(label1);
		contenu.add(saisie1);

		contenu.add(label7);
		contenu.add(saisie7);

		contenu.add(label2);
		contenu.add(saisie2);

		contenu.add(label3);
		contenu.add(saisie3);

		contenu.add(label4);
		contenu.add(saisie4);

		contenu.add(label6);
		contenu.add(saisie6);

		contenu.add(label5);


		saisie1.addActionListener(this);
		saisie7.addActionListener(this);
		saisie2.addActionListener(this);
		saisie3.addActionListener(this);
		saisie4.addActionListener(this);
		saisie6.addActionListener(this);

		bouton = new JButton("OK");
		contenu.add(bouton);
		bouton.addActionListener(this);

		hote = saisie1.getText();
		port = saisie7.getText();
		user = saisie2.getText();
		pswd = saisie3.getText();
		nomBase = saisie4.getText();
		projection = saisie6.getText();


	}

	public synchronized void actionPerformed(ActionEvent e) {

		if (e.getSource() == bouton){ 
			ConnexionBaseDonnees.setHote(saisie1.getText());
			ConnexionBaseDonnees.setPort(saisie7.getText());
			ConnexionBaseDonnees.setUser(saisie2.getText());
			ConnexionBaseDonnees.setPswd(saisie3.getText());
			ConnexionBaseDonnees.setNomBase(saisie4.getText());
			ConnexionBaseDonnees.setProjection(saisie6.getText());

		}

		System.out.println("Database creation example!");
		Connection con = null;
		try{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://"+ConnexionBaseDonnees.getHote()+":"+ConnexionBaseDonnees.getPort()+"/postgres",ConnexionBaseDonnees.getUser(),ConnexionBaseDonnees.getPswd());

			if (con!=null){
				try{
					Statement st = con.createStatement();
					String database = ConnexionBaseDonnees.getNomBase();
					//st.execute("DROP DATABASE "+database);
					st.executeUpdate("CREATE DATABASE "+database);
					System.out.println("Base de donn�es "+database+" cr�e");
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

		this.dispose();

		this.setVisible(false);



		if (con!=null){

			try {
				new PostGIS();
			} catch (IOException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}

			try {
				new SpatialRef();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 

			try {
				new Postgisraster();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}

			for (String efg:shapes){

				Pattern p = Pattern.compile("/");
				String [] split = p.split(efg);

				int length = split.length;

				String dernier = split[length-1];

				Pattern point = Pattern.compile("\\.");

				String [] splitPoint = point.split(dernier);

				this.setFinalShape(splitPoint[0]);

				splitt.add(finalShape);

			}

			for(int ty=0;ty<shapes.size();ty++){


				this.setNomShapee(splitt.get(ty));
				this.setPathDuShape(shapes.get(ty));


				//new Thread(new Class2(this,infoShp2pgsql)).start();

			}
			InfoShp2pgsql infoShp2pgsql = new InfoShp2pgsql(this,fen);
			System.out.println("hello");
			try {
				new Shp2pgsql(infoShp2pgsql,this);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		}
	}


	public JTextField getSaisie1() {
		return saisie1;
	}

	public void setSaisie1(JTextField saisie1) {
		this.saisie1 = saisie1;
	}

	public JTextField getSaisie2() {
		return saisie2;
	}

	public void setSaisie2(JTextField saisie2) {
		this.saisie2 = saisie2;
	}

	public JTextField getSaisie3() {
		return saisie3;
	}

	public void setSaisie3(JTextField saisie3) {
		this.saisie3 = saisie3;
	}

	public JTextField getSaisie4() {
		return saisie4;
	}

	public void setSaisie4(JTextField saisie4) {
		this.saisie4 = saisie4;
	}

	public JLabel getLabel1() {
		return label1;
	}

	public void setLabel1(JLabel label1) {
		this.label1 = label1;
	}

	public JLabel getLabel2() {
		return label2;
	}

	public void setLabel2(JLabel label2) {
		this.label2 = label2;
	}

	public JLabel getLabel3() {
		return label3;
	}

	public void setLabel3(JLabel label3) {
		this.label3 = label3;
	}

	public JLabel getLabel4() {
		return label4;
	}

	public void setLabel4(JLabel label4) {
		this.label4 = label4;
	}

	public JLabel getLabel5() {
		return label5;
	}

	public void setLabel5(JLabel label5) {
		this.label5 = label5;
	}

	public JButton getBouton() {
		return bouton;
	}

	public void setBouton(JButton bouton) {
		this.bouton = bouton;
	}

	public static boolean isVisibile() {
		return visibile;
	}

	public static void setVisibile(boolean visibile) {
		ConnexionBaseDonnees.visibile = visibile;
	}

	public static String getPort() {
		return port;
	}

	public static void setPort(String port) {
		ConnexionBaseDonnees.port = port;
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


	public ArrayList<String> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<String> shapes) {
		this.shapes = shapes;
	}

	public String getFinalShape() {
		return finalShape;
	}

	public void setFinalShape(String finalShape) {
		this.finalShape = finalShape;
	}

	public ArrayList<String> getSplitt() {
		return splitt;
	}

	public void setSplitt(ArrayList<String> splitt) {
		this.splitt = splitt;
	}

	public String getNomShapee() {
		return nomShapee;
	}

	public void setNomShapee(String nomShapee) {
		this.nomShapee = nomShapee;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getPathDuShape() {
		return pathDuShape;
	}


	public void setPathDuShape(String pathDuShape) {
		this.pathDuShape = pathDuShape;
	}




	public static String getProjection() {
		return projection;
	}

	public static void setProjection(String projection) {
		ConnexionBaseDonnees.projection = projection;
	}

	public static String getHote() {
		return hote;
	}

	public static void setHote(String hote) {
		ConnexionBaseDonnees.hote = hote;
	}



}