package pe.edu.cibertec.view.managedbeans;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pe.edu.cibertec.dominio.Usuario;
import pe.edu.cibertec.service.UsuarioService;

//@ManagedBean
//@SessionScoped
@Component("loginBean")
@Scope("session")
public class LoginBean {
    
    @Autowired
    private UsuarioService usuarioService;
    
    private Usuario usuario = new Usuario(); 

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String login() {

        usuario = usuarioService.login(usuario.getEmail(),usuario.getPassword());
        
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
