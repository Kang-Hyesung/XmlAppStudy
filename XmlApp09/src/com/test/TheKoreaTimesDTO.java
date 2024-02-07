package com.test;

public class TheKoreaTimesDTO
{
	private String title, description, media, author, pubDate, link;

	public TheKoreaTimesDTO(String title, String description, String media, String author, String pubDate, String link)
	{
		this.title = title;
		this.description = description;
		this.media = media;
		this.author = author;
		this.pubDate = pubDate;
		this.link = link;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getMedia()
	{
		return media;
	}

	public void setMedia(String media)
	{
		this.media = media;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getPubDate()
	{
		return pubDate;
	}

	public void setPubDate(String pubDate)
	{
		this.pubDate = pubDate;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}
	
	
}
