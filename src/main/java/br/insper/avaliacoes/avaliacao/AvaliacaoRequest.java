package br.insper.avaliacoes.avaliacao;

// Corpo do POST. O email do avaliador NAO vem aqui: sai do token do Auth0.
public record AvaliacaoRequest(String emailAvaliado, String comentario, Integer nota) {
}
