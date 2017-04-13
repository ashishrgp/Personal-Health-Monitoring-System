package com.example.dell_pc.health_first;

/**
 * Created by ashish pc on 12-Apr-17.
 */
public class NotesField {
    String tiTle;
    String conTent;

    public NotesField()
    {

    }

    public NotesField(String tiTle,String conTent)
    {
        this.tiTle=this.tiTle;
        this.conTent=this.conTent;
    }

    public String getTiTle() { return tiTle;}
    public void setTiTle(String tiTle)
    {
        this.tiTle=tiTle;

    }

    public String getConTent() {return conTent; }
    public void setConTent(String content)
    {
        this.conTent=conTent;
    }
}
