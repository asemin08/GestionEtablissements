pipeline {
	agent any
    tools {
    	maven 'maven'
        jdk 'java-15'
    }
    stages {
            stage ('Initialize') {
                steps {
                        echo "maven"
                        echo "jdk"
                }
            }
    }
	stages {
		stage('Build') {
			steps {
				echo 'Building..'
				sh("mvn clean package")
			}
			post {
                success {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
		}

		stage('Test') {
			steps {
				echo 'Testing..'
				sh("mvn test")
			}
		}
		stage('Deploy') {
			steps {
				echo 'Deploying....'
				 script {
                    try {
                        deploy adapters: [tomcat9(credentialsId: '0d15bf67-c9a3-4100-8b12-4b5f41d6c630', path: '/manager/text', url: 'http://vps-0c0ccce5.vps.ovh.net:8081')], contextPath: '/job8', war: '**/*.war'
                        } catch (err) {
                    }
                 }
			}
		}
	}
}