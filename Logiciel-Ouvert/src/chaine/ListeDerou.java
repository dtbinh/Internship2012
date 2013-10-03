package chaine;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;





public class ListeDerou extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	RequeteEau reqeau;

	private JButton bouton = new JButton("Annuler");
	private JButton bouton2 = new JButton("Suivant");
	private JPanel container = new JPanel();
	private JLabel label = new JLabel("Champ d'identification");
	private JComboBox combo = new JComboBox();
	private ArrayList <String> nomColonne = new ArrayList <String>();
	private String selectedItem;




	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	public ListeDerou(RequeteEau reqeau){
		this.reqeau=reqeau;
		this.nomColonne=reqeau.getNomColonne();

		this.setTitle("Choix champ d'identification couche eau");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);

		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());

		bouton.addActionListener(this);             
		bouton2.addActionListener(this);

		bouton.setEnabled(false);
		JPanel south = new JPanel();
		JPanel center = new JPanel();

		south.add(bouton);
		south.add(bouton2);
		container.add(south, BorderLayout.SOUTH);

		for (String e:nomColonne){
			combo.addItem(e);
		}
		combo.addActionListener(this);

		JPanel top = new JPanel();
		top.add(label);
		center.add(combo);

		container.add(top, BorderLayout.NORTH);
		container.add(center, BorderLayout.CENTER);

		this.setContentPane(container);
		this.setVisible(true);           
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub


		if (e.getSource() == bouton2){
			//System.out.println(combo.getSelectedItem());
			this.setSelectedItem(combo.getSelectedItem().toString());
			System.out.println(this.getSelectedItem());

			try {
				new Requete2(this);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


		}


	}



}
