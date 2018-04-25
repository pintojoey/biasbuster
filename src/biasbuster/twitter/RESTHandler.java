package biasbuster.twitter;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RESTHandler {

	public static String createRequest(String input_url) throws IOException{
		
		URL url = new URL(input_url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

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
