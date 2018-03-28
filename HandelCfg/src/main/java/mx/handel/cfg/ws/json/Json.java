package mx.handel.cfg.ws.json;

import com.google.gson.JsonObject;


public class Json {
	private JsonObject jsonObject;
	public Json(){
		this.jsonObject = new JsonObject();
	}
	public Json(JsonObject jsonObject){
		this.jsonObject = jsonObject;
	}
	public Json add(String property, Number value) {
		jsonObject.addProperty(property,value);
		return this;
	}
	public Json add(String property, String value) {
		jsonObject.addProperty(property,value);
		return this;
	}
	public Json add(String property, Boolean value) {
		jsonObject.addProperty(property,value);
		return this;
	}
	public Json add(String property, Json json) {
		jsonObject.add(property,json.jsonObject);
		return this;
	}
	@Override
	public String toString() {
		return jsonObject.toString();
	}	
}
