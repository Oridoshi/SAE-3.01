**Module**
 - forceValider
 - semestre >= 1 && <= 6
 - libLong
 - libCourt

**Programme**
 - module
 - catH
 - nbHPn
 - nbHPromo (nullable)
 - nbSemaine (nullable)
 - nbHParSemaine (nullable)
Si nbHPromo alors pas de nbSemaine ou nbHParSemaine

**Semestre**
 - nbGrpTd
 - nbGrpTp
 - nbEtd
 - nbSemaines

**Intervenant**
 - categorie
 - nom
 - prenom
 - hMax (nullable)

**Categorie Intervenant**
 - code
 - nom
 - minH
 - maxH
 - coefTp

**Categorie Heure**
- idCatHeure
- nomCat (ex : CM, TD, TP...)
- coeffCat

**Affectation**
 - intervenant
 - idCatHeure
 - nbH (nullable ) la création d'un module
 - nbGrp (nullable ) la création d'un module
 - module
 - commentaire
 - nbSemaine (nullable ) la création d'un module

Où NbH ne peut pas être rempli si nbGrp est rempli et inversement
