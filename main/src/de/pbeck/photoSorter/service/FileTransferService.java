package de.pbeck.photoSorter.service;

import de.pbeck.photoSorter.config.PhotoSorterProperty;
import de.pbeck.photoSorter.config.PropertyFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FileTransferService {

    public static void transferFiles() {
        File source = Paths.get(PropertyFactory.readProperty(PhotoSorterProperty.SOURCE_DIRECTORY)).toFile();
        File target = Paths.get(PropertyFactory.readProperty(PhotoSorterProperty.DESTINATION_DIRECTORY)).toFile();
        checkReadWriteAccess(source, target);

        FileWriterService fileWriterService = new FileWriterService();
        List<File> filesToTransfer = new ArrayList<>();
        AtomicInteger successfulTransferedFiles = new AtomicInteger(0);

        //read
        try {
            filesToTransfer.addAll(FileReaderService.listFiles(source));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //write
        filesToTransfer.forEach(file -> {
            try {
                fileWriterService.copyFile(file, target);
                successfulTransferedFiles.incrementAndGet();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        System.out.println("Succesfully transfered " + successfulTransferedFiles.get() + "/" + filesToTransfer.size() + "files.");
    }

    private static void checkReadWriteAccess(File sourceFolder, File destFolder) {
        if (!sourceFolder.isDirectory() || !sourceFolder.canRead()) {
            throw new RuntimeException("The sourcefolder " + sourceFolder.toString() + " is either no directory or not readable.");
        }

        if (!destFolder.isDirectory() || !destFolder.canWrite()) {
            throw new RuntimeException("The targetfolder " + destFolder.toString() + " is either no directory or not writeable.");
        }
    }
}
