/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.channel.action;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author tringapps
 */
public class GatewayInteraction {

	/**
	 * used to perform the given action with the corresponding gateway
	 * 
	 * @param requiredAction
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws JSONException
	 */
	public static Action newPerformAction(Action requiredAction, String url)
			throws IOException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException, JSONException {


		boolean isInputBodyUtilized = false;
		String requiredURL = url;
		if (requiredAction.getPath() != null) {
			requiredURL = requiredURL + requiredAction.getPath();
		}

		if (requiredAction.getMethod().equalsIgnoreCase("GET") || requiredURL.equalsIgnoreCase("https://api.home.nest.com/oauth2/access_token")) {
			if (!requiredAction.getInputBody().isEmpty()
					&& requiredAction.getInputBody().size() != 0
					&& requiredAction.getInputBody() != null) {
				
				
				StringBuilder builderForQueryString = new StringBuilder();
				builderForQueryString.append("?");
				for (Map.Entry<String, Object> queryStringPair : requiredAction
						.getInputBody().entrySet()) {
					builderForQueryString.append(queryStringPair.getKey() + "="
							+ queryStringPair.getValue());
					builderForQueryString.append("&");
				}
				String queryString = builderForQueryString.toString();

				requiredURL = requiredURL
						+ queryString.substring(0,
								builderForQueryString.length() - 1);

			}
			isInputBodyUtilized = true;
		}
		
		Logger.getLogger(GatewayInteraction.class.getSimpleName()).warning("requiredURL : "+requiredURL);

		URL urlObject = new URL(requiredURL);
		// opening urlConnection with help of URlObject
		HttpURLConnection connection = (HttpURLConnection) urlObject
				.openConnection();
		// setting request property for method and changed into uppercase

		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod(requiredAction.getMethod().toUpperCase());
		connection.setConnectTimeout(30000);

		Map<String, String> inputHeader = requiredAction.getInputHeader();

		Logger.getLogger("LOG").warning("inputHeader : "+inputHeader);
		

			// used for iterating number values in header
			for (Map.Entry<String, String> headerSet : inputHeader.entrySet()) {
				// getting keys and values of input header
				connection.setRequestProperty(headerSet.getKey(),
						headerSet.getValue());
			}
		

		// checking the input body is empty or o and null
		if (!requiredAction.getInputBody().isEmpty()
				&& requiredAction.getInputBody().size() != 0
				&& requiredAction.getInputBody() != null
				&& !isInputBodyUtilized) {

			Logger.getLogger(GatewayInteraction.class.getSimpleName()).warning("getInputBody : "+requiredAction.getInputBody());
			// System.out.println("hi1");
			DataOutputStream payLoadWriter = new DataOutputStream(
					connection.getOutputStream());

			boolean isSmartSwitch = false;

			if (requiredAction.getMethod().equalsIgnoreCase("PUT")) {
				// Nest
			} else if (requiredAction.getInputBody().size() == 1) {
				/**
				 * for smart switch body : turnOn (this alone will come)
				 */
				for (String key : requiredAction.getInputBody().keySet()) {
					if (key.equals(requiredAction.getInputBody().get(key))) {
						// System.out.println("hi");
						// opening file adding URL in data output stream
						payLoadWriter.writeBytes(key);
						// clearing data output stream
						payLoadWriter.flush();
						// closing data output stream
						payLoadWriter.close();
						isSmartSwitch = true;
					}
				}
			} else {
				// for fibaro
			}

			if (!isSmartSwitch) {
				JSONObject jsonObject = new JSONObject();

				for (Map.Entry<String, Object> mapForJson : requiredAction
						.getInputBody().entrySet())
					jsonObject.put(mapForJson.getKey(), mapForJson.getValue());

				String finalContent = jsonObject.toString();
				// System.out.println(jsonObject.toString());
				// opening file adding URL in data output stream
				payLoadWriter.writeBytes(finalContent);
				// clearing data output stream
				payLoadWriter.flush();
				// closing data output stream
				payLoadWriter.close();
			}

		}

		// assigning response code into int
		int responseCode = connection.getResponseCode();
		// System.out.println("responseCode: "+responseCode);
		// creating object for ouput stream
		BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		// defining string variable

		String inputLine;
		// creating string buffer class object
		StringBuffer response = new StringBuffer();
		// System.out.println(response);
		// appending the response

		while ((inputLine = in.readLine()) != null) {

			response.append(inputLine);

		}
		// closing response
		in.close();

		// print result		
		String jsonMap = response.toString();

		Map<String, Object> outputBodyMap = new HashMap<String, Object>();

		if (jsonMap != null && !jsonMap.equalsIgnoreCase("")) {

			if (jsonMap.substring(0, 1).equalsIgnoreCase("[")) {

				JSONArray jsonArr = new JSONArray(jsonMap);

				for (int i = 0; i < jsonArr.length(); i++) {

					JSONObject jsonObject = jsonArr.getJSONObject(i);

					Iterator<String> nameItr = jsonObject.keys();

					// Map<String, Object> deviceArray = new HashMap<String,
					// Object>();
					JSONObject deviceArray = new JSONObject();

					while (nameItr.hasNext()) {
						String name = nameItr.next();
						deviceArray.put(name, jsonObject.get(name));
					}

					outputBodyMap.put(i + "", deviceArray);

				}

			} else if (!jsonMap.startsWith("{") && !jsonMap.startsWith("[")) {
				outputBodyMap.put("response", jsonMap);
				// System.out.println(jsonMap);

			} else {

				JSONObject objectToIterate = new JSONObject(jsonMap);

				Iterator<String> key = objectToIterate.keys();
				while (key.hasNext()) {
					String next = key.next();
					outputBodyMap.put(next, objectToIterate.get(next));
				}

			}

		}

		outputBodyMap.put("responseCode", responseCode);

		Logger.getLogger(GatewayInteraction.class.getSimpleName()).warning("outputBodyMap : "+outputBodyMap);

		
		requiredAction.setOutputBody(outputBodyMap);
		return requiredAction;

	}
}