package ch.ofte.aicompanion.server.ai.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.deepseek.DeepSeekAssistantMessage;
import org.springframework.ai.deepseek.DeepSeekChatOptions;
import org.springframework.ai.deepseek.api.DeepSeekApi;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import ch.ofte.aicompanion.server.ai.dto.PromptRequest;

@Component("deepSeekAIService")
@RequiredArgsConstructor
@Validated
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

    @Override
    public Flux<String> getStreamingResponseFromRequest(PromptRequest request) {
        return chatClient.prompt().user(request.getPrompt()).stream().content();
    }

    private Flux<String> toChunkCR(Flux<ChatResponse> tokenFlux) {
        final int chunkSize = 500;
        return Flux.create(sink -> {
            StringBuilder buffer = new StringBuilder();
            tokenFlux.subscribe(
                    cr -> {
                        DeepSeekAssistantMessage assistantMessage = (DeepSeekAssistantMessage) cr.getResult().getOutput();
                        buffer.append(assistantMessage.getReasoningContent());
                        if (buffer.length() >= chunkSize) {
                            sink.next(buffer.toString());
                            buffer.setLength(0);
                        }
                    },
                    sink::error,
                    () -> {
                        if (!buffer.isEmpty()) {
                            sink.next(buffer.toString());
                        }
                        sink.complete();
                    }
            );
        });
    }

    private Flux<String> toChunk(Flux<String> tokenFlux) {
        final int chunkSize = 500;
        return Flux.create(sink -> {
            StringBuilder buffer = new StringBuilder();
            tokenFlux.subscribe(
                    token -> {
                        buffer.append(token);
                        if (buffer.length() >= chunkSize) {
                            sink.next(buffer.toString());
                            buffer.setLength(0);
                        }
                    },
                    sink::error,
                    () -> {
                        if (!buffer.isEmpty()) {
                            sink.next(buffer.toString());
                        }
                        sink.complete();
                    }
            );
        });
    }

    private static final DeepSeekChatOptions promptOptionsReasoner = DeepSeekChatOptions.builder().model(DeepSeekApi.ChatModel.DEEPSEEK_REASONER).build();
    @Override
    public Flux<String> getStreamingResponseFromRequestAsChunks(PromptRequest request) {
        return chatClient.prompt(new Prompt(new UserMessage(request.getPrompt()), promptOptionsReasoner))
                            .user(request.getPrompt())
                            .stream().chatResponse().transform(this::toChunkCR);
    }
}
