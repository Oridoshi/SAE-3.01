-- INSERT valeurs de SEMESTRE
INSERT INTO Semestre VALUES
(1, 3, 6, 62, 14),
(2, 3, 6, 85, 14),
(3, 2, 4, 52, 14),
(4, 2, 4, 52, 14),
(5, 2, 4, 52, 14),
(6, 2, 4, 52, 14);

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