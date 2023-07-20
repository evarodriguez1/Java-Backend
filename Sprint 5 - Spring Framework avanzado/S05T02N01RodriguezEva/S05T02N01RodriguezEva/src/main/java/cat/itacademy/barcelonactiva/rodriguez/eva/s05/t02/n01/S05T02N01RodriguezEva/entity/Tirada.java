package cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tiradas")
public class Tirada {

    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jugador")
    private Long id;

    @Getter @Setter
    @Column(name = "dado1")
    private int dado1;

    @Getter @Setter
    @Column(name = "dado2")
    private int dado2;

    @Getter @Setter
    @Column(name = "num_victorias")
    private int victorias;

    @Getter @Setter
    @Column(name = "num_derrotas")
    private int derrotas;

    @Getter @Setter
    @ManyToOne()
    @JoinColumn (name = "id_jugador", nullable = false, insertable = false,updatable = false)
    private Jugador jugador;

    @Getter @Setter
    private String tiro;

    public Tirada() {

    }

    public String tiraLosDados(Long id_jugador) {
        dado1 = (int)(Math.random()*(6-1+1)+1);
        setDado1(dado1);
        dado2 = (int)(Math.random()*(6-1+1)+1);
        setDado1(dado1);

        if (dado1+dado2 == 7) {
            tiro = "Victoria";
            setTiro(tiro);
            victorias =+ 1;
        } else{
            tiro = "no has sumado 7";
            setTiro(tiro);
            derrotas =+ 1;

        }
        return tiro;

    }

    public boolean ganador(){
        boolean ganador = false;
        int resultado = dado1 + dado2;
        if(resultado==7){
            ganador=true;
        }
        return ganador;
    }
}
