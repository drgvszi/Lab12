package org.example;
import java.net.URL;
import java.net.URLClassLoader;

public class CustomLoader extends URLClassLoader {
    public CustomLoader() {
        super(new URL[0], ClassLoader.getSystemClassLoader());
    }
}
