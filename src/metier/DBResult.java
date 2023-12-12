package metier;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBResult {

    private List<String> colonnes;
    private List<Map<String, String>> lignes;

    public DBResult(ResultSet rs){
        colonnes = new ArrayList<>();
        lignes = new ArrayList<>();

        try{
            ResultSetMetaData metaData = rs.getMetaData();
            int nombreColonnes = metaData.getColumnCount();
            for (int i = 1; i <= nombreColonnes; i++) {
                this.colonnes.add(metaData.getColumnName(i));
            }
            while ( rs.next() ){
                Map<String, String> map = new HashMap<>();
                for ( String colonne : this.colonnes ){
                    map.put(colonne, rs.getString(colonne));
                }
                this.lignes.add(map);
            }
        } catch ( Exception e ){
            e.printStackTrace();
        }
    }

    public List<String> getColonnes() {
        return colonnes;
    }

    public List<Map<String, String>> getLignes() {
        return lignes;
    }

}
