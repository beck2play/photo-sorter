package de.pbeck.photoSorter.service;

import java.io.File;
import java.io.IOException;

public class FileWriterService {

    public void copyFile(File source, File destDir) throws IOException {

    }

    private File getTargetDir(File source, File destDir) {
        //determine correct subfolder in target (by file-creationdate or filename)
        return source;
    }
}
