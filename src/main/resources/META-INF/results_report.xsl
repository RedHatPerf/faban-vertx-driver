<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/benchResults">
benchmark.version=<xsl:value-of select="benchSummary/@name"/> <xsl:value-of select="benchSummary/@version"/>
result.start_time=><xsl:value-of select="benchSummary/startTime"/>
        <xsl:for-each select="driverSummary">
            <xsl:variable name="driverName" select="@name"/>
            <xsl:for-each select="mix/operation">
                <xsl:variable name="operationName" select="@name"/>
result.<xsl:value-of select="$driverName" />.<xsl:value-of select="$operationName" />.count=<xsl:value-of select="successes"/>            
            </xsl:for-each>
            <xsl:for-each select="responseTimes/operation">
                <xsl:variable name="operationName" select="@name"/>
result.<xsl:value-of select="$driverName" />.<xsl:value-of select="$operationName" />.resp_time.avg=<xsl:value-of select="avg"/>
result.<xsl:value-of select="$driverName" />.<xsl:value-of select="$operationName" />.resp_time.max=<xsl:value-of select="max"/>
result.<xsl:value-of select="$driverName" />.<xsl:value-of select="$operationName" />.resp_time.90p=<xsl:value-of select="p90th"/>
            </xsl:for-each>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>

