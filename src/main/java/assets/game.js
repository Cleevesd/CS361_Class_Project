var isSetup = true;
var placedShips = 0;
var sonarPulseUnlocked = false;
var sonarPulseCount = 0;
var sonarPulseActive = false;
var moveFleetUnlocked = false;
var moveFleetCount = 0;
var moveFleetActive = false;
var game;
var shipType;
var vertical;
var btn = document.getElementById('is_vertical');
var Phase1_text = document.getElementById('Place_indicator');
var Phase2_text = document.getElementById('Attack_indicator');
var attackLog = document.getElementById('attack_log');
var direction;


function updateRotate() {
    if(btn.value === 'Vertical') {
        btn.value = 'Horizontal';
    }
    else {
        btn.value = 'Vertical';
    }
}

function makeGrid(table, isPlayer) {
    Phase2_text.style.display = 'none';
    for (i=0; i<10; i++) {
        let row = document.createElement('tr');
        for (j=0; j<10; j++) {
            let column = document.createElement('td');
            column.addEventListener("click", cellClick);
            row.appendChild(column);
        }
        table.appendChild(row);
    }
}

function markHits(board, elementId, surrenderText) {
    board.attacks.forEach((attack) => {
        let className;
        if (attack.result === "MISS")
            className = "miss";
        else if (attack.result === "HIT")
            className = "hit";
        else if (attack.result === "SUNK")
            className = "hit";
        else if (attack.result === "SURRENDER")
            alert(surrenderText);
        document.getElementById(elementId).rows[attack.location.row-1].cells[attack.location.column.charCodeAt(0) - 'A'.charCodeAt(0)].classList.add(className);
    });
}

function redrawGrid() {
    Array.from(document.getElementById("opponent").childNodes).forEach((row) => row.remove());
    Array.from(document.getElementById("player").childNodes).forEach((row) => row.remove());
    makeGrid(document.getElementById("opponent"), false);
    makeGrid(document.getElementById("player"), true);
    if (game === undefined) {
        return;
    }

    game.playersBoard.ships.forEach((ship) => ship.occupiedSquares.forEach((square) => {
        document.getElementById("player").rows[square.row-1].cells[square.column.charCodeAt(0) - 'A'.charCodeAt(0)].classList.add("occupied");
    }));

    // Display the area affected by Sonar Pulse.
    game.opponentsBoard.sonarPulseEmptySquares.forEach((square) => {
        document.getElementById("opponent").rows[square.row-1].cells[square.column.charCodeAt(0) - 'A'.charCodeAt(0)].classList.add("pulseEmpty");
    });
    game.opponentsBoard.sonarPulseOccupiedSquares.forEach((square) => {
        document.getElementById("opponent").rows[square.row-1].cells[square.column.charCodeAt(0) - 'A'.charCodeAt(0)].classList.add("pulseOccupied");
    });

    markHits(game.opponentsBoard, "opponent", "You won the game");
    markHits(game.playersBoard, "player", "You lost the game");
}

var oldListener;
function registerCellListener(f) {
    let el = document.getElementById("player");
    for (i=0; i<10; i++) {
        for (j=0; j<10; j++) {
            let cell = el.rows[i].cells[j];
            cell.removeEventListener("mouseover", oldListener);
            cell.removeEventListener("mouseout", oldListener);
            cell.addEventListener("mouseover", f);
            cell.addEventListener("mouseout", f);
        }
    }
    oldListener = f;
}

function cellClick() {
    let row = this.parentNode.rowIndex + 1;
    let col = String.fromCharCode(this.cellIndex + 65);
    console.log(col);

    if (isSetup) {
        sendXhr("POST", "/place", {game: game, shipType: shipType, x: row, y: col, isVertical: vertical}, function(data) {
            game = data;

            redrawGrid();
            placedShips++;
            if (placedShips == 3) {
                isSetup = false;
                var buttons = document.getElementById('button_panel');
                buttons.style.display = 'none';
                Phase1_text.style.display = 'none';
                Phase2_text.style.display = 'block'
                registerCellListener((e) => {});
            }
        });

        // Post ship placements to attack log
        var placeString = "You placed a ship at: " + col + " , " + row + "!";
        var placeNode = document.createTextNode(placeString);
        var br = document.createElement("br");
        attackLog.appendChild(placeNode);
        attackLog.appendChild(br);
        attackLog.scrollTop = attackLog.scrollHeight;
    }
    else if (sonarPulseActive) {
        sendXhr("POST", "/sonarPulseAttack", {game: game, x: row, y: col}, function(data) {
            game = data;
            redrawGrid();
            alert("You just used your sonar pulse!");
        })
        sonarPulseActive = false;
    }
    else if (moveFleetActive) {
        sendXhr(
            "POST"
            , "/moveFleet"
            , {game: game, direction: direction}
            , function(data) {
                game = data;
                redrawGrid();
                alert("You just moved your fleet!");
            }
        )
        moveFleetActive = false;
    }
    else {
        sendXhr("POST", "/attack", {game: game, x: row, y: col}, function(data) {
            game = data;
            redrawGrid();

            // If the player just sunk the first enemy ship, unlock Sonar Pulse.
            if (game.opponentsBoard.ships.length < 3 && !sonarPulseUnlocked) {
                sonarPulseUnlocked = true;
                sonarPulseCount = 2;
                alert("Sonar Pulse has been unlocked!\nUse this weapon to reveal enemy ships within a 3x3 grid.");

                // Load button for Sonar Pulse.
                document.getElementById('sonar_pulse').style.display = 'block';
                document.getElementById('sonar_pulse').addEventListener("click", function(e) {
                    registerCellListener(sonarPulse());
                });
            }

            // If the player just sunk the second enemy ship, unlock Fleet Movement.
            if (game.opponentsBoard.ships.length < 2 && !moveFleetUnlocked) {
                moveFleetUnlocked = true;
                moveFleetCount = 2;
                alert("Fleet Movement has been unlocked!\nUse this to move all of your ships in the selected direction by one square.");

                // Load buttons for Fleet Movement.
                document.getElementById('move_container').style.display = 'block';
                document.getElementById('move_container').addEventListener("click", function(e) {
                    registerCellListener(moveFleet());
                });
                document.getElementById('move_container').addEventListener("click", cellClick);
                var moveButtonArray = document.getElementsByClassName('move_button');
                for (var i = 0; i < moveButtonArray.length; i++) {
                    moveButtonArray[i].addEventListener("click", function(e) {
                        registerCellListener(setDirection());
                    })
                }
            }

            // Post attacks to attack log
            var attackString = "You attacked at: " + col + " , " + row + "!";
            var attackNode = document.createTextNode(attackString);
            var br = document.createElement("br");
            attackLog.appendChild(attackNode);
            attackLog.appendChild(br);
            attackLog.scrollTop = attackLog.scrollHeight;
        })
    }
}

function sendXhr(method, url, data, handler) {
    var req = new XMLHttpRequest();
    req.addEventListener("load", function(event) {
        if (req.status != 200) {
            if (url === "/attack") {
                alert("You tried to attack a spot that has already been attacked.");
            }
            else if (url === "/place") {
                alert("You tried to place a ship invalidly.");
            }
            return;
        }
        handler(JSON.parse(req.responseText));
    });
    req.open(method, url);
    req.setRequestHeader("Content-Type", "application/json");
    req.send(JSON.stringify(data));
}

function setDirection() {
    console.log("Moving fleet one square to the: ", this.document.activeElement.id);
    direction = this.document.activeElement.id;
}

function moveFleet() {
    if (moveFleetCount === 2) {
        alert("Only one fleet movement left.");
        moveFleetActive = true;
        moveFleetCount--;
    } else if (moveFleetCount === 1) {
        alert("You can no longer move your fleet.");
        document.getElementById('move_container').style.display = 'none';
        moveFleetActive = true;
        moveFleetCount--;
    } else {
        alert("Sorry, you can no longer move your fleet! This button shouldn't be here anymore.");
        document.getElementById('move_container').style.display = 'none';
    }
}

function sonarPulse() {
    if (sonarPulseCount === 2) {
        alert("Sonar Pulse is locked and loaded! You have 1 Sonar Pulse left.");
        sonarPulseActive = true;
        sonarPulseCount--;
    } else if (sonarPulseCount === 1) {
        alert("Final Sonar Pulse is locked and loaded! You have no more Sonar Pulses left.");
        document.getElementById('sonar_pulse').style.display = 'none';
        sonarPulseActive = true;
        sonarPulseCount--;
    } else {
        alert("Sorry, you're all out of Sonar Pulses! This button shouldn't be here anymore.");
        document.getElementById('sonar_pulse').style.display = 'none';
    }
}

function place(size) {
    return function() {
        let row = this.parentNode.rowIndex;
        let col = this.cellIndex;

        if(document.getElementById("is_vertical").value === 'Vertical') {
            vertical = true;
        }
        else {
            vertical = false;
        }
        let table = document.getElementById("player");
        for (let i=0; i<size; i++) {
            let cell;
            if(vertical) {
                let tableRow = table.rows[row+i];
                if (tableRow === undefined) {
                    // ship is over the edge; let the back end deal with it
                    break;
                }
                cell = tableRow.cells[col];
            } else {
                cell = table.rows[row].cells[col+i];
            }
            if (cell === undefined) {
                // ship is over the edge; let the back end deal with it
                break;
            }
            cell.classList.toggle("placed");
        }
    }
}

function initGame() {
    makeGrid(document.getElementById("opponent"), false);
    makeGrid(document.getElementById("player"), true);
    document.getElementById("place_minesweeper").addEventListener("click", function(e) {
        shipType = "MINESWEEPER";
       registerCellListener(place(2));
    });
    document.getElementById("place_destroyer").addEventListener("click", function(e) {
        shipType = "DESTROYER";
       registerCellListener(place(3));
    });
    document.getElementById("place_battleship").addEventListener("click", function(e) {
        shipType = "BATTLESHIP";
       registerCellListener(place(4));
    });
    btn.addEventListener('click', updateRotate);
    sendXhr("GET", "/game", {}, function(data) {
        game = data;
    });
};
