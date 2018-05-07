package pe.edu.cibertec.view.managedbeans;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpSession;
import pe.edu.cibertec.dominio.Usuario;
import pe.edu.cibertec.repositorio.UsuarioRepositorio;
import pe.edu.cibertec.repositorio.impl.UsuarioJpaRepositorioImpl;

@ManagedBean
@SessionScoped
public class LoginBean {
    
    private Usuario usuario = new Usuario(); 

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String login() {
        EntityManagerFactory emf = (EntityManagerFactory)FacesContext.getCurrentInstance().getExternalContext()
                .getApplicationMap().get("emf");
        EntityManager em = emf.createEntityManager();

        UsuarioRepositorio usuarioRepositorio = new UsuarioJpaRepositorioImpl().setEm(em);
        usuario = usuarioRepositorio.login(usuario.getEmail(),usuario.getPassword());
        em.close();
        
        if(usuario != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
            return "productos.xhtml?faces-redirect=true";
        }
        usuario = new Usuario();

        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Email o contraseña incorrecta","Datos incorrectos");
        FacesContext.getCurrentInstance().addMessage(null, fm);
        return null;
    }
    
    public void logout() throws IOException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        String patch = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(patch+"/login.xhtml");

    }
}
