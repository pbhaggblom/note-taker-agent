package org.acme;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Incoming;

public class FileHandler {

  @ConfigProperty(name = "file.directory")
  String directory;

  @Incoming("summaries-in")
  public void writeToFile(String note) {

    String fileName = generateFileName();
    Path path = Path.of(directory + fileName);

    try {
      Files.writeString(path, note, StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Invalid path");
    }
  }

  public String generateFileName() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    String timestamp = LocalDateTime.now().format(formatter);
    return "summary_" + timestamp + ".md";
  }

}
