variable "key_name" {
  description = "EC2 key pair"
  type        = string
  default     = "jenkins-key"
}

variable "public_key_path" {
  description = "public SSH key"
  type        = string
  default     = "/Users/snehaavula/.ssh/id_rsa.pub"
}

variable "ami_id" {
  description = "EC2 instance"
  type        = string
  default     = "ami-0c96815ba84c71c13"
}
