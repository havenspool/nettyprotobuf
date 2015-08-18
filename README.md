# nettyprotobuf
#netty 5.0.0.Alpha2
#protobuf 3.0.0-alpha-2

# proto
protoc -IPATH=.proto文件所在得目录 --java_out=java文件的输出路径  .proto的名称

protoc --java_out=/home/havens/Work/GitCode/nettyprotobuf/src/main/java/  MessageVo.proto
