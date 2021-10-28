package service;

public interface LoadableManager {

    void parse(String[] fileString);
    String toFileString();
    String getFileLabel();
}
