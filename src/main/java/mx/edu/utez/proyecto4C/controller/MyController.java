package mx.edu.utez.proyecto4C.controller;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto4C.controller.dto.RequestBodyDTO;
import mx.edu.utez.proyecto4C.controller.dto.RequestCalculadoraDTO;
import mx.edu.utez.proyecto4C.service.MyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin({"*"}) // todos los origenes
@RestController
@RequestMapping("my-services")
public class MyController {

    private final MyService service;

    // Inyección de dependencias
    public MyController(MyService service){
        this.service = service;
    }

    @GetMapping
    public String miPrimerServicio(){
        return "Hello word";
    }

    @GetMapping("/servicio2")
    public String servicio2() {
        return "segundo servicio";
    }

    @PostMapping
    public String servicio3(){
        return "Este es el servicio 3";
    }

    @GetMapping("/path/{id}")
    public String pathvariable(@PathVariable String id){
        return "El path variable es: "+ id;
    }

    @PostMapping("/body")
    public ResponseEntity<RequestBodyDTO> body(@RequestBody @Valid RequestBodyDTO payload){

        System.out.println(payload.getNombre());
        System.out.println(payload.getEdad());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(payload);
    }

    @PostMapping ("/calculadora")
    public double calculadora(@RequestBody @Valid RequestCalculadoraDTO payload){
        return service.calculadora(payload);
    }

}