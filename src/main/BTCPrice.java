package main;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BTCPrice extends API implements IParseJSON{
	
	private String json;

	public BTCPrice(String link) {
		super(link); // Suoer class constructor

	}

	@Override
	public void parseJSON() {
		
		JSONParser parse = new JSONParser();
		JSONObject dataObj = null;
		
		try {
			dataObj = (JSONObject) parse.parse(json);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JSONObject timeObject = (JSONObject) dataObj.get("time");
		String priceTime = (String) timeObject.get("updated");
		System.out.println("Time of price: " + priceTime);
		
		JSONObject bpiObject = (JSONObject) dataObj.get("bpi");
		JSONObject usdObject = (JSONObject) bpiObject.get("USD");
		double usdAmount = (double) usdObject.get("rate_float");
		System.out.println("USD price of one Bitcoin: " + usdAmount);
		

	} 
	
	public void setJson(String s) {
		json = s;
		
	}
	
	public static void main(String[] args) {
		BTCPrice usdBitcoin = new BTCPrice("https://api.coindesk.com/v1/bpi/currentprice/CAD.json");
		usdBitcoin.setJson(usdBitcoin.getJSON());
		usdBitcoin.parseJSON();
		
	}
	
	
	
}
