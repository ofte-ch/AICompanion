package ch.ofte.aicompanion.server.ai.internal;

import ch.ofte.aicompanion.server.ai.dto.PromptRequest;

public interface AIServiceExecutor {
    String getResponseFromRequest(PromptRequest request);
    String getName();
}
