
function getPlayer(){
 document.getElementById()
function getOnePlayer(id) {
    var url = "/team/" + id;
   
    var xhttpList = new XMLHttpRequest();
    

   
    xhttpList.onreadystatechange = function () {

        if (this.readyState == 4 && this.status == 200) {
            sessionStorage.setItem("player", this.responseText);
        }
    };
    xhttpList.open("GET", url, false);
    xhttpList.send();
    console.log("Single player retrieved");

    return sessionStorage.getItem("player");
}

}