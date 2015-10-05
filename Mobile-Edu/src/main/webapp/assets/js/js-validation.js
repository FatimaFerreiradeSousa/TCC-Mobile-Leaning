function validateFormCadastro() {
    var login = document.forms["formCadastro"]["login"].value;
    
    if (login == null || login == "") {
        document.getElementById("loginNull").innerHTML = "Campo login obrigatório";
        return false;
    }
}

