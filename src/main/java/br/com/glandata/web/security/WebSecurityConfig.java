package br.com.glandata.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		/*
		 * .antMatchers(HttpMethod.POST, "/clientes/**").hasRole("CADASTROS")
		 * .antMatchers("/produtos/**").hasAnyRole("CADASTROS","ADMIN")
		 * .antMatchers("/categorias/**").permitAll()
		 */
		// .anyRequest().authenticated()
		.anyRequest().permitAll()
		.and()
			.exceptionHandling()
				.accessDeniedPage("/acessoNegado")
		.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/", true)
				.permitAll()
		.and()
			.logout()
				.logoutUrl("/logout")
				.invalidateHttpSession(true);
//				.deleteCookies("nome do cookie para excluir");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**");
	}
}
