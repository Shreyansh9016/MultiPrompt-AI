package com.ai.SpringAiDemo.Service;


import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Service
public class PlannerService {
    private final ChatModel chatModel;

    public PlannerService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String generatePlanner(@RequestParam String tasks) {

        var template = """
            You are a smart productivity assistant. Based on the following tasks/goals:
            {tasks}

            Generate a time-blocked daily planner for an 8-hour day. 
            Include time, task, and short descriptions. Format it clearly for readability.
            """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> variables = Map.of("tasks", tasks);
        Prompt prompt = promptTemplate.create(variables);

        return chatModel.call(prompt).getResult().getOutput().getText();
    }
}
