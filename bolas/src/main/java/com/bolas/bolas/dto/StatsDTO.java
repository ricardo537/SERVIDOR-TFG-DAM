package com.bolas.bolas.dto;

import java.util.UUID;

import com.bolas.bolas.entity.Stats;
import com.bolas.bolas.entity.User;

import jakarta.persistence.Column;

public class StatsDTO {

	private Integer[] soccer;
	private Integer[] volleyball;
	private Integer[] basketball;
	private Integer[] tenis;
	private SessionDTO session;
	
	public StatsDTO(Integer[] soccer, Integer[] volleyball, Integer[] basketball, Integer[] tenis, SessionDTO session) {
		super();
		this.soccer = soccer;
		this.volleyball = volleyball;
		this.basketball = basketball;
		this.tenis = tenis;
		this.session =  session;
	}

	public StatsDTO() {
		super();
	}

	public Integer[] getSoccer() {
		return soccer;
	}

	public void setSoccer(Integer[] soccer) {
		this.soccer = soccer;
	}

	public Integer[] getVolleyball() {
		return volleyball;
	}

	public void setVolleyball(Integer[] volleyball) {
		this.volleyball = volleyball;
	}

	public Integer[] getBasketball() {
		return basketball;
	}

	public void setBasketball(Integer[] basketball) {
		this.basketball = basketball;
	}

	public Integer[] getTenis() {
		return tenis;
	}

	public void setTenis(Integer[] tenis) {
		this.tenis = tenis;
	}

	public SessionDTO getSession() {
		return session;
	}

	public void setSession(SessionDTO session) {
		this.session = session;
	}
	
	public Stats toStats(User user) {
		Stats stats = new Stats();
		stats.setSoccer(soccer);
		stats.setVolleyball(volleyball);
		stats.setBasketball(basketball);
		stats.setTenis(tenis);
		stats.setUser(user);
		return stats;
	}
	
}
