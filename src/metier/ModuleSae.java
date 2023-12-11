package metier;

import java.util.ArrayList;

public class ModuleSae
{
	public static ArrayList<ModuleSae> lstModuleSae;
	private int nbHeuresPn;
	private int nbHeuresTut;
	private ArrayList<SousModuleSae> lstSousModuleSae;

	public ModuleSae(int nbHeuresPn, int nbHeuresTut)
	{
		this.nbHeuresPn = nbHeuresPn;
		this.nbHeuresTut = nbHeuresTut;
		this.lstSousModuleSae = new ArrayList<SousModuleSae>();
		lstModuleSae.add(this);
	}

	public void AjouterSousModuleSae(String nomIntervenant, String typeH, int nbHeures, String commentaire)
	{
		lstSousModuleSae.add(new SousModuleSae(nomIntervenant, typeH, nbHeures, commentaire));
	}
	
	
	public class SousModuleSae
	{
		private String nomIntervenant;
		private String typeH;
		private int nbHeures;
		private String commentaire;

		public SousModuleSae(String nomIntervenant, String typeH, int nbHeures, String commentaire)
		{
			this.nomIntervenant = nomIntervenant;
			this.typeH = typeH;
			this.nbHeures = nbHeures;
			this.commentaire = commentaire;
		}
	}
}

