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

    public Credential get(int id) {
        return  mapper.get(id);
    }

    public List<Credential> getAll(int id) {
        return mapper.getAll(id);
    }

    public int delete(int id) {
        return mapper.delete(id);
    }

    public void edit(CredentialForm credential) {
      //n mapper.edit(new Credential(credential.getCredentialId(),credential.getUrl(),credential.getUsername(),credential.getKey(),credential.getUserId()));
    }

    public int insert(CredentialForm credential) {
        return mapper.insert(new Credential(
                null,
                credential.getUrl(),
                credential.getUsername(),
                credential.getPassword(),
                credential.getKey(),
                credential.getUserId()
        ));}

}
