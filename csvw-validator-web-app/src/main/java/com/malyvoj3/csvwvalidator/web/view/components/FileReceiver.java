package com.malyvoj3.csvwvalidator.web.view.components;

import com.vaadin.flow.component.upload.Receiver;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Data
@Slf4j
public class FileReceiver implements Receiver {

    private File file;
    private String fileName;

    @Override
    public OutputStream receiveUpload(String filename,
                                      String mimeType) {
        // Create upload stream
        FileOutputStream fos = null;
        try {
            // Open the file for writing.
            file = File.createTempFile("tmp", null, new File("tmp"));
            file.deleteOnExit();
            fileName = filename;
            fos = new FileOutputStream(file);
        } catch (Exception ex) {
            log.error("Error during creating UPLOAD file.");
            return null;
        }
        // Return the output stream to write to
        return fos;
    }
}
