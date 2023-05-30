//enviroment에 우분투 ssh 아이디 비번 넣는 방법 ssh -step에 나와 있다.
//scm 으로 jenkinsfile 이용하는 방법
//git pull 적용되는 지 확인
pipeline {
    agent any
    environment {
        registryCredential = 'DockerHub_IdPwd'
        dockerImage = ''
    }

    stages {
        // git에서 repository clone
        stage('Prepare') {
            steps {
                echo 'Clonning Repository'
                git url: 'https://github.com/dae0hwang/letsgo',
                        branch: 'master'
            }
            post {
                success {
                    echo 'Successfully Cloned Repository'
                }
                failure {
                    error 'This pipeline stops here...'
                }
            }
        }

        // gradle build
        stage('Bulid Gradle') {
            agent any
            steps {
                echo 'Bulid Gradle'
                dir ('../new-test') {
                    bat 'gradlew clean build'
                }
            }
            post {
                success {
                    echo "SuccessfulAZly Build gradle"
                }
                failure {
                    echo "Fail to build gradle"
                    error 'This pipeline stops here...'
                }
            }
        }

        // test
        stage('JUnit Test'){
            steps{
                echo 'Test Result'
                junit '**/build/test-results/test/*.xml'
            }
        }

        // docker build
        stage('Bulid Docker') {
            agent any
            steps {
                echo 'Bulid Docker'
                dir ('../new-test') {
                    script {
                        dockerImage = docker.build 'hwangdy/docker-test'
                    }
                }
            }
            post {
                failure {
                    error 'This pipeline stops here...'
                }
            }
        }

        // docker push
        stage('Push Docker') {
            steps {
                echo 'Push Docker'
                script {
                    docker.withRegistry('', registryCredential) {
                        dockerImage.push()
                    }
                }
            }
            post {
                failure {
                    error 'This pipeline stops here...'
                }
            }
        }

        // shh - deploy
        stage('SSH') {
            steps {
                script {
                    def remote = [:]
                    remote.name = 'test'
                    remote.host = '127.0.0.5'
                    remote.user = 'j'
                    remote.password = 'j'
                    remote.allowAnyHosts = true
                    stage('Remote SSH') {
                        sshCommand remote: remote, command: "jenkins a.sh"
                    }
                }
            }
        }
    }
}