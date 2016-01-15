package org.kik.dashboard.controller;

import java.util.List;

import org.kik.dashboard.model.Project;
import org.kik.dashboard.service.ProjectService;
import org.kik.dashboard.service.exception.FunctionalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ProjectController.class);

	@Autowired
	private ProjectService projectSvc;

	public ProjectController() {
		// Empty constructor
	}

	public ProjectController(final ProjectService userService) {
		this.projectSvc = userService;
	}

	/**
	 * GET the list of all projects.
	 * 
	 * @return a list of {@link Project}
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Project>> getProjects() {
		LOGGER.info("[API] GET projects");
		return new ResponseEntity<List<Project>>(projectSvc.getList(),
				HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> createProject(
			@RequestBody Project project) {
		LOGGER.info("[API] POST a new project");
		HttpStatus status = HttpStatus.CREATED;
		try {
			projectSvc.save(project);
		} catch (FunctionalException fe) {
			status = HttpStatus.BAD_REQUEST;
			LOGGER.error(fe.getMessage());
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			LOGGER.error("unexpected error", e);
		}
		return new ResponseEntity<String>(status);
	}

	/**
	 * Update a project.
	 * 
	 * @param projectId
	 *            Id of the project
	 * @return
	 */
	@RequestMapping(value = "/{projectId}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateProject(@PathVariable String projectId,
			@RequestBody Project project) {
		LOGGER.info("[API] PUT project " + projectId);
		HttpStatus status = HttpStatus.OK;
		try {
			projectSvc.updateProject(project);
		} catch (FunctionalException fe) {
			status = HttpStatus.BAD_REQUEST;
			LOGGER.error(fe.getMessage());
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			LOGGER.error("unexpected error", e);
		}
		return new ResponseEntity<String>(status);
	}

	/**
	 * Delete a project.
	 * 
	 * @param projectId
	 *            Id of the project
	 * @return
	 */
	@RequestMapping(value = "/{projectId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteProject(@PathVariable String projectId) {
		LOGGER.info("[API] DELETE project " + projectId);
		HttpStatus status = HttpStatus.NO_CONTENT;
		projectSvc.deleteProject(Long.valueOf(projectId));
		return new ResponseEntity<String>(status);
	}

}