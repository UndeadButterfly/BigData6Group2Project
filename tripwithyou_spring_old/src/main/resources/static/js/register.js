function resetClass(element, classname) {
    element.classList.remove(classname);
}

document.getElementsByClassName("show-signup")[0].addEventListener("click", function () {
    let form = document.getElementsByClassName("form")[0];
    resetClass(form, "reset");
    form.classList.add("signup");
    document.getElementById("submit-btn").innerText = "Sign Up";
});
document.getElementsByClassName("show-reset")[0].addEventListener("click", function () {
    let form = document.getElementsByClassName("form")[0];
    resetClass(form, "signup");
    form.classList.add("reset");
    document.getElementById("submit-btn").innerText = "Reset password";
});