package cat.itacademy.barcelonactiva.rodriguez.eva.s05.t01.n01.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.List;


public class SucursalDTO {

    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private String nombreSucursal;

    @Getter @Setter
    private String paisSucursal;

    @Getter @Setter
    private String tipoSucursal;

    final List<String> paisesUE = Arrays.asList("alemania", "austria", "belgica", "bulgaria","chipre", "croacia","dinamarca","eslovenia",
            "españa", "estonia","finlandia", "francia", "grecia", "hungria","irlanda","italia", "letonia", "lituania",
            "luxebmurg", "malta","paises bajos","polonia","portugal", "republica checa", "republica eslovaca", "rumania", "suecia");


    //contructores
    public SucursalDTO (){

    }
    public SucursalDTO(String nombre, String pais){
        nombreSucursal = nombre;
        paisSucursal = pais;
        tipoSucursal = verificacionUE(pais);
    }

    //devuelve si el pais es de la UE  o fueraUE
    public String verificacionUE (String pais){

        /*int i = 0;
       boolean encontrado = false;
       String ue = "FueraUE";
        do{
           if( pais.equals(paisesUE.get(i))){
               encontrado = true;
               ue = "UE";
           }
            i++;
       }while (!encontrado && i < paisesUE.size());
       return ue;
         */
        for(String paises : paisesUE) {
            if(pais.equalsIgnoreCase(paises)){
                return "UE";
            }
        }
        return "fueraUE";

    }


}
