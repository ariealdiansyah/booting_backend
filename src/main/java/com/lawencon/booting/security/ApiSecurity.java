package com.lawencon.booting.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lawencon.booting.service.AccountsService;

@EnableWebSecurity
public class ApiSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private ApiSecurityServiceImpl apiSecurityService;

	@Autowired
	private AccountsService accountsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();

		http.addFilter(new AuthenticationFilter(super.authenticationManager(), accountsService));

		http.addFilter(new AutorizationFilter(super.authenticationManager()));
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(apiSecurityService).passwordEncoder(passEncoder());
	}

	@Bean
	public BCryptPasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.POST, "/accounts/", "/photo/uploads");
		web.ignoring().antMatchers(HttpMethod.PUT, "/accounts/forgot", "/accounts/");
		web.ignoring().antMatchers(HttpMethod.GET, "/photo/**", "/report/**");
		web.ignoring().antMatchers("/v2/api-docs/**");
		web.ignoring().antMatchers("/swagger.json");
		web.ignoring().antMatchers("/swagger-ui.html");
		web.ignoring().antMatchers("/swagger-resources/**");
		web.ignoring().antMatchers("/webjars/**");
//		web.ignoring().antMatchers(HttpMethod.GET, "/**");
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/login")
						.allowedOrigins("http://127.0.0.1:5500", "http://localhost:4200", "http://147.139.130.49")
						.allowedMethods(HttpMethod.POST.name(), HttpMethod.GET.name());

				registry.addMapping("/accounts/**")
						.allowedOrigins("http://127.0.0.1:5500", "http://localhost:4200", "http://147.139.130.49")
						.allowedMethods(HttpMethod.POST.name(), HttpMethod.GET.name(), HttpMethod.PUT.name(),
								HttpMethod.DELETE.name());

				registry.addMapping("/roles/**")
						.allowedOrigins("http://127.0.0.1:5500", "http://localhost:4200", "http://147.139.130.49")
						.allowedMethods(HttpMethod.POST.name(), HttpMethod.GET.name(), HttpMethod.PUT.name(),
								HttpMethod.DELETE.name());

				registry.addMapping("/products/**")
						.allowedOrigins("http://127.0.0.1:5500", "http://localhost:4200", "http://147.139.130.49")
						.allowedMethods(HttpMethod.POST.name(), HttpMethod.GET.name(), HttpMethod.PUT.name(),
								HttpMethod.DELETE.name());

				registry.addMapping("/classifications/**")
						.allowedOrigins("http://127.0.0.1:5500", "http://localhost:4200", "http://147.139.130.49")
						.allowedMethods(HttpMethod.POST.name(), HttpMethod.GET.name(), HttpMethod.PUT.name(),
								HttpMethod.DELETE.name());

				registry.addMapping("/priorities/**")
						.allowedOrigins("http://127.0.0.1:5500", "http://localhost:4200", "http://147.139.130.49")
						.allowedMethods(HttpMethod.POST.name(), HttpMethod.GET.name(), HttpMethod.PUT.name(),
								HttpMethod.DELETE.name());

				registry.addMapping("/users/**")
						.allowedOrigins("http://127.0.0.1:5500", "http://localhost:4200", "http://147.139.130.49")
						.allowedMethods(HttpMethod.POST.name(), HttpMethod.GET.name(), HttpMethod.PUT.name(),
								HttpMethod.DELETE.name());

				registry.addMapping("/client-products/**")
						.allowedOrigins("http://127.0.0.1:5500", "http://localhost:4200", "http://147.139.130.49")
						.allowedMethods(HttpMethod.POST.name(), HttpMethod.GET.name(), HttpMethod.PUT.name(),
								HttpMethod.DELETE.name());

				registry.addMapping("/agent-relations/**")
						.allowedOrigins("http://127.0.0.1:5500", "http://localhost:4200", "http://147.139.130.49")
						.allowedMethods(HttpMethod.POST.name(), HttpMethod.GET.name(), HttpMethod.PUT.name(),
								HttpMethod.DELETE.name());

				registry.addMapping("/tickets/**")
						.allowedOrigins("http://127.0.0.1:5500", "http://localhost:4200", "http://147.139.130.49")
						.allowedMethods(HttpMethod.POST.name(), HttpMethod.GET.name(), HttpMethod.PUT.name(),
								HttpMethod.DELETE.name());

				registry.addMapping("/companies/**")
						.allowedOrigins("http://127.0.0.1:5500", "http://localhost:4200", "http://147.139.130.49")
						.allowedMethods(HttpMethod.POST.name(), HttpMethod.GET.name(), HttpMethod.PUT.name(),
								HttpMethod.DELETE.name());

				registry.addMapping("/status/**")
						.allowedOrigins("http://127.0.0.1:5500", "http://localhost:4200", "http://147.139.130.49")
						.allowedMethods(HttpMethod.POST.name(), HttpMethod.GET.name(), HttpMethod.PUT.name(),
								HttpMethod.DELETE.name());

				registry.addMapping("/mail/**")
						.allowedOrigins("http://127.0.0.1:5500", "http://localhost:4200", "http://147.139.130.49")
						.allowedMethods(HttpMethod.POST.name(), HttpMethod.GET.name(), HttpMethod.PUT.name(),
								HttpMethod.DELETE.name());

				registry.addMapping("/photo/**")
						.allowedOrigins("http://127.0.0.1:5500", "http://localhost:4200", "http://147.139.130.49")
						.allowedMethods(HttpMethod.POST.name(), HttpMethod.GET.name(), HttpMethod.PUT.name(),
								HttpMethod.DELETE.name());

				registry.addMapping("/report/**")
						.allowedOrigins("http://127.0.0.1:5500", "http://localhost:4200", "http://147.139.130.49")
						.allowedMethods(HttpMethod.POST.name(), HttpMethod.GET.name(), HttpMethod.PUT.name(),
								HttpMethod.DELETE.name());
			}
		};
	}

}
