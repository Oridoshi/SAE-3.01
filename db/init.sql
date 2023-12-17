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
	id SERIAL PRIMARY KEY,
	nbGrpTd INT NOT NULL,
	nbGrpTp INT NOT NULL,
	nbEtd INT NOT NULL,
	nbSemaines INT NOT NULL
);

CREATE TABLE CategorieIntervenant (
	code VARCHAR(255) PRIMARY KEY,
	nom VARCHAR(255) NOT NULL,
	minH INT NOT NULL,
	maxH INT NOT NULL,
	coefTp DECIMAL(5, 2) NOT NULL default 1
);

CREATE TABLE CategorieHeure (
	nom VARCHAR(50) PRIMARY KEY,
	coeffCat DECIMAL(5, 2) NOT NULL default 1
);

CREATE TABLE CategorieModule(
	nom VARCHAR(50) PRIMARY KEY
);

CREATE TABLE Module (
	code VARCHAR(30) PRIMARY KEY,
	forceValider boolean NOT NULL,
	idSemestre INT NOT NULL,
	nomCatModule VARCHAR(50) NOT NULL,
	libLong VARCHAR(255) NOT NULL,
	libCourt VARCHAR(50) NOT NULL,
	FOREIGN KEY (idSemestre) REFERENCES Semestre(id),
	FOREIGN KEY (nomCatModule) REFERENCES CategorieModule(nom)
);

CREATE TABLE RemplirProgramme(
	nomCatModule VARCHAR(50) NOT NULL,
	nomCatH VARCHAR(50) NOT NULL,
	codeModule VARCHAR(50) NOT NULL,
	nbHProgramme INT NOT NULL,
	nbHPromo INT default 0 NOT NULL,
	nbSemaine INT default 0 NOT NULL,
	FOREIGN KEY (nomCatModule) REFERENCES CategorieModule(nom),
	FOREIGN KEY (nomCatH) REFERENCES CategorieHeure(nom),
	FOREIGN KEY (codeModule) REFERENCES Module(code),
	PRIMARY KEY(nomCatModule, nomCatH, codeModule)
);

CREATE TABLE RemplirCategorieModule (
	nomCatModule VARCHAR(50) NOT NULL,
	nomCatH VARCHAR(50) NOT NULL,
	FOREIGN KEY (nomCatModule) REFERENCES CategorieModule(nom),
	FOREIGN KEY (nomCatH) REFERENCES CategorieHeure(nom),
	PRIMARY KEY(nomCatModule, nomCatH)
);

CREATE TABLE Intervenant (
	id SERIAL PRIMARY KEY,
	codeCatIntervenant VARCHAR(50) NOT NULL,
	nom VARCHAR(255) NOT NULL,
	prenom VARCHAR(255) NOT NULL,
	hMax INT default 0 NOT NULL,
	hMin INT default 0 NOT NULL,
	coefTp DECIMAL(5, 2) NOT NULL default 1,
	FOREIGN KEY (codeCatIntervenant) REFERENCES CategorieIntervenant(code)
);

CREATE TABLE Affectation (
	id SERIAL PRIMARY KEY,
	idIntervenant INT NOT NULL,
	nomCatHeure VARCHAR(50) NOT NULL,
	nbH INT default 0 NOT NULL,
	nbGrp INT default 0 NOT NULL,
	codeModule VARCHAR(50) NOT NULL,
	commentaire TEXT,
	nbSemaine INT default 0 NOT NULL,
	FOREIGN KEY (idIntervenant) REFERENCES Intervenant(id),
	FOREIGN KEY (nomCatHeure) REFERENCES CategorieHeure(nom),
	FOREIGN KEY (codeModule) REFERENCES Module(code)
);