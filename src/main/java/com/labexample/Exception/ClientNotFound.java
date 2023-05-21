package com.labexample.Exception;

public class ClientNotFound extends RuntimeException {
    public ClientNotFound(String message) { super(message);
    }
}
