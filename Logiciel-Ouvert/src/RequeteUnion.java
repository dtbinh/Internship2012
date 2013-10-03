import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class RequeteUnion extends JFrame implements ActionListener{
	/**
	 * 
	 */
	FenetreRaster fen;
	FenetreUnion fenun;
	private static final long serialVersionUID = 1L;
	Statement stmt = null;
	//RequeteCominerRasterFenetrePixel habit = new RequeteCominerRasterFenetrePixel();
	//	InsertSQL insert;
	private String nomBase;
	private String port;
	private String nomUtilisateur;
	private String motDePasse;
	private String hote;

	private double surfaceTotale;
	private ArrayList <String> shapes = new ArrayList <String>();
	private static ArrayList <String> srids = new ArrayList <String>();
	public boolean b = false;



	private ArrayList <String[][]> tableaux = new ArrayList<String[][]>();
	private ArrayList <String> tableaunion = new ArrayList<String>();

	private ArrayList <String> taillePixel = new ArrayList<String>();
	private ArrayList <String> nomCouches = new ArrayList<String>();
	private static ArrayList <String> comparaison = new ArrayList<String>();

	public static ArrayList<String> getComparaison() {
		return comparaison;
	}


	public static void setComparaison(ArrayList<String> comparaison) {
		RequeteUnion.comparaison = comparaison;
	}


	ConnexionBaseDonnees connex;
	public RequeteUnion(FenetreRaster fen) throws SQLException {

		this.fen=fen;
		tableaux=FenetreRaster.getTableauTailles();
		srids.clear();




		comparaison=ConnexionRaster.getArrayy4();

		System.out.println("comapraison: "+comparaison.size());


		for(int fg=0;fg<tableaux.size();fg++){
			String test34[][]=tableaux.get(fg);
			String taillePixels=test34[fg][1];
			taillePixel.add(taillePixels);
			String nomCouche=test34[fg][0].toString();
			nomCouches.add(nomCouche);
		}

		for(int fg=0;fg<comparaison.size();fg++){
			System.out.println("comapraison: "+comparaison.get(fg));
		}


		for(int siz=0;siz<ConnexionRaster.getGeomms4().size();siz++){
			System.out.println("siz22 "+ConnexionRaster.getGeomms4().get(siz));
		}

		for(int i=0;i<nomCouches.size();i++){


			String trouver="";
			b=false;
			String trouve = nomCouches.get(i);
			System.out.println("nomcouches: "+trouve);

			if(comparaison.contains(trouve)){
				b=true;
				if(b=true){
					trouver=nomCouches.get(i);
					System.out.println("trouver: "+trouver);

					for(int j=0;j<comparaison.size();j++){
						if(comparaison.get(j).compareTo(trouver)==0){
							System.out.println(comparaison.get(j));
							srids.add(ConnexionRaster.getGeomms4().get(j));
						}
					}


					//srids.add(ConnexionRaster.getGeomms().get(comparaison.indexOf(trouver)));
				}
			}

		}

		System.out.println("size: "+srids.size());

		for(String siz : srids){
			System.out.println("siz "+siz);
		}

		System.out.println("size2: "+nomCouches.size());


		for(int i=0;i<srids.size();i++){

			System.out.println("test: "+ srids.get(i) +" "+ nomCouches.get(i));
		}
		//shapes=ConnexionUnion.getArrayy();


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


			for(int j=0;j<taillePixel.size();j++){
				if (con!=null){
					try{
						Statement st = con.createStatement();

						//st.execute("ALTER TABLE batiments DROP COLUMN surface_total ");

						System.out.println(nomCouches.get(j));
						st.execute("CREATE TABLE "+nomCouches.get(j)+"2 as SELECT ST_BUFFER("+srids.get(j)+",0.0) as "+srids.get(j)+" from "+nomCouches.get(j)+"");
						st.execute("CREATE TABLE "+nomCouches.get(j)+"_unionraster as SELECT ST_UNION("+srids.get(j)+") as "+srids.get(j)+" from "+nomCouches.get(j)+"2");
						st.execute("ALTER TABLE "+nomCouches.get(j)+"_unionraster ADD COLUMN gid serial primary key");
						st.execute("DROP TABLE "+nomCouches.get(j)+"2");



						//					stmt = con.createStatement();
						//
						//					String query = "select SUM(surfpalu) from "+shapes.get(j)+" as sum";
						//					ResultSet rs = stmt.executeQuery(query);
						//					while (rs.next()) {
						//
						//						//  String coffeeName = rs.getString("abc");
						//						//  int test = rs.getInt("sum");
						//						this.setSurfaceTotale(rs.getDouble("sum"));
						//						//float test = rs.getFloat("sum");
						//						//float price = rs.getFloat("PRICE");
						//						// int sales = rs.getInt("SALES");
						//						// int total = rs.getInt("TOTAL");
						//						System.out.println("surface totalefeneraster pour:"+ shapes.get(j)+" "+this.getSurfaceTotale());
						//					}
						//					
						//					st.execute("ALTER TABLE "+shapes.get(j)+" DROP COLUMN surfpalu");


						//System.out.println("Base de donn�es "+database+" cr�e");
					}
					catch (SQLException s){
						s.printStackTrace();// JDBCTutorialUtilities.printSQLException(s);
					}
				}
			}
		}
		catch (Exception b){
			b.printStackTrace();
			JOptionPane.showMessageDialog(this,"Erreur : "+b,"Titre : exception",JOptionPane.ERROR_MESSAGE);
		}



		//	System.out.println("Surface: "+this.getSurfaceTotale());

		new RequeteRaster();
	}


	public RequeteUnion(FenetreUnion fenun) throws SQLException {

		this.fenun=fenun;
		tableaux.clear();
		srids.clear();
		taillePixel.clear();
		nomCouches.clear();




		nomCouches=FenetreUnion.getNomShape1();
		srids= FenetreUnion.getSrids();

		if(nomCouches.size()==2){

			System.out.println("size: "+srids.size());

			for(String siz : srids){
				System.out.println("siz "+siz);
			}

			System.out.println("size2: "+nomCouches.size());

			for(int i=0;i<nomCouches.size();i++){
				System.out.println("nomCouches: "+nomCouches.get(i));
			}

			for(int i=0;i<srids.size();i++){

				System.out.println("test: "+ srids.get(i) +" "+ nomCouches.get(i));
			}
			//shapes=ConnexionUnion.getArrayy();


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


				//for(int j=0;j<nomCouches.size();j++){
				if (con!=null){
					try{
						Statement st = con.createStatement();

						//st.execute("ALTER TABLE batiments DROP COLUMN surface_total ");
						st.execute("drop table if exists uuuuuuunion_"+nomCouches.get(0));
						st.execute("drop table if exists uuuuuuunion_"+nomCouches.get(1));
						System.out.println("test");

						st.execute("create table uuuuuuunion_"+nomCouches.get(0)+" as select ST_Buffer("+srids.get(0)+", 0) as "+srids.get(0)+" from "+nomCouches.get(0));
						System.out.println("create table uuuuuuunion_"+nomCouches.get(0)+" as select ST_Buffer("+srids.get(0)+", 0) as "+srids.get(0)+" from "+nomCouches.get(0));

						st.execute("create table uuuuuuunion_"+nomCouches.get(1)+" as select ST_Buffer("+srids.get(1)+", 0) as "+srids.get(1)+" from "+nomCouches.get(1));
						System.out.println("create table uuuuuuunion_"+nomCouches.get(1)+" as select ST_Buffer("+srids.get(1)+", 0) as geom_"+nomCouches.get(1)+" from "+nomCouches.get(1));


						System.out.println("create table union_"+nomCouches.get(0)+"_"+nomCouches.get(1)+" as select st_union(a."+srids.get(0)+", b."+srids.get(1)+") from uuuuuuunion_"+nomCouches.get(0)+" a, uuuuuuunion_"+nomCouches.get(1)+" b");

						st.execute("create table union_"+nomCouches.get(0)+"_"+nomCouches.get(1)+" as select st_union(a."+srids.get(0)+", b."+srids.get(1)+") from uuuuuuunion_"+nomCouches.get(0)+" a, uuuuuuunion_"+nomCouches.get(1)+" b");


						st.execute("drop table if exists uuuuuuunion_"+nomCouches.get(0));
						st.execute("drop table if exists uuuuuuunion_"+nomCouches.get(1));


						//					stmt = con.createStatement();
						//
						//					String query = "select SUM(surfpalu) from "+shapes.get(j)+" as sum";
						//					ResultSet rs = stmt.executeQuery(query);
						//					while (rs.next()) {
						//
						//						//  String coffeeName = rs.getString("abc");
						//						//  int test = rs.getInt("sum");
						//						this.setSurfaceTotale(rs.getDouble("sum"));
						//						//float test = rs.getFloat("sum");
						//						//float price = rs.getFloat("PRICE");
						//						// int sales = rs.getInt("SALES");
						//						// int total = rs.getInt("TOTAL");
						//						System.out.println("surface totalefeneraster pour:"+ shapes.get(j)+" "+this.getSurfaceTotale());
						//					}
						//					
						//					st.execute("ALTER TABLE "+shapes.get(j)+" DROP COLUMN surfpalu");


						//System.out.println("Base de donn�es "+database+" cr�e");
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


			//	System.out.println("Surface: "+this.getSurfaceTotale());

			//new RequeteRasterVector2Points(fen);
		}

		else{
			if(nomCouches.size()==1){
				System.out.println("size: "+srids.size());

				for(String siz : srids){
					System.out.println("siz "+siz);
				}

				System.out.println("size2: "+nomCouches.size());

				for(int i=0;i<nomCouches.size();i++){
					System.out.println("nomCouches: "+nomCouches.get(i));
				}

				for(int i=0;i<srids.size();i++){

					System.out.println("test: "+ srids.get(i) +" "+ nomCouches.get(i));
				}
				//shapes=ConnexionUnion.getArrayy();


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


					//for(int j=0;j<nomCouches.size();j++){
					if (con!=null){
						try{
							Statement st = con.createStatement();

							//st.execute("ALTER TABLE batiments DROP COLUMN surface_total ");
							st.execute("drop table if exists uuuuuuunion_"+nomCouches.get(0));
							System.out.println("test size 1");

							st.execute("create table uuuuuuunion_"+nomCouches.get(0)+" as select ST_Buffer("+srids.get(0)+", 0) as "+srids.get(0)+" from "+nomCouches.get(0));
							System.out.println("create table uuuuuuunion_"+nomCouches.get(0)+" as select ST_Buffer("+srids.get(0)+", 0) as "+srids.get(0)+" from "+nomCouches.get(0));

							System.out.println("create table union_"+nomCouches.get(0)+" as select st_union(a."+srids.get(0)+") from uuuuuuunion_"+nomCouches.get(0)+" a");

							st.execute("create table union_"+nomCouches.get(0)+" as select st_union(a."+srids.get(0)+") from uuuuuuunion_"+nomCouches.get(0)+" a");


							st.execute("drop table if exists uuuuuuunion_"+nomCouches.get(0));


							//					stmt = con.createStatement();
							//
							//					String query = "select SUM(surfpalu) from "+shapes.get(j)+" as sum";
							//					ResultSet rs = stmt.executeQuery(query);
							//					while (rs.next()) {
							//
							//						//  String coffeeName = rs.getString("abc");
							//						//  int test = rs.getInt("sum");
							//						this.setSurfaceTotale(rs.getDouble("sum"));
							//						//float test = rs.getFloat("sum");
							//						//float price = rs.getFloat("PRICE");
							//						// int sales = rs.getInt("SALES");
							//						// int total = rs.getInt("TOTAL");
							//						System.out.println("surface totalefeneraster pour:"+ shapes.get(j)+" "+this.getSurfaceTotale());
							//					}
							//					
							//					st.execute("ALTER TABLE "+shapes.get(j)+" DROP COLUMN surfpalu");


							//System.out.println("Base de donn�es "+database+" cr�e");
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

			}
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

	public ArrayList<String> getShapes() {
		return shapes;
	}


	public void setShapes(ArrayList<String> shapes) {
		this.shapes = shapes;
	}


	public static ArrayList<String> getSrids() {
		return srids;
	}


	public static void setSrids(ArrayList<String> srids) {
		RequeteUnion.srids = srids;
	}


	public ArrayList <String> getTableaunion() {
		return tableaunion;
	}


	public void setTableaunion(ArrayList <String> tableaunion) {
		this.tableaunion = tableaunion;
	}





}



