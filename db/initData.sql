-- INSERT valeurs de SEMESTRE
INSERT INTO Semestre VALUES
(1, 3, 6, 62, 14),
(2, 3, 6, 85, 14),
(3, 2, 4, 52, 14),
(4, 2, 4, 52, 14),
(5, 2, 4, 52, 14),
(6, 2, 4, 52, 14);

INSERT INTO CategorieIntervenant VALUES
('info_ec', 'info_ec', 192, 364, 1),
('vaca_pro', 'vaca_pro', 120, 187, 0.67),
('vaca_sd', 'vac_sd', 90, 187, 0.67),
('vaca_ret', 'vaca_ret', 80, 96, 0.67),
('info_etd', 'vaca_ret', 1, 1000, 0.01),
('info_sd', 'info_sd', 384, 576, 1);


-- INSERT valeurs de CATEGORIEHEURE
INSERT INTO CategorieHeure VALUES
('CM'  , 1.5),
('TD'  , 1  ),
('TP'  , 1  ),
('HT'  , 1  ),
('REH' , 1  ),
('HSAE', 1  ),
('HP'  , 1  );



-- INSERT valeurs de CATEGORIEMODULE
INSERT INTO CategorieModule VALUES
('Ressource'  ),
('SAE'        ),
('PPP'        ),
('Stage/Suivi');


-- INSERT valeurs de MODLULE
INSERT INTO Module VALUES
	( 'R1.01', false , 1, 'Ressource'  , 'Initiation au développement'                        , 'Init dev'            ),
	( 'R1.02', false , 1, 'Ressource'  , 'Developpement Interfaces Web'                       , 'Intro web'           ),
	( 'R1.03', false , 1, 'Ressource'  , 'Introduction Architecture'                          , 'Intro archi'         ),
	( 'R1.04', false , 1, 'Ressource'  , 'Introduction Système'                               , 'Intro sys'           ),
	( 'R1.05', false , 1, 'Ressource'  , 'Introduction aux bases de Données'                  , 'BD1'                 ),
	( 'R1.06', false , 1, 'Ressource'  , 'Math discrètes'                                     , 'Math discrètes'      ),
	( 'R1.07', false, 1, 'Ressource'  , 'Outils Mathématiques fondamentaux'                  , 'Outils fondamentaux' ),
	( 'R1.08', false, 1, 'Ressource'  , 'Gestion de projet & des organisations'              , 'GPO 1'               ),
	( 'R1.09', false, 1, 'Ressource'  , 'Intro Economie'                                     , 'Eco 1'               ),
	( 'R1.10', false, 1, 'Ressource'  , 'Anglais Technique'                                  , 'Anglais 1'           ),
	( 'R1.11', false, 1, 'Ressource'  , 'Bases de la communication'                          , 'Comm 1'              ),
	( 'R1.12', false, 1, 'Ressource'  , 'Projet Professionnel et personnel'                  , 'PPP 1'               ),

	( 'S1.01', false , 1, 'SAE'        , 'SAE 1'                                              , 'SAE 1'               ),
	( 'S1.02', false , 1, 'SAE'        , 'SAE 2'                                              , 'SAE 2'               ),
	( 'S1.03', false , 1, 'SAE'        , 'SAE 3'                                              , 'SAE 3'               ),
	( 'S1.04', false , 1, 'SAE'        , 'SAE 4'                                              , 'SAE 4'               ),
	( 'S1.05', false , 1, 'SAE'        , 'SAE 5'                                              , 'SAE 5'               ),
	( 'S1.06', false , 1, 'SAE'        , 'SAE 6'                                              , 'SAE 6'               ),

	( 'R3.01', false , 3, 'Ressource'  , 'Développement Web'                                  , 'Dév Web'             ),
	( 'R3.02', false , 3, 'Ressource'  , 'Développement Efficace'                             , 'Dév Efficace'        ),
	( 'R3.03', false , 3, 'Ressource'  , 'Analyse'                                            , 'Analyse'             ),
	( 'R3.04', false , 3, 'Ressource'  , 'Qualité de développement 3'                         , 'Qualité dev 3'       ),
	( 'R3.05', false , 3, 'Ressource'  , 'Programmation Système'                              , 'Prog sys'            ),
	( 'R3.06', false , 3, 'Ressource'  , 'Architecture des Réseaux'                           , 'Archi réseaux'       ),
	( 'R3.07', false , 3, 'Ressource'  , 'SQL dans un langage de programmation'               , 'BD3'                 ),
	( 'R3.08', false, 3, 'Ressource'  , 'Probabilités'                                       , 'Proba'               ),
	( 'R3.09', false, 3, 'Ressource'  , 'Cryptographie et Sécurité'                          , 'Crypto'              ),
	( 'R3.10', false, 3, 'Ressource'  , 'Management des Systèmes d information'              , 'MSI'                 ),
	( 'R3.11', false, 3, 'Ressource'  , 'Droits des contrats et du numérique'                , 'Droit 3'             ),
	( 'R3.12', false, 3, 'Ressource'  , 'Anglais'                                            , 'Anglais'             ),
	( 'R3.13', false, 3, 'Ressource'  , 'Communication professionnelle'                      , 'Comm 3'              ),
	( 'R3.14', false, 3, 'Ressource'  , 'Projet Professionnel et Personnel'                  , 'PPP 3'               ),

	( 'S3.01', false , 3, 'SAE'        , 'SAE 1'                                              , 'SAE 1'               ),

	( 'R5.01', false , 5, 'Ressource'  , 'Initiation au management d une équipe de projet'    , 'Init management'     ),
	( 'R5.02', false , 5, 'Ressource'  , 'Projet Professionnel et Personnel'                  , 'PPP'                 ),
	( 'R5.03', false , 5, 'Ressource'  , 'Politique de communication'                         , 'Comm 5'              ),
	( 'R5.04', false , 5, 'Ressource'  , 'Qualité algorithmique'                              , 'Qualité algo'        ),
	( 'R5.05', false , 5, 'Ressource'  , 'Programmation Avancée'                              , 'Prog avancée'        ),
	( 'R5.06', false , 5, 'Ressource'  , 'Sensibilisation à la programmation Multimédia'      , 'Prog multimédia'     ),
	( 'R5.07', false , 5, 'Ressource'  , 'Automatisation de la chaine de production'          , 'Automatisation'      ),
	( 'R5.08', false, 5, 'Ressource'  , 'Qualité de développement'                           , 'Qualité dév 5'       ),
	( 'R5.09', false, 5, 'Ressource'  , 'Virtualisation avancée'                             , 'Virtualisation'      ),
	( 'R5.10', false, 5, 'Ressource'  , 'Nouveaux paradigmes de base de données'             , 'BD5'                 ),
	( 'R5.11', false, 5, 'Ressource'  , 'Méthodes d optimisation pour l aide à la décision'  , 'Opti'                ),
	( 'R5.12', false, 5, 'Ressource'  , 'Modélisations mathématiques'                        , 'IA avec Python'      ),
	( 'R5.13', false, 5, 'Ressource'  , 'Economie durable et numérique'                      , 'Eco'                 ),
	( 'R5.14', false, 5, 'Ressource'  , 'Anglais'                                            , 'Anglais'             ),

	( 'S5.01', false , 5, 'SAE'        , 'SAE 1'                                              , 'SAE 1'               ),

	( 'R6.01', false , 6, 'Ressource'  , 'Entrepreunariat'                                    , 'Entrepreunariat'     ),
	( 'R6.02', false , 6, 'Ressource'  , 'Droit du numérique et de la propriété intelectuelle', 'DROIT 6'             ),
	( 'R6.03', false , 6, 'Ressource'  , 'Organisation et Diffusion de l information'         , 'Comm 6'              ),
	( 'R6.04', false, 6, 'Ressource'  , 'Projet Profesionnel et Personnel'                   , 'PPP'                 ),
	( 'R6.05', false, 6, 'Ressource'  , 'Développement avancé'                               , 'Dev avancé'          ),
	( 'R6.06', false, 6, 'Ressource'  , 'Maintenance applicative'                            , 'Maintenance'         ),

	( 'S6.01', false , 6, 'Stage/Suivi', 'Stage'                                              , 'Stage'               ),

	( 'S6.02', false , 6, 'SAE'        , 'SAE 1'                                              , 'SAE 1'               );


-- INSERT valeurs de REMPLIRCATEGORIEMODULE
INSERT INTO RemplirCategorieModule VALUES
( 'Ressource', 'CM' ),
( 'Ressource', 'TD' ),
( 'Ressource', 'TP' ),
( 'Ressource', 'HP' ),
( 'SAE', 'HT' ),
( 'SAE', 'HSAE'),
( 'Stage/Suivi', 'HT'  ),
( 'PPP', 'HT'  ),
( 'PPP', 'CM'  ),
( 'PPP', 'TD'  ),
( 'PPP', 'TP'  ),
( 'PPP', 'HP'  ),
( 'Stage/Suivi', 'REH' );

-- INSERT valeurs de REMPLIRPROGRAMME
INSERT INTO RemplirProgramme VALUES

( 'Ressource', 'CM', 'R3.01',  0,    0, 0 ),
( 'Ressource', 'TD', 'R3.01', 34,    2, 13 ),
( 'Ressource', 'TP', 'R3.01',  0,    0, 0 ),
( 'Ressource', 'HP', 'R3.01',  0,    8, 0  ),

( 'Ressource', 'CM', 'R3.02',  0,    0, 0 ),
( 'Ressource', 'TD', 'R3.02', 41,    3, 13 ),
( 'Ressource', 'TP', 'R3.02',  0,    0, 0 ),
( 'Ressource', 'HP', 'R3.02',  0,    2, 0  ),

( 'Ressource', 'CM', 'R3.03',  2,    1, 2 ),
( 'Ressource', 'TD', 'R3.03', 10,    1, 8 ),
( 'Ressource', 'TP', 'R3.03',  0,    0, 0 ),
( 'Ressource', 'HP', 'R3.03',  0,    2, 0  ),

( 'Ressource', 'CM', 'R3.04',  0,    0, 0 ),
( 'Ressource', 'TD', 'R3.04', 42,    3, 13 ),
( 'Ressource', 'TP', 'R3.04',  0,    0, 0 ),
( 'Ressource', 'HP', 'R3.04',  3,    0, 0  ),

( 'Ressource', 'CM', 'R3.05',  7,    1, 7 ),
( 'Ressource', 'TD', 'R3.05', 21,    1, 13 ),
( 'Ressource', 'TP', 'R3.05',  0,    0, 0 ),
( 'Ressource', 'HP', 'R3.05',  0,    8, 0  ),

( 'Ressource', 'CM', 'R3.06',  0,    0, 0 ),
( 'Ressource', 'TD', 'R3.06', 19,    1, 12 ),
( 'Ressource', 'TP', 'R3.06',  0,    0, 0 ),
( 'Ressource', 'HP', 'R3.06',  0,    7, 0  ),

( 'Ressource', 'CM', 'R3.07',  3,    1, 3 ),
( 'Ressource', 'TD', 'R3.07', 32,    2, 12 ),
( 'Ressource', 'TP', 'R3.07',  0,    0, 0 ),
( 'Ressource', 'HP', 'R3.07',  0,    8, 0  ),

( 'Ressource', 'CM', 'R3.08',  0,    0, 0 ),
( 'Ressource', 'TD', 'R3.08', 34,    2, 13 ),
( 'Ressource', 'TP', 'R3.08',  0,    0, 0 ),
( 'Ressource', 'HP', 'R3.08',  0,    8, 0  ),

( 'Ressource', 'CM', 'R3.09',  0,    0, 0 ),
( 'Ressource', 'TD', 'R3.09', 22,    1, 13 ),
( 'Ressource', 'TP', 'R3.09',  0,    0, 0 ),
( 'Ressource', 'HP', 'R3.09',  0,    9, 0  ),

( 'Ressource', 'CM', 'R3.10',  0,    0, 0 ),
( 'Ressource', 'TD', 'R3.10', 32,    2, 12 ),
( 'Ressource', 'TP', 'R3.10',  0,    0, 0 ),
( 'Ressource', 'HP', 'R3.10',  0,    8, 0  ),

( 'Ressource', 'CM', 'R3.11',  7,    0, 0 ),
( 'Ressource', 'TD', 'R3.11', 17,    1, 13 ),
( 'Ressource', 'TP', 'R3.11',  0,    0, 0 ),
( 'Ressource', 'HP', 'R3.11',  0,    0, 0  ),

( 'Ressource', 'CM', 'R3.12',  0,    0, 0 ),
( 'Ressource', 'TD', 'R3.12', 20,    1, 13 ),
( 'Ressource', 'TP', 'R3.12', 13,    1, 13 ),
( 'Ressource', 'HP', 'R3.12',  0,    7, 0  ),

( 'Ressource', 'CM', 'R3.13',  0,    0, 0 ),
( 'Ressource', 'TD', 'R3.13', 20,    1, 13 ),
( 'Ressource', 'TP', 'R3.13', 13,    1, 13 ),
( 'Ressource', 'HP', 'R3.13',  0,    7, 0  ),

( 'Ressource', 'CM', 'R3.14',  0,    0, 0 ),
( 'Ressource', 'TD', 'R3.14', 16,    5, 2 ),
( 'Ressource', 'TP', 'R3.14',  0,    0, 0 ),
( 'Ressource', 'HP', 'R3.14',  0,    6, 0  ),

( 'SAE', 'HT',   'S3.01',  70,  70, 0 ),
( 'SAE', 'HSAE', 'S3.01',  40,  40, 0 ),

( 'Ressource', 'CM', 'R1.01',  6,    1, 6 ),
( 'Ressource', 'TD', 'R1.01', 65,    4, 14 ),
( 'Ressource', 'TP', 'R1.01', 28,    2, 14 ),
( 'Ressource', 'HP', 'R1.01',  0,    9, 0  ),

( 'Ressource', 'CM', 'R1.02',  0,    0, 0 ),
( 'Ressource', 'TD', 'R1.02', 30,    2, 13 ),
( 'Ressource', 'TP', 'R1.02',  0,    0, 0 ),
( 'Ressource', 'HP', 'R1.02',  0,    4, 0  ),

( 'Ressource', 'CM', 'R1.03',  0,    0, 0 ),
( 'Ressource', 'TD', 'R1.03', 15,   2, 7 ),
( 'Ressource', 'TP', 'R1.03',  7,   1, 7 ),
( 'Ressource', 'HP', 'R1.03',  0,    1, 0  ),

( 'Ressource', 'CM', 'R1.04',  0,    0, 0 ),
( 'Ressource', 'TD', 'R1.04', 15,   2, 7 ),
( 'Ressource', 'TP', 'R1.04',  7,   1, 7 ),
( 'Ressource', 'HP', 'R1.04',  0,    1, 0  ),

( 'Ressource', 'CM', 'R1.05',  0,    0, 0 ),
( 'Ressource', 'TD', 'R1.05', 31,    2, 14 ),
( 'Ressource', 'TP', 'R1.05', 14,    1, 14 ),
( 'Ressource', 'HP', 'R1.05',  0,    3, 0  ),

( 'Ressource', 'CM', 'R1.06',  0,    0, 0 ),
( 'Ressource', 'TD', 'R1.06', 38,    2, 14 ),
( 'Ressource', 'TP', 'R1.06',  0,    0, 0 ),
( 'Ressource', 'HP', 'R1.06',  0,    10, 0  ),

( 'Ressource', 'CM', 'R1.07',  0,    0, 0 ),
( 'Ressource', 'TD', 'R1.07', 24,    1, 14 ),
( 'Ressource', 'TP', 'R1.07',  0,    0, 0 ),
( 'Ressource', 'HP', 'R1.07',  0,    10, 0  ),

( 'Ressource', 'CM', 'R1.08',  6,    1, 6 ),
( 'Ressource', 'TD', 'R1.08', 23,    1, 14 ),
( 'Ressource', 'TP', 'R1.08',  0,    0, 0 ),
( 'Ressource', 'HP', 'R1.08',  0,    9, 0  ),

( 'Ressource', 'CM', 'R1.09',  0,    0, 0 ),
( 'Ressource', 'TD', 'R1.09', 22,    1, 14 ),
( 'Ressource', 'TP', 'R1.09',  0,    0, 0 ),
( 'Ressource', 'HP', 'R1.09',  0,    8, 0  ),

( 'Ressource', 'CM', 'R1.10',  0,    0, 0 ),
( 'Ressource', 'TD', 'R1.10', 16,    1, 14 ),
( 'Ressource', 'TP', 'R1.10', 14,    1, 14 ),
( 'Ressource', 'HP', 'R1.10',  0,    2, 0  ),

( 'Ressource', 'CM', 'R1.11',  0,    0, 0 ),
( 'Ressource', 'TD', 'R1.11', 17,    1, 15 ),
( 'Ressource', 'TP', 'R1.11', 15,    1, 15 ),
( 'Ressource', 'HP', 'R1.11',  0,    2, 0  ),

( 'Ressource', 'CM', 'R1.12',  0,    0, 0 ),
( 'Ressource', 'TD', 'R1.12',  7,    1, 7 ),
( 'Ressource', 'TP', 'R1.12',  7,    1, 7 ),
( 'Ressource', 'HP', 'R1.12',  0,    2, 0  ),

( 'SAE', 'HT',   'S1.01',  18,  18, 0 ),
( 'SAE', 'HSAE', 'S1.01',  9,  9, 0 ),

( 'SAE', 'HT',   'S1.02',  18,  18, 0 ),
( 'SAE', 'HSAE', 'S1.02',  9,  9, 0 ),

( 'SAE', 'HT',   'S1.03',  18,  18, 0 ),
( 'SAE', 'HSAE', 'S1.03',  9,  9, 0 ),

( 'SAE', 'HT',   'S1.04',  18,  18, 0 ),
( 'SAE', 'HSAE', 'S1.04',  9,  9, 0 ),

( 'SAE', 'HT',   'S1.05',  18,  18, 0 ),
( 'SAE', 'HSAE', 'S1.05',  9,  9, 0 ),

( 'SAE', 'HT',   'S1.06',  18,  18, 0 ),
( 'SAE', 'HSAE', 'S1.06',  9,  9, 0 );


-- INSERT valeurs de INTERVENANT
INSERT INTO Intervenant ( codeCatIntervenant, nom, prenom) VALUES
	( 'info_ec' , 'Boukachour', 'Hadhoum'   ), --- 0
	( 'vaca_pro', 'Colignon'  , 'Thomas'    ), --- 1
	( 'vaca_pro', 'Dubocage'  , 'Tiphaine'  ), --- 2
	( 'vaca_sd' , 'Hervé'     , 'Nathalie'  ), --- 3
	( 'vaca_ret', 'Pecqueret' , 'Véronique' ), --- 4
	( 'info_sd' , 'Laffeach'  , 'Quentin'   ), --- 5
	( 'info_sd' , 'Lepivert'  , 'Philippe'  ), --- 6
	( 'info_sd' , 'Legrix'    , 'Bruno'     ), --- 7
	( 'info_sd' , 'Nivet'     , 'Laurence'  ), --- 8
	( 'info_ec' , 'Guinand'     , 'Frédéric'  ), --- 9
	( 'vaca_pro' , 'Pytel'     , 'Steeve'  ), --- 10
	( 'info_sd' , 'Boukachour'     , 'Jaouad'  ), --- 11
	( 'info_sd' , 'Zahour'     , 'Abderrazak'  ), --- 12
	( 'info_sd' , 'Boudebous'     , 'Dalila'  ), --- 13
	( 'info_ec' , 'Alabboud'     , 'Hassan'  ), --- 14
	( 'vaca_pro' , 'Pascal'     , 'Rembert'  ), --- 15
	( 'info_sd' , 'Griette'     , 'Quentin'  ), --- 16
	( 'info_sd' , 'Foubert'     , 'Jean'  ), --- 17
	( 'info_sd' , 'Bertin'     , 'Sébastien'  ), --- 18
	( 'info_sd' , 'Delarue'     , 'Isabelle'  ), --- 19
	( 'info_sd' , 'Sadeg'     , 'Bruno'  ); --- 20


-- INSERT valeurs de AFFECTATION
INSERT INTO Affectation (idIntervenant, nomCatHeure, nbH, nbSemaine, nbGrp, codeModule) VALUES
	( 6, 'TD',  0, 14, 2, 'R1.01' ),
	( 6, 'CM',  0, 6, 1, 'R1.01' ),
	( 7, 'TD',  0, 14, 1, 'R1.01' ),
	( 1, 'TP',  0, 14, 2, 'R1.01' ),
	( 2, 'TP',  0, 14, 2, 'R1.01' ),

	( 1, 'HP',  5, 0, 0, 'R1.01' ),
	( 1, 'HP',  5, 0, 0, 'R1.01' ),
	( 2, 'HP',  2, 0, 0, 'R1.01' ),
	( 6, 'HP',  2, 0, 0, 'R1.01' ),
	( 7, 'HP',  4, 0, 0, 'R1.01' ),
	( 1, 'HP',  4, 0, 0, 'R1.01' ),
	( 2, 'HP',  4, 0, 0, 'R1.01' ),

	( 1, 'TD',  0, 13, 2, 'R1.02' ),
	( 9, 'TD',  0, 13, 1, 'R1.02' ),
	( 1, 'HP',  4, 0, 0, 'R1.02' ),
	( 9, 'HP',  4, 0, 0, 'R1.02' ),

	(11, 'TP',  0, 7, 2, 'R1.03' ),
	(12, 'TP',  0, 7, 4, 'R1.03' ),
	(11, 'TD',  0, 7, 1, 'R1.03' ),
	(12, 'TD',  0, 7, 2, 'R1.03' ),
	(11, 'HP',  1, 0, 0, 'R1.03' ),
	(12, 'HP',  1, 0, 0, 'R1.03' ),

	(11, 'TP',  0, 7, 4, 'R1.04' ),
	(12, 'TP',  0, 7, 2, 'R1.04' ),
	(11, 'TD',  0, 7, 2, 'R1.04' ),
	(12, 'TD',  0, 7, 1, 'R1.04' ),
	(11, 'HP',  1, 0, 0, 'R1.04' ),
	(12, 'HP',  1, 0, 0, 'R1.04' ),

	(1, 'TP',  0, 14, 2, 'R1.05' ),
	(13, 'TP',  0, 14, 2, 'R1.05' ),
	(20, 'TP',  0, 14, 2, 'R1.05' ),
	(1, 'TD',  0, 14, 1, 'R1.05' ),
	(13, 'TD',  0, 14, 1, 'R1.05' ),
	(20, 'TD',  0, 14, 1, 'R1.05' ),
	(1, 'HP',  4, 0, 0, 'R1.05' ),
	(13, 'HP',  3, 0, 0, 'R1.05' ),
	(20, 'HP',  2, 0, 0, 'R1.05' ),

	(14, 'TD',  0, 14, 1, 'R1.06' ),
	(15, 'TD',  0, 14, 2, 'R1.06' ),
	(14, 'HP',  2, 0, 0, 'R1.06' ),
	(15, 'HP',  2, 0, 0, 'R1.06' ),

	(14, 'TD',  0, 14, 1, 'R1.07' ),
	(15, 'TD',  0, 14, 2, 'R1.07' ),
	(14, 'HP',  4, 0, 0, 'R1.07' ),
	(15, 'HP',  4, 0, 0, 'R1.07' ),

	(5, 'TD',  0, 3, 1, 'R1.08' ),
	(5, 'TD',  0, 11, 3, 'R1.08' ),
	(6, 'TD',  0, 3, 2, 'R1.08' ),
	(5, 'HP',  4, 0, 0, 'R1.08' ),
	(6, 'HP',  2, 0, 0, 'R1.08' ),

	(5, 'TD',  0, 14, 3, 'R1.09' ),
	(5, 'HP',  1, 0, 0, 'R1.09' ),

	(3, 'TP',   0, 14, 2, 'R1.10' ),
	(17, 'TP',  0, 14, 4, 'R1.10' ),
	(3, 'TD',   0, 14, 2, 'R1.10' ),
	(17, 'TD',  0, 14, 1, 'R1.10' ),
	(3, 'HP',   6, 0, 0, 'R1.10' ),

	(8, 'TP',   0, 15, 2, 'R1.11' ),
	(19, 'TP',  0, 15, 4, 'R1.11' ),
	(8, 'TD',   0, 14, 2, 'R1.11' ),
	(19, 'TD',  0, 14, 1, 'R1.11' ),
	(8, 'HP',   2, 0, 0, 'R1.11' ),
	(19, 'HP',   2, 0, 0, 'R1.11' ),

	(1, 'TP',   1, 0, 0, 'R1.12' ),
	(1, 'TP',   1, 0, 0, 'R1.12' ),
	(2, 'TP',   1, 0, 0, 'R1.12' ),
	(3, 'TP',   4, 0, 0, 'R1.12' ),
	(4, 'TP',   4, 0, 0, 'R1.12' ),
	(5, 'TP',   4, 0, 0, 'R1.12' ),
	(6, 'TP',   2, 0, 0, 'R1.12' ),
	(7, 'TP',   4, 0, 0, 'R1.12' ),
	(8, 'TP',   2, 0, 0, 'R1.12' ),
	(9, 'TP',   2, 0, 0, 'R1.12' ),
	(10, 'TP',   4, 0, 0, 'R1.12' ),
	(11, 'TP',   4, 0, 0, 'R1.12' ),
	(12, 'TP',   4, 0, 0, 'R1.12' ),
	(13, 'TP',   2, 0, 0, 'R1.12' ),
	(1, 'TD',   7, 0, 0, 'R1.12' ),
	(1, 'TD',   7, 0, 0, 'R1.12' ),
	(2, 'TD',   7, 0, 0, 'R1.12' ),

	(1, 'HT',   6, 0, 0, 'S1.01' ),
	(6, 'HT',   6, 0, 0, 'S1.01' ),
	(7, 'HT',   6, 0, 0, 'S1.01' ),
	(1, 'HSAE',   3, 0, 0, 'S1.01' ),
	(6, 'HSAE',   3, 0, 0, 'S1.01' ),
	(7, 'HSAE',   3, 0, 0, 'S1.01' ),
	

	(1, 'HT',   6, 0, 0, 'S1.02' ),
	(6, 'HT',   6, 0, 0, 'S1.02' ),
	(7, 'HT',   6, 0, 0, 'S1.02' ),
	(1, 'HSAE',   3, 0, 0, 'S1.02' ),
	(6, 'HSAE',   3, 0, 0, 'S1.02' ),
	(7, 'HSAE',   3, 0, 0, 'S1.02' ),

	(20, 'HT',     9, 0, 0, 'S1.03' ),
	(20, 'HT',     9, 0, 0, 'S1.03' ),
	(19, 'HSAE',   4, 0, 0, 'S1.03' ),
	(19, 'HSAE',   4, 0, 0, 'S1.03' ),

	(3, 'HT',   6, 0, 0, 'S1.04' ),
	(4, 'HT',   6, 0, 0, 'S1.04' ),
	(5, 'HT',   6, 0, 0, 'S1.04' ),
	(3, 'HSAE',   3, 0, 0, 'S1.04' ),
	(4, 'HSAE',   3, 0, 0, 'S1.04' ),
	(5, 'HSAE',   3, 0, 0, 'S1.04' ),

	(11, 'HT',   6, 0, 0, 'S1.05' ),
	(12, 'HT',   6, 0, 0, 'S1.05' ),
	(13, 'HT',   6, 0, 0, 'S1.05' ),
	(11, 'HSAE',   3, 0, 0, 'S1.05' ),
	(12, 'HSAE',   3, 0, 0, 'S1.05' ),
	(13, 'HSAE',   3, 0, 0, 'S1.05' ),

	(17, 'HT',   6, 0, 0, 'S1.06' ),
	(18, 'HT',   6, 0, 0, 'S1.06' ),
	(19, 'HT',   6, 0, 0, 'S1.06' ),
	(17, 'HSAE',   3, 0, 0, 'S1.06' ),
	(18, 'HSAE',   3, 0, 0, 'S1.06' ),
	(19, 'HSAE',   3, 0, 0, 'S1.06' );