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
	