//import java.awt.BorderLayout;
//import java.util.ArrayList;
//import java.util.regex.Pattern;
//import javax.swing.DefaultCellEditor;
//import javax.swing.JCheckBox;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.table.AbstractTableModel;
//
//
//
//
//public class FenetreChoixCategorie extends JFrame {
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	private JTable tableau;
//
//	private ArrayList<String> fichiers = OuvrirFenetre.pathArray();
//	private ArrayList<String> typeDonnees= new ArrayList<String>();
//	private String finalShape;
//	private ArrayList <String> splitt = new ArrayList<String>();
//	private String[] comboData = {"Végétation", "Eau", "Bâtiments", "Quartiers", "PateMaison"};
//
//	private String[] valider = {"OK", "OK", "OK", "OK", "OK"};
//
//	public FenetreChoixCategorie(){
//		
//		for (String efg:fichiers){
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
//			this.setFinalShape(dernier);
//
//			splitt.add(finalShape);
//
//		}
//		
//		typeDonnees.add("végétation");
//		typeDonnees.add("eau");
//		typeDonnees.add("bâtiments");
//		typeDonnees.add("quartiers");
//		typeDonnees.add("pateMaison");
//
//		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setSize(600, 880);
//		this.setTitle("Sélectionnez les correspondances");
//
//		Object[][] data = new Object[fichiers.size()][fichiers.size()];
//
//		for (int i = 0; i<splitt.size();i++){
//			data[i][0]= splitt.get(i);
//		}
//		
//		for (int i=0;i<splitt.size();i++){
//			data [i][1] = comboData[i];
//			data [i][2] = valider[i];
//		}
//	
//		
//		String  title[] = {"Nome shape", "Correspondance", "OK?"};
//		JComboBox combo = new JComboBox(comboData);
//
//		ZModel zModel = new ZModel(data, title);
//
//		this.tableau = new JTable(zModel);
//		this.tableau.setRowHeight(30);
//		
//		this.tableau.getColumn("OK?").setCellRenderer(new ButtonRenderer());
//		this.tableau.getColumn("OK?").setCellEditor(new ButtonEditor(new JCheckBox()));
//
//		this.tableau.getColumn("Correspondance").setCellEditor(new DefaultCellEditor(combo));
//
//		
//                //On ajoute notre tableau à notre contentPane dans un scroll
//                //Sinon les titres des colonnes ne s'afficheront pas ! !	
//		this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
//	
//	}
//	
//	class ZModel extends AbstractTableModel{
//
//		/**
//		 * 
//		 */
//		private static final long serialVersionUID = 1L;
//		private Object[][] data;
//		private String[] title;
//		/**
//		 * Constructeur
//		 * @param data
//		 * @param title
//		 */
//		public ZModel(Object[][] data, String[] title){
//			this.data = data;
//			this.title = title;
//		}
//		/**
//		* Retourne le titre de la colonne à l'indice spécifé
//		*/
//		public String getColumnName(int col) {
//		  return this.title[col];
//		}
//
//		/**
//		 * Retourne le nombre de colonnes
//		 */
//		public int getColumnCount() {
//			return this.title.length;
//		}
//		
//		/**
//		 * Retourne le nombre de lignes
//		 */
//		public int getRowCount() {
//			return this.data.length;
//		}
//		
//		/**
//		 * Retourne la valeur à l'emplacement spécifié
//		 */
//		public Object getValueAt(int row, int col) {
//			return this.data[row][col];
//		}
//		
//		/**
//		 * Défini la valeur à l'emplacement spécifié
//		 */
//		public void setValueAt(Object value, int row, int col) {
//			//On interdit la modification sur certaine colonne !
//			if(!this.getColumnName(col).equals("Age") && !this.getColumnName(col).equals("Suppression"))
//				this.data[row][col] = value;
//		}
//				
//		/**
//		* Retourne la classe de la donnée de la colonne
//		* @param col
//		*/
//		public Class<? extends Object> getColumnClass(int col){
//			//On retourne le type de la cellule à la colonne demandée
//			//On se moque de la ligne puisque les données sur chaque ligne sont les mêmes
//			//On choisit donc la première ligne
//			return this.data[0][col].getClass();
//		}
//
//		public boolean isCellEditable(int row, int col){
//			return true;
//		}
//	}
//	
//	
//	
//	
//
//	public String getFinalShape() {
//		return finalShape;
//	}
//
//	public void setFinalShape(String finalShape) {
//		this.finalShape = finalShape;
//	}
//	
//	
//	
//}
