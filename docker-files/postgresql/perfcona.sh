apt update
apt install -y wget gnupg2 curl lsb-release
wget https://repo.percona.com/apt/percona-release_latest.generic_all.deb
dpkg -i percona-release_latest.generic_all.deb
percona-release setup ppg13
apt-get install percona-pg-stat-monitor13