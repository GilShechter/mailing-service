package com.handson.mail.repo;

import com.handson.mail.model.PostOffice;
import org.springframework.data.repository.CrudRepository;

public interface PostOfficeRepository extends CrudRepository<PostOffice,Long> {
}