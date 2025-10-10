package ch.ofte.aicompanion.server.ai.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PromptRequest {
    private String prompt;
    private String model;
}
