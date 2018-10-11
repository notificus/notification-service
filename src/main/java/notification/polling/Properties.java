package notification.polling;


public class Properties {
    private int id;
    private String nouvelle;
    private String createur;

    public void setId(int id){
        this.id =id;
    }
    public void setNouvelle(String nouvelle){
        this.nouvelle = nouvelle;
    }
    public void setCreateur(String createur){
        this.createur = createur;
    }

    public int getId(){
        return id;
    }
    public String getNouvelle(){
        return nouvelle;
    }
    public String getCreateur(){
        return createur;
    }
}
