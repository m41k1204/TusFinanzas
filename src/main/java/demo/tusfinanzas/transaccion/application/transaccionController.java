package demo.tusfinanzas.transaccion.application;

import demo.tusfinanzas.transaccion.domain.Transaccion;
import demo.tusfinanzas.transaccion.domain.TransaccionService;
import demo.tusfinanzas.transaccion.dto.transaccionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaccion")
public class transaccionController {

    @Autowired
    TransaccionService transaccionService;



    @PostMapping("/nueva")
    public ResponseEntity<Void> newTransaccion(@RequestBody transaccionDto transaccionDto) {
        transaccionService.newTransaccion(transaccionDto);
        return ResponseEntity.ok().build();
    }


}
