package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Admin;

@Path("/Admin")
public class AdminService {
	Admin registerObj = new Admin();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAdmin() {
		return registerObj.readAdmin();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAdmin(@FormParam("cName") String cName,
			
	 @FormParam("cAddress") String cAddress,
	 @FormParam("cEmail") String cEmail,
	 @FormParam("cDate") String cDate,
	 @FormParam("pno") String pno)
	{
	 String output = registerObj.insertAdmin(cName, cAddress, cEmail, cDate, pno);
	return output;
	}
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAdmin(String adminData)
	{
	//Convert the input string to a JSON object
	 JsonObject regObject = new JsonParser().parse(adminData).getAsJsonObject();
	//Read the values from the JSON object
	 String cID = regObject.get("cID").getAsString();
	 String cName = regObject.get("cName").getAsString();
	 String cAddress = regObject.get("cAddress").getAsString();
	 String cEmail = regObject.get("cEmail").getAsString();
	 String cDate = regObject.get("cDate").getAsString();
	 String pno = regObject.get("pno").getAsString();
	 String output = registerObj.updateAdmin(cID, cName, cAddress, cEmail, cDate, pno);
	return output;
	} 
	
	