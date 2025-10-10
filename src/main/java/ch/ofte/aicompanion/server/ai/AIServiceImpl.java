package ch.ofte.aicompanion.server.ai;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import ch.ofte.aicompanion.server.ai.dto.PromptRequest;
import ch.ofte.aicompanion.server.ai.internal.AIServiceExecutor;

@Service("aiService")
public class AIServiceImpl implements AIService {
    private static final Logger logger = LogManager.getLogger(AIServiceImpl.class);
    private ApplicationContext applicationContext;

    private final Map<String, AIServiceExecutor> REGISTERED_CHAT_MODEL_EXECUTORS = new HashMap<>();

    public AIServiceImpl(@Autowired ApplicationContext applicationContext) {
        try {
            for (AIServiceExecutor executor : applicationContext.getBeanProvider(AIServiceExecutor.class)) {
                REGISTERED_CHAT_MODEL_EXECUTORS.put(executor.getName(), executor);
            }
        } catch (NullPointerException e) {
            logger.error("Something went wrong on registering model executors...", e);
        }
    }

    private AIServiceExecutor getModelExecutor(String modelName) {
        if (REGISTERED_CHAT_MODEL_EXECUTORS.containsKey(modelName)) {
            return REGISTERED_CHAT_MODEL_EXECUTORS.get(modelName);
        }

        if (REGISTERED_CHAT_MODEL_EXECUTORS.containsKey("default")) {
            return REGISTERED_CHAT_MODEL_EXECUTORS.get("default");
        }

        throw new NoModelAvailableException();
    }

    @Override
    public String getResponseFromRequest(PromptRequest request) {
        return getModelExecutor(request.getModel()).getResponseFromRequest(request);
    }

    @Override
    public Flux<?> getStreamingResponseFromRequest(PromptRequest request) {
        return getModelExecutor(request.getModel()).getStreamingResponseFromRequest(request);
    }

    @Override
    public Flux<?> getStreamingResponseFromRequestAsChunks(PromptRequest request) {
        return getModelExecutor(request.getModel()).getStreamingResponseFromRequestAsChunks(request);
    }
}
