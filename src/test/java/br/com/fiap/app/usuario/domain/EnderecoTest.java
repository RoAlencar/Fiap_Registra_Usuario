package br.com.fiap.app.usuario.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EnderecoTest {

    @Test
    public void EnderecoTest(){

        Endereco endereco = new Endereco();
        endereco.setLogradouro("Av. Paulista");
        endereco.setNumero("1000");
        endereco.setComplemento("N/A");
        endereco.setCep("N/A");

        assertThat(endereco.getLogradouro()).isEqualTo("Av. Paulista");
        assertThat(endereco.getNumero()).isEqualTo("1000");
        assertThat(endereco.getComplemento()).isEqualTo("N/A");
        assertThat(endereco.getCep()).isEqualTo("N/A");
    }
}
