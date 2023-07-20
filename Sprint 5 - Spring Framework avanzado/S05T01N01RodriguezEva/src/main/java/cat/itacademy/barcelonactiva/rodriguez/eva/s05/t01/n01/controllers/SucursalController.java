package cat.itacademy.barcelonactiva.rodriguez.eva.s05.t01.n01.controllers;


import cat.itacademy.barcelonactiva.rodriguez.eva.s05.t01.n01.DTO.SucursalDTO;
import cat.itacademy.barcelonactiva.rodriguez.eva.s05.t01.n01.model.Sucursal;
import cat.itacademy.barcelonactiva.rodriguez.eva.s05.t01.n01.services.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarSucursal(@RequestBody SucursalDTO sucursalDto){
    return ResponseEntity.status(HttpStatus.CREATED).body(sucursalService.agregarSucursal(sucursalDto));
    }

    @GetMapping("/sucursal/{id}")
    public ResponseEntity<?> getSucursal (@PathVariable Long id) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(sucursalService.getSucursal(id));

    }

    @GetMapping("/todasSucursales")
    public List<Sucursal> getAllSucursal (){
        return sucursalService.getAllSucursal();
    }

    @PutMapping ("/actualizar/{id}")
    public String actualizarSucursal(@PathVariable Long id,@RequestBody SucursalDTO sucursalDto) throws Exception {

        if(!sucursalService.findById(id)){
            return  "No se encuentra la sucursal con id: "+id;
        }else {
            Sucursal sucursalNew = sucursalService.getSucursal(id);
            if (sucursalDto.getNombreSucursal() != null) {
                sucursalNew.setNombreSucursal(sucursalDto.getNombreSucursal());
            }
            if (sucursalDto.getPaisSucursal() != null) {
                sucursalNew.setPaisSucursal(sucursalDto.getPaisSucursal());
                sucursalNew.setTipoSucursal(sucursalDto.verificacionUE(sucursalDto.getPaisSucursal()));
            }
            sucursalService.actualizarSucursal(sucursalNew);
        }
        return "Sucursal actualizada";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarSucursal (@PathVariable Long id){
        if(!sucursalService.findById(id)){
            return "No se encuentra la sucursal con id: "+id;
        }else{
            sucursalService.eliminarSucursal(id);
        }
        return "la sucursal con id "+id+" se ha eliminado";
    }

}
