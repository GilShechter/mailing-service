package com.handson.mail.repo;

import com.handson.mail.model.Package;
import org.springframework.data.repository.CrudRepository;

public interface PackageRepository extends CrudRepository<Package, Long> {
}
