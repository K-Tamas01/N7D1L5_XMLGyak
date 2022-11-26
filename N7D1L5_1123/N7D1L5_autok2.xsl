<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:gt="http://www.gtech.com/lsp/2009-09-23"
exclude-result-prefixes="gt">

<xsl:template match="/autok" >
    <html>
        <body>
            <table border="1">
                <tr bgcolor="#9acd32">
                    <th>db</th>
                    <th>kuszob</th>
                </tr>
                <xsl:for-each select="auto">
                <xsl:if test="ar>30000">
                <tr>
                    <tr>
                    <td><xsl:value-of select="tipus"/></td>
                    <td><xsl:value-of select="ar"/></td>
                    <td><xsl:value-of select="szin"/></td>
                    <td><xsl:value-of select="tulaj/nev"/></td>
                    <td><xsl:value-of select="tulaj/varos"/></td>
                </tr>
                </tr>   
                </xsl:if>
                </xsl:for-each>
            </table>
        </body>
    </html>
</xsl:template>

</xsl:stylesheet>