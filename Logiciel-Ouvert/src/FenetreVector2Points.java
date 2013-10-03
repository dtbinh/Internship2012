import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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



public class FenetreVector2Points extends JFrame implements ActionListener{

	/**
	 * 
	 */
	//private JLabel label1 = new JLabel("Taille du pixel");

	//private JTextField saisiePixel;

	private static final long serialVersionUID = 1L;
	//private JButton change = new JButton("Charger une couche");
	private JButton supprimer = new JButton("Supprimer");
	private JButton valider = new JButton("valider");
	private JButton chargerTaillePixel = new JButton("Charger Taille Pixel");

	//private JTextComponent taillePixel = new JTextComponent();

	private int sel;
	//	private ArrayList <String> hmm = new ArrayList <String> ();
	private static ArrayList <String> nomShape = new ArrayList <String> ();
	private static ArrayList <String> nomShape1 = new ArrayList <String> ();
	private ArrayList <String [][]> tableauTailles = new ArrayList <String [][]> ();
	private static ArrayList <String [][]> taillesPixel = new ArrayList <String [][]> ();

	private static ArrayList <String> nomShape2 = new ArrayList <String> ();


	public static ArrayList<String[][]> getTaillesPixel() {
		return taillesPixel;
	}

	public static void setTaillesPixel(ArrayList<String[][]> taillesPixel) {
		FenetreVector2Points.taillesPixel = taillesPixel;
	}



	private static ArrayList <String> nomTaillePixel = new ArrayList <String> ();
	private static ArrayList <String> lestaillesdespixel = new ArrayList <String> ();
	private ArrayList <String> taillesdesPixel = new ArrayList <String> ();
	private ArrayList <String> taillesdesPixel2 = new ArrayList <String> ();
	private static ArrayList <String> srids = new ArrayList <String> ();
	private static ArrayList <String> srids1 = new ArrayList <String> ();
	private static ArrayList <String> srids2 = new ArrayList <String> ();
	private static ArrayList <String> nomCouche = new ArrayList <String> ();
	private static ArrayList <String> tailleCouche = new ArrayList <String> ();

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






	public static ArrayList<String> getSrids2() {
		return srids2;
	}

	public static void setSrids2(ArrayList<String> srids2) {
		FenetreVector2Points.srids2 = srids2;
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
		FenetreVector2Points.test34 = test34;
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

	public FenetreVector2Points(){

		nomCouche=DensitePopulation.getNomCouches2();
		tailleCouche=DensitePopulation.getTaillesCouches();


		//	saisiePixel=new JTextField("",20);
		if(DensitePopulation.getTaillesPixel2().size()>0){
			FenetreVector2Points.taillesPixel=DensitePopulation.getTaillesPixel2();
			FenetreVector2Points.srids=DensitePopulation.getSrids();
		}



		System.out.println("print fenetre raster taille size"+FenetreVector2Points.taillesPixel.size());




		System.out.println(lestaillesdespixel.size());


		if(FenetreVector2Points.nomShape1.size()==0){
			FenetreVector2Points.nomShape1=ConnexionVector2Points.getArrayy();
			FenetreVector2Points.srids1=ConnexionVector2Points.getGeomms();
			FenetreVector2Points.nomShape2=ConnexionVector2Points.getArrayy2();
			FenetreVector2Points.srids2=ConnexionVector2Points.getGeomms2();
		}
		else{

			//nomShape2=nomShape1;

			if(FenetreVector2Points.nomShape1.size()>0){
				FenetreVector2Points.getNomShape1().removeAll(FenetreVector2Points.getNomShape1());
				FenetreVector2Points.getNomShape1().addAll(ConnexionVector2Points.getArrayy());
				FenetreVector2Points.getSrids1().removeAll(FenetreVector2Points.getSrids1());
				FenetreVector2Points.getSrids1().addAll(ConnexionVector2Points.getGeomms());
			}
		}



		if(FenetreVector2Points.getNomShape2().size()>0){
			FenetreVector2Points.getNomShape2().removeAll(FenetreVector2Points.getNomShape2());
			FenetreVector2Points.getNomShape2().addAll(ConnexionVector2Points.getArrayy());
			FenetreVector2Points.getSrids2().removeAll(FenetreVector2Points.getSrids2());
			FenetreVector2Points.getSrids2().addAll(ConnexionVector2Points.getGeomms());
		}

		System.out.println("srids siize " +srids1.size());

		/*if(RequeteRaster.getNomRaster().size()>0){
			for(int i=0,j=0;i<nomTaillePixel.size()&&j<RequeteRaster.getNomRaster().size();i++,j++){
				if(nomTaillePixel.get(i).contains(RequeteRaster.getNomRaster().get(i))){
					taillesPixel.remove(i);
					lestaillesdespixel.remove(i);
				}
			}
		}*/

		this.setLocationRelativeTo(null);
		this.setTitle("JTable5678");
		this.setSize(500, 240);

		for(int f=0;f<nomShape1.size();f++){
			System.out.println("1 "+nomShape1.get(f));
		}
		FenetreVector2Points.nomShape1.removeAll(nomCouche);
		//this.srids1.removeAll(srids);

		for(int f=0;f<nomTaillePixel.size();f++){
			System.out.println("2 "+nomTaillePixel.get(f));
		}

		for(int f=0;f<nomShape1.size();f++){
			System.out.println("3 "+nomShape1.get(f));
		}

		TableColumn ColumnName     = new TableColumn();

		ColumnName.setMinWidth(100);

		TableColumn ColumnName1   = new TableColumn();


		ColumnName1.setMinWidth(100);


		model.addColumn(ColumnName);
		model.addColumn(ColumnName1);

		tableau.getColumnModel().getColumn(0).setHeaderValue(new String("Nom"));
		tableau.getColumnModel().getColumn(1).setHeaderValue(new String("Taille Pixel"));

		tableau.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent abc) {
				// TODO Auto-generated method stub
				setSel(tableau.getSelectedRow());
				System.out.println("sel"+getSel());
			}
		});




		for(int ab=0;ab<nomShape1.size();ab++){
			//if(!RequeteRaster.getNomRaster().contains(nomShape1.get(ab))){

			model.addRow(new Object [] {nomShape1.get(ab),""});
			//}
		}

		for(int ac=0;ac<nomCouche.size();ac++){
			//if(!RequeteRaster.getNomRaster().contains(nomCouche.get(ac))){

			model.addRow(new Object [] {nomCouche.get(ac),tailleCouche.get(ac)});
			//}
		}


		for(int abcd=0;abcd<model.getRowCount();abcd++){

			taillesdesPixel.add(model.getValueAt(abcd, 1).toString());

		}

		for(String abcd:taillesdesPixel){
			System.out.println("abcd "+abcd);
		}


		String bcd ="";
		valider.setEnabled(true);

		for(String abcd:taillesdesPixel){
			bcd=abcd;
			if(bcd==""){
				valider.setEnabled(false);
			}
		}



		//			if(bcd!=""){
		//				valider.setEnabled(true);


		//lestaillesdespixel.get(ac)

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
				//				for(int icv=0;icv<model.getRowCount();icv++){
				//					System.out.println(model.getValueAt(icv,1));
				//					System.out.println(srids.get(icv));
				//					if(taillesPixel.contains(model.getValueAt(icv, 1))){
				//						taillesPixel.remove(model.getValueAt(icv, 1));
				//						
				//					}
				//				}
				//				
				//				for(int ab=0;ab<model.getRowCount();ab++){
				//					System.out.println(model.getValueAt(ab,1));
				//					System.out.println(srids1.get(ab));
				//
				//					if(nomShape1.contains(model.getValueAt(ab, 1))){
				//						nomShape1.remove(model.getValueAt(ab, 1));
				//						
				//					}
				//				}

				nomShape2.remove(getSel());
				//ConnexionTraitement.getArrayy().remove(getSel());
				srids2.remove(getSel());
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
		//this.setDefaultCloseOperation(JFrame.);
		//this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		valider.addActionListener(this);
		pan.add(valider);

		chargerTaillePixel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				for(int abcd=0;abcd<model.getRowCount();abcd++){

					taillesdesPixel2.add(model.getValueAt(abcd, 1).toString());

				}

				String xvz="";
				for(String abcd:taillesdesPixel2){

					xvz=abcd;
					if(xvz!=""){
						valider.setEnabled(true);
					}
				}


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
		double size=0.0;
		String siize;
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

			high3 [high2][1]=Double.toString(size);
			//	high3 [high2][2]=model.getValueAt(high2,2).toString();
			System.out.println(model.getValueAt(high2,0).toString());
			System.out.println(model.getValueAt(high2,1).toString());
			//	System.out.println(model.getValueAt(high2,2).toString());

			tableauTailles.add(high3);
			//tableauTailles.add({model.getValueAt(0, 0).toString(),model.getValueAt(0, 1).toString(),model.getValueAt(0, 2).toString()})
		}


		for(int fg=0;fg<tableauTailles.size();fg++){
			//for (String[][] f: tableauTailles){
			String test34[][]=tableauTailles.get(fg);
			System.out.println("tableauTailles: "+test34[fg][0]);
			System.out.println("tableauTailles1: "+test34[fg][1]);
			//System.out.println(test34[fg][2]);


		}

		this.setVisible(false);

		try {
			new RequeteUnionVector2Points(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}

	public static ArrayList<String> getNomShape() {
		return nomShape;
	}

	public static void setNomShape(ArrayList<String> nomShape) {
		FenetreVector2Points.nomShape = nomShape;
	}

	public static ArrayList<String> getNomShape1() {
		return nomShape1;
	}

	public static void setNomShape1(ArrayList<String> nomShape1) {
		FenetreVector2Points.nomShape1 = nomShape1;
	}

	public static ArrayList<String> getNomTaillePixel() {
		return nomTaillePixel;
	}

	public static void setNomTaillePixel(ArrayList<String> nomTaillePixel) {
		FenetreVector2Points.nomTaillePixel = nomTaillePixel;
	}

	public static ArrayList<String> getLestaillesdespixel() {
		return lestaillesdespixel;
	}

	public static void setLestaillesdespixel(ArrayList<String> lestaillesdespixel) {
		FenetreVector2Points.lestaillesdespixel = lestaillesdespixel;
	}

	public static ArrayList<String> getSrids() {
		return srids;
	}

	public static void setSrids(ArrayList<String> srids) {
		FenetreVector2Points.srids = srids;
	}

	public static ArrayList<String> getSrids1() {
		return srids1;
	}

	public static void setSrids1(ArrayList<String> srids1) {
		FenetreVector2Points.srids1 = srids1;
	}

	public static ArrayList<String> getNomCouche() {
		return nomCouche;
	}

	public static void setNomCouche(ArrayList<String> nomCouche) {
		FenetreVector2Points.nomCouche = nomCouche;
	}

	public static ArrayList<String> getNomShape2() {
		return nomShape2;
	}

	public static void setNomShape2(ArrayList<String> nomShape2) {
		FenetreVector2Points.nomShape2 = nomShape2;
	}

}




