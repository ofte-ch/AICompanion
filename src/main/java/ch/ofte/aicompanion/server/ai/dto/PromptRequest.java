package ch.ofte.aicompanion.server.ai.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class PromptRequest {
    private String prompt;
    @NotNull
    private String model;
}
