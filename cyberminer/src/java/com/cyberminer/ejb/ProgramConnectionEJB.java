/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberminer.ejb;

import com.cyberminer.kwic.*;
import com.cyberminer.url.*;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Zach
 */
@Stateless
public class ProgramConnectionEJB {
    
    public List<Url> getSearchResults(String searchTerm, int start, int end, int type) {
        List<Url> urls;
        if(searchTerm.contains("AND")){
            String s1 = searchTerm.substring(0, searchTerm.indexOf("AND") - 1);
            String s2 = searchTerm.substring(searchTerm.indexOf("AND") + 2);
            urls = Kwic.doAndSearch(s1, s2, start, end, type);
        } else if(searchTerm.contains("NOT")){
            String s1 = searchTerm.substring(0, searchTerm.indexOf("NOT") - 1);
            String s2 = searchTerm.substring(searchTerm.indexOf("NOT") + 2);
            urls = Kwic.doNotSearch(s1, s2, start, end, type);
        } else {
            urls = Kwic.doSearch(searchTerm, start, end, type);
        }
        return urls;
    }
    
    public List<Url> getSearchResults(String searchTerm) {
        List<Url> urls= Kwic.doSearch(searchTerm);
        return urls;
    }
    
    public boolean addSearchTerm(String keyword, String url, boolean sponsored){
        return new Kwic().addUrl(url, keyword, sponsored);
    }
    
    public boolean deleteURL(int urlId){
        return Kwic.deleteUrl(urlId);
    }
}
