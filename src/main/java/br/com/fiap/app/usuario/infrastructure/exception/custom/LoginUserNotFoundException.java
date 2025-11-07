package br.com.fiap.app.usuario.infrastructure.exception.custom;

public class LoginUserNotFoundException extends Exception {
    public LoginUserNotFoundException() {
        super("Credenciais inválidas.");
    }
}