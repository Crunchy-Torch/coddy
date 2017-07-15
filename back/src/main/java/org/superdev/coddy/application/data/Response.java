package org.superdev.coddy.application.data;

import java.io.Serializable;

/**
 * this class is used when a error is throwing and we must inform the client about this error
 */
public class Response implements Serializable {

    public static final String INTERNAL_ERROR = "internal error";

    private static final long serialVersionUID = -836303086510094002L;

    private String message;

    public Response(){
        // this blank constructor is needed by the library jackson
    }

    /**
     *
     * @param message : the error message
     */
    public Response(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
