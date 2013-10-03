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



public class FenetreBuffersTailles extends JFrame implements ActionListener{

	/**
	 * 
	 */
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
	private static ArrayList <String> maximValues = new ArrayList <String> ();
	private static ArrayList <String> srids = new ArrayList <String> ();
	private static ArrayList <String> srids1 = new ArrayList <String> ();

	private ArrayList <String> splitt = new ArrayList<String>();
	private String finalShape;

	private static ArrayList <String [][]> tableauTailles = new ArrayList <String [][]> ();

	private final DefaultTableModel model = new DefaultTableModel();

	private static ArrayList <String[][]> tableauxx = new ArrayList <String[][]> ();

	private static ArrayList <String> nomCouches = new ArrayList <String> ();
	private static ArrayList <String> nombreClasses = new ArrayList <String> ();
	private static ArrayList <String> maxim = new ArrayList <String> ();
	private static ArrayList <Double> valeurs = new ArrayList <Double>();



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

	public FenetreBuffersTailles(){

		int jjj=0;
		System.out.println("tableauxxx"+ tableauxx.size());


		//tableauxx.clear();

		System.out.println("tableauxxxdddd"+ FenetreBuffersNumeros.getTableauTailles().size());

		srids.clear();

		System.out.println("srids"+ FenetreBuffersNumeros.getSrids().size());
		System.out.println("srids"+ srids.size());

		nombreClasses.clear();
		nomCouches.clear();
		valeurs.clear();
		System.out.println("nomCouches"+ nomCouches.size());
		System.out.println("nombreClasses"+ nombreClasses.size());


		FenetreBuffersTailles.tableauxx=FenetreBuffersNumeros.getTableauTailles();


		System.out.println("tableaux"+ tableauxx.size());

		for(int fg=0;fg<FenetreBuffersNumeros.getTableauTailles().size();fg++){
			String test34[][]=FenetreBuffersNumeros.getTableauTailles().get(fg);
			String taillePixels=test34[fg][1];
			nombreClasses.add(taillePixels);
			String nomCouche=test34[fg][0].toString();
			nomCouches.add(nomCouche);
		}

		srids=FenetreBuffersNumeros.getSrids();

		System.out.println("nombreClasses"+ nombreClasses.size());

		for(int fg=0;fg<srids.size();fg++){
			System.out.println("srids"+ srids.get(fg));

		}


		System.out.println("buffer"+ nombreClasses.get(0));
		System.out.println("buffer"+ nomCouches.get(0));


		System.out.println("reclassification2");

		for(int i=0;i<nombreClasses.size();i++){
			valeurs.add(Double.parseDouble(nombreClasses.get(i)));
		}






		//	this.connexion=connexion;
		if(FenetreBuffersTailles.nomShape1.size()==0){
			FenetreBuffersTailles.nomShape1=Rreclassification.getNomCouches();
			maximValues=Rreclassification.getMaximCouches();
		}


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
		ColumnName.setMinWidth(200);
		model.addColumn(ColumnName);

		TableColumn ColumnName2     = new TableColumn();
		ColumnName2.setMinWidth(200);
		model.addColumn(ColumnName2);


		tableau.getColumnModel().getColumn(0).setHeaderValue(new String("Nom"));
		tableau.getColumnModel().getColumn(1).setHeaderValue(new String("Valeur"));


		tableau.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public synchronized void valueChanged(ListSelectionEvent abc) {
				// TODO Auto-generated method stub
				setSel(tableau.getSelectedRow());
				System.out.println("sel"+getSel());
			}
		});


		for (int efg=0;efg<nomCouches.size();efg++){                     //// a revoir, probleme nomcouches
			System.out.println("efg"+nomCouches.get(efg));
		}
		for(jjj=0;jjj<valeurs.size();jjj++){
			System.out.println("jjj"+valeurs.get(jjj));
		}



		for (int efg=0;efg<nomCouches.size();efg++){                     //// a revoir, probleme nomcouches
			//	model.addRow(new Object []{nomCouches.get(efg)});

			for(jjj=0;jjj<valeurs.get(efg);jjj++){
				model.addRow(new Object [] {nomCouches.get(efg),""});
				srids1.add(srids.get(efg));

			}
		}

		for(int fg=0;fg<srids1.size();fg++){
			System.out.println("srids1"+ srids1.get(fg));

		}


		//this.model.removeRow(1);
		JPanel pan = new JPanel();




		int a = tableau.getSelectedColumn();
		System.out.println("a"+ a);



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



	public static ArrayList<String> getMaximValues() {
		return maximValues;
	}

	public static void setMaximValues(ArrayList<String> maximValues) {
		FenetreBuffersTailles.maximValues = maximValues;
	}

	public static ArrayList<String> getMaxim() {
		return maxim;
	}

	public static void setMaxim(ArrayList<String> maxim) {
		FenetreBuffersTailles.maxim = maxim;
	}

	public static ArrayList<String[][]> getTableauxx() {
		return tableauxx;
	}

	public static void setTableauxx(ArrayList<String[][]> tableauxx) {
		FenetreBuffersTailles.tableauxx = tableauxx;
	}

	public static ArrayList<String> getNomCouches() {
		return nomCouches;
	}

	public static void setNomCouches(ArrayList<String> nomCouches) {
		FenetreBuffersTailles.nomCouches = nomCouches;
	}

	public static ArrayList<String> getNomShape1() {
		return nomShape1;
	}

	public static void setNomShape1(ArrayList<String> nomShape1) {
		FenetreBuffersTailles.nomShape1 = nomShape1;
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

		double minim=0;
		String siize;

		int high = model.getRowCount();
		int weight = model.getColumnCount();

		//	String test=(model.getValueAt(0, 2).toString());

		//		System.out.println("test: "+test);
		//		System.out.println("high: "+high);
		//		System.out.println("width: "+weight);

		String high3 [][] = new String[high][weight+1]; 

		for(int high2=0;high2<high;high2++){


			high3 [high2][0]=model.getValueAt(high2,0).toString();

			System.out.println("high2 0 "+high3 [high2][0]);

			siize=model.getValueAt(high2, 1).toString();
			minim = Double.parseDouble(siize);
			System.out.println("double"+minim);



			high3 [high2][1]=Double.toString(minim);
			high3 [high2][2]=srids1.get(high2);

			//	high3 [high2][2]=model.getValueAt(high2,2).toString();
			//	System.out.println(model.getValueAt(high2,0).toString());
			//	System.out.println(model.getValueAt(high2,1).toString());
			//	System.out.println(model.getValueAt(high2,2).toString());

			//	System.out.println(model.getValueAt(high2,2).toString());

			tableauTailles.add(high3);
			//tableauTailles.add({model.getValueAt(0, 0).toString(),model.getValueAt(0, 1).toString(),model.getValueAt(0, 2).toString()})
		}


		for(int fg=0;fg<tableauTailles.size();fg++){
			//for (String[][] f: tableauTailles){
			String test34[][]=tableauTailles.get(fg);
			System.out.println("tableauTailles: "+test34[fg][0]);
			System.out.println("tableauTailles1: "+test34[fg][1]);
			System.out.println("tableauTailles2: "+test34[fg][2]);

			//System.out.println(test34[fg][2]);


		}

		this.setVisible(false);

		try {
			new RequeteBuffers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public JButton getChange() {
		return change;
	}

	public void setChange(JButton change) {
		this.change = change;
	}

	public JButton getSupprimer() {
		return supprimer;
	}

	public void setSupprimer(JButton supprimer) {
		this.supprimer = supprimer;
	}

	public JButton getValider() {
		return valider;
	}

	public void setValider(JButton valider) {
		this.valider = valider;
	}

	public ArrayList<String> getHmm() {
		return hmm;
	}

	public void setHmm(ArrayList<String> hmm) {
		this.hmm = hmm;
	}



	public static ArrayList<String[][]> getTableauTailles() {
		return tableauTailles;
	}

	public static void setTableauTailles(ArrayList<String[][]> tableauTailles) {
		FenetreBuffersTailles.tableauTailles = tableauTailles;
	}

	public JTable getTableau() {
		return tableau;
	}




}
