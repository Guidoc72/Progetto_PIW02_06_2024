package com.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
@Bean
SecurityFilterChain chain(HttpSecurity http) throws Exception {
		
		http
		.csrf(csrf->csrf.disable())
//		.httpBasic(httpBasic->httpBasic.disable())
		.authorizeHttpRequests()

//		.requestMatchers(HttpMethod.POST).hasAuthority("Studente") // cambiarlo in plurale
		.requestMatchers("/home_utente.html","/visualizzazione_quiz.html","risultato_quiz.html").hasAuthority("Studente")
		.requestMatchers("/landingPageDocente","/creaQuiz","/inserimentoDomanda","inserimentoLinguaggio","modificaStudente").hasAuthority("Docente")
		.anyRequest().permitAll()
		
		.and()
		.formLogin()
        .loginPage("/login.html") // URL della tua pagina di login personalizzata
        .usernameParameter("mail") // Nome del campo email nel form
        .passwordParameter("password") // Nome del campo password nel form
        
        .permitAll() // Permetti l'accesso alla pagina di login senza autenticazione
        .defaultSuccessUrl("/") // URL della homepage
		.and()
		.logout()
		.and()
		.httpBasic();
		

		return http.build();

	}
	
	
	
	
	
	
	
	
	

}
