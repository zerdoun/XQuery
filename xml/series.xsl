<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : series.xsl
    Created on : 01 nov 2015, 14:16
    Author     : Simon
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
			<style type="text/css"> 
				body{
				font-size:15px;}
				h1{
				text-align: center;
				}
				h2{
				margin-left: 2.5%;
				}
				h3{
				font-style: oblique;				
				font-weight:bolder;				
				text-align:left;
				margin: 4px 10px;
				font-size:x-large;
				}
				hr{
					height: 12px; border: 0; box-shadow: inset 0 12px 12px -12px rgba(0, 0, 0, 0.5);
				}
				.type:first-letter{
				text-transform : capitalize;
				font-style: oblique;				
				font-weight:lighter;
				font-size: 150%;
				}
			</style> 		
            </head>
            <body>
				<xsl:for-each select="series/serie">
				<h3 class="type"><xsl:value-of select="nom"/> :</h3>				
				<ul><xsl:for-each select="link">
					<li><xsl:value-of select="current()"/></li>
				</xsl:for-each></ul>
				<hr></hr>
				</xsl:for-each>
				
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>