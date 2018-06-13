
package pe.edu.cibertec.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter{
    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;
    
    @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("*/js/**").permitAll()
                .antMatchers("*/css/**").permitAll()
                .antMatchers("*/admin/**").hasAnyRole("ADMIN","CONTENT_MANAGER")
                .antMatchers("/login.jsp").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/productos.xhtml").hasAnyRole("ROLE_ADMIN")
                .antMatchers("/pasarela.xhtml").permitAll()
                .antMatchers("addCarrito").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                    .loginPage("/login.jsp")
                    .loginProcessingUrl("/login")
                    .usernameParameter("txtUsuario")
                    .passwordParameter("txtContrasena")
                    .successForwardUrl("/productos.xhtml")
            .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login.jsp")
                    .invalidateHttpSession(true);
    }
    
    @Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
    
        /*
    @Bean
    @SuppressWarnings("deprecation")
    public UserDetailsService userDetailsService() {
        
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                users.username("carlos").password("carlos123").roles("USER").build());
        manager.createUser(
                users.username("yul").password("yul123").roles("CONTENT_MANAGER").build());
        manager.createUser(
                users.username("roger").password("roger123").roles("ADMIN").build());
        return manager;
    }
*/
}
