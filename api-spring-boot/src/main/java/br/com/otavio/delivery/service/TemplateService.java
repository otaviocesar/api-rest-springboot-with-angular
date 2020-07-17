package br.com.otavio.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otavio.delivery.model.Template;
import br.com.otavio.delivery.model.TemplateStatus;
import br.com.otavio.delivery.repository.TemplateRepository;

@Service
public class TemplateService {
	
	@Autowired
	private TemplateRepository templateRepository;
	
	public List<Template> findAll() {
		return templateRepository.findAll();
	}
	
	public Template findById(Long id) {
		Optional<Template> templateOpt = templateRepository.findById(id);
		return templateOpt.get();
	}

	public Template save(Template template) {
		return templateRepository.save(template);
	}

	public Template update(Long templateId, Template template) {
		Optional<Template> templateUpdated = templateRepository.update(templateId, template);
		return templateUpdated.get();
	}

	public void updateTemplateStatus(Long templateId, TemplateStatus newTemplateStaus) {
		templateRepository.updateStatus(templateId, newTemplateStaus);
	}
}
