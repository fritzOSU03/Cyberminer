package com.cyberminer.url;

import com.cyberminer.dao.UrlConnectionService;

/**
 * Url.java
 * 
 * @author James
 * Date: July 27th, 2020
 */
public class Url {
	private int id;
	private String url;
	private String desc;
	private int hits;
	private boolean paid;
	
	
	public Url() {
		setId(-1);
		setUrl("");
		setDesc("");
		setHits(0);
		setPaid(false);
	}
	
	public Url(String url, String desc) {
		setId(-1);
		setUrl(url);
		setDesc(desc);
		setHits(0);
		setPaid(false);
		addUrl();
	}
	
	public Url(String url, String desc, boolean paid) {
		setId(-1);
		setUrl(url);
		setDesc(desc);
		setHits(hits);
		setPaid(paid);
		addUrl();
	}
	
	public Url(int id, String url, String desc, int hits, boolean paid) {
		setId(id);
		setUrl(url);
		setDesc(desc);
		setHits(hits);
		setPaid(paid);
	}
	
	public Url(Url url) {
		setId(url.getId());
		setUrl(url.getUrl());
		setDesc(url.getDesc());
		setHits(url.getHits());
		setPaid(url.getPaid());
	}
	
	protected void setId(int id)		{this.id = id;}
	private void setUrl(String url)		{this.url = new String(url);}
	private void setDesc(String desc)	{this.desc = new String(desc);}
	private void setHits(int hits)		{this.hits = hits;}
	private void setPaid(boolean paid)	{this.paid = paid;}
	
	public int getId()				{return id;}
	public String getUrl()			{return url;}
	public String getDesc()			{return desc;}
	public int getHits()			{return hits;}
	public boolean getPaid()		{return paid;}
	public int getDescNumWords()	{return getDesc().split("\\s").length;}
	public String getWord(int i)	{return getDesc().split("\\s")[i];}
	
	private void addUrl() {
		try {setId(new UrlConnectionService().addUrl(this));}
		catch(Exception e) {e.printStackTrace(); setId(-1);}
	}
	
	@Override
    public String toString() {
		String s = String.format("URL ID: %d\nURL: %s\nDescription: %s\nURL Hits: %d\nSubscriber: %s", getId(), getUrl(), getDesc(), getHits(), getPaid() ? "yes" : "no");
		return s;
	}
}
