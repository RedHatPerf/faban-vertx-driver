package org.jboss.vertx.perf.driver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReferenceArray;
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
import com.sun.faban.driver.transport.sunhttp.ThreadCookieHandler;

/**
 * Purpose of this driver is to simulate application traffic. Without
 * implementing the application. Configuration determines the rate packets are
 * sent for each bucket (packet size range).
 *
 * @author Jeremy Whiting jwhiting@redhat.com
 *
 */

@BenchmarkDriver(name = "Vertx Driver", threadPerScale = 1, metric = "1threadperscale")
@FlatMix(operations = { "postBucket00", "postBucket01", "postBucket02",
      "postBucket03", "postBucket04", "postBucket05", "postBucket06", 
      "postBucket07", "postBucket08", "postBucket09", "postBucket10", 
      "postBucket11", "postBucket12", "postBucket13", "postBucket14", 
      "postBucket15", "postBucket16", "postBucket17", "postBucket18", 
      "postBucket19" }, mix = { 57.4633871211, 2.2707518051, 1.797903862, 0.0211184811, 0.1571008961, 0.0128771226, 1.9940224397, 11.3581372469, 0.5431570326, 5.6522842084, 1.4258837891, 1.0550226573, 0.4103938983, 0.2192973984, 0.1722959008, 0.3617183748, 0.0730132853, 0.2092532428, 0.1437086886, 14.6586725489}, deviation = 2)

public class PacketDistributionDriver implements Serializable {

   private static final long serialVersionUID = 5788884811324069023L;
   private DriverContext ctx;
   private HttpTransport transport;
   private static Logger logger;
   private static URL postURL;
   private Map<String, String> headers;
   private static AtomicReferenceArray<byte[]> postData;
   // TODO: bucket packet size generated randomly
   private boolean random;
   private static final String CONTENT_LENGTH = "content-length";
   private int threadNum;
   private static int threadCount;
   private static CountDownLatch configLatch = new CountDownLatch(1);
   private static CountDownLatch initLatch = new CountDownLatch(1);

   static {
      if (postData == null) {
         // postData = new byte[20][];
         postData = new AtomicReferenceArray<byte[]>(20);
      }
   }

   @BenchmarkOperation(name = "postBucket00", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket00() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket00");
      try {
         post(getBody(0), "00");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket00");
      }
   }

   @BenchmarkOperation(name = "postBucket01", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket01() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
      try {
         post(getBody(1), "01");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
      }
   }

   @BenchmarkOperation(name = "postBucket02", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket02() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
      try {
         post(getBody(2), "02");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
      }
   }

   @BenchmarkOperation(name = "postBucket03", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket03() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
      try {
         post(getBody(3), "03");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
      }
   }

   @BenchmarkOperation(name = "postBucket04", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket04() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
      try {
         post(getBody(4), "04");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
      }
   }

   @BenchmarkOperation(name = "postBucket05", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket05() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
      try {
         post(getBody(5), "05");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
      }
   }

   @BenchmarkOperation(name = "postBucket06", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket06() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
      try {
         post(getBody(6), "06");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
      }
   }

   @BenchmarkOperation(name = "postBucket07", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket07() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
      try {
         post(getBody(7), "07");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
      }
   }

   @BenchmarkOperation(name = "postBucket08", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket08() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
      try {
         post(getBody(8), "08");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
      }
   }

   @BenchmarkOperation(name = "postBucket09", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket09() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
      try {
         post(getBody(9), "09");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
      }
   }

   @BenchmarkOperation(name = "postBucket10", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket10() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
      try {
         post(getBody(10), "10");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
      }
   }

   @BenchmarkOperation(name = "postBucket11", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket11() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
      try {
         post(getBody(11), "11");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
      }
   }

   @BenchmarkOperation(name = "postBucket12", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket12() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
      try {
         post(getBody(12), "12");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
      }
   }

   @BenchmarkOperation(name = "postBucket13", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket13() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
      try {
         post(getBody(13), "13");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
      }
   }

   @BenchmarkOperation(name = "postBucket14", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket14() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
      try {
         post(getBody(14), "14");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
      }
   }

   @BenchmarkOperation(name = "postBucket15", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket15() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
      try {
         post(getBody(15), "15");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
      }
   }

   @BenchmarkOperation(name = "postBucket16", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket16() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
      try {
         post(getBody(16), "16");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
      }
   }

   @BenchmarkOperation(name = "postBucket17", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket17() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
      try {
         post(getBody(17), "17");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
      }
   }

   @BenchmarkOperation(name = "postBucket18", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket18() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
      try {
         post(getBody(18), "18");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
      }
   }

   @BenchmarkOperation(name = "postBucket19", max90th = 2.0, timing = Timing.AUTO)
   @FixedTime(cycleType = CycleType.CYCLETIME, cycleTime = 1000, cycleDeviation = 2)
   public void postBucket19() throws FatalException {
      getLogger().entering(PacketDistributionDriver.class.getName(), "postBucket01");
      try {
         post(getBody(19), "19");
      } finally {
         getLogger().exiting(PacketDistributionDriver.class.getName(), "postBucket01");
      }
   }

   protected void post(byte[] body, String bucket) throws FatalException {
      if (body.length == 0){
         return;
      }
      getHeaders().put(CONTENT_LENGTH, new Integer(body.length).toString());
      getHeaders().put("bucket", bucket);
      HttpTransport t = getTransport();
      try {
         t.readURL(getPostUrl(), body, headers);
      } catch (IOException ioe) {
         throw new FatalException("Network problem processing request/response", ioe);
      }
      checkResponse(t);
   }

   private HttpTransport checkResponse(HttpTransport t) throws FatalException {
      int rc = t.getResponseCode();
      if (200 != rc) {
         throw new FatalException(String.format(
               "The http response contained a failure code [%1$d] with this body [%2$s] and these headers [%3$s]", rc,
               t.getResponseBuffer(), t.dumpResponseHeaders()));
      }
      return t;
   }

   protected void configure() {
      logger = ctx.getLogger();
      logger.entering(PacketDistributionDriver.class.getName(), "configure");
      threadNum = threadCount++;
      doConfigOncePerAgent(configLatch);
      doInitOncePerAgent(initLatch);
      logger.exiting(PacketDistributionDriver.class.getName(), "configure");
   }

   private void doConfigOncePerAgent(CountDownLatch latch) throws FatalException{
      try {
         if (threadNum == 0) {
            try {
               postURL = new URL(ctx.getXPathValue("//fd:value[../fd:name='postURL']"));
            } catch (MalformedURLException murle) {
               logger.severe(
                     String.format("Url provided is malformed [%1$s] reason [%2$s].", postURL, murle.getMessage()));
               throw new FatalException(
                     String.format("Url provided is malformed [%1$s] reason [%2$s].", postURL, murle.getMessage()), murle);
            } catch (XPathExpressionException xpathe) {
               throw new FatalException("Exception using the xpath to get the [postURL] value.", xpathe);
            }
            headers.put("Keep-Alive", "true");
            headers.put("Content-Type", "application/json");
         } else {
            latch.await();
         }
      } catch (Throwable e) {
         throw new FatalException("Fatal error during configure.", e);
      } finally {
         latch.countDown();
         logger.exiting(PacketDistributionDriver.class.getName(), "configure");
      }
   }

   private void doInitOncePerAgent(CountDownLatch latch){
      try {
         if (threadNum == 0) {
            try {
               Charset c = Charset.forName("UTF-8");
               char[] b = new String("hellosun").toCharArray();
   
               createBytes(0, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-00-content-size-in-bytes']"), b,
                     c);
               createBytes(1, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-01-content-size-in-bytes']"), b,
                     c);
               createBytes(2, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-02-content-size-in-bytes']"), b,
                     c);
               createBytes(3, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-03-content-size-in-bytes']"), b,
                     c);
               createBytes(4, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-04-content-size-in-bytes']"), b,
                     c);
               createBytes(5, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-05-content-size-in-bytes']"), b,
                     c);
               createBytes(6, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-06-content-size-in-bytes']"), b,
                     c);
               createBytes(7, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-07-content-size-in-bytes']"), b,
                     c);
               createBytes(8, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-08-content-size-in-bytes']"), b,
                     c);
               createBytes(9, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-09-content-size-in-bytes']"), b,
                     c);
               createBytes(10, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-10-content-size-in-bytes']"), b,
                     c);
               createBytes(11, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-11-content-size-in-bytes']"), b,
                     c);
               createBytes(12, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-12-content-size-in-bytes']"), b,
                     c);
               createBytes(13, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-13-content-size-in-bytes']"), b,
                     c);
               createBytes(14, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-14-content-size-in-bytes']"), b,
                     c);
               createBytes(15, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-15-content-size-in-bytes']"), b,
                     c);
               createBytes(16, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-16-content-size-in-bytes']"), b,
                     c);
               createBytes(17, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-17-content-size-in-bytes']"), b,
                     c);
               createBytes(18, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-18-content-size-in-bytes']"), b,
                     c);
               createBytes(19, getDriverContext().getXPathValue("//fd:value[../fd:name='bucket-19-content-size-in-bytes']"), b,
                     c);
               validate();
            } catch (XPathExpressionException xpathe) {
               throw new FatalException("Exception using the xpath to get the [content-size-in-bytes] value.", xpathe);
            }
         } else {
            latch.await();
         }
      } catch (Throwable e) {
         throw new FatalException("Fatal error during initialization.", e);
      } finally {
         latch.countDown();
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
         validateBody(0);
         validateBody(1);
         validateBody(2);
         validateBody(3);
         validateBody(4);
         validateBody(5);
         validateBody(6);
         validateBody(7);
         validateBody(8);
         validateBody(9);
         validateBody(10);
         validateBody(11);
         validateBody(12);
         validateBody(13);
         validateBody(14);
         validateBody(15);
         validateBody(16);
         validateBody(17);
         validateBody(18);
         validateBody(19);
      } finally {
         logger.exiting(PacketDistributionDriver.class.getName(), "validate");
      }
   }

   private void validateBody(int position){
      if (getBody(position) == null) {
         throw new FatalException(String.format("bucket at %1$d is null", position));
      }
   }

   /**
    * 
    * @param bucket array position
    * @param amount
    * @param template
    * @param c
    * @throws IllegalArgumentException
    * @throws IOException
    */
   protected void createBytes(int bucket, String amount, char[] template, Charset c)
         throws IllegalArgumentException, IOException {
      if (null == amount) {
         throw new IllegalArgumentException(String.format("amount parameter was null for bucket [%1$s]", bucket));
      }
      if (null == getBody(bucket)) {
         int sizeInBytes = Integer.parseInt(amount);
         if (sizeInBytes % template.length != 0){
            throw new  IllegalArgumentException(String.format("The amount of bytes configured [%3$s] for your bucket [%1$d] needs to be divisable by [%2$d].", bucket, template.length, amount));
         }

         ByteArrayOutputStream baos = null;
         try {
            baos = new ByteArrayOutputStream(sizeInBytes);
            int lastPos = asIndex(sizeInBytes);
            for (int i = 0; i < lastPos; i += template.length) {
               try {
                  IOUtils.write(template, baos, c);
               } catch (IndexOutOfBoundsException ioobe) {
                  logger.severe(String.format("Array handling issue [%1$d] size [%2$d]", i, sizeInBytes));
                  throw ioobe;
               }
            }
            byte[] b = baos.toByteArray();
            setBody(bucket, b);
            assert b.length == sizeInBytes : String.format("Length comparison of expected [%1$d] and actual [%2$d] failed.", sizeInBytes, b.length);
         } finally {
            IOUtils.closeQuietly(baos);
         }
      }
   }

   public PacketDistributionDriver() {
      ctx = DriverContext.getContext();
      transport = HttpTransport.newInstance();
      headers = new HashMap<String, String>();
      configure();
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

   byte[] getBody(int bucket) {
      return postData.get(bucket);
   }

   protected void setBody(int bucket, byte[] b) {
      postData.compareAndSet(bucket, null, b);
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
