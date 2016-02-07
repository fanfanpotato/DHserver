#! /bin/bash
echo "Clean all the content from the filter table"
sudo iptables -F INPUT
sudo iptables -P INPUT ACCEPT
sudo iptables -F FORWARD
sudo iptables -P FORWARD ACCEPT
sudo iptables -F OUTPUT
sudo iptables -P OUTPUT ACCEPT
echo "Show the clean filter table"
sudo iptables -t filter --list
echo "Please write what kind of protocol type can pass through"
read proto
echo "Please specify the the destination IP address"
read address
echo "Add the rule"
sudo iptables -A INPUT -p $proto -s $address -i eth1 -j ACCEPT
sudo iptables -A INPUT -p $proto -s $address -i eth1 -j LOG
echo "Block other rule"
sudo iptables -A INPUT -i eth1 -j DROP
echo "Show filter table"
sudo iptables -L
