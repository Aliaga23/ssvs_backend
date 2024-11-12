package com.ssvs.SSVS.backend.service;

import com.ssvs.SSVS.backend.model.DocumentosLaboratorio;
import com.ssvs.SSVS.backend.repository.DocumentosLaboratorioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentosLaboratorioService {

    private final DocumentosLaboratorioRepository documentosLaboratorioRepository;

    public DocumentosLaboratorioService(DocumentosLaboratorioRepository documentosLaboratorioRepository) {
        this.documentosLaboratorioRepository = documentosLaboratorioRepository;
    }

    public List<DocumentosLaboratorio> getAllDocumentosLaboratorio() {
        return documentosLaboratorioRepository.findAll();
    }

    public DocumentosLaboratorio getDocumentoLaboratorioById(int id) {
        return documentosLaboratorioRepository.findById(id);
    }

    public void createDocumentoLaboratorio(DocumentosLaboratorio documento) {
        documentosLaboratorioRepository.save(documento);
    }

    public void updateDocumentoLaboratorio(int id, DocumentosLaboratorio documento) {
        documentosLaboratorioRepository.update(id, documento);
    }

    public void deleteDocumentoLaboratorio(int id) {
        documentosLaboratorioRepository.delete(id);
    }
}
