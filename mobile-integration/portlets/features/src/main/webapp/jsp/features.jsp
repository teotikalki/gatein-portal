<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<portlet:defineObjects/>

<%-- The resourceBundle used to retrieve locale string values --%>
<c:set var="resourceBundle" value="${portletConfig.getResourceBundle(renderRequest.locale)}"/>
<c:set var="featuresTitle" value="${renderRequest.getPreferences().getValue('features.title', 'gateinFeatures')}"/>
<c:set var="featureList" value="${renderRequest.getPreferences().getValue('features.list', 'sso, nui, ps')}"/>
<c:set var="featureLink" value="${renderRequest.getAttribute('featureLink')}"/>

<div class="gtnResponsiveFeaturesPortlet">
  <c:if test="${not empty featuresTitle}">
  <div class="title">
    <h1>${resourceBundle.getString(featuresTitle)}</h1>
  </div>
  </c:if>
  <div class="features">
  <c:if test="${not empty featureList}">
    <c:forEach var="featureName" items="${featureList.split(',')}">
      <c:set var="feature" value="${featureName.trim()}"/>
      <div class="feature ${feature}Feature">
        <img alt="${resourceBundle.getString(feature.concat('.alttext'))}" src="${renderRequest.contextPath}/images/feature-${feature}.svg">
        <div class="text">
          <h2>${resourceBundle.getString(feature.concat(".label"))}</h2>
          <p>${resourceBundle.getString(feature.concat(".text"))}</p>
        </div>
      </div>
      <div class="separator"></div>
    </c:forEach>
  </c:if>
  </div>
  <div class="footer">
   <c:if test="${not empty featureLink}">
   <a href="${featureLink}">${resourceBundle.getString("browseFeaturesPages")}</a>
   </c:if>
  </div>
</div>
