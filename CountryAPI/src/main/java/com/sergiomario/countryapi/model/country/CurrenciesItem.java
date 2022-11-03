package com.sergiomario.countryapi.model.country;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrenciesItem{

	@JsonProperty("symbol")
	private String symbol;

	@JsonProperty("code")
	private String code;

	@JsonProperty("name")
	private String name;

	public String getSymbol(){
		return symbol;
	}

	public String getCode(){
		return code;
	}

	public String getName(){
		return name;
	}
}