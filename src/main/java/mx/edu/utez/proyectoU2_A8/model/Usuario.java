package mx.edu.utez.proyectoU2_A8.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="usuarios")
public class Usuario {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

   @Column(nullable = false)
    private String nombre;

   @Column(nullable = false)
    private Boolean activo=true;

   @OneToMany(mappedBy = "usuario", cascade=CascadeType.ALL)
    private List<Ticket>tickets;

   public Usuario(){}
    public Usuario(String nombre, Boolean activo){
       this.nombre=nombre;
        this.activo=activo;
    }
    public Long getId(){return id;}
    public void setId (Long id){
       this.id=id;}
    public String getNombre(){return nombre;}
    public Boolean getActivo(){return activo;}
    public void setActivo(Boolean activo){this.activo=activo;}
    public List<Ticket> getTickets() { return tickets; }
    public void setTickets(List<Ticket> tickets) { this.tickets = tickets; }

    public void setNombre(String nombre) {
    }
}

