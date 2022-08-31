 package com.stefanini.bibliotecaMusica.service;


import com.stefanini.bibliotecaMusica.exception.PermissoesNotFoundException;
import com.stefanini.bibliotecaMusica.model.Permissoes;
import com.stefanini.bibliotecaMusica.repository.PermissoesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissoesService {

    private final PermissoesRepository permissoesRepository;

    public PermissoesService(PermissoesRepository permissoesRepository) {
        this.permissoesRepository = permissoesRepository;
    }

    public List<Permissoes> findAllPermissoes(){
        return permissoesRepository.findAll();
    }

    public Permissoes findById(Long id) throws PermissoesNotFoundException {
        return permissoesRepository.findById(id)
                .orElseThrow(()-> new PermissoesNotFoundException(id));
    }

    public Permissoes save(Permissoes permissoes){
        return permissoesRepository.save(permissoes);
    }
    
    public void delete(Permissoes permissoes){
        permissoesRepository.delete(permissoes);
    }

}
