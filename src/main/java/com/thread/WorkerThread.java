/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thread;

import com.persistence.entity.WebSite;
import com.persistence.repository.WebSiteRepository;
import com.persistence.repository.WebSiteRepositoryImpl;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
/**
 *
 * @author a.casera
 */

    
    
    


public class WorkerThread implements Runnable {
  
    private WebSite website;
    private WebSiteRepositoryImpl repository;
    private List results;
    private boolean result = false;
    
    public WorkerThread(WebSiteRepositoryImpl repository, WebSite website, List results){
        this.website = website;
        this.repository = repository;
        this.results = results;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Starting parsing website = "+website.getUrl());
        try {
            processCommand();
        } catch (IOException ex) {
            Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(Thread.currentThread().getName()+" End.");
    }

    private void processCommand() throws IOException{
        try{
        if(!website.getUrl().startsWith("http://"))
                website.setUrl("http://"+website.getUrl());
        Document doc = Jsoup.connect(website.getUrl()).get();    
        String title = doc.title().toLowerCase();
        if(title.contains("news") || title.contains("noticias"))
            result = true;
        
        }catch(Exception e){}finally{
            website.setMarfeelable(result);
            repository.executeQuery(website);
            results.add(result);
        }
    }

//    @Override
//    public String toString(){
//        return ""+this.result;
//    }
}

    

