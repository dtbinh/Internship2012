/*import java.io.IOException;




public class Class2 implements Runnable{
	*//**
	 * 
	 *//*
	ConnexionBaseDonnees conne;

	InfoShp2pgsql i;



	public Class2(ConnexionBaseDonnees conne, InfoShp2pgsql i){
		this.i=i;
		this.conne=conne;
	}	

	public void run(){

		boolean a=true;

		while (a) {
			if (i.isVisible()) {

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					break;
				}
			}
			else {
				if(!i.isVisible()) {

					a=false;
				}
			}
		}

		i.ajouteNomTable(this.i.getNomTable());
		i.ajoutenomGeom(this.i.getNomgeom());
		i.ajouteProjection(this.i.getProjection());

		try {
			Shp2pgsql shp2pgsqll = new Shp2pgsql(i);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}




*/