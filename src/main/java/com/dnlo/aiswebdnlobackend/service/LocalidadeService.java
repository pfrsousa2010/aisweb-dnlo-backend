package com.dnlo.aiswebdnlobackend.service;

import com.dnlo.aiswebdnlobackend.entity.Localidade;
import com.dnlo.aiswebdnlobackend.repository.LocalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalidadeService {

    @Autowired
    private LocalidadeRepository localidadeRepository;

    public Localidade salvar(Localidade localidade) {
        return localidadeRepository.save(localidade);
    }

    public List<Localidade> listaLocalidade() {
        return localidadeRepository.findAll();
    }

    public Optional<Localidade> buscarPorId(int id) {
        return localidadeRepository.findById(id);
    }

    public void removerPorId(int id) {
        localidadeRepository.deleteById(id);
    }
}
