/**
 * Copyright (C) 2009 eXo Platform SAS.
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

package org.exoplatform.webui.config;

import org.exoplatform.webui.Util;
import org.exoplatform.webui.core.lifecycle.Lifecycle;
import org.exoplatform.webui.event.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Created by The eXo Platform SARL Author : Tuan Nguyen tuan08@users.sourceforge.net May 4, 2006 */
public class Component
{

   private String id;

   private String type;

   private String lifecycle;

   private String template;

   private String decorator;

   private InitParams initParams;

   private List<Validator> validators;

   private List<Event> events;

   private List<EventInterceptor> eventInterceptors;

   transient private Map<String, Event> eventMap;

   transient private Lifecycle componentLifecycle;

   public String getId()
   {
      return id;
   }

   public String getType()
   {
      return type;
   }

   public String getLifecycle()
   {
      return lifecycle;
   }

   public String getTemplate()
   {
      return template;
   }

   public String getDecorator()
   {
      return decorator;
   }

   public void setId(String id)
   {
      this.id = id;
   }

   public void setType(String type)
   {
      this.type = type;
   }

   public void setLifecycle(String lifecycle)
   {
      this.lifecycle = lifecycle;
   }

   public void setTemplate(String template)
   {
      this.template = template;
   }

   public void setDecorator(String decorator)
   {
      this.decorator = decorator;
   }

   public InitParams getInitParams()
   {
      return initParams;
   }

   public void setInitParams(InitParams initParams)
   {
      this.initParams = initParams;
   }

   public List<Validator> getValidators()
   {
      return validators;
   }

   public void setValidators(List<Validator> validators)
   {
      this.validators = validators;
   }

   public List<Event> getEvents()
   {
      return events;
   }

   public void setEvents(List<Event> events)
   {
      this.events = events;
   }

   public List<EventInterceptor> getEventInterceptors()
   {
      return eventInterceptors;
   }

   public void setEventInterceptors(List<EventInterceptor> events)
   {
      eventInterceptors = events;
   }

   public Event getUIComponentEventConfig(String eventName) throws Exception
   {
      if (eventMap != null)
      {
         return eventMap.get(eventName);
      }
      eventMap = new HashMap<String, Event>();
      if (events == null)
      {
         return null;
      }
      for (Event event : events)
      {
         createCachedEventListeners(event);
         eventMap.put(event.getName(), event);
      }
      return eventMap.get(eventName);
   }

   public List<EventListener> getUIComponentEventListeners(String eventName) throws Exception
   {
      Event event = getUIComponentEventConfig(eventName);
      if (event == null)
      {
         return null;
      }
      List<EventListener> cachedListeners = event.getCachedEventListeners();
      if (cachedListeners != null)
      {
         return cachedListeners;
      }
      cachedListeners = new ArrayList<EventListener>();
      for (String listener : event.getListeners())
      {
         if (listener.indexOf(".") < 0)
         {
            listener = type + "$" + listener;
         }
         EventListener eventListener = (EventListener)Util.createObject(listener, event.getInitParams());
         cachedListeners.add(eventListener);
      }
      event.setCachedEventListeners(cachedListeners);
      return cachedListeners;
   }

   private void createCachedEventListeners(Event event) throws Exception
   {
      List<EventListener> cachedListeners = new ArrayList<EventListener>();
      for (String listener : event.getListeners())
      {
         if (listener.indexOf(".") < 0)
         {
            listener = type + "$" + listener;
         }
         EventListener eventListener = (EventListener)Util.createObject(listener, event.getInitParams());
         cachedListeners.add(eventListener);
      }
      event.setCachedEventListeners(cachedListeners);
   }

   public Lifecycle getUIComponentLifecycle() throws Exception
   {
      if (componentLifecycle != null)
      {
         return componentLifecycle;
      }
      if (lifecycle != null)
      {
         componentLifecycle = (Lifecycle)Util.createObject(lifecycle, null);
      }
      else
      {
         componentLifecycle = Util.createObject(Lifecycle.class, null);
      }
      return componentLifecycle;
   }

}