package ch.ofte.aicompanion.server.ai;

import ch.ofte.aicompanion.server.ai.dto.PromptRequest;

public interface AIService {
    String getResponseFromRequest(PromptRequest request);
}
