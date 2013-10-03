package chaine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class EauxBuffers extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Statement stmt = null;
	DensitePopulation densite;

	private String nomBase;
	private String port;
	private String nomUtilisateur;
	private String motDePasse;
	private String hote;
	private double surfaceTotale;

	ConnexionBaseDonnees connex;
	public EauxBuffers() throws SQLException {

		this.setNomBase(ConnexionBaseDonnees.getNomBase());
		this.setHote(ConnexionBaseDonnees.getHote());
		this.setPort(ConnexionBaseDonnees.getPort());
		this.setNomUtilisateur(ConnexionBaseDonnees.getUser());
		this.setMotDePasse(ConnexionBaseDonnees.getPswd());

		//System.out.println("Test");
		Connection con = null;
		try{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://"+this.getHote()+":"+this.getPort()+"/"+this.getNomBase(),this.getNomUtilisateur(),this.getMotDePasse());

			if (con!=null){
				try{
					Statement st = con.createStatement();

					//st.execute("ALTER TABLE batiments DROP COLUMN surface_total ");
					st.execute("create table buffer_eaux400 as select gid, st_buffer(geom_eaux,400) from eaux;");
					st.execute("create table buffer_eaux600 as select gid, st_buffer(geom_eaux,600) from eaux;");
					
					st.execute("create table extend as SELECT st_envelope(rast) from densite_batiment");
					
					st.execute("create table union_buffers as select distinct a.gid, ST_Union(a.st_buffer, b.st_buffer) as geom_union from  buffer_eaux400 a, buffer_eaux600 b");
					
					st.execute("create table intersect_buffers as select distinct a.gid, ST_INTERSECTION(a.geom_union, b.st_envelope) from  union_buffers a, extend b"); 
					st.execute("create table union_intersect as select st_union(st_intersection) from intersect_buffers"); 
					st.execute("create table raster_alea as select st_AsRaster(st_union,4.2,-4.2) as rast from union_intersect");
					
					st.execute("create table union400 as select st_union(st_buffer) from buffer_eaux400");
					
					st.execute("create table union600 as select st_union(st_buffer) from buffer_eaux600");
					st.execute("ALTER TABLE union600 ADD COLUMN gid serial primary key");
					
					st.execute("create table union200 as select ST_SymDifference(a.st_union,b.st_union) from union600 a, union400 b");
					st.execute("alter table union200 add column gid serial primary key");
					
					st.execute("create table raster2001 as select st_intersection(st_symdifference,st_envelope) from union200,extend");
					st.execute("create table raster2002 as select st_asraster(st_intersection,4.2,-4.2) as rast from raster2001");
					st.execute("ALTER TABLE raster2002 add column rid serial primary key");
					
					st.execute("create table raster4001 as select st_intersection(st_union,st_envelope) from union400,extend");
					st.execute("create table raster4002 as select st_asraster(st_intersection,4.2,-4.2) as rast from raster4001");
					st.execute("ALTER TABLE raster4002 add column rid serial primary key");

					
					
				}
				catch (SQLException s){
					s.printStackTrace();// JDBCTutorialUtilities.printSQLException(s);
				}
			}
		}
		catch (Exception b){
			b.printStackTrace();
			JOptionPane.showMessageDialog(this,"Erreur : "+b,"Titre : exception",JOptionPane.ERROR_MESSAGE);
		}

		//	RequeteCominerRasterFenetrePixel habitat = new RequeteCominerRasterFenetrePixel(this);

		//habitat.setVisible(true);
		//System.out.println("Surface: "+this.getSurfaceTotale());
		
		try {
			new RDensiteetalea();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

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

	public String getHote() {
		return hote;
	}

	public void setHote(String hote) {
		this.hote = hote;
	}




}



