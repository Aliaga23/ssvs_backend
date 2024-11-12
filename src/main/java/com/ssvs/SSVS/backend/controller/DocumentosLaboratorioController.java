package com.ssvs.SSVS.backend.controller;

import com.ssvs.SSVS.backend.model.DocumentosLaboratorio;
import com.ssvs.SSVS.backend.service.DocumentosLaboratorioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos-laboratorio")
public class DocumentosLaboratorioController {

    private final DocumentosLaboratorioService documentosLaboratorioService;

    public DocumentosLaboratorioController(DocumentosLaboratorioService documentosLaboratorioService) {
        this.documentosLaboratorioService = documentosLaboratorioService;
    }

    @GetMapping
    public List<DocumentosLaboratorio> getAllDocumentosLaboratorio() {
        return documentosLaboratorioService.getAllDocumentosLaboratorio();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentosLaboratorio> getDocumentoLaboratorioById(@PathVariable int id) {
        DocumentosLaboratorio documento = documentosLaboratorioService.getDocumentoLaboratorioById(id);
        if (documento != null) {
            return ResponseEntity.ok(documento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DocumentosLaboratorio> createDocumentoLaboratorio(@RequestBody DocumentosLaboratorio documento) {
        documentosLaboratorioService.createDocumentoLaboratorio(documento);
        return ResponseEntity.ok(documento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentosLaboratorio> updateDocumentoLaboratorio(@PathVariable int id, @RequestBody DocumentosLaboratorio documento) {
        DocumentosLaboratorio existingDocumento = documentosLaboratorioService.getDocumentoLaboratorioById(id);
        if (existingDocumento != null) {
            documentosLaboratorioService.updateDocumentoLaboratorio(id, documento);
            return ResponseEntity.ok(documento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumentoLaboratorio(@PathVariable int id) {
        DocumentosLaboratorio existingDocumento = documentosLaboratorioService.getDocumentoLaboratorioById(id);
        if (existingDocumento != null) {
            documentosLaboratorioService.deleteDocumentoLaboratorio(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
