package br.insper.avaliacoes.avaliacao;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    // Mesmo namespace da Action do Auth0 (guia do professor)
    private static final String NAMESPACE = "https://social-insper.com/";

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    // Somente ADMIN
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Avaliacao cadastrar(@AuthenticationPrincipal Jwt jwt,
                               @RequestBody AvaliacaoRequest request) {
        List<String> roles = jwt.getClaimAsStringList(NAMESPACE + "roles");
        if (roles == null || !roles.contains("ADMIN")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas ADMIN pode cadastrar avaliacoes");
        }
        String emailAvaliador = jwt.getClaimAsString(NAMESPACE + "email");
        return avaliacaoService.cadastrar(request, emailAvaliador);
    }

    // ADMIN e USER: basta estar autenticado (garantido pelo WebSecurityConfig)
    @GetMapping
    public List<Avaliacao> listar() {
        return avaliacaoService.listar();
    }
}
