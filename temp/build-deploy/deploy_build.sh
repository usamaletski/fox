#!/bin/bash
server_id=root@vm.secret.com

ssh $server_id -p 2223 << EOF
	echo "------------------------------------------------------"
	echo "Stopping Tomcat & Lucene services on remote server ..."
	echo "------------------------------------------------------"
	pkill java

	/etc/init.d/secret-lucene stop
	/etc/init.d/secret-notifications stop
	/etc/init.d/secret-emails stop
	/etc/init.d/tomcat8 stop

	echo "------------------------------------------------------"
	echo "Removing previous install ..."
	echo "------------------------------------------------------"
	rm -f /var/secret-lucene/secret-lucene.jar
	rm -r /var/secret-lucene/logs/*
	rm -f /var/secret-notifications/secret-notifications.jar
	rm -r /var/secret-notifications/logs/*
	rm -f /var/secret-emails/secret-emails.jar
    rm -r /var/secret-emails/logs/*
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
	rm -r /var/lib/tomcat8/webapps/tomcat8/secret_report_files
	rm -r /var/lib/tomcat8/webapps/tomcat8/methode_files
	rm -f /var/lib/tomcat8/webapps/tomcat8/*
EOF

echo "------------------------------------------------------"
echo "Copying new build files ..."
echo "------------------------------------------------------"
scp -P 2223 secret* $server_id://var/lib/tomcat8/webapps/

ssh $server_id -p 2223 << EOF
#    export LC_TYPE="en_US.UTF-8"
#    export LC_ALL="en_US.UTF-8"

	echo "------------------------------------------------------"
	echo "Configuring new build  ..."
	echo "------------------------------------------------------"
	mv /var/lib/tomcat8/webapps/*lucene*.jar /var/secret-lucene/secret-lucene.jar
	chmod +x /var/secret-lucene/secret-lucene.jar
	chown root /var/secret-lucene/secret-lucene.jar
	mv /var/lib/tomcat8/webapps/*notifications*.jar /var/secret-notifications/secret-notifications.jar
    chmod +x /var/secret-notifications/secret-notifications.jar
    chown root /var/secret-notifications/secret-notifications.jar
	mv /var/lib/tomcat8/webapps/*mobile*.war /var/lib/tomcat8/webapps/m.war
	zip /var/lib/tomcat8/webapps/m.war /WEB-INF/classes/application.properties
	mv /var/lib/tomcat8/webapps/secret*.war /var/lib/tomcat8/webapps/ROOT.war
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
	/etc/init.d/secret-lucene start
	/etc/init.d/secret-notifications start
	/etc/init.d/secret-emails start
EOF
