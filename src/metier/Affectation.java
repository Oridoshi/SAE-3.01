package metier;

public class Affectation
{
	private Intervenant intervenant;
	private TypeHeure typeHeure;
	private Integer nbGroupe;
	private Integer nbSemaine;
	private Integer nbHeure;
	private String commentaire;

	public Affectation(Intervenant intervenant, TypeHeure typeHeure, int nbGroupe, int nbSemaine, String commentaire)
	{
		this.intervenant = intervenant;
		this.typeHeure = typeHeure;
		this.nbGroupe = nbGroupe;
		this.nbSemaine = nbSemaine;
		this.commentaire = commentaire;
	}

	public Affectation(Intervenant intervenant, TypeHeure typeHeure, int nbHeure, String commentaire)
	{
		this.intervenant = intervenant;
		this.typeHeure = typeHeure;
		this.nbHeure = nbHeure;
		this.commentaire = commentaire;
	}

	public Intervenant getIntervenant(){return intervenant;}
	public TypeHeure getTypeHeure(){return typeHeure;}
	public int getNbGroupe(){return nbGroupe;}
	public int getNbSemaine(){return nbSemaine;}
	public String getCommentaire(){return commentaire;}
	public int getNbHeure(){return nbHeure;}
}