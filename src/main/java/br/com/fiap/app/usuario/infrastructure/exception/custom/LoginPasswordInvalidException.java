package br.com.fiap.app.usuario.infrastructure.exception.custom;

public class LoginPasswordInvalidException extends Exception {
    public LoginPasswordInvalidException() {
        super("Credenciais inválidas.");
    }
}