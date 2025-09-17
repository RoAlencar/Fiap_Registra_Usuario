package br.com.fiap.app.usuario.adapter.in.web;

import br.com.fiap.app.usuario.application.dto.request.AtualizaUsuarioDto;
import br.com.fiap.app.usuario.application.dto.request.CriarUsuarioDto;
import br.com.fiap.app.usuario.application.dto.response.AtualizaUsuarioResponse;
import br.com.fiap.app.usuario.application.dto.response.BuscaUsuarioResponse;
import br.com.fiap.app.usuario.application.dto.response.CriarUsuarioResponse;
import br.com.fiap.app.usuario.application.port.AtualizaUsuarioUseCasePort;
import br.com.fiap.app.usuario.application.port.BuscaUsuarioUseCasePort;
import br.com.fiap.app.usuario.application.port.CriarUsuarioUseCasePort;
import br.com.fiap.app.usuario.application.port.DeletaUsuarioUseCasePort;
import br.com.fiap.app.usuario.infrastructure.exception.custom.AddressRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.DuplicateEmailException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.EmailRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.ModificaUsuarioException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.NameRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.PasswordRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserNotFoundException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserRequiredException;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/usuario")
@RequiredArgsConstructor
public class RegistroUsuarioController {

    private final AtualizaUsuarioUseCasePort atualizaUsuarioUseCasePort;
    private final BuscaUsuarioUseCasePort buscaUsuarioUseCasePort;
    private final CriarUsuarioUseCasePort criarUsuarioUseCasePort;
    private final DeletaUsuarioUseCasePort deletaUsuarioUseCasePort;

    @GetMapping
    public ResponseEntity<List<BuscaUsuarioResponse>> buscaTodosUsuarios() {
        log.info("[Usuario - Busca Todos os Usuarios] Iniciando processo.");
        return new ResponseEntity<>(buscaUsuarioUseCasePort.buscaTodosUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<BuscaUsuarioResponse> buscaUsuarioPorNome(@PathVariable String name) throws UserNotFoundException {
        log.info("[Usuario - Busca Por Nome] Iniciando processo.");
        BuscaUsuarioResponse buscaUsuarioREsponseDto = buscaUsuarioUseCasePort.buscaUsuarioPorNome(name);
        return new ResponseEntity<>(buscaUsuarioREsponseDto,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CriarUsuarioResponse> criarUsuario(@RequestBody CriarUsuarioDto dto) throws AddressRequiredException, DuplicateEmailException, EmailRequiredException,
            NameRequiredException, PasswordRequiredException, UserRequiredException{
        log.info("[Usuario - Criar Usuario] Iniciando processo.");
        return new ResponseEntity<>(criarUsuarioUseCasePort.criarUsuario(dto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AtualizaUsuarioResponse> atualizaUsuario(@RequestBody AtualizaUsuarioDto dto) throws UserNotFoundException, ModificaUsuarioException {
        log.info("[Usuario - Atualiza Usuario] Iniciando processo.");
        AtualizaUsuarioResponse atualizaUsuarioResponse = atualizaUsuarioUseCasePort.atualizaUsuario(dto);
        return new ResponseEntity<>(atualizaUsuarioResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaUsuario(@PathVariable Long id) throws UserNotFoundException {
        log.info("[Usuario - Deleta Usuario] Iniciando processo.");
        deletaUsuarioUseCasePort.deletaUsuario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
