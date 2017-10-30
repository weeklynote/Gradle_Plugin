package com.gradle.viewer

import com.gradle.bean.LearnExtension
import com.gradle.task.LearnTask
import org.gradle.api.Project
import org.gradle.api.Plugin

class LauncherPlugin implements Plugin<Project>{

    private final String EXTENSION_PATCHER = "learner"

    @Override
    void apply(Project project) {
        project.gradle.addListener(new TimeListener())
        println(project.name)
        project.extensions.create(EXTENSION_PATCHER, LearnExtension)
        project.tasks.create("LearnTaskTest", LearnTask)
    }
}
