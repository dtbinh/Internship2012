import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;



public class FenetreTaillePixel extends JFrame implements ActionListener{

	/**
	 * 
	 */
	//private JLabel label1 = new JLabel("Taille du pixel");

	//private JTextField saisiePixel;

	private static final long serialVersionUID = 1L;
	ConnexionTaillePixel connexion;
	//private JButton change = new JButton("Charger une couche");
	private JButton supprimer = new JButton("Supprimer");
	private JButton valider = new JButton("valider");
	private JButton chargerTaillePixel = new JButton("Charger Taille Pixel");

	//private JTextComponent taillePixel = new JTextComponent();

	private int sel;
	//	private ArrayList <String> hmm = new ArrayList <String> ();
	private static ArrayList <String> nomShape1 = new ArrayList <String> ();
	private static ArrayList <String> sridss = new ArrayList<String>();
	private static ArrayList <String> nomShape2 = new ArrayList <String> ();
	private static ArrayList <String> sridss2 = new ArrayList<String>();
	private static ArrayList <String> taillesPixel = new ArrayList<String>();





	public static ArrayList<String> getNomShape1() {
		return nomShape1;
	}

	public static void setNomShape1(ArrayList<String> nomShape1) {
		FenetreTaillePixel.nomShape1 = nomShape1;
	}

	public static ArrayList<String> getSridss() {
		return sridss;
	}

	public static void setSridss(ArrayList<String> sridss) {
		FenetreTaillePixel.sridss = sridss;
	}

	public static ArrayList<String> getNomShape2() {
		return nomShape2;
	}

	public static void setNomShape2(ArrayList<String> nomShape2) {
		FenetreTaillePixel.nomShape2 = nomShape2;
	}

	public static ArrayList<String> getSridss2() {
		return sridss2;
	}

	public static void setSridss2(ArrayList<String> sridss2) {
		FenetreTaillePixel.sridss2 = sridss2;
	}



	private ArrayList <String [][]> tableauTailles = new ArrayList <String [][]> ();

	private ArrayList <String> splitt = new ArrayList<String>();
	private String finalShape;


	private final DefaultTableModel model = new DefaultTableModel();
	private final JTable tableau = new JTable(model);

	private static String test34[][];

	public int getSel() {
		return sel;
	}

	public void setSel(int sel) {
		this.sel = sel;
	}





	public ArrayList<String[][]> getTableauTailles() {
		return tableauTailles;
	}

	public void setTableauTailles(ArrayList<String[][]> tableauTailles) {
		this.tableauTailles = tableauTailles;
	}

	public static String[][] getTest34() {
		return test34;
	}

	public static void setTest34(String[][] test34) {
		FenetreTaillePixel.test34 = test34;
	}

	public JTable getTableau() {
		return tableau;
	}

	public String getFinalShape() {
		return finalShape;
	}

	public void setFinalShape(String finalShape) {
		this.finalShape = finalShape;
	}

	public FenetreTaillePixel(){


		//	saisiePixel=new JTextField("",20);
		System.out.println("nouvelleee size: "+ConnexionTaillePixel.getArrayy().size());
		System.out.println("nouvelleee size2: "+nomShape1.size());


		//	this.connexion=connexion;

		if(FenetreTaillePixel.nomShape1.size()==0){
			FenetreTaillePixel.nomShape1=ConnexionTaillePixel.getArrayy();
			FenetreTaillePixel.sridss=ConnexionTaillePixel.getGeomms();
			FenetreTaillePixel.nomShape2=ConnexionTaillePixel.getArrayy2();
			FenetreTaillePixel.sridss2=ConnexionTaillePixel.getGeomms2();
			System.out.println("nouvelleee size11: "+ConnexionTaillePixel.getArrayy().size());
			System.out.println("nouvelleee size211: "+nomShape1.size());
		}
		
		
		

		//nomShape2=nomShape1;

		if(FenetreTaillePixel.getNomShape2().size()>0){
			FenetreTaillePixel.getNomShape2().removeAll(FenetreTaillePixel.getNomShape2());
			FenetreTaillePixel.getNomShape2().addAll(ConnexionTaillePixel.getArrayy());
			FenetreTaillePixel.getSridss2().removeAll(FenetreTaillePixel.getSridss2());
			FenetreTaillePixel.getSridss2().addAll(ConnexionTaillePixel.getGeomms());
		}


		for(String fg:nomShape1){
			System.out.println("fg "+fg);
		}

		for(String bc:nomShape2){
			System.out.println("bc "+bc);
		}


		this.setLocationRelativeTo(null);
		this.setTitle("JTable5678");
		this.setSize(500, 240);

		TableColumn ColumnName     = new TableColumn();

		ColumnName.setMinWidth(100);

		TableColumn ColumnName1   = new TableColumn();

		TableColumn ColumnName2   = new TableColumn();

		ColumnName1.setMinWidth(100);

		ColumnName2.setMinWidth(100);

		model.addColumn(ColumnName);
		model.addColumn(ColumnName1);
		model.addColumn(ColumnName2);

		tableau.getColumnModel().getColumn(0).setHeaderValue(new String("Nom"));
		tableau.getColumnModel().getColumn(1).setHeaderValue(new String("Surface"));
		tableau.getColumnModel().getColumn(2).setHeaderValue(new String("Nombre habitants"));

		tableau.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent abc) {
				// TODO Auto-generated method stub
				setSel(tableau.getSelectedRow());
				System.out.println("sel"+getSel());
			}
		});
		
		System.out.println("size "+RequeteSurface.getTailles().size());
		System.out.println("size2 "+nomShape1.size());

		for(int i=0;i<RequeteSurface.getTailles().size();i++){

		for(int j=0;j<nomShape1.size();j++){

				if(RequeteSurface.getShapes1().get(i).compareTo(nomShape1.get(j))==0){
					taillesPixel.add(RequeteSurface.getTailles().get(i));
				}
			}
		}

		for(int ab=0;ab<nomShape1.size();ab++){

			model.addRow(new Object [] {nomShape1.get(ab), taillesPixel.get(ab),""});

		}

		//JScrollPane listScroller = new JScrollPane(list);
		//listScroller.setPreferredSize(new Dimension(250, 80));
		//listScroller.setAlignmentX(LEFT_ALIGNMENT);

		//Lay out the label and scroll pane from top to bottom.


		JPanel pan = new JPanel();

		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));




		//		Insets insets = pane.getInsets();




		//int a = tableau.getSelectedColumn();
		//System.out.println("a"+ a);
		supprimer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				nomShape2.remove(getSel());
				//ConnexionTraitement.getArrayy().remove(getSel());
				sridss2.remove(getSel());
				//ConnexionTraitement.getGeomms().remove(getSel());
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

		JPanel pan2 = new JPanel();
		this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
		this.getContentPane().add(pan, BorderLayout.EAST);
		this.getContentPane().add(pan2, BorderLayout.SOUTH);


		valider.addActionListener(this);
		pan.add(valider);

		chargerTaillePixel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		pan.add(chargerTaillePixel);

		//label1.setLabelFor(saisiePixel);
		//pan2.add(label1);
		//pan2.add(saisiePixel);
	}





	public ArrayList<String> getSplitt() {
		return splitt;
	}

	public void setSplitt(ArrayList<String> splitt) {
		this.splitt = splitt;
	}



	public synchronized void actionPerformed(ActionEvent arg0) {

		int high = model.getRowCount();
		int weight = model.getColumnCount();

		//	String test=(model.getValueAt(0, 2).toString());

		//		System.out.println("test: "+test);
		//		System.out.println("high: "+high);
		//		System.out.println("width: "+weight);

		String high3 [][] = new String[high][weight]; 

		for(int high2=0;high2<high;high2++){

			high3 [high2][0]=model.getValueAt(high2,0).toString();
			high3 [high2][1]=model.getValueAt(high2,1).toString();
			high3 [high2][2]=model.getValueAt(high2,2).toString();
			System.out.println(model.getValueAt(high2,0).toString());
			System.out.println(model.getValueAt(high2,1).toString());
			System.out.println(model.getValueAt(high2,2).toString());

			tableauTailles.add(high3);
			//tableauTailles.add({model.getValueAt(0, 0).toString(),model.getValueAt(0, 1).toString(),model.getValueAt(0, 2).toString()})
		}


		for(int fg=0;fg<tableauTailles.size();fg++){
			//for (String[][] f: tableauTailles){
			String test34[][]=tableauTailles.get(fg);
			System.out.println(test34[fg][0]);
			System.out.println(test34[fg][1]);
			System.out.println(test34[fg][2]);


		}

		this.setVisible(false);

		try {
			new DensitePopulation(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}




}
