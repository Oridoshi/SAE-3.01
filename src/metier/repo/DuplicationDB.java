package metier.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import metier.DB;
import metier.DBResult;

public class DuplicationDB {
    
    private static Connection db = DB.getInstance();

    private static PreparedStatement psGetDuplication;
    private static PreparedStatement psUpdateDuplication;
    private static PreparedStatement psCreateDuplication;
	private static PreparedStatement psGetAllDuplication;

    static{
        try{
			psGetAllDuplication = db.prepareStatement("SELECT libelle FROM Duplication");
            psGetDuplication = db.prepareStatement("SELECT * FROM Duplication WHERE libelle = ?");
            psUpdateDuplication = db.prepareStatement("UPDATE Duplication SET content = ? WHERE libelle = ?");
            psCreateDuplication = db.prepareStatement("INSERT INTO Duplication VALUES (?, ?)");
        } catch ( Exception e ){

        }
    }

	public static List<String> list(){
		try{
			DBResult result = DB.query(psGetAllDuplication);
			return result.getLignes().stream().map(ligne -> ligne.get("libelle")).toList();
		} catch ( Exception e ){ return null; }
	}

    public static String getDuplication(String libelle){
        try{
            psGetDuplication.setString(1, libelle);
            DBResult result = DB.query(psGetDuplication);
            if ( result.getLignes().size() == 0 ) return null;
            Map<String, String> ligne = result.getLignes().get(0);
            return ligne.get("content");
        } catch ( Exception e ){ return null; }
    }

    public static void save(String libelle, String content){
        try{
            if ( getDuplication(libelle) == null){
                // Dans ce cas on create
                psCreateDuplication.setString(1, libelle);
                psCreateDuplication.setString(2, content);
                DB.update(psCreateDuplication);
            } else {
                // Dans ce cas on update
                psUpdateDuplication.setString(1, content);
                psUpdateDuplication.setString(2, libelle);
                DB.update(psUpdateDuplication);
            }
        } catch ( Exception e ){ e.printStackTrace();}

    }

}