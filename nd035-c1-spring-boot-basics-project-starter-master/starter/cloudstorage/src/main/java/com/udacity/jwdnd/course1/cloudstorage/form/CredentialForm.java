package com.udacity.jwdnd.course1.cloudstorage.form;

public class CredentialForm {
    private final Integer credentialId;
    private final String url;
    private final String username;
    private final String key;
    private final String userId;

    public CredentialForm(Integer credentialId, String url, String username, String key, String userId) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.key = key;
        this.userId = userId;
    }

    public Integer getCredentialId() {
        return credentialId;
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

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Credential{" +
                "credentialId=" + credentialId +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", key='" + key + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
