pipeline {
    agent any 	// 사용 가능한 에이전트에서 이 파이프라인 또는 해당 단계를 실행

        stage('Build') {
            steps {
            	// gralew이 있어야됨. git clone해서 project를 가져옴.
                sh 'chmod +x gradlew'
                sh  './gradlew clean build'

                sh 'ls -al ./build'
            }
            post {
                success {
                    echo 'gradle build success'
                }

                failure {
                    echo 'gradle build failed'
                }
            }
        }

    }
}