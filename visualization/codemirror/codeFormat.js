function xhrSuccess () { this.callback.apply(this, this.arguments); }

function xhrError () { console.error(this.statusText); }

function loadFile (sURL, fCallback /*, argumentToPass1, argumentToPass2, etc. */) {
  var oReq = new XMLHttpRequest();
  oReq.callback = fCallback;
  oReq.arguments = Array.prototype.slice.call(arguments, 2);
  oReq.onload = xhrSuccess;
  oReq.onerror = xhrError;
  oReq.open("get", sURL, true);
  oReq.send(null);
}

function showMessage (sMsg) {
  alert(sMsg + this.responseText);
}

loadFile("b.txt", showMessage, "New message!\n\n");

//chrome.exe --disable-web-security

var value =  sessionStorage.getItem('fileName');
value += "\n\n Some example code \n if ( true ) { then awesome; }";
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