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



public class FenetreTraitement extends JFrame implements ActionListener{

	/**
	 * 
	 */

	ConnexionTraitement connex;
	private static final long serialVersionUID = 1L;
	ConnexionTraitement connexion;
	private JButton change = new JButton("Charger une couche");
	//private JButton retablir = new JButton("Rétablir");
	private JButton supprimer = new JButton("Supprimer");
	private JButton valider = new JButton("valider");

	private int sel;
	private ArrayList <String> hmm = new ArrayList <String> ();
	private ArrayList <String> nomShape = new ArrayList <String> ();
	private static ArrayList <String> nomShape1 = new ArrayList <String> ();

	private ArrayList <String> splitt = new ArrayList<String>();
	private static ArrayList <String> sridss = new ArrayList<String>();

	public static ArrayList<String> getSridss() {
		return sridss;
	}

	public static void setSridss(ArrayList<String> sridss) {
		FenetreTraitement.sridss = sridss;
	}

	public static ArrayList<String> getSridss2() {
		return sridss2;
	}

	public static void setSridss2(ArrayList<String> sridss2) {
		FenetreTraitement.sridss2 = sridss2;
	}




	private static ArrayList <String> nomShape2 = new ArrayList <String> ();
	private static ArrayList <String> sridss2 = new ArrayList<String>();



	public static ArrayList<String> getNomShape1() {
		return nomShape1;
	}

	public static void setNomShape1(ArrayList<String> nomShape1) {
		FenetreTraitement.nomShape1 = nomShape1;
	}

	public static ArrayList<String> getNomShape2() {
		return nomShape2;
	}

	public static void setNomShape2(ArrayList<String> nomShape2) {
		FenetreTraitement.nomShape2 = nomShape2;
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

	public FenetreTraitement(){
		System.out.println("nouvelleee size: "+ConnexionTraitement.getArrayy().size());
		System.out.println("nouvelleee size2: "+nomShape1.size());

		if(FenetreTraitement.nomShape1.size()==0){
			FenetreTraitement.nomShape1=ConnexionTraitement.getArrayy();
			FenetreTraitement.sridss=ConnexionTraitement.getGeomms();
			FenetreTraitement.nomShape2=ConnexionTraitement.getArrayy2();
			FenetreTraitement.sridss2=ConnexionTraitement.getGeomms2();
		}
		else{

			//nomShape2=nomShape1;

			if(FenetreTraitement.nomShape1.size()>0){
				FenetreTraitement.getNomShape1().removeAll(FenetreTraitement.getNomShape1());
				FenetreTraitement.getNomShape1().addAll(ConnexionTraitement.getArrayy());
				FenetreTraitement.getSridss().removeAll(FenetreTraitement.getSridss());
				FenetreTraitement.getSridss().addAll(ConnexionTraitement.getGeomms());
			}
		}



		if(FenetreTraitement.getNomShape2().size()>0){
			FenetreTraitement.getNomShape2().removeAll(FenetreTraitement.getNomShape2());
			FenetreTraitement.getNomShape2().addAll(ConnexionTraitement.getArrayy());
			FenetreTraitement.getSridss2().removeAll(FenetreTraitement.getSridss2());
			FenetreTraitement.getSridss2().addAll(ConnexionTraitement.getGeomms());
		}



		for(String fg:nomShape1){
			System.out.println("fg "+fg);
		}

		for(String fg:sridss2){
			System.out.println("sridss2 "+fg);
		}

		for(String fg:sridss){
			System.out.println("sridss "+fg);
		}

		for(String bc:nomShape2){
			System.out.println("bc "+bc);
		}


		//nomShape1=nomShape2;
		//
		//			FenetreTraitement.nomShape1.removeAll(RequeteSurface.getShapes1());
		//			FenetreTraitement.sridss.removeAll(RequeteSurface.getSrids1());
		//		}

		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//this.setDefaultCloseOperation(this.nomShape1.remove(0));
		this.setTitle("JTable5678");
		this.setSize(500, 240);


		//		final DefaultTableModel model = new DefaultTableModel();
		//		final JTable tableau = new JTable(model);

		//		 tableau.setColumnSelectionAllowed (true);
		//		 tableau.setRowSelectionAllowed (false);


		TableColumn ColumnName     = new TableColumn();
		ColumnName.setHeaderValue(new String("Name") );
		ColumnName.setMinWidth(200);
		model.addColumn(ColumnName);
		tableau.getColumnModel().getColumn(0).setHeaderValue(new String("Nom"));

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

		for(String bc:nomShape2){
			System.out.println("bcccc"+bc);
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


		change.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {	

				DefaultTableModel Model = new DefaultTableModel();

				Model.addColumn("test");

				hmm = OuvrirFenetre.pathArray();
				//tableau.set
				Object[][] data = new Object[hmm.size()][hmm.size()];


				for (int i = 0; i<hmm.size();i++){
					data[i][0]= hmm.get(i);							
				}			

				//tableau.setValueAt("New Value", rowIndex, vColIndex);
				//							Model.addRow(new Object[] { "test" } );
				//for(String f:hmm){
				System.out.println(hmm.size());
				for(int f=0;f<hmm.size();f++){
					//	System.out.println("test"+hmm.get(f));
					//model.addRow(new Object [] {f});
					//				if (model.getValueAt(0,0) == hmm.get(0)){
					//					hmm.remove(hmm.get(0));
					//				}
					int ab = model.getRowCount();
					System.out.println(ab);
					model.insertRow(ab, new Object[]{hmm.get(f)});
					System.out.println("ab"+hmm.get(f));
					//	hmm.remove(hmm.get(f));
					OuvrirFenetre.path.remove(hmm.get(f));
					//System.out.println("bb"+hmm.get(f));
				}



			}

		});

		int a = tableau.getSelectedColumn();
		System.out.println("a"+ a);


		supprimer.addActionListener(new ActionListener(){

			@Override
			public synchronized void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				System.out.println("siez1" +nomShape1.size());
				System.out.println("size 2 "+nomShape2.size());
				nomShape2.remove(getSel());
				//ConnexionTraitement.getArrayy().remove(getSel());
				sridss2.remove(getSel());
				//ConnexionTraitement.getGeomms().remove(getSel());
				model.removeRow(getSel());

			}

		}
				);
		pan.add(supprimer);
		pan.add(change);
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

		try {
			new RequeteSurface();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}




}
