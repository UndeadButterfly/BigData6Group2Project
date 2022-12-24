const draggables = document.querySelectorAll(".draggable");
const containers = document.querySelectorAll(".dragBox");
 
draggables.forEach(draggable => {
  draggable.addEventListener("dragstart", () => {
    draggable.classList.add("dragging");
    dragging.innerHTML='<input type="text" value="memo내용입니다."></input>'
    console.log("dragStart")
  });


  draggable.addEventListener("dragend", () => {
    draggable.classList.remove("dragging");
    console.log("dragEnd");
  });
});
 
containers.forEach(dragBox => {
  dragBox.addEventListener("dragover", e => {
    e.preventDefault();
    const afterElement = getDragAfterElement(dragBox, e.clientX);
    const draggable = document.querySelector(".dragging");
    if (afterElement === undefined) {
      dragBox.appendChild(draggable);
      console.log("append draggable");
    } else {
      dragBox.insertBefore(draggable, afterElement);
    }
  });
});
 
function getDragAfterElement(container, x) {
    console.log("dragElement is called")
  const draggableElements = [
    ...container.querySelectorAll(".draggable:not(.dragging)"),
  ];
 
  return draggableElements.reduce(
    (closest, child) => {
      const box = child.getBoundingClientRect();
      const offset = x - box.left - box.width / 2;
      // console.log(offset);
      if (offset < 0 && offset > closest.offset) {
        return { offset: offset, element: child };
      } else {
        return closest;
      }
    },
    { offset: Number.NEGATIVE_INFINITY },
  ).element;
}
