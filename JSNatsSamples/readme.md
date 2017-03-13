#JSNatsSamples

this section relates to the streaming technology NATS.io this is a lightweight messaging technology written in language go! very efficient, easy to deploy.

##Prerequisites

##OSX
```
>brew install go
`#create directory /usr/local/Cellar/go/1.8/libexec/src/github.com/nats-io
>cd /usr/local/Cellar/go/1.8/libexec/src/github.com/nats-io
>git clone https://github.com/nats-io/nats-streaming-server.git
>git clone https://github.com/nats-io/go-nats-streaming.git
```
##Linux Ubuntu
```
>sudo add-apt-repository ppa:ubuntu-lxc/lxd-stable
>sudo apt-get update
>sudo apt-get install golang
```

Installing nats streeaming that contains all examples in addition to the server

```
# create the required directory to execute the following 'cd'
cd /usr/lib/go-1.6/src/github.com/nats-io/
# -E option to path all environment including $GOPATH
sudo -E go get github.com/nats-io/nats-streaming-server
sudo -E go get github.com/gogo/protobuf/proto
sudo -E go get github.com/gogo/protobuf/gogoproto
sudo -E go get github.com/nats-io/go-nats
```


##Test on two machines

each client that wants to publish can do it the following way

```
# using Go language 
>go run stan-pub.go -s http://192.168.0.10:4222 stan-pub.go "tettre"
#using Javascript
>./stan-pub.js -s http://192.168.0.10:4222 stan-pub.go "testrdggf"
```

Each client that wants to subscribe can do it the following way

```
# using Go language
>go run stan-sub.go -id toto -s http://192.168.0.10:4222 stan-pub.go
#using Javascript
>./stan-sub.js -id eric  -s http://192.168.0.10:4222 stan-pub.go
Elasticsearch
```

###Prerequisite

Glide package manager

```
brew install glide
```

```
[WARN]  Unable to checkout golang.org/x/crypto
[ERROR] Update failed for golang.org/x/crypto: Cannot detect VCS
[WARN]  Unable to checkout golang.org/x/sys
[ERROR] Update failed for golang.org/x/sys: Cannot detect VCS
[WARN]  Unable to checkout golang.org/x/text
[ERROR] Update failed for golang.org/x/text: Cannot detect VCS
[ERROR] Failed to install: Cannot detect VCS
```

```
sudo -E go get golang.org/x/sys/unix
sudo -E go get github.com/Sirupsen/logrus 
```


# Getting Elk plugin
```
sudo -E go get github.com/netlify/elastinats
```

Remark to test Elk plugin, it is required to make sure you are publishing in json otherwise nothing will happen you can test using benchmark on nats-io/go-nats/examples like :

```
# running 4 publishers 10 subscribers for 1,000,000 messages of size 1000 on topic stan-pub.go
go run nats-bench.go -s http://192.168.0.10:4222  -np 4 -ns 10 -n 1000000 -ms 10000 stan-pub.go
```