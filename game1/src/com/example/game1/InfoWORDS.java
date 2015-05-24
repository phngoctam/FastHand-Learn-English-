package com.example.game1;

public class InfoWORDS {
	private	int id;
	private int idtopic;
	private String content;
	private String picture;
	private String sound;
	public int getIdtopic() {
		return idtopic;
	}
	public void setIdtopic(int idtopic) {
		this.idtopic = idtopic;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getSound() {
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
	}
	private InfoTOPICS topics;
	public InfoTOPICS getTopics() {
		return topics;
	}
	public void setTopics(InfoTOPICS topics) {
		this.topics = topics;
	}
	
	
}
