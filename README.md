# Application ASTRE

## Procédure d’installation de l’application
### Windows
Pour installer l'application sur votre appareil, suivez ces étapes :
- Accédez au projet GitHub depuis ce lien : https://github.com/Oridoshi/SAE-3.01
- Dirigez-vous vers la section des versions (releases) : https://github.com/Oridoshi/SAE-3.01/releases
- Téléchargez l'installeur (AstreSetup.exe) à partir de là.
**OU**
- Accéder directement au téléchargement ci dessous : https://github.com/Oridoshi/SAE-3.01/releases/download/V1.1.0/AstreSetup.exe
Une fois que vous avez l'installeur à votre disposition, lancez-le et suivez les différentes étapes. Grâce à cela, vous serez en mesure d'utiliser l'application Astre à votre convenance.
### Linux
Pour installer l'application sur votre appareil, suivez ces étapes :
- Accédez au projet GitHub depuis ce lien : https://github.com/Oridoshi/SAE-3.01
- Dirigez-vous vers la section des versions (releases) : https://github.com/Oridoshi/SAE-3.01/releases
- Téléchargez l'archive (AstreLinux.tar).
**OU**
- Accéder directement au téléchargement ci dessous : https://github.com/Oridoshi/SAE-3.01/releases/download/V1.1.0/AstreLinux.tar
Une fois que vous avez installé le dossier .tar, décompressez-le. Vous pourrez trouver à l'intérieur un dossier "data" et un fichier .jar. Ne touchez pas au dossier "data" ni au fichier .jar ; les deux doivent toujours être dans le même dossier.
Une fois les tous ceci fait lancer le .jar avec la commande “java -jar SAE-3.01.jar“ et l’application ce lanceras
_Note : Vous pourrez trouver le manuel d’utilisation dans les releases si besoin._


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

