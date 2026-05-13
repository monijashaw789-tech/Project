package com.example.aibot.AIBOT.service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {

	    private final String UPLOAD_DIR = "uploads/";

	    public String saveFile(MultipartFile file) throws Exception {

	        File folder = new File(UPLOAD_DIR);

	        if(!folder.exists()) {
	            folder.mkdirs();
	        }

	        String fileId =
	                UUID.randomUUID()
	                + "_"
	                + file.getOriginalFilename();

	        Path path = Paths.get(UPLOAD_DIR + fileId);

	        Files.copy(file.getInputStream(), path);

	        return fileId;
	    }

	    public String readFileContent(String fileId) throws Exception {

	        Path path = Paths.get(UPLOAD_DIR + fileId);

	        // TXT
	        if(fileId.endsWith(".txt")) {

	            return Files.readString(path);
	        }

	        // PDF
	        else if(fileId.endsWith(".pdf")) {

	            PDDocument document =
	                    PDDocument.load(path.toFile());

	            PDFTextStripper stripper =
	                    new PDFTextStripper();

	            String text = stripper.getText(document);

	            document.close();

	            return text;
	        }

	        // Other files
	        return "Unsupported file type.";
	    }
	}
