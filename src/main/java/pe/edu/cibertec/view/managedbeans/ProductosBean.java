/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.view.managedbeans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import pe.edu.cibertec.dominio.Categoria;
import pe.edu.cibertec.dominio.Producto;
import pe.edu.cibertec.repositorio.CategoriaRepositorio;
import pe.edu.cibertec.repositorio.ProductoRepositorio;
import pe.edu.cibertec.repositorio.impl.CategoriaJpaRepositorioImpl;
import pe.edu.cibertec.repositorio.impl.ProductoJpaRepositorioImpl;

@ManagedBean
@ViewScoped
public class ProductosBean {
    private List<Producto> lstProducto;
    private Producto producto;
    private List<Categoria> lstCategoria;
    
    public ProductosBean(){
    }
    
    @PostConstruct
    public void init() {
        EntityManagerFactory emf = (EntityManagerFactory) FacesContext.getCurrentInstance()
                .getExternalContext().getApplicationMap().get("emf");
        EntityManager em = emf.createEntityManager();
        
        ProductoRepositorio productoRepositorio = new ProductoJpaRepositorioImpl().setEm(em);
        lstProducto = productoRepositorio.obtenerTodos();
        
        CategoriaRepositorio categoriaRepositorio = new CategoriaJpaRepositorioImpl().setEm(em);
        lstCategoria = categoriaRepositorio.obtenerTodos();
        em.close();
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
