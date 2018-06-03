/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.view.managedbeans;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pe.edu.cibertec.dominio.Categoria;
import pe.edu.cibertec.dominio.Producto;
import pe.edu.cibertec.service.CategoriaService;
import pe.edu.cibertec.service.ProductoService;

//@ManagedBean
//@ViewScoped
@Component("ProductosBean")
@Scope("view")
public class ProductosBean {
    private List<Producto> lstProducto;
    private Producto producto;
    private List<Categoria> lstCategoria;
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private CategoriaService categoriaService;
    
    public ProductosBean(){
    }
    
    @PostConstruct
    public void init() {
        lstProducto = productoService.obtenerTodos();
        lstCategoria = categoriaService.obtenerTodos();

        // no tiene argumentos, es void, public, no debe lanzar excepción, anotación @PostConstruct  
        producto = new Producto();

    }

    public List<Producto> getLstProducto() {
        return lstProducto;
    }

    public void setLstProducto(List<Producto> lstProducto) {
        this.lstProducto = lstProducto;
    }

    public List<Categoria> getLstCategoria() {
        return lstCategoria;
    }

    public void setLstCategoria(List<Categoria> lstCategoria) {
        this.lstCategoria = lstCategoria;
    }
    
    
}
