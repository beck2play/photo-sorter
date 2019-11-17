package de.pbeck.photoSorter.startup;

import de.pbeck.photoSorter.config.PropertyFactory;
import de.pbeck.photoSorter.service.FileTransferService;

import java.util.Arrays;
import java.util.List;

public class Main {

    private static final String optionRun = "run";
    private static final String optionConfig = "config";

    private static final List<String> startParameters = Arrays.asList(optionRun, optionConfig);

    public static void main(String[] args) {
        //System.out.println("Photo-Sorter args: <run, conf> [")
        System.out.println("Launching Photo-Sorter to copy files...");

        checkConfig();

        //TODO: options -> check/modify config, run program
        //TODO: read/override config with args
        //System.out.println("source: " + args[0]);
        //System.out.println("destination: " + args[1]);

        run();
    }

    private static void run() {
        FileTransferService.transferFiles();
    }

    private static void checkConfig() {
        System.out.println("Trying to read config from " + PropertyFactory.getConfigPath() + " ...");
        System.out.println("Found PhotoSorterPropertys : " + PropertyFactory.getProperties());
    }
}
