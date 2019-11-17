package de.pbeck.photoSorter.service;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileReaderServiceTest {

    private final File emptyFolder = new File("test/res/emptyFolder");
    private final File folderWithContent = new File("test/res/folderWithContent");

    @Test
    public void scrollEmptyFolder() throws IOException {
        List<File> files = FileReaderService.listFiles(emptyFolder);
        Assert.assertEquals(new ArrayList<Path>(), files);
    }

    @Test
    public void scrollFolder() throws IOException {
        List<File> files = FileReaderService.listFiles(folderWithContent);
        Assert.assertEquals(4, files.size());
    }
}
