import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class DensitePopulation {
	FenetreTaillePixel fen;
	//RequeteCominerRasterFenetrePixel habitants;
	RequeteSurface requete;
	ConnexionBaseDonnees connex;
	private double nombreHabitants;
	private double densiteHabitants;
	private double surfaceTotales;
	private static double taillePixel;
	private ArrayList <String[][]> tableaux = new ArrayList<String[][]>();
	private ArrayList <String> surfaces = new ArrayList<String>();
	private ArrayList <String> nombreHabit = new ArrayList<String>();
	private static ArrayList <String> nomCouches = new ArrayList<String>();


	private static ArrayList <String> shapes = new ArrayList <String>();
	private static ArrayList <String> shapes1 = new ArrayList <String>();
	private static ArrayList <String> srids = new ArrayList <String>();
	private static ArrayList <String> srids2 = new ArrayList <String>();

	private static ArrayList <String> srids1 = new ArrayList <String>();
	private static ArrayList <String> taillesCouches = new ArrayList <String>();
	private static ArrayList <String> nomCouches2 = new ArrayList<String>();

	private static ArrayList <String[][]> taillesPixel = new ArrayList<String[][]>();
	private static ArrayList <String[][]> taillesPixel2 = new ArrayList<String[][]>();

	//private ArrayList <String> tableaux = new ArrayList<String>();

	public static ArrayList<String> getSrids() {
		return srids;
	}

	public static void setSrids(ArrayList<String> srids) {
		DensitePopulation.srids = srids;
	}
	public static ArrayList<String[][]> getTaillesPixel2() {
		return taillesPixel2;
	}

	public static void setTaillesPixel2(ArrayList<String[][]> taillesPixel2) {
		DensitePopulation.taillesPixel2 = taillesPixel2;
	}



	public DensitePopulation(FenetreTaillePixel fen) throws SQLException, IOException{


		this.fen=fen;
		tableaux=fen.getTableauTailles();
		srids=FenetreTaillePixel.getSridss();
		srids2=FenetreTaillePixel.getSridss();

		for(int f=0;f<srids.size();f++){
			System.out.println("sridss "+ srids.get(f));
		}

		for(int fg=0;fg<tableaux.size();fg++){
			String test34[][]=tableaux.get(fg);
			String surface=test34[fg][1];
			surfaces.add(surface);
			String nombreHab=test34[fg][2];
			nombreHabit.add(nombreHab);
			String nomCouche=test34[fg][0];
			nomCouches.add(nomCouche);
		}



		for(int ff=0;ff<nomCouches.size();ff++){
			System.out.println(ff);
			System.out.println(srids2.size());

			shapes.add(nomCouches.get(ff));
			//srids1.add(srids.get(ff));
			surfaceTotales=Double.parseDouble(surfaces.get(ff));

			this.setNombreHabitants((Double.parseDouble(nombreHabit.get(ff))));

			System.out.println("Nombre Habitants: "+this.getNombreHabitants());
			//System.out.println("surface"+surfaceTotales);

			//this.surfaceTotales=requete.getSurfaceTotale();

			this.setDensiteHabitants(surfaceTotales/this.getNombreHabitants());

			if (this.getDensiteHabitants()>0){
				DensitePopulation.setTaillePixel(Math.sqrt(this.getDensiteHabitants()));
			}
			System.out.println("Taille pixel pour: "+shapes.get(ff)+" = "+DensitePopulation.getTaillePixel());
			String taillesPixels [][] = new String [surfaces.size()][2];
			taillesPixels [ff][0]=nomCouches.get(ff);
			taillesPixels [ff][1]=Double.toString(DensitePopulation.getTaillePixel());

			System.out.println("nomCouches3333 "+nomCouches.get(ff));

			System.out.println("srids11111 "+ srids2.get(ff));

			if(!nomCouches2.contains(nomCouches.get(ff))){
				System.out.println("srids11111 "+ nomCouches.get(ff));
				System.out.println("srids11111 "+ srids2.get(ff));
				nomCouches2.add(nomCouches.get(ff));
				taillesCouches.add(Double.toString(DensitePopulation.getTaillePixel()));
				srids1.add(srids2.get(ff));
			}

			if(!taillesPixel.contains(taillesPixels)){
				taillesPixel.add(taillesPixels);
			}

			if(!shapes1.contains(shapes.get(ff))){
				shapes1.add(shapes.get(ff));
				//				srids1.add(srids.get(ff));

			}

			//System.out.println(ConnexionTaillePixel.getArrayy().size());


		}

		ConnexionTaillePixel.getArrayy().remove(shapes);
		ConnexionTaillePixel.getGeomms().remove(srids);

		System.out.println("tes "+shapes.size());
		System.out.println("tes1 "+nomCouches.size());

		for(int ff=0;ff<nomCouches.size();ff++){
			System.out.println("nomCouches "+nomCouches.get(ff));
		}

		for(int ff=0;ff<nomCouches2.size();ff++){
			System.out.println("nomCouches2 "+nomCouches2.get(ff));
			System.out.println("taillesCouches "+taillesCouches.get(ff));
			System.out.println("srids1 "+srids1.get(ff));

		}



		DensitePopulation.taillesPixel2.addAll(taillesPixel);



		//		for(int fg=0;fg<DensitePopulation.taillesPixel2.size();fg++){
		//			//for(int fg=(this.taillesPixel.size()-1);fg>=0;fg--){
		//			String test34[][]=DensitePopulation.taillesPixel2.get(fg);
		//			System.out.println("Print "+fg);
		//			String surface=test34[0][0];
		//			System.out.println("surface "+surface);
		//			//nomTaillePixel.add(surface);
		//			String taille = test34[0][1];
		//			System.out.println("nom "+taille);
		//			//lestaillesdespixel.add(taille);
		//		}
		nomCouches = new ArrayList<String>();
		shapes = new ArrayList<String>();
		srids = new ArrayList<String>();
		tableaux.removeAll(tableaux);

	}

	public static ArrayList<String> getTaillesCouches() {
		return taillesCouches;
	}

	public static void setTaillesCouches(ArrayList<String> taillesCouches) {
		DensitePopulation.taillesCouches = taillesCouches;
	}

	public static ArrayList<String> getNomCouches2() {
		return nomCouches2;
	}

	public static void setNomCouches2(ArrayList<String> nomCouches2) {
		DensitePopulation.nomCouches2 = nomCouches2;
	}

	public double getNombreHabitants() {
		return nombreHabitants;
	}

	public void setNombreHabitants(double nombreHabitants) {
		this.nombreHabitants = nombreHabitants;
	}

	public double getDensiteHabitants() {
		return densiteHabitants;
	}

	public void setDensiteHabitants(double densiteHabitants) {
		this.densiteHabitants = densiteHabitants;
	}

	public double getSurfaceTotales() {
		return surfaceTotales;
	}

	public void setSurfaceTotale(double surfaceTotale) {
		this.surfaceTotales = surfaceTotale;
	}



	public static double getTaillePixel() {
		return taillePixel;
	}

	public static void setTaillePixel(double taillePixel) {
		DensitePopulation.taillePixel = taillePixel;
	}

	public static ArrayList<String> getNomCouches() {
		return nomCouches;
	}

	public static void setNomCouches(ArrayList<String> nomCouches) {
		DensitePopulation.nomCouches = nomCouches;
	}

	public static ArrayList<String[][]> getTaillesPixel() {
		return taillesPixel;
	}

	public static void setTaillesPixel(ArrayList<String[][]> taillesPixel) {
		DensitePopulation.taillesPixel = taillesPixel;
	}

	public static ArrayList<String> getShapes() {
		return shapes;
	}

	public static void setShapes(ArrayList<String> shapes) {
		DensitePopulation.shapes = shapes;
	}

	public static ArrayList<String> getShapes1() {
		return shapes1;
	}

	public static void setShapes1(ArrayList<String> shapes1) {
		DensitePopulation.shapes1 = shapes1;
	}

	public static ArrayList<String> getSrids1() {
		return srids1;
	}

	public static void setSrids1(ArrayList<String> srids1) {
		DensitePopulation.srids1 = srids1;
	}






}
