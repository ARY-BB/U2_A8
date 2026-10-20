package mx.edu.utez.proyectoU2_A8.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mx.edu.utez.proyectoU2_A8.model.PrioridadTicket;

public class CrearTicketDTO {
    @NotBlank(message="la descripccion no pued estar vacia")
    private String descripccion;

    @NotNull(message = "la prioridad es obligatoria")
    private PrioridadTicket prioridad;

    @NotNull(message = "el ID es obligatorio no pued estar vacio")
    private Long usuarioId;

    public String getDescripccion(){return descripccion;}
    public void setDescripccion(String descripccion){
        this.descripccion=descripccion;}

    public PrioridadTicket getPrioridad(){return prioridad;}
    public void setPrioridad(PrioridadTicket prioridad){
        this.prioridad=prioridad;}

    public Long getUsuarioId(){return usuarioId;}
    public void setUsuarioId(Long usuarioId){this.usuarioId=usuarioId;}

}
