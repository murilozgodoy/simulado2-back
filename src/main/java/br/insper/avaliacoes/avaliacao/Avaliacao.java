package br.insper.avaliacoes.avaliacao;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacao")
@Data
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String emailAvaliador;

    @Column(nullable = false)
    private String emailAvaliado;

    @Column(length = 2000)
    private String comentario;

    @Column(nullable = false)
    private Integer nota;

    @Column(nullable = false)
    private LocalDateTime dataAvaliacao;
}
