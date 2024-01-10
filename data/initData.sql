-- INSERT valeurs de SEMESTRE
INSERT INTO Semestre VALUES
(1, 3, 6, 62, 14),
(2, 3, 6, 85, 14),
(3, 2, 4, 52, 14),
(4, 2, 4, 52, 14),
(5, 2, 4, 52, 14),
(6, 2, 4, 52, 14);

INSERT INTO CategorieIntervenant (code, nom, minH, maxH, coefTp) VALUES
('info_ec', 'info_ec', 192, 364, 1),
('vaca_pro', 'vaca_pro', 120, 187, 0.67),
('vaca_sd', 'vac_sd', 90, 187, 0.67),
('vaca_ret', 'vaca_ret', 80, 96, 0.67),
('info_etd', 'vaca_ret', 1, 1000, 0.01),
('info_sd', 'info_sd', 384, 576, 1);


-- INSERT valeurs de CATEGORIEHEURE
INSERT INTO CategorieHeure (nom, coeffCat) VALUES
('CM'  , 1.5),
('TD'  , 1  ),
('TP'  , 1  ),
('HT'  , 1  ),
('REH' , 1  ),
('HSAE', 1  ),
('HP'  , 1  );



-- INSERT valeurs de CATEGORIEMODULE
INSERT INTO CategorieModule (nom) VALUES
('Ressource'  ),
('SAE'        ),
('PPP'        ),
('Stage/Suivi');


-- INSERT valeurs de MODLULE
INSERT INTO Module (code, forceValider, idSemestre, idCatModule, libLong, libCourt) VALUES
	( 'R1.01', false , 1, 1  , 'Initiation au développement'                        , 'Init dev'            ),
	( 'R1.02', false , 1, 1  , 'Developpement Interfaces Web'                       , 'Intro web'           ),
	( 'R1.03', false , 1, 1  , 'Introduction Architecture'                          , 'Intro archi'         ),
	( 'R1.04', false , 1, 1  , 'Introduction Système'                               , 'Intro sys'           ),
	( 'R1.05', false , 1, 1  , 'Introduction aux bases de Données'                  , 'BD1'                 ),
	( 'R1.06', false , 1, 1  , 'Math discrètes'                                     , 'Math discrètes'      ),
	( 'R1.07', false, 1, 1  , 'Outils Mathématiques fondamentaux'                  , 'Outils fondamentaux' ),
	( 'R1.08', false, 1, 1  , 'Gestion de projet & des organisations'              , 'GPO 1'               ),
	( 'R1.09', false, 1, 1  , 'Intro Economie'                                     , 'Eco 1'               ),
	( 'R1.10', false, 1, 1  , 'Anglais Technique'                                  , 'Anglais 1'           ),
	( 'R1.11', false, 1, 1  , 'Bases de la communication'                          , 'Comm 1'              ),
	( 'R1.12', false, 1, 1  , 'Projet Professionnel et personnel'                  , 'PPP 1'               ),

	( 'S1.01', false , 1, 2        , 'SAE 1'                                              , 'SAE 1'               ),
	( 'S1.02', false , 1, 2        , 'SAE 2'                                              , 'SAE 2'               ),
	( 'S1.03', false , 1, 2        , 'SAE 3'                                              , 'SAE 3'               ),
	( 'S1.04', false , 1, 2        , 'SAE 4'                                              , 'SAE 4'               ),
	( 'S1.05', false , 1, 2        , 'SAE 5'                                              , 'SAE 5'               ),
	( 'S1.06', false , 1, 2        , 'SAE 6'                                              , 'SAE 6'               ),
	( 'P1.01', false , 1, 3 , 'PPP'                                              , 'PPP'               ),
	( 'S1.ST', false , 1, 4 , 'Stage'                                              , 'Stage'               ),

	( 'R3.01', false , 3, 1  , 'Développement Web'                                  , 'Dév Web'             ),
	( 'R3.02', false , 3, 1  , 'Développement Efficace'                             , 'Dév Efficace'        ),
	( 'R3.03', false , 3, 1  , 'Analyse'                                            , 'Analyse'             ),
	( 'R3.04', false , 3, 1  , 'Qualité de développement 3'                         , 'Qualité dev 3'       ),
	( 'R3.05', false , 3, 1  , 'Programmation Système'                              , 'Prog sys'            ),
	( 'R3.06', false , 3, 1  , 'Architecture des Réseaux'                           , 'Archi réseaux'       ),
	( 'R3.07', false , 3, 1  , 'SQL dans un langage de programmation'               , 'BD3'                 ),
	( 'R3.08', false, 3, 1  , 'Probabilités'                                       , 'Proba'               ),
	( 'R3.09', false, 3, 1  , 'Cryptographie et Sécurité'                          , 'Crypto'              ),
	( 'R3.10', false, 3, 1  , 'Management des Systèmes d information'              , 'MSI'                 ),
	( 'R3.11', false, 3, 1  , 'Droits des contrats et du numérique'                , 'Droit 3'             ),
	( 'R3.12', false, 3, 1  , 'Anglais'                                            , 'Anglais'             ),
	( 'R3.13', false, 3, 1  , 'Communication professionnelle'                      , 'Comm 3'              ),
	( 'R3.14', false, 3, 1  , 'Projet Professionnel et Personnel'                  , 'PPP 3'               ),

	( 'S3.01', false , 3, 2       , 'SAE 1'                                              , 'SAE 1'               ),

	( 'R5.01', false , 5,1  , 'Initiation au management d une équipe de projet'    , 'Init management'     ),
	( 'R5.02', false , 5,1  , 'Projet Professionnel et Personnel'                  , 'PPP'                 ),
	( 'R5.03', false , 5,1  , 'Politique de communication'                         , 'Comm 5'              ),
	( 'R5.04', false , 5,1  , 'Qualité algorithmique'                              , 'Qualité algo'        ),
	( 'R5.05', false , 5,1  , 'Programmation Avancée'                              , 'Prog avancée'        ),
	( 'R5.06', false , 5,1  , 'Sensibilisation à la programmation Multimédia'      , 'Prog multimédia'     ),
	( 'R5.07', false , 5,1  , 'Automatisation de la chaine de production'          , 'Automatisation'      ),
	( 'R5.08', false, 5, 1 , 'Qualité de développement'                           , 'Qualité dév 5'       ),
	( 'R5.09', false, 5, 1 , 'Virtualisation avancée'                             , 'Virtualisation'      ),
	( 'R5.10', false, 5, 1 , 'Nouveaux paradigmes de base de données'             , 'BD5'                 ),
	( 'R5.11', false, 5, 1 , 'Méthodes d optimisation pour l aide à la décision'  , 'Opti'                ),
	( 'R5.12', false, 5, 1 , 'Modélisations mathématiques'                        , 'IA avec Python'      ),
	( 'R5.13', false, 5, 1 , 'Economie durable et numérique'                      , 'Eco'                 ),
	( 'R5.14', false, 5, 1 , 'Anglais'                                            , 'Anglais'             ),

	( 'S5.01', false , 5, 2     , 'SAE 1'                                              , 'SAE 1'               ),

	( 'R6.01', false , 6,1  , 'Entrepreunariat'                                    , 'Entrepreunariat'     ),
	( 'R6.02', false , 6,1  , 'Droit du numérique et de la propriété intelectuelle', 'DROIT 6'             ),
	( 'R6.03', false , 6,1  , 'Organisation et Diffusion de l information'         , 'Comm 6'              ),
	( 'R6.04', false, 6, 1 , 'Projet Profesionnel et Personnel'                   , 'PPP'                 ),
	( 'R6.05', false, 6, 1 , 'Développement avancé'                               , 'Dev avancé'          ),
	( 'R6.06', false, 6, 1 , 'Maintenance applicative'                            , 'Maintenance'         ),

	( 'S6.01', false , 6, 4, 'Stage'                                              , 'Stage'               ),

	( 'S6.02', false , 6, 2        , 'SAE 1'                                              , 'SAE 1'               );


-- INSERT valeurs de REMPLIRCATEGORIEMODULE
INSERT INTO RemplirCategorieModule (idCatModule, idCatH) VALUES
( 1, 1 ),
( 1, 2 ),
( 1, 3 ),
( 1, 7 ),
( 2, 4 ),
( 2, 6),
( 4, 4  ),
( 3, 4  ),
( 3, 1  ),
( 3, 2  ),
( 3, 3  ),
( 3, 7  ),
( 4, 5 );

-- INSERT valeurs de REMPLIRPROGRAMME
INSERT INTO RemplirProgramme (idCatModule, idCatH, idModule, nbHProgramme, nbHPromo, nbSemaine) VALUES

( 1, 1, 21,  0,    0, 0 ),
( 1, 2, 21, 34,    2, 13 ),
( 1, 3, 21,  0,    0, 0 ),
( 1, 7, 21,  0,    8, 0  ),

( 1, 1, 22,  0,    0, 0 ),
( 1, 2, 22, 41,    3, 13 ),
( 1, 3, 22,  0,    0, 0 ),
( 1, 7, 22,  0,    2, 0  ),

( 1, 1, 23,  2,    1, 2 ),
( 1, 2, 23, 10,    1, 8 ),
( 1, 3, 23,  0,    0, 0 ),
( 1, 7, 23,  0,    2, 0  ),

( 1, 1, 24,  0,    0, 0 ),
( 1, 2, 24, 42,    3, 13 ),
( 1, 3, 24,  0,    0, 0 ),
( 1, 7, 24,  3,    0, 0  ),

( 1, 1, 25,  7,    1, 7 ),
( 1, 2, 25, 21,    1, 13 ),
( 1, 3, 25,  0,    0, 0 ),
( 1, 7, 25,  0,    8, 0  ),

( 1, 1, 26,  0,    0, 0 ),
( 1, 2, 26, 19,    1, 12 ),
( 1, 3, 26,  0,    0, 0 ),
( 1, 7, 26,  0,    7, 0  ),

( 1, 1, 27,  3,    1, 3 ),
( 1, 2, 27, 32,    2, 12 ),
( 1, 3, 27,  0,    0, 0 ),
( 1, 7, 27,  0,    8, 0  ),

( 1, 1, 28,  0,    0, 0 ),
( 1, 2, 28, 34,    2, 13 ),
( 1, 3, 28,  0,    0, 0 ),
( 1, 7, 28,  0,    8, 0  ),

( 1, 1, 29,  0,    0, 0 ),
( 1, 2, 29, 22,    1, 13 ),
( 1, 3, 29,  0,    0, 0 ),
( 1, 7, 29,  0,    9, 0  ),

( 1, 1, 30,  0,    0, 0 ),
( 1, 2, 30, 32,    2, 12 ),
( 1, 3, 30,  0,    0, 0 ),
( 1, 7, 30,  0,    8, 0  ),

( 1, 1, 31,  7,    0, 0 ),
( 1, 2, 31, 17,    1, 13 ),
( 1, 3, 31,  0,    0, 0 ),
( 1, 7, 31,  0,    0, 0  ),

( 1, 1, 32,  0,    0, 0 ),
( 1, 2, 32, 20,    1, 13 ),
( 1, 3, 32, 13,    1, 13 ),
( 1, 7, 32,  0,    7, 0  ),

( 1, 1, 33,  0,    0, 0 ),
( 1, 2, 33, 20,    1, 13 ),
( 1, 3, 33, 13,    1, 13 ),
( 1, 7, 33,  0,    7, 0  ),

( 1, 1, 34,  0,    0, 0 ),
( 1, 2, 34, 16,    5, 2 ),
( 1, 3, 34,  0,    0, 0 ),
( 1, 7, 34,  0,    6, 0  ),

( 2, 4, 35,  70,  70, 0 ),
( 2, 6, 35,  40,  40, 0 ),

( 1, 1, 1,  6,    1, 6 ),
( 1, 2, 1, 65,    4, 14 ),
( 1, 3, 1, 28,    2, 14 ),
( 1, 7, 1,  0,    9, 0  ),

( 1, 1, 2,  0,    0, 0 ),
( 1, 2, 2, 30,    2, 13 ),
( 1, 3, 2,  0,    0, 0 ),
( 1, 7, 2,  0,    4, 0  ),

( 1, 1, 3,  0,    0, 0 ),
( 1, 2, 3, 15,   2, 7 ),
( 1, 3, 3,  7,   1, 7 ),
( 1, 7, 3,  0,    1, 0  ),

( 1, 1, 4,  0,    0, 0 ),
( 1, 2, 4, 15,   2, 7 ),
( 1, 3, 4,  7,   1, 7 ),
( 1, 7, 4,  0,    1, 0  ),

( 1, 1, 5,  0,    0, 0 ),
( 1, 2, 5, 31,    2, 14 ),
( 1, 3, 5, 14,    1, 14 ),
( 1, 7, 5,  0,    3, 0  ),

( 1, 1, 6,  0,    0, 0 ),
( 1, 2, 6, 38,    2, 14 ),
( 1, 3, 6,  0,    0, 0 ),
( 1, 7, 6,  0,    10, 0  ),

( 1, 1, 7,  0,    0, 0 ),
( 1, 2, 7, 24,    1, 14 ),
( 1, 3, 7,  0,    0, 0 ),
( 1, 7, 7,  0,    10, 0  ),

( 1, 1, 8,  6,    1, 6 ),
( 1, 2, 8, 23,    1, 14 ),
( 1, 3, 8,  0,    0, 0 ),
( 1, 7, 8,  0,    9, 0  ),

( 1, 1, 9,  0,    0, 0 ),
( 1, 2, 9, 22,    1, 14 ),
( 1, 3, 9,  0,    0, 0 ),
( 1, 7, 9,  0,    8, 0  ),

( 1, 1, 10,  0,    0, 0 ),
( 1, 2, 10, 16,    1, 14 ),
( 1, 3, 10, 14,    1, 14 ),
( 1, 7, 10,  0,    2, 0  ),

( 1, 1, 11,  0,    0, 0 ),
( 1, 2, 11, 17,    1, 15 ),
( 1, 3, 11, 15,    1, 15 ),
( 1, 7, 11,  0,    2, 0  ),

( 1, 1, 12,  0,    0, 0 ),
( 1, 2, 12,  7,    1, 7 ),
( 1, 3, 12,  7,    1, 7 ),
( 1, 7, 12,  0,    2, 0  ),

( 4, 5, 20,  10,    10, 0  ),
( 4, 4, 20,  10,    10, 0  ),

( 3, 1, 19,  0,    0, 0 ),
( 3, 2, 19,  7,    7, 0 ),
( 3, 3, 19,  7,    7, 0 ),
( 3, 4, 19,  2,    2, 0  ),
( 3, 7, 19,  0,    0, 0  ),

( 2, 4, 13,  18,  18, 0 ),
( 2, 6, 13,  9,  9, 0 ),

( 2, 4, 14,   18,  18, 0 ),
( 2, 6, 14, 9,  9, 0 ),

( 2, 4, 15,   18,  18, 0 ),
( 2, 6, 15, 9,  9, 0 ),

( 2, 4, 16,   18,  18, 0 ),
( 2, 6, 16, 9,  9, 0 ),

( 2, 4, 17,   18,  18, 0 ),
( 2, 6, 17, 9,  9, 0 ),

( 2, 4, 18,   18,  18, 0 ),
( 2, 6, 18, 9,  9, 0 );


-- INSERT valeurs de INTERVENANT
INSERT INTO Intervenant ( idCatIntervenant, nom, prenom, hmax, hmin) VALUES
	( 1 , 'Boukachour', 'Hadhoum'     , -1, -1 ), --- 0
	( 2, 'Colignon'  , 'Thomas'       , -1, -1 ), --- 1
	( 2, 'Dubocage'  , 'Tiphaine'     , -1, -1 ), --- 2
	( 3 , 'Hervé'     , 'Nathalie'    , -1, -1 ), --- 3
	( 4, 'Pecqueret' , 'Véronique'    , -1, -1 ), --- 4
	( 6 , 'Laffeach'  , 'Quentin'     , -1, -1 ), --- 5
	( 6 , 'Lepivert'  , 'Philippe'    , -1, -1 ), --- 6
	( 6 , 'Legrix'    , 'Bruno'       , -1, -1 ), --- 7
	( 6 , 'Nivet'     , 'Laurence'    , -1, -1 ), --- 8
	( 1 , 'Guinand'     , 'Frédéric'  , -1, -1 ), --- 9
	( 2 , 'Pytel'     , 'Steeve'      , -1, -1 ), --- 10
	( 6 , 'Boukachour'     , 'Jaouad' , -1, -1 ), --- 11
	( 6 , 'Zahour'     , 'Abderrazak' , -1, -1 ), --- 12
	( 6 , 'Boudebous'     , 'Dalila'  , -1, -1 ), --- 13
	( 1 , 'Alabboud'     , 'Hassan'   , -1, -1 ), --- 14
	( 2 , 'Pascal'     , 'Rembert'    , -1, -1 ), --- 15
	( 6 , 'Griette'     , 'Quentin'   , -1, -1 ), --- 16
	( 6 , 'Foubert'     , 'Jean'      , -1, -1 ), --- 17
	( 6 , 'Bertin'     , 'Sébastien'  , -1, -1 ), --- 18
	( 6 , 'Delarue'     , 'Isabelle'  , -1, -1 ), --- 19
	( 6 , 'Sadeg'     , 'Bruno'       , -1, -1 ); --- 20


-- INSERT valeurs de AFFECTATION
INSERT INTO Affectation (idIntervenant, idCatHeure, nbH, nbSemaine, nbGrp, idModule) VALUES
	( 6, 2,  0, 14, 2,1 ),
	( 6, 1,  0, 6, 1, 1 ),
	( 7, 2,  0, 14, 1,1 ),
	( 1, 3,  0, 14, 2,1 ),
	( 2, 3,  0, 14, 2,1 ),

	( 1, 7,  5, 0, 0, 1 ),
	( 1, 7,  5, 0, 0, 1 ),
	( 2, 7,  2, 0, 0, 1 ),
	( 6, 7,  2, 0, 0, 1 ),
	( 7, 7,  4, 0, 0, 1 ),
	( 1, 7,  4, 0, 0, 1 ),
	( 2, 7,  4, 0, 0, 1 ),

	( 1, 2,  0, 13, 2,2 ),
	( 9, 2,  0, 13, 1,2 ),
	( 1, 7,  4, 0, 0, 2),
	( 9, 7,  4, 0, 0, 2),

	(11, 3,  0, 7, 2, 3 ),
	(12, 3,  0, 7, 4, 3 ),
	(11, 2,  0, 7, 1, 3 ),
	(12, 2,  0, 7, 2, 3 ),
	(11, 7,  1, 0, 0, 3 ),
	(12, 7,  1, 0, 0, 3 ),

	(11, 3,  0, 7, 4, 4 ),
	(12, 3,  0, 7, 2, 4 ),
	(11, 2,  0, 7, 2, 4 ),
	(12, 2,  0, 7, 1, 4 ),
	(11, 7,  1, 0, 0, 4 ),
	(12, 7,  1, 0, 0, 4 ),

	(1, 3,  0, 14, 2, 5),
	(13, 3,  0, 14, 2,5 ),
	(20, 3,  0, 14, 2,5 ),
	(1, 2,  0, 14, 1, 5),
	(13, 2,  0, 14, 1,5 ),
	(20, 2,  0, 14, 1,5 ),
	(1, 7,  4, 0, 0, 5),
	(13, 7,  3, 0, 0, 5),
	(20, 7,  2, 0, 0, 5),

	(14, 2,  0, 14, 1,6 ),
	(15, 2,  0, 14, 2,6 ),
	(14, 7,  2, 0, 0, 6),
	(15, 7,  2, 0, 0, 6),

	(14, 2,  0, 14, 1,7 ),
	(15, 2,  0, 14, 2,7 ),
	(14, 7,  4, 0, 0, 7),
	(15, 7,  4, 0, 0, 7),

	(5, 2,  0, 3, 1, 8),
	(5, 2,  0, 11, 3,8 ),
	(6, 2,  0, 3, 2, 8),
	(5, 7,  4, 0, 0, 8),
	(6, 7,  2, 0, 0, 8),

	(5, 2,  0, 14, 3,9 ),
	(5, 7,  1, 0, 0, 9),

	(3, 3,   0, 14, 2,10 ),
	(17, 3,  0, 14, 4,10 ),
	(3, 2,   0, 14, 2,10 ),
	(17, 2,  0, 14, 1,10 ),
	(3, 7,   6, 0, 0, 10),

	(8, 3,   0, 15, 2,11 ),
	(19, 3,  0, 15, 4,11 ),
	(8, 2,   0, 14, 2,11 ),
	(19, 2,  0, 14, 1,11 ),
	(8, 7,   2, 0, 0, 11),
	(19, 7,   2, 0, 0,11 ),

	(1, 3,   1, 0, 0, 12),
	(1, 3,   1, 0, 0, 12),
	(2, 3,   1, 0, 0, 12),
	(3, 3,   4, 0, 0, 12),
	(4, 3,   4, 0, 0, 12),
	(5, 3,   4, 0, 0, 12),
	(6, 3,   2, 0, 0, 12),
	(7, 3,   4, 0, 0, 12),
	(8, 3,   2, 0, 0, 12),
	(9, 3,   2, 0, 0, 12),
	(10, 3,   4, 0, 0,12 ),
	(11, 3,   4, 0, 0,12 ),
	(12, 3,   4, 0, 0,12 ),
	(13, 3,   2, 0, 0,12 ),
	(1, 2,   7, 0, 0, 12),
	(1, 2,   7, 0, 0, 12),
	(2, 2,   7, 0, 0, 12),

	(1, 4,   6, 0, 0, 13 ),
	(6, 4,   6, 0, 0, 13 ),
	(7, 4,   6, 0, 0, 13 ),
	(1, 6,   3, 0, 0, 13 ),
	(6, 6,   3, 0, 0, 13 ),
	(7, 6,   3, 0, 0, 13 ),
	

	(1, 4,   6, 0, 0, 14 ),
	(6, 4,   6, 0, 0, 14 ),
	(7, 4,   6, 0, 0, 14 ),
	(1, 6,   3, 0, 0, 14 ),
	(6, 6,   3, 0, 0, 14 ),
	(7, 6,   3, 0, 0, 14 ),

	(20, 4,     9, 0, 0, 15),
	(20, 4,     9, 0, 0, 15),
	(19, 6,   4, 0, 0, 15),
	(19, 6,   4, 0, 0, 15),

	(3, 4,   6, 0, 0, 16 ),
	(4, 4,   6, 0, 0, 16 ),
	(5, 4,   6, 0, 0, 16 ),
	(3, 6,   3, 0, 0, 16 ),
	(4, 6,   3, 0, 0, 16 ),
	(5, 6,   3, 0, 0, 16 ),

	(11, 4,   6, 0, 0, 17 ),
	(12, 4,   6, 0, 0, 17 ),
	(13, 4,   6, 0, 0, 17 ),
	(11, 6,   3, 0, 0, 17 ),
	(12, 6,   3, 0, 0, 17 ),
	(13, 6,   3, 0, 0, 17 ),

	(7, 2,   7, 0, 0, 19 ),
	(6, 3,   7, 0, 0, 19 ),
	(8, 4,   2, 0, 0, 19 ),

	(4, 5,   10, 0, 0, 20 ),
	(3, 4,   10, 0, 0, 20 ),

	(17, 4,   6, 0, 0, 18 ),
	(18, 4,   6, 0, 0, 18 ),
	(19, 4,   6, 0, 0, 18 ),
	(17, 6,   3, 0, 0, 18 ),
	(18, 6,   3, 0, 0, 18 ),
	(19, 6,   3, 0, 0, 18 );