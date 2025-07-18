package com.ai.SpringAiDemo.Service;


import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RewriterService {

    private final ChatModel chatModel;

    public RewriterService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }
    public String rewriteText(String input , String tone) {

        var template = """
                Rewrite the following text in a {tone} tone:
                {input}
                """;

        PromptTemplate pT = new PromptTemplate(template);
        Map<String , Object> map = Map.of(
          "tone" , tone,
          "input" , input
        );

        Prompt prompt = pT.create(map);
        return chatModel.call(prompt).getResult().getOutput().getText();
    }
}
