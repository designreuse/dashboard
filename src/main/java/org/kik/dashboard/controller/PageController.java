package org.kik.dashboard.controller;

import java.util.Map;

import org.kik.dashboard.service.ProjectService;
import org.kik.dashboard.utils.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PageController.class);

	@Value("${application.message:Hello World}")
	private String message = "Hello World";

	@Autowired
	ProjectService svcProject;

	@RequestMapping("/")
	public ModelAndView home() {
		LOGGER.debug("[GET] /");
		ModelAndView model = PageUtils.initModelAndView("home", "welcome !");
		return model;
	}

	@RequestMapping("/foo")
	public String foo(Map<String, Object> model) {
		throw new RuntimeException("Foo");
	}

	@RequestMapping(value = "/projectNew", method = RequestMethod.GET)
	public ModelAndView projectPage() {
		LOGGER.debug("[GET] /projectNew");
		ModelAndView model = PageUtils.initModelAndView("projectNew",
				"new project");
		return model;
	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {
		LOGGER.debug("[GET] /admin");
		ModelAndView model = PageUtils.initModelAndView("admin",
				"administration");
		return model;
	}
}
