package com.springBajo8.springBajo8.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "padecimientos")
public class Padecimiento {
    @Id
    private String Id;
    private String motivo;
    private String idPaciente;

    public Padecimiento() {
    }

    public Padecimiento(String motivo, String idPaciente) {
        this.motivo = motivo;
        this.idPaciente = idPaciente;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }


}
