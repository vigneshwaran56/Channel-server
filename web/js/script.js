var ws;
var json;
function connect() {
    var username = document.getElementById("username").value;
    ws = new WebSocket("ws://localhost:8080/OnQuik-Channel/api/"+ username);
    ws.onmessage = function(event) {
    var log = document.getElementById("log");
        console.log(event.data);
        json = JSON.parse(event.data);
        log.innerHTML += json.from + " : " + json.message + "\n";
    };
    ws.onclose = function(event){
        onClose(event);
    };
}

function send() {
    var content = document.getElementById("msg").value;
    var to = document.getElementById("to").value;
    var json = JSON.stringify({
        "to":to,
        "content":content
    });
    
    ws.send(json);
    log.innerHTML += "Me : " + content + "\n";
}

function onClose(){
    ws.onclose("disconnect");
}
function disconnect(){
   ws.close();

 
}
window.make =  function make(){
    json = JSON.stringify(json);
    makeApi("http://localhost:8080/OnQuik-Channel/clientservlet","POST",json,clientws)
}

function clientws(response){
    json = JSON.parse(response);
        log.innerHTML += json.from + " : " + json.message + "\n";
}

function makeApi(url,method,payload,callback) {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
  //   document.getElementById("demo").innerHTML = this.responseText;
  console.log(xhttp.responseText);
  callback.call(xhttp.responseText);
    }
  };
  xhttp.open(method, url, true);
  
  if((method == 'POST')||(method == 'PUT'))
  xhttp.send(payload);
  else
  xhttp.send();
  
}