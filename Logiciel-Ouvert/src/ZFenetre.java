import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



public class ZFenetre extends JFrame {

	/**
	 * 
	 */
	ConnexionBaseDonnees connex;
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier");
	private JMenu pretraitement = new JMenu("Pré-traitements");
	private JMenu traitements = new JMenu("Traitements");

	private JMenu traitementstatistiques = new JMenu("Statistiques");
	private JMenu traitementsanalyse = new JMenu("Analyse Spatiale");
	private JMenu transformation = new JMenu("Transformation");

	private JMenu renomm = new JMenu("Renommer");

	private JMenu visualiser = new JMenu("Visualiser");

	private JMenu inserer = new JMenu("Insérer");
	private JMenu supprimer = new JMenu("Supprimer");

	private JMenu posttraitement = new JMenu("Post-traitements");
	private JMenu chainePalu = new JMenu("Chaînes de traitements");

	private JMenu traitementStockage = new JMenu("Traitements de stockage");
	private JMenu exporter = new JMenu("Exporter");




	private JMenuItem iteminserer = new JMenuItem("Insérer vecteurs dans base");
	private JMenuItem iteminsererraster = new JMenuItem("Insérer rasters dans base");
	private JMenuItem itemchangerpojection = new JMenuItem("Changer projection de la base de données");
	private JMenuItem item2 = new JMenuItem("Fermer");
	private JMenuItem itemcarterisque = new JMenuItem("Carte de risque paludisme");
	private JMenuItem itemintersection = new JMenuItem("Intersection");
	private JMenuItem supprimerCouche = new JMenuItem("Supprimer Couche vecteur");
	private JMenuItem supprimerRaster = new JMenuItem("Supprimer Couche raster");
	private JMenuItem transformerRasterPoints = new JMenuItem("Densité des points raster");
	private JMenuItem transformerShapePoints = new JMenuItem("Densité des points shape");
	private JMenuItem itemunion = new JMenuItem("Union");
	private JMenuItem reclassfier = new JMenuItem("Reclassifier un raster");
	private JMenuItem zonesBuffers = new JMenuItem("Créer des buffers");
	private JMenuItem differenceSym = new JMenuItem("Différence entre deux vecteurs");
	private JMenuItem extend = new JMenuItem("Emprise d'une couche");
	private JMenuItem renommer = new JMenuItem("Renommer Vecteur");
	private JMenuItem renommerraster = new JMenuItem("Renommer Raster");
	private JMenuItem exporterVecteur = new JMenuItem("Exporter des vecteurs");

	private JMenuItem combiner = new JMenuItem("Combiner Raster");

	private static JMenuItem itemsurfacetotale = new JMenuItem("Surface totale d'une couche");
	private static JMenuItem visualiserRaster = new JMenuItem("Visualiser Raster");
	private static JMenuItem visualiserVecteur = new JMenuItem("Visualiser Vecteur");


	private static JMenuItem supprimerChamp = new JMenuItem("Supprimer Champ");



	private static JMenuItem taillePixel = new JMenuItem("Taille d'un pixel");

	private JMenuItem rasterfichier = new JMenuItem("Rastériser un fichier de forme");

	public static boolean check = false;



	public static boolean isCheck() {
		return check;
	}


	public static void setCheck(boolean check) {
		ZFenetre.check = check;
	}

	public ZFenetre(){
		this.setSize(1200, 600);
		this.setTitle("PaluChaine");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);


		this.pretraitement.add(traitementStockage);

		iteminserer.addActionListener(new ActionListener(){

			@Override
			public synchronized void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				InsererBase insert = new InsererBase();
				insert.setVisible(true);
				check=true;

			}
		});
		
		this.traitementStockage.add(inserer);
		this.inserer.add(iteminserer);

		supprimerCouche.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new ConnexionSupprimer();
			}

		});

		this.traitementStockage.add(supprimer);
		this.supprimer.add(supprimerCouche);

		itemchangerpojection.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				ModifSrid modifsrid = new ModifSrid();
				modifsrid.setVisible(true);
			}

		});

		this.transformation.add(itemchangerpojection);

		//On ajoute les �l�ments dans notre sous-menu

		//Ajout d'un s�parateur
		this.fichier.addSeparator();
		item2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}				
		});
		this.fichier.add(item2);

		//this.traitement.addSeparator();


		itemintersection.addActionListener(new ActionListener(){

			@Override
			public synchronized void actionPerformed(ActionEvent arg0) {
				new ConnexionIntersection();

			}

		});

		this.traitementsanalyse.add(itemintersection);

		itemunion.addActionListener(new ActionListener(){

			@Override
			public synchronized void actionPerformed(ActionEvent arg0) {
				new ConnexionUnion();

			}

		});

		this.traitementsanalyse.add(itemunion);

		this.traitementsanalyse.addSeparator();


		itemsurfacetotale.addActionListener(new ActionListener(){

			@Override
			public synchronized void actionPerformed(ActionEvent arg0) {
				new ConnexionTraitement();
			}

		});

		this.traitementstatistiques.add(itemsurfacetotale);

		getTaillePixel().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new ConnexionTaillePixel();

			}

		});
		this.traitementstatistiques.add(getTaillePixel());

		ZFenetre.getTaillePixel().setEnabled(false);


		rasterfichier.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ConnexionRaster();

			}

		});
		this.transformation.add(rasterfichier);


		this.traitementstatistiques.addSeparator();

		this.traitementstatistiques.add(transformerRasterPoints);

		transformerRasterPoints.setEnabled(false);
		reclassfier.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//new ConnexionRaster();
				new ConnexionReclassifierRaster();

			}

		});

		this.posttraitement.add(reclassfier);

		this.renomm.add(renommer);
		renommer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//new ConnexionRaster();
				new ConnexionRenommer();

			}

		});
		
		this.renomm.add(renommerraster);
		renommerraster.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//new ConnexionRaster();
				new ConnexionRenommerRaster();

			}

		});


		this.posttraitement.add(supprimerChamp);
		supprimerChamp.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//new ConnexionRaster();
				new ConnexionSupprimerChamp();

			}

		});

		transformerShapePoints.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//new ConnexionRaster();
				new ConnexionVector2Points();

			}

		});

		this.traitementstatistiques.add(transformerShapePoints);


		zonesBuffers.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//new ConnexionRaster();
				new ConnexionBuffers();

			}

		});

		this.traitementsanalyse.add(zonesBuffers);


		supprimerRaster.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ConnexionSupprimerRaster();

			}

		});


		this.traitementsanalyse.add(differenceSym);

		differenceSym.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ConnexionDifference();

			}

		});

		extend.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ConnexionExtend();

			}

		});

		this.traitementsanalyse.add(extend);


		this.supprimer.add(supprimerRaster);

		iteminsererraster.addActionListener(new ActionListener(){

			@Override
			public synchronized void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				InsererRaster insert = new InsererRaster();
				insert.setVisible(true);
				check=true;

			}
		});

		this.traitementStockage.add(inserer);
		this.inserer.add(iteminsererraster);


		visualiserRaster.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//new ConnexionRaster();
				new ConnexionVisualiserRaster();
				FenetreVisualiserRaster fenvis = new FenetreVisualiserRaster();	
				fenvis.setVisible(true);
			}

		});

		
		
		this.visualiser.add(visualiserRaster);



		visualiserVecteur.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//new ConnexionRaster();
				new ConnexionVisualiserVecteur();

			}

		});

		this.visualiser.add(visualiserVecteur);

		itemcarterisque.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//new ConnexionRaster();
				new chaine.ProgrammeChaine();
				System.out.println("toooot");
			}

		});
		this.chainePalu.add(itemcarterisque);

		this.posttraitement.add(combiner);
		combiner.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//new ConnexionRaster();
				new ConnexionCombinerRaster();
			}

		});
		
		
		exporter.add(exporterVecteur);
		
		exporter.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//new ConnexionRaster();
				new ConnexionExporterVecteurs();
				FenetreExporterVecteurs fenvis = new FenetreExporterVecteurs();	
				fenvis.setVisible(true);
			}

		});
		
		this.posttraitement.addSeparator();
		
		this.posttraitement.add(renomm);
		this.posttraitement.add(visualiser);
		this.posttraitement.add(exporter);



		//	ZFenetre.getVisualiserRaster().setEnabled(false);


		//L'ordre d'ajout va d�terminer l'ordre d'apparition dans le menu de gauche � droite
		//Le premier ajout� sera tout � gauche de la barre de menu et inversement pour le dernier

		this.traitements.add(traitementsanalyse);
		this.traitements.add(traitementstatistiques);
		this.traitements.add(transformation);


		this.menuBar.add(fichier);
		this.menuBar.add(pretraitement);
		this.menuBar.add(traitements);
		//this.menuBar.add(traitementsvecteur);
		this.menuBar.add(posttraitement);
		this.menuBar.add(chainePalu);



		//-------------------------

		this.setJMenuBar(menuBar);
		this.setVisible(true);

	}


	public static JMenuItem getTaillePixel() {
		return taillePixel;
	}


	public static void setTaillePixel(JMenuItem taillePixel) {
		ZFenetre.taillePixel = taillePixel;
	}


	public static JMenuItem getItemsurfacetotale() {
		return itemsurfacetotale;
	}


	public static void setItemsurfacetotale(JMenuItem itemsurfacetotale) {
		ZFenetre.itemsurfacetotale = itemsurfacetotale;
	}


	public JMenuItem getSupprimerRaster() {
		return supprimerRaster;
	}


	public void setSupprimerRaster(JMenuItem supprimerRaster) {
		this.supprimerRaster = supprimerRaster;
	}
	public static JMenuItem getVisualiserRaster() {
		return visualiserRaster;
	}


	public static void setVisualiserRaster(JMenuItem visualiserRaster) {
		ZFenetre.visualiserRaster = visualiserRaster;
	}

}
