package main;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class CurrencyConversion extends API implements IParseJSON, IUSDtoCAD {
	
	private String json;
	private JSONObject obj;

	public CurrencyConversion(String link) {
		super(link); // Suoer class constructor

	}


	@Override
	public void convert(String currencyID) {
		JSONObject bpiObject = (JSONObject) obj.get("bpi");
		JSONObject converted = (JSONObject) bpiObject.get(currencyID);
		double convertedAmount =  (double) converted.get("rate_float");
		System.out.println(currencyID + " price of one Bitcoin: " + convertedAmount);
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
		
		obj = dataObj;
		JSONObject timeObject = (JSONObject) dataObj.get("time");
		String priceTime = (String) timeObject.get("updated");
		System.out.println("Time of price: " + priceTime);
		
		
	}
	
	public void setJson(String s) {
		json = s;
		
	}
	
	
	public static void main(String[] args) {
		CurrencyConversion usdtoCAD = new CurrencyConversion("https://api.coindesk.com/v1/bpi/currentprice/CAD.json");
		usdtoCAD.setJson(usdtoCAD.getJSON());
		usdtoCAD.parseJSON();
		usdtoCAD.convert("CAD");

		
	}
}
