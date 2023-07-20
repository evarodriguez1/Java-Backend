package cat.itacademy.barcelonactiva.rodriguez.eva.s05.t02.n01.S05T02N01RodriguezEva.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="jugadores")
public class Jugador implements Serializable {
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_jugador",unique = true)
    private Long id_jugador;

    @Getter @Setter
    @Column(name="nombre")
    private String nombre;

    @Getter @Setter
    @Column(name="email")
    private String email;

    @Getter @Setter
    @Column(name="date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Getter @Setter
    @Column(name="porcentaje_exito")
    private Double porcentaje_Exito;

    @Getter @Setter
    @Column(name = "num_jugadas")
    private int num_jugadas;

    @Getter @Setter
    @OneToMany(mappedBy = "jugador")
    private List<Tirada> lanzamientos = new ArrayList<Tirada>();

    public Jugador() {
    }

    public Jugador(String nombre, String email){
        this.nombre = nombre;
        this.email = email;
        this.date = new Date();
        this.porcentaje_Exito = 0.0;
        this.lanzamientos = null;

    }

    public double porcentajeExito (List<Tirada> lanzamientos){
        double partidesTotals= lanzamientos.size();
        double victories= 0;
        ;

        for(int i=0;i<partidesTotals;i++){
            if(lanzamientos.get(i).ganador()){
                victories += 1;
            }
        }

        double percentatge= victories/partidesTotals*100;

        return percentatge;
    }
}
