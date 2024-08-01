package pers.chenjiahao.springai.controller;

import lombok.AllArgsConstructor;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 基于Open-Ai的文本转语音示例
 * @author ChenJiahao(五条)
 * @date 2024/08/01 21:31:27
 */
@RestController
@AllArgsConstructor
public class TtsController {

    private final OpenAiAudioSpeechModel openAiAudioSpeechModel;

    @GetMapping("ai/tts")
    public Object tts(@RequestParam("message") String message) {
        byte[] bytes = openAiAudioSpeechModel.call(message);
        save("D:\\IDEA\\WorkSpace\\Spring-AI-Learning\\spring-ai-04-tts\\src\\main\\resources\\test.mp3", bytes);
        return "";
    }

    public void save(String filePath, byte[] byteArray){
        // 将字节数组写入文件
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

