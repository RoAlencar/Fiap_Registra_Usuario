package br.com.fiap.app.usuario.application.usecase;

import br.com.fiap.app.usuario.application.dto.request.CriarUsuarioDto;
import br.com.fiap.app.usuario.application.dto.response.CriarUsuarioResponse;
import br.com.fiap.app.usuario.application.port.UsuarioRepositoryPort;
import br.com.fiap.app.usuario.domain.Endereco;
import br.com.fiap.app.usuario.domain.Usuario;
import br.com.fiap.app.usuario.domain.enums.TipoUsuario;
import br.com.fiap.app.usuario.infrastructure.exception.custom.AddressRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.DuplicateEmailException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.EmailRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.NameRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.PasswordRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.TipoUsuarioRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserRequiredException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CriarUsuarioUseCaseTest {

    private UsuarioRepositoryPort usuarioRepositoryPort;
    private ModelMapper modelMapper;
    private CriarUsuarioUseCase criarUsuarioUseCase;

    @BeforeEach
    void setUp() {
        usuarioRepositoryPort = mock(UsuarioRepositoryPort.class);
        modelMapper = new ModelMapper();
        criarUsuarioUseCase = new CriarUsuarioUseCase(usuarioRepositoryPort, modelMapper);
    }

    @Test
    void deveCriarUsuarioComSucesso() throws Exception {
        CriarUsuarioDto dto = new CriarUsuarioDto();
        dto.setNome("João");
        dto.setTipo(TipoUsuario.USUARIO);
        dto.setEmail("joao@email.com");
        dto.setLogin("joao123");
        dto.setSenha("senha123");
        dto.setEndereco(new Endereco());

        Usuario usuarioSalvo = Usuario.builder()
                .nome(dto.getNome())
                .tipo(dto.getTipo())
                .email(dto.getEmail())
                .login(dto.getLogin())
                .senha(dto.getSenha())
                .endereco(dto.getEndereco())
                .build();

        when(usuarioRepositoryPort.findByEmail(dto.getEmail())).thenReturn(Optional.empty());
        when(usuarioRepositoryPort.save(any(Usuario.class))).thenReturn(usuarioSalvo);

        CriarUsuarioResponse response = criarUsuarioUseCase.criarUsuario(dto);

        assertNotNull(response);
        assertEquals("João", response.getNome());
        assertEquals(TipoUsuario.USUARIO, response.getTipo());
    }

    @Test
    void deveLancarUserRequiredExceptionQuandoDtoForNulo() {
        assertThrows(UserRequiredException.class, () -> criarUsuarioUseCase.criarUsuario(null));
    }

    @Test
    void deveLancarNameRequiredExceptionQuandoNomeNulo() {
        CriarUsuarioDto dto = new CriarUsuarioDto();
        dto.setTipo(TipoUsuario.USUARIO);
        dto.setEmail("email@email.com");
        dto.setSenha("senha");
        dto.setEndereco(new Endereco());
        assertThrows(NameRequiredException.class, () -> criarUsuarioUseCase.criarUsuario(dto));
    }

    @Test
    void deveLancarTipoUsuarioRequiredExceptionQuandoTipoNulo() {
        CriarUsuarioDto dto = new CriarUsuarioDto();
        dto.setNome("João");
        dto.setEmail("email@email.com");
        dto.setSenha("senha");
        dto.setEndereco(new Endereco());
        assertThrows(TipoUsuarioRequiredException.class, () -> criarUsuarioUseCase.criarUsuario(dto));
    }

    @Test
    void deveLancarEmailRequiredExceptionQuandoEmailNulo() {
        CriarUsuarioDto dto = new CriarUsuarioDto();
        dto.setNome("João");
        dto.setTipo(TipoUsuario.USUARIO);
        dto.setSenha("senha");
        dto.setEndereco(new Endereco());
        assertThrows(EmailRequiredException.class, () -> criarUsuarioUseCase.criarUsuario(dto));
    }

    @Test
    void deveLancarDuplicateEmailExceptionQuandoEmailJaExiste() {
        CriarUsuarioDto dto = new CriarUsuarioDto();
        dto.setNome("João");
        dto.setLogin("joao123");
        dto.setTipo(TipoUsuario.USUARIO);
        dto.setEmail("email@email.com");
        dto.setSenha("senha");
        dto.setEndereco(new Endereco());

        when(usuarioRepositoryPort.findByEmail("email@email.com"))
                .thenReturn(Optional.of(new Usuario()));

        assertThrows(DuplicateEmailException.class,
                () -> criarUsuarioUseCase.criarUsuario(dto));

        verify(usuarioRepositoryPort, never()).save(any(Usuario.class));
    }

    @Test
    void deveLancarPasswordRequiredExceptionQuandoSenhaNula() {
        CriarUsuarioDto dto = new CriarUsuarioDto();
        dto.setNome("João");
        dto.setLogin("login");
        dto.setTipo(TipoUsuario.USUARIO);
        dto.setEmail("email@email.com");
        dto.setEndereco(new Endereco());
        assertThrows(PasswordRequiredException.class, () -> criarUsuarioUseCase.criarUsuario(dto));
    }

    @Test
    void deveLancarAddressRequiredExceptionQuandoEnderecoNulo() {
        CriarUsuarioDto dto = new CriarUsuarioDto();
        dto.setNome("João");
        dto.setLogin("abc");
        dto.setTipo(TipoUsuario.USUARIO);
        dto.setEmail("email@email.com");
        dto.setSenha("senha");
        dto.setEndereco(null);
        assertThrows(AddressRequiredException.class, () -> criarUsuarioUseCase.criarUsuario(dto));
    }
}
