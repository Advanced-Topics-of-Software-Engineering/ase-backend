package edu.tum.ase.project.controller;

import edu.tum.ase.project.model.Project;
import edu.tum.ase.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("")
    public List<Project> getAllProject() {
        return projectService.getAllProjects();
    }

    @PostMapping("")
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @GetMapping("/{projectName}")
    public Project findProjectByName(@PathVariable String projectName) throws Exception {
        return projectService.findByName(projectName);
    }
}
