package br.com.springboot.respository;

import br.com.springboot.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long>{

    @Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%") /*Trim tira o espaço no postgresql e upper pega letras maiusculas e menusculas*/
    List<Usuario> buscarPorNome(String name);
}
