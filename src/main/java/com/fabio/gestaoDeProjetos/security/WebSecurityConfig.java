package com.fabio.gestaoDeProjetos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //AQUI INFORMO QUE É UMA CLASSE SE GURANÇA DO WEB SECURITY
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDatailsService customUserDatailsService;

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    /**
     * MÉTODO QUE DEVOLVE A INSTÂNCIA DE UM OBJETO QUE SABE DEVOLVER O NOSSO PADRÃO DE CODIFICAÇÃO
     * ISSO NÃO TEM NADA A VER COM O JWT
     * AQUI SERÁ USADO PARA CODIFICAR A SENHA DO USUÁRIO GERANDO UM HASH
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //METODO PRDRÃO PARA CONFIGURAR O NOSSO CUSTOM COM O NOSSO MÉTODO DE CODIFICAR SENHA
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDatailsService)
                .passwordEncoder(passwordEncoder());

    }

    //MÉTODO PADRÃO: ESSE MÉTODO É OBRIGATÓRIO PARA CONSEGUIRMOS TRABALHAR COM AUTENTICAÇÃO NO LOGIN
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        //PARTE PADRÃO DA CONFIGURAÇÃO, POR ENQUANTO MANTER PARA TODOS OS PROJETOS
        http
                .cors().and().csrf().disable()//cors: qualquer cliente pode acessar.
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()

        /**
         * DAQUI PRA BAIXO É ONDE NÓS VAMOS VAZER NOSSOAS VALIDAÇÕES
         * AQUI VAMOS INFORMAR AS ROTAS QUE NÃO VÃO PRECISAR DE AUTENTICAÇÃO
         */
                //INFORMA QUE TODOS PODEM ACESSAR, NÃO PRECISAM DE AUTENTICAÇÃO
                .antMatchers(HttpMethod.POST, "/api/usuarios", "/api/usuarios/login")
                .permitAll()
                //DIGO QUE AS DEMAIS REQUISIÇÕES DEVEM SER REQUISITADAS
                .anyRequest()
                .authenticated();
                //AQUI EU INFORMO QUE ANTES DE QUALQUER REQUISIÇÃO HTTP, O SISTEMA DEVE USAR O NOSSO FILTRO JWT jwtAuthenticationFilter
                http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
