<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : XSLT_Temp.xsl
    Created on : 5 de noviembre de 2024, 17:30
    Author     : israe
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <xsl:for-each select="//drink">
            <mensaje>
                <id>
                    <xsl:value-of select="//order_id"/>
                </id>
                <nombre>
                    <xsl:value-of select="name"/>
                </nombre>
                <tipo>
                    <xsl:value-of select="type"/>
                </tipo>
                <Is_last>
                    <!-- Check if the current drink is the last in the list -->
                    <xsl:choose>
                        <xsl:when test="position() = last()">true</xsl:when>
                        <xsl:otherwise>false</xsl:otherwise>
                    </xsl:choose>
                </Is_last>
            </mensaje>
            <xsl:text>&#10;</xsl:text>
        </xsl:for-each>

    </xsl:template>

</xsl:stylesheet>