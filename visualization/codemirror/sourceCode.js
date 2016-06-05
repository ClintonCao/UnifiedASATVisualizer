
//chrome.exe --disable-web-security


var sourceCode = (function() {

function hightlight(lineNumber, type){
	
	$( '.CodeMirror-code').children().each(function () {
       if ($(this).find('.CodeMirror-gutter-wrapper').find('.CodeMirror-linenumber').text() == lineNumber ){
		  switch(type) {
		case 'CheckStyle':
			$(this).css('background','#386938');
			break;
		case 'PMD':
			$(this).css('background','#88120a');
			break;
		case 'FindBugs':
			$(this).css('background','#043e70');
			break;
		  }
	   }
    });
}

function displayCode(pathID){	
for ( var i = 0; i < codeExport.length; i++){	

	if (codeExport[i].path == pathID){
	var value = codeExport[i].code;
	}
	
}
var editor = CodeMirror(document.body.getElementsByTagName("article")[0], {
    value: value,
    lineNumbers: true,
    mode: "javascript",
    keyMap: "sublime",
	readOnly: "nocursor",
    autoCloseBrackets: true,
    matchBrackets: true,
    showCursorWhenSelecting: true,
    theme: "monokai",
    tabSize: 2
});
}

return {
		
	readAllCode: function(){	
		console.log(editor.getValue());
	},
	show: function(){
		var chartDiv = document.getElementById("code-div");
			chartDiv.style.visibility = 'visible';
			document.getElementById("chart").style.display  = 'none';  
	},
	hide: function(){
		var chartDiv = document.getElementById("code-div");
			chartDiv.style.visibility = 'hidden';
			document.getElementById("chart").style.display  = 'inline-block';  
	},
	display: function(path){
		displayCode(path);
	},
	highlightWarnings: function(){
		var warnings = getWarningLines("CheckStyle","Match.java");
		for( var i =0 ; i < warnings.warningList.length; i ++ ){
			hightlight(warnings.warningList[i].line, warnings.warningList[i].type);
		}
	}
}


}());