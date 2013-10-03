import java.io.*;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class CreationBaseDonnees extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	ConnexionBaseDonnees connex;

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

	public CreationBaseDonnees(){

		this.setTitle("Information connexion base de données");
		this.setLocationRelativeTo(null);

		this.setSize(250, 350);
		//this.setSize(this.getPreferredSize());
		//this.setMaximumSize(getPreferredSize());
		JPanel contenu = new JPanel();
		//Container contenu = getContentPane();
		contenu.setLayout(new BoxLayout(contenu, BoxLayout.Y_AXIS));
		saisie1 = new JTextField ("localhost",20);
		saisie2 = new JTextField ("entringe",20);
		saisie3 = new JPasswordField ("959426G/e",20);
		saisie4 = new JTextField ("test",20);
		saisie6 = new JTextField ("32360",6);
		saisie7 = new JTextField ("5432",4);


		//contenu.setLayout(new FlowLayout());
		//	label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setHorizontalAlignment(SwingConstants.CENTER);

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

		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(contenu, BorderLayout.CENTER);


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
				e4.getMessage();
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



			Fenetre fen = new Fenetre();
			fen.setVisible(true);




		}
	}}