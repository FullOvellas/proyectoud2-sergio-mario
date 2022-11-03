package com.sergiomario.countryapi.model.country;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Translations{

	@JsonProperty("br")
	private String br;

	@JsonProperty("de")
	private String de;

	@JsonProperty("pt")
	private String pt;

	@JsonProperty("ja")
	private String ja;

	@JsonProperty("hr")
	private String hr;

	@JsonProperty("fa")
	private String fa;

	@JsonProperty("it")
	private String it;

	@JsonProperty("fr")
	private String fr;

	@JsonProperty("hu")
	private String hu;

	@JsonProperty("nl")
	private String nl;

	@JsonProperty("es")
	private String es;

	public String getBr(){
		return br;
	}

	public String getDe(){
		return de;
	}

	public String getPt(){
		return pt;
	}

	public String getJa(){
		return ja;
	}

	public String getHr(){
		return hr;
	}

	public String getFa(){
		return fa;
	}

	public String getIt(){
		return it;
	}

	public String getFr(){
		return fr;
	}

	public String getHu(){
		return hu;
	}

	public String getNl(){
		return nl;
	}

	public String getEs(){
		return es;
	}
}