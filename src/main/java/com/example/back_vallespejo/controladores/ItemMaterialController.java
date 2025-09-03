package com.example.back_vallespejo.controladores;

import com.example.back_vallespejo.models.entities.ItemMaterial;
import com.example.back_vallespejo.models.entities.ListaMateriales;
import com.example.back_vallespejo.service.IItemMaterialService;
import com.example.back_vallespejo.service.IListaMaterialesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ItemMaterialController {

    @Autowired
    private IItemMaterialService itemMaterialService;

    @Autowired
    private IListaMaterialesService listaMaterialesService;


    @DeleteMapping("/lista-materiales/{listaId}/items/{itemId}")
    @Transactional
    public void eliminarItemMaterial(@PathVariable Long listaId, @PathVariable Long itemId){
        try {

            // Estos 3 métodos son para control de errores en caso no se encuentre la lista Material, o el Item.
            ListaMateriales listaMateriales = listaMaterialesService.findById(listaId);
            

            ItemMaterial itemMaterial = itemMaterialService.findById(itemId);
            
            // Nota R para los news: Este metodo (delete) ya está implementado en el ItemMaterialService lo puedes ver
            // en la carpeta service.
            itemMaterialService.delete(itemMaterial);
            
            // Nota R: Estos 2 metodos, 1 sirve para actualizar la fecha de modificación automaticamente por la actual
            // y el segundo registra nuevamente la listaMateriales, tanto con la nueva fecha como sin la item que se acaba de eliminar
            listaMateriales.actualizarFechaModificacion();
            listaMaterialesService.registrarListaMateriales(listaMateriales);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
