package de.pbeck.photoSorter.config;

public enum PhotoSorterProperty {

    SOURCE_DIRECTORY("source", "/src"),
    DESTINATION_DIRECTORY("target", "/dest"),
    LOG_DIRECTORY("logDir", "/log"),
    MODE("mode", "COPY"),
    ALLOWED_FILE_EXTENSIONS("fileExtensions", ".jpg;.jpeg;.bmp;.png");

    private String key;
    private String defaultValue;

    PhotoSorterProperty(String key, String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
