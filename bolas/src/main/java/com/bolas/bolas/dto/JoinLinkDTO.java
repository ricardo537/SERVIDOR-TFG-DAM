package com.bolas.bolas.dto;

public class JoinLinkDTO {

	private String joinerLink;
	private String disJoinLink;

	public JoinLinkDTO(String joinerLink, String disJoinLink) {
		super();
		this.joinerLink = joinerLink;
		this.disJoinLink = disJoinLink;
	}

	public JoinLinkDTO() {
		super();
	}

	public String getJoinerLink() {
		return joinerLink;
	}

	public void setJoinerLink(String joinerLink) {
		this.joinerLink = joinerLink;
	}

	public String getDisJoinLink() {
		return disJoinLink;
	}

	public void setDisJoinLink(String disJoinLink) {
		this.disJoinLink = disJoinLink;
	}

}
