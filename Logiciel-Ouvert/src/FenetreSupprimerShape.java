import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;



public class FenetreSupprimerShape extends JFrame implements ActionListener{

	/**
	 * 
	 */

	ConnexionSupprimer connex;
	private static final long serialVersionUID = 1L;
	ConnexionSupprimer connexion;
	//	private JButton change = new JButton("Charger une couche");
	//private JButton retablir = new JButton("Rétablir");
	private JButton supprimer = new JButton("enlever");
	private JButton valider = new JButton("valider");

	private int sel;
	//	private ArrayList <String> hmm = new ArrayList <String> ();
	private ArrayList <String> nomShape = new ArrayList <String> ();
	private static ArrayList <String> nomShape1 = new ArrayList <String> ();

	private ArrayList <String> splitt = new ArrayList<String>();
	private static ArrayList <String> sridss = new ArrayList<String>();

	public static ArrayList<String> getSridss() {
		return sridss;
	}

	public static void setSridss(ArrayList<String> sridss) {
		FenetreSupprimerShape.sridss = sridss;
	}








	public static ArrayList<String> getNomShape1() {
		return nomShape1;
	}

	public static void setNomShape1(ArrayList<String> nomShape1) {
		FenetreSupprimerShape.nomShape1 = nomShape1;
	}




	private String finalShape;


	private final DefaultTableModel model = new DefaultTableModel();

	public DefaultTableModel getModel() {
		return model;
	}


	private final JTable tableau = new JTable(model);

	public int getSel() {
		return sel;
	}

	public void setSel(int sel) {
		this.sel = sel;
	}





	public String getFinalShape() {
		return finalShape;
	}

	public void setFinalShape(String finalShape) {
		this.finalShape = finalShape;
	}

	public FenetreSupprimerShape(){

		nomShape1 = new ArrayList<String>();

		if(FenetreSupprimerShape.nomShape1.size()==0){
			FenetreSupprimerShape.nomShape1=ConnexionSupprimer.getArrayy();
		}

		//nomShape2=nomShape1;




		for(String fg:nomShape1){
			System.out.println("fg "+fg);
		}



		this.setLocationRelativeTo(null);

		this.setTitle("Choisissez les vecteurs à supprimer");
		this.setSize(500, 240);



		TableColumn ColumnName     = new TableColumn();
		ColumnName.setHeaderValue(new String("Name") );
		ColumnName.setMinWidth(200);
		model.addColumn(ColumnName);
		tableau.getColumnModel().getColumn(0).setHeaderValue(new String("Nom couches"));

		tableau.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public synchronized void valueChanged(ListSelectionEvent abc) {
				// TODO Auto-generated method stub
				setSel(tableau.getSelectedRow());
				System.out.println("sel"+getSel());
			}
		});


		for(int g=0;g<RequeteSurface.getShapes1().size();g++){
			System.out.println("req surf: "+RequeteSurface.getShapes1().get(g));
		}



		//if(model.getRowCount()<nomShape1.size()&&model.getRowCount()==0){

		//System.out.println("sizze "+model.getRowCount());

		for (int efgg=0;efgg<nomShape1.size();efgg++){
			System.out.println("efgg "+nomShape1.get(efgg));
			model.addRow(new Object [] {nomShape1.get(efgg)});
			//	System.out.println("sizze "+model.getRowCount());
			System.out.println("siiize "+nomShape1.get(efgg));


		}


		//}

		//this.model.removeRow(1);
		JPanel pan = new JPanel();


		//		change.addActionListener(new ActionListener(){
		//			public void actionPerformed(ActionEvent arg0) {	
		//
		//				DefaultTableModel Model = new DefaultTableModel();
		//
		//				Model.addColumn("test");
		//
		//				hmm = OuvrirFenetre.pathArray();
		//				//tableau.set
		//				Object[][] data = new Object[hmm.size()][hmm.size()];
		//
		//
		//				for (int i = 0; i<hmm.size();i++){
		//					data[i][0]= hmm.get(i);							
		//				}			
		//
		//				//tableau.setValueAt("New Value", rowIndex, vColIndex);
		//				//							Model.addRow(new Object[] { "test" } );
		//				//for(String f:hmm){
		//				System.out.println(hmm.size());
		//				for(int f=0;f<hmm.size();f++){
		//					//	System.out.println("test"+hmm.get(f));
		//					//model.addRow(new Object [] {f});
		//					//				if (model.getValueAt(0,0) == hmm.get(0)){
		//					//					hmm.remove(hmm.get(0));
		//					//				}
		//					int ab = model.getRowCount();
		//					System.out.println(ab);
		//					model.insertRow(ab, new Object[]{hmm.get(f)});
		//					System.out.println("ab"+hmm.get(f));
		//					//	hmm.remove(hmm.get(f));
		//					OuvrirFenetre.path.remove(hmm.get(f));
		//					//System.out.println("bb"+hmm.get(f));
		//				}
		//
		//
		//
		//			}
		//
		//		});

		int a = tableau.getSelectedColumn();
		System.out.println("a"+ a);


		supprimer.addActionListener(new ActionListener(){

			@Override
			public synchronized void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				System.out.println("siez1" +nomShape1.size());
				nomShape1.remove(getSel());
				//ConnexionSupprimer.getArrayy().remove(getSel());
				//ConnexionSupprimer.getGeomms().remove(getSel());
				model.removeRow(getSel());

			}

		}
				);
		pan.add(supprimer);
		//pan.add(change);
		//		
		//		
		//			String  titl2[] = {"Nome shape"};
		//
		//			this.tableau = new JTable(data,titl2);		
		//model.setRowHeight(30);	
		//model.setNumRows(30);
		//On remplace cette ligne
		this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
		this.getContentPane().add(pan, BorderLayout.SOUTH);


		valider.addActionListener(this);

		pan.add(valider);

	}

	public ArrayList<String> getNomShape() {
		return nomShape;
	}

	public void setNomShape(ArrayList<String> nomShape) {
		this.nomShape = nomShape;
	}

	public ArrayList<String> getSplitt() {
		return splitt;
	}

	public void setSplitt(ArrayList<String> splitt) {
		this.splitt = splitt;
	}


	public synchronized void actionPerformed(ActionEvent arg0) {

		//		System.out.println("count"+this.model.getRowCount());
		//
		//		// TODO Auto-generated method stub

		//			System.out.println(tableau.getValueAt(i,0));
		//			nomShape.add((tableau.getValueAt(i,0)).toString());
		//			System.out.println("rest"+nomShape.get(i));
		//			}
		//		for (String efg:nomShape){
		//
		//			Pattern p = Pattern.compile("/");
		//			String [] split = p.split(efg);
		//
		//			int length = split.length;
		//
		//			String dernier = split[length-1];
		//
		//			//Pattern point = Pattern.compile("\\.");
		//
		//			//String [] splitPoint = point.split(dernier);
		//
		//			finalShape=(dernier.replaceAll("\\W", "")).toLowerCase();
		//			
		//			
		//			System.out.println(finalShape);
		//
		//
		//			splitt.add(finalShape);
		//			
		//		}



		this.setVisible(false);

		System.out.println("Size nomshape supprim:" + nomShape1.size());
		System.out.println("Size RequeteSurface supprim:" + RequeteSurface.getShapes().size());

		for(int ab=0;ab<nomShape1.size();ab++){

			for(int abc=0;abc<RequeteSurface.getShapes().size();abc++){
				System.out.println("abc"+abc);
				System.out.println("ab"+ab);

				if(RequeteSurface.getShapes().get(abc).compareTo(nomShape1.get(ab))==0){
					System.out.println("abc1"+abc);
					System.out.println("ab1"+ab);
					//System.out.println("nom: "+nomShape1.get(ab));
					//System.out.println("nom2: "+RequeteSurface.getShapes().get(abc));

					if(RequeteSurface.getShapes().size()>0){
						RequeteSurface.getShapes().remove(abc);
					}
					if(RequeteSurface.getShapes1().size()>0){
						RequeteSurface.getShapes1().remove(abc);
					}
					if(RequeteSurface.getTailles().size()>0){
						RequeteSurface.getTailles().remove(abc);
					}

					if(RequeteSurface.getSrids().size()>0){
						RequeteSurface.getSrids().remove(abc);
					}

					if(RequeteSurface.getSrids1().size()>0){
						RequeteSurface.getSrids1().remove(abc);
					}

				}
			}
		}

		System.out.println("1 "+FenetreTaillePixel.getNomShape1().size());
		System.out.println("1 "+nomShape1.size());
		for(int ab=0;ab<nomShape1.size();ab++){

			for(int abc=0;abc<FenetreTaillePixel.getNomShape1().size();abc++){

				if(FenetreTaillePixel.getNomShape1().get(abc).compareTo(nomShape1.get(ab))==0){
					System.out.println("trouuvverraa1");
					if(FenetreTaillePixel.getNomShape1().size()>0){

						FenetreTaillePixel.getNomShape1().remove(abc);
					}					
					if(FenetreTaillePixel.getSridss().size()>0){

						FenetreTaillePixel.getSridss().remove(abc);
					}
				}
			}
		}

		System.out.println("2 "+DensitePopulation.getShapes1().size());
		System.out.println("2 "+nomShape1.size());
		for(int ab=0;ab<nomShape1.size();ab++){
			for(int abc=0;abc<FenetreTaillePixel.getNomShape2().size();abc++){
				if(FenetreTaillePixel.getNomShape2().get(abc).compareTo(nomShape1.get(ab))==0){
					System.out.println("trouuvverraa1");
					if(FenetreTaillePixel.getNomShape2().size()>0){

						FenetreTaillePixel.getNomShape2().remove(abc);
					}
					if(FenetreTaillePixel.getSridss2().size()>0){

						FenetreTaillePixel.getSridss2().remove(abc);
					}
				}
			}
		}

		System.out.println("3 "+DensitePopulation.getShapes1().size());
		System.out.println("3 "+nomShape1.size());

		for(int ab=0;ab<nomShape1.size();ab++){
			for(int abc=0;abc<DensitePopulation.getShapes1().size();abc++){
				if(DensitePopulation.getShapes1().get(abc).compareTo(nomShape1.get(ab))==0){
					System.out.println("trouuvverraa1");
					if(DensitePopulation.getShapes1().size()>0){
						DensitePopulation.getShapes1().remove(abc);
					}
					if(DensitePopulation.getSrids1().size()>0){

						DensitePopulation.getSrids1().remove(abc);
					}

					if(DensitePopulation.getNomCouches2().size()>0){

						DensitePopulation.getNomCouches2().remove(abc);
					}

					if(DensitePopulation.getTaillesCouches().size()>0){

						DensitePopulation.getTaillesCouches().remove(abc);
					}
				}
			}
		}

		for(int ab=0;ab<nomShape1.size();ab++){
			for(int abc=0;abc<RequeteRaster.getShapes1().size();abc++){
				if(RequeteRaster.getShapes1().get(abc).compareTo(nomShape1.get(ab))==0){
					System.out.println("trouuvverraa1");
					if(RequeteRaster.getShapes1().size()>0){

						RequeteRaster.getShapes1().remove(abc);
					}
					if(RequeteRaster.getSrids1().size()>0){

						RequeteRaster.getSrids1().remove(abc);
					}
				}
			}
		}




		try {
			new RequeteSupprimerShape();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}




}
