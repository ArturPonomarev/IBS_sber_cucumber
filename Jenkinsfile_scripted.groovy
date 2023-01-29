def mvn = "/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/3.6.3/bin/mvn"

pipeline {
    agent { label 'windows'}
    stages {
        stage ('build') {
            steps{
                sh "${mvn} clean compile"
            }
        }
        stage ('Run Tests') {
            steps{
                sh "${mvn} test"
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