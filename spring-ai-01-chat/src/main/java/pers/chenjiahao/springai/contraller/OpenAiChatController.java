package pers.chenjiahao.springai.contraller;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基本Open-Ai的聊天模型示例
 * @author ChenJiahao(五条)
 * @date 2024/07/28 22:59:11
 */
@RestController
@AllArgsConstructor
public class OpenAiChatController {

    private final OpenAiChatModel openAiChatModel;

    @GetMapping("ai/chat")
    public String chat(@RequestParam("message") String message) {
        // 默认使用的GPT-4o，由于我的API-KEY不支持GPT-4o模型，所以在配置文件设置为3.5
        return openAiChatModel.call(message);
    }

    @GetMapping("ai/chat/customConfiguration")
    public String customConfigurationChat(@RequestParam("message") String message) {
        // 手动设置模型
        OpenAiChatOptions openAiChatOptions = OpenAiChatOptions.builder()
                .withModel(OpenAiApi.ChatModel.GPT_3_5_TURBO_1106.getValue())
                .build();

        Prompt prompt = new Prompt(message, openAiChatOptions);
        ChatResponse call = openAiChatModel.call(prompt);
        return call.getResult().getOutput().getContent();
    }

}
