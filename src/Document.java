import java.io.Serializable;

public interface Document extends Serializable {
	
	public Integer _identifiant = new Integer(0);
	public String _titre = new String();
	
	public void setIdentifiant(Integer id);
	public void setTitre(String titre);
	public Integer getIdentifiant();
	public String getTitre();
}
