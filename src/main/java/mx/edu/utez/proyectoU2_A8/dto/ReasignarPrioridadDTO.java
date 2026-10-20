package mx.edu.utez.proyectoU2_A8.dto;

import jakarta.validation.constraints.NotNull;
import mx.edu.utez.proyectoU2_A8.model.PrioridadTicket;

public class ReasignarPrioridadDTO {
    @NotNull(message = "La nueva prioridad es obligatoria")
    private PrioridadTicket prioridad;


    public PrioridadTicket getPrioridad() { return prioridad; }
    public void setPrioridad(PrioridadTicket prioridad) { this.prioridad = prioridad; }
}
