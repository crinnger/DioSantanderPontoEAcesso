package br.com.crinnger.DioPontoEAcesso.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "MOVIMENTACAO")
@Audited
public class Movimentacao {

    public Movimentacao(Usuario Usuario, Long idMovimento){
        this.movimentacaoId=new MovimentacaoId(idMovimento,Usuario);
    }
    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class MovimentacaoId implements Serializable{
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idMovimento;
        @ManyToOne
        private Usuario usuario;
    }

    @EmbeddedId
    private MovimentacaoId movimentacaoId;

    private LocalDate entrada;
    private LocalDate saida;

    private Integer periodoPermanencia;

    @ManyToOne
    private Ocorrencia ocorrencia;

    @ManyToOne
    private Calendario calendario;

}
