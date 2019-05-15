/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistence.repository;

import com.persistence.entity.WebSite;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author a.casera
 */
@NoRepositoryBean
public interface WebSiteRepository extends JpaRepository<WebSite, Integer> {
     
    public List<WebSite> find();
    public void setEntityManagerFactory(EntityManagerFactory emFactory);
}
