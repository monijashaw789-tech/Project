package com.example.aibot.AIBOT.controller;
import com.example.aibot.AIBOT.model.ChatRequest;
import com.example.aibot.AIBOT.service.FileService;
import com.example.aibot.AIBOT.service.OpenAIService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ChatController {

    @Autowired
    private FileService fileService;

    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/upload")
    public Map<String, String> upload(
            @RequestParam("file") MultipartFile file
    ) throws Exception {

        String fileId = fileService.saveFile(file);

        return Map.of(
                "status", "success",
                "fileId", fileId
        );
    }

    @PostMapping("/chat")
    public Map<String, String> chat(
            @RequestBody ChatRequest request
    ) throws Exception {

        String fileContent =
                fileService.readFileContent(request.getFileId());

        String aiResponse =
                openAIService.askAI(
                        fileContent,
                        request.getQuestion()
                );

        return Map.of(
                "answer", aiResponse
        );
    }
}