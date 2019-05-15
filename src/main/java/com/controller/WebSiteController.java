/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.persistence.repository.WebSiteRepository;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thread.WorkerThread;
import java.util.List;
import com.persistence.entity.WebSite;
import com.persistence.repository.WebSiteRepositoryImpl;
import java.io.IOException;
import java.util.ArrayList;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author a.casera
 *
 */

@RestController
@RequestMapping("/websites")
public class WebSiteController {
    
    @Autowired
    private WebSiteRepositoryImpl repository;
 
    public WebSiteRepositoryImpl getRepository(){
        return this.repository;
    }
    
    public void setRepository(WebSiteRepositoryImpl repository){
        this.repository = repository;
    }
    
    @RequestMapping(value = "/webgrabbing", method = RequestMethod.POST, consumes="application/json")
    @ResponseBody
    @Transactional
    public List<Boolean> grabbing(@RequestBody String jsonString) {
        List<Boolean> parsedSites = new ArrayList<Boolean>();
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            List<WebSite> websiteList = objectMapper.readValue(jsonString, typeFactory.constructCollectionType(List.class, WebSite.class));       
            ExecutorService executor = Executors.newFixedThreadPool(5);
            for (WebSite website:websiteList) {
                Runnable worker = new WorkerThread(repository, website, parsedSites);
                executor.execute(worker);
              }
            executor.shutdown();
            while (!executor.isTerminated()) {
            }
        }catch(IOException e){
        }finally{
            return parsedSites;                
        }
    }
    
   
}
