package com.example.shipmentservice.adapter.messaging.input;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PollingMessage implements CommandLineRunner {

  private final MessageDispatcher messageDispatcher;

  public PollingMessage(MessageDispatcher messageDispatcher) {
    this.messageDispatcher = messageDispatcher;
  }

  @Override
  public void run(String... args) throws Exception {
    File inputFile = new File("events/input.msg.txt");

    log.info("Waiting for input files like {}", inputFile.getAbsolutePath());

    while (!Thread.currentThread().isInterrupted()) {
      Thread.sleep(1_000L);
      if (inputFile.exists() && inputFile.canRead()) {
        log.info("File found: {}", inputFile.getAbsolutePath());

        processFile(inputFile);

        if (inputFile.delete()) {
          log.info("File {} deleted", inputFile.getAbsolutePath());
        }
      }
    }

    log.info("CommandLineRunner stopped");
  }

  private void processFile(File inputFile) {
    try {
      String content = Files.readString(Path.of(inputFile.toURI()));
      messageDispatcher.onMessage(content);
    } catch (Exception e) {
      log.error("ERROR: {}", e.getMessage(), e);
    }
  }
}
