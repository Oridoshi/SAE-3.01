package metier.model;

public class ProgrammeItem {

    private Integer nbHPn;
    private Integer nbSemaine;
    private Integer nbHeure;

    public ProgrammeItem(Integer nbHPn, Integer nbSemaine, Integer nbHeure) {
        this.nbHPn = nbHPn;
        this.nbHeure = nbHeure;
        this.nbSemaine = nbSemaine;
    }

    public Integer getNbHPn() {
        return nbHPn;
    }

    public Integer getNbSemaine() {
        return nbSemaine;
    }

    public Integer getNbHeure() {
        return nbHeure;
    }

}
