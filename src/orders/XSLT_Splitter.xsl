<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="/">
        <cafe_order>
            <xsl:for-each select="cafe_order/drinks/drink">
                <cafe_subOrder>
                    <order_id>
                        <xsl:value-of select="../../order_id"/>
                    </order_id>
                    <drinks>
                        <drink>
                            <name>
                                <xsl:value-of select="name"/>
                            </name>
                            <type>
                                <xsl:value-of select="type"/>
                            </type>
                        </drink>
                    </drinks>
                </cafe_subOrder>
            </xsl:for-each>
        </cafe_order>
    </xsl:template>
</xsl:stylesheet>