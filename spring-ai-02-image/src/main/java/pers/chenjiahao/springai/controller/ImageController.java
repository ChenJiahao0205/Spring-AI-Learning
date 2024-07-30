package pers.chenjiahao.springai.controller;

import lombok.AllArgsConstructor;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基于Open-Ai的图像模型示例
 * @author ChenJiahao(五条)
 * @date 2024/07/30 23:01:21
 */
@RestController
@AllArgsConstructor
public class ImageController {

    private final OpenAiImageModel openAiImageModel;

    @GetMapping("ai/image")
    public Object image(@RequestParam("message") String message) {
        ImagePrompt imagePrompt = new ImagePrompt(message);
        ImageResponse imageResponse = openAiImageModel.call(imagePrompt);
        return imageResponse.getResult().getOutput().getUrl();
    }

    @GetMapping("ai/image/customConfiguration")
    public Object customConfigurationImage(@RequestParam("message") String message) {
        OpenAiImageOptions openAiImageOptions = OpenAiImageOptions.builder()
                .withQuality("hd") // 高清图片
                .withN(2) // 生成的图像个数
                .withHeight(1024) // 高度
                .withWidth(1024) // 宽度
                .build();
        ImagePrompt imagePrompt = new ImagePrompt(message, openAiImageOptions);
        ImageResponse imageResponse = openAiImageModel.call(imagePrompt);
        return imageResponse.getResult().getOutput().getUrl();
    }
}
