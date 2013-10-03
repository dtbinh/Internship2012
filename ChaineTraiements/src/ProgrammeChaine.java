import java.io.IOException;



public class ProgrammeChaine {

	public static void main(String[] arguments) throws IOException{


		new OuvrirFenetre();


		new ChoisirRepertoireSQL();

		Fenetre3 fen3 = new Fenetre3();
		fen3.setVisible(true);

		//ConnexionBaseDonnees basededonnes = new ConnexionBaseDonnees();
		//basededonnes.setVisible(true);

	}
}

