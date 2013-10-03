import java.util.ArrayList;
import javax.swing.JTextField;


public class InfoShp2pgsql{

	ConnexionBaseDonnees connex;
	Fenetre3 fen;

	private String finalShp;

	public JTextField saisie1;
	public JTextField saisie2;
	public JTextField saisie3;

	private ArrayList <String> nomTableArray = new ArrayList <String>();
	private ArrayList <String> nomGeomArray = new ArrayList <String>();
	private ArrayList <String> projectionArray= new ArrayList <String>();
	private ArrayList <Integer> compteur = new ArrayList<Integer>();


	private String nomTable;
	private String projection;
	private String nomgeom;
	private String pathSapess;

	private int compter=0;
	private int sizenoTable=nomTableArray.size();

	private String vegetation="";
	private String eau="";
	private String batiments="";
	private String quartiers="";
	private String pateMaison="";
	private ArrayList <String> correspon = new ArrayList <String>();



	public InfoShp2pgsql(ConnexionBaseDonnees connex, Fenetre3 fen)  {

		System.out.println("hello2");

		this.connex= connex;
		this.fen=fen;

		//System.out.println(fen.getReorganise().size());
		this.setPathSapess(this.connex.getPathDuShape());



		correspon = Fenetre3.reorganise2();

		this.setVegetation(this.correspon.get(0));
		this.setEau(this.correspon.get(1));
		this.setBatiments(this.correspon.get(2));
		this.setQuartiers(this.correspon.get(3));
		this.setPateMaison(this.correspon.get(4));

		nomTableArray.add("vegetation");
		nomTableArray.add("eaux");
		nomTableArray.add("batiments");
		nomTableArray.add("quartiers");
		nomTableArray.add("pate_maison");

		nomGeomArray.add("geom_vegetation");
		nomGeomArray.add("geom_eaux");
		nomGeomArray.add("geom_batiements");
		nomGeomArray.add("geom_quartiers");
		nomGeomArray.add("geom_pate_maison");




	}



	public ArrayList<String> getCorrespon() {
		return correspon;
	}



	public void setCorrespon(ArrayList<String> correspon) {
		this.correspon = correspon;
	}



	public String getVegetation() {
		return vegetation;
	}



	public void setVegetation(String vegetation) {
		this.vegetation = vegetation;
	}



	public String getEau() {
		return eau;
	}



	public void setEau(String eau) {
		this.eau = eau;
	}



	public String getBatiments() {
		return batiments;
	}



	public void setBatiments(String batiments) {
		this.batiments = batiments;
	}



	public String getQuartiers() {
		return quartiers;
	}



	public void setQuartiers(String quartiers) {
		this.quartiers = quartiers;
	}



	public String getPateMaison() {
		return pateMaison;
	}



	public void setPateMaison(String pateMaison) {
		this.pateMaison = pateMaison;
	}



	public ArrayList<String> getNomTableArray() {
		return nomTableArray;
	}

	public void setNomTableArray(ArrayList<String> nomTableArray) {
		this.nomTableArray = nomTableArray;
	}

	public ArrayList<String> getNomGeomArray() {
		return nomGeomArray;
	}

	public void setNomGeomArray(ArrayList<String> nomGeomArray) {
		this.nomGeomArray = nomGeomArray;
	}

	public void ajouteNomTable(String nomTable){
		nomTableArray.add(nomTable);
	}

	public void ajouteProjection(String projection){
		nomTableArray.add(projection);
	}

	public void ajoutenomGeom(String nomGeom){
		nomTableArray.add(nomGeom);
	}

	public ArrayList<String> nomTableArray(){
		ArrayList <String> test = new ArrayList <String>();
		for (String e:this.nomTableArray){
			test.add(e);
		}
		return test;
	}

	public  ArrayList<String> nomGeomArray(){
		ArrayList <String> test = new ArrayList <String>();
		for (String e:this.nomGeomArray){
			test.add(e);
		}
		return test;
	}

	public  ArrayList<String> projectionArray(){
		ArrayList <String> test = new ArrayList <String>();
		for (String e:this.projectionArray){
			test.add(e);
		}
		return test;
	}

	public ArrayList<Integer> compteur(){
		ArrayList <Integer> test = new ArrayList <Integer>();
		for (Integer e:compteur){
			test.add(e);
		}
		return test;
	}

	public String getPathSapess() {
		return pathSapess;
	}

	public void setPathSapess(String pathSapess) {
		this.pathSapess = pathSapess;
	}

	public int getSizenoTable() {
		return sizenoTable;
	}

	public void setSizenoTable(int sizenoTable) {
		this.sizenoTable = sizenoTable;
	}

	public int getCompter() {
		return compter;
	}

	public void setCompter(int compter) {
		this.compter = compter;
	}

	public String getFinalShp() {
		return finalShp;
	}

	public void setFinalShp(String finalShp) {
		this.finalShp = finalShp;
	}

	public String getNomTable() {
		return nomTable;
	}

	public void setNomTable(String nomTable) {
		this.nomTable = nomTable;
	}

	public String getProjection() {
		return projection;
	}

	public void setProjection(String projection) {
		this.projection = projection;
	}

	public String getNomgeom() {
		return nomgeom;
	}

	public void setNomgeom(String nomgeom) {
		this.nomgeom = nomgeom;
	}

}



