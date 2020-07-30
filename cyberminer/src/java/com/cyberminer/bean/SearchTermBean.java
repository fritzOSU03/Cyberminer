/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberminer.bean;

import com.cyberminer.ejb.ProgramConnectionEJB;
import com.cyberminer.url.Url;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Zach
 */
@Named(value = "searchTermBean")
@SessionScoped
public class SearchTermBean implements Serializable{

    @EJB
    private ProgramConnectionEJB programSeedEJB;
    private String searchTerm;
    private int searchType;
    private int highestIndex;
    private int startIndex;
    private int pageIncrement;
    
    public SearchTermBean() {}
    @PostConstruct
    public void init(){
        searchType = 5;
        highestIndex = -1;
        pageIncrement = 3;
        startIndex = 0;
    }
    public String getSearchTerm(){
        return searchTerm;
    }
    public void setSearchTerm(String searchTerm){
        this.searchTerm = searchTerm;
    }
    public int getSearchType() {
        return searchType;
    }
    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageIncrement() {
        return pageIncrement;
    }

    public void setPageIncrement(int pageIncrement) {
        this.pageIncrement = pageIncrement;
    }
    
    public boolean hasPrevious(){
        return startIndex > 0;
    }
    public boolean hasNext(){
        return highestIndex == -1 || startIndex + pageIncrement < highestIndex;
    }
    public List<Url> getSearchResults(){
        //request the list from the ejb using the search term
        List<Url> results = programSeedEJB.getSearchResults(searchTerm, startIndex, pageIncrement, searchType);
        if(results.size() < pageIncrement){
            highestIndex = startIndex + results.size();
        }
        return results;
    }
    public void redirect(String url) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect(url);
    }

}
