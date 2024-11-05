<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : XSLT_Temp.xsl
    Created on : 5 de noviembre de 2024, 17:30
    Author     : israe
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <mensaje>
            <id>
                <xsl:value-of select="//order_id"/>
            </id>
            <nombre>
                <xsl:value-of select="//name"/>
            </nombre>
            <tipo>
                <xsl:value-of select="//type"/>
            </tipo>
        </mensaje>  

    </xsl:template>

</xsl:stylesheet>