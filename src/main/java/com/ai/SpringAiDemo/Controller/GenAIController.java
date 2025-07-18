
package com.ai.SpringAiDemo.Controller;

import com.ai.SpringAiDemo.Service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenAIController {

    private final ChatService chatService;
    private final RecipeService recipeService;
    private final PlannerService plannerService;
    private final RewriterService rewriterService;
    private final ExplainLikeIm5Service explainLikeIm5Service;

    public GenAIController(ChatService chatService,
                           RecipeService recipeService,
                           PlannerService plannerService,
                           RewriterService rewriterService,
                           ExplainLikeIm5Service explainLikeIm5Service) {
        this.chatService = chatService;
        this.recipeService = recipeService;
        this.plannerService = plannerService;
        this.rewriterService = rewriterService;
        this.explainLikeIm5Service = explainLikeIm5Service;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt){
        return chatService.getResponseOptions(prompt);
    }

    @GetMapping("recipe-generator")
    public String getRecipeGenerator(@RequestParam String ingredients,
                                           @RequestParam(defaultValue = "any") String cuisine,
                                           @RequestParam(defaultValue = "any") String dietaryRestrictions){

        return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);
    }

    @GetMapping("/daily-planner")
    public String getPlanner(@RequestParam String tasks) {
        return plannerService.generatePlanner(tasks);
    }

    @GetMapping("/rewrite-text")
    public String rewriteText(@RequestParam String input,
                              @RequestParam(defaultValue = "casual") String tone
                              ){
        return rewriterService.rewriteText(input,tone);
    };

    @GetMapping("/eli5")
    public String explainLikeIm5(@RequestParam String input){
        return explainLikeIm5Service.explainLikeIm5(input);
    }
}
