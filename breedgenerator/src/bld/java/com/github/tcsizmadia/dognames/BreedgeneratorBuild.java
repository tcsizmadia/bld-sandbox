package com.github.tcsizmadia.dognames;

import rife.bld.BuildCommand;
import rife.bld.WebProject;
import rife.bld.dependencies.Scope;
import rife.bld.Project;
import rife.bld.dependencies.VersionNumber;

import java.util.List;

import static rife.bld.dependencies.Repository.MAVEN_CENTRAL;
import static rife.bld.dependencies.Repository.RIFE2_RELEASES;

public class BreedgeneratorBuild extends Project {
    private static final VersionNumber JACKSON_JR_VERSION = new VersionNumber(2, 11, 0);
    private static final VersionNumber BLD_VERSION = new VersionNumber(2, 1, 0);

    public BreedgeneratorBuild() {
        pkg = "com.github.tcsizmadia.dognames";
        name = "Breedgenerator";
        version = version(0, 1, 0);

        downloadSources = true;
        repositories = List.of(MAVEN_CENTRAL, RIFE2_RELEASES);
        scope(Scope.compile)
                .include(dependency("com.uwyn.rife2", "bld", BLD_VERSION))
                .include(dependency("com.fasterxml.jackson.jr", "jackson-jr-objects", JACKSON_JR_VERSION));
    }

    public static void main(String[] args) {
        new BreedgeneratorBuild().start(args);
    }
}