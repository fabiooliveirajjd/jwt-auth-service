package com.fabio.gestaoDeProjetos.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensagemEmail {
    //MODELO DE ENVIO DE EMAIL
    private String assunto;
    private String mensagem;
    private String remetente;
    private List<String> destinatarios;
}
