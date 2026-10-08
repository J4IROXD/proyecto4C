package mx.edu.utez.proyecto4C.service;

import mx.edu.utez.proyecto4C.controller.dto.RequestCalculadoraDTO;
import mx.edu.utez.proyecto4C.exception.customExceptions.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    public double calculadora(RequestCalculadoraDTO payload){
        double resultado = 0;

        // en caso de que no sea una operacion valida lanzar una excepcion para cortar el flujo
        if (!payload.getOperacion().equals("DIVISION")
                && !payload.getOperacion().equals("SUMA")
                && !payload.getOperacion().equals("RESTA")
                && !payload.getOperacion().equals("MULTIPLICACION")){
            throw new BadRequestException("La operacion no es valida");
        }
        switch (payload.getOperacion()){

            case "MULTIPLICACION":
                resultado = payload.getNum1() * payload.getNum2();
                break;

            case "SUMA":
                resultado = payload.getNum1() + payload.getNum2();
                break;

            case "RESTA":
                resultado = payload.getNum1() - payload.getNum2();
                break;

            case "DIVICION":
                resultado = payload.getNum1() / payload.getNum2();
                break;
        }
        return resultado;
    }
}
