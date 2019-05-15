/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author a.casera
 */

@Entity
@Table(name = "website")
public class WebSite {
//    private @Id @GeneratedValue Long id;
    
    @Column(name="url", nullable = false)
    private @Id String url;
    
    @Column(name="rank", nullable = false)
    private String rank;
    
    @Column(name="marfeelable", nullable = true)
    private boolean marfeelable;

  WebSite() {}

  WebSite(String url, String rank) {
    this.url = url;
    this.rank = rank;
  }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public boolean isMarfeelable() {
        return marfeelable;
    }

    public void setMarfeelable(boolean marfeelable) {
        this.marfeelable = marfeelable;
    }
  
  
}
