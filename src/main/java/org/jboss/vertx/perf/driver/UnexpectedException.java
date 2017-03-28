package org.jboss.vertx.perf.driver;

public class UnexpectedException extends Exception {

   private static final long serialVersionUID = 6982480606946100673L;

   public UnexpectedException(){
      super();
   }
   public UnexpectedException(String message){
      super(message);
   }
   public UnexpectedException(String m, Throwable t){
      super(m,t);
   }
}
