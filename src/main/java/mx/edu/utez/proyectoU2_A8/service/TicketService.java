package mx.edu.utez.proyectoU2_A8.service;

import mx.edu.utez.proyectoU2_A8.dto.CrearTicketDTO;
import mx.edu.utez.proyectoU2_A8.dto.ReasignarPrioridadDTO;
import mx.edu.utez.proyectoU2_A8.exception.BadRequestException;
import mx.edu.utez.proyectoU2_A8.exception.ResourceNotFoundException;
import mx.edu.utez.proyectoU2_A8.model.EstadoTicket;
import mx.edu.utez.proyectoU2_A8.model.Ticket;
import mx.edu.utez.proyectoU2_A8.model.Usuario;
import mx.edu.utez.proyectoU2_A8.repository.TicketRepository;
import mx.edu.utez.proyectoU2_A8.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;
    public TicketService(TicketRepository ticketRepository, UsuarioRepository usuarioRepository) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
    }
    public Ticket crearTicket(CrearTicketDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con ID " + dto.getUsuarioId() + " no existe."));
        if (!usuario.getActivo()) {
            throw new BadRequestException("No se puede crear un ticket para un usuario inactivo.");
        }
        Ticket ticket = new Ticket();
        ticket.setDescripcion(dto.getDescripccion());
        ticket.setPrioridad(dto.getPrioridad());
        ticket.setEstado(EstadoTicket.ABIERTO);
        ticket.setUsuario(usuario);
        return ticketRepository.save(ticket);
    }
    public Ticket actualizarEstadoTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("El ticket con ID " + ticketId + " no existe."));
        switch (ticket.getEstado()) {
            case ABIERTO:
                ticket.setEstado(EstadoTicket.EN_PROCESO);
                break;
            case EN_PROCESO:
                ticket.setEstado(EstadoTicket.CERRADO);
                break;
            case CERRADO:
                throw new BadRequestException("El ticket ya se encuentra CERRADO y no se puede modificar.");
        }
        return ticketRepository.save(ticket);
    }
    public Ticket reasignarPrioridad(Long ticketId, ReasignarPrioridadDTO dto) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("El ticket con ID " + ticketId + " no existe."));
        if (ticket.getEstado() == EstadoTicket.CERRADO) {
            throw new BadRequestException("No se puede cambiar la prioridad a un ticket CERRADO.");
        }
        ticket.setPrioridad(dto.getPrioridad());
        return ticketRepository.save(ticket);
    }
    public List<Ticket> obtenerTicketsUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResourceNotFoundException("El usuario con ID " + usuarioId + " no existe.");
        }
        return ticketRepository.findByUsuarioId(usuarioId);
    }
}
