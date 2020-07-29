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
    
    public List<Url> getSearchResults(String searchTerm) {
        List<Url> urls;
        if(searchTerm.contains("AND")){
            String s1 = searchTerm.substring(0, searchTerm.indexOf("AND") - 1);
            String s2 = searchTerm.substring(searchTerm.indexOf("AND") + 2);
            urls = Kwic.doAndSearch(s1, s2);
        } else if(searchTerm.contains("NOT")){
            String s1 = searchTerm.substring(0, searchTerm.indexOf("NOT") - 1);
            String s2 = searchTerm.substring(searchTerm.indexOf("NOT") + 2);
            urls = Kwic.doNotSearch(s1, s2);
        } else {
            urls = Kwic.doSearch(searchTerm);
        }

        Collections.sort(urls);
        Collections.reverse(urls);
        return urls;
    }
    
    public boolean addSearchTerm(String keyword, String url){
        return new Kwic().addUrl(url, keyword, false);
    }
    
    public boolean deleteURL(int urlId){
        return Kwic.deleteUrl(urlId);
    }
}
