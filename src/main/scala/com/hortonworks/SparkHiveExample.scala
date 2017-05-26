package com.hortonworks

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.sql.functions._

/**
 * test
 *
 */
object SparkHiveExample extends App {

        val application_name = args(0)
        val target_directory = args(1)

        val conf = new SparkConf().setAppName(application_name)
        val sc = new SparkContext(conf)
        val sqlContext = new HiveContext(sc)
        val disp_loc_df = sqlContext.sql("select dispatching_base_num, locationid from mine.uber")
        val zonelookup_df = sqlContext.sql("select locationid, borough, zone from mine.taxizonelookup")
        val fulldf = disp_loc_df.join(zonelookup_df, disp_loc_df("locationid") === zonelookup_df("locationid"))
        val group = fulldf.groupBy("borough", "zone")
        val agg = group.agg(count(disp_loc_df("locationid"))).orderBy("borough", "zone")
        agg.map(x => x.mkString(",")).saveAsTextFile(target_directory)
}
