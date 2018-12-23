package de.pbeck.photoSorter.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for searching and filtering file (-names) in a folder.
 */
public class FileScrollerService {

    /**
     * Lists all files in a folder, including subfolders until maxDepth is reached.
     * @param sourceFolder the sourcefolder
     * @param maxDepth maximum depth of scrolling. maxDepth 0 equals no subfolders
     * @return all files and subfolders in sourceFolder
     */
    public static List<String> listFiles(File sourceFolder, int maxDepth) {
        List<String> allFiles = new ArrayList<>();

        if (sourceFolder != null && sourceFolder.isDirectory()) {
            for (final File file : sourceFolder.listFiles()) {
                if (file.isDirectory() && maxDepth > 0) {
                    listFiles(file, maxDepth - 1);
                } else {
                    allFiles.add(file.getName());
                }
            }
        }

        return allFiles;
    }

    /**
     * Filters a list of filenames by its extensions.
     * If a file does not have an allowed extension, it is being removed from the list.
     * @param files the list of filenames
     * @param allowedFileExtensions the list of allowed extensions
     */
    public void filterFileExtensions(List<String> files, List<String> allowedFileExtensions) {
        for (final String fileName : files) {
            if (!allowedFileExtensions.contains(getFileExtension(fileName))) {
                files.remove(fileName);
            }
        }
    }

    /**
     * Assuming filename is a regular file, this function searches for the last dot in a string
     * and returns everything after it.
     * @param filename the name of a file
     * @return the file extension (aka everything after the last dot)
     */
    private String getFileExtension(String filename) {
        int lastDot = filename.lastIndexOf('.');
        return lastDot != -1 ? filename.substring(lastDot) : filename;
    }
}
