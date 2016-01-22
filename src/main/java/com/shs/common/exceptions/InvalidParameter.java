/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shs.common.exceptions;

/**
 *
 * @author Nabil
 */
public class InvalidParameter extends Exception {

    private String message;

    public InvalidParameter() {
        this.message = "Invalid Parameter.";
    }

    public InvalidParameter(String msg) {
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
