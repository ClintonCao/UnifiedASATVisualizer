var txt = '';
var xmlhttp = new XMLHttpRequest();
xmlhttp.onreadystatechange = function(){
  if(xmlhttp.status == 200 && xmlhttp.readyState == 4){
    txt = xmlhttp.responseText;
  }
};
xmlhttp.open("GET","b.txt",true);
console.log(txt);
console.log("txts");
console.log(txt);
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