import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;




public class Fenetre3 extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableau;
	private JComboBox combo;


	private ArrayList<String> fichiers = OuvrirFenetre.pathArray();
	private ArrayList<String> typeDonnees= new ArrayList<String>();
	private String finalShape;
	private ArrayList <String> splitt = new ArrayList<String>();
	private String[] comboData = {"Végétation", "Eau", "Bâtiments", "Quartiers", "PateMaison"};
	//private String[] valider = {"OK", "OK", "OK", "OK", "OK"};

	private ArrayList<String> numeros=new ArrayList<String>();
	private static ArrayList<String> reorganise=new ArrayList<String>();

	public Fenetre3(){
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		this.setTitle("Correspondances des couches");
		this.setSize(600, 250);
		this.createContent();
	}

	private void createContent() {
		// TODO Auto-generated method stub
		for (String efg:fichiers){

			Pattern p = Pattern.compile("/");
			String [] split = p.split(efg);

			int length = split.length;

			String dernier = split[length-1];

			//Pattern point = Pattern.compile("\\.");

			//String [] splitPoint = point.split(dernier);

			this.setFinalShape(dernier);

			splitt.add(finalShape);

		}

		typeDonnees.add("Végétation");
		typeDonnees.add("Eau");
		typeDonnees.add("Bâtiments");
		typeDonnees.add("Quartiers");
		typeDonnees.add("PateMaison");

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Veuillez choisir les correspondances");
		this.setSize(700, 280);

		Object[][] data = new Object[fichiers.size()][fichiers.size()];

		for (int i = 0; i<splitt.size();i++){
			data[i][0]= splitt.get(i);
		}

		for (int i=0;i<splitt.size();i++){
			data [i][1] = comboData[i];
		}

		//		for (int i=0;i<splitt.size();i++){
		//			data [i][1] = comboData[0];
		//		}

		String  title[] = {"Nome shape", "Correspondance"};

		combo = new JComboBox(comboData);
		ZModel zModel = new ZModel(data, title);


		this.tableau = new JTable(zModel);		
		this.tableau.setRowHeight(30);
		//this.tableau.getColumn("Age").setCellRenderer(new ButtonRenderer());
		//this.tableau.getColumn("Age").setCellEditor(new ButtonEditor(new JCheckBox()));

		//On définit l'éditeur par défaut pour la cellule 
		//en lui spécifiant quel type d'affichage prendre en compte
		this.tableau.getColumn("Correspondance").setCellEditor(new DefaultCellEditor(combo));
		DefaultTableCellRenderer dcr = new DefaultTableCellRenderer();
		this.tableau.getColumn("Correspondance").setCellRenderer(dcr);

		//On définit un éditeur pour la colonne "supprimer"
		//this.tableau.getColumn("Suppression").setCellEditor(new DeleteButtonEditor(new JCheckBox()));

		//On ajoute le tableau
		this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);

		JButton ajouter = new JButton("Valider les correspondances");
		ajouter.addActionListener(this);
		this.getContentPane().add(ajouter, BorderLayout.SOUTH);
	}		

	class ZModel extends AbstractTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Object[][] data;
		private String[] title;
		/**
		 * Constructeur
		 * @param data
		 * @param title
		 */
		public ZModel(Object[][] data, String[] title){
			this.data = data;
			this.title = title;
		}
		/**
		 * Retourne le titre de la colonne à l'indice spécifé
		 */
		public String getColumnName(int col) {
			return this.title[col];
		}

		/**
		 * Retourne le nombre de colonnes
		 */
		public int getColumnCount() {
			return this.title.length;
		}

		/**
		 * Retourne le nombre de lignes
		 */
		public int getRowCount() {
			return this.data.length;
		}

		/**
		 * Retourne la valeur à l'emplacement spécifié
		 */
		public Object getValueAt(int row, int col) {
			return this.data[row][col];
		}

		/**
		 * Défini la valeur à l'emplacement spécifié
		 */
		public void setValueAt(Object value, int row, int col) {
			//On interdit la modification sur certaine colonne !
			if(!this.getColumnName(col).equals("Age") && !this.getColumnName(col).equals("Suppression"))
				this.data[row][col] = value;
		}

		/**
		 * Retourne la classe de la donnée de la colonne
		 * @param col
		 */
		public Class<? extends Object> getColumnClass(int col){
			//On retourne le type de la cellule à la colonne demandée
			//On se moque de la ligne puisque les données sur chaque ligne sont les mêmes
			//On choisit donc la première ligne
			return this.data[0][col].getClass();
		}

		/**
		 * Méthode permettant de retirer une ligne du tableau
		 * @param position
		 */
		public void removeRow(int position){

			int indice = 0, indice2 = 0, nbRow = this.getRowCount()-1, nbCol = this.getColumnCount();
			Object temp[][] = new Object[nbRow][nbCol];

			for(Object[] value : this.data){
				if(indice != position){
					temp[indice2++] = value;
				}
				System.out.println("Indice = " + indice);
				indice++;
			}
			this.data = temp;
			temp = null;
			//Cette méthode permet d'avertir le tableau que les données ont été modifiées
			//Ce qui permet une mise à jours complète du tableau
			this.fireTableDataChanged();
		}

		/**
		 * Permet d'ajouter une ligne dans le tableau
		 * @param data
		 */
		public void addRow(Object[] data){
			int indice = 0, nbRow = this.getRowCount(), nbCol = this.getColumnCount();

			Object temp[][] = this.data;
			this.data = new Object[nbRow+1][nbCol];

			for(Object[] value : temp)
				this.data[indice++] = value;


			this.data[indice] = data;
			temp = null;
			//Cette méthode permet d'avertir le tableau que les données ont été modifiées
			//Ce qui permet une mise à jours complète du tableau
			this.fireTableDataChanged();
		}


		public boolean isCellEditable(int row, int col){
			return true;
		}


	}







	public String getFinalShape() {
		return finalShape;
	}

	public void setFinalShape(String finalShape) {
		this.finalShape = finalShape;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("test");



		//for(int i=0; i<tableau.getRowCount(); i++)
		//{
		//	numeros.add((String) tableau.getModel().getValueAt(i,0));
		//	System.out.println(numeros.get(i));

		//}
		//System.out.println("test0");


		for(int i=0;i<tableau.getRowCount();i++){
			numeros.add(fichiers.get(i));
			numeros.add((String) tableau.getModel().getValueAt(i,1));
			//System.out.println("test1");

		}

		for (int y=0;y<comboData.length;y++){
			for (int i=0;i<numeros.size();i++){
				//System.out.println("test3");
				if(numeros.get(i).equals(comboData[y])){
					reorganise.add(numeros.get(i-1));
					//System.out.println("test");
				}
			}
		}

		this.setReorganise(reorganise);

		for (int i=0;i<reorganise.size();i++){
			//System.out.println("test2");

			System.out.println(reorganise.get(i));
		}

		/*		for (String e:numeros){
			System.out.println(e);
			if(e.equals(comboData[0])){
				reorganise[0]=e;
			}
		}*/

		this.dispose();
		this.setVisible(false);

		ConnexionBaseDonnees basededonnes = new ConnexionBaseDonnees();
		basededonnes.setVisible(true);		


	}

	public static ArrayList<String> reorganise2(){
		ArrayList <String> test = new ArrayList <String>();
		for (String e:reorganise){
			test.add(e);
		}
		return test;
	}

	public ArrayList<String> getNumeros() {
		return numeros;
	}

	public void setNumeros(ArrayList<String> numeros) {
		this.numeros = numeros;
	}

	public ArrayList<String> getReorganise() {
		return reorganise;
	}

	public void setReorganise(ArrayList<String> reorganise) {
		Fenetre3.reorganise = reorganise;
	}


}
