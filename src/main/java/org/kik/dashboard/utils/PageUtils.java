package org.kik.dashboard.utils;

import java.util.List;

import org.kik.dashboard.model.Project;
import org.kik.dashboard.model.User;
import org.springframework.web.servlet.ModelAndView;

public class PageUtils {

	private PageUtils() {
		// static class
	}

	public static ModelAndView initModelAndView(final String viewName,
			final String pageTitle) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(viewName);
		mv.addObject("title", pageTitle);
		return mv;
	}

	public static void addProjectInfo(ModelAndView mv, Project project) {
		mv.addObject("projectId", project.getId());
		mv.addObject("project", project);
	}

	public static void addUsersList(ModelAndView mv, List<User> users) {
		mv.addObject("users", users);
	}
}
