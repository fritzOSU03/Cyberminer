/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberminer.bean;

import com.cyberminer.ejb.ProgramConnectionEJB;
import com.cyberminer.url.Url;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Zach
 */
@Named(value = "deleteTermBean")
@SessionScoped
public class DeleteTermBean implements Serializable{

    @EJB
    private ProgramConnectionEJB programConnectionEJB;
    private String searchTerm;
    private int urlId;
    private String result;
    
    public DeleteTermBean() {
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public int getUrlId() {
        return urlId;
    }

    public void setUrlId(int urlId) {
        this.urlId = urlId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    public List<Url> searchResults(){
        return programConnectionEJB.getSearchResults(searchTerm);
    }
    
    public void deleteURLFromData(){
        if(programConnectionEJB.deleteURL(urlId)){
            result = "Successfully deleted the URL from the database";
        } else {
            result = "The URL could not be deleted";
        }
    }
}
