import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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



public class FenetreReclassificationParametres extends JFrame implements ActionListener{

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
	private static ArrayList <String> minimValues = new ArrayList <String> ();

	private ArrayList <String> splitt = new ArrayList<String>();
	private String finalShape;

	private static ArrayList <String [][]> tableauTailles = new ArrayList <String [][]> ();

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

	public FenetreReclassificationParametres(){

		//	this.connexion=connexion;
		nomShape1.clear();
		if(FenetreReclassificationParametres.nomShape1.size()==0){
			FenetreReclassificationParametres.nomShape1=Rreclassification.getNomCouches();
			maximValues=Rreclassification.getMaximCouches();
			minimValues=Rreclassification.getMinimCouches();
		}

		for(String fg:nomShape1){
			System.out.println("fg "+fg);
		}

		for(String bc:nomShape1){
			System.out.println("bc"+bc);
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

		TableColumn ColumnName3     = new TableColumn();
		ColumnName3.setMinWidth(200);
		model.addColumn(ColumnName3);

		TableColumn ColumnName4     = new TableColumn();
		ColumnName4.setMinWidth(200);
		model.addColumn(ColumnName4);

		tableau.getColumnModel().getColumn(0).setHeaderValue(new String("Nom"));
		tableau.getColumnModel().getColumn(1).setHeaderValue(new String("Nombre de classes"));
		tableau.getColumnModel().getColumn(2).setHeaderValue(new String("Minimum"));
		tableau.getColumnModel().getColumn(3).setHeaderValue(new String("Maximum"));


		tableau.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public synchronized void valueChanged(ListSelectionEvent abc) {
				// TODO Auto-generated method stub
				setSel(tableau.getSelectedRow());
				System.out.println("sel"+getSel());
			}
		});



		if(model.getRowCount()<nomShape1.size()&&model.getRowCount()==0){

			//System.out.println("sizze "+model.getRowCount());

			for (int efg=0;efg<nomShape1.size();efg++){
				model.addRow(new Object [] {nomShape1.get(efg),"",minimValues.get(efg),maximValues.get(efg)});
				//	System.out.println("sizze "+model.getRowCount());

			}
		}

		//this.model.removeRow(1);
		JPanel pan = new JPanel();




		int a = tableau.getSelectedColumn();
		System.out.println("a"+ a);


		supprimer.addActionListener(new ActionListener(){

			@Override
			public synchronized void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				nomShape1.remove(getSel());
				maximValues.remove(getSel());

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



	public static ArrayList<String> getMaximValues() {
		return maximValues;
	}

	public static void setMaximValues(ArrayList<String> maximValues) {
		FenetreReclassificationParametres.maximValues = maximValues;
	}

	public static ArrayList<String> getMinimValues() {
		return minimValues;
	}

	public static void setMinimValues(ArrayList<String> minimValues) {
		FenetreReclassificationParametres.minimValues = minimValues;
	}

	public static ArrayList<String> getNomShape1() {
		return nomShape1;
	}

	public static void setNomShape1(ArrayList<String> nomShape1) {
		FenetreReclassificationParametres.nomShape1 = nomShape1;
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

		double size=0;
		String siize;

		String minim;
		String maxim;

		double maximu;
		double minimu;

		int high = model.getRowCount();
		int weight = model.getColumnCount();

		//	String test=(model.getValueAt(0, 2).toString());

		//		System.out.println("test: "+test);
		//		System.out.println("high: "+high);
		//		System.out.println("width: "+weight);

		String high3 [][] = new String[high][weight]; 

		for(int high2=0;high2<high;high2++){

			high3 [high2][0]=model.getValueAt(high2,0).toString();
			siize=model.getValueAt(high2, 1).toString();
			size = Double.parseDouble(siize);
			System.out.println("double"+size);

			minim=model.getValueAt(high2, 2).toString();
			minimu=Double.parseDouble(minim);

			maxim=model.getValueAt(high2, 3).toString();
			maximu=Double.parseDouble(maxim);


			high3 [high2][1]=Double.toString(size);
			high3 [high2][2]=Double.toString(minimu);
			high3 [high2][3]=Double.toString(maximu);

			//	high3 [high2][2]=model.getValueAt(high2,2).toString();
			System.out.println(model.getValueAt(high2,0).toString());
			System.out.println(model.getValueAt(high2,1).toString());
			System.out.println(model.getValueAt(high2,2).toString());
			System.out.println(model.getValueAt(high2,3).toString());

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


		FenetreReclassificationParametresClasses fener = new FenetreReclassificationParametresClasses();
		fener.setVisible(true);
		//new RreclassificationParametre();



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
		FenetreReclassificationParametres.tableauTailles = tableauTailles;
	}

	public JTable getTableau() {
		return tableau;
	}




}
