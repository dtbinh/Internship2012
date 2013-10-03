import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rcaller.Globals;
import rcaller.RCaller;
import rcaller.RCode;

public class VisualiserVecteur{


	private String nomBase;
	private String port;
	private String nomUtilisateur;
	private String motDePasse;
	private String hote;
	private double surfaceTotale;
	private double taillePixel; 
	private ImageIcon im;

	FenetreVisualiserVecteur fenvis;

	private ArrayList<String> rasters = new ArrayList<String>();



	public VisualiserVecteur(FenetreVisualiserVecteur fenvis) throws IOException{

		try{

			this.fenvis=fenvis;
			rasters=FenetreVisualiserVecteur.getNomShape1();

			//this.pathSQL=ChoisirRepertoireSQL.getPath();
			this.setNomBase(ConnexionBaseDonnees.getNomBase());
			this.setPort(ConnexionBaseDonnees.getPort());
			this.setNomUtilisateur(ConnexionBaseDonnees.getUser());
			this.setMotDePasse(ConnexionBaseDonnees.getPswd());
			this.setHote(ConnexionBaseDonnees.getHote());
			this.setTaillePixel(DensitePopulation.getTaillePixel());
			System.out.println(this.getTaillePixel());

			for(int i=0;i<rasters.size();i++){

				im=new ImageIcon();

				System.out.println("rasters: "+rasters.get(i));

				RCaller caller = new RCaller();
				  Globals.detect_current_rscript();
			      caller.setRscriptExecutable(Globals.Rscript_current);

				RCode code = new RCode();

				code.addRCode("library(RPostgreSQL)");
				code.addRCode("library(rgdal)");
				code.addRCode("library(raster)");
				code.addRCode("library(maptools)");
				code.addRCode("library(sp)");
				code.addRCode("library(spatstat)");
				System.out.println(this.getNomBase()+" "+this.getNomUtilisateur());
				System.out.println("layers <- readOGR(dsn=\"PG:host="+this.hote+" user="+this.nomUtilisateur+" dbname="+this.nomBase+" password="+this.motDePasse+"\",layer=\""+rasters.get(i)+"\")");
				code.addRCode("layers <- readOGR(dsn=\"PG:host="+this.hote+" user="+this.nomUtilisateur+" dbname="+this.nomBase+" password="+this.motDePasse+"\",layer=\""+rasters.get(i)+"\")");
				//File file = code.startPlot();
				

				//code.addRCode("plot(mylayer)");
				//System.out.println("Plot will be saved to : " + file);
				//code.endPlot();




				File file2 = null;
				try {
					file2 = code.startPlot();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				/*File file2 = null;

        File file3 = File.createTempFile("RPlot","png");
        code.addRCode("png(\""+file3.toString()+"\")");
        ImageIcon img = new ImageIcon(file3.toString());
        RPlotViewer plotter = new RPlotViewer(img);
        plotter.setVisible(true);
        plotter.setSize(img.getIconWidth() +20 , img.getIconHeight()+60);
        plotter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        plotter.setTitle("RCaller 2.0 - Generated Plot");
        Graphics g = null;

        if(img!=null) g.drawImage(img.getImage(), 10, 30, plotter);
				 */
				//code.addRCode("plot(points_arbres)");
				//System.out.println("Plot will be saved to : " + file2);
				//code.endPlot();


				//	code.addRCode("drv <- dbDriver(\"PostgreSQL\")");
				//code.addRCode("con <- dbConnect(drv, port=5432, dbname= \"testohmam\", user=\"Gilles\", password=\"959426G/e\")");
				//code.addRCode("rs <- dbSendQuery(con, \"select surf from batiments\")");
				//code.addRCode("data <- fetch(rs,n=-1)");
				//code.addRCode("plot(id,data$surf");


				code.addRCode("plot(layers)");



				//code.addRCode("set.seed(123)");
				//code.addRCode("x<-rnorm(10)");
				//code.addRCode("y<-rnorm(10)");
				//code.addRCode("ols<-lm(y~x)");

				caller.setRCode(code);
				//rcaller.setRCode(code);

				caller.runOnly();
				//code.showPlot(file2);

				code.endPlot();


				im = code.getPlot(file2);
				
				JFrame jfra = new JFrame();

				jfra.setTitle("Affichage vecteur "+rasters.get(i));
				JPanel panel1 = new JPanel();
				panel1.add(new JLabel(im));
				jfra.add(panel1);
				jfra.pack();
				System.out.println("test");

				//	file2.deleteOnExit();
				jfra.setVisible(true);

			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String getNomBase() {
		return nomBase;
	}

	public void setNomBase(String nomBase) {
		this.nomBase = nomBase;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public double getSurfaceTotale() {
		return surfaceTotale;
	}

	public void setSurfaceTotale(double surfaceTotale) {
		this.surfaceTotale = surfaceTotale;
	}

	public double getTaillePixel() {
		return taillePixel;
	}

	public void setTaillePixel(double taillePixel) {
		this.taillePixel = taillePixel;
	}

	public String getHote() {
		return hote;
	}

	public void setHote(String hote) {
		this.hote = hote;
	} 





}

