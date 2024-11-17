<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <!-- Root template -->
    <xsl:template match="/cafe_subOrder">
        <cafe_order>
            <!-- Select the order_id (assuming all fragments have the same order_id) -->
            <order_id>
                <xsl:value-of select="cafe_subOrder/order_id"/>
            </order_id>
            <drinks>
                <!-- Collect all drink elements from each cafe_subOrder -->
                <xsl:for-each select="//drink">
                    <xsl:copy-of select="."/>
                </xsl:for-each>
            </drinks>
        </cafe_order>
    </xsl:template>
</xsl:stylesheet>