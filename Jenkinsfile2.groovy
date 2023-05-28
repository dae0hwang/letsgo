pipeline {
    agent any

    environment {
        imagename = "hwangdy/new-test"
        registryCredential = '9aa42473-4719-43e4-bc11-6fe70f2e5470'
        dockerImage = ''
    }

    stages {
        // git에서 repository clone

        stage('Prepare') {
          steps {
            echo 'Clonning Repository'
            git url: 'https://github.com/dae0hwang/letsgo',
              branch: 'master',
              credentialsId: '9aa42473-4719-43e4-bc11-6fe70f2e5470'
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

        stage('JUnit Test'){
            steps{
                junit '**/build/test-results/test/*.xml'
            }
        }

//        stage('Test') {
//            steps {
//                echo 'Test Gradle'
//                dir ('../new-test') {
//                    bat 'gradlew check'
//                }
//            }
//        }
    }

//    post {
//        always {
//            dir ('../new-test') {
//                junit  '**/reports/tests/test/*.xml'
//            }
//        }
//    }
}