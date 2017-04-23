package br.com.sociotorcedor.rest.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Representa os dados do Sócio Torcedor que devem ser recebidos e retornados pela API Rest de Socio Torcedor
 * @author : Claudio Nazareth  chtnazareth@gmail.com
 */
public class SocioTorcedorResource {

    @Size(min=5, max=100, message="Nome tem capacidade de 5 a 100 caracteres.")
    @NotNull(message="Nome do Sócio Torcedor é obrigatório!")
    private String nomeCompleto;

    @Size(min=5, max=150, message="E-mail tem capacidade de 5 a 150 caracteres.")
    @NotNull(message="E-mail é obrigatório!")
    @Email(message = "O e-mail esta com formato inválido")
    private String email;

    @NotNull(message="Data de nascimento é obrigatório!")

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataNascimento;

    @Size(min=5, max=100, message="Time do coração tem a capacidade de 5 a 100 caracteres.")
    @NotNull(message="A identifição do time é obrigatório!")
    private String timeCoracao;

    SocioTorcedorResource() {
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTimeCoracao() {
        return timeCoracao;
    }

    public void setTimeCoracao(String timeCoracao) {
        this.timeCoracao = timeCoracao;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("nomeCompleto", nomeCompleto)
                .add("email", email)
                .add("dataNascimento", dataNascimento)
                .add("timeCoracao", timeCoracao)
                .toString();
    }
}
