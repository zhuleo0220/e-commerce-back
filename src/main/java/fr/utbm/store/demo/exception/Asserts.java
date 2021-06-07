package fr.utbm.store.demo.exception;


import fr.utbm.store.demo.api.IErrorCode;


public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
