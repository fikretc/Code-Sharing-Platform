<html>

<head>
	<title>Code</title>
	<link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    <link rel="stylesheet" href="style.css">
</head>

<body>
	<span id="load_date">${codeRecordNth.dateOfCode}</span>
    <#noautoesc>
    <#assign xx = '<span id="views_restriction"> ${codeRecordNth.views} more views allowed </span>'>
	<#assign yy = '<span id="time_restriction">The code will be available for ${codeRecordNth.remainingTime} seconds</span>'>
	${(codeRecordNth.viewsRestriction)?then(xx,'')}
    ${(codeRecordNth.remainingTime>0)?then(yy,'')}
    </#noautoesc>
	<pre id="code_snippet"><code>${codeRecordNth.code}</code></pre>
</body>

</html>