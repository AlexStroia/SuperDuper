package com.udacity.jwdnd.course1.cloudstorage.form;

public class CredentialForm {
    private  Integer credentialId;
    private  String url;
    private  String username;
    private  String password;
    private  String key;
    private Integer userId;

    public CredentialForm(Integer credentialId, String url, String username, String password, String key, Integer userId) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.password = password;
        this.key = key;
        this.userId = userId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getKey() {
        return key;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "CredentialForm{" +
                "credentialId=" + credentialId +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", key='" + key + '\'' +
                ", userId=" + userId +
                '}';
    }
}
