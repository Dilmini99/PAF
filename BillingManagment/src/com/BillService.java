package com;

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

import model.Bill;

@Path("/Bill")
public class BillService {
	
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBills() {
		String out = "";
		
		Bill newBill = new Bill();
		
		out = newBill.readBill();
		
		return out;
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBill(
	 @FormParam("name") String name,
	 @FormParam("phone") String phone,
	 @FormParam("units") String units,
	 @FormParam("bill") String bill)
	{
		String out = "";
		
		try {
			Bill newBill = new Bill();
			
			out = newBill.billInsert(name, phone, Float.parseFloat(units), Float.parseFloat(bill));
		}
		catch (Exception e) {
			out = "Failed";
		}
		return out;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(String billData)
	{
		String output = "";
		
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());
		 
		//Read the value from the element <ID>
		 String bid = doc.select("bid").text();
		 
		 Bill newBill = new Bill();
		 
		 output = newBill.deleteBill(bid);
		
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(String billData)
	{
		String output = "";
		 //Convert the input string to a JSON object
		 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());
		
		 //Read the values from the JSON object
		 String bid = doc.select("bid").text();
		 String name = doc.select("name").text();
		 String phone = doc.select("phone").text();
		 String units = doc.select("units").text();
		 String bill = doc.select("bill").text();
		 
		 System.out.print(bid);
		 
		 Bill newBill = new Bill();
		 
		 output = newBill.billUpdate(bid, name, phone, Float.parseFloat(units), Float.parseFloat(bill));
		 return output;
	} 
	
	
}
