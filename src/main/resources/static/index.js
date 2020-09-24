
function initialize() {
   
    renderModal("createPlayer", "modals");
    
    getPlayers("/team/firstteam");
}


function getPlayers(url) {

   
    var xhttpList = new XMLHttpRequest();

    
    xhttpList.onreadystatechange = function () {

        if (this.readyState == 4 && this.status == 200) {
            renderPlayer(this.responseText);
        }
    };
    xhttpList.open("GET", url, true);
    xhttpList.send();
    console.log("Player List stored");

}


var id= document.getElementsById("search");
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


function renderPlayer(data) {
    var json = JSON.parse(data);
    


    for (var index = 0; index < json.length; index++) {
       
        var cardHtml ='<div class="card bg-dark text-white" id="' + json[index].id + '">'
            + '<img src="'+json[index].imagePath + '"class="card-img" alt="...">'
            +'<div class="card-img-overlay">'
            +'<div class="container"></div>'
            +'<br></br><br></br><br></br><br></br><br></br><br></br><br></br><br></br>'
            + '<h5 class="card-title">'+json[index].name +' '+ json[index].lastname+'</h5>'
            +'<p class="card-text">'+json[index].id +'</p>'
            + '<p class="card-text">'+json[index].name+' has played '+ json[index].gamesPlayed +' games this season'
            +' scoring '+json[index].goals+' goals and managing '+json[index].assists +' assists </p>'
            + '<div class="card-footer btn-group" id="update' + json[index].id + '">'
            + '<button class="btn btn-warning" onclick="deletePlayer(' + json[index].id + ')">DELETE</button>'
            + '</div>'
            + '</div>'
            + '</div>';
        console.log("Player Card with ID: " + json[index].id + " created");

       
        var cardDeck;
        if (index % 1 == 0) {
            cardDeck = document.createElement("div");
            cardDeck.classList.add("card-deck");
            cardDeck.id = "deck" + index;
            document.getElementById("players").appendChild(cardDeck);
            cardDeck = document.getElementById("deck" + index);
        }

        cardDeck.insertAdjacentHTML('beforeend', cardHtml);
        
        renderModal("updatePlayer", json[index].id);
    }

}



function renderModal(modalPurpose, id) {

    var location;
    var color;
    var btntxt;
    var player;
    var playerID = '';

    
    switch (modalPurpose) {
        case "createPlayer":
            location = id;
            color = "btn-success";
            btntxt = "Create a Player";
            break;
        case "updatePlayer":
            player = getOnePlayer(id);
            playerID = JSON.parse(player).id;
            location = "update" + id;
            color = "btn-warning";
            btntxt = "Update";
            break;
    }

   
    var buttonHtml = '<button type="button" class="btn ' + color + '" data-toggle="modal" data-target="#' + modalPurpose + playerID + '">' + btntxt + '</button>';
    document.getElementById(location).insertAdjacentHTML('beforeend', buttonHtml);


   
    var modalHtml = ' <div class="modal fade" id="' + modalPurpose + playerID + '"> '
        + ' <div class="modal-dialog modal-xl"> '
        + ' <div class="modal-content"> '

        + '<div class="modal-header">'
        + '<h4 class="modal-title">Modal Heading</h4>'
        + '<button type="button" class="close" data-dismiss="modal">&times;</button>'
        + '</div>'

        + '<div class="modal-body">'
        + playerForm(player, modalPurpose)
        + '</div>'

        + '<div class="modal-footer">'
        + '<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>'
        + '</div>'

        + '</div>'
        + '</div>'
        + '</div>'
        + '</div>';

    
    document.getElementById("modals").insertAdjacentHTML('beforeend', modalHtml);

}

function createPlayer() {

    
    var sendData = {
        "name": document.getElementById("createPlayername").value,
        "lastname": document.getElementById("createPlayerlastname").value,
        "gamesPlayed": document.getElementById("createPlayergamesPlayed").value,
        "assists":document.getElementById("createPlayerassists").value,
        "imagePath": document.getElementById("createPlayerimagePath").value,
        "goals": document.getElementById("createPlayergoals").value
    }
    console.log(sendData);

    var ok = confirm("Ready to send?");

    if (ok == true) {
        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "/team/addPlayer", true);
        xhttp.setRequestHeader('Content-Type', 'application/json');
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                console.log("Update success");
                
                var display = document.getElementById("players");
                display.innerHTML = '';
                getPlayers("/team/firstteam");
                console.log("Player created!");
            }
        };
        
        xhttp.send(JSON.stringify(sendData));
    }

}


function deletePlayer(id) {
   
    var link = "/team/deletePlayer/" + id;
    console.log("Loaded into delete function");

    var ok = confirm("Are you sure you want to delete?\nPress 'OK' to confirm, or 'cancel' to cancel");
    if (ok == true) {

        var xhttp = new XMLHttpRequest();
        xhttp.open("DELETE", link, true);

        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
              
                var removeCard = document.getElementById(id);

                removeCard.parentNode.removeChild(removeCard);
                console.log("Player deleted.");
            }
        };
  
        xhttp.send(null);
    }
}


function updatePlayer(id) {

    
    var sendData = {
        "id": id,
        "name": document.getElementById("updatePlayername"+id).value,
        "lastname": document.getElementById("updatePlayerlastname"+id).value,
        "gamesPlayed": document.getElementById("updatePlayergamesPlayed"+id).value,
        "assists":document.getElementById("updatePlayerassists"+id).value,
        "imagePath": document.getElementById("updatePlayerimagePath"+id).value,
        "goals": document.getElementById("updatePlayergoals"+id).value
    }
    console.log(sendData);

    var ok = confirm("Please confirm you wish to apply these changes");

  
    if (ok == true) {
        var xhttp = new XMLHttpRequest();
        xhttp.open("PUT", "/team/updatePlayer", true);
        xhttp.setRequestHeader('content-Type', 'application/json');
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                console.log("Update success");
                var display = document.getElementById("players");
                display.innerHTML = '';
                getPlayers("/team/firstteam");
            }
        };
        
        xhttp.send(JSON.stringify(sendData));
    }
}

function playerForm(player, purpose) {
    var input;
    var id;
    var name;
    var lastname;
    var gamesPlayed;
    var goals;
    var assists;
    var imagePath;
    var action;

   
    switch (purpose) {
        case "createPlayer":
            input = '';
            id ='';
            name = '';
            lastname = '';
            gamesPlayed = '';
            goals = '';
            assists='';
            imagePath = '';
            action = 'createPlayer()';
            break;
        case "updatePlayer":
            input = JSON.parse(player);
            id = input.id;
            name = input.name;
            lastname = input.lastname;
            gamesPlayed = input.gamesPlayed;
            goals = input.goals;
            assists=input.assists;
            imagePath = input.imagePath;
            action = 'updatePlayer(' + input.id + ')';
            break;
    }

   
    var form = ''
        + '<form>'
        + '<div class="input-group mb-3">'
        + '<div class="input-group-prepend">'
        + '<span class="input-group-text">First Name</span>'
        + '</div>'
        + '<input type="text" class="form-control" placeholder="Enter First Name" id="' + purpose + 'name' + id +'" value="' + name + '">'
        + '</div>'
        + '<div class="input-group mb-3">'
        + '<div class="input-group-prepend">'
        + '<span class="input-group-text">Last Name</span>'
        + '</div>'
        + '<input type="text" class="form-control" placeholder="Enter Last Name" id="' + purpose + 'lastname' + id +'" value="' + lastname + '">'
        + '</div>'
        + '<div class="input-group mb-3">'
        + '<div class="input-group-prepend">'
        + '<span class="input-group-text">gamesPlayed</span>'
        + '</div>'
        + '<input type="text" class="form-control" placeholder="Enter Games Played" id="' + purpose + 'gamesPlayed' + id +'" value="' + gamesPlayed + '">'
        + '</div>'
        + '<div class="input-group mb-3">'
        + '<div class="input-group-prepend">'
        + '<span class="input-group-text">goals</span>'
        + '</div>'
        + '<input type="text" class="form-control" placeholder="Enter Goals Scored" id="' + purpose + 'goals' + id +'" value="' + goals + '">'
        + '</div>'
        +'<div class="input-group mb-3">'
        + '<div class="input-group-prepend">'
        + '<span class="input-group-text">assists</span>'
        + '</div>'
        + '<input type="text" class="form-control" placeholder="Enter Assists Given" id="' + purpose + 'assists' + id +'" value="' + assists + '">'
        + '</div>'
        + '<div class="input-group mb-3">'
        + '<div class="input-group-prepend">'
        + '<span class="input-group-text">Image URL</span>'
        + '</div>'
        + '<input type="text" class="form-control" placeholder="Enter Image Path" id="' + purpose + 'imagePath' + id +'" value="' + imagePath + '">'
        + '</div>'
        + '<button type="submit" class="btn btn-primary" data-dismiss="modal" onclick="' + action + '">Submit</button>'
        + '</form>';

    return form;
}