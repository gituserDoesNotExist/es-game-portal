pipeline {
    agent any
    
    parameters {
        booleanParam(defaultValue: true, description: 'do we need tests?', name: 'runTest')
    }
    
    stages {
        stage ('run bash script') {
            steps {
            	echo "flag is ${params.runTest}"
                sh '''#!/bin/bash
                      echo "hello world"
                '''
            }
        }
        stage ('compile') {
            steps {
                sh 'mvn --version'
                sh 'mvn clean'
                sh 'mvn compile'
                sh 'mvn test-compile'
            }
        }
        stage ('unit test') {
        	when {
        		expression {params.runTest}
        	}
            steps {
                sh 'mvn surefire:test'
            }
        }
        
        stage ('integration test') {
        	when {
        		expression {params.runTest}
        	}
            steps {
                sh 'mvn failsafe:integration-test'
            }
        }
        stage ('install') {
            steps {
                sh 'mvn install -Dmaven.test.skip=true'
            }
        }
        
    }
}