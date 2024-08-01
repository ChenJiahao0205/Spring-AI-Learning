package pers.chenjiahao.springai.controller;

import lombok.AllArgsConstructor;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基于Open-Ai的音频翻译文本示例
 * @author ChenJiahao(五条)
 * @date 2024/08/01 21:00:57
 */
@RestController
@AllArgsConstructor
public class TranscriptionController {

    private final OpenAiAudioTranscriptionModel openAiAudioTranscriptionModelModel;

    @GetMapping("ai/transcription")
    public Object transcription() {
        Resource resource = new ClassPathResource("bohemianrhapsody.mp3");
        return openAiAudioTranscriptionModelModel.call(resource);
    }
}
