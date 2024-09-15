package com.github.tcsizmadia.dognames;

import rife.bld.BuildCommand;
import rife.bld.WebProject;
import rife.bld.dependencies.Scope;
import rife.bld.dependencies.VersionNumber;
import rife.bld.extension.BootJarOperation;

import com.github.tcsizmadia.dognames.breedgenerator.extension.BreedGeneratorHelp;
import com.github.tcsizmadia.dognames.breedgenerator.extension.BreedGeneratorOperation;

import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

import static rife.bld.dependencies.Repository.MAVEN_CENTRAL;

public class BackendBuild extends WebProject {
    public static final VersionNumber BOOT_VERSION = new VersionNumber(3,3,3);
    public static final VersionNumber JUPITER_VERSION = new VersionNumber(5,11,0);
    public static final VersionNumber JUNIT_PLATFORM_VERSION = new VersionNumber(1,11,0);
    public static final Logger logger = Logger.getLogger(BackendBuild.class.getName());

    public BackendBuild() {
        pkg = "com.github.tcsizmadia.dognames";
        name = "Backend";
        mainClass = "com.github.tcsizmadia.dognames.BackendMain";
        version = version(0,1,0);

        javaRelease = 17;

        autoDownloadPurge = true;
        repositories = List.of(MAVEN_CENTRAL);

        scope(Scope.compile)
                .include(dependency("org.springframework.boot", "spring-boot-starter", BackendBuild.BOOT_VERSION))
                .include(dependency("org.springframework.boot", "spring-boot-starter-web", BackendBuild.BOOT_VERSION));

        scope(Scope.standalone)
                .include(dependency("org.springframework.boot", "spring-boot-loader", BackendBuild.BOOT_VERSION));

        scope(Scope.test)
                .include(dependency("org.springframework.boot", "spring-boot-starter-test", BackendBuild.BOOT_VERSION))
                .include(dependency("org.junit.jupiter", "junit-jupiter", BackendBuild.JUPITER_VERSION))
                .include(dependency("org.junit.platform", "junit-platform-console-standalone", BackendBuild.JUNIT_PLATFORM_VERSION));
    }

    @BuildCommand(summary = "Creates an executable JAR for the project")
    public void bootjar() throws Exception {
        new BootJarOperation()
                .fromProject(this)
                .execute();
    }

    @BuildCommand(summary = "Generate dog breeds", value = "generate-breeds", help = BreedGeneratorHelp.class)
    public void generateBreeds() throws Exception {
        new BreedGeneratorOperation()
                .fromProject(this)
                .model("llama3.1")
                .ollamaHost("localhost")
                .ollamaPort(11434)
                .execute();
    }

    public static void main(String[] args) {
        new BackendBuild().start(args);
    }
}
