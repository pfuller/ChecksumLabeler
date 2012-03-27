package com.github.pfuller.checksumlabeler.io;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;       // Requires java 6
import java.util.UUID;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author fullerp
 */
public class FileSystemOps implements IFileOperations{
    /**
     * Merges two arrays of the same type
     * 
     * @param <T>       Array Type
     * @param first     First Array
     * @param second    Second Array
     * @return          Merged Array
     */
    private static <T> T[] mergeArrays(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
    
    /**
     * Gets a list of files within a directory and subdirectories
     * 
     * @param directory     Directory to return files contained within
     * @return              Array of files
     */
    public File[] getFiles(File directory){
        File[] files = directory.listFiles();
        
        File[] subDirs = directory.listFiles(
            new FileFilter(){
                @Override
                public boolean accept(File f){  
                    return f.isDirectory();  
                }  
                public String getDescription(){  
                    return "Directories Only";  
                }  
            });
        
        // Recursively call the function to get the sub directories
        for(File dirToSearch: subDirs){
            files = mergeArrays(files, getFiles(dirToSearch));
        }
        
        // Return full file list
        return files;
    }
    
    /**
     * Generates a unique file name to put the Key in
      * 
     * @param file      Original File to create a key for
     * @param keyType   Key Type to be used in new file name
     * @return          New file to hold the key
     */
    public File getKeyNameFile(File file, String keyType){
        File newKeyFile = new File(file.getAbsolutePath() + keyType);
        
        if(createNewFile(newKeyFile)){
            return newKeyFile;
        } else {
            // File exists, try a different name
            do {
                // Try creating a file using a UUID
                newKeyFile = new File(file.getAbsolutePath() + UUID.randomUUID() + keyType);
            } while (!createNewFile(newKeyFile)); // Try again if it failed
            
            return newKeyFile;
        }
    }
    
    /**
     * Implementation of create new file that ignores IO Errors
     * 
     * @param file  File to create
     * @return      Whether the file could be created (if it did not exist)
     */
    private boolean createNewFile(File file){
        try {
            return file.createNewFile();
        } catch (IOException e){
            // Ignore all errors and return false
            return false;
        }
    }
    
    /**
     * Writes the key to file
     * 
     * @param keyFile   File to write the key to
     * @param key       The key contents
     */
    public void writeKey(File keyFile, String key){
        throw new NotImplementedException();
    }
}
