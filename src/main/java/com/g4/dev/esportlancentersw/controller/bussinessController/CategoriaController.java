package com.g4.dev.esportlancentersw.controller.bussinessController;


import com.g4.dev.esportlancentersw.model.Categoria;
import com.g4.dev.esportlancentersw.model.Producto;
import com.g4.dev.esportlancentersw.service.IServices.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired private ICategoriaService iCategoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> listarProductosActivos()
    {
        return  new ResponseEntity<>(iCategoriaService.listarDatos(), HttpStatus.OK);
    }


}
