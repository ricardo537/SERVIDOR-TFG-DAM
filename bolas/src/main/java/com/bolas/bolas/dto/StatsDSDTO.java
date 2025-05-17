package com.bolas.bolas.dto;

import com.bolas.bolas.entity.Stats;

public class StatsDSDTO {
	
	private Integer[] soccer;
	private Integer[] volleyball;
	private Integer[] basketball;
	private Integer[] tenis;
	
	public StatsDSDTO(Integer[] soccer, Integer[] volleyball, Integer[] basketball, Integer[] tenis) {
		super();
		this.soccer = soccer;
		this.volleyball = volleyball;
		this.basketball = basketball;
		this.tenis = tenis;
	}

	public StatsDSDTO() {
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
	
	public static StatsDSDTO toStatsDSDTO(Stats stats) {
		return new StatsDSDTO(stats.getSoccer(), stats.getVolleyball(), stats.getBasketball(), stats.getTenis());
	}
	
}
