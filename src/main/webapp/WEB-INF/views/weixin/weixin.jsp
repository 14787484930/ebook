<%@ page contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<xml>
<ToUserName><![CDATA[${map['FromUserName']}]]></ToUserName>
<FromUserName><![CDATA[${map['ToUserName']}]]></FromUserName>
<CreateTime><![CDATA[${map['CreateTime']}]]></CreateTime>
<MsgType><![CDATA[${map['MsgType']}]]></MsgType>
<c:choose>
	<c:when test="${map['MsgType']=='image'}">
		<Image>
		    <MediaId><![CDATA[${map['MediaId']}]]></MediaId>
		</Image>
	</c:when>
	<c:when test="${map['MsgType']=='voice'}">
		<Voice>
		    <MediaId><![CDATA[${map['MediaId']}]]></MediaId>
		</Voice>
	</c:when>
	<c:when test="${map['MsgType']=='video'}">
		<Video>
			<MediaId><![CDATA[${map['MediaId']}]]></MediaId>
			<Title><![CDATA[${map['Title']}]]></Title>
			<Description><![CDATA[${map['Description']}]]></Description>
		</Video> 
	</c:when>
	<c:otherwise>
	   <Content><![CDATA[${map['Content']}]]></Content>
	</c:otherwise>
</c:choose> 
</xml>