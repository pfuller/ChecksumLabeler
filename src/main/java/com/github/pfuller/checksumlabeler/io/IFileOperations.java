package com.github.pfuller.checksumlabeler.io;

import java.io.File;

/**
 * Operations we want to perform on the file system to create the MD5 keys
 * 
 * @author fullerp
 */
public interface IFileOperations {
    /**
     * Gets a list of files within a directory and subdirectories
     * 
     * @param directory     Directory to return files contained within
     * @return              Array of files
     */
    public File[] getFiles(File directory);
    
    /**
     * Generates a unique file name to put the Key in
      * 
     * @param file      Original File to create a key for
     * @param keyType   Key Type to be used in new file name
     * @return          New file to hold the key
     */
    public File getKeyNameFile(File file, String keyType);
    
    /**
     * Writes the key to file
     * 
     * @param keyFile   File to write the key to
     * @param key       The key contents
     */
    public void writeKey(File keyFile, String key);
}
