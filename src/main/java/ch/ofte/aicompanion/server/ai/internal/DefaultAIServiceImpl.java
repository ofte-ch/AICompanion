package ch.ofte.aicompanion.server.ai.internal;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class DefaultAIServiceImpl extends DeepSeekAIServiceImpl {

    public DefaultAIServiceImpl(ChatClient chatClient) {
        super(chatClient);
    }

    @Override
    public String getName() {
        return "default";
    }
}
