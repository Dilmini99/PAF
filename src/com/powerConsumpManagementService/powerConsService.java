package com.powerConsumpManagementService;


import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.powerConsumpManagementModel.powerConsModel;

@Path("/powerCons")
public class powerConsService {
	
	powerConsModel powConsModel = new powerConsModel();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPowCons() {
		return powConsModel.readData();
	}
	
	@POST
	@Path("/insertPowConsDetails")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPowConsData(@FormParam("user_name") String userName, @FormParam("email") String userEmail, @FormParam("month") String month, @FormParam("unit") int unit) {
		String output = powConsModel.insertData(userName, userEmail, month, unit);
		
		return output;
	}
	
	@PUT
	@Path("/updateEntry")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePowConsData(String powConsData) {
		
		JsonObject powConsObject = new JsonParser().parse(powConsData).getAsJsonObject();
		
		String uID = powConsObject.get("user_id").getAsString();
		String userName = powConsObject.get("user_name").getAsString();
		String userEmail = powConsObject.get("email").getAsString();
		String month = powConsObject.get("user_id").getAsString();
		String unit = powConsObject.get("unit").getAsString();
		
		String output = powConsModel.updateData(uID, userName, userEmail, month, unit);
		
		return output;
	}
	
	@DELETE
	@Path("/removeEntry")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePowConsData(String unitData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(unitData, "", Parser.xmlParser());
		
		//Read the value from the element <userID>
		String uId = doc.select("user_id").text();
		String output = powConsModel.deleteData(uId);
		
	return output;
	}
	
	@GET
	@Path("/{user_id}")
	@Produces(MediaType.TEXT_HTML)
	public String getBillById(@PathParam("user_id") int userID){
		return powConsModel.getDataByMonthAndId(userID);
	}

}
