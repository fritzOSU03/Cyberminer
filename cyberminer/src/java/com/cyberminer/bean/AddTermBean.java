/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberminer.bean;

import com.cyberminer.ejb.ProgramConnectionEJB;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "addTermBean")
@RequestScoped
public class AddTermBean {

    @EJB
    private ProgramConnectionEJB programConnectionEJB;
    private String keyword;
    private String url;
    private String result;
    
    public AddTermBean() {
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    public void addTerm(){
        if(programConnectionEJB.addSearchTerm(keyword, url)){
            result = "Search term successfully added";
        } else {
            result = "Could not add search term";
        }
    }
}
