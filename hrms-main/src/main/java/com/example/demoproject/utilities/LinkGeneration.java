package com.example.demoproject.utilities;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class LinkGeneration {
	
	@Value("${server.address}")
	private String serverAddress;
	
	@Value("${server.servlet.context-path}")	
	private String path; // Base URL of your API
	
	@Value("${server.port}")
	private int serverPort;
	
	public String generateLink(String contextPath) {
						
			URI uri = null;
			try {
				
				uri = new URI("http", null, serverAddress, serverPort, contextPath, null, null);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			String baseUrl = uri.toString();
			        
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl)
	                .path(path);
	        
	        String link = builder.toUriString();
	                
	        return link;
	    }

}
