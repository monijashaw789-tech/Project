package com.example.aibot.AIBOT.service;
import org.springframework.stereotype.Service;

@Service
public class OpenAIService {

    public String askAI(String fileContent, String question) {

        // Convert to lowercase for matching
        question = question.toLowerCase();
        fileContent = fileContent.toLowerCase();

        // Java
        if(question.contains("java")
                && fileContent.contains("java")) {

            return "Java is a programming language used for backend, web and enterprise applications.";
        }

        // Spring Boot
        if(question.contains("spring")
                && fileContent.contains("spring")) {

            return "Spring Boot is a Java framework used for building REST APIs and backend applications.";
        }

        // Backend
        if(question.contains("backend")
                && fileContent.contains("backend")) {

            return "Backend handles server-side logic, APIs and database operations.";
        }

        // Position
        if(question.contains("position")
                && fileContent.contains("position")) {

            return "CSS positions include static, relative, absolute, fixed and sticky.";
        }

        // Sticky
        if(question.contains("sticky")
                && fileContent.contains("sticky")) {

            return "Sticky position sticks the element when scrolling.";
        }

        // Absolute
        if(question.contains("absolute")
                && fileContent.contains("absolute")) {

            return "Absolute position moves element relative to nearest positioned parent.";
        }

        // Fixed
        if(question.contains("fixed")
                && fileContent.contains("fixed")) {

            return "Fixed position keeps the element fixed on screen during scrolling.";
        }

        // Summary
        if(question.contains("summary")) {

            return "The uploaded file contains notes related to frontend development, CSS positions and web concepts.";
        }

        // Default response
        return "Answer found from uploaded file:\n\n"
                + fileContent.substring(
                        0,
                        Math.min(fileContent.length(), 300)
                );
    }
}