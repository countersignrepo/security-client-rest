package com.cs.security.client.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import flexjson.JSONSerializer;

/**
 * Implements a connection handler for JSON messages exchanged with Security via Restful
 * Use the FlexJson Lib, Object Serializable and Deserializable Java to/from JSON
 * @author ramirez
 *
 */
public class FlexJsonConnHandler {
	
	private static final Logger logger = Logger.getLogger(FlexJsonConnHandler.class);

	protected String svcUrl = "";

	/**
	 * Constructor
	 * @param svcUrl the Security´s URL, including the Restful´s Security API context
	 */
	public FlexJsonConnHandler(String svcUrl) {
		this.svcUrl = svcUrl;
	}

	/**
	 * Connects to the Security API service. Will build the JSON message, and will handle the JSON response
	 * @param svcMethod the Security method to be accessed
	 * @param objReq the Object to be parsed to JSON string
	 * @return
	 */
	public String callSvcREST(String svcMethod, Object objReq) {
		try {
			logger.debug("callSvcREST() at URL: " + svcUrl);

			URL url = new URL(svcUrl + "/" + svcMethod);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);

			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			if(objReq != null) {
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setDoOutput(true);
				
				String sendData = new JSONSerializer().exclude("*.class").serialize(objReq);
				logger.info("sending: " + sendData);

				OutputStream os = conn.getOutputStream();
				os.write(sendData.getBytes());
				os.flush();
			}

			if ((conn.getResponseCode() != 200) && (conn.getResponseCode() != 400) ) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
	 
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String data = "";
			String outputLine;
			while ((outputLine = br.readLine()) != null) {
				System.out.println(outputLine);
				data += outputLine;
			}
			return data;

		} catch (Exception ex) {
			logger.error(ex);
			return null;
		}
	}

}
