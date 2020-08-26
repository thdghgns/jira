package xyz.hohoon.jira.controller;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IssueController {
    @Autowired
    Graphviz graphviz;

    @ResponseBody
    @GetMapping(value = "/api/issue/flow", consumes = MediaType.ALL_VALUE, produces = "image/svg+xml")
    public String renderSVG() {
        return graphviz.render(Format.SVG).toString();
    }
}
