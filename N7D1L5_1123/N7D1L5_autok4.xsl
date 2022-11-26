<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:gt="http://www.gtech.com/lsp/2009-09-23"
exclude-result-prefixes="gt">

<xsl:template match="/autok" >
    <html>
        <body>
            <xsl:value-of select="count(auto)"/>
        </body>
    </html>
</xsl:template>

</xsl:stylesheet>