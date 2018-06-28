#!/bin/bash
sed -i '/<clouds\/>/d' /var/jenkins_home/config.xml
sed -i '/<hudson>/r docker_cloud_config' /var/jenkins_home/config.xml 
sed -i '/<name>docker<\/name>/r docker_slave_template_config' /var/jenkins_home/config.xml
