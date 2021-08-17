package com.unsada.integradora.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.unsada.integradora.security.jwt.AuthEntryPointJwt;
import com.unsada.integradora.security.jwt.AuthTokenFilter;
import com.unsada.integradora.security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers("/api/edificio/all","/api/sede/find/**",
					"/api/solicitud/find/uuid/**","/api/solicitud/update/**","/api/login/**","/api/auth/**",
					"/api/persona/find/dni/**","/api/persona/create","/api/ddjj/crear/**","/api/pregunta/all",
					"/api/FactorDeRiesgo/all","/api/sede/all","/api/sede/find/","/api/dependencia/all",
					"/api/propuesta/find/dependencia/**","/api/actividad/find/propuesta/**","/api/actividad/find/**",
					"/api/horario/find/sede_actrividad/**","/api/sesionpresencial/sesionhorario/**","/api/sesionpresencial/find/**",
					"/api/aula/find/sesion/**","/api/edificio/edificioByAula/**","/api/solicitud/create-ddjj-actividad-aula-horario/**",
					"/api/solicitud/find/**").permitAll()
			.antMatchers("/api/test/**").permitAll()
			.anyRequest().authenticated();
			

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
