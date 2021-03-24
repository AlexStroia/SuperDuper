package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {

    private final Integer fileid;
    private final String filename;
    private final String contenttype;
    private final String filesize;
    private final Integer userid;
    private final byte[] filedata;

    public File(Integer fileid, String filename, String contentType, String filesize, Integer userid, byte[] filedata) {
        this.fileid = fileid;
        this.filename = filename;
        this.contenttype = contentType;
        this.filesize = filesize;
        this.userid = userid;
        this.filedata = filedata;
    }

    @Override
    public String toString() {
        return "File{" +
                "fileId=" + fileid +
                ", fileName='" + filename + '\'' +
                ", contentType='" + contenttype + '\'' +
                ", fileSize='" + filesize + '\'' +
                ", userId=" + userid +
                ", fileData=" + filedata +
                '}';
    }
}
