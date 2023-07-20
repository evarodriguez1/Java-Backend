package cat.itacademy.barcelonactiva.rodriguez.eva.s05.t01.n01.services;

import cat.itacademy.barcelonactiva.rodriguez.eva.s05.t01.n01.DTO.SucursalDTO;
import cat.itacademy.barcelonactiva.rodriguez.eva.s05.t01.n01.model.Sucursal;
import cat.itacademy.barcelonactiva.rodriguez.eva.s05.t01.n01.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    //metodo para crear sucursal
    public Sucursal agregarSucursal(SucursalDTO sucursalDto){
        Sucursal sucursal = new Sucursal
                (sucursalDto.getNombreSucursal(),sucursalDto.getPaisSucursal(),sucursalDto.verificacionUE(sucursalDto.getPaisSucursal())) ;
        sucursalRepository.save(sucursal);
        return sucursal;
    }

    //metodo para eliminar sucursal buscandola x el id
    public void eliminarSucursal (Long id){
        sucursalRepository.deleteById(id);
    }

    //metodo para guardar la ctualizacion de actualizarSucursal en controller
    public void actualizarSucursal(Sucursal sucursal){
        sucursalRepository.save(sucursal);
    }

    //devuelve una sucursal buscada x id
    public Sucursal getSucursal (Long id) throws Exception {
        Optional<Sucursal> optionalSucursal = sucursalRepository.findById(id);
        return optionalSucursal.orElseThrow(() -> new Exception("No se encuentra la sucursal con id: " + id));
    }

    //sirve en getallsucursales
    public List<Sucursal> getAllSucursal (){
        return sucursalRepository.findAll();
    }

    //metodo para encontrar la sucursal por id, sirve en actualizar y eliminar controller
    public boolean findById(Long id){
        return sucursalRepository.existsById(id);
    }

}
