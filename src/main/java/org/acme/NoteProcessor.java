package org.acme;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;

public class NoteProcessor {

  @Inject
  NoteAgent agent;

  @Incoming("note-requests-in")
  @Outgoing("summaries-out")
  @Blocking
  public String process(String request) {
    return agent.summarizeToMarkdown(request);
  }
}
