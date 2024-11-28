<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="/">
        <SQL>
            SELECT p.precio
            FROM bebidas p
            WHERE p.name = <xsl:value-of select="//name"/>;
        </SQL>
    </xsl:template>
</xsl:stylesheet>