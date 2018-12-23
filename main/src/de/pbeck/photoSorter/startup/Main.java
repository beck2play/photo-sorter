package de.pbeck.photoSorter.startup;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Photo-Sorter needs to be lauched with args <src> <dest>");
            return;
        }
        System.out.println("Launched Photo-Sorter to copy files");
        System.out.println("source: " + args[0]);
        System.out.println("destination: " + args[1]);
    }
}
