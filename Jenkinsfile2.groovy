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
//             dir ('.'){
// //                 sh """
// //                 ./gradlew clean build --exclude-task test
// //                 """
//               sh "./gradlew clean build"
//
//             }
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

        stages {
            stage('Test') {
                steps {
                    bat 'gradlew check'
                }
            }
        }
        post {
            always {
                junit 'build/reports/**/*.xml'
            }
        }

//        // docker build
//        stage('Bulid Docker') {
//          agent any
//          steps {
//            echo 'Bulid Docker'
//            dir ('../new-test') {
//              script {
//                bat 'cd ../new-test'
//                dockerImage = docker.build imagename
//              }
//            }
//          }
//          post {
//            failure {
//              error 'This pipeline stops here...'
//            }
//          }
//        }
//
//        // docker push
//        stage('Push Docker') {
//          agent any
//          steps {
//            echo 'Push Docker'
//            dir ('../new-test') {
//              script {
//                docker.withRegistry( '', registryCredential) {
//                  dockerImage.push("latest")
//                }
//              }
//            }
//
////             script {
////                 bat 'cd ../new-test'
////                 docker.withRegistry( '', registryCredential) {
////                     dockerImage.push("1.0")  // ex) "1.0"
////                 }
////             }
//          }
//          post {
//            failure {
//              error 'This pipeline stops here...'
//            }
//          }
//        }
    }
}