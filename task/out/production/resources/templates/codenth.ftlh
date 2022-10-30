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
	<span id="load_date">${codeNth.date}</span>
    <#noautoesc>
    <#assign xx = '<p><span id="views-restriction">${codeNth.views} more views allowed</span></p>'>
	<#assign yy = '<p><span id="time-restriction">The code will be available for ${codeNth.time} seconds</span></p>'>
	${(codeNth.views>0)?then(xx,'')}
    ${(codeNth.time>0)?then(yy,'')}
    </#noautoesc>
	<pre id="code_snippet"><code>${codeNth.code}</code></pre>
</body>

</html>