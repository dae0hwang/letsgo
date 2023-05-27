pipeline {
    agent any 	// 사용 가능한 에이전트에서 이 파이프라인 또는 해당 단계를 실행

    stages {
        stage('Prepare') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/mooh2jj/docker-jenkins-pipeline-test2.git'
            }

            post {
                success {
                    sh 'echo "Successfully Cloned Repository"'
                }
                failure {
                    sh 'echo "Fail Cloned Repository"'
                }
            }
        }
    }
}