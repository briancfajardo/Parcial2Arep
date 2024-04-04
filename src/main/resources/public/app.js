function loadGetLinear() {
    let list = document.getElementById("linear-list").value;
    let value = document.getElementById("linear-value").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("getresplinear").innerHTML = this.responseText;
    }
    xhttp.open("GET", "/linearsearch?list="+list+"&value="+value);
    xhttp.send();
}

function loadGetBinary() {
    let list = document.getElementById("binary-list").value;
    let value = document.getElementById("binary-value").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("getrespbinary").innerHTML = this.responseText;
    }
    xhttp.open("GET", "/binarysearch?list="+list+"&value="+value);
    xhttp.send();
}
