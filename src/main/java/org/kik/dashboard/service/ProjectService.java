package org.kik.dashboard.service;

import java.util.List;

import org.kik.dashboard.model.Project;
import org.kik.dashboard.service.exception.FunctionalException;

public interface ProjectService {

	Project getProject(long id);

	Project save(Project user) throws FunctionalException;

	void updateProject(Project project) throws FunctionalException;

	List<Project> getList();

	void deleteProject(long id);

}