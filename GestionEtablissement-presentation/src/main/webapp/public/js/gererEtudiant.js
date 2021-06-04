var pathsite = document.location.origin;

document.querySelector('#etudiantSelect').addEventListener("change", function() {

    url = pathsite +'/GestionEtablissement_presentation_war/GetEtudiant?id=' + this.value;
    fetch(url)
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            viderFormulaire();
            remplirFormulaire(data);
        });



});

function remplirFormulaire(data){
    document.getElementById("nom").value = data.firstname;
    document.getElementById("prenom").value = data.lastname;
    document.getElementById("addresse").value = data.address;
    document.getElementById("tel").value = data.phoneNumber;
    document.getElementById("email").value = data.mailAddress;
    document.getElementById("date").value = data.dateOfBirth;
    document.getElementById("password").value = data.password;
}

function viderFormulaire(){
    document.getElementById("nom").value = "";
    document.getElementById("prenom").value = "";
    document.getElementById("addresse").value = "";
    document.getElementById("tel").value = "";
    document.getElementById("email").value = "";
    document.getElementById("date").value = "";
    document.getElementById("password").value = "";
}


let buttondelete = document.querySelector('#delete');

buttondelete.addEventListener('click', ()=>{
    // On récupére la personne sélectionner
    var etudiantSelect = document.getElementById('etudiantSelect');
    let etudiantID = etudiantSelect.value;
    location.href = pathsite +'/GestionEtablissement_presentation_war/DeleteEtudiant?id=' + etudiantID + "&redirect=" + true;
})
