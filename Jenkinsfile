pipeline {
    agent any

    environment {
        AWS_REGION = 'us-east-1'
        AWS_ACCESS_KEY_ID = credentials('aws-access-key-id')
        AWS_SECRET_ACCESS_KEY = credentials('aws-secret-access-key')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                script {
                    sh './gradlew build'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    sh './gradlew test'
                }
            }
        }

        stage('Deploy to AWS') {
            steps {
                script {
                    sh 'terraform apply -auto-approve'
                }
            }
        }
    }

    post {
        always {
            cleanup()
        }
    }
}