package pers.chenjiahao.springai.controller;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 多模态API示例
 * @author ChenJiahao(五条)
 * @date 2024/08/01 22:01:49
 */
@RestController
@AllArgsConstructor
public class MultiModelController {

    private final ChatModel chatModel;

    @GetMapping("ai/multiModel")
    public Object multiModel(@RequestParam("message") String message) {
        UserMessage userMessage = new UserMessage(message, List.of(new Media(MimeTypeUtils.IMAGE_PNG, new ClassPathResource("/dog.jpg"))));
        OpenAiChatOptions openAiChatOptions = OpenAiChatOptions.builder ()
                .withModel(OpenAiApi.ChatModel.GPT_4_O.getValue())
                .build();
        ChatResponse chatResponse = chatModel.call(new Prompt(userMessage, openAiChatOptions));
        System.out.println(chatResponse);
        return chatResponse.getResult().getOutput().getContent();
    }

    @GetMapping("ai/multiModelInputImage")
    public Object multiModelInputImage(@RequestParam("message") String message, @RequestParam("imageUrl") String imageUrl) {
        UserMessage userMessage = new UserMessage(message, List.of(new Media(MimeTypeUtils.IMAGE_PNG, imageUrl)));
        OpenAiChatOptions openAiChatOptions = OpenAiChatOptions.builder()
                .withModel(OpenAiApi.ChatModel.GPT_4_O.getValue())
                .build();
        ChatResponse chatResponse = chatModel.call(new Prompt(userMessage, openAiChatOptions));
        System.out.println(chatResponse);
        return chatResponse.getResult().getOutput().getContent();
    }
}
