let input = document.getElementById("input");
let initLabel = document.getElementById("label");

input.addEventListener("change", (event) => {
    const files = changeEvent(event);
    handleUpdate(files);
});

initLabel.addEventListener("mouseover", (event) => {
    event.preventDefault();
    const label = document.getElementById("label");
    label?.classList.add("label--hover");
});

initLabel.addEventListener("mouseout", (event) => {
    event.preventDefault();
    const label = document.getElementById("label");
    label?.classList.remove("label--hover");
});

document.addEventListener("dragenter", (event) => {
    event.preventDefault();
    console.log("dragenter");
    if (event.target.className === "inner") {
        event.target.style.background = "#616161";
    }
});

document.addEventListener("dragover", (event) => {
    console.log("dragover");
    event.preventDefault();
});

document.addEventListener("dragleave", (event) => {
    event.preventDefault();
    console.log("dragleave");
    if (event.target.className === "inner") {
        event.target.style.background = "#3a3a3a";
    }
});

document.addEventListener("drop", (event) => {
    event.preventDefault();
    console.log("drop");
    if (event.target.className === "inner") {
        const files = event.dataTransfer?.files;
        event.target.style.background = "#3a3a3a";
        handleUpdate([...files]);
    }
    console.log(event.target.result)
});

function changeEvent(event){
    const { target } = event;
    return [...target.files];
};

function handleUpdate(fileList,cnt){
    const preview = document.getElementById("preview");

    fileList.forEach((file) => {
        const reader = new FileReader();
        reader.addEventListener("load", (event) => {
            const img = el("img", {
                className: "embed-img",
                src: event.target?.result,
            });
            const imgContainer = el("li", { className: "container-img",onclick:removeImg }, img);
            preview.append(imgContainer);
        });
        reader.readAsDataURL(file);
        console.log(reader);

    });
};
function removeImg(){
    let imgContainerList=document.querySelectorAll(".container-img");
    imgContainerList.forEach((imgContainer)=>{
        imgContainer.onclick=(e)=>{
            console.log("li test");
            console.log(imgContainer);
            imgContainer.remove();
        }
    })
}

function el(nodeName, attributes, ...children) {
    const node =
        nodeName === "fragment"
            ? document.createDocumentFragment()
            : document.createElement(nodeName);

    Object.entries(attributes).forEach(([key, value]) => {
        if (key === "events") {
            Object.entries(value).forEach(([type, listener]) => {
                node.addEventListener(type, listener);
            });
        } else if (key in node) {
            try {
                node[key] = value;
            } catch (err) {
                node.setAttribute(key, value);
            }
        } else {
            node.setAttribute(key, value);
        }
    });

    children.forEach((childNode) => {
        if (typeof childNode === "string") {
            node.appendChild(document.createTextNode(childNode));
        } else {
            node.appendChild(childNode);
        }
    });

    return node;
}

