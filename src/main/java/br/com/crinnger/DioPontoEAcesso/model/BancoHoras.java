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
@Entity(name = "BANCO_HORAS")
@Audited
public class BancoHoras {

    public  BancoHoras(Movimentacao movimentacao,Long idBancoHoras){
        this.bancoHorasId= new BancoHorasId(idBancoHoras,movimentacao);
    }
    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public  class BancoHorasId implements Serializable {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idBancoHoras;
        @ManyToOne
        private Movimentacao movimentacao;
    }

    @EmbeddedId
    private BancoHorasId bancoHorasId;

    private LocalDate dataTrabalhada;
    private Integer quantidadeHoras;
    private Integer saldoHoras;
}
