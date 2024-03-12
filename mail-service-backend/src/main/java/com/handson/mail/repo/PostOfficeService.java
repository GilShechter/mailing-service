package com.handson.mail.repo;


import com.handson.mail.model.PostOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostOfficeService {

    @Autowired
    PostOfficeRepository repository;

    public Iterable<PostOffice> all() {
        return repository.findAll();
    }

    public Optional<PostOffice> findById(Long id) {
        return repository.findById(id);
    }


    public PostOffice save(PostOffice postOffice) {
        return repository.save(postOffice);
    }

    public void delete(PostOffice postOffice) {
        repository.delete(postOffice);
    }

}