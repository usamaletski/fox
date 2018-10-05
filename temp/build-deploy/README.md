## Deploy build on VM (vm09.intetics.com as example)

#### 1. Install required packages 
`sudo apt-get update && sudo apt-get install openssh`

#### 2. Check if you already have public key
```
➜  ~ ls ~/.ssh/id_rsa.pub  
/Users/alex/.ssh/id_rsa.pub
```

In case your output looks like this
```
➜  ~ ls ~/.ssh/id_rsa.pub
ls: /Users/alex/.ssh/id_rsa.pub: No such file or directory
``` 

Then generate a pair of authentication keys (DO NOT ENTER PASSPHRASE)
```
➜  ~ ssh-keygen -t rsa
Generating public/private rsa key pair.
Enter file in which to save the key (/home/a/.ssh/id_rsa): 
Created directory '/home/a/.ssh'.
Enter passphrase (empty for no passphrase): 
Enter same passphrase again: 
Your identification has been saved in /home/a/.ssh/id_rsa.
Your public key has been saved in /home/a/.ssh/id_rsa.pub.
The key fingerprint is:
3e:4f:05:79:3a:9f:96:7c:3b:ad:e9:58:37:bc:37:e4 a@A
```

#### 3. Add your public key to _authorized_keys_ on server
`cat ~/.ssh/id_rsa.pub | ssh root@vm09.intetics.com 'cat >> ~/.ssh/authorized_keys'`

#### 4. From now on you can log into vm09.intetics.com as ROOT without password
`ssh root@vm09.intetics.com`

#### 5. Copy all required JARs to _/path/to/build-deploy_ folder and execute deployment script
`./deploy_build.sh`

## Deploy build on CI (citest.desk-net.com as example)

#### 1. Install required packages 
`sudo apt-get update && sudo apt-get install sshpass`

#### 2. Update _cideploy_build.sh_ with own username and password
```
#!/bin/bash
server_id=[your user name]@citest.desk-net.com
pwd=[your password]
```

#### 3. Copy all required JARs to _/path/to/build-deploy_ folder and execute deployment script
`./cideploy_build.sh`