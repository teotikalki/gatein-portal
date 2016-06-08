package org.exoplatform.portal.application.virtual;

import org.exoplatform.component.test.ConfigurationUnit;
import org.exoplatform.component.test.ConfiguredBy;
import org.exoplatform.component.test.ContainerScope;
import org.exoplatform.portal.AbstractPortalTest;
import org.exoplatform.portal.application.virtual.VirtualPortletAppService;

@ConfiguredBy({@ConfigurationUnit(scope = ContainerScope.PORTAL, path = "conf/portal/test-configuration.xml") })
public class TestVirtualPortletAppService extends AbstractPortalTest {

  public void testPlugin() {
    VirtualPortletAppService service = getContainer().getComponentInstanceOfType(VirtualPortletAppService.class);
    assertEquals("bar", service.getRealApp("foo"));
    assertEquals("newwar", service.getRealApp("origin"));

    //
    assertEquals("zoo", service.getRealApp("zoo"));
  }
}
