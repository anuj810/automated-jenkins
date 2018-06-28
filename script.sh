#1/bin/bash
cd jenkins-master
make stop
make clean-data
make clean-images
make build
make run
sleep 30
docker exec -it jenkins_master_1 bash -c "cd /var/jenkins_home; bash change.sh"
docker restart jenkins_master_1
