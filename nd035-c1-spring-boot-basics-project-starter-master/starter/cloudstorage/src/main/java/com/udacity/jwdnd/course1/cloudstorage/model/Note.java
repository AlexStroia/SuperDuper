package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {
    final Integer noteid;
    final String notetitle;
    final String notedescription;
    final Integer userid;

    public Note(Integer noteid, String notetitle, String notedescription, Integer userid) {
        this.noteid = noteid;
        this.notetitle = notetitle;
        this.notedescription = notedescription;
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
