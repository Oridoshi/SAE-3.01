package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import metier.DB;

public class Initialisation
{
	public static void initialisationBD()
	{
		try
		{
			Connection db = DB.getInstance();

			PreparedStatement ps = db.prepareStatement
				(
					"DROP TABLE if exists Module cascade;"+
					"DROP TABLE if exists RemplirProgramme cascade;"+
					"DROP TABLE if exists Semestre cascade;"+
					"DROP TABLE if exists Intervenant cascade;"+
					"DROP TABLE if exists CategorieIntervenant cascade;"+
					"DROP TABLE if exists CategorieHeure cascade;"+
					"DROP TABLE if exists CategorieModule cascade;"+
					"DROP TABLE if exists Affectation cascade;"+
					"DROP TABLE if exists RemplirCategorieModule cascade;"+

					"CREATE TABLE if not exists Duplication("+
					"libelle VARCHAR PRIMARY KEY,"+
					"content TEXT NOT NULL"+
					");"+

					"CREATE TABLE Semestre ("+
					"	id SERIAL PRIMARY KEY,"+
					"	nbGrpTd INT NOT NULL,"+
					"	nbGrpTp INT NOT NULL,"+
					"	nbEtd INT NOT NULL,"+
					"	nbSemaines INT NOT NULL"+
					");"+

					"CREATE TABLE CategorieIntervenant ("+
					"	id SERIAL PRIMARY KEY,"+
					"	code VARCHAR(255) NOT NULL,"+
					"	nom VARCHAR(255) NOT NULL,"+
					"	minH INT NOT NULL,"+
					"	maxH INT NOT NULL,"+
					"	coefTp DECIMAL(5, 2) NOT NULL default 1"+
					");"+

					"CREATE TABLE CategorieHeure ("+
					"	id SERIAL PRIMARY KEY,"+
					"	nom VARCHAR(50),"+
					"	coeffCat DECIMAL(5, 2) NOT NULL default 1"+
					");"+

					"CREATE TABLE CategorieModule("+
					"	id SERIAL PRIMARY KEY,"+
					"	nom VARCHAR(50)"+
					");"+

					"CREATE TABLE Module ("+
					"	id SERIAL PRIMARY KEY,"+
					"	code VARCHAR(30),"+
					"	forceValider boolean NOT NULL,"+
					"	idSemestre INT NOT NULL,"+
					"	idCatModule INT NOT NULL,"+
					"	libLong VARCHAR(255) NOT NULL,"+
					"	libCourt VARCHAR(50) NOT NULL,"+
					"	FOREIGN KEY (idSemestre) REFERENCES Semestre(id),"+
					"	FOREIGN KEY (idCatModule) REFERENCES CategorieModule(id)"+
					");"+

					"CREATE TABLE RemplirProgramme("+
					"	id SERIAL PRIMARY KEY,"+
					"	idCatModule INT NOT NULL,"+
					"	idCatH INT NOT NULL,"+
					"	idModule INT NOT NULL,"+
					"	nbHProgramme INT NOT NULL,"+
					"	nbHPromo INT default 0 NOT NULL,"+
					"	nbSemaine INT default 0 NOT NULL,"+
					"	FOREIGN KEY (idCatModule) REFERENCES CategorieModule(id),"+
					"	FOREIGN KEY (idCatH) REFERENCES CategorieHeure(id),"+
					"	FOREIGN KEY (idModule) REFERENCES Module(id)"+
					");"+

					"CREATE TABLE RemplirCategorieModule ("+
					"	id SERIAL PRIMARY KEY,"+
					"	idCatModule INT NOT NULL,"+
					"	idCatH INT NOT NULL,"+
					"	FOREIGN KEY (idCatModule) REFERENCES CategorieModule(id),"+
					"	FOREIGN KEY (idCatH) REFERENCES CategorieHeure(id)"+
					");"+

					"CREATE TABLE Intervenant ("+
					"	id SERIAL PRIMARY KEY,"+
					"	idCatIntervenant INT NOT NULL,"+
					"	nom VARCHAR(255) NOT NULL,"+
					"	prenom VARCHAR(255) NOT NULL,"+
					"	hMax INT default 0 NOT NULL,"+
					"	hMin INT default 0 NOT NULL,"+
					"	coefTp DECIMAL(5, 2) NOT NULL default 1,"+
					"	FOREIGN KEY (idCatIntervenant) REFERENCES CategorieIntervenant(id)"+
					");"+

					"CREATE TABLE Affectation ("+
					"	id SERIAL PRIMARY KEY,"+
					"	idIntervenant INT NOT NULL,"+
					"	idCatHeure INT NOT NULL,"+
					"	nbH INT default 0 NOT NULL,"+
					"	nbGrp INT default 0 NOT NULL,"+
					"	idModule INT NOT NULL,"+
					"	commentaire TEXT,"+
					"	nbSemaine INT default 0 NOT NULL,"+
					"	FOREIGN KEY (idIntervenant) REFERENCES Intervenant(id),"+
					"	FOREIGN KEY (idCatHeure) REFERENCES CategorieHeure(id),"+
					"	FOREIGN KEY (idModule) REFERENCES Module(id)"+
					");"
				);

			try{
				ps.executeUpdate();
				System.out.println("DEBUG : CRÉATION DES TABLES RÉUSSIE");
			} catch ( SQLException e ){
				System.out.println("DEBUG : " + e);
			}

			ps = db.prepareStatement
				(
					"INSERT INTO Semestre VALUES"+
					"(1, 4, 7, 85, 15),"+
					"(2, 4, 7, 85, 15),"+
					"(3, 2, 4, 52, 13),"+
					"(4, 2, 4, 52, 13),"+
					"(5, 2, 4, 52, 13),"+
					"(6, 2, 4, 52, 13);"
				);

			try{
				ps.executeUpdate();
				System.out.println("DEBUG : INSERTION DES DONNÉES RÉUSSIE IL Y A EU " + String.format("%2d",ps.getUpdateCount()) + " INSERTIONS");
			} catch ( SQLException e ){
				System.out.println("DEBUG : " + e);
			}

			ps = db.prepareStatement
				(
					"INSERT INTO CategorieHeure (nom, coeffCat) VALUES"+
					"('CM'  , 1.5),"+
					"('TD'  , 1  ),"+
					"('TP'  , 1  ),"+
					"('HT'  , 1  ),"+
					"('REH' , 1  ),"+
					"('HSAE', 1  ),"+
					"('HP'  , 1  );"
				);

			try{
				ps.executeUpdate();
				System.out.println("DEBUG : INSERTION DES DONNÉES RÉUSSIE IL Y A EU " + String.format("%2d",ps.getUpdateCount()) + " INSERTIONS");
			} catch ( SQLException e ){
				System.out.println("DEBUG : " + e);
			}

			ps = db.prepareStatement
				(
					"INSERT INTO CategorieModule (nom) VALUES"+
					"('Ressource'  ),"+
					"('PPP'        ),"+
					"('SAE'        ),"+
					"('Stage/Suivi');"
				);

			try{
				ps.executeUpdate();
				System.out.println("DEBUG : INSERTION DES DONNÉES RÉUSSIE IL Y A EU " + String.format("%2d",ps.getUpdateCount()) + " INSERTIONS");
			} catch ( SQLException e ){
				System.out.println("DEBUG : " + e);
			}

			ps = db.prepareStatement
				(
					"INSERT INTO RemplirCategorieModule (idCatModule, idCatH) VALUES"+
					"( 1, 1 ),"+
					"( 1, 2 ),"+
					"( 1, 3 ),"+
					"( 1, 7 ),"+
					"( 2, 4 ),"+
					"( 2, 6 ),"+
					"( 4, 4 ),"+
					"( 3, 4 ),"+
					"( 3, 1 ),"+
					"( 3, 2 ),"+
					"( 3, 3 ),"+
					"( 3, 7 ),"+
					"( 4, 5 );"
				);

			try{
				ps.executeUpdate();
				System.out.println("DEBUG : INSERTION DES DONNÉES RÉUSSIE IL Y A EU " + String.format("%2d",ps.getUpdateCount()) + " INSERTIONS");
			} catch ( SQLException e ){
				System.out.println("DEBUG : " + e);
			}
		}
		catch (Exception e)
		{
			System.out.println("DEBUG : " + e);
		}
	}
}