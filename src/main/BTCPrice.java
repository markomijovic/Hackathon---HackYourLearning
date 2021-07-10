package main;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BTCPrice extends API implements IParseJSON, ICAD{
	/**
	 * USD Bitcoin price
	 */
	private static double usdPrice;
	/**
	 * Non USD Bitcoin price
	 */
	private static double convertedPrice;
	/**
	 * JSON text received from Bitcoin API
	 */
	private String json;
	/**
	 * JSON object parsed from JSON text
	 */
	private JSONObject obj;

	public BTCPrice(String link) {
		super(link); // Super class constructor

	}
	/**
	 * Parse JSON text objects to access specific price information/data
	 */
	@Override
	public void parseJSON() {
		
		JSONParser parse = new JSONParser();
		
		try {
			obj = (JSONObject) parse.parse(json);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JSONObject timeObject = (JSONObject) obj.get("time");
		String priceTime = (String) timeObject.get("updated");

		JSONObject bpiObject = (JSONObject) obj.get("bpi");
		JSONObject usdObject = (JSONObject) bpiObject.get("USD");
		usdPrice = (double) usdObject.get("rate_float");
		//System.out.println("USD price of one Bitcoin: " + usdAmount);

	}

	/**
	 * Retrieves bitcoin price in user specified currency
	 * @param currencyID is the ID of the currency
	 */
	@Override
	public void convert(String currencyID) {
		JSONObject bpiObject = (JSONObject) obj.get("bpi");
		JSONObject converted = (JSONObject) bpiObject.get(currencyID);
		convertedPrice =  (double) converted.get("rate_float");
		//System.out.println(currencyID + " price of one Bitcoin: " + convertedAmount);
	}
	/**
	 * @param s JSON text retrieved from the bitcoin API
	 */
	public void setJson(String s) {
		json = s;
		
	}

	/**
	 * Accesses API, retrieves JSON file, parses JSON file into the bit coin cost for non USD currency
	 * @return Bitcoin price in non USD currency (double data type)
	 */
	public static double getConvertedPrice(){
		BTCPrice convertedBitcoin = new BTCPrice("https://api.coindesk.com/v1/bpi/currentprice/CAD.json");
		convertedBitcoin.setJson(convertedBitcoin.getJSON());
		convertedBitcoin.parseJSON();
		convertedBitcoin.convert("CAD"); // Can be changed to whatever you want

		return convertedPrice;
	}

	/**
	 * Accesses API, retrieves JSON file, parses JSON file into the bit coin cost for USD currency
	 * @return Bitcoin price in USD currency (double data type)
	 */
	public static double getUSDPrice(){
		BTCPrice usdBitcoin = new BTCPrice("https://api.coindesk.com/v1/bpi/currentprice/CAD.json");
		usdBitcoin.setJson(usdBitcoin.getJSON());
		usdBitcoin.parseJSON();

		return usdPrice;
	}

}
