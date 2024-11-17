<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="/cafe_subOrder">
        <cafe_order>
            <order_id>
                <xsl:value-of select="cafe_subOrder/order_id"/>
            </order_id>
            <drinks>
                <xsl:for-each select="//drink">
                    <xsl:copy-of select="."/>
                </xsl:for-each>
            </drinks>
        </cafe_order>
    </xsl:template>
</xsl:stylesheet>