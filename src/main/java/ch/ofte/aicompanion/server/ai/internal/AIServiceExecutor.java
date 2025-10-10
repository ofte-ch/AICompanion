package ch.ofte.aicompanion.server.ai.internal;

import ch.ofte.aicompanion.server.ai.dto.PromptRequest;
import reactor.core.publisher.Flux;

public interface AIServiceExecutor {
    String getResponseFromRequest(PromptRequest request);
    Flux<String> getStreamingResponseFromRequest(PromptRequest request);
    Flux<String> getStreamingResponseFromRequestAsChunks(PromptRequest request);
    String getName();
}
