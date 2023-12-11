DROP TABLE if exists Module cascade;
DROP TABLE if exists RemplirProgramme cascade;
DROP TABLE if exists Semestre cascade;
DROP TABLE if exists Intervenant cascade;
DROP TABLE if exists CategorieIntervenant cascade;
DROP TABLE if exists CategorieHeure cascade;
DROP TABLE if exists CategorieModule cascade;
DROP TABLE if exists Affectation cascade;
DROP TABLE if exists RemplirCategorieModule cascade;

CREATE TABLE Semestre (
	idSemestre SERIAL PRIMARY KEY,
	nbGrpTd INT NOT NULL,
	nbGrpTp INT NOT NULL,
	nbEtd INT NOT NULL,
	nbSemaines INT NOT NULL
);

CREATE TABLE CategorieIntervenant (
	idCatIntervenant SERIAL PRIMARY KEY,
	code VARCHAR(255) NOT NULL,
	nom VARCHAR(255) NOT NULL,
	minH INT NOT NULL,
	maxH INT NOT NULL,
	coefTp DECIMAL(5, 2) NOT NULL default 1
);

CREATE TABLE CategorieHeure (
	idCatHeure SERIAL PRIMARY KEY,
	nomCat VARCHAR(50) NOT NULL,
	coeffCat DECIMAL(5, 2) NOT NULL default 1
);

CREATE TABLE CategorieModule(
	idCatModule SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL
);

CREATE TABLE Module (
	idModule SERIAL PRIMARY KEY,
	forceValider boolean NOT NULL,
	idSemestre INT NOT NULL,
	idCatModule INT NOT NULL,
	code VARCHAR(30) NOT NULL,
	libLong VARCHAR(255) NOT NULL,
	libCourt VARCHAR(50) NOT NULL,
	FOREIGN KEY (idSemestre) REFERENCES Semestre(idSemestre),
	FOREIGN KEY (idCatModeul) REFERENCES CategorieModule(idCatModule)
);

CREATE TABLE RemplirProgramme(
	idCatModule INT NOT NULL,
	idCatH INT NOT NULL,
	idModule INT NOT NULL,
	nbHProgramme INT NOT NULL,
	nbHPromo INT default 0,
	nbSemaine INT default 0,
	nbHParSemaine INT default 0,
	FOREIGN KEY (idCatModule) REFERENCES CategorieModule(idCatModule),
	FOREIGN KEY (idCatH) REFERENCES CategorieHeure(idCatHeure),
	FOREIGN KEY (idModule) REFERENCES Module(idModule),
	PRIMARY KEY(idCatModule, idCatH, idModule)
);

CREATE TABLE RemplirCategorieModule (
	idCatModule INT NOT NULL,
	idCatH INT NOT NULL,
	FOREIGN KEY (idCatModule) REFERENCES CategorieModule(idCatModule),
	FOREIGN KEY (idCatH) REFERENCES CategorieHeure(idCatHeure),
	PRIMARY KEY(idCatModule, idCatH)
);

CREATE TABLE Intervenant (
	idIntervenant SERIAL PRIMARY KEY,
	idCatIntervenant INT NOT NULL,
	nom VARCHAR(255) NOT NULL,
	prenom VARCHAR(255) NOT NULL,
	hMax INT default 0,
	FOREIGN KEY (idCatIntervenant) REFERENCES CategorieIntervenant(idCatIntervenant)
);

CREATE TABLE Affectation (
	idAffectation SERIAL PRIMARY KEY,
	idIntervenant INT NOT NULL,
	idCatHeure INT NOT NULL,
	nbH INT default 0,
	nbGrp INT default 0,
	idModule INT NOT NULL,
	commentaire TEXT,
	nbSemaine INT,
	FOREIGN KEY (idIntervenant) REFERENCES Intervenant(idIntervenant),
	FOREIGN KEY (idCatHeure) REFERENCES CategorieHeure(idCatHeure),
	FOREIGN KEY (idModule) REFERENCES Module(idModule)
);