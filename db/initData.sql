---- Init Catégories d'heures ----
INSERT INTO CategorieHeure VALUES
("CM", 1.5),
("TD", 1),
("TP", 1),
("HT", 1),
("REH", 1),
("HSAE", 1),
("HP", 1);

---- Création des catégories de modules ----
INSERT INTO CategorieModule VALUES
("Ressource"),
("SAE"),
("Stage/Suivi");

---- Définir les heures qu'il y aura dans une catégorie de module ----
INSERT INTO RemplirCategorieModule VALUES
(0, 0),
(0, 1),
(0, 2),
(1, 3),
(1, 5),
(2, 4),
(2, 3);


---- Création des catégories d'intervenants ----
INSERT INTO CategorieIntervenant VALUES
("info_ec", "info_ec", 192, 364, 1),
("vaca_pro", "vaca_pro", 120, 187, 0.67),
("vac_sd", "vac_sd", 90, 187, 0.67),
("vaca_ret", "vaca_ret", 80, 96, 0.67),
("info_sd", "info_sd", 384, 576, 1);

---- Création des semestres ----
INSERT INTO Semestre VALUES
(1, 4, 7, 85, 15),
(2, 4, 7, 85, 15),
(3, 2, 4, 52, 13),
(4, 2, 4, 52, 13);

---- Création des intervenants ----
INSERT INTO Intervenant (idCatIntervenant, nom, prenom) VALUES
(0, "Boukachour", "Hadhoum"),
(1, "Colignon", "Thomas"),
(1, "Dubocage", "Tiphaine"),
(2, "Hervé", "Nathalie"),
(3, "Pecqueret", "Véronique"),
(4, "Laffeach", "Quentin"),
(4, "Lepivert", "Philippe"),
(4, "Legrix", "Bruno"),
(4, "Nivet", "Laurence");


---- Création des modules ----
INSERT INTO Module VALUES
(false, 1, 0, "R1.01", "Initiation au développement", "Init Dev"),
(false, 1, 0, "R1.01", "Développement interfaces Web", "Dev Web"),
(false, 1, 0, "R1.01", "Introduction Architecture", "Architecture"),
(false, 1, 0, "R1.01", "Introduction Système", "Système"),
(false, 1, 0, "R1.01", "Introduction Base de données", "BDD"),