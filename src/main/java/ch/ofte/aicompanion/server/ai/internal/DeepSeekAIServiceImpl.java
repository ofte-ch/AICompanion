package ch.ofte.aicompanion.server.ai.internal;

import ch.ofte.aicompanion.server.ai.dto.PromptRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component("deepSeekAIService")
@RequiredArgsConstructor
public class DeepSeekAIServiceImpl implements DeepSeekAIService {
    private final ChatClient chatClient;

    @Override
    public String getName() {
        return "deepSeek";
    }

    @Override
    public String getResponseFromRequest(PromptRequest request) {
        return chatClient.prompt().user(request.getPrompt()).call().content();
    }
}
