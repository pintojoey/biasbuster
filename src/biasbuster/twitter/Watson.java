package biasbuster.twitter;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Watson {
	public static List<String> getPreferences(String handle) {
		List<String>consumptions=new ArrayList<String>();
		try {
			JSONObject data=new JSONObject();
			data.put("content", getTwitterProfile(handle));
			JSONObject watson=new JSONObject(getWatsonPersonality(data.toString()));
			JSONArray preference_categories=watson.getJSONArray("consumption_preferences");
		
			for(int i=0;i<preference_categories.length();i++){
				JSONArray preferences=preference_categories.getJSONObject(i).getJSONArray("consumption_preferences");
				
				for(int j=0;j<preferences.length();j++){
					JSONObject preference=preferences.getJSONObject(j);

					String consumption_name=preference.getString("name");
					double consumption_score=preference.getDouble("score");
					if(consumption_score>=0.5)consumptions.add(consumption_name);
				}
			}
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return consumptions;
	}
	public static String getTwitterProfile(String username){
		String statuses="";
		try {

			JSONArray obj=new JSONArray(getStatuses(username));
			for(int i=0;i<obj.length();i++){
				statuses+=obj.getJSONObject(i).getString("text");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statuses;
	}
	public static String getStatuses(String username) throws IOException{

		URL url = new URL("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name="+username);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty ("Authorization", "Bearer AAAAAAAAAAAAAAAAAAAAAHbB5gAAAAAANtTxGMLFASU44U7q5FEj7auFIxI%3DpbgFVWzZfTORLCJiFTEhBDuyGEt7n60VXdZDXiPE1s1Pm0goJh");
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		StringBuilder output=new StringBuilder();
		String buffer;
		while ((buffer = br.readLine()) != null) {
			output.append(buffer);
		}

		conn.disconnect();

		return output.toString();


	}

	public static String getWatsonPersonality(String profile) throws IOException{
		System.out.println(profile);
		URL url = new URL("https://gateway.watsonplatform.net/personality-insights/api/v3/profile?version=2017-10-13&consumption_preferences=true&raw_scores=true");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty ("Authorization", "Basic YjJmNDYyZDQtNjVmMC00OGNjLTk3ODAtZDBmMjI2ZDg4MmZjOlZjR28xWW0xcDdpZQ==");
		conn.setRequestProperty ("Content-Type", "text/plain");
		conn.setRequestProperty ("Accept", "application/json");

		conn.setDoOutput( true );
		conn.setInstanceFollowRedirects( false );
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(profile);
		wr.flush();
		wr.close();


		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		StringBuilder output=new StringBuilder();
		String buffer;
		while ((buffer = br.readLine()) != null) {
			output.append(buffer);
		}

		conn.disconnect();

		return output.toString();
	}
}
