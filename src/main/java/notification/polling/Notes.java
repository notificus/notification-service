package notification.polling;


public class Notes {
    private String cip;
    private String classSigil;
    private String competence;
    private String noteType;

    public String getCip(){
        return this.cip;
    }

    public String getClassSigil(){
        return  this.classSigil;
    }

    public String getCompetence(){
        return  this.competence;
    }

    public String getNoteType(){
        return  this.noteType;
    }

    void setCip(String cip){
        this.cip =cip;
    }

    void setClassSigil(String classSigil){
        this.classSigil = classSigil;
    }

    void setCompetence(String competence){
        this.competence = competence;
    }

    void setNoteType(String noteType){
        this.noteType = noteType;
    }
}
