package org.acme;

import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.ApplicationScoped;
import dev.langchain4j.service.SystemMessage;

@RegisterAiService
@ApplicationScoped
public interface NoteAgent {

  @SystemMessage("""
      You are an expert in structuring information.
      Summarize the passed in text in bullet points.
      Use strict Markdown-format (.md).
      Main title should start with #.
      """)
  String summarizeToMarkdown(String text);
}
