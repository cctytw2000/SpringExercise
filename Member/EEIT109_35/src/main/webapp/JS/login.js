document.addEventListener("DOMContentLoaded", function () {
    if (document.getElementById("account").value == "") {
        document.getElementById("account_msg").innerHTML = "<img src='../Images/noway.jpg'>不可空白";
    }
    if (document.getElementById("password").value == "") {
        document.getElementById("passowrd_msg").innerHTML = "<img src='../Images/noway.jpg'>不可空白";
    }
    document.getElementById("account").addEventListener("input", check_account);
    document.getElementById("password").addEventListener("input", check_password);
});
let check_account_ = false;
let check_password_ = false;
function check_account() {
    if (document.getElementById("account").value != "") {
        check_account_ = true;
        document.getElementById("account_msg").innerHTML = "<img src='../Images/yes.jpg'>";
    }
    if (check_account_ && check_password_) {

        document.getElementById("submit").innerHTML = '<input name="login" type="submit" value="登入"> <input name="fergetpwd" type="submit" value="忘記密碼"></input>';

    }
}

function check_password() {
    if (document.getElementById("password").value != "") {
        check_password_ = true;
        document.getElementById("passowrd_msg").innerHTML = "<img src='../Images/yes.jpg'>";
    }
    if (check_account_ && check_password_) {

        document.getElementById("submit").innerHTML = '<input name="login" type="submit" value="登入"> <input name="fergetpwd" type="submit" value="忘記密碼"></input>';

    }
}
