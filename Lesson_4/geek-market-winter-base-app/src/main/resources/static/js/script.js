var stompClient = null;

window.onload = connect();

function connect() {
    var socket = new SockJS('/hello');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function(greeting){
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function sendName() {
    var name = 'товар';
    stompClient.send("/app/hello", {}, JSON.stringify({ 'name': name }));
}

// отправка сообщения на сервер
function sendContent() {
    stompClient.send("/app/item", {}, JSON.stringify({'content':
            $("#content").val()}));
}

function showGreeting(message) {
    console.log(message);
    document.getElementById("resultInput").value=message;
}