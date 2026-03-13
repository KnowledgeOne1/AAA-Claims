package com.aaa.service.claim.config;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;


@Configuration
public class FirebaseConfig {
	
	@Value("${file.firebase.config}")
	private String firebaseConfigFile;
	
	
	@PostConstruct
	public void firebaseInitialization() throws IOException {
		Resource resource = new ClassPathResource(firebaseConfigFile);
		InputStream serviceAccount = resource.getInputStream();
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();
		if (FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
		}
	}
}
