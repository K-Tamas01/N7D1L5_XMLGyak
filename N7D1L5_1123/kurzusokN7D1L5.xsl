<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">

    <xsl:template match = "/">

        <html>
            <body>
                <h2>Vizsga for-each, value-of</h2>

                <table border="6">
                    <tr bgcolor="#FF0000">
                        <th>Kurzus</th>
                        <th>Helyszín</th>
                        <th>Időpont</th>
                        <th>Oktató</th>
                        <th>Jegy</th>
                    </tr>

                    <xsl:for-each select="vizsgak_N7D1L5/vizsga">
                        <tr>
                            <td><xsl:value-of select="kurzus"/></td>
                            <td><xsl:value-of select="helyszín"/></td>
                            <td><xsl:value-of select="idopont"/></td>
                            <td><xsl:value-of select="oktato"/></td>
                            <td><xsl:value-of select="jegy"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>