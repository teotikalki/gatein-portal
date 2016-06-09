/*
 * Copyright (C) 2016 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.exoplatform.portal.application.virtual;

import java.util.HashMap;
import java.util.Map;

import org.exoplatform.container.component.ComponentPlugin;

/**
 * A service manages the mapping between virtual and real
 * applications of portlets. The mapping can be pluggability configurable via
 * {@link VirtualPortletAppPlugin} thank to component-plugin mechanism.
 * <p>
 * For example if you want to map serving portlets from a virtual application
 * war "foo" to the real one "bar", you could configure virtual app plugin with
 * init-params like following:
 *
 * <pre>
 * {@code
 *   <external-component-plugins>
 *    <target-component>org.exoplatform.portal.application.virtual.VirtualPortletAppService</target-component>
 *    <component-plugin>
 *      <name>addVirtualAppPlugin</name>
 *      <set-method>addVirtualAppPlugin</set-method>
 *      <type>org.exoplatform.portal.application.virtual.VirtualPortletAppPlugin</type>
 *      <description>Add a virtual portlet app plugin</description>
 *      <init-params>
 *        <object-param>
 *          <name>app1</name>
 *          <description>description</description>
 *          <object type="org.exoplatform.portal.application.virtual.VirtualApplication">
 *            <field name="virtual">
 *              <string>foo</string>
 *            </field>
 *            <field name="real">
 *              <string>bar</string>
 *            </field>
 *          </object>
 *        </object-param>
 *      </init-params>
 *    </component-plugin>
 *  </external-component-plugins>
 * }
 * </pre>
 *
 * @author <a href="trongtt@exoplatform.com">Trong Tran</a>
 * @version $Revision$
 */
public class VirtualPortletAppService {

  Map<String, String> map = new HashMap<String, String>();

  public String getRealApp(String appName) {
    if (map.containsKey(appName)) {
      appName = map.get(appName);
    }

    return appName;
  }

  public void addVirtualAppPlugin(ComponentPlugin plugin) {
    if (plugin instanceof VirtualPortletAppPlugin) {
      map.putAll(((VirtualPortletAppPlugin)plugin).getVirtualAppMap());
    }
  }
}
