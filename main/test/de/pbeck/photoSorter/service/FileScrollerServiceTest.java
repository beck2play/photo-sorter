package de.pbeck.photoSorter.service;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileScrollerServiceTest {

    private final File emptyFolder = new File("test/res/emptyFolder");

    @Test
    public void scrollEmptyFolder() {
        List<String> files = FileScrollerService.listFiles(emptyFolder, 0);
        Assert.assertEquals(new ArrayList<String>(), files);
    }
}
