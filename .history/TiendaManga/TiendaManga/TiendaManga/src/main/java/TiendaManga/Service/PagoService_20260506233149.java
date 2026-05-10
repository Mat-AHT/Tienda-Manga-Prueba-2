package TiendaManga.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TiendaManga.Model.Pago;
import TiendaManga.Repository.PagoRepository;
import TiendaManga.Repository.MangaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private MangaRepository mangaRepository;

    public List<Pago> obtenerTodos(){
        return pagoRepository.findAll();
    }

    public Pago buscarPorId(Integer id){
        return pagoRepository.findById(id).orElseThrow(() -> new O();
        );
    }

    public Pago guardarPago(Pago pago){
        return pagoRepository.save(pago);
    }

    



}
