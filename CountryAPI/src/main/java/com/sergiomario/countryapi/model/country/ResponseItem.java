package com.sergiomario.countryapi.model.country;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseItem{

	@JsonProperty("nativeName")
	private String nativeName;

	@JsonProperty("capital")
	private String capital;

	@JsonProperty("demonym")
	private String demonym;

	@JsonProperty("flag")
	private String flag;

	@JsonProperty("alpha2Code")
	private String alpha2Code;

	@JsonProperty("independent")
	private boolean independent;

	@JsonProperty("borders")
	private List<String> borders;

	@JsonProperty("flags")
	private Flags flags;

	@JsonProperty("numericCode")
	private String numericCode;

	@JsonProperty("alpha3Code")
	private String alpha3Code;

	@JsonProperty("topLevelDomain")
	private List<String> topLevelDomain;

	@JsonProperty("cioc")
	private String cioc;

	@JsonProperty("translations")
	private Translations translations;

	@JsonProperty("altSpellings")
	private List<String> altSpellings;

	@JsonProperty("area")
	private double area;

	@JsonProperty("languages")
	private List<LanguagesItem> languages;

	@JsonProperty("subregion")
	private String subregion;

	@JsonProperty("callingCodes")
	private List<String> callingCodes;

	@JsonProperty("regionalBlocs")
	private List<RegionalBlocsItem> regionalBlocs;

	@JsonProperty("gini")
	private double gini;

	@JsonProperty("population")
	private int population;

	@JsonProperty("timezones")
	private List<String> timezones;

	@JsonProperty("name")
	private String name;

	@JsonProperty("region")
	private String region;

	@JsonProperty("latlng")
	private List<Double> latlng;

	@JsonProperty("currencies")
	private List<CurrenciesItem> currencies;

	public String getNativeName(){
		return nativeName;
	}

	public String getCapital(){
		return capital;
	}

	public String getDemonym(){
		return demonym;
	}

	public String getFlag(){
		return flag;
	}

	public String getAlpha2Code(){
		return alpha2Code;
	}

	public boolean isIndependent(){
		return independent;
	}

	public List<String> getBorders(){
		return borders;
	}

	public Flags getFlags(){
		return flags;
	}

	public String getNumericCode(){
		return numericCode;
	}

	public String getAlpha3Code(){
		return alpha3Code;
	}

	public List<String> getTopLevelDomain(){
		return topLevelDomain;
	}

	public String getCioc(){
		return cioc;
	}

	public Translations getTranslations(){
		return translations;
	}

	public List<String> getAltSpellings(){
		return altSpellings;
	}

	public double getArea(){
		return area;
	}

	public List<LanguagesItem> getLanguages(){
		return languages;
	}

	public String getSubregion(){
		return subregion;
	}

	public List<String> getCallingCodes(){
		return callingCodes;
	}

	public List<RegionalBlocsItem> getRegionalBlocs(){
		return regionalBlocs;
	}

	public double getGini(){
		return gini;
	}

	public int getPopulation(){
		return population;
	}

	public List<String> getTimezones(){
		return timezones;
	}

	public String getName(){
		return name;
	}

	public String getRegion(){
		return region;
	}

	public List<Double> getLatlng(){
		return latlng;
	}

	public List<CurrenciesItem> getCurrencies(){
		return currencies;
	}
}