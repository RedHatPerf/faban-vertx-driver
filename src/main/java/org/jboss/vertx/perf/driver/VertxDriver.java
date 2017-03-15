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
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.io.IOUtils;

import com.sun.faban.driver.BenchmarkDriver;
import com.sun.faban.driver.BenchmarkOperation;
import com.sun.faban.driver.CycleType;
import com.sun.faban.driver.DriverContext;
import com.sun.faban.driver.FatalException;
import com.sun.faban.driver.HttpTransport;
import com.sun.faban.driver.MatrixMix;
import com.sun.faban.driver.NegativeExponential;
import com.sun.faban.driver.OnceBefore;
import com.sun.faban.driver.Row;
import com.sun.faban.driver.Timing;

/**
 * Driver to send http requests
 * @author Jeremy Whiting <jwhiting@redhat.com>
 *
 */
@BenchmarkDriver (
    name = "VertxDriver",
    threadPerScale = 1,
    metric="Verts"
)

@MatrixMix(
      operations= {
            "doPost"
      },
      mix={@Row({100})},
      deviation=2
)

@NegativeExponential (
    cycleType = CycleType.THINKTIME,
    cycleMean = 400,
    cycleMin  = 100,
    cycleDeviation = 2
)

public class VertxDriver implements Serializable{

  private static final long   serialVersionUID = 1L;
  private DriverContext       ctx;
  private HttpTransport       http;
  private static Logger              logger;
  private URL                 postURL;
  private Map<String, String> headers;
  private static AtomicReference<byte[]> postData = null;

  @BenchmarkOperation(
      name = "doPost",
      max90th=2.0,
      timing = Timing.AUTO
   )
  public void doSimplePost() throws UnexpectedException{
    logger.entering(VertxDriver.class.getName(),"doPost");
    try {
      http.readURL(postURL, postData.get(), headers);
    } catch (IOException ioe) {
      throw new UnexpectedException(ioe.getMessage());
    } finally {
       logger.exiting(VertxDriver.class.getName(),"doPost");
    }
  }

  public void configure(){
    logger.entering(VertxDriver.class.getName(), "configure");
    try {
       try {
         postURL = new URL(ctx.getXPathValue("/VertxBenchmark/fa:runConfig/fd:driverConfig[@name='VertxDriver']/fd:properties/fd:property[fd:name='postURL']/fd:value"));
       } catch (MalformedURLException murle) {
         throw new FatalException(String.format("Url provided is malformed [%1$s] reason [%2$s].", postURL, murle.getMessage()), murle);
       } catch (XPathExpressionException xpathe) {
         throw new FatalException("Exception using the xpath to get the [postURL] value.", xpathe);
       }
       Charset c = Charset.forName("UTF-8");
       char[] b = new String("hellosun").toCharArray();

       try {
         if (postData.get() == null){
            int sizeInK = Integer.parseInt(ctx.getXPathValue("/VertxBenchmark/fa:runConfig/fd:driverConfig[@name='VertxDriver']/fd:properties/fd:property[fd:name='content-size-in-k']/fd:value"));
            int sizeInBytes = asBytes(sizeInK) ;
            headers.put("content-length", new Integer(sizeInBytes).toString());
            ByteArrayOutputStream baos = new ByteArrayOutputStream(sizeInBytes);
            sizeInBytes = asIndex(sizeInBytes);
            for (int i = 0; i < sizeInBytes; i += b.length ){
              try {
                IOUtils.write (b, baos, c);
              } catch (IndexOutOfBoundsException ioobe){
                logger.severe(String.format("Array handling issue [%1$d] size [%2$d]", i, sizeInBytes));
                throw ioobe;
              }
            }
            postData.compareAndSet(null, baos.toByteArray());
         }
       } catch (XPathExpressionException xpathe) {
         throw new FatalException("Exception using the xpath to get the [content-size-in-k] value.", xpathe);
       }
    } catch (Throwable e){
       throw new FatalException("Fatal error during configure.", e);
    } finally {
       logger.exiting(VertxDriver.class.getName(), "configure");
    }
  }

  public void validate(){
    logger.entering(VertxDriver.class.getName(), "validate");
    try {
       if (null == postURL){
          throw new FatalException("postURL field is null.");
       }
       if (null == postData.get()) {
          throw new FatalException("postData is null");
       }
       if (null == headers){
          throw new FatalException("headers is null");
       }
    } finally {
       logger.exiting(VertxDriver.class.getName(), "validate");
    }
  }

  @OnceBefore
  public void preRun() {
  }

  public VertxDriver() {
    ctx     = DriverContext.getContext();
    http    = HttpTransport.newInstance();
    logger  = ctx.getLogger();
    headers = new HashMap<String, String>();
    configure();
    validate();
  }

  private int asBytes(int k){
    return k*1024;
  }

  private int asIndex(int s){
     return s -=1;
  }
}

