package org.exoplatform.portal.application.virtual;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.exoplatform.container.component.BaseComponentPlugin;
import org.exoplatform.container.xml.InitParams;

/**
 * @author <a href="trongtt@exoplatform.com">Trong Tran</a>
 * @version $Revision$
 */
public class VirtualPortletAppPlugin extends BaseComponentPlugin {

  Map<String, String> map = new HashMap<String, String>();

  public VirtualPortletAppPlugin(InitParams params) throws Exception {
    List<VirtualApplication> list = params.getObjectParamValues(VirtualApplication.class);
    for (VirtualApplication app : list) {
      map.put(app.getVirtual(), app.getReal());
    }
  }

  public Map<String, String> getVirtualAppMap() {
    return map;
  }
}
