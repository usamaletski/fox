#!/bin/bash
server_id=user@citest.secret.com

ssh $server_id << EOF
	echo "------------------------------------------------------"
	echo "Stopping Tomcat & Lucene services on remote server ..."
	echo "------------------------------------------------------"
    pkill java

	sudo /etc/init.d/secret-lucene stop
	sudo /etc/init.d/secret-notifications stop
	sudo /etc/init.d/secret-emails stop
	sudo /etc/init.d/tomcat8 stop


	echo "------------------------------------------------------"
	echo "Removing previous install ..."
	echo "------------------------------------------------------"
	sudo rm -f /var/secret-lucene/secret-lucene.jar
	sudo rm -r /var/secret-lucene/logs/*
	sudo rm -f /var/secret-notifications/secret-notifications.jar
	sudo rm -r /var/secret-notifications/logs/*
	sudo rm -f /var/secret-emails/secret-emails.jar
    sudo rm -r /var/secret-emails/logs/*
	sudo rm -r /var/lib/tomcat8/webapps/m
	sudo rm -f /var/lib/tomcat8/webapps/m.war
	sudo rm -r /var/lib/tomcat8/webapps/ROOT
	sudo rm -f /var/lib/tomcat8/webapps/ROOT.war
#	sudo rm -r /var/lib/tomcat8/webapps/tomcat8/index
EOF

echo "------------------------------------------------------"
echo "Copying new build files ..."
echo "------------------------------------------------------"
scp secret* $server_id://var/lib/tomcat8/webapps/

ssh $server_id << EOF
	echo "------------------------------------------------------"
	echo "Configuring new build  ..."
	echo "------------------------------------------------------"
	sudo mv /var/lib/tomcat8/webapps/*lucene*.jar /var/secret-lucene/secret-lucene.jar
	sudo chmod +x /var/secret-lucene/secret-lucene.jar
	sudo chown root /var/secret-lucene/secret-lucene.jar
	sudo mv /var/lib/tomcat8/webapps/*notifications*.jar /var/secret-notifications/secret-notifications.jar
    sudo chmod +x /var/secret-notifications/secret-notifications.jar
    sudo chown root /var/secret-notifications/secret-notifications.jar
    sudo mv /var/lib/tomcat8/webapps/*emails*.jar /var/secret-emails/secret-emails.jar
    sudo chmod +x /var/secret-emails/secret-emails.jar
    sudo chown root /var/secret-emails/secret-emails.jar
	sudo mv /var/lib/tomcat8/webapps/*mobile*.war /var/lib/tomcat8/webapps/m.war
	sudo zip /var/lib/tomcat8/webapps/m.war /WEB-INF/classes/application.properties
	sudo mv /var/lib/tomcat8/webapps/secret*.war /var/lib/tomcat8/webapps/ROOT.war
	sudo zip /var/lib/tomcat8/webapps/ROOT.war /WEB-INF/classes/application.properties
	sudo chmod -R a+wrx /var/lib/tomcat8/webapps

	echo "------------------------------------------------------"
	echo "Starting Tomcat & Lucene services on remote server ..."
	echo "------------------------------------------------------"
	sudo /etc/init.d/tomcat8 start
	sudo /etc/init.d/secret-lucene start
	sudo /etc/init.d/secret-notifications start
	sudo /etc/init.d/secret-emails start
EOF
