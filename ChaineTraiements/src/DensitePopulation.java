import java.io.IOException;
import java.sql.SQLException;


public class DensitePopulation {

	NombreHabitants habitants;
	RequeteSurface requete;
	ConnexionBaseDonnees connex;
	private double nombreHabitants;
	private double densiteHabitants;
	private double surfaceTotales;
	private static double taillePixel;

	public DensitePopulation(NombreHabitants habitants, RequeteSurface requete) throws SQLException, IOException{

		this.habitants=habitants;
		this.requete=requete;

		surfaceTotales=requete.getSurfaceTotale();

		this.setNombreHabitants(NombreHabitants.getNombreHabitants());

		System.out.println("Nombre Habitants: "+this.getNombreHabitants());
		//System.out.println("surface"+surfaceTotales);

		//this.surfaceTotales=requete.getSurfaceTotale();

		this.setDensiteHabitants(surfaceTotales/this.getNombreHabitants());

		if (this.getDensiteHabitants()>0){
			this.setTaillePixel(Math.sqrt(this.getDensiteHabitants()));
		}
		System.out.println("Taille pixel: "+DensitePopulation.getTaillePixel());

		try {
			new Batiraster(connex,this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	public void setTaillePixel(double taillePixel) {
		DensitePopulation.taillePixel = taillePixel;
	}






}
