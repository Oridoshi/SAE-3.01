# Application ASTRE

## Objectifs du Projet

L'application ASTRE (Administration et Suivi des Temps des Ressources d’Enseignement) vise à faciliter la gestion complexe des heures d'enseignement dans chaque département de l'IUT. Cette application monoposte est destinée à une équipe restreinte, généralement composée du chef de département et de sa secrétaire.

## Fonctionnalités Principales

### Saisie des Intervenants
- Permet d'ajouter, supprimer et modifier les informations des intervenants, y compris la catégorie, le coefficient TP, le service, etc.
- Affiche les heures du prévisionnel par semestre, avec des sous-totaux pour chaque période.

### Paramétrages
- Permet de paramétrer les semestres, les catégories d'intervenants, et les catégories d'heures.
- Permet d'affecter des coefficients équivalents TD aux différentes catégories.

### Saisie du Prévisionnel
- Permet de créer, supprimer et modifier les modules, y compris les ressources, les Saé, les stages/suivi.
- Permet de saisir les heures prévisionnelles pour chaque module avec un suivi détaillé.

### Génération des États
- Permet de générer des pages récapitulatives par intervenant et par module au format HTML.
- Inclut un tableau récapitulatif de tous les intervenants avec leurs informations.

## Besoins Non Fonctionnels

- **Environnemental:** L'application doit être écoresponsable.
- **Maintenabilité / Évolutivité:** Le code doit être propre et modulaire pour permettre des modifications et ajouts futurs.
- **Fiabilité:** L'application doit être testée avec des cas divers pour assurer sa stabilité.
- **Sécurité:** La base de données doit être protégée par un mot de passe.
- **Ergonomique:** L'application doit être homogène et intuitive.

## Glossaire

- **Heures PN:** Heures prévues dans le Programme National du BUT.
- **Heures du Prévisionnel:** Heures affectées aux intervenants dans les différents semestres.
- **Heures EQTD:** Heures équivalent TD, unité de référence pour la pondération des enseignements.
- **Heures Réelles:** Heures calculées en fonction du type d'intervenant, en prenant en compte le coefficient EQTD et le ratio TP.
- **Heures Tutorées (H Tut):** Heures dévolues au suivi des étudiants.
- **Heures REH:** Heures Référentiel Équivalent Horaire, dévolues au suivi des stages.

