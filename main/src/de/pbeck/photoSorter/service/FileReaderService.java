package de.pbeck.photoSorter.service;

import de.pbeck.photoSorter.config.PhotoSorterProperty;
import de.pbeck.photoSorter.config.PropertyFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for searching and filtering files (by fileextensions) in a folder and its subfolders.
 */
public class FileReaderService {

    /**
     * Lists all files in a folder and its subfolders.
     * @param sourceFolder the sourcefolder
     * @return all files and subfolders in sourceFolder
     */
    public static List<File> listFiles(File sourceFolder) throws IOException {

        List<File> allFiles = new ArrayList<>();

        Files.walkFileTree(sourceFolder.toPath(), new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                allFiles.add(path.toFile());
                return FileVisitResult.CONTINUE;
            }
        });

        return filterFileExtensions(allFiles);
    }

    /**
     * Filters a list of filenames by its extensions.
     * If a file does not have an allowed extension, it is being removed from the list.
     * @param files the list of filenames
     */
    private static List<File> filterFileExtensions(List<File> files) {
        List<String> allowedFileExtensions = getAllowedFileExtensions();
        return files.stream()
                .filter(filename -> allowedFileExtensions.contains(getFileExtension(filename)))
                .collect(Collectors.toList());
    }

    private static List<String> getAllowedFileExtensions() {
        String allowedFileExtensionsAsString = PropertyFactory.readProperty(PhotoSorterProperty.ALLOWED_FILE_EXTENSIONS);
        return Arrays.asList(allowedFileExtensionsAsString.split(";"));
    }

    private static String getFileExtension(File file) {
        String filename = file.getName();
        int lastDot = filename.lastIndexOf('.');
        return lastDot != -1 ? filename.substring(lastDot) : filename;
    }
}
