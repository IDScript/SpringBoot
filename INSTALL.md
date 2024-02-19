# Spring Boot RESTful API

## Table Of Content

- [Tech Stack](#tech-stack)
  - [Server](#server)
  - [Java JDK 21.0.1](#java-spec)
  - [MySQL 15.1](#mysql-spec)
  - [Apache Maven 3.9.6](#maven-spec)
- [Server Installation and Configuration](#server-installation-and-configuration)
  - [Systemd](#systemd)
    - [Copy File](#copy-file)
    - [Symbolic Link](#symbolic-link)
    - [Reload systemd files](#reload-systemd-files)
  - [Start App](#start-app)
  - [Add App Config to Env Variable](#add-app-config-to-env-variable)
  - [Sudo access for CI/CD](#sudo-access-for-cicd)

## Tech Stack

- [Server](#server)
- [Java JDK 21.0.1](#java-spec)
- [MySQL 15.1](#mysql-spec)
- [Apache Maven 3.9.6](#maven-spec)

### Server

```bash
lsb_release -a
```

```bash
Distributor ID: Ubuntu
Description:    Ubuntu 22.04.4 LTS
Release:        22.04
Codename:       jammy
```

### Java Spec

```bash
java --version
```

```bash
openjdk 21.0.1 2023-10-17
OpenJDK Runtime Environment (build 21.0.1+12-29)
OpenJDK 64-Bit Server VM (build 21.0.1+12-29, mixed mode, sharing)
```

### MySQL Spec

```bash
mysql -V
```

```bash
mysql  Ver 15.1 Distrib 10.6.16-MariaDB, for debian-linux-gnu (x86_64) using  EditLine wrapper
```

### Maven Spec

```bash
mvn -v
```

```bash
Apache Maven 3.9.6 (bc0240f3c744dd6b6ec2920b3cd08dcc295161ae)
Maven home: /home/idscript/dev/mvn
Java version: 21.0.1, vendor: Oracle Corporation, runtime: /home/idscript/dev/jdk
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "6.2.0-1018-aws", arch: "amd64", family: "unix"
```

## Server Installation and Configuration

### Systemd

Create file with ``.service`` extension Ex: ``budgetin.service``

```systemd
[Unit]
Description=Budgetin port 8090
After=network-online.target syslog.target
Wants=network-online.target systemd-networkd-wait-online.service

[Service]
RestartSec=5
User=idscript
Group=idscript
TimeoutStopSec=10
Restart=on-failure
StandardError=syslog
StandardOutput=syslog
SuccessExitStatus=143
Environment=JAVA_HOME=/home/idscript/dev/jdk
WorkingDirectory=/home/idscript/work/SpringBoot
ExecStart=/home/idscript/dev/mvn/bin/mvn spring-boot:run

[Install]
WantedBy=multi-user.target
```

#### Copy File

copy `.service` file to `systemd` directory in `/usr/lib/systemd/system/`

```bash
sudo cp budgetin.service /usr/lib/systemd/system/budgetin.service
```

#### Symbolic Link

or you can create symbolic link

```bash
sudo ln -s /home/path/budgetin.service /usr/lib/systemd/system/budgetin.service
```

#### Reload systemd files

```bash
sudo systemctl daemon-reload
```

### Start app

now you can run your app with following command

```bash
sudo systemctl start budgetin
```

to stop the app you can run

```bash
sudo systemctl stop budgetin
```

or if you want restart your app

```bash
sudo systemctl restart budgetin
```

if you want check your app status run the command below

```bash
sudo watch systemctl status budgetin
```

because in `.service` file we add `StandardError=syslog` and `StandardOutput=syslog` so app log will avail in `syslog`

```bash
sudo watch tail /var/log/syslog
```

### Add App Config to Env Variable

```bash
export $(cat .env | xargs)
```

### Sudo access for CI/CD

if you want add CI/CD to your app with this method, you must add folowing step for skip passwort prompt when run `systemctl` from CI/CD Proccess

open `sudoers` file

```bash
sudo vi /etc/sudoers
```

and add following code

```config
username ALL=(ALL) NOPASSWD: /usr/bin/systemctl
```
