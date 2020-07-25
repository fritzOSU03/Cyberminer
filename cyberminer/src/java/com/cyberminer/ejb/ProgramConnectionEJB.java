/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cyberminer.ejb;

import com.cyberminer.kwic.*;
import com.cyberminer.url.*;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Zach
 */
@Stateless
public class ProgramConnectionEJB {
    
    public List<String> getSearchResults(String searchTerm) {
        //Kwic kwic = new Kwic();
        
        System.out.println(String.format("Inside: ProgramConnectionEJB.getSearchResults(%s)", searchTerm));
        List<Url> urls = Kwic.doSearch(searchTerm);
        List<String> strings = new ArrayList<String>();
        for(Url url: urls){
            strings.add(url.getUrl());
            //strings.add("<a href=\"" + url.getUrl() + "\">" + url.getDesc() + "</a>");
        }
        return strings;
        
        /*
        List<String> test = new ArrayList<String>();
        test.add("hello");
        test.add("James");
        test.add("jack");
        return test;
        */
    }
    
    public boolean addSearchTerm(String keyword, String url){
        return false;
    }
    
    public boolean deleteURL(String url){
        return false;
    }
}
