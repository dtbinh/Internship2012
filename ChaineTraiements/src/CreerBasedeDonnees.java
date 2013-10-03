//import java.sql.*;
//import java.awt.Container;
//import java.awt.FlowLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JTextField;
//
//
//public class CreerBasedeDonnees extends JFrame implements ActionListener{
//
//
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	//private JPanel container = new JPanel();
//	public JTextField saisie;
//	//private JLabel label = new JLabel("Nom de la base de données");
//	private JButton bouton;
//	public static String nomBase;
//
//	public CreerBasedeDonnees(){
//		this.setVisible(false);
//
//		this.setTitle("Nom de la base de données");
//		this.setSize(300, 300);
//		Container contenu = getContentPane();
//		contenu.setLayout(new FlowLayout());
//		saisie = new JTextField (20);
//		contenu.add(saisie);
//		saisie.addActionListener(this);
//
//		bouton = new JButton("OK");
//		contenu.add(bouton);
//		bouton.addActionListener(this);
//
//		nomBase = saisie.getText();
//
//
//
//	}
//
//	public static String getNomBase() {
//		return nomBase;
//
//	}
//
//	public void setNomBase(String nomBase) {
//		CreerBasedeDonnees.nomBase = nomBase;
//	}
//
//
//
//
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//
//		if (e.getSource() == bouton){ 
//			String test = saisie.getText();
//			this.setNomBase(test);
//			//System.out.println(texte +"whoho");
//			//System.out.println(nomBase +"whoho");
//
//		}
//
//		//nomBase= this.getNomBase();
//
//		System.out.println(nomBase +"whoho22");
//
//
//
//
//		System.out.println("Database creation example!");
//		Connection con = null;
//		try{
//			Class.forName("org.postgresql.Driver");
//			con = DriverManager.getConnection
//					("jdbc:postgresql://localhost:5432/Malaria","entringe","959426G/e");
//			if (con!=null){
//				try{
//					Statement st = con.createStatement();
//					//System.out.println("Enter Database name:");
//					String database = CreerBasedeDonnees.getNomBase();
//					st.executeUpdate("CREATE DATABASE "+database);
//					System.out.println("Base de données "+database+" crée");
//				}
//				catch (SQLException s){
//					System.out.println("SQL statement is not executed!");
//				}
//			}
//		}
//		catch (Exception b){
//			b.printStackTrace();
//		}
//
//	}
//
//
//
//}