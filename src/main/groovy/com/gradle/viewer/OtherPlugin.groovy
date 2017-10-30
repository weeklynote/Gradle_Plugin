package com.gradle.viewer

import org.gradle.api.Plugin
import org.gradle.api.Project

class OtherPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println("OtherPlugin apply method invoked!!!")
    }
}
