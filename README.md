Bounding Box Detector

Reads a grid of characters made up of asterisks and dashes, and finds the largest non-overlapping group of connected asterisks. It then prints the smallest rectangle (bounding box) that contains that group. The groups are connected only horizontally and vertically, not diagonally.

Using Java and Gradle for building and testing. There are both unit tests and integration tests included. The code is also set up to work with a Jenkins CI/CD pipeline, and you can deploy the infrastructure using Terraform and AWS.

You can run all the tests using './gradlew test'. This runs both unit tests and integration tests.

You can prepare a file with a character grid and can run it through Gradle using:

cat boxes.txt | ./gradlew run

For CI/CD, the project includes a Jenkinsfile. The pipeline checks out the code from GitHub, builds the project, runs tests and deploy the infrastructure using Terraform. To trigger the pipeline, just push your code to GitHub. Jenkins will pick it up automatically if it’s set up with a webhook. You’ll also need to configure AWS credentials in Jenkins to allow Terraform to access your AWS account. These should be added as secure credentials in Jenkins, not hardcoded anywhere in the code.

If you're deploying to AWS, you can use the Terraform files provided. Run terraform init for setup, then terraform plan to preview the changes, and finally terraform apply to deploy. 
Get the public IP address for your Jenkins server, which can be opened in a browser to finish the Jenkins setup.
