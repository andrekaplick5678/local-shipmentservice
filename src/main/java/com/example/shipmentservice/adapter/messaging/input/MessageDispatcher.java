package com.example.shipmentservice.adapter.messaging.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageDispatcher {
  public void onMessage(String event) throws Exception {
    // TODO: to be implemented
    throw new UnsupportedOperationException("not yet implemented");
  }
}
