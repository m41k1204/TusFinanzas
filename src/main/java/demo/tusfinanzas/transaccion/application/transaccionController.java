package demo.tusfinanzas.transaccion.application;

import demo.tusfinanzas.transaccion.domain.Transaccion;
import demo.tusfinanzas.transaccion.domain.TransaccionService;
import demo.tusfinanzas.transaccion.dto.transaccionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaccion")
public class transaccionController {

    @Autowired
    TransaccionService transaccionService;

    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> deleteAll() {
        transaccionService.deleteAllTransacciones();
        return ResponseEntity.noContent().build();
    }



}
