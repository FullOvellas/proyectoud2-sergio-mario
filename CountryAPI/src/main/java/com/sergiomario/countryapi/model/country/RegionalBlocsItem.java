package com.sergiomario.countryapi.model.country;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RegionalBlocsItem{

	@JsonProperty("otherNames")
	private List<String> otherNames;

	@JsonProperty("acronym")
	private String acronym;

	@JsonProperty("name")
	private String name;

	@JsonProperty("otherAcronyms")
	private List<String> otherAcronyms;

	public List<String> getOtherNames(){
		return otherNames;
	}

	public String getAcronym(){
		return acronym;
	}

	public String getName(){
		return name;
	}

	public List<String> getOtherAcronyms(){
		return otherAcronyms;
	}
}