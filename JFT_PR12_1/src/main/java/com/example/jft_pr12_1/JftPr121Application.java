package com.example.jtf_pr12_1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class JftPr121Application {
	static BufferedWriter writer;
	static BufferedReader reader;
	static Path filePath1;
	static Path filePath2;

	@PostConstruct
	public void init() throws IOException {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			String line;
			while ((line = reader.readLine()) != null) {
				writer.write(hashText(line, digest));
				writer.newLine();
			}
			reader.close();
			writer.close();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} finally {
			reader.close();
			writer.close();
		}
	}

	private String hashText(String text, MessageDigest digest) {
		byte[] hashBytes = digest.digest(text.getBytes());
		StringBuilder hashBuilder = new StringBuilder();
		for (byte b : hashBytes) {
			String hex = String.format("%02x", b);
			hashBuilder.append(hex);
		}
		return hashBuilder.toString();
	}

	@PreDestroy
	public void destroy() throws IOException {
		Files.delete(filePath1.toAbsolutePath());
	}

	public static void main(String[] args) throws IOException {
		filePath1 = Paths.get("C:\\Users\\badha\\IdeaProjects\\java4sem\\practice12\\file1.txt");
		filePath2 = Paths.get("C:\\Users\\badha\\IdeaProjects\\java4sem\\practice12\\file2.txt");
		reader = new BufferedReader(new FileReader(filePath1.toString()));
		writer = new BufferedWriter(new FileWriter(filePath2.toString()));

		SpringApplication.run(JftPr121Application.class);
	}

}
