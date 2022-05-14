job('NodeJS Docker example') {
    scm {
        git('https://github.com/gtorreblancaluna/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('gtorreblancaluna')
            node / gitConfigEmail('gtorreblancaluna@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('gtorreblancaluna/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('gtorreblancaluna')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
