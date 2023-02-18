package com.example.Crud.operation.and.validation.reposatory;

import com.example.Crud.operation.and.validation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
