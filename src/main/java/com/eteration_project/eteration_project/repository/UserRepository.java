package com.eteration_project.eteration_project.repository;

import com.eteration_project.eteration_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User , Integer>{

}
