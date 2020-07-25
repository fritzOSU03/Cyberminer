/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberminer.bean;

import com.cyberminer.ejb.ProgramConnectionEJB;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Zach
 */
@Named(value = "searchTermBean")
@RequestScoped
public class SearchTermBean {

    @EJB
    private ProgramConnectionEJB programSeedEJB;
    private String searchTerm;
    
    public SearchTermBean() {}
    public String getSearchTerm(){
        return searchTerm;
    }
    public void setSearchTerm(String searchTerm){
        this.searchTerm = searchTerm;
    }
    public List<String> getSearchResults(){
        //request the list from the ejb using the search term
        return programSeedEJB.getSearchResults(searchTerm);
    }
    public void redirect(String url) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(url);
    }

}
