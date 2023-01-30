def mvn = "/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/3.6.3/bin/mvn"

pipeline {
    agent { label 'linux' }
    stages {
        stage('Checkout SCM') {
            steps{
                checkout(
                        [$class: 'GitSCM',
                         branches: [[name: "refs/heads/${BRANCH}"]],
                         userRemoteConfigs: [[url: 'https://github.com/ArturPonomarev/IBS_sber_cucumber']]]
                )
            }
        }
        stage ('build') {
            steps{
                sh "${mvn} clean compile"
            }
        }
        stage ('Run Tests') {
            steps{
                sh "${mvn} test -DbrowserName=remote -DremoteBrowserName=${REMOTE_BROWSER}"
            }
        }
        stage ('Allure Report Generation') {
            steps{
                allure includeProperties: false,
                        jdk: '',
                        results: [[path: 'target/reports/allure-results']]
            }
        }
    }
}