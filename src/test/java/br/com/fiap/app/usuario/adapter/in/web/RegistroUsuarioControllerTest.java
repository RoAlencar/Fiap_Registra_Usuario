package br.com.fiap.app.usuario.adapter.in.web;

import br.com.fiap.app.usuario.application.dto.request.AtualizaUsuarioDto;
import br.com.fiap.app.usuario.application.dto.response.AtualizaUsuarioResponse;
import br.com.fiap.app.usuario.application.port.AtualizaUsuarioUseCasePort;
import br.com.fiap.app.usuario.application.port.DeletaUsuarioUseCasePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistroUsuarioControllerTest {

    @Mock
    private AtualizaUsuarioUseCasePort atualizaUsuarioUseCasePort;
    @Mock
    private DeletaUsuarioUseCasePort deletaUsuarioUseCasePort;

    @InjectMocks
    private RegistroUsuarioController controller;

    private AtualizaUsuarioDto dto;
    private AtualizaUsuarioResponse responseMock;

    @BeforeEach
    void setup() {
        dto = new AtualizaUsuarioDto();
        dto.setId(1L);
        dto.setNome("João");

        responseMock = new AtualizaUsuarioResponse();
        responseMock.setNome("João");
    }

    @Test
    void deveRetornarOkQuandoAtualizarUsuarioComSucesso() throws Exception {
        // Arrange
        when(atualizaUsuarioUseCasePort.atualizaUsuario(dto)).thenReturn(responseMock);

        // Act
        ResponseEntity<AtualizaUsuarioResponse> response = controller.atualizaUsuario(dto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(responseMock, response.getBody());
        verify(atualizaUsuarioUseCasePort).atualizaUsuario(dto);
    }

    @Test
    void deveChamarDelecaoDeUsuarioComSucesso() throws Exception {
        // Act
        ResponseEntity<Void> response = controller.deletaUsuario(1L);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        verify(deletaUsuarioUseCasePort).deletaUsuario(1L);
    }

}
