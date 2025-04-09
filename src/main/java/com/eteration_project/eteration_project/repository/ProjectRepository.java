package com.eteration_project.eteration_project.repository;

import com.eteration_project.eteration_project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project , Integer> {

}
