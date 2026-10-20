package mx.edu.utez.proyectoU2_A8.service;

import mx.edu.utez.proyectoU2_A8.dto.CrearUsuarioDTO;
import mx.edu.utez.proyectoU2_A8.exception.ResourceNotFoundException;
import mx.edu.utez.proyectoU2_A8.model.Usuario;
import mx.edu.utez.proyectoU2_A8.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public Usuario crearUsuario(CrearUsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setActivo(true);
        return usuarioRepository.save(usuario);
    }
    public Usuario desactivarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con ID " + id + " no existe."));
        usuario.setActivo(false);
        return usuarioRepository.save(usuario);
    }
}
