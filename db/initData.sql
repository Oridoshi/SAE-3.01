-- INSERT valeurs de SEMESTRE
INSERT INTO Semestre ( nbGrpTd, nbGrpTp, nbSemaines ) VALUES
	( 3, 6, 60, 17 ),
	( 3, 6, 60, 15 ),
	( 2, 4, 40, 16 ),
	( 2, 4, 40, 10 ),
	( 2, 4, 40, 12 ),
	( 2, 4, 40, 6 );


-- INSERT valeurs de CATEGORIEINTERVENANT
INSERT INTO CategorieIntervenant VALUES
	("info_ec" , "info_ec" , 192, 364, 1   ),
	("vaca_pro", "vaca_pro", 120, 187, 0.67),
	("vac_sd"  , "vac_sd"  , 90 , 187, 0.67),
	("vaca_ret", "vaca_ret", 80 , 96 , 0.67),
	("info_sd" , "info_sd" , 384, 576, 1   );



-- INSERT valeurs de CATEGORIEHEURE
INSERT INTO CategorieHeure VALUES
	("CM"  , 1.5),
	("TD"  , 1  ),
	("TP"  , 1  ),
	("HT"  , 1  ),
	("REH" , 1  ),
	("HSAE", 1  ),
	("HP"  , 1  );



-- INSERT valeurs de CATEGORIEMODULE
INSERT INTO CategorieModule VALUES
	("Ressource"  ),
	("SAE"        ),
	("Stage/Suivi");



-- INSERT valeurs de MODLULE
INSERT INTO Module VALUES
	( "R1.01", true , 1, "Ressource"  , "Initiation au développement"                        , "Init dev"            ),
	( "R1.02", true , 1, "Ressource"  , "Developpement Interfaces Web"                       , "Intro web"           ),
	( "R1.03", true , 1, "Ressource"  , "Introduction Architecture"                          , "Intro archi"         ),
	( "R1.04", true , 1, "Ressource"  , "Introduction Système"                               , "Intro sys"           ),
	( "R1.05", true , 1, "Ressource"  , "Introduction aux bases de Données"                  , "BD1"                 ),
	( "R1.06", true , 1, "Ressource"  , "Math discrètes"                                     , "Math discrètes"      ),
	( "R1.07", false, 1, "Ressource"  , "Outils Mathématiques fondamentaux"                  , "Outils fondamentaux" ),
	( "R1.08", false, 1, "Ressource"  , "Gestion de projet & des organisations"              , "GPO 1"               ),
	( "R1.09", false, 1, "Ressource"  , "Intro Economie"                                     , "Eco 1"               ),
	( "R1.10", false, 1, "Ressource"  , "Anglais Technique"                                  , "Anglais 1"           ),
	( "R1.11", false, 1, "Ressource"  , "Bases de la communication"                          , "Comm 1"              ),
	( "R1.12", false, 1, "Ressource"  , "Projet Professionnel et personnel"                  , "PPP 1"               ),

	( "S1.01", true , 1, "SAE"        , "SAE 1"                                              , "SAE 1"               ),
	( "S1.02", true , 1, "SAE"        , "SAE 2"                                              , "SAE 2"               ),
	( "S1.03", true , 1, "SAE"        , "SAE 3"                                              , "SAE 3"               ),

	( "R3.01", true , 3, "Ressource"  , "Développement Web"                                  , "Dév Web"             ),
	( "R3.02", true , 3, "Ressource"  , "Développement Efficace"                             , "Dév Efficace"        ),
	( "R3.03", true , 3, "Ressource"  , "Analyse"                                            , "Analyse"             ),
	( "R3.04", true , 3, "Ressource"  , "Qualité de développement 3"                         , "Qualité dev 3"       ),
	( "R3.05", true , 3, "Ressource"  , "Programmation Système"                              , "Prog sys"            ),
	( "R3.06", true , 3, "Ressource"  , "Architecture des Réseaux"                           , "Archi réseaux"       ),
	( "R3.07", true , 3, "Ressource"  , "SQL dans un langage de programmation"               , "BD3"                 ),
	( "R3.08", false, 3, "Ressource"  , "Probabilités"                                       , "Proba"               ),
	( "R3.09", false, 3, "Ressource"  , "Cryptographie et Sécurité"                          , "Crypto"              ),
	( "R3.10", false, 3, "Ressource"  , "Management des Systèmes d'information"              , "MSI"                 ),
	( "R3.11", false, 3, "Ressource"  , "Droits des contrats et du numérique"                , "Droit 3"             ),
	( "R3.12", false, 3, "Ressource"  , "Anglais"                                            , "Anglais"             ),
	( "R3.13", false, 3, "Ressource"  , "Communication professionnelle"                      , "Comm 3"              ),
	( "R3.14", false, 3, "Ressource"  , "Projet Professionnel et Personnel"                  , "PPP 3"               ),

	( "S3.01", true , 3, "SAE"        , "SAE 1"                                              , "SAE 1"               ),

	( "R5.01", true , 5, "Ressource"  , "Initiation au management d'une équipe de projet"    , "Init management"     ),
	( "R5.02", true , 5, "Ressource"  , "Projet Professionnel et Personnel"                  , "PPP"                 ),
	( "R5.03", true , 5, "Ressource"  , "Politique de communication"                         , "Comm 5"              ),
	( "R5.04", true , 5, "Ressource"  , "Qualité algorithmique"                              , "Qualité algo"        ),
	( "R5.05", true , 5, "Ressource"  , "Programmation Avancée"                              , "Prog avancée"        ),
	( "R5.06", true , 5, "Ressource"  , "Sensibilisation à la programmation Multimédia"      , "Prog multimédia"     ),
	( "R5.07", true , 5, "Ressource"  , "Automatisation de la chaine de production"          , "Automatisation"      ),
	( "R5.08", false, 5, "Ressource"  , "Qualité de développement"                           , "Qualité dév 5"       ),
	( "R5.09", false, 5, "Ressource"  , "Virtualisation avancée"                             , "Virtualisation"      ),
	( "R5.10", false, 5, "Ressource"  , "Nouveaux paradigmes de base de données"             , "BD5"                 ),
	( "R5.11", false, 5, "Ressource"  , "Méthodes d'optimisation pour l'aide à la décision"  , "Opti"                ),
	( "R5.12", false, 5, "Ressource"  , "Modélisations mathématiques"                        , "IA avec Python"      ),
	( "R5.13", false, 5, "Ressource"  , "Economie durable et numérique"                      , "Eco"                 ),
	( "R5.14", false, 5, "Ressource"  , "Anglais"                                            , "Anglais"             ),

	( "S5.01", true , 5, "SAE"        , "SAE 1"                                              , "SAE 1"               ),

	( "R6.01", true , 6, "Ressource"  , "Entrepreunariat"                                    , "Entrepreunariat"     ),
	( "R6.02", true , 6, "Ressource"  , "Droit du numérique et de la propriété intelectuelle", "DROIT 6"             ),
	( "R6.03", true , 6, "Ressource"  , "Organisation et Diffusion de l'information"         , "Comm 6"              ),
	( "R6.04", false, 6, "Ressource"  , "Projet Profesionnel et Personnel"                   , "PPP"                 ),
	( "R6.05", false, 6, "Ressource"  , "Développement avancé"                               , "Dev avancé"          ),
	( "R6.06", false, 6, "Ressource"  , "Maintenance applicative"                            , "Maintenance"         ),

	( "S6.01", true , 6, "Stage/Suivi", "Stage"                                              , "Stage"               ),

	( "S6.01", true , 6, "SAE"        , "SAE 1"                                              , "SAE 1"               );


-- INSERT valeurs de REMPLIRPROGRAMME
INSERT INTO RemplirProgramme VALUES
	( "Ressource", "CM", "R1.01",  6,    6, 14 ),
	( "Ressource", "TD", "R1.01", 65,   56, 14 ),
	( "Ressource", "TP", "R1.01", 28,   28, 14 ),
	( "Ressource", "HP", "R1.01",  0,    9, 14 ),
	( "Ressource", "TD", "R3.13", 20, 19.5, 13 ),
	( "Ressource", "TP", "R3.13", 13,   13, 13 ),
	( "Ressource", "HP", "R3.13",  0,    1, 13 );


-- INSERT valeurs de REMPLIRCATEGORIEMODULE
INSERT INTO RemplirCategorieModule VALUES
	( "CM", "R1.01" ),
	( "TD", "R1.01" ),
	( "TP", "R1.01" ),
	( "HP", "R1.01" ),
	( "TD", "R3.13" ),
	( "TP", "R3.13" ),
	( "HP", "R3.13" );


-- INSERT valeurs de INTERVENANT
INSERT INTO Intervenant VALUES
	( "info_ec" , "Boukachour", "Hadhoum"   ),
	( "vaca_pro", "Colignon"  , "Thomas"    ),
	( "vaca_pro", "Dubocage"  , "Tiphaine"  ),
	( "vaca-sd" , "Hervé"     , "Nathalie"  ),
	( "vaca_ret", "Pecqueret" , "Véronique" ),
	( "info_sd" , "Laffeach"  , "Quentin"   ),
	( "info_sd" , "Lepivert"  , "Philippe"  ),
	( "info_sd" , "Legrix"    , "Bruno"     ),
	( "info_sd" , "Nivet"     , "Laurence"  );


-- INSERT valeurs de AFFECTATION
INSERT INTO Affectation VALUES
	( 1, "TD",  6, 2, "R1.01" ),
	( 2, "TD",  8, 3, "R1.01" ),
	( 2, "TP",  7, 2, "R3.13" ),
	( 3, "TD", 13, 2, "R3.13" );