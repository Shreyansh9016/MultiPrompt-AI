package com.ai.SpringAiDemo.Service;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String getResponse(String prompt){
        return chatModel.call(prompt);
    }

public String getResponseOptions(String prompt) {
    Prompt chatPrompt = new Prompt(
            List.of(new UserMessage(prompt)),
            OllamaOptions.builder()
                    .temperature(0.4)
                    .build()
    );

    ChatResponse response = chatModel.call(chatPrompt);
    Message assistantMessage = response.getResult().getOutput();
    return assistantMessage.getText();
}
}
