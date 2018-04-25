package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import biasbuster.twitter.Card;
import biasbuster.twitter.QueryBuilder;
import biasbuster.twitter.Swipe;
import biasbuster.twitter.User;
import biasbuster.twitter.Watson;


@Path("/services")
public class Services {
	@GET
	@Path("/user/{handle}/{gender}")
	@Produces("application/json")
	public static User addUser(@PathParam("handle")String handle,@PathParam("gender")String gender){
	
		User user = null;
		try {
			user = QueryBuilder.getUserByHandle(handle);
		
		if(user==null){
			user=new User();
			user.setHandle(handle);
			user.setGender(gender);
			user=QueryBuilder.addUser(user);
			List<String> preferences=Watson.getPreferences(handle);
			for(String preference:preferences){
				Card card=new Card();
				card.setHandle(handle);
				card.setAnswer(gender);
				card.setPreference(preference);
				QueryBuilder.addCard(card);
			}
			return user;
				
		}
		else {
			
			return user;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/cards/{handle}/{gender}")
	@Produces("application/json")
	public static List<Card>getCards(@PathParam("handle")String handle,@PathParam("gender")String gender){
		System.out.println(handle+" "+gender);
		User user = null;
		try {
			user = QueryBuilder.getUserByHandle(handle);
		
		if(user==null){
			user=new User();
			user.setHandle(handle);
			user.setGender(gender);
			System.out.println(user.getHandle());
			user=QueryBuilder.addUser(user);
			List<String> preferences=Watson.getPreferences(handle);
			for(String preference:preferences){
				Card card=new Card();
				card.setHandle(handle);
				card.setAnswer(gender);
				card.setPreference(preference);
				QueryBuilder.addCard(card);
			}
				
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		try {
			return QueryBuilder.getCards(handle);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/swipe/{card_id}/{handle}/{score}")
	@Produces("application/json")
	public static Swipe addSwipe(@PathParam("card_id") Long card_id,@PathParam("score")int score,@PathParam("handle")String handle){
		try {
			Swipe swipe=new Swipe();
			swipe.setCard_id(card_id);
			swipe.setScore(score);
			swipe.setSwiper(handle);
			return QueryBuilder.addSwipe(swipe);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
