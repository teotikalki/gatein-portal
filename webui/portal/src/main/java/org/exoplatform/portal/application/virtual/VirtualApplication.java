package org.exoplatform.portal.application.virtual;

import java.io.Serializable;

/**
 * @author <a href="trongtt@exoplatform.com">Trong Tran</a>
 * @version $Revision$
 */
public class VirtualApplication implements Serializable {

  private String virtual;

  private String real;
  
  public String getVirtual() {
    return virtual;
  }

  public String getReal() {
    return real;
  }

}
