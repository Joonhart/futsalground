function getAll() {
    var getAll = document.getElementById("getAll");
    if(getAll.classList.contains("btn-dark"))           getAll.classList.remove("btn-dark");
    getAll.classList.add("btn-primary");
    var getRecruit = document.getElementById("getRecruit");
    if (getRecruit.classList.contains("btn-primary"))   getRecruit.classList.remove("btn-primary");
    getRecruit.classList.add("btn-dark");

    $.ajax({
        type: "GET",
        url: "/recruit/",
        data: { ajax : "yes" }
    }).done(function (result) {
        $('#list').replaceWith(result);
    });
}

function getRecruit() {
    var getAll = document.getElementById("getAll");
    if(getAll.classList.contains("btn-primary"))   getAll.classList.remove("btn-primary");
    getAll.classList.add("btn-dark");
    var getRecruit = document.getElementById("getRecruit");
    if(getAll.classList.contains("btn-dark"))    getRecruit.classList.remove("btn-dark");
    getRecruit.classList.add("btn-primary");

    $.ajax({
        type: "GET",
        url: "/recruit/recrutingList"
    }).done(function (result) {
        $('#list').replaceWith(result);
    });
}