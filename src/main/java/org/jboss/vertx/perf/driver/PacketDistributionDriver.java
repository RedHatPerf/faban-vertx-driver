package org.jboss.vertx.perf.driver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.io.IOUtils;

import com.sun.faban.driver.BenchmarkDriver;
import com.sun.faban.driver.BenchmarkOperation;
import com.sun.faban.driver.CycleType;
import com.sun.faban.driver.DriverContext;
import com.sun.faban.driver.FatalException;
import com.sun.faban.driver.FixedTime;
import com.sun.faban.driver.FlatMix;
import com.sun.faban.driver.HttpTransport;
import com.sun.faban.driver.Timing;

/**
 * Purpose of this driver is to simulate application traffic. Without
 * implementing the application. Configuration determines the rate packets are
 * sent for each bucket (packet size range).
 *
 * @author Jeremy Whiting jwhiting@redhat.com
 *
 */

@BenchmarkDriver(name = "Vertx Driver", threadPerScale = 1, metric = "1threadperscale")
@FlatMix(operations = { "postBucket01", "postBucket02" }, mix = { 50, 50 }, deviation = 2)

public class PacketDistributionDriver implements Serializable {

  private static final long serialVersionUID = 5788884811324069023L;
  private DriverContext ctx;
  private HttpTransport transport;
  private static Logger logger;
  private static URL postURL;
  private Map<String, String> headers;
  private static byte[][] postData;
  // TODO: bucket packet size generated randomly
  private boolean random;
  private static final String CONTENT_LENGTH = "content-length";

  static {
    if (postData == null) {
      postData = new byte[20][];
    }
  }

  @BenchmarkOperation(name = "postBucket01", max90th = 2.0, timing = Timing.AUTO)
  @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 100, cycleDeviation = 2)
  public void postBucket01() throws UnexpectedException {
    getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
    try {
      post(getBody(1));
    } catch (IOException ioe) {
      throw new UnexpectedException(ioe.getMessage());
    } finally {
      getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
    }
  }

  @BenchmarkOperation(name = "postBucket02", max90th = 2.0, timing = Timing.AUTO)
  @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 100, cycleDeviation = 2)
  public void postBucket02() throws UnexpectedException {
    getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket02");
    try {
      post(getBody(2));
    } catch (IOException ioe) {
      throw new UnexpectedException(ioe.getMessage());
    } finally {
      getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket02");
    }
  }

  protected void post(byte[] body) throws IOException {
    getHeaders().put(CONTENT_LENGTH, new Integer(body.length).toString());
    getTransport().readURL(getPostUrl(), body, headers);
  }

  protected void configure() {
    logger = ctx.getLogger();
    logger.entering(PacketDistributionDriver.class.getName(), "configure");
    try {
      try {
        // postURL = new URL(ctx.getXPathValue(
        // "/VertxBenchmark/fa:runConfig/fd:driverConfig[@name='PacketDistributionDriver']/fd:properties/fd:property[fd:name='postURL']/fd:value"));
        postURL = new URL(ctx.getXPathValue("//fd:value[../fd:name='postURL']"));
      } catch (MalformedURLException murle) {
        logger.severe(String.format("Url provided is malformed [%1$s] reason [%2$s].", postURL, murle.getMessage()));
        throw new FatalException(
            String.format("Url provided is malformed [%1$s] reason [%2$s].", postURL, murle.getMessage()), murle);
      } catch (XPathExpressionException xpathe) {
        throw new FatalException("Exception using the xpath to get the [postURL] value.", xpathe);
      }
      try {
        Charset c = Charset.forName("UTF-8");
        char[] b = new String("hellosun").toCharArray();
        createBytes(1, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-01-content-size-in-k']"), b, c);
        createBytes(2, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-02-content-size-in-k']"), b, c);
        createBytes(3, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-03-content-size-in-k']"), b, c);
        createBytes(4, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-04-content-size-in-k']"), b, c);
        createBytes(5, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-05-content-size-in-k']"), b, c);
        createBytes(6, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-06-content-size-in-k']"), b, c);
        createBytes(7, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-07-content-size-in-k']"), b, c);
        createBytes(8, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-08-content-size-in-k']"), b, c);
        createBytes(9, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-09-content-size-in-k']"), b, c);
        createBytes(10, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-10-content-size-in-k']"), b, c);
        createBytes(11, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-11-content-size-in-k']"), b, c);
        createBytes(12, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-12-content-size-in-k']"), b, c);
        createBytes(13, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-13-content-size-in-k']"), b, c);
        createBytes(14, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-14-content-size-in-k']"), b, c);
        createBytes(15, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-15-content-size-in-k']"), b, c);
        createBytes(16, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-16-content-size-in-k']"), b, c);
        createBytes(17, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-17-content-size-in-k']"), b, c);
        createBytes(18, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-18-content-size-in-k']"), b, c);
        createBytes(19, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-19-content-size-in-k']"), b, c);
        createBytes(20, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-20-content-size-in-k']"), b, c);
      } catch (XPathExpressionException xpathe) {
        throw new FatalException("Exception using the xpath to get the [content-size-in-k] value.", xpathe);
      }

    } catch (Throwable e) {
      throw new FatalException("Fatal error during configure.", e);
    } finally {
      logger.exiting(PacketDistributionDriver.class.getName(), "configure");
    }
  }

  protected void validate() {
    logger.entering(PacketDistributionDriver.class.getName(), "validate");
    try {
      if (null == postURL) {
        throw new FatalException("postURL field is null.");
      }
      if (null == postData) {
        throw new FatalException("postData is null");
      }
      if (null == headers) {
        throw new FatalException("headers is null");
      }
      if (null == getBody(1)){
        throw new FatalException("body content for bucket 01 is null");
      }
      if (null == getBody(2)){
        throw new FatalException("body content for bucket 02 is null");
      }
    } finally {
      logger.exiting(PacketDistributionDriver.class.getName(), "validate");
    }
  }

  protected void createBytes(int bucket, String amount, char[] template, Charset c)
      throws IllegalArgumentException, IOException {
    if (null == amount) {
      throw new IllegalArgumentException(String.format("amount parameter was null for bucket [%1$s]", bucket));
    }
    if (null == getBody(bucket)) {
      int sizeInK = Integer.parseInt(amount);
      int sizeInBytes = asBytes(sizeInK);

      ByteArrayOutputStream baos = new ByteArrayOutputStream(sizeInBytes);
      sizeInBytes = asIndex(sizeInBytes);
      for (int i = 0; i < sizeInBytes; i += template.length) {
        try {
          IOUtils.write(template, baos, c);
        } catch (IndexOutOfBoundsException ioobe) {
          logger.severe(String.format("Array handling issue [%1$d] size [%2$d]", i, sizeInBytes));
          throw ioobe;
        }
      }
      setBody(bucket, baos.toByteArray());
    }
  }

  public PacketDistributionDriver() {
    ctx = DriverContext.getContext();
    transport = HttpTransport.newInstance();
    headers = new HashMap<String, String>();
    configure();
    validate();
  }

  protected DriverContext getDriverContext() {
    return ctx;
  }

  protected Map<String, String> getHeaders() {
    return headers;
  }

  protected URL getPostUrl() {
    return postURL;
  }

  protected HttpTransport getTransport() {
    return transport;
  }

  protected byte[] getBody(int bucket) {
    return postData[asIndex(bucket)];
  }

  protected byte[] setBody(int bucket, byte[] b){
    postData[asIndex(bucket)] = b;
    return b;
  }

  protected byte[][] getBucketBodies() {
    return postData;
  }

  protected int asBytes(int k) {
    return k * 1024;
  }

  protected int asIndex(int s) {
    return s -= 1;
  }

  protected Logger getLogger() {
    return logger;
  }
}

/*
 * Example of benchmark run packet counts. ┌ Packet Distribution by Size
 * ─────────────────────────────────────────────────────────────────────────────
 * ────────── │ │ Packet size brackets for interface ens1 │ │ │ Packet Size
 * (bytes) Count Packet Size (bytes) Count │ 1 to 450: 446244 4501 to 4950:
 * 11073 │ 451 to 900: 17634 4951 to 5400: 8193 │ 901 to 1350: 13962 5401 to
 * 5850: 3187 │ 1351 to 1800: 164 5851 to 6300: 1703 │ 1801 to 2250: 1220 6301
 * to 6750: 1338 │ 2251 to 2700: 100 6751 to 7200: 2809 │ 2701 to 3150: 15485
 * 7201 to 7650: 567 │ 3151 to 3600: 88204 7651 to 8100: 1625 │ 3601 to 4050:
 * 4218 8101 to 8550: 1116 4051 to 4500: 43894 8551 to 9000+: 113835
 */
