#!/bin/bash
server_id=root@vm09.intetics.com

ssh $server_id -p 2223 << EOF
	echo "------------------------------------------------------"
	echo "Stopping Tomcat & Lucene services on remote server ..."
	echo "------------------------------------------------------"
	pkill java

	/etc/init.d/desknet-lucene stop
	/etc/init.d/desknet-notifications stop
	/etc/init.d/desknet-emails stop
	/etc/init.d/tomcat8 stop

	echo "------------------------------------------------------"
	echo "Removing previous install ..."
	echo "------------------------------------------------------"
	rm -f /var/desknet-lucene/desknet-lucene.jar
	rm -r /var/desknet-lucene/logs/*
	rm -f /var/desknet-notifications/desknet-notifications.jar
	rm -r /var/desknet-notifications/logs/*
	rm -f /var/desknet-emails/desknet-emails.jar
    rm -r /var/desknet-emails/logs/*
	rm -r /var/lib/tomcat8/webapps/m
	rm -f /var/lib/tomcat8/webapps/m.war
	rm -r /var/lib/tomcat8/webapps/ROOT
	rm -f /var/lib/tomcat8/webapps/ROOT.war
	rm -r /var/lib/tomcat8/webapps/apidoc
	rm -f /var/lib/tomcat8/webapps/apidoc.war
	rm -r /var/lib/tomcat8/webapps/tomcat8/index
	rm -r /var/lib/tomcat8/webapps/tomcat8/export_files
	rm -r /var/lib/tomcat8/webapps/tomcat8/backup_files
	rm -r /var/lib/tomcat8/webapps/tomcat8/storage
	rm -r /var/lib/tomcat8/webapps/tomcat8/billing_files
	rm -r /var/lib/tomcat8/webapps/tomcat8/desknet_report_files
	rm -r /var/lib/tomcat8/webapps/tomcat8/methode_files
	rm -f /var/lib/tomcat8/webapps/tomcat8/*
EOF

echo "------------------------------------------------------"
echo "Copying new build files ..."
echo "------------------------------------------------------"
scp -P 2223 desknet* $server_id://var/lib/tomcat8/webapps/

ssh $server_id -p 2223 << EOF
#    export LC_TYPE="en_US.UTF-8"
#    export LC_ALL="en_US.UTF-8"

	echo "------------------------------------------------------"
	echo "Configuring new build  ..."
	echo "------------------------------------------------------"
	mv /var/lib/tomcat8/webapps/*lucene*.jar /var/desknet-lucene/desknet-lucene.jar
	chmod +x /var/desknet-lucene/desknet-lucene.jar
	chown root /var/desknet-lucene/desknet-lucene.jar
	mv /var/lib/tomcat8/webapps/*notifications*.jar /var/desknet-notifications/desknet-notifications.jar
    chmod +x /var/desknet-notifications/desknet-notifications.jar
    chown root /var/desknet-notifications/desknet-notifications.jar
	mv /var/lib/tomcat8/webapps/*mobile*.war /var/lib/tomcat8/webapps/m.war
	zip /var/lib/tomcat8/webapps/m.war /WEB-INF/classes/application.properties
	mv /var/lib/tomcat8/webapps/desknet*.war /var/lib/tomcat8/webapps/ROOT.war
	zip /var/lib/tomcat8/webapps/ROOT.war /WEB-INF/classes/application.properties
	mv /var/lib/tomcat8/webapps/apidoc*.war /var/lib/tomcat8/webapps/apidoc.war
	chmod -R a+wrx /var/lib/tomcat8/webapps
	cp -r /storage /var/log/tomcat8
	chown -R tomcat8 /var/log/tomcat8/storage

#	echo "------------------------------------------------------"
#	echo "Disabling ReCaptcha for automation purposes ..."
#	echo "------------------------------------------------------"
#	sed -i 's/recaptcha.secret = 6LfyqikUAAAAAMhcmoZw-BLCg3pMhygm2QDqIkkt/recaptcha.secret = 6LeIxAcTAAAAAGG-vFI1TnRWxMZNFuojJ4WifJWe/' /WEB-INF/classes/application.properties
#    sed -i '1s/.*/var sitekey = "6LeIxAcTAAAAAJcZVRqyHh71UMIEGNQ_MXjiZKhI";/' /var/lib/tomcat8/webapps/ROOT/scripts/reCaptcha.js
#    perl -pi -e 's/\r\n/\n/;' /var/lib/tomcat8/webapps/ROOT/scripts/reCaptcha.js

	echo "------------------------------------------------------"
	echo "Starting Tomcat & Lucene services on remote server ..."
	echo "------------------------------------------------------"
	/etc/init.d/tomcat8 start
	/etc/init.d/desknet-lucene start
	/etc/init.d/desknet-notifications start
	/etc/init.d/desknet-emails start
EOF
