const socket = new WebSocket("ws://localhost:10003/ratings");
const ratingSpan = document.querySelector("#rating-value");

socket.onopen = () => console.log("Connected");

socket.onclose = () => console.log("Disconnected");

socket.onerror = (error) => console.log("Error: " + error);

socket.onmessage = (message) => ratingSpan.innerText = message.data;


