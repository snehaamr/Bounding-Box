variable "key_name" {
  description = "EC2 key pair"
  type        = string
  default     = "jenkins-key"
}

variable "public_key" {
  description = "public SSH key"
  type        = string
  default     = "/Users/snehaavula/.ssh/id_rsa.pub"
}

variable "ami_id" {
  description = "EC2 instance"
  type        = string
  default     = "ami-01e3c4a339a264cc9"
}
