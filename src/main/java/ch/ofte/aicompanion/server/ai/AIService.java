package ch.ofte.aicompanion.server.ai;

import ch.ofte.aicompanion.server.ai.dto.PromptRequest;
import reactor.core.publisher.Flux;

public interface AIService {
    String getResponseFromRequest(PromptRequest request);
    Flux<?> getStreamingResponseFromRequest(PromptRequest request);
    Flux<?> getStreamingResponseFromRequestAsChunks(PromptRequest request);
}
