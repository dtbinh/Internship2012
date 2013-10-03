import javax.swing.JOptionPane;


public class lancer {
	
	private static boolean option = false;

	
	public static boolean isOption() {
		return option;
	}


	public static void setOption(boolean option) {
		lancer.option = option;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] choix = {"Base de donnees et Disque dur", "Disque dur", "Base de donnees PostGIS"};
		int rang = JOptionPane.showOptionDialog(null, 
										"Veuillez choisir l'emplacement de vos donnees",
										"PaluChaine",
										JOptionPane.YES_NO_CANCEL_OPTION,
										JOptionPane.QUESTION_MESSAGE,
										null,
										choix,
										choix[2]);
		
		if(choix[rang]=="Base de donnees et Disque dur"){
			ConnexionBaseDonnees connex = new ConnexionBaseDonnees();
			connex.setVisible(true);
			setOption(true);	
			}
		else{
			if(choix[rang]=="Disque dur"){
				CreationBaseDonnees creation = new CreationBaseDonnees();
				creation.setVisible(true);
			}
			if(choix[rang]=="Base de donnees PostGIS"){
				ConnexionBaseDonnees connex = new ConnexionBaseDonnees();
				connex.setVisible(true);
			}
		}
		//jop2.showMessageDialog(null, "Votre sexe est " + sexe[rang], "Etat civil", JOptionPane.INFORMATION_MESSAGE);
		
		
		
		
		
	}

}



