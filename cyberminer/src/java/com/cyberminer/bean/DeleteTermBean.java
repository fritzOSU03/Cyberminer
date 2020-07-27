/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberminer.bean;

import com.cyberminer.ejb.ProgramConnectionEJB;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Zach
 */
@Named(value = "deleteTermBean")
@RequestScoped
public class DeleteTermBean {

    @EJB
    private ProgramConnectionEJB programConnectionEJB;
    private String searchTerm;
    private String deleteURL;
    private String result;
    
    public DeleteTermBean() {
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getDeleteURL() {
        return deleteURL;
    }

    public void setDeleteURL(String deleteURL) {
        this.deleteURL = deleteURL;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    public List<String> getSearchTerms(){
        return programConnectionEJB.getSearchResults(searchTerm);
    }
    
    public void deleteURLFromData(){
        if(programConnectionEJB.deleteURL(deleteURL)){
            result = "Successfully deleted the URL from the database";
        } else {
            result = "The URL could not be deleted";
        }
    }
}