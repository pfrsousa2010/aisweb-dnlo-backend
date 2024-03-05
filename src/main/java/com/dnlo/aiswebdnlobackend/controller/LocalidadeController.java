package com.dnlo.aiswebdnlobackend.controller;

import com.dnlo.aiswebdnlobackend.entity.Localidade;
import com.dnlo.aiswebdnlobackend.service.LocalidadeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/localidade")
public class LocalidadeController {

    private static final List<String> LOCALIDADES_PADRAO_PESQUISA = List.of(
            "SSAP",
            "SSOG",
            "SSCP",
            "SBLO",
            "SBMG",
            "SSPI",
            "SSSZ",
            "SBXO",
            "SDDZ",
            "SSUW",
            "SNTT",
            "SSJP",
            "SJEL",
            "SSOC",
            "SSHN",
            "SI6B",
            "SWOE",
            "SWES",
            "SJTL",
            "SWMT",
            "SSHL",
            "SIOX",
            "SJHA",
            "SWSA",
            "SI72",
            "SSOK",
            "SSPX",
            "SSFX",
            "SJVB",
            "SSXO",
            "SI2M",
            "SJVS",
            "SWKS",
            "SIMC",
            "SWEP"
    );
    @Autowired
    private LocalidadeService localidadeService;

    @Autowired
    private ModelMapper modalMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Localidade savar(@RequestBody Localidade localidade) {
        return localidadeService.salvar(localidade);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Localidade> listaLocalidade() {
        return localidadeService.listaLocalidade();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Localidade buscaLocalidadePorId(@PathVariable("id") int id) {
        return localidadeService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Localidade não encontrada."));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerLocalidade(@PathVariable("id") int id) {
        localidadeService.buscarPorId(id)
                .map(localidade -> {
                    localidadeService.removerPorId(localidade.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Localidade não encontrada."));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarLocalidade(@PathVariable("id") int id, @RequestBody Localidade localidade) {
        localidadeService.buscarPorId(id)
                .map(localidadeBase -> {
                    modalMapper.map(localidade, localidadeBase);
                    localidadeService.salvar(localidadeBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Localidade não encontrada."));
    }

    @GetMapping("/salvar-todos")
    @ResponseStatus(HttpStatus.OK)
    public void salvarTodos() {
        var lista = LOCALIDADES_PADRAO_PESQUISA;
        var localidade = new Localidade();
        for (int i = 0; i < lista.size(); i++) {
            localidade.setId(i);
            localidade.setIcao(lista.get(i));
            localidade.setCabeceira("THR 13");
            localidadeService.salvar(
                    localidade
            );
        }
    }
}
