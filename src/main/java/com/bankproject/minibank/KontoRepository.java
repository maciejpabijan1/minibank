package com.bankproject.minibank;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KontoRepository extends CrudRepository<Konto, String> {

}