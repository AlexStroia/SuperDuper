package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.form.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    private final CredentialMapper mapper;

    public CredentialService( CredentialMapper  mapper) {
        this.mapper = mapper;
    }

    Credential get(int id) {
        return  mapper.get(id);
    }

    List<Credential> getAll() {
        return mapper.getAll();
    }

    int delete(int id) {
        return mapper.delete(id);
    }

    public int edit(CredentialForm credential) {
        return mapper.edit(new Credential(credential.getCredentialId(),credential.getUrl(),credential.getUsername(),credential.getKey(),credential.getUserId()));
    }

    public int insert(CredentialForm credential) {
        return mapper.insert(new Credential(credential.getCredentialId(),credential.getUrl(),credential.getUsername(),credential.getKey(),credential.getUserId()));}

}
