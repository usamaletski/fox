#!/bin/bash
server_id=a.frantsuzov@demo.desk-net.com

ssh $server_id << EOF
	echo "------------------------------------------------------"
	echo "Stopping Tomcat & Lucene services on remote server ..."
	echo "------------------------------------------------------"

	sudo /etc/init.d/desknet-lucene stop
	sudo /etc/init.d/desknet-notifications stop
	sudo /etc/init.d/desknet-emails stop
	sudo /etc/init.d/tomcat8 stop


	echo "------------------------------------------------------"
	echo "Removing previous install ..."
	echo "------------------------------------------------------"
	sudo rm -f /var/desknet-lucene/desknet-lucene.jar
	sudo rm -r /var/desknet-lucene/logs/*
	sudo rm -f /var/desknet-notifications/desknet-notifications.jar
	sudo rm -r /var/desknet-notifications/logs/*
	sudo rm -f /var/desknet-emails/desknet-emails.jar
    sudo rm -r /var/desknet-emails/logs/*
	sudo rm -r /var/lib/tomcat8/webapps/m
	sudo rm -f /var/lib/tomcat8/webapps/m.war
	sudo rm -r /var/lib/tomcat8/webapps/ROOT
	sudo rm -f /var/lib/tomcat8/webapps/ROOT.war
#	sudo rm -r /var/lib/tomcat8/webapps/tomcat8/index
EOF

echo "------------------------------------------------------"
echo "Copying new build files ..."
echo "------------------------------------------------------"
scp desknet* $server_id://var/lib/tomcat8/webapps/

ssh $server_id << EOF
	echo "------------------------------------------------------"
	echo "Configuring new build  ..."
	echo "------------------------------------------------------"
	sudo mv /var/lib/tomcat8/webapps/*lucene*.jar /var/desknet-lucene/desknet-lucene.jar
	sudo chmod +x /var/desknet-lucene/desknet-lucene.jar
	sudo chown root /var/desknet-lucene/desknet-lucene.jar
	sudo mv /var/lib/tomcat8/webapps/*notifications*.jar /var/desknet-notifications/desknet-notifications.jar
    sudo chmod +x /var/desknet-notifications/desknet-notifications.jar
    sudo chown root /var/desknet-notifications/desknet-notifications.jar
    sudo mv /var/lib/tomcat8/webapps/*emails*.jar /var/desknet-emails/desknet-emails.jar
    sudo chmod +x /var/desknet-emails/desknet-emails.jar
    sudo chown root /var/desknet-emails/desknet-emails.jar
	sudo mv /var/lib/tomcat8/webapps/*mobile*.war /var/lib/tomcat8/webapps/m.war
	sudo zip /var/lib/tomcat8/webapps/m.war /WEB-INF/classes/application.properties
    sudo zip /var/lib/tomcat8/webapps/m.war /WEB-INF/applicationContext-mail.xml
	sudo mv /var/lib/tomcat8/webapps/desknet*.war /var/lib/tomcat8/webapps/ROOT.war
	sudo zip /var/lib/tomcat8/webapps/ROOT.war /WEB-INF/classes/application.properties
	sudo zip /var/lib/tomcat8/webapps/ROOT.war /WEB-INF/applicationContext-mail.xml
	sudo chmod -R a+wrx /var/lib/tomcat8/webapps

	echo "------------------------------------------------------"
	echo "Starting Tomcat & Lucene services on remote server ..."
	echo "------------------------------------------------------"
	sudo /etc/init.d/tomcat8 start
	sudo /etc/init.d/desknet-lucene start
	sudo /etc/init.d/desknet-notifications start
	sudo /etc/init.d/desknet-emails start
EOF