var value = "Some example code \n if ( true ) { then awesome; }";
var editor = CodeMirror(document.body.getElementsByTagName("article")[0], {
    value: value,
    lineNumbers: true,
    mode: "javascript",
    keyMap: "sublime",
    autoCloseBrackets: true,
    matchBrackets: true,
    showCursorWhenSelecting: true,
    theme: "monokai",
    tabSize: 2
});

function readAllCode(){
	// the code after editing...
	console.log(editor.getValue());
}