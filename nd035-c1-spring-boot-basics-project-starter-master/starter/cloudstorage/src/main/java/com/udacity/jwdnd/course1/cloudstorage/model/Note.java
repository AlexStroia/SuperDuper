package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {
    Integer noteid;
    String notetitle;
    String notedescription;
    Integer userid;

    public Note(Integer noteid, String notetitle, String notedescription, Integer userid) {
        this.noteid = noteid;
        this.notetitle = notetitle;
        this.notedescription = notedescription;
        this.userid = userid;
    }

    public void setNoteid(Integer noteid) {
        this.noteid = noteid;
    }

    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle;
    }

    public void setNotedescription(String notedescription) {
        this.notedescription = notedescription;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getNoteid() {
        return noteid;
    }

    public String getNotetitle() {
        return notetitle;
    }

    public String getNotedescription() {
        return notedescription;
    }

    public Integer getUserid() {
        return userid;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteid +
                ", noteTitle='" + notetitle + '\'' +
                ", noteDescription='" + notedescription + '\'' +
                ", userId=" + userid +
                '}';
    }
}
