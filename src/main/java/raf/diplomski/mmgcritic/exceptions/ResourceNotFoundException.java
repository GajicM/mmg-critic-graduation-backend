package raf.diplomski.mmgcritic.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Object o){
        super("Resource "+ o.toString()+  "not found");
    }
}
