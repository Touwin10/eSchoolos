$(document).ready(function (){
    let _deleteId;

    window.showDeleteModalById = function (ref, id) {
        console.log(ref, id)
        $('#' + ref).modal('show');
        _deleteId = id;
    }

    window.onDelete = function (url) {
        location.href = url + _deleteId;
    }


})