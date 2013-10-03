import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;



public class InsererRaster extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JButton change = new JButton("Charger couches");
	private JButton retablir = new JButton("Rétablir");
	private JButton supprimer = new JButton("Supprimer");
	private JButton valider = new JButton("valider");

	private int sel;
	private ArrayList <String> hmm = new ArrayList <String> ();
	private ArrayList <String> nomShape = new ArrayList <String> ();
	private ArrayList <String> splitt = new ArrayList<String>();
	private String finalShape;


	private final DefaultTableModel model = new DefaultTableModel();
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

	public InsererRaster(){


		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("JTable112");
		this.setSize(500, 240);


		//		final DefaultTableModel model = new DefaultTableModel();
		//		final JTable tableau = new JTable(model);

		//		 tableau.setColumnSelectionAllowed (true);
		//		 tableau.setRowSelectionAllowed (false);


		TableColumn ColumnName     = new TableColumn();
		ColumnName.setHeaderValue(new String("Name") );
		ColumnName.setMinWidth(200);
		model.addColumn(ColumnName);
		tableau.getColumnModel().getColumn(0).setHeaderValue(new String("Nom couche"));

		tableau.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent abc) {
				// TODO Auto-generated method stub
				setSel(tableau.getSelectedRow());
				System.out.println("sel"+getSel());
			}
		});


		//model.addRow(new Object [] {"bla"});


		JPanel pan = new JPanel();


		change.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {	

				DefaultTableModel Model = new DefaultTableModel();

				Model.addColumn("test");
				hmm.clear();
				new OuvrirFenetreRaster();
				hmm = OuvrirFenetreRaster.pathArray();
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
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				model.removeRow(getSel());
			}

		}
				);
		pan.add(supprimer);

		retablir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				changeSize(75, 16);
				change.setEnabled(true);
				retablir.setEnabled(false);
			}			
		});

		retablir.setEnabled(false);
		pan.add(change);
		pan.add(retablir);
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

	/**
	 * Change la taille d'une ligne et d'une colonne
	 * J'ai mis deux boucles afin que vous puissiez voir comment parcourir les colonnes et les lignes
	 * @param width
	 * @param height
	 */
	public void changeSize(int width, int height){
		//On cr�e un objet TableColumn afin de travailler sur notre colonne
		TableColumn col;
		for(int i = 0; i < tableau.getColumnCount(); i++){
			if(i == 1){
				//On r�cup�re le mod�le de la colonne
				col = tableau.getColumnModel().getColumn(i);
				//On lui affecte la nouvelle valeur
				col.setPreferredWidth(width);
			}
		}				
		for(int i = 0; i < tableau.getRowCount(); i++){
			//On affecte la taille de la ligne � l'indice sp�cifi� !
			if(i == 1)
				tableau.setRowHeight(i, height);
		}
	}

	public synchronized void actionPerformed(ActionEvent arg0) {

		System.out.println("count"+this.model.getRowCount());

		// TODO Auto-generated method stub
		for(int i=0;i<model.getRowCount();i++){
			System.out.println(tableau.getValueAt(i,0));
			nomShape.add((tableau.getValueAt(i,0)).toString());
			System.out.println("rest"+nomShape.get(i));
		}
		for (String efg:nomShape){

			Pattern p = Pattern.compile("/");
			String [] split = p.split(efg);

			int length = split.length;

			String dernier = split[length-1];

			//Pattern point = Pattern.compile("\\.");

			//String [] splitPoint = point.split(dernier);

			finalShape=(dernier.replaceAll("\\W", "")).toLowerCase();


			System.out.println(finalShape);


			splitt.add(finalShape);

		}



		try {
			new Raster2pgsql(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.setVisible(false);

		if(ZFenetre.isCheck()==false){
			new ZFenetre();
		}

	}




}
