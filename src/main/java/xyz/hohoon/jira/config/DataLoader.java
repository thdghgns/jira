package xyz.hohoon.jira.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import xyz.hohoon.jira.entity.Project;
import xyz.hohoon.jira.entity.Version;
import xyz.hohoon.jira.repository.ComponentRepository;
import xyz.hohoon.jira.repository.ProjectRepository;
import xyz.hohoon.jira.repository.VersionRepository;
import xyz.hohoon.jira.security.user.User;
import xyz.hohoon.jira.security.user.UserRepository;

/**
 * make default data for local test
 */
@Component
@Profile("local")
public class DataLoader implements ApplicationRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ComponentRepository componentRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    VersionRepository versionRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // user
        for (int i = 0; i < 10; i++) {
            userRepository.save(new User("user" + i, "user@email.com", "user" + i));
        }

        // component
        for (int i = 0; i < 20; i++) {
            componentRepository.save(new xyz.hohoon.jira.entity.Component("COMP" + i, "COMP" + i, "COMP" + i));
        }

        // project
        for (int i = 0; i < 20; i++) {
            projectRepository.save(new Project("PROJ" + i, "PROJ" + i, "PROJ" + i));
        }

        // version
        for (int i = 0; i < 10; i++) {
            versionRepository.save(new Version("JIRA_V_" + 1));
        }
    }
}
