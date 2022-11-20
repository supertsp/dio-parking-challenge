package br.com.tiagopedroso.dioparkingchallenge.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class WrongPasswordException extends BusinessException {

    public WrongPasswordException(String username) {
        super("Invalid password for login \"%s\"", username);
    }

}
