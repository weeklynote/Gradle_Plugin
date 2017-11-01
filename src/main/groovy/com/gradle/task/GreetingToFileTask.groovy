package com.gradle.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class GreetingToFileTask extends DefaultTask{

    GreetingToFileTask(){
        super()
        // 默认是other
        group = 'learn'
    }

    @TaskAction
    def greet(){
        def destination = project.learner.greetingFile
        def file = project.file(destination)
        file.parentFile.mkdirs()
        file.write("GreetingToFileTask greeting....")
    }
}
