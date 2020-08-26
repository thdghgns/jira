package xyz.hohoon.jira.config;

import static guru.nidi.graphviz.model.Factory.mutGraph;
import static guru.nidi.graphviz.model.Factory.node;
import static guru.nidi.graphviz.model.Link.to;

import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.Node;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import xyz.hohoon.jira.statemachine.IssueEvent;
import xyz.hohoon.jira.statemachine.IssueState;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
public class StatemachineGraphicConfiguration {
    @Autowired
    StateMachineFactory<IssueState, IssueEvent> stateMachineFactory;

    @Bean
    public Graphviz graphviz() {
        StateMachine<IssueState, IssueEvent> stateMachine = stateMachineFactory.getStateMachine();
        MutableGraph graph = mutGraph("Issue State Machine").setDirected(true);

        Map<String, Set<GraphVizLink>> graphMap = new HashMap<>();

        stateMachine.getStates()
                .forEach(s -> graphMap.put(s.getId().name(), new HashSet<>()));

        stateMachine.getTransitions()
                .forEach(t -> graphMap.get(t.getSource().getId().name())
                            .add(new GraphVizLink(t.getTarget().getId().name(), t.getTrigger().getEvent().name())));

        graphMap.forEach((key, targetSet) -> {
            Node node = node(key).with(Shape.RECTANGLE);
            targetSet.forEach(l -> graph.add(node.link(to(node(l.getTarget())).with(Label.of(l.getLabel().toLowerCase())))));
        });

        return Graphviz.fromGraph(graph);
    }

    @Data
    private static class GraphVizLink {

        private final String target;
        private final String label;
    }
}
