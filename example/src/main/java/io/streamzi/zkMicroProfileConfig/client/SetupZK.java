package io.streamzi.zkMicroProfileConfig.client;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.logging.Logger;

/**
 * Class to add properties to Zookeeper for use with the MicroProfile Config Source Example
 */
public class SetupZK {
    private static final Logger logger = Logger.getLogger(SetupZK.class.getName());

    private static final String zkUrl = "localhost:2181";
    private static final String applicationId = "com.example.zkexample";

    public static void main(String[] args) throws Exception {

        logger.info("Adding properties to Zookeeper");

        CuratorFramework client = CuratorFrameworkFactory.newClient(zkUrl, new ExponentialBackoffRetry(1000, 3));
        client.start();

        client.createContainers("/" + applicationId + "/strProp");
        client.setData().forPath("/" + applicationId + "/strProp", "zk_value".getBytes());

        client.createContainers("/" + applicationId + "/intProp");
        client.setData().forPath("/" + applicationId + "/intProp", "17".getBytes());

        client.close();

    }

}
