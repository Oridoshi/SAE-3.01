import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class SemestreDB{

	private Connection db = DB.getInstance();

	private PreparedStatement psGetSemestres = db.prepareStatement("SELECT * FROM Semestre");

	public List<Semestre> getSemestres(){

	}

}