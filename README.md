# Spark Example

This is a simple example of a Spark 1.6.x application that reads data from a
Hive table using Spark SQL does some grouping and aggregation and saves the
output to a file on HDFS

Hive --> Spark --> HDFS

## Prerequistes

* JDK 1.8
* Spark 1.6.x
* Scala 2.10

## Usage

### Import

If you are using IntelliJ, import the project as Maven project using JDK 1.8

### Build

Use maven on the command line to create a jar.
```
mvn package
```

Find the jars, one with the other without dependencies in the target directory.

### Submit to HDP

In order to submit your application to an existing YARN cluster with Spark
1.6.x libraries installed you need to perform the following commands. The kinit
command is only required on a secure cluster.

```
kinit username  [-kt /path/to/keytab]
spark-submit --class com.hortonworks.SparkHiveExample --master yarn spark-example-1.0-SNAPSHOT.jar MyApplicationName /path/to/hdfs-dir/with/write/access/
```
