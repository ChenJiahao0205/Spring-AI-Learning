package pers.chenjiahao.springai.controller;

import lombok.AllArgsConstructor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 访问本地Ollama运行模型的API示例
 * @author ChenJiahao(五条)
 * @date 2024/08/04 20:56:10
 */
@RestController
@AllArgsConstructor
public class OllamaController {

    public final OllamaChatModel ollamaChatModel;

    @GetMapping("ai/ollama")
    public Object ollama(@RequestParam("message") String message) {
        return ollamaChatModel.call(message);
    }
}
