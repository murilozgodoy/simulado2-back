package br.insper.avaliacoes.avaliacao;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public Avaliacao cadastrar(AvaliacaoRequest request, String emailAvaliador) {
        if (emailAvaliador == null || emailAvaliador.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token sem o email do avaliador");
        }
        if (request.emailAvaliado() == null || request.emailAvaliado().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email do avaliado e obrigatorio");
        }
        if (request.nota() == null || request.nota() < 0 || request.nota() > 10) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nota deve estar entre 0 e 10");
        }

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setEmailAvaliador(emailAvaliador);
        avaliacao.setEmailAvaliado(request.emailAvaliado());
        avaliacao.setComentario(request.comentario());
        avaliacao.setNota(request.nota());
        avaliacao.setDataAvaliacao(LocalDateTime.now());
        return avaliacaoRepository.save(avaliacao);
    }

    public List<Avaliacao> listar() {
        return avaliacaoRepository.findAll();
    }
}
