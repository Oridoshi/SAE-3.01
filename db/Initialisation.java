package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Initialisation
{
	public static String chemin = "bernouy.com/sae301";
	public static String identifiant = "admin";
	public static String motDePasse = "Matthias76930!";
	public static void main(String[] args)
	{
		try
		{
			Connection db = DriverManager.getConnection("jdbc:postgresql://" + chemin,identifiant,motDePasse);

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

					"CREATE TABLE Semestre ("+
					"	id SERIAL PRIMARY KEY,"+
					"	nbGrpTd INT NOT NULL,"+
					"	nbGrpTp INT NOT NULL,"+
					"	nbEtd INT NOT NULL,"+
					"	nbSemaines INT NOT NULL"+
					");"+

					"CREATE TABLE CategorieIntervenant ("+
					"	code VARCHAR(255) PRIMARY KEY,"+
					"	nom VARCHAR(255) NOT NULL,"+
					"	minH INT NOT NULL,"+
					"	maxH INT NOT NULL,"+
					"	coefTp DECIMAL(5, 2) NOT NULL default 1"+
					");"+

					"CREATE TABLE CategorieHeure ("+
					"	nom VARCHAR(50) PRIMARY KEY,"+
					"	coeffCat DECIMAL(5, 2) NOT NULL default 1"+
					");"+

					"CREATE TABLE CategorieModule("+
					"	nom VARCHAR(50) PRIMARY KEY"+
					");"+

					"CREATE TABLE Module ("+
					"	code VARCHAR(30) PRIMARY KEY,"+
					"	forceValider boolean NOT NULL,"+
					"	idSemestre INT NOT NULL,"+
					"	nomCatModule VARCHAR(50) NOT NULL,"+
					"	libLong VARCHAR(255) NOT NULL,"+
					"	libCourt VARCHAR(50) NOT NULL,"+
					"	FOREIGN KEY (idSemestre) REFERENCES Semestre(id),"+
					"	FOREIGN KEY (nomCatModule) REFERENCES CategorieModule(nom)"+
					");"+

					"CREATE TABLE RemplirProgramme("+
					"	nomCatModule VARCHAR(50) NOT NULL,"+
					"	nomCatH VARCHAR(50) NOT NULL,"+
					"	codeModule VARCHAR(50) NOT NULL,"+
					"	nbHProgramme INT NOT NULL,"+
					"	nbHPromo INT default 0 NOT NULL,"+
					"	nbSemaine INT default 0 NOT NULL,"+
					"	FOREIGN KEY (nomCatModule) REFERENCES CategorieModule(nom),"+
					"	FOREIGN KEY (nomCatH) REFERENCES CategorieHeure(nom),"+
					"	FOREIGN KEY (codeModule) REFERENCES Module(code),"+
					"	PRIMARY KEY(nomCatModule, nomCatH, codeModule)"+
					");"+

					"CREATE TABLE RemplirCategorieModule ("+
					"	nomCatModule VARCHAR(50) NOT NULL,"+
					"	nomCatH VARCHAR(50) NOT NULL,"+
					"	FOREIGN KEY (nomCatModule) REFERENCES CategorieModule(nom),"+
					"	FOREIGN KEY (nomCatH) REFERENCES CategorieHeure(nom),"+
					"	PRIMARY KEY(nomCatModule, nomCatH)"+
					");"+

					"CREATE TABLE Intervenant ("+
					"	id SERIAL PRIMARY KEY,"+
					"	codeCatIntervenant VARCHAR(50) NOT NULL,"+
					"	nom VARCHAR(255) NOT NULL,"+
					"	prenom VARCHAR(255) NOT NULL,"+
					"	hMax INT default 0 NOT NULL,"+
					"	hMin INT default 0 NOT NULL,"+
					"	coefTp DECIMAL(5, 2) NOT NULL default 1,"+
					"	FOREIGN KEY (codeCatIntervenant) REFERENCES CategorieIntervenant(code)"+
					");"+

					"CREATE TABLE Affectation ("+
					"	id SERIAL PRIMARY KEY,"+
					"	idIntervenant INT NOT NULL,"+
					"	nomCatHeure VARCHAR(50) NOT NULL,"+
					"	nbH INT default 0 NOT NULL,"+
					"	nbGrp INT default 0 NOT NULL,"+
					"	codeModule VARCHAR(50) NOT NULL,"+
					"	commentaire TEXT,"+
					"	nbSemaine INT default 0 NOT NULL,"+
					"	FOREIGN KEY (idIntervenant) REFERENCES Intervenant(id),"+
					"	FOREIGN KEY (nomCatHeure) REFERENCES CategorieHeure(nom),"+
					"	FOREIGN KEY (codeModule) REFERENCES Module(code)"+
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
					"INSERT INTO CategorieIntervenant VALUES"+
					"('info_ec', 'Vacataire', 192, 364, 1),"+
					"('vaca_pro', 'Vacataire', 120, 187, 0.67),"+
					"('vaca_sd', 'Vacataire', 90, 187, 0.67),"+
					"('vaca_ret', 'Vacataire', 80, 96, 0.67),"+
					"('info_etd', 'Etudiant', 1, 1000, 0.01),"+
					"('info_sd', 'info_sd', 384, 576, 1);"
				);

			try{
				ps.executeUpdate();
				System.out.println("DEBUG : INSERTION DES DONNÉES RÉUSSIE IL Y A EU " + String.format("%2d",ps.getUpdateCount()) + " INSERTIONS");
			} catch ( SQLException e ){
				System.out.println("DEBUG : " + e);
			}

			ps = db.prepareStatement
				(
					"INSERT INTO CategorieHeure VALUES"+
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
					"INSERT INTO CategorieModule VALUES"+
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
					"INSERT INTO Module VALUES"+
					"	( 'R1.01', false , 1, 'Ressource'  , 'Initiation au développement'                        , 'Init dev'            ),"+
					"	( 'R1.02', false , 1, 'Ressource'  , 'Developpement Interfaces Web'                       , 'Intro web'           ),"+
					"	( 'R1.03', false , 1, 'Ressource'  , 'Introduction Architecture'                          , 'Intro archi'         ),"+
					"	( 'R1.04', false , 1, 'Ressource'  , 'Introduction Système'                               , 'Intro sys'           ),"+
					"	( 'R1.05', false , 1, 'Ressource'  , 'Introduction aux bases de Données'                  , 'BD1'                 ),"+
					"	( 'R1.06', false , 1, 'Ressource'  , 'Math discrètes'                                     , 'Math discrètes'      ),"+
					"	( 'R1.07', false, 1, 'Ressource'  , 'Outils Mathématiques fondamentaux'                  , 'Outils fondamentaux' ),"+
					"	( 'R1.08', false, 1, 'Ressource'  , 'Gestion de projet & des organisations'              , 'GPO 1'               ),"+
					"	( 'R1.09', false, 1, 'Ressource'  , 'Intro Economie'                                     , 'Eco 1'               ),"+
					"	( 'R1.10', false, 1, 'Ressource'  , 'Anglais Technique'                                  , 'Anglais 1'           ),"+
					"	( 'R1.11', false, 1, 'Ressource'  , 'Bases de la communication'                          , 'Comm 1'              ),"+
					"	( 'R1.12', false, 1, 'Ressource'  , 'Projet Professionnel et personnel'                  , 'PPP 1'               ),"+

					"	( 'S1.01', false , 1, 'SAE'        , 'SAE 1'                                              , 'SAE 1'               ),"+
					"	( 'S1.02', false , 1, 'SAE'        , 'SAE 2'                                              , 'SAE 2'               ),"+
					"	( 'S1.03', false , 1, 'SAE'        , 'SAE 3'                                              , 'SAE 3'               ),"+

					"	( 'R3.01', false , 3, 'Ressource'  , 'Développement Web'                                  , 'Dév Web'             ),"+
					"	( 'R3.02', false , 3, 'Ressource'  , 'Développement Efficace'                             , 'Dév Efficace'        ),"+
					"	( 'R3.03', false , 3, 'Ressource'  , 'Analyse'                                            , 'Analyse'             ),"+
					"	( 'R3.04', false , 3, 'Ressource'  , 'Qualité de développement 3'                         , 'Qualité dev 3'       ),"+
					"	( 'R3.05', false , 3, 'Ressource'  , 'Programmation Système'                              , 'Prog sys'            ),"+
					"	( 'R3.06', false , 3, 'Ressource'  , 'Architecture des Réseaux'                           , 'Archi réseaux'       ),"+
					"	( 'R3.07', false , 3, 'Ressource'  , 'SQL dans un langage de programmation'               , 'BD3'                 ),"+
					"	( 'R3.08', false, 3, 'Ressource'  , 'Probabilités'                                       , 'Proba'               ),"+
					"	( 'R3.09', false, 3, 'Ressource'  , 'Cryptographie et Sécurité'                          , 'Crypto'              ),"+
					"	( 'R3.10', false, 3, 'Ressource'  , 'Management des Systèmes d information'              , 'MSI'                 ),"+
					"	( 'R3.11', false, 3, 'Ressource'  , 'Droits des contrats et du numérique'                , 'Droit 3'             ),"+
					"	( 'R3.12', false, 3, 'Ressource'  , 'Anglais'                                            , 'Anglais'             ),"+
					"	( 'R3.13', false, 3, 'Ressource'  , 'Communication professionnelle'                      , 'Comm 3'              ),"+
					"	( 'R3.14', false, 3, 'Ressource'  , 'Projet Professionnel et Personnel'                  , 'PPP 3'               ),"+

					"	( 'S3.01', false , 3, 'SAE'        , 'SAE 1'                                              , 'SAE 1'               ),"+

					"	( 'R5.01', false , 5, 'Ressource'  , 'Initiation au management d une équipe de projet'    , 'Init management'     ),"+
					"	( 'R5.02', false , 5, 'Ressource'  , 'Projet Professionnel et Personnel'                  , 'PPP'                 ),"+
					"	( 'R5.03', false , 5, 'Ressource'  , 'Politique de communication'                         , 'Comm 5'              ),"+
					"	( 'R5.04', false , 5, 'Ressource'  , 'Qualité algorithmique'                              , 'Qualité algo'        ),"+
					"	( 'R5.05', false , 5, 'Ressource'  , 'Programmation Avancée'                              , 'Prog avancée'        ),"+
					"	( 'R5.06', false , 5, 'Ressource'  , 'Sensibilisation à la programmation Multimédia'      , 'Prog multimédia'     ),"+
					"	( 'R5.07', false , 5, 'Ressource'  , 'Automatisation de la chaine de production'          , 'Automatisation'      ),"+
					"	( 'R5.08', false, 5, 'Ressource'  , 'Qualité de développement'                           , 'Qualité dév 5'       ),"+
					"	( 'R5.09', false, 5, 'Ressource'  , 'Virtualisation avancée'                             , 'Virtualisation'      ),"+
					"	( 'R5.10', false, 5, 'Ressource'  , 'Nouveaux paradigmes de base de données'             , 'BD5'                 ),"+
					"	( 'R5.11', false, 5, 'Ressource'  , 'Méthodes d optimisation pour l aide à la décision'  , 'Opti'                ),"+
					"	( 'R5.12', false, 5, 'Ressource'  , 'Modélisations mathématiques'                        , 'IA avec Python'      ),"+
					"	( 'R5.13', false, 5, 'Ressource'  , 'Economie durable et numérique'                      , 'Eco'                 ),"+
					"	( 'R5.14', false, 5, 'Ressource'  , 'Anglais'                                            , 'Anglais'             ),"+

					"	( 'S5.01', false , 5, 'SAE'        , 'SAE 1'                                              , 'SAE 1'               ),"+

					"	( 'R6.01', false , 6, 'Ressource'  , 'Entrepreunariat'                                    , 'Entrepreunariat'     ),"+
					"	( 'R6.02', false , 6, 'Ressource'  , 'Droit du numérique et de la propriété intelectuelle', 'DROIT 6'             ),"+
					"	( 'R6.03', false , 6, 'Ressource'  , 'Organisation et Diffusion de l information'         , 'Comm 6'              ),"+
					"	( 'R6.04', false, 6, 'Ressource'  , 'Projet Profesionnel et Personnel'                   , 'PPP'                 ),"+
					"	( 'R6.05', false, 6, 'Ressource'  , 'Développement avancé'                               , 'Dev avancé'          ),"+
					"	( 'R6.06', false, 6, 'Ressource'  , 'Maintenance applicative'                            , 'Maintenance'         ),"+

					"	( 'S6.01', false , 6, 'Stage/Suivi', 'Stage'                                              , 'Stage'               ),"+

					"	( 'S6.02', false , 6, 'SAE'        , 'SAE 1'                                              , 'SAE 1'               );"
				);

			try{
				ps.executeUpdate();
				System.out.println("DEBUG : INSERTION DES DONNÉES RÉUSSIE IL Y A EU " + String.format("%2d",ps.getUpdateCount()) + " INSERTIONS");
			} catch ( SQLException e ){
				System.out.println("DEBUG : " + e);
			}

			ps = db.prepareStatement
				(
					"INSERT INTO RemplirCategorieModule VALUES"+
					"( 'Ressource', 'CM' ),"+
					"( 'Ressource', 'TD' ),"+
					"( 'Ressource', 'TP' ),"+
					"( 'Ressource', 'HP' ),"+
					"( 'PPP', 'CM' ),"+
					"( 'PPP', 'TD' ),"+
					"( 'PPP', 'TP' ),"+
					"( 'PPP', 'HT' ),"+
					"( 'PPP', 'HP' ),"+
					"( 'SAE', 'HT' ),"+
					"( 'SAE', 'HSAE'),"+
					"( 'Stage/Suivi', 'HT'  ),"+
					"( 'Stage/Suivi', 'REH' );"
				);

			try{
				ps.executeUpdate();
				System.out.println("DEBUG : INSERTION DES DONNÉES RÉUSSIE IL Y A EU " + String.format("%2d",ps.getUpdateCount()) + " INSERTIONS");
			} catch ( SQLException e ){
				System.out.println("DEBUG : " + e);
			}

			ps = db.prepareStatement
				(
					"INSERT INTO RemplirProgramme VALUES"+
					"( 'Ressource', 'CM', 'R1.01',  6,    6, 14 ),"+
					"( 'Ressource', 'TD', 'R1.01', 65,   56, 14 ),"+
					"( 'Ressource', 'TP', 'R1.01', 28,   28, 14 ),"+
					"( 'Ressource', 'HP', 'R1.01',  0,    9, 0  ),"+
					"( 'Ressource', 'CM', 'R3.13',  6,    6, 14 ),"+
					"( 'Ressource', 'TD', 'R3.13', 20, 19.5, 13 ),"+
					"( 'Ressource', 'TP', 'R3.13', 13,   13, 13 ),"+
					"( 'Ressource', 'HP', 'R3.13',  0,    1, 13 );"
				);

			try{
				ps.executeUpdate();
				System.out.println("DEBUG : INSERTION DES DONNÉES RÉUSSIE IL Y A EU " + String.format("%2d",ps.getUpdateCount()) + " INSERTIONS");
			} catch ( SQLException e ){
				System.out.println("DEBUG : " + e);
			}

			ps = db.prepareStatement
				(
					"INSERT INTO Intervenant ( codeCatIntervenant, nom, prenom) VALUES"+
					"	( 'info_ec' , 'Boukachour', 'Hadhoum'   ),"+
					"	( 'vaca_pro', 'Colignon'  , 'Thomas'    ),"+
					"	( 'vaca_pro', 'Dubocage'  , 'Tiphaine'  ),"+
					"	( 'vaca_sd' , 'Hervé'     , 'Nathalie'  ),"+
					"	( 'vaca_ret', 'Pecqueret' , 'Véronique' ),"+
					"	( 'info_sd' , 'Laffeach'  , 'Quentin'   ),"+
					"	( 'info_sd' , 'Lepivert'  , 'Philippe'  ),"+
					"	( 'info_sd' , 'Legrix'    , 'Bruno'     ),"+
					"	( 'info_sd' , 'Nivet'     , 'Laurence'  );"
				);

			try{
				ps.executeUpdate();
				System.out.println("DEBUG : INSERTION DES DONNÉES RÉUSSIE IL Y A EU " + String.format("%2d",ps.getUpdateCount()) + " INSERTIONS");
			} catch ( SQLException e ){
				System.out.println("DEBUG : " + e);
			}

			ps = db.prepareStatement
				(
					"INSERT INTO Intervenant ( codeCatIntervenant, nom, prenom, hMax, hMin, coefTp) VALUES"+
					"	( 'info_etd', 'Dunet'   , 'Tom'      , 100, 1, 0.1 ),"+
					"	( 'info_etd', 'Bernouy' , 'Matthias' , 100, 1, 0.1 ),"+
					"	( 'info_etd', 'Bouloché', 'Éléonore' , 100, 1, 0.1 ),"+
					"	( 'info_etd', 'Bureaux' , 'Axel'     , 100, 1, 0.1 ),"+
					"	( 'info_etd', 'Bertaux' , 'Titouan'  , 100, 1, 0.1 );"
				);

			try{
				ps.executeUpdate();
				System.out.println("DEBUG : INSERTION DES DONNÉES RÉUSSIE IL Y A EU " + String.format("%2d",ps.getUpdateCount()) + " INSERTIONS");
			} catch ( SQLException e ){
				System.out.println("DEBUG : " + e);
			}

			ps = db.prepareStatement
				(
					"INSERT INTO Affectation (idIntervenant, nomCatHeure, nbH, codeModule) VALUES"+
					"	( 1, 'TD',  6, 'R1.01' ),"+
					"	( 2, 'TD',  8, 'R1.01' ),"+
					"	( 2, 'TP',  7, 'R3.13' ),"+
					"	( 3, 'TD', 13, 'R3.13' );"
				);

			try{
				ps.executeUpdate();
				System.out.println("DEBUG : INSERTION DES DONNÉES RÉUSSIE IL Y A EU " + String.format("%2d",ps.getUpdateCount()) + " INSERTIONS");
			} catch ( SQLException e ){
				System.out.println("DEBUG : " + e);
			}

			ps = db.prepareStatement
				(
					"INSERT INTO Affectation (idIntervenant, nomCatHeure, nbGrp, nbSemaine, codeModule) VALUES"+
					"	( 2, 'TP',  2, 14, 'R3.13' ),"+
					"	( 1, 'TD',  2, 14, 'R1.01' );"

				);

			try{
				ps.executeUpdate();
				System.out.println("DEBUG : INSERTION DES DONNÉES RÉUSSIE IL Y A EU " + String.format("%2d",ps.getUpdateCount()) + " INSERTIONS");
			} catch ( SQLException e ){
				System.out.println("DEBUG : " + e);
			}

			ps = db.prepareStatement
				(
					"INSERT INTO Affectation (idIntervenant, nomCatHeure, nbH, codeModule) VALUES"+
					"	( 10, 'HSAE', 600, 'S3.01' ),"+
					"	( 11, 'HSAE', 600, 'S3.01' ),"+
					"	( 12, 'HSAE', 600, 'S3.01' ),"+
					"	( 13, 'HSAE', 600, 'S3.01' ),"+
					"	( 14, 'HSAE', 600, 'S3.01' );"
				);

			try{
				ps.executeUpdate();
				System.out.println("DEBUG : INSERTION DES DONNÉES RÉUSSIE IL Y A EU " + String.format("%2d",ps.getUpdateCount()) + " INSERTIONS");
			} catch ( SQLException e ){
				System.out.println("DEBUG : " + e);
			}

			db.close();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}
}