package com.sergiomario.countryapi.model.country;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Flags{

	@JsonProperty("svg")
	private String svg;

	@JsonProperty("png")
	private String png;

	public String getSvg(){
		return svg;
	}

	public String getPng(){
		return png;
	}
}