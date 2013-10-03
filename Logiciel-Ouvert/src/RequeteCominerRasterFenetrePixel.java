import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class RequeteCominerRasterFenetrePixel extends JFrame implements ActionListener{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JLabel label1 = new JLabel("Nombre d'habitants");

	public JTextField saisie1;
	private JButton bouton;
	private static String nombreHabitantsString;

	private static double nombreHabitants;

	public RequeteCominerRasterFenetrePixel(){
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 250);

		this.setTitle("Veuillez saisir le nombre d'habitatns de la zone d'étude");
		
		Container contenu = getContentPane();
		contenu.setLayout(new FlowLayout());
		saisie1 = new JTextField ("",20);
		
		contenu.add(label1);
		contenu.add(saisie1);
		
		saisie1.addActionListener(this);

		bouton = new JButton("Valider");
		contenu.add(bouton);
		bouton.addActionListener(this);
		
		//nombreHabitantsString=saisie1.getText();
		//nombreHabitants=Double.parseDouble(nombreHabitantsString);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static double getNombreHabitants() {
		return nombreHabitants;
	}

	public static void setNombreHabitants(double nombreHabitants) {
		RequeteCominerRasterFenetrePixel.nombreHabitants = nombreHabitants;
	}
	

	public static String getNombreHabitantsString() {
		return nombreHabitantsString;
	}

	public static void setNombreHabitantsString(String nombreHabitantsString) {
		RequeteCominerRasterFenetrePixel.nombreHabitantsString = nombreHabitantsString;
	}

	@Override
	public void actionPerformed(ActionEvent f) {
		// TODO Auto-generated method stub
		if (f.getSource() == bouton){
			RequeteCominerRasterFenetrePixel.setNombreHabitants(Double.parseDouble(saisie1.getText()));
			RequeteCominerRasterFenetrePixel.setNombreHabitantsString(Double.toString(nombreHabitants));

		}
		
		this.setVisible(false);
		
		try {
			new RequeteCombinerRasterTaillePixel();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
