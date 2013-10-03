
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class RequeteSupprimerChamp2 extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton bouton = new JButton("Précédent");
	private JButton bouton2 = new JButton("Suivant");
	private JPanel container = new JPanel();
	private JLabel label = new JLabel("Champ d'identification");
	//private JLabel label1 = new JLabel("Possibilité");

	private static ArrayList <String> lignes = new ArrayList <String>();
	private JCheckBox morph;
	private static ArrayList<String> selection = new ArrayList<String>();


	public RequeteSupprimerChamp2(){


		lignes=RequeteSuppirmerChamp1.getLignes();

		this.setTitle("Choix champ d'identification couche eau");
		this.setSize(300, 300);
		//this.setMinimumSize();
		this.setLocationRelativeTo(null);

		//container.setBackground(Color);
		container.setLayout(new BorderLayout());

		bouton.addActionListener(this);             
		bouton2.addActionListener(this);

		bouton.setEnabled(true);
		JPanel south = new JPanel();
		south.add(bouton);
		south.add(bouton2);
		container.add(south, BorderLayout.SOUTH);
		JPanel top = new JPanel();
		top.add(label);
		JPanel center = new JPanel();
		center.setBackground(Color.white);
		//center.add(label1);
		for (String e:lignes){
			morph=new JCheckBox(e);
			//System.out.println(e);
			center.add(morph);
			morph.addActionListener(new Checkboxlistener());
		}


		//combo.addActionListener(this);



		container.add(top, BorderLayout.NORTH);
		container.add(center, BorderLayout.CENTER);

		this.setContentPane(container);
		//this.setVisible(true);           
		//this.dispose();

	}



	public static ArrayList<String> getLignes() {
		return lignes;
	}

	public static void setLignes(ArrayList<String> lignes) {
		RequeteSupprimerChamp2.lignes = lignes;
	}



	public class Checkboxlistener implements ActionListener{


		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//if(((JButton)e.getSource()==bouton2)){
			//	System.out.println("test");

			if(((JCheckBox)e.getSource()).isSelected()==true){
				selection.add(((JCheckBox)e.getSource()).getText());
				//System.out.println("source : " + ((JCheckBox)e.getSource()).getText() + " - état : " + ((JCheckBox)e.getSource()).isSelected());
			}
			else {
				if(((JCheckBox)e.getSource()).isSelected()==false){
				}
				selection.remove(((JCheckBox)e.getSource()).getText());
			}


			for(String s:selection){
				System.out.println(s);
			}
		}

	}

	public synchronized void actionPerformed(ActionEvent e) {
		if (e.getSource()==bouton2){
			try {
				new RequeteSupprimerChamp3();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else{

			if (e.getSource()==bouton){
				//	new RequeteSupprimerChampsListeDerou(	reqeau);
				// 	this.reqeau=reqeau;
				System.out.println("bonjours");
				this.setVisible(false);
				new ConnexionSupprimerChamp();

			}
		}
		this.setVisible(false);
	}



	public static ArrayList<String> getSelection() {
		return selection;
	}



	public static void setSelection(ArrayList<String> selection) {
		RequeteSupprimerChamp2.selection = selection;
	}

	


}       




