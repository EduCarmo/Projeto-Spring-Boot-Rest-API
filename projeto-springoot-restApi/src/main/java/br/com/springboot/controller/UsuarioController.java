package br.com.springboot.controller;

import br.com.springboot.model.Usuario;
import br.com.springboot.respository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*Criação do CRUD Spring Boot Rest API*/
@RestController
public class UsuarioController {



    @Autowired
    private UsuarioRepository usuarioRepository;

    /*Lista*/
    @GetMapping(value = "listatodos")
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Usuario>> listaUsuario(){
        List<Usuario> usuarios =  usuarioRepository.findAll(); /*Consulta no banco de dados*/
        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); /*Retorna a lista JSON*/
    }

    /*Savar*/
    @PostMapping(value = "salvar") /*Mapea a url*/
    @ResponseBody() /*Descrição da resposta*/
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){ /*Recebe os dados para salvar*/

        Usuario user = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }

    /*Editar*/

    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){

        if(usuario.getId() == null) {
            return new ResponseEntity<String>("não foi informado o ID para atualização", HttpStatus.OK);
        }
        Usuario user = usuarioRepository.saveAndFlush(usuario);
        return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }


    /*Deleta*/
    @DeleteMapping(value = "delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long iduser){

        usuarioRepository.deleteById(iduser);

        return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);
    }

    //Buscar Usuario por ID
    @GetMapping(value = "buscaruserid")
    @ResponseBody
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name = "iduser") Long iduser){ /*Recebe os dados para consultar   */

        Usuario usuario = usuarioRepository.findById(iduser).get();

        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);

    }


    /*Buscar Usuario por nome*/

    @GetMapping(value = "buscarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam (name = "name") String name){
        List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase()); /*Trim tira o espaço no postgresql e toUpperCase tranforma tudo maisculo*/

        return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    }




    @RequestMapping(value = "/mostranome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String texto(@PathVariable String name){

        return "Olá" + name;

    }



}
