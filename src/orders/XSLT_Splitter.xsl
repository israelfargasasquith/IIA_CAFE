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

    <xsl:param name="varSplitter" select="'cafe_order/drinks/drink'"/>
    <xsl:param name="varOriginal_ID" select="'order_id'"/>
    <xsl:param name="varName" select="'name'"/>
    <xsl:param name="varType" select="'type'"/>
    
    <xsl:template match="/">
        <mensajes>
        <xsl:for-each select="cafe_order/drinks/drink"> 
            <!--   <xsl:for-each select="$varSplitter"> O pasar lista de nodos literal en XSLT2.0 o tiene que ser asi-->
            <mensaje>
                <header>
                    <nFrag>
                        <xsl:value-of select="position()"/> <!-- Counter using position() -->
                    </nFrag>
                </header>
                <body>
                    <id>
                        <!--<xsl:value-of select="order_id"/> 
                        Conforme vayamos solucionando otros ejercicios se añadiran otros test de when
                        --> 
                        <xsl:choose>
                            <xsl:when test="$varOriginal_ID = 'order_id'">
                                <xsl:value-of select="../../order_id"/>
                            </xsl:when>
                            <!-- Add other cases if needed -->
                            <xsl:otherwise>
                                <xsl:text>Unknown ID</xsl:text>
                            </xsl:otherwise>
                        </xsl:choose>
                    </id>
                    <nombre>
                        <!--<xsl:value-of select="name"/> -->
                        <xsl:choose>
                            <xsl:when test="$varName = 'name'">
                                <xsl:value-of select="name"/>
                            </xsl:when>
                            <!-- Add other cases if needed -->
                            <xsl:otherwise>
                                <xsl:text>Unknown Name</xsl:text>
                            </xsl:otherwise>
                        </xsl:choose>
                    </nombre>
                    <tipo>
                        <!--<xsl:value-of select="type"/> -->
                        <xsl:choose>
                            <xsl:when test="$varType = 'type'">
                                <xsl:value-of select="type"/>
                            </xsl:when>
                            <!-- Add other cases if needed -->
                            <xsl:otherwise>
                                <xsl:text>Unknown Type</xsl:text>
                            </xsl:otherwise>
                        </xsl:choose>
                    </tipo>
                </body>
            </mensaje>
            <xsl:text>&#10;</xsl:text>
        </xsl:for-each>
        </mensajes>
    </xsl:template>

</xsl:stylesheet>