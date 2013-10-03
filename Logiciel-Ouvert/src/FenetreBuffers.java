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



public class FenetreBuffers extends JFrame implements ActionListener{

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
	private ArrayList <String> nomShape = new ArrayList <String> ();
	private static ArrayList <String> nomShape1 = new ArrayList <String> ();
	private static ArrayList <String> srids = new ArrayList <String> ();

	private ArrayList <String> splitt = new ArrayList<String>();
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

	public FenetreBuffers(){

		//	this.connexion=connexion;
		nomShape1.clear();
		srids.clear();

		System.out.println("buffffers");

		if(FenetreBuffers.nomShape1.size()==0){
			FenetreBuffers.nomShape1=ConnexionBuffers.getArrayy();
			FenetreBuffers.srids=ConnexionBuffers.getGeomms();
		}

		for(String fg:nomShape1){
			System.out.println("fg "+fg);
		}

		for(String fg:srids){
			System.out.println("srids "+fg);
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



		if(model.getRowCount()<nomShape1.size()&&model.getRowCount()==0){

			//System.out.println("sizze "+model.getRowCount());

			for (int efg=0;efg<nomShape1.size();efg++){
				System.out.println("efg");
				System.out.println("efg "+nomShape1.get(efg));
				model.addRow(new Object [] {nomShape1.get(efg)});
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

				srids.remove(getSel());
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



	public static ArrayList<String> getNomShape1() {
		return nomShape1;
	}

	public static void setNomShape1(ArrayList<String> nomShape1) {
		FenetreBuffers.nomShape1 = nomShape1;
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

		FenetreBuffersNumeros fenet = new FenetreBuffersNumeros();
		fenet.setVisible(true);

	}

	public static ArrayList<String> getSrids() {
		return srids;
	}

	public static void setSrids(ArrayList<String> srids) {
		FenetreBuffers.srids = srids;
	}




}
