package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Customer;

@Path("/Customer")
public class CustomerService {
	Customer registerObj = new Customer();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomer() {
		return registerObj.readCustomer();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(@FormParam("cName") String cName,
			
	 @FormParam("cAddress") String cAddress,
	 @FormParam("cEmail") String cEmail,
	 @FormParam("cDate") String cDate,
	 @FormParam("pno") String pno)
	{
	 String output = registerObj.insertCustomer(cName, cAddress, cEmail, cDate, pno);
	return output;
	}
	