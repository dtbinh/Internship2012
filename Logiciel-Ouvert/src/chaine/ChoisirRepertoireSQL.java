package chaine;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;


public class ChoisirRepertoireSQL {

	ArrayList <String> shapeChoisis = OuvrirFenetre.pathArray();
	int l= shapeChoisis.size();
	private static String path;

	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		ChoisirRepertoireSQL.path = path;
	}

	public ChoisirRepertoireSQL()  { 
		if (l!=0){
			JFileChooser chooser = new JFileChooser(new File("/Users/Gilles/Documents/")); //chemin vers le dossier qui est ouvert au début
			chooser.setAcceptAllFileFilterUsed(false); //pas tous les fichiers sont acceptés
			chooser.getFileFilter(); 
			chooser.removeChoosableFileFilter(chooser.getFileFilter());
			chooser.setApproveButtonText("Choisir");
			chooser.setDialogTitle("Veuillez choisir le répertoire pour les données en sortie");
			chooser.setMultiSelectionEnabled(false); //possibilité de choisir plusieurs fichiers
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //seulement des fichier, pas de dossiers

			if (chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) { //lancer la fenetre
				File file = chooser.getSelectedFile(); //récupère les fichiers sélectionnés

				ChoisirRepertoireSQL.setPath(file.getAbsolutePath());
				System.out.println(ChoisirRepertoireSQL.getPath());

			}
		}
	}




}
