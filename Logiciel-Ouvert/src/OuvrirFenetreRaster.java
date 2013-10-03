
import javax.swing.*; 
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
import java.util.ArrayList;


public class OuvrirFenetreRaster extends JFrame {

	static ArrayList<String> path = new ArrayList<String>(); //ArrayList pour mettre dedans les path

	private static final long serialVersionUID = 1L; //aucune id√©e
	private static String pathhh="";
	private String pathShape;


	public String getPathShape() {
		return pathShape;
	}

	public void setPathShape(String pathShape) {
		this.pathShape = pathShape;
	}

	public static String getPathhh() {
		return pathhh;
	}

	public void setPathhh(String pathhh) {
		OuvrirFenetreRaster.pathhh = pathhh;
	}

	public OuvrirFenetreRaster()  { 
		path.clear();
		JFileChooser chooser = new JFileChooser(new File("/Users/Gilles/Documents/Université/Master 2 Géomatique/Semestre 2/Stage/Donnees_Paludisme")); //chemin vers le dossier qui est ouvert au d√©but
		chooser.setAcceptAllFileFilterUsed(false); //pas tous les fichiers sont accept√©s
		chooser.getFileFilter(); 
		chooser.setDialogTitle("Veuillez choisir les fichiers raster");
		chooser.removeChoosableFileFilter(chooser.getFileFilter());
		chooser.setFileFilter(new FileNameExtensionFilter("TIF file", "tif")); //les fichiers accept√©s

		chooser.setFileFilter(new FileNameExtensionFilter("PNG file", "png")); //les fichiers accept√©s
		chooser.setFileFilter(new FileNameExtensionFilter("JPEG file", "jpg", "jpeg")); //les fichiers accept√©s
		chooser.setApproveButtonText("Choisir");
		chooser.setMultiSelectionEnabled(true); //possibilit√© de choisir plusieurs fichiers
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY); //seulement des fichier, pas de dossiers

		if (chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) { //lancer la fenetre
			File selected[] = chooser.getSelectedFiles(); //r√©cup√®re les fichiers s√©lectionn√©s
			int len = selected.length; //nombre de fichiers s√©lectionn√©s
			for (int i = 0 ; i < len ; i++) {
			//	path.add(selected[i].getName());
				path.add(selected[i].getAbsolutePath()); //ajouter les diff√©rents path √† l'array List
			}
		}
		for (String e:path){
			System.out.println(e +" ");  //afficher le r√©sultat
			setPathhh(e);
		}
	}

	public static ArrayList<String> pathArray(){
		ArrayList <String> test = new ArrayList <String>();
		for (String e:path){
			test.add(e);
		}
		return test;
	}


}


