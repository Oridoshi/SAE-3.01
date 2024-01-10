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
	id SERIAL PRIMARY KEY,
	code VARCHAR(255) NOT NULL,
	nom VARCHAR(255) NOT NULL,
	minH INT NOT NULL,
	maxH INT NOT NULL,
	coefTp DECIMAL(5, 2) NOT NULL default 1
);

CREATE TABLE CategorieHeure (
	id SERIAL PRIMARY KEY,
	nom VARCHAR(50),
	coeffCat DECIMAL(5, 2) NOT NULL default 1
);

CREATE TABLE CategorieModule(
	id SERIAL PRIMARY KEY,
	nom VARCHAR(50)
);

CREATE TABLE Module (
	id SERIAL PRIMARY KEY,
	code VARCHAR(30),
	forceValider boolean NOT NULL,
	idSemestre INT NOT NULL,
	idCatModule INT NOT NULL,
	libLong VARCHAR(255) NOT NULL,
	libCourt VARCHAR(50) NOT NULL,
	FOREIGN KEY (idSemestre) REFERENCES Semestre(id),
	FOREIGN KEY (idCatModule) REFERENCES CategorieModule(id)
);

CREATE TABLE RemplirProgramme(
	id SERIAL PRIMARY KEY,
	idCatModule INT NOT NULL,
	idCatH INT NOT NULL,
	idModule INT NOT NULL,
	nbHProgramme INT NOT NULL,
	nbHPromo INT default 0 NOT NULL,
	nbSemaine INT default 0 NOT NULL,
	FOREIGN KEY (idCatModule) REFERENCES CategorieModule(id),
	FOREIGN KEY (idCatH) REFERENCES CategorieHeure(id),
	FOREIGN KEY (idModule) REFERENCES Module(id)
);

CREATE TABLE RemplirCategorieModule (
	id SERIAL PRIMARY KEY,
	idCatModule INT NOT NULL,
	idCatH INT NOT NULL,
	FOREIGN KEY (idCatModule) REFERENCES CategorieModule(id),
	FOREIGN KEY (idCatH) REFERENCES CategorieHeure(id)
);

CREATE TABLE Intervenant (
	id SERIAL PRIMARY KEY,
	idCatIntervenant INT NOT NULL,
	nom VARCHAR(255) NOT NULL,
	prenom VARCHAR(255) NOT NULL,
	hMax INT default 0 NOT NULL,
	hMin INT default 0 NOT NULL,
	coefTp DECIMAL(5, 2) NOT NULL default 1,
	FOREIGN KEY (idCatIntervenant) REFERENCES CategorieIntervenant(id)
);

CREATE TABLE Affectation (
	id SERIAL PRIMARY KEY,
	idIntervenant INT NOT NULL,
	idCatHeure INT NOT NULL,
	nbH INT default 0 NOT NULL,
	nbGrp INT default 0 NOT NULL,
	idModule INT NOT NULL,
	commentaire TEXT,
	nbSemaine INT default 0 NOT NULL,
	FOREIGN KEY (idIntervenant) REFERENCES Intervenant(id),
	FOREIGN KEY (idCatHeure) REFERENCES CategorieHeure(id),
	FOREIGN KEY (idModule) REFERENCES Module(id)
);