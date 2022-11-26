<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">

    <xsl:template match = "/">

        <html>
            <style>
                table {
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: 100%;
                }

                td, th {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
                }

                tr:nth-child(even) {
                background-color: #dddddd;
                }

                caption {
                display: table-caption;
                text-align: center;
                }

            </style>
            <body>
                <h2>Orarend for-each, value-of</h2>

                <table border="6">
                    <tr bgcolor="#FF0000">
                        <th>ID</th>
                        <th>Tipus</th>
                        <th>Tárgy</th>
                        <th>Időpont</th>
                        <th>Helyszín</th>
                        <th>Oktató</th>
                        <th>Szak</th>
                    </tr>

                    <xsl:for-each select="orarend/ora">
                        <tr>
                            <td>
                                <xsl:value-of select="@id"/>
                            </td>
                            <td>
                                <xsl:value-of select="@tipus"/>
                            </td>

                            <td><xsl:value-of select="targy"/></td>
                            <td><xsl:value-of select="idopont"/></td>
                            <td><xsl:value-of select="helyszin"/></td>
                            <td><xsl:value-of select="oktato"/></td>
                            <td><xsl:value-of select="szak"/></td>
                        </tr>
                    </xsl:for-each>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>