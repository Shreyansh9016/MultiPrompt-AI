package com.ai.SpringAiDemo.Service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExplainLikeIm5Service {

    public final ChatModel chatModel;

    public ExplainLikeIm5Service(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String explainLikeIm5(String input) {
        String template = """
                Explain the following concept like I'm 5 years old:
                {input}
                """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> variables = Map.of("input", input);
        Prompt prompt = promptTemplate.create(variables);

        return chatModel.call(prompt).getResult().getOutput().getText();

    }
}
