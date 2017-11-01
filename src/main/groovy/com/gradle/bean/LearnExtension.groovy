package com.gradle.bean

import org.gradle.api.Project

/**
 * 在插件中构建并存储Gradle配置块中的属性，与配置信息要一一对应
 */
class LearnExtension {

    // 属性不能使用private进行修饰，否则在build.gradle文件中将找不到对应的命名
    String sourcePath
    boolean readOnly
    String greetingFile


    LearnExtension() {
        this.sourcePath = ''
        this.readOnly = true
        greetingFile = ''
    }

    @Override
    String toString() {
        return "LearnExtension{" +
                "sourcePath='" + sourcePath + '\'' +
                ", readOnly=" + readOnly +
                ", greetingFile='" + greetingFile + '\'' +
                '}'
    }
}
