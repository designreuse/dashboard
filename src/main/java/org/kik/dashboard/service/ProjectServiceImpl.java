package org.kik.dashboard.service;

import java.util.List;
import java.util.Optional;

import org.kik.dashboard.dao.ProjectRepository;
import org.kik.dashboard.model.Project;
import org.kik.dashboard.service.exception.FunctionalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectServiceImpl implements ProjectService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ProjectServiceImpl.class);

	@Autowired
	private ProjectRepository daoProject;

	public ProjectServiceImpl() {
		// Empty constructor
	}

	public ProjectServiceImpl(final ProjectRepository repository) {
		this.daoProject = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Project> getList() {
		LOGGER.debug("Retrieving the list of all projects");
		return daoProject.findAll();
	}

	@Override
	public Project getProject(long id) {
		Project p = daoProject.findOne(id).get();
		return p;
	}

	@Override
	@Transactional
	public Project save(final Project project) throws FunctionalException {

		checkProject(project);

		Project p = daoProject.save(project);

		return p;
	}

	@Override
	public void updateProject(Project project) throws FunctionalException {

		checkProject(project);

		Project p = daoProject.findOne(project.getId()).get();
		p.setName(project.getName());
		p.setDescription(project.getDescription());

		daoProject.save(p);
	}

	private void checkProject(final Project project) throws FunctionalException {
		// Check fields
		if (project.getName() == null || project.getName().isEmpty()) {
			throw new FunctionalException("Project's name is mandatory.");
		}
	}

	@Override
	public void deleteProject(long id) {
		Optional<Project> opt = daoProject.findOne(id);
		if (opt.isPresent()) {
			daoProject.delete(opt.get());
		}
	}

}
