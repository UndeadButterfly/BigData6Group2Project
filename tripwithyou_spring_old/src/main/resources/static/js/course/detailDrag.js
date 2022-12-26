function dragstart(ev) {
    console.log("dragStart");
    // Change the source element's background color to signify drag has started
    ev.currentTarget.style.border = "dashed";
    // Add the id of the drag source element to the drag data payload so
    // it is available when the drop event is fired
    ev.dataTransfer.setData("text", ev.target.id);
    // Tell the browser both copy and move are possible
    ev.target.classList.add("dragging");
    ev.effectAllowed = "copyMove";
}

function dragover(ev) {
    console.log("dragOver");
    // Change the target element's border to signify a drag over event
    // has occurred
    ev.currentTarget.style.background = "lightblue";
    ev.preventDefault();
    const afterElement = getDragAfterElement(ev.target, e.clientY);
    const draggable = document.querySelector('.dragging')
    // container.appendChild(draggable)
    container.insertBefore(draggable, afterElement)
}
function deleteBtn() {
    const newIds = document.querySelectorAll("#newId");
    newIds.forEach(newId => {
        console.log(newId + ": 삭제버튼 눌림");
        const deleteBtn = newId.querySelector(".delete");
        console.log("deleteBtn:" + deleteBtn);
        deleteBtn.addEventListener("click", e => {
            newId.remove();
        })
    })
}

function drop(ev) {
    console.log("Drop");
    ev.preventDefault();
    // Get the id of drag source element (that was added to the drag data
    // payload by the dragstart event handler)
    var id = ev.dataTransfer.getData("text");
    const dragCopies = document.querySelectorAll(".dragCopy");
    const items = document.querySelectorAll(".item");
    const dests = document.querySelectorAll(".dest");
    const dragBoxes = document.querySelectorAll(".dragBox");
    for (let j = 1; j < dests.length + 1; j++) {
        for (let i = 1; i < items.length + 1; i++) {
            if (id == `src_move${i}` && ev.target.id == `day${j}`) {
                ev.target.appendChild(document.getElementById(id));
            }
        }
    }
    for (let j = 1; j < dests.length + 1; j++) {
        for (let i = 1; i < dragCopies.length + 1; i++) {
            if (id == `src_copy${i}` && ev.target.id == `day${j}`) {
                var nodeCopy = document.getElementById(id).cloneNode(true);
                nodeCopy.id = "newId";
                ev.target.appendChild(nodeCopy);
                nodeCopy.innerHTML += '<button class="btn btn-warning btn-sm delete" type="button">삭제하기</button>';
                deleteBtn();
            }
        }
    }


}
function dragend(ev) {
    console.log("dragEnd");
    // Restore source's border
    ev.target.style.border = "solid black";
    // Remove all of the drag data
    ev.target.classList.remove("dragging");
    ev.dataTransfer.clearData();
}
